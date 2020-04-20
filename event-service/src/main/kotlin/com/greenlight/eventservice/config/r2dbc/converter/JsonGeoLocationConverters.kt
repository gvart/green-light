package com.greenlight.eventservice.config.r2dbc.converter

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import com.greenlight.eventservice.domain.GeoLocation
import io.r2dbc.postgresql.codec.Json
import org.springframework.core.convert.converter.Converter
import org.springframework.data.convert.ReadingConverter
import org.springframework.data.convert.WritingConverter

@ReadingConverter
class JsonGeoLocationConverter(private val mapper: ObjectMapper) : Converter<Json, GeoLocation> {

    override fun convert(source: Json): GeoLocation = mapper.readValue(source.asArray())
}

@WritingConverter
class GeoLocationJsonConverter(private val mapper: ObjectMapper) : Converter<GeoLocation, Json> {

    override fun convert(source: GeoLocation): Json = Json.of(mapper.writeValueAsBytes(source))
}