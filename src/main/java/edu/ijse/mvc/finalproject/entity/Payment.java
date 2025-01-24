package edu.ijse.mvc.finalproject.entity;

import lombok.*;

import java.sql.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Payment {
    private String payment_id;
    private Date payment_date;
    private String admin_id;
}
