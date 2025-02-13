package edu.ijse.mvc.finalproject.dto;

import lombok.*;

import java.util.ArrayList;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ScheduleDto {
    String schedule_id;
    String name;
    String admin_id;
    ArrayList<ExerciseScheduleDto> exerciseScheduleDtos;

    public ScheduleDto(String scheduleId, String name, String adminId) {
        this.schedule_id = scheduleId;
        this.name = name;
        this.admin_id = adminId;
    }
}
