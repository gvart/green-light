//package com.greenlight.event.config
//
//import com.fasterxml.jackson.databind.ObjectMapper
//import com.greenlight.event.domain.Point
//import com.greenlight.event.utility.converter.FromJsonConverter
//import com.greenlight.event.utility.converter.ToJsonConverter
//import io.r2dbc.postgresql.PostgresqlConnectionConfiguration
//import io.r2dbc.postgresql.PostgresqlConnectionFactory
//import io.r2dbc.spi.ConnectionFactory
//import org.springframework.boot.autoconfigure.r2dbc.R2dbcProperties
//import org.springframework.context.annotation.Bean
//import org.springframework.context.annotation.Configuration
//import org.springframework.data.r2dbc.config.AbstractR2dbcConfiguration
//import org.springframework.data.r2dbc.convert.R2dbcCustomConversions
//
//@Configuration
//class PostgresConfig(private val objectMapper: ObjectMapper, private val connectionFactory: ConnectionFactory): AbstractR2dbcConfiguration() {
//    override fun connectionFactory(): ConnectionFactory {
//        val configuration = PostgresqlConnectionConfiguration.builder()
//            .applicationName(r2dbcProperties.name).connectTimeout(r2dbcProperties.exte)
//            .build()
//        return PostgresqlConnectionFactory(configuration)
//    }
//
//    @Bean
//    override fun r2dbcCustomConversions(): R2dbcCustomConversions {
//        val converters = listOf(FromJsonConverter(objectMapper), ToJsonConverter<Point>(objectMapper))
//        return R2dbcCustomConversions(storeConversions, converters)
//    }
//}