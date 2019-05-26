package com.qf.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.qf.common.SysResult;
import com.qf.entity.SysMenu;
import com.qf.entity.SysUser;
import com.qf.service.ISysMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.awt.*;
import java.util.List;

@Controller
@RequestMapping("/sysMenu")
public class SysMenuController {
    @Autowired
    private ISysMenuService sysMenuService;

    @RequestMapping("/page")
    public String show(@RequestParam(value = "pageNum",defaultValue = "1") Integer pageNum, Model model, SysMenu sysMenu){
        PageHelper.startPage(pageNum,3);
        List<SysMenu> list = sysMenuService.getList();
        PageInfo pageInfo=new PageInfo(list,5);
        model.addAttribute("pageInfo",pageInfo);
        return "sysMenu/sysMenu_list";

    }

    @RequestMapping("/delete")
    @ResponseBody
    public SysResult delete(@RequestParam Long menuId){
        SysResult sysResult=new SysResult();
        int result=sysMenuService.deleteByPrimaryKey(menuId);
        if(result>0){
            sysResult.setResult(true);
        }else{
            sysResult.setResult(false);
        }
        return sysResult;
    }

        @RequestMapping("/toAdd")
        public String toAdd(){
                return "sysMenu/sysMenu_add";
        }

        //查询所有菜单
    @RequestMapping("/showMenu")
    @ResponseBody
        public List<SysMenu> showMenu(){
        List<SysMenu> list= sysMenuService.showMenu();
        return list;
        }

        //添加菜单
        @RequestMapping("/add")
        @ResponseBody
        public SysResult add(SysMenu sysMenu){
            SysResult sysResult=new SysResult();
            int result=sysMenuService.insertSelective(sysMenu);
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
        public String toUpdate(@RequestParam Long menuId,Model model){
            SysMenu sysMenu = sysMenuService.selectByPrimaryKey(menuId);
            List<SysMenu> list= sysMenuService.showMenu();
            model.addAttribute("sysMenu",sysMenu);
            model.addAttribute("list",list);
            return "sysMenu/sysMenu_update";
        }
        @RequestMapping("/update")
        @ResponseBody
        public SysResult update(SysMenu sysMenu){
            SysResult sysResult=new SysResult();
            int result=sysMenuService.updateByPrimaryKeySelective(sysMenu);
            if(result>0){
                sysResult.setResult(true);
                sysResult.setData("修改成功");
            }else{
                sysResult.setResult(false);
                sysResult.setData("修改失败");
            }
            return sysResult;
        }

        @RequestMapping("/batchdel")
        @ResponseBody
        public SysResult batchdel(@RequestParam List<Long> ids){
            return sysMenuService.batchdel(ids);
        }
}
