package com.greenlight.common.rsocket.factory

import com.greenlight.common.rsocket.RSocketMimeType
import io.rsocket.RSocketFactory
import io.rsocket.client.LoadBalancedRSocketMono
import io.rsocket.client.filter.RSocketSupplier
import io.rsocket.frame.decoder.PayloadDecoder
import io.rsocket.transport.netty.client.TcpClientTransport
import org.slf4j.LoggerFactory
import org.springframework.cloud.client.discovery.DiscoveryClient
import org.springframework.http.MediaType
import org.springframework.messaging.rsocket.RSocketRequester
import org.springframework.messaging.rsocket.RSocketStrategies
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import reactor.util.retry.Retry
import java.time.Duration

class RSocketClientFactory {
    companion object {
        private val log = LoggerFactory.getLogger(RSocketClientFactory::class.java)
    }

    fun createRSocketClient(
        serviceName: String,
        discoveryClient: DiscoveryClient,
        rSocketStrategies: RSocketStrategies
    ): Mono<RSocketRequester> {
        val hosts = discoveryClient.getInstances(serviceName)
            .map { IpPortHolder(it.metadata[RSocketEurekaInstanceProperties.SERVER_PORT.value()]!!.toInt(), it.host) }
            .map { host ->
                RSocketSupplier {
                    RSocketFactory.connect()
                        .mimeType(RSocketMimeType.RSOCKET_ROUTING, MediaType.APPLICATION_CBOR_VALUE)
                        .frameDecoder(PayloadDecoder.ZERO_COPY)
                        .transport(TcpClientTransport.create(host.ipAddress, host.port))
                        .start()
                        .retryWhen(Retry.backoff(5,Duration.ofSeconds(5)))
                        .doOnSubscribe { log.info("RSocket connection established: $it") }
                }
            }

        return LoadBalancedRSocketMono.create(Flux.just(hosts)).map {
            RSocketRequester.wrap(
                it,
                MediaType.APPLICATION_CBOR,
                MediaType.valueOf(RSocketMimeType.RSOCKET_ROUTING),
                rSocketStrategies
            )
        }
    }

    private data class IpPortHolder(val port: Int, val ipAddress: String)
}