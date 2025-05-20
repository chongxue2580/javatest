package com.xp;

import com.xp.entity.User;
import com.xp.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@SpringBootTest
class MybatisPlusApplicationTests {

    @Autowired
    private UserService userService;

    @Test
    @Transactional // 添加事务支持，测试完成后自动回滚
    void testInsert() {
        User user = new User();
        user.setId(5L);
        user.setUsername("Lucy1");
        user.setPassword("123");
        user.setPhone("18688990011");
        user.setBalance(200);
        user.setInfo("{\"age\": 24, \"intro\": \"英文老师\", \"gender\": \"female\"}");
        user.setCreateTime(LocalDateTime.now());
        user.setUpdateTime(LocalDateTime.now());

        boolean result = userService.save(user);
        assert result : "保存用户失败";
    }
}
