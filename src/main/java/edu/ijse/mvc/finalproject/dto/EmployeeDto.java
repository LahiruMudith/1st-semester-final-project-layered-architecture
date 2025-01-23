package edu.ijse.mvc.finalproject.dto;

import lombok.*;

import java.sql.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class EmployeeDto {
    private String employee_id;
    private String center_id;
    private String name;
    private String phone_number;
    private Date date_of_hire;
    private String position;
    private int age;
    private String address;
}
