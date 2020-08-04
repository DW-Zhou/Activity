package com.dengzhou.Task;

import com.dengzhou.bean.Create_Group;
import org.activiti.engine.IdentityService;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.TaskService;
import org.activiti.engine.identity.Group;
import org.activiti.engine.identity.User;
import org.activiti.engine.task.Task;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class NewTask {
    ProcessEngine engine = ProcessEngines.getDefaultProcessEngine();
    static void saveTask(TaskService taskService,String id){
        Task task = taskService.newTask(id);
        taskService.saveTask(task);
    }
    @Test
    public void create_newTask(){
        TaskService taskService = engine.getTaskService();

        Task task1 = taskService.newTask();
        task1.setName("zcy_开发任务1");
        taskService.saveTask(task1);
        Task task2 = taskService.newTask();
        task2.setName("zcy_开发任务2");
        taskService.saveTask(task2);
    }

    @Test
    public void delete_Task(){
        TaskService taskService = engine.getTaskService();
       /* for (int i = 1 ; i < 6 ; i++){
            saveTask(taskService,String.valueOf(i));
        }*/
        long count = taskService.createTaskQuery().count();
        System.out.println("Task表删除数据之前有多少："+count+"条");
        // 删除
        taskService.deleteTask("1");
        taskService.deleteTask("2",true);
        System.out.println("Task表删除数据之前有多少："+taskService.createTaskQuery().count()+"条");

        // 删除多个task（不包括历史数据和子任务）
        List<String> ids = new ArrayList<>();
        ids.add("3");
        ids.add("4");
        taskService.deleteTasks(ids);
        // 增加true字段，不仅ACT_RU_TASK数据会被删除，并且ACT_HI_TASKINST中的数据也会被删除，级联操作
        taskService.deleteTasks(ids,true);


    }

    // 任务权限
    // 8.2.3 权限数据查询
    @Test
    public void Querry(){
        // 获取身份服务组件
        IdentityService identityService = engine.getIdentityService();
        // 新建用户
        User user =  identityService.newUser("user1");
        user.setFirstName("张三");
        user.setLastName("last");
        user.setEmail("12345@163.com");
        user.setPassword("123231");
        identityService.saveUser(user);
        Group groupA = new Create_Group().create_group(identityService, "group1", "经理组-拼多多", "manager");
        Group groupB = new Create_Group().create_group(identityService, "group2", "员工组-拼多多", "employee");

//        获取任务服务组件
        TaskService taskService = engine.getTaskService();
        Task task1 = taskService.newTask("task1");
        task1.setName("申请假期");
        taskService.saveTask(task1);
        Task task2 = taskService.newTask("task2");
        task2.setName("审批假期");
        taskService.saveTask(task2);
        // 权限绑定
        taskService.addCandidateGroup("task1",groupA.getId());
        taskService.addCandidateGroup("task2",groupB.getId());
        taskService.addCandidateUser("task2",user.getId());
        // 根据用户组查询任务
        List<Task> tasks = taskService.createTaskQuery().taskCandidateGroup(groupA.getId()).list();
        System.out.println("经理组的候选任务有：");
        for (Task data : tasks){
            System.out.println("  "+data.getName());
        }
        // 根据用户查询任务
        List<Task> userTasks = taskService.createTaskQuery().taskCandidateUser(user.getId()).list();
        System.out.println("张三同学的候选任务有：");
        for (Task userTask : userTasks){
            System.out.println(" "+userTask.getName());
        }
        //  查询权限数据
    }
}
