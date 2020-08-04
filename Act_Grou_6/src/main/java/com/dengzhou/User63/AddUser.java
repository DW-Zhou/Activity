package com.dengzhou.User63;

import org.activiti.engine.IdentityService;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.identity.User;

import java.util.UUID;

public class AddUser {
    public static void main(String[] args) {
        ProcessEngine engine = ProcessEngines.getDefaultProcessEngine();
        IdentityService identityService = engine.getIdentityService();
        // 创建用户实例
        String userId = UUID.randomUUID().toString();
        User user = identityService.newUser(userId);
        user.setFirstName("刚");
        user.setLastName("王");
        user.setEmail("12345678@qq.com");
        user.setPassword("123");
        identityService.saveUser(user);

        user = identityService.createUserQuery().userId(userId).singleResult();
        System.out.println(user.getEmail());

    }
}
