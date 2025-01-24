package edu.ijse.mvc.finalproject.entity;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class PaymentPlan {
    String plan_id;
    String plan_name;
    double price;
}
