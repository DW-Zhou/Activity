package com.dengzhou.Variable83;

import com.dengzhou.bean.Person;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.TaskService;
import org.activiti.engine.task.Task;
import org.junit.Test;

import java.util.Date;

public class BasicVariableType {
    ProcessEngine engine = ProcessEngines.getDefaultProcessEngine();
    @Test
    public void basicVariable(){
        TaskService taskService = engine.getTaskService();
        Task task1 = taskService.newTask("test_Var");
        taskService.saveTask(task1);

        Date d = new Date();
        short s = 3;
        taskService.setVariable(task1.getId(),"arg0",false);
        taskService.setVariable(task1.getId(),"arg1",d);
        taskService.setVariable(task1.getId(),"arg2",s);
    }

    // 测试参数为object
    @Test
    public void SerilizableVariableType(){
        TaskService taskService = engine.getTaskService();
        Task task = taskService.newTask("测试参数为对象object");
        task.setName("请假回家");
        taskService.saveTask(task);
        taskService.setVariable(task.getId(),"arg0",new Person("林动"));
    }
}
