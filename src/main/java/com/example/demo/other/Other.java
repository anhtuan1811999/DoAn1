package com.example.demo.other;

import com.example.demo.entity.Cart;
import com.example.demo.entity.Post;

import javax.xml.crypto.Data;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class Other {
    public static LocalDateTime getDateTimeNow(){
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-mm-dd hh:mm:ss");
        return LocalDateTime.now();
    }

    public boolean isPostExist(Post post, List<Cart> cartList) {
        int size = cartList.size();
        int idpost = post.getIdPost();
        int count = 0;
        for (int i=0; i<size; i++){
            if (idpost==cartList.get(i).getIdPost()) {
                count++;
            }
        }
        if (count==0) {
            return false;
        } else {
            return true;
        }
    }
}
