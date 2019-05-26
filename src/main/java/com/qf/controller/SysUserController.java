package com.qf.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.gson.Gson;

import com.google.gson.JsonObject;
import com.qf.common.Page;
import com.qf.common.SysResult;
import com.qf.entity.SysArea;
import com.qf.entity.SysMenu;
import com.qf.entity.SysOrg;
import com.qf.entity.SysUser;
import com.qf.service.ISysAreaService;
import com.qf.service.ISysUserService;


import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/sysUser")
public class SysUserController {
    @Autowired
    private ISysUserService sysUserService;
    @Autowired
    private ISysAreaService sysAreaService;


         @RequestMapping("/login")
        public String login(){
            return "login";
        }


    @RequestMapping("/checkLogin")
    public String checkLogin(SysUser sysUser,Model model){
       Subject currUser = SecurityUtils.getSubject();
            if(!currUser.isAuthenticated()){
                UsernamePasswordToken token=new UsernamePasswordToken(sysUser.getUserName(),sysUser.getUserPassword());
               try {
                   currUser.login(token);//登录认证
               }catch (AuthenticationException e){
                   System.out.println("认证失败");
                   return "login";
               }

            }
      //登录成功
        //通过用户id查询进入登录页面能看到哪些菜单
            //获取realm里面的那个用户
        SysUser user = (SysUser) currUser.getPrincipal();
        List<SysMenu> menuList=sysUserService.getMenu(user.getUserId());
       model.addAttribute("menuList",menuList);
       return "index";
    }




    @RequestMapping("/page")
    public String show(Page page, Model model,SysUser sysUser){
        PageHelper.startPage(page.getCurrentPage(),page.getPageSize());
        List<SysUser> list= sysUserService.getList();

        PageInfo pageInfo=new PageInfo(list);
        model.addAttribute("pageInfo",pageInfo);
        model.addAttribute("sysUser",sysUser);
        return "sysUser/sysUser_list";
    }

    @RequestMapping("/selectByCondition")
    public String selectByCondition(Page page, SysUser sysUser, ModelMap map){
        //得到一个pageInfo对象
        PageInfo<SysUser> pageInfo = sysUserService.selectByCondition(page,sysUser);
        //装起来
        map.put("pageInfo",pageInfo);
        map.put("sysUser",sysUser);
        map.put("url","sysUser/selectByCondition");

        Gson gson = new Gson();
        Map<String,Object> paramMap = new HashMap<String,Object>();
        paramMap.put("userName",sysUser.getUserName());
        paramMap.put("flag",sysUser.getFlag());
        map.put("params",gson.toJson(paramMap));
        return "sysUser/sysUser_list";

    }
    @RequestMapping("/toAdd")
    public String toAdd(){
        return "sysUser/user_add";
    }

    @RequestMapping("/add")
    @ResponseBody
    public SysResult add(SysUser sysUser){
        SysResult sysResult=new SysResult();
       int result= sysUserService.insertSelective(sysUser);
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
    public String toUpdate(Long userId,Model model){
        SysUser sysUser = sysUserService.selectByPrimaryKey(userId);
        //查询所有的省,市,区显示在页面
        List<SysArea> pList=sysAreaService.getAllProvince();
        List<SysArea> cityList=sysAreaService.getAllCity();
        List<SysArea> countryList=sysAreaService.getAllCountry();
        model.addAttribute("sysUser",sysUser);
        model.addAttribute("pList",pList);
        model.addAttribute("cityList",cityList);
        model.addAttribute("countryList",countryList);
        return "sysUser/user_update";
    }

    @RequestMapping("/selCityById")
    @ResponseBody
    public List<SysArea> selCityById(@RequestParam String provinceName){

        List<SysArea> cList=sysAreaService.selCityById(provinceName);

        return cList;
    }





    @RequestMapping("/update")
    @ResponseBody
    public SysResult update(SysUser sysUser,Model model){
       int result=sysUserService.updateByPrimaryKeySelective(sysUser);
        SysResult sysResult=new SysResult();
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
    public SysResult delete(@RequestParam("userId") Long userId){
       int result= sysUserService.deleteByPrimaryKey(userId);
        SysResult sysResult=new SysResult();
        if(result>0){
            sysResult.setResult(true);


        }else{
            sysResult.setResult(false);

        }
        return sysResult;
    }

    @RequestMapping("/batchdel")
    @ResponseBody
    //注意结合中id的类型
    public SysResult batchdel(@RequestParam List<Long> ids){

        SysResult sysResult=new SysResult();
        int count=sysUserService.delAll(ids);
        if(count>0){
            sysResult.setResult(true);

        }else{

            sysResult.setResult(false);

        }
        return sysResult;
    }

}
