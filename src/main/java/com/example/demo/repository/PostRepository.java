package com.example.demo.repository;

import com.example.demo.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface PostRepository extends JpaRepository<Post,Integer> {
    Post findPostByIdPost(Integer id);

    @Query(value = "select * from Post where id_user = :iduser", nativeQuery = true)
    List<Post> findAllByIdUser(@Param("iduser") int idUser);

    List<Post> findAllByStatus(int status);

    @Transactional
    @Modifying
    @Query(value = "update post set status = 1 where id_post = :idpost",nativeQuery = true)
    void updateStatusPost(@Param("idpost") int idpost);
}
