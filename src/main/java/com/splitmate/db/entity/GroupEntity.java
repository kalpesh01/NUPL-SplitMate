package com.splitmate.db.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "user_group")
@Getter
@Setter
public class GroupEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotNull
    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "groupEntity",
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    private List<GroupMemberEntity> members  = new ArrayList<>();;

    @OneToMany(mappedBy = "groupEntity",
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    private List<ExpenseEntity> expense = new ArrayList<>();

    @Override
    public boolean equals(Object obj)
    {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (!this.getClass().isInstance(obj) && !obj.getClass().isInstance(this))
            return false;

        GroupEntity other = (GroupEntity) obj;
        return id != null && id.equals(other.getId());
    }

    @Override
    public int hashCode() {
        return 40;
    }
}
