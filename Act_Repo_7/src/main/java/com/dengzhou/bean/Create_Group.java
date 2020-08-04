package com.dengzhou.bean;

import org.activiti.engine.IdentityService;
import org.activiti.engine.identity.Group;

public class Create_Group {
    public Group create_group(IdentityService identityService,String id,String name,String type){
        Group group = identityService.newGroup(id);
        group.setName(name);
        group.setType(type);
        identityService.saveGroup(group);
        return identityService.createGroupQuery().groupId(id).singleResult();
    }
}
