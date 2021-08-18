package com.example.demo.repository;


import com.example.demo.entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface CartRepository extends JpaRepository<Cart,Integer> {
    List<Cart> findAllByIdUser(int idUser);

    Cart findCartByIdPost(int idPost);

    @Transactional
    void deleteCartByIdPost(int idPost);
}
