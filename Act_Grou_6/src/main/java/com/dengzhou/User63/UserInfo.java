package com.dengzhou.User63;

import org.activiti.engine.IdentityService;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.identity.Group;
import org.activiti.engine.identity.User;
import org.junit.Test;

import javax.imageio.ImageIO;
import javax.imageio.stream.ImageOutputStream;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.UUID;

public class UserInfo {
    ProcessEngine engine = ProcessEngines.getDefaultProcessEngine();
    @Test
    public void AddDeleteUserInfo(){
        IdentityService identityService = engine.getIdentityService();
        String userId = UUID.randomUUID().toString();
        new Create_User().createUser(identityService,userId,"名","刘","15170047205@163.com","abc");
        identityService.setUserInfo(userId,"age","22");
        identityService.setUserInfo(userId,"weight","65KG");
    }

    @Test
    public void getUserInfo(){
        IdentityService identityService = engine.getIdentityService();
        identityService.deleteUserInfo("0a72e4ee-a98a-4de6-8955-72578d12f644","age");

        String weight = identityService.getUserInfo("0a72e4ee-a98a-4de6-8955-72578d12f644", "weight");
        System.out.println("weight的值：" + weight);
    }

    /*
    *  用户ID 用户用户组ID
    * */
    @Test
    public void CreateMeberShip(){
        IdentityService identityService = engine.getIdentityService();
        String userId = UUID.randomUUID().toString();
        String groupId = UUID.randomUUID().toString();
        // 保存用户
        new Create_User().createUser(identityService,userId,"angus","yong","15170047205@163.com","1252112");
        /// 保存用户组
        new Create_Group().create_group(identityService,groupId,"经理组","manager");

        //关系绑定
        identityService.createMembership(userId,groupId);
    }

    @Test
    public void deleteMembership(){
        IdentityService identityService = engine.getIdentityService();
        String useriD = "0f26036b-f450-4562-a868-e7a1b7fec491";
        String gropId = "b7aeea72-101f-4958-b55f-21695a41b4fe";
//        identityService.deleteMembership(useriD,gropId);
        identityService.createMembership(useriD,gropId);
    }
    // 根据用户组查询下列所属用户   memberOfGroup
    @Test
    public void QueryUserByGroup(){
        IdentityService identityService = engine.getIdentityService();
        String userId1 = UUID.randomUUID().toString();
        String userId2 = UUID.randomUUID().toString();
        String userId3 = UUID.randomUUID().toString();
        String groupId = UUID.randomUUID().toString();
        new Create_User().createUser(identityService,userId1,"张经理-阿里","yong","15170047205@163.com","1252112");
        new Create_User().createUser(identityService,userId2,"刘经理-阿里","yong","15170047205@163.com","8987722");
        new Create_User().createUser(identityService,userId3,"王经理-阿里","yong","15170047205@163.com","771873-23");

        /// 保存用户组
        Group manager = new Create_Group().create_group(identityService, groupId, "经理组-阿里", "manager");
        // 将用户分配到该用户组
        identityService.createMembership(userId1,groupId);
        identityService.createMembership(userId2,groupId);
        identityService.createMembership(userId3,groupId);

        List<User> lists = identityService.createUserQuery().memberOfGroup(manager.getId()).list();
        System.out.println("经理组有如下人员：");
        for (User user : lists){
            System.out.println(user.getFirstName());
        }
    }

    //  根据用户ID 查询所属用户组  groupMember
    @Test
    public void QueryGroupByUser(){
        IdentityService identityService = engine.getIdentityService();
        List<Group> groups = identityService.createGroupQuery().groupMember("5f640cff-efc1-4b01-a8d7-c684cfe4f959").list();
        System.out.println("王经理-阿里所属用户组：");
        for (Group group : groups){
            System.out.println(group.getName());
        }
    }

}
