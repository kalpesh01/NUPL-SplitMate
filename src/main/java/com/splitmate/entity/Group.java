package com.splitmate.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.List;

@EntityListeners(AuditingEntityListener.class)
@Entity
@Table(name = "groups")
@Getter
@Setter
public class Group {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotNull
    @Column(name = "name")
    private String name;

    @CreationTimestamp
    @Column(name = "created_date", updatable = false)
    private String createdDate;

    @OneToMany(mappedBy = "group")
    private List<GroupMember> members;

    @OneToMany(mappedBy = "group")
    private List<Expense> expenses;
}
