package com.qf.service.impl;

import com.qf.dao.IBaseDao;
import com.qf.entity.SysPurchase;
import com.qf.mapper.SysPurchaseMapper;
import com.qf.service.ISysPurchaseService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.runtime.ProcessInstanceQuery;
import org.activiti.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class SysPurchaseServiceImpl extends BaseServiceImpl<SysPurchase> implements ISysPurchaseService {
    @Autowired(required = false)
    private SysPurchaseMapper sysPurchaseMapper;
    @Autowired
    private RuntimeService runtimeService;
    @Autowired
    private TaskService taskService;
    @Override
    public IBaseDao<SysPurchase> getBaseDao() {
        return sysPurchaseMapper;
    }

    @Override
    public void addPurse(SysPurchase sysPurchase) {
        sysPurchaseMapper.insertSelective(sysPurchase);
        //启动流程
        //map装参数金额,申请人
        //获得采购单的订单号
        String businessKey=sysPurchase.getId().toString();
        Map<String, Object> map=new HashMap<>();
        map.put("money",sysPurchase.getMoney());
        map.put("currentId",sysPurchase.getUserId());
        map.put("administrationId",2);
        map.put("managerId",3);
        map.put("financialId",4);

        runtimeService.startProcessInstanceByKey("purchase",businessKey,map);
    }

    @Override
    public List<SysPurchase> selectAllPurchase() {
        return sysPurchaseMapper.selectAllPurchase();
    }

    @Override
    public SysPurchase getPurchase(Long taskId) {
        //通任务id获得流程实例对象---通过流程实例id获得bisnesskey--通过这个businesskey获得采购单id,从而获得采购单对象
       //通任务id获得流程实例对象
        Task task = taskService.createTaskQuery().taskId(taskId.toString()).singleResult();
        //通过流程实例id获得流程实例id
        String processInstanceId = task.getProcessInstanceId();
        //通过流程实例id查询流程实例对象
        ProcessInstance processInstance = runtimeService.createProcessInstanceQuery().processInstanceId(processInstanceId).singleResult();
        //通过流程实例对象获得bisnesskey
        String businessKey = processInstance.getBusinessKey();
         //通过这个businesskey获得采购单对象
       Long id=Long.parseLong(businessKey);
        SysPurchase sysPurchase = sysPurchaseMapper.selectByPrimaryKey(id);
        return sysPurchase;
    }
}
