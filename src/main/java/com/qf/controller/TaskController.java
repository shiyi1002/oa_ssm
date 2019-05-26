package com.qf.controller;

import com.qf.entity.SysUser;
import org.activiti.engine.TaskService;
import org.activiti.engine.task.Task;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/process")
public class TaskController {
    @Autowired
    private TaskService taskService;
    //展示当前用户所有任务列表
    @RequestMapping("/queryTaskByCurrentUser")
    public String queryTaskByCurrentUser(Model model){
        //获得当前登录用户
        Subject subject = SecurityUtils.getSubject();
        SysUser sysUser = (SysUser) subject.getPrincipal();
        List<Task> taskList = taskService.createTaskQuery().taskAssignee(sysUser.getUserId().toString()).list();
       model.addAttribute("taskList",taskList);
        return "task/task_list";
    }



}
