package com.edu.ElasticSearch.configution;

import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.WritingConverter;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;

public class LocalDateTimeToLongConverter implements Converter<LocalDateTime, Long> {
    @Override
    public Long convert(LocalDateTime source) {
        return source.toInstant(ZoneOffset.UTC).toEpochMilli();
    }
}