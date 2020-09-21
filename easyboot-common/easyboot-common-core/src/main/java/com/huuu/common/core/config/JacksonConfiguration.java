package com.huuu.common.core.config;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalTimeSerializer;
import com.huuu.common.core.constant.DateConstants;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * Jackson 配置
 * @author chenzhenhu
 */
@Configuration
public class JacksonConfiguration {

    @Bean
    public Jackson2ObjectMapperBuilderCustomizer customizer() {
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern(DateConstants.DEFAULT_TIME_FORMAT);
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern(DateConstants.DEFAULT_DATE_FORMAT);
        DateTimeFormatter datetimeFormatter = DateTimeFormatter.ofPattern(DateConstants.DEFAULT_DATE_TIME_FORMAT);

        return builder -> builder
                .featuresToDisable(SerializationFeature.FAIL_ON_EMPTY_BEANS,
                        SerializationFeature.WRITE_DATES_AS_TIMESTAMPS,
                        DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES)
                .serializerByType(LocalTime.class, new LocalTimeSerializer(timeFormatter))
                .serializerByType(LocalDate.class, new LocalDateSerializer(dateFormatter))
                .serializerByType(LocalDateTime.class, new LocalDateTimeSerializer(datetimeFormatter))
                .deserializerByType(LocalTime.class, new LocalTimeDeserializer(timeFormatter))
                .deserializerByType(LocalDate.class, new LocalDateDeserializer(dateFormatter))
                .deserializerByType(LocalDateTime.class, new LocalDateTimeDeserializer(datetimeFormatter));
    }
}
