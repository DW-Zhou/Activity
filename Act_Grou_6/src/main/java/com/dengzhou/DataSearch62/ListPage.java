package com.dengzhou.DataSearch62;

import org.activiti.engine.IdentityService;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.identity.Group;
import org.activiti.engine.identity.GroupQuery;

import java.util.List;

public class ListPage {
    public static void main(String[] args) {
        ProcessEngine engine = ProcessEngines.getDefaultProcessEngine();
        IdentityService identityService = engine.getIdentityService();

        List<Group> groups = identityService.createGroupQuery().listPage(2, 3);
        for (Group group : groups){
            System.out.println(group.getName());
        }

        // 查询全部数据的数量
        long count = identityService.createGroupQuery().count();
        System.out.println("Group的数量："+count);
        System.out.println("--------------------");
        // 排序查询 desc 降序  asc升序
        List<Group> list = identityService.createGroupQuery().orderByGroupName().desc().list();
        for (Group group : list){
            System.out.println(group.getName());
        }

    }
}
