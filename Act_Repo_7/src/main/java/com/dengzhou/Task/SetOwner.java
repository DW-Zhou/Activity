package com.dengzhou.Task;

import com.dengzhou.bean.Create_User;
import org.activiti.engine.IdentityService;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.TaskService;
import org.activiti.engine.identity.User;
import org.activiti.engine.task.Task;
import org.junit.Test;

public class SetOwner {
    ProcessEngine engine = ProcessEngines.getDefaultProcessEngine();
    /*
       setOwner 设置任务的持有人
    */

    @Test
    public void setOwner(){
        IdentityService identityService = engine.getIdentityService();
        User user = new Create_User().createUser(identityService,"userOwnerTest","张三","last","abc@163.com","234123");
        TaskService taskService = engine.getTaskService();
        Task task1 = taskService.newTask();
        task1.setName("申请任务");
        taskService.saveTask(task1);

        // 设置任务持有人
        taskService.setOwner(task1.getId(),user.getId());

        System.out.println("用户张三持有任务的数量："+
                taskService.createTaskQuery().taskOwner(user.getId()).count());
    }
}
