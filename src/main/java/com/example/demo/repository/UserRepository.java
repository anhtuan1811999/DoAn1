package com.example.demo.repository;

import com.example.demo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.w3c.dom.stylesheets.LinkStyle;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User,Integer> {

    @Query(value = "select*from User where id_user = :idUser and last_name = :lastName",nativeQuery = true)
    User returnUserByIdAndLastname(@Param("idUser") int id,@Param("lastName") String lastName);

    @Query(value = "select * from User where user_name = :username",nativeQuery = true)
    User findUserByUser_name(@Param("username") String username);

    @Query(value = "select * from User where user_name = :username and password = :password",nativeQuery = true)
    User findUserByUser_nameAndPassword( @Param("username") String username,@Param("password") String password);

    User findUserByIdUser(int id);

    @Query(value = "update user set address = :address where user_name = :username",nativeQuery = true)
    void updateUserByUsername(@Param("address")String address,@Param("username")String username);

    List<User> findAll();

    @Query(value = "select user_name from user",nativeQuery = true)
    List<String> returnUsernameFromUser();
}
