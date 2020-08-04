package com.dengzhou;

import org.activiti.bpmn.model.*;

public class ProcessModel {
    public BpmnModel createProcessModel(){
        // 创建BpMn对象
        BpmnModel bpmnModel = new BpmnModel();
        org.activiti.bpmn.model.Process process = new org.activiti.bpmn.model.Process();
        bpmnModel.addProcess(process);
        process.setId("myProcess_7");
        process.setName("My_Process_7");

        // 事件开始
        StartEvent startEvent = new StartEvent();
        startEvent.setId("startEvent");
        process.addFlowElement(startEvent);

        // 用户任务
        UserTask userTask = new UserTask();
        userTask.setName("User_Task_7");
        userTask.setId("userTask7");
        process.addFlowElement(userTask);

        // 事件结束
        EndEvent endEvent = new EndEvent();
        endEvent.setId("endEvent");
        process.addFlowElement(endEvent);

        // 添加流程顺序
        process.addFlowElement(new SequenceFlow("startEvent","userTask"));
        process.addFlowElement(new SequenceFlow("userTask","endEvent"));

        return bpmnModel;

    }
}
