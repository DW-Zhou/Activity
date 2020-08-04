package com.dengzhou;

import org.activiti.engine.IdentityService;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.identity.Group;

import java.util.UUID;

public class DeleteGroup {
    public static void main(String[] args) {
        ProcessEngine engine = ProcessEngines.getDefaultProcessEngine();
        // 获取用户的服务组件
        IdentityService identityService = engine.getIdentityService();
        String genId = UUID.randomUUID().toString();

        // 创建用户组实例
        Group group = identityService.newGroup(genId);
        group.setName("经理组3");
        group.setType("manger");
        // 存储到数据库
        identityService.saveGroup(group);
        // 查询用户组 数量
        System.out.println("保存后用户组数量："+identityService.createGroupQuery().count());
        // 根据ID 删除用户组
        identityService.deleteGroup(genId);

        System.out.println("删除后用户组数量："+identityService.createGroupQuery().count());
    }
}
