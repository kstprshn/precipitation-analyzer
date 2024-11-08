package com.example.project3.DTO;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class SensorDTO {

    @NotEmpty(message = "The name should not be empty")
    @Size(min = 3, max = 30, message = "Sensor's name should be from 3 to 30 characters")
    private String name;
}
