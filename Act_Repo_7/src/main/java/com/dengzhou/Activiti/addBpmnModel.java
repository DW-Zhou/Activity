package com.dengzhou.Activiti;

import com.dengzhou.ProcessModel;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.repository.DeploymentBuilder;
import org.junit.Test;

public class addBpmnModel {
    ProcessEngine engine = ProcessEngines.getDefaultProcessEngine();

    @Test
    public void addBpmnMode(){
        RepositoryService repositoryService = engine.getRepositoryService();
        //  创建流程存储服务对象
        DeploymentBuilder builder = repositoryService.createDeployment();
        builder.addBpmnModel("MyCodeProcess",new ProcessModel().createProcessModel()).name("MyCodeDeploy").deploy();

    }
}
