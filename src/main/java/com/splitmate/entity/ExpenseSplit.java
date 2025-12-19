package com.splitmate.entity;

import com.splitmate.enums.PaymentStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "expense_split")
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

    @Override
    public boolean equals(Object obj)
    {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (!this.getClass().isInstance(obj) && !obj.getClass().isInstance(this))
            return false;

        ExpenseSplit other = (ExpenseSplit) obj;
        return id != null && id.equals(other.getId());
    }

    @Override
    public int hashCode() {
        return 40;
    }
}

