package edu.ijse.mvc.finalproject.entity;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class DietPlan {
    private String diet_plan_id;
    private String admin_id;
    private String name;
    private String duration;
    private String description;
}
