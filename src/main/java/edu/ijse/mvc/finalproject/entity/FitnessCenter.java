package edu.ijse.mvc.finalproject.entity;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class FitnessCenter {
    private String center_id;
    private String admin_id;
    private String center_name;
    private String location;
}
