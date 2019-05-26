package com.qf.controller;

import com.github.pagehelper.PageInfo;
import com.google.gson.Gson;

import com.qf.common.Page;
import com.qf.common.SysResult;
import com.qf.entity.SysOrg;
import com.qf.service.SysOrgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/sysOrg")
public class SysOrgController {

    @Autowired
    private SysOrgService sysOrgService;

   //通过id查询组织对象
    @RequestMapping("/findById")
    @ResponseBody
    public SysOrg queryById(Long orgId) {
        return sysOrgService.selectByPrimaryKey(orgId);
    }


    @RequestMapping("/page")
    public String page(Page page, ModelMap map) {
        //查询分页的数据   pageInfo
        PageInfo<SysOrg> pageInfo = sysOrgService.getPage(page);

        map.put("pageInfo", pageInfo);

        return "org/org_list";
    }

    /**
     * 跳转到添加组织页面
     */
    @RequestMapping("/toAdd")
    public String toAdd() {
        return "sysOrg/sysOrg_add";
    }

    /**
     * 添加组织
     */
    @RequestMapping("/add")
    @ResponseBody
    public SysResult add(SysOrg sysOrg) {
        //专门存返回结果的对象
        SysResult sysResult = new SysResult();
        int count = sysOrgService.insertSelective(sysOrg);
        if (count > 0) {
            sysResult.setResult(true);
            sysResult.setData("添加成功!");
        } else {
            sysResult.setResult(false);
            sysResult.setData("添加失败!");
        }
        return sysResult;
    }

    /**
     * 查询所有的组织
     */
    @RequestMapping("/list")
    @ResponseBody
    public List<SysOrg> list() {
        List<SysOrg> orgList = sysOrgService.getOrgList();
        return orgList;
    }

    /**
     * 跳到修改页面
     */
    @RequestMapping("/toUpdate")
    public String toUpdate(Long orgId, ModelMap map) {

        SysOrg sysOrg = sysOrgService.selectByPrimaryKey(orgId);
        map.put("sysOrg", sysOrg);
        return "sysOrg/sysOrg_update";
    }

   //修改
    @RequestMapping("/update")
    @ResponseBody
    public SysResult update(SysOrg sysOrg) {
        SysResult sysResult = new SysResult();
        int count = sysOrgService.updateByPrimaryKeySelective(sysOrg);
        if (count > 0) {
            sysResult.setResult(true);
            sysResult.setData("修改成功!");
        } else {
            sysResult.setResult(false);
            sysResult.setData("修改失败!");
        }
        return sysResult;
    }

    /**
     * 假删除
     */
    @RequestMapping("/delete")
    @ResponseBody
    public SysResult delete(Long orgId){
        return sysOrgService.updateFlagByOrgId(orgId);
    }

    /**
     * 批量假删除
     */
    @RequestMapping("/batchDel")
    @ResponseBody
    public SysResult batchDelete(@RequestParam List<Long> idList){
        return sysOrgService.updateFlagByIdList(idList);
    }



    //搜索框分页
    @RequestMapping("/selectByCondition")
    public String selectByCondition(SysOrg sysOrg, Page page, ModelMap map){
        //返回pageInfo对象
        PageInfo<SysOrg> pageInfo = sysOrgService.selectByCondition(sysOrg,page);
        map.put("pageInfo",pageInfo);
        map.put("sysOrg",sysOrg);
        map.put("url","sysOrg/selectByCondition");

        //传递json格式的条件给page使用
        Gson gson = new Gson();
        Map<String,Object> paramMap = new HashMap<String,Object>();
        paramMap.put("orgName",sysOrg.getOrgName());
        paramMap.put("orgParentName",sysOrg.getOrgParentName());
        paramMap.put("flag",sysOrg.getFlag());
        map.put("params",gson.toJson(paramMap));
        return "sysOrg/sysOrg_list";
    }
}
