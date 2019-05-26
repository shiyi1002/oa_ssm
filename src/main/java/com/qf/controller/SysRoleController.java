package com.qf.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.qf.common.SysResult;
import com.qf.entity.SysRole;
import com.qf.entity.SysUser;
import com.qf.service.ISysRoleService;
import com.qf.service.ISysUserService;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/sysRole")
public class SysRoleController {
    @Autowired
    private ISysRoleService sysRoleService;
    @RequestMapping("/page")
    public String show(@RequestParam(value = "pageNum",defaultValue = "1") Integer pageNum, Model model,SysRole sysRole){
        PageHelper.startPage(pageNum,3);
        List<SysRole> list= sysRoleService.getList();

        PageInfo pageInfo=new PageInfo(list,5);
        model.addAttribute("pageInfo",pageInfo);
        model.addAttribute("sysRole",sysRole);
        return "sysRole/sysRole_list";
    }

    @RequestMapping("/toAdd")
    public String toAdd(){
        return "sysRole/role_add";
    }

    @RequestMapping("/add")
    @ResponseBody
    public SysResult add(SysRole sysRole){
        SysResult sysResult=new SysResult();
        int result=sysRoleService.insertSelective(sysRole);
        if(result>0){
            sysResult.setResult(true);
            sysResult.setData("添加成功");
        }else{

            sysResult.setResult(false);
            sysResult.setData("添加失败");
        }
        return sysResult;
    }

    @RequestMapping("/toUpdate")
    public String toUpdate(Long roleId,Model model){
        SysRole sysRole = sysRoleService.selectByPrimaryKey(roleId);
        model.addAttribute("sysRole",sysRole);
        return "sysRole/role_update";
    }

    @RequestMapping("/update")
    @ResponseBody
   public SysResult update(SysRole sysRole){
        SysResult sysResult=new SysResult();
        int result=sysRoleService.updateByPrimaryKeySelective(sysRole);
        if(result>0){
            sysResult.setResult(true);
            sysResult.setData("修改成功");
        }else{

            sysResult.setResult(false);
            sysResult.setData("修改失败");
        }
        return sysResult;
   }

   @RequestMapping("/delete")
   @ResponseBody
   public SysResult delete(@RequestParam Long roleId){
       SysResult sysResult=new SysResult();
       int result=sysRoleService.updateFlagById(roleId);
       if(result>0){
           sysResult.setResult(true);
           sysResult.setData("删除成功");
       }else{

           sysResult.setResult(false);
           sysResult.setData("删除失败");
       }
       return sysResult;
   }

    @RequestMapping("/batchdel")
    @ResponseBody
    public SysResult batchdel(@RequestParam List<Long> ids){
        SysResult sysResult=new SysResult();
        int result=sysRoleService.updateFlagByIds(ids);
        if(result>0){
            sysResult.setResult(true);

        }else{

            sysResult.setResult(false);

        }
        return sysResult;
    }



}
