package com.dengzhou.DataSearch62;

import org.activiti.engine.IdentityService;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.identity.Group;

import java.util.List;


public class ListData {
    public static void main(String[] args) {
        ProcessEngine engine = ProcessEngines.getDefaultProcessEngine();
        IdentityService identityService = engine.getIdentityService();
       /* createGroup(identityService,"1","GroupA","typeA");
        createGroup(identityService,"2","GroupB","typeB");
        createGroup(identityService,"3","GroupC","typeC");
        createGroup(identityService,"4","GroupD","typeD");
        createGroup(identityService,"5","GroupE","typeE");

        List<Group> list = identityService.createGroupQuery().list();
        for (Group group : list){
            System.out.println(group.getId()+"-----"+group.getName());
        }*/
        Group group = identityService.createGroupQuery().groupId("1").singleResult();
        group.setName("经理组A");
        identityService.saveGroup(group);

    }

    static void createGroup(IdentityService identityService,String id,String name,String type){
        Group group = identityService.newGroup(id);
        group.setName(name);
        group.setType(type);
        identityService.saveGroup(group);
    }
}
