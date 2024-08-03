package com.project.Shop.entity;

import com.project.Shop.entity.enumClass.PaymentMethodName;
import lombok.*;
import org.hibernate.annotations.Nationalized;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "PaymentMethod")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaymentMethod implements Serializable {
    @Id
    private Long id;

    @Nationalized
    @Enumerated(EnumType.STRING)
    private PaymentMethodName name;
    private int status;
}