package com.greenlight.eventservice.config.r2dbc.converter

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import com.greenlight.eventservice.domain.Point
import io.r2dbc.postgresql.codec.Json
import org.springframework.core.convert.converter.Converter
import org.springframework.data.convert.ReadingConverter
import org.springframework.data.convert.WritingConverter

@ReadingConverter
class JsonPointConverter(private val mapper: ObjectMapper) : Converter<Json, Point> {

    override fun convert(source: Json): Point = mapper.readValue(source.asArray())
}

@WritingConverter
class PointJsonConverter(private val mapper: ObjectMapper) : Converter<Point, Json> {

    override fun convert(source: Point): Json = Json.of(mapper.writeValueAsBytes(source))
}