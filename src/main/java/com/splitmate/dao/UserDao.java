package com.splitmate.dao;

import com.splitmate.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDao extends JpaRepository<User,Long>{   // rename as Dao
    boolean existsByEmail(String email);
}
