package com.greenlight.eventservice.config.r2dbc


import com.fasterxml.jackson.databind.ObjectMapper
import com.greenlight.eventservice.config.r2dbc.converter.JsonPointConverter
import com.greenlight.eventservice.config.r2dbc.converter.PointJsonConverter
import io.r2dbc.spi.ConnectionFactories
import io.r2dbc.spi.ConnectionFactory
import org.springframework.boot.autoconfigure.r2dbc.R2dbcProperties
import org.springframework.context.annotation.Configuration
import org.springframework.data.r2dbc.config.AbstractR2dbcConfiguration

@Configuration
class R2dbcConfiguration(private val properties: R2dbcProperties, private val mapper: ObjectMapper) :
    AbstractR2dbcConfiguration() {
    override fun connectionFactory(): ConnectionFactory {
        return ConnectionFactories.get(properties.determineUrl())
    }


    override fun getCustomConverters(): MutableList<Any> {
        return mutableListOf(JsonPointConverter(mapper), PointJsonConverter(mapper))
    }

}