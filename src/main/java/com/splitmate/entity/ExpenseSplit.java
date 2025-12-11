package com.splitmate.entity;

import com.splitmate.enums.PaymentStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "expense_splits")
@Getter
@Setter
public class ExpenseSplit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "split_amount")
    private double splitAmount;

    @Enumerated(EnumType.STRING)
    @Column(name = "payment_status", length = 50)
    private PaymentStatus paymentStatus;

    @ManyToOne
    @JoinColumn(name = "ref_expense")
    private Expense expense;

    @ManyToOne
    @JoinColumn(name = "ref_user")
    private User ownBy;


}

