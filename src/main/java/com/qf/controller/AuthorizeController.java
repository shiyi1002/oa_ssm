package com.qf.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.gson.Gson;
import com.qf.common.Page;
import com.qf.common.SysResult;
import com.qf.entity.SysMenu;
import com.qf.entity.SysRole;
import com.qf.entity.SysUser;
import com.qf.service.ISysMenuService;
import com.qf.service.ISysRoleService;
import com.qf.service.ISysUserService;
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
@RequestMapping("/authorization")
public class AuthorizeController {
    @Autowired
    private ISysRoleService sysRoleService;
    @Autowired
    private ISysUserService sysUserService;
    @Autowired
    private ISysMenuService sysMenuService;
    //查询所有的角色信息
    @RequestMapping("/quaryAll")
    public String quaryAll(Model model){
        List<SysRole> roleList= sysRoleService.getList();
        model.addAttribute("roleList",roleList);
        return "authorize/authorize_list";
    }
    //查询所有授权的角色
    @RequestMapping("/queryAuthUserByRoleId")
    public String queryAllRole(Long roleId,Page page,ModelMap map) {

        PageInfo<SysUser> pageInfo = sysUserService.queryAuthUserByRoleId(roleId, page);
        map.put("pageInfo", pageInfo);
        map.put("url", "authorization/queryAuthUserByRoleId");

        Gson gson = new Gson();
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("roleId", roleId);
        map.put("params", gson.toJson(paramMap));
        return "authorize/authorize_authUser_list";
    }
//授权新用户---查询没有授权的用户通过用户名

   //通过用户名在没有授权的用户的里面找这个用户

    @RequestMapping("/queryNoAuthUserByRoleId")
    public String queryNoAuthUserByRoleId(Long roleId, String userName, Page page, ModelMap map) {
        PageInfo<SysUser> pageInfo = sysUserService.queryNoAuthUserByRoleId(roleId, userName, page);
        map.put("pageInfo", pageInfo);
        map.put("roleId", roleId);
        map.put("userName", userName);
        map.put("url", "authorization/queryNoAuthUserByRoleId");

        Gson gson = new Gson();
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("userName", userName);
        paramMap.put("roleId", roleId);
        map.put("params", gson.toJson(paramMap));
        return "authorize/noAuthorize_list";
    }
/*批量授权*/
    @RequestMapping("/batchAdd")
    @ResponseBody
    public SysResult batchAdd(@RequestParam List<Long>ids,Long roleId){
        return sysRoleService.batchAdd(ids,roleId);


    }
    /*解除授权关系*/
    @RequestMapping("/delRoleAndUser")
    @ResponseBody
    public SysResult delRoleAndUser(Long userId,Long roleId){
        return sysRoleService.delRoleAndUser(userId,roleId);
    }

    @RequestMapping("/queryAllMenu")
    public String queryAllMenu(@RequestParam("roleId")Long roleId,Page page,Model model){
        PageInfo<SysMenu> pageInfo=sysMenuService.selectByRoleId(roleId,page);
       model.addAttribute("pageInfo",pageInfo);
        model.addAttribute("roleId",roleId);
        model.addAttribute("url","authorization/queryAllMenu");

        Gson gson=new Gson();
        Map<String,Object> map=new HashMap<>();
        //装搜索条件
        map.put("roleId",roleId);
        model.addAttribute("params",gson.toJson(map));//把参数传到page页面

        return "authorize/authorize_authMenu_list";
    }

    @RequestMapping("/queryNoAuthMenuByRoleId")
    public String queryNoAuthMenuByRoleId(Long roleId,String menuName,Model model,Page page){

        PageInfo<SysMenu> pageInfo=sysMenuService.selectMenuByName(menuName,roleId,page);
         model.addAttribute("pageInfo",pageInfo);

            model.addAttribute("menuName",menuName);//带到页面去回显
            model.addAttribute("roleId",roleId);
            Map<String,Object> map=new HashMap<>();
            map.put("menuName",menuName);
            map.put("roleId",roleId);
            Gson gson=new Gson();
            model.addAttribute("url","authorization/queryNoAuthMenuByRoleId");
            model.addAttribute("params",gson.toJson(map));
            return "authorize/noAuthMenu_list";
    }

    @RequestMapping("/batchAddMenu")
    @ResponseBody
    public SysResult batchAddMenu(@RequestParam List<Long>ids,Long roleId){
        return sysRoleService.batchAddMenu(ids,roleId);
    }

    @RequestMapping("/delRoleAndMenu")
    @ResponseBody
    public SysResult delRoleAndMenu(Long menuId,Long roleId){
        return sysRoleService.delRoleAndMenu(menuId,roleId);
    }
}
