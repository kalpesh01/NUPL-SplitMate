package com.splitmate.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
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

    @NotNull
    @Column(name = "amount")
    private double amount;

    @Column(name = "expense_date")
    private LocalDate expenseDate;

    @ManyToOne
    @JoinColumn(name = "ref_group")
    private Group group;

    @ManyToOne
    @JoinColumn(name = "ref_user")
    private User paidOutBy;

    @OneToMany(mappedBy = "expense",
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    private List<ExpenseSplit> splits = new ArrayList<>();
}
