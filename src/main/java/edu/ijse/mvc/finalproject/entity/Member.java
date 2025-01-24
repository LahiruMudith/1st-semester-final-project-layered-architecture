package edu.ijse.mvc.finalproject.entity;

import lombok.*;

import java.sql.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Member {
    private String member_id;
    private String name;
    private String address;
    private String phone_number;
    private String email;
    private Date register_date;
    private double weight;
    private double height;
    private String schedule_id;
    private String plan_id;
    private String diet_plan_id;

}
