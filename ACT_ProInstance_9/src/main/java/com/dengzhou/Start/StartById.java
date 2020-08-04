package com.dengzhou.Start;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.DeploymentBuilder;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.ProcessInstance;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class StartById {
    ProcessEngine engine = ProcessEngines.getDefaultProcessEngine();
    // 流程开启
    @Test
    public void testStartById(){
        // 流程存储时服务
        RepositoryService repositoryService = engine.getRepositoryService();
        RuntimeService runtimeService = engine.getRuntimeService();

        // 部署流程定义文件
       /* DeploymentBuilder builder = repositoryService.createDeployment();
        Deployment deploy = builder.addClasspathResource("leave.xml").deploy();*/

        // 查找流程定义
        ProcessDefinition processDefinition = repositoryService.createProcessDefinitionQuery().deploymentId("25001").singleResult();

        // 设置流程参数
        Map<String,Object> vars = new HashMap<>();
        vars.put("days",5);

        // 流程启动
        ProcessInstance processInstance = runtimeService.startProcessInstanceByKey(processDefinition.getId());
        System.out.println(processInstance.getId());
      /*  runtimeService.startProcessInstanceByKey(processDefinition.getId(), vars);
        runtimeService.startProcessInstanceByKey(processDefinition.getId(), " vacationRequest1");
        runtimeService.startProcessInstanceByKey(processDefinition.getId()," vacationRequest1",vars);
*/


    }
}
