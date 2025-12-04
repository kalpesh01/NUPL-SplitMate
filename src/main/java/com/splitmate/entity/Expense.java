package com.splitmate.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "expenses")
@Getter
@Setter
public class Expense {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "description")
    private String description;

    @Column(name = "amount")
    private double amount;

    @ManyToOne
    @JoinColumn(name = "group_id")
    private Group group;

    @ManyToOne
    @JoinColumn(name = "paid_by")
    private User paidBy;

    @OneToMany(mappedBy = "expense")
    private List<ExpenseSplit> splits;
}
