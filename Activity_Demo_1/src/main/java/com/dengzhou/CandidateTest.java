package com.dengzhou;

import org.activiti.engine.IdentityService;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.TaskService;
import org.activiti.engine.identity.Group;
import org.activiti.engine.identity.User;
import org.activiti.engine.task.Task;
import org.junit.Test;

import java.util.List;
import java.util.UUID;

/*
*  测试用户权限
*
* */
public class CandidateTest {
    @Test
    public void test_Candidate(){
        ProcessEngine engine = ProcessEngines.getDefaultProcessEngine();
        TaskService ts = engine.getTaskService();

        IdentityService identityService = engine.getIdentityService();
        // 创建任务
        String taskId = UUID.randomUUID().toString();
        Task task = ts.newTask(taskId);
        task.setName("测试任务");
        ts.saveTask(task);
        // 创建用户
        String  userId = UUID.randomUUID().toString();
        User user = identityService.newUser(userId);
        user.setFirstName("angus");
        identityService.saveUser(user);
        // 设置用户的候选用户组
        ts.addCandidateUser(taskId,userId);


        //查询

        List<Task> tasks = ts.createTaskQuery().taskCandidateUser(userId).list();
        System.out.println("这个用户的ID是："+userId+":有权限");
        for (Task t : tasks){
            System.out.println(t.getName());
        }

    }

    @Test
    public void test_ClaimTest(){
        ProcessEngine engine = ProcessEngines.getDefaultProcessEngine();
        TaskService ts = engine.getTaskService();
        IdentityService is = engine.getIdentityService();
        // 创建任务
        String taskId = UUID.randomUUID().toString();
        Task task = ts.newTask();
        task.setName("新的测试任务2");
        ts.saveTask(task);

        //创建用户
        String userId = UUID.randomUUID().toString();
        User user = is.newUser(userId);
        user.setFirstName("傻逼用户A");
        is.saveUser(user);

        //设置任务的代理用户
        ts.claim(taskId,userId);
        List<Task> lists = ts.createTaskQuery().taskAssignee(userId).list();
        for (Task t : lists){
            System.out.println(t.getName() );
        }
    }
}
