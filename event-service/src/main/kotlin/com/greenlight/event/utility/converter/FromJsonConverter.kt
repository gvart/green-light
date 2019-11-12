package com.greenlight.event.utility.converter

import com.fasterxml.jackson.databind.ObjectMapper
import com.greenlight.event.domain.Point
import io.r2dbc.postgresql.codec.Json
import org.springframework.core.convert.converter.Converter

class FromJsonConverter(private val objectMapper: ObjectMapper) : Converter<Json, Point> {
    override fun convert(source: Json): Point = objectMapper.readValue(source.asArray(), Point::class.java)

}