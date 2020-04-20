package com.greenlight.common.date

import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import java.time.format.DateTimeFormatter

@Configuration
class JacksonJava8DateConfig {

    companion object {
        private const val DATE_FORMAT = "dd/MM/yyyy";
        private const val DATE_TIME_FORMAT = "dd/MM/yyyy HH:mm";
    }

    @Bean
    fun localDateCustomizer(): Jackson2ObjectMapperBuilderCustomizer {
        return Jackson2ObjectMapperBuilderCustomizer {
            it.simpleDateFormat(DATE_FORMAT)
            it.serializers(LocalDateSerializer(DateTimeFormatter.ofPattern(DATE_FORMAT)))
            it.serializers(LocalDateTimeSerializer(DateTimeFormatter.ofPattern(DATE_TIME_FORMAT)))
            it.deserializers(LocalDateDeserializer(DateTimeFormatter.ofPattern(DATE_FORMAT)))
            it.deserializers(LocalDateTimeDeserializer(DateTimeFormatter.ofPattern(DATE_TIME_FORMAT)))
        }
    }
}