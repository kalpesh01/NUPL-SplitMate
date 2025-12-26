package com.splitmate.db.dao;

import com.splitmate.db.dao.custom.UserDAOCustom;
import com.splitmate.db.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDAO extends JpaRepository<UserEntity,Long>, UserDAOCustom {

}
