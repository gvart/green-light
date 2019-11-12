package com.greenlight.event.utility.converter

import com.fasterxml.jackson.databind.ObjectMapper
import com.greenlight.event.domain.Point
import io.r2dbc.postgresql.codec.Json
import org.springframework.core.convert.converter.Converter

class ToJsonConverter<T>(private val objectMapper: ObjectMapper) : Converter<T, Json> {
    override fun convert(source: T) = Json.of(objectMapper.writeValueAsBytes(source))
}