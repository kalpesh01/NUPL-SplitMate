package com.splitmate.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "expense_splits", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"expense_id", "user_id"})})
public class ExpenseSplit {

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "split_amount")
    private double splitAmount;

    @Column(name = "payment_status")
    private double paymentStatus;

    @ManyToOne
    @JoinColumn(name = "expense_id")
    private Expense expense;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;


}

