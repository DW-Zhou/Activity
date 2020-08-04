package com.dengzhou.User63;

import org.activiti.engine.IdentityService;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.identity.User;
import org.junit.Test;

import java.util.UUID;

public class AddUser {
    ProcessEngine engine = ProcessEngines.getDefaultProcessEngine();

    @Test
    public void addUser(){
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
    @Test
    public void DeleteUser(){
        ProcessEngine engine = ProcessEngines.getDefaultProcessEngine();
        IdentityService identityService = engine.getIdentityService();
        // 计算有多少条数据
        long count = identityService.createUserQuery().count();
        System.out.println("ACT_ID_USER: 有"+count+"条数据");
        identityService.deleteUser("041328df-cd36-4670-91d0-af75a97db363");
        long count1 = identityService.createUserQuery().count();
        System.out.println("删除一条之后，ACT_ID_USER: 有"+count1+"条数据");

    }

    @Test
    public void checkPassword(){
        IdentityService identityService = engine.getIdentityService();
        String userid = UUID.randomUUID().toString();
        User user = identityService.newUser(userid);
        user.setLastName("刘");
        user.setFirstName("名");
        user.setEmail("15170047205@163.com");
        user.setPassword("abc");
        identityService.saveUser(user);

        //验证用户密码
        System.out.println("验证密码结果：" + identityService.checkPassword(userid,"abc"));
        System.out.println("验证用户的密码：" + identityService.checkPassword(userid,"cc"));

    }
}
