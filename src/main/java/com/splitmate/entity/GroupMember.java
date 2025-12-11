package com.splitmate.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


@Entity
@Table(name = "group_members")
@Setter
@Getter
public class GroupMember {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "ref_group")
    private Group group;

    @ManyToOne
    @JoinColumn(name = "ref_user")
    private User user;
}
