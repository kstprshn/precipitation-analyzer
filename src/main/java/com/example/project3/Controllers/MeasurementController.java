package com.example.project3.Controllers;

import com.example.project3.DTO.MeasurementDTO;
import com.example.project3.DTO.MeasurementsResponse;
import com.example.project3.DTO.mappers.MeasurementDtoMapper;
import com.example.project3.DTO.mappers.MeasurementMapper;
import com.example.project3.Models.Measurement;
import com.example.project3.Services.MeasurementService;
import com.example.project3.util.ErrorsUtil;
import com.example.project3.util.MeasurementErrorResponse;
import com.example.project3.util.MeasurementException;
import com.example.project3.util.MeasurementValidator;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Collectors;

@RestController
@RequestMapping("/measurements")
public class MeasurementController {

    private final MeasurementService measurementService;
    private final MeasurementValidator measurementValidator;
    private final MeasurementMapper measurementMapper;
    private final MeasurementDtoMapper measurementDtoMapper;
    private final ErrorsUtil errorsUtil;


    @Autowired
    public MeasurementController(MeasurementService measurementService,
                                 MeasurementValidator measurementValidator,
                                 MeasurementMapper measurementMapper,
                                 MeasurementDtoMapper measurementDtoMapper,
                                 ErrorsUtil errorsUtil) {
        this.measurementService = measurementService;
        this.measurementValidator = measurementValidator;
        this.measurementMapper = measurementMapper;
        this.measurementDtoMapper = measurementDtoMapper;
        this.errorsUtil = errorsUtil;
    }

    @PostMapping("/add")
    public ResponseEntity<HttpStatus> add(@RequestBody @Valid MeasurementDTO measurementDTO,
                                          BindingResult bindingResult) {
        Measurement measurementToAdd = measurementDtoMapper.convert(measurementDTO);
        measurementValidator.validate(measurementToAdd, bindingResult);
        if (bindingResult.hasErrors())
            errorsUtil.returnErrorsToClient(bindingResult);
        measurementService.createMeasurement(measurementToAdd);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @GetMapping("/rainyDaysCount")
    public Long getRainyDaysCount() {
        return measurementService.findAll().stream().filter(Measurement::getIsRaining).count();
    }

    @GetMapping()
    public MeasurementsResponse getMeasurements() {
        return new MeasurementsResponse(measurementService.findAll().stream().map(measurementMapper::convert)
                .collect(Collectors.toList()));
    }

    @ExceptionHandler
    private ResponseEntity<MeasurementErrorResponse> handlerException(MeasurementException e) {
        var response = new MeasurementErrorResponse(
                e.getMessage(), System.currentTimeMillis()
        );
        return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
    }
}
