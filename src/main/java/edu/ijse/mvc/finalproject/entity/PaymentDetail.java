package edu.ijse.mvc.finalproject.entity;

import lombok.*;

import java.sql.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class PaymentDetail {
    String payment_id;
    String member_id;
    String member_name;
    Date payment_date;
    double price;
    String payment_method;
    String month;
}
