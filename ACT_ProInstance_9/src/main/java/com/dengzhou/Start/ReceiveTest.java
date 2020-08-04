package com.dengzhou.Start;

import org.activiti.engine.*;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.ProcessDefinitionQuery;
import org.activiti.engine.runtime.ProcessInstance;

public class ReceiveTest {
    public static void main(String[] args) {
        ProcessEngine engine = ProcessEngines.getDefaultProcessEngine();
        RuntimeService runtimeService = engine.getRuntimeService();
        RepositoryService repositoryService = engine.getRepositoryService();
        TaskService taskService = engine.getTaskService();

        Deployment deploy = repositoryService.createDeployment().addClasspathResource("receiveTask.bpmn").deploy();
        ProcessInstance processInstance = runtimeService.startProcessInstanceByKey("receiveTask");

        System.out.println(processInstance.getId());
    }
}
