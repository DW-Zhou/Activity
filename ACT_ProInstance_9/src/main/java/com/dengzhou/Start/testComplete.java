package com.dengzhou.Start;

import org.activiti.engine.*;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.runtime.ProcessInstance;

public class testComplete {
    public static void main(String[] args) {
        ProcessEngine engine = ProcessEngines.getDefaultProcessEngine();
        RuntimeService runtimeService = engine.getRuntimeService();
        RepositoryService repositoryService = engine.getRepositoryService();
        TaskService taskService = engine.getTaskService();

        //taskService.complete("11");

        //taskService.complete("13");

        taskService.complete("20005");

    }
}
