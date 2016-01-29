package com.zt.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.zt.dao.UserDao;
import com.zt.entity.User;

/**
 * Repository 继承定义好的接口
 * 
 * @author zt
 *
 */
public interface UserRepository extends JpaRepository<User, Long>, UserDao {

}
