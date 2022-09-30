package com.infobip.bipcars.entities;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.DayOfWeek;
import java.time.LocalTime;

@Data
@Entity
public class WorkingHour {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    @NotNull
    @Column(unique = true)
    private DayOfWeek day;

    @NotNull
    private LocalTime open;

    @NotNull
    private LocalTime close;
}
