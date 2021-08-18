package com.example.demo.other;

import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class CheckAccount {

    public boolean checkAccExist (String name, List<String> usernames){


        int size = usernames.size();
        int index = 0;
        for (int i = 0;i<size;i++){
            if (usernames.get(i).equals(name)){
                index++;
            }
        }
        if (index == 0){
            return true;
        } else {
            return false;
        }
    }
}
