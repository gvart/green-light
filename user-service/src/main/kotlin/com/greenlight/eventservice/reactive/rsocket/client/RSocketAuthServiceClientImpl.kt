//package com.greenlight.eventservice.reactive.rsocket.client
//
//import com.greenlight.eventservice.config.RSocketConfig.Companion.AUTH_SERVICE_CLIENT_BEAN_ID
//import com.greenlight.eventservice.reactive.service.AuthServiceClient
//import com.greenlight.eventservice.transfer.user.UserAuthRequest
//import org.springframework.beans.factory.annotation.Qualifier
//import org.springframework.messaging.rsocket.RSocketRequester
//import org.springframework.stereotype.Component
//import reactor.core.publisher.Mono
//
//@Component
//class RSocketAuthServiceClientImpl(
//    @Qualifier(AUTH_SERVICE_CLIENT_BEAN_ID) private val userServiceClient: Mono<RSocketRequester>
//) : AuthServiceClient {
//
//    override fun createUserAccount(username: UserAuthRequest): Mono<Unit> {
//        return userServiceClient.flatMap {
//            it.route("user/create/regular")
//                .data(username)
//                .retrieveMono(Unit::class.java)
//                .onErrorMap { exception -> exception }
//        }
//    }
//}