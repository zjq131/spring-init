package com.zjq.init.user;

import com.zjq.init.module.domain.User;
import com.zjq.init.module.dto.UserDTO;
import com.zjq.init.service.UserService;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.List;

@SpringBootTest
public class UserTest {

    @Resource
    private UserService userService;

    @Test
    void testLink(){
        List<User> list = userService.list();
        list.forEach(System.out::println);
    }

    @Test
    void testRegister(){
        UserDTO userDTO = new UserDTO();
        userDTO.setUsername("test");
        userDTO.setUserPassword("1234567");
        userDTO.setAckPassword("1234567");
        Assertions.assertTrue(userService.register(userDTO));
    }
}
