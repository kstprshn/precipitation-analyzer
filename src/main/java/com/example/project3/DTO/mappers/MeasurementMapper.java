package com.example.project3.DTO.mappers;

import com.example.project3.DTO.MeasurementDTO;
import com.example.project3.Models.Measurement;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = "spring")
public interface MeasurementMapper extends Converter<Measurement, MeasurementDTO> {
    @Mapping(target = "isRaining", source = "isRaining")
    MeasurementDTO convert(Measurement measurement);
}
