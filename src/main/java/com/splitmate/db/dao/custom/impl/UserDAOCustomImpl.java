package com.splitmate.db.dao.custom.impl;

import com.querydsl.jpa.impl.JPAQuery;
import com.splitmate.db.dao.custom.UserDAOCustom;
import com.splitmate.db.entity.QUserEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

public class UserDAOCustomImpl implements UserDAOCustom {


    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public boolean existsByEmail(String email) {
        QUserEntity qUserEntity = QUserEntity.userEntity;

        JPAQuery<Boolean> jpaQuery = new JPAQuery<>(entityManager);
        return jpaQuery.select(qUserEntity)
                .from(qUserEntity)
                .where(qUserEntity.email.eq(email))
                .fetchFirst() != null;
    }
}
