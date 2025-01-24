package edu.ijse.mvc.finalproject.entity;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class SignIn {
    private String adminId;
    private String name;
    private String address;
    private String password;
}
