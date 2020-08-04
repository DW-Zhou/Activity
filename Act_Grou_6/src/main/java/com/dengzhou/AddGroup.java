package com.dengzhou;

import org.activiti.engine.IdentityService;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.identity.Group;

import java.util.UUID;

public class AddGroup {
    public static void main(String[] args) {
        ProcessEngine engine = ProcessEngines.getDefaultProcessEngine();
        // 获取用户身份服务
        IdentityService identityService = engine.getIdentityService();
       /* String genId = UUID.randomUUID().toString();
        // 调用newGroup
        Group group = identityService.newGroup(genId);
        group.setName("经理组");
        group.setType("manager");
        identityService.saveGroup(group);

        // 查询用户组
        org.activiti.engine.identity.Group data = identityService.createGroupQuery().groupId(genId).singleResult();
        System.out.println("Group ID:" + data.getId() + ",Name: " + data.getName()+"---"+data.getType());*/

        // 修改用户组，先查在修改
        Group data = identityService.createGroupQuery().groupId("a300c606-6133-4d3d-ab06-6923a16565b3").singleResult();
        data.setName("经理组2");
        identityService.saveGroup(data);
    }
}
