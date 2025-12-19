package com.splitmate.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "user")
@Getter
@Setter
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotNull
    @Column(name = "name")
    private String name;

    @NotNull
    @Column(name = "email", unique = true)
    private String email;

    @OneToMany(mappedBy = "user")
    private List<GroupMember> memberships;

    @OneToMany(mappedBy = "paidOutBy")
    private List<Expense> expensesPaid;

    @Override
    public boolean equals(Object obj)
    {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (!this.getClass().isInstance(obj) && !obj.getClass().isInstance(this))
            return false;

        User other = (User) obj;
        return id != null && id.equals(other.getId());
    }

    @Override
    public int hashCode() {
        return 40;
    }
}
