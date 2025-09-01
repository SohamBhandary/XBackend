package com.Soham.X.Repository;

import com.Soham.X.Entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    User findByEmail(String email);

    @Query("SELECT DISTINCT u FROM User u WHERE u.fullName LIKE CONCAT('%', :query, '%') OR u.email LIKE CONCAT('%', :query, '%')")
    public List<User> search(@Param("query") String query);

}
