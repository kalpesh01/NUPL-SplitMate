package com.splitmate.db.dao;

import com.splitmate.db.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDao extends JpaRepository<UserEntity,Long>{   // rename as Dao
    boolean existsByEmail(String email);
}
