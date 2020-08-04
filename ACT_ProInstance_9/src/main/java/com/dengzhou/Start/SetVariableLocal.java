package com.dengzhou.Start;

import org.activiti.engine.*;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.runtime.Execution;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;

import java.util.List;

public class SetVariableLocal {
    public static void main(String[] args) {
        ProcessEngine engine = ProcessEngines.getDefaultProcessEngine();
        RuntimeService runtimeService = engine.getRuntimeService();
        RepositoryService repositoryService = engine.getRepositoryService();
        TaskService taskService = engine.getTaskService();

        Deployment deploy = repositoryService.createDeployment().addClasspathResource("scope.bpmn").deploy();
        ProcessInstance processInstance = runtimeService.startProcessInstanceByKey("vactionRequest");

        List<Task> list = taskService.createTaskQuery().processInstanceId(processInstance.getId()).list();
        for (Task task : list){
            Execution execution = runtimeService.createExecutionQuery().executionId(task.getExecutionId()).singleResult();
            if ("Manager Audit".equals(task.getName())){
                // 经理审核节点，设置参数
                runtimeService.setVariableLocal(execution.getId(),"managerVar","manager var");
            }else {
                // HR审核节点 设置全局参数
                runtimeService.setVariable(execution.getId(),"hrVar","hr var");
            }
        }
        // 执行两个执行流时输出参数
        for (Task task : list){
            Execution execution = runtimeService.createExecutionQuery().executionId(task.getExecutionId()).singleResult();
            if ("Manager Audit".equals(task.getName())){
                System.out.println("使用getVariableLocal方法获取经理的参数："+runtimeService.getVariableLocal(execution.getId(),"managerVar"));
                System.out.println("使用getVariable方法获取经理的参数："+runtimeService.getVariable(execution.getId(),"managerVar"));
            }else{
                System.out.println("使用getVariableLocal方法获取HR的参数："+runtimeService.getVariableLocal(execution.getId(),"hrVar"));
                System.out.println("使用getVariable方法获取HR的参数："+runtimeService.getVariable(execution.getId(),"hrVar"));
            }
        }

      /*  // 完成两个任务
        for (Task task : list){
            taskService.complete(task.getId());
        }
        System.out.println("-=======  完成流程分支 =============");
        // 重新查找流程任务
        List<Task> list1 = taskService.createTaskQuery().processInstanceId(processInstance.getId()).list();
        for (Task task : list1){
            System.out.println("当前流程节点："+task.getName());
            Execution execution = runtimeService.createExecutionQuery().executionId(task.getExecutionId()).singleResult();
            System.out.println("经理的参数："+runtimeService.getVariable(execution.getId(),"managerVar"));
            System.out.println("HR的参数："+runtimeService.getVariable(execution.getId(),"hrVar"));
        }*/
    }
}
