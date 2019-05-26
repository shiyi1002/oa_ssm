package com.qf.controller;

import com.qf.entity.SysPurchase;
import com.qf.entity.SysUser;
import com.qf.service.ISysPurchaseService;
import org.activiti.engine.RuntimeService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/purchase")
public class SysPurchaseController {
    @Autowired
private ISysPurchaseService sysPurchaseService;

    @RequestMapping("/toAdd")
    public String toAdd(){
        return "sysPurchase/purchase_add";
    }

    @RequestMapping("/showPurchase")
    public String showPurchase(Model model){
        List<SysPurchase> list=sysPurchaseService.selectAllPurchase();
        model.addAttribute("list",list);
        return "sysPurchase/purchase_list";
    }



    /*添加采购申请*/
    @RequestMapping("/add")
    public String add(SysPurchase sysPurchase){
        //添加采购申请并启动流程
        //获得subject中的userId和username
        Subject subject = SecurityUtils.getSubject();
        SysUser user = (SysUser) subject.getPrincipal();
        //添加到采购对象中
        sysPurchase.setUserId(user.getUserId());
        sysPurchase.setUserName(user.getUserName());
        //把采购申请添加进表里面
        sysPurchaseService.addPurse(sysPurchase);
        return "redirect:/purchase/showPurchase";
    }

    //跳转到审批页面
    @RequestMapping("/check")
    public String check(Long taskId,Model model){
        //通过任务id获得审批单对象
        SysPurchase sysPurchase=sysPurchaseService.getPurchase(taskId);
        //把采购单带到页面回显
        model.addAttribute("sysPurchase",sysPurchase);
        model.addAttribute("taskId",taskId);
        return "sysPurchase/audit";
    }

    //审批
    @RequestMapping("/audit")
    public String audit(){
        return null;
    }
}
