package com.example.project3.Models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "measurement")
@NoArgsConstructor
@Data
public class Measurement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private long id;

    @Column(name="value")
    @NotNull
    @Min(-100)
    @Max(100)
    private Double value;

    @NotNull
    @Column(name="raining")
    private Boolean isRaining;

    @NotNull
    @Column(name="measure_date_time")
    private LocalDateTime measure_date_time;

    @ManyToOne
    @JoinColumn(name = "sensor", referencedColumnName = "name")
    private Sensor sensor;


}
