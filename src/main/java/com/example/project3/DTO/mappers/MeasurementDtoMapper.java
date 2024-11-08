package com.example.project3.DTO.mappers;

import com.example.project3.DTO.MeasurementDTO;
import com.example.project3.Models.Measurement;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = "spring")
public interface MeasurementDtoMapper extends Converter<MeasurementDTO, Measurement> {
    @Mapping(target = "isRaining", source = "isRaining")
    Measurement convert(MeasurementDTO measurementDTO);
}
