package com.splitmate.db.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "expense")
@Getter
@Setter
public class ExpenseEntity {

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
    private GroupEntity groupEntity;

    @ManyToOne
    @JoinColumn(name = "ref_user")
    private UserEntity paidOutBy;

    @OneToMany(mappedBy = "expenseEntity",
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    private List<ExpenseSplitEntity> splits = new ArrayList<>();

    @Override
    public boolean equals(Object obj)
    {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (!this.getClass().isInstance(obj) && !obj.getClass().isInstance(this))
            return false;

        ExpenseEntity other = (ExpenseEntity) obj;
        return id != null && id.equals(other.getId());
    }

    @Override
    public int hashCode() {
        return 40;
    }
}
