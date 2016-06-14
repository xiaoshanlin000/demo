package com.shanlin.demo.database.repository;

import com.shanlin.demo.database.entry.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Shanlin on 16/6/4.
 */
public interface UserRepository extends JpaRepository<User,Integer> {

    User findByUsername(String username);
}
