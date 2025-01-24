package edu.ijse.mvc.finalproject.entity;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Admin {
    private String admin_id;
    private String name;
    private String address;
    private String password;
}
