package com.splitmate.db.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


@Entity
@Table(name = "group_member")
@Setter
@Getter
public class GroupMemberEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "ref_group")
    private GroupEntity groupEntity;

    @ManyToOne
    @JoinColumn(name = "ref_user")
    private UserEntity user;

    @Override
    public boolean equals(Object obj)
    {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (!this.getClass().isInstance(obj) && !obj.getClass().isInstance(this))
            return false;

        GroupMemberEntity other = (GroupMemberEntity) obj;
        return id != null && id.equals(other.getId());
    }

    @Override
    public int hashCode() {
        return 40;
    }
}
