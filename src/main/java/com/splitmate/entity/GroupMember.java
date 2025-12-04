package com.splitmate.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "group_members", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"group_id", "user_id"})})
public class GroupMember {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "group_id")
    private Group group;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
