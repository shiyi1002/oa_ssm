package com.qf.service.impl;

import com.qf.dao.IBaseDao;
import com.qf.entity.SysOrg;
import com.qf.service.IBaseService;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public abstract class BaseServiceImpl<T> implements IBaseService<T>{
    public abstract IBaseDao<T> getBaseDao();
    @Override
    public int deleteByPrimaryKey(Long t) {
        return getBaseDao().deleteByPrimaryKey(t);
    }



    @Override
    public int insert(T t) {
        return getBaseDao().insert(t);
    }

    @Override
    public int insertSelective(T t) {
        return getBaseDao().insertSelective(t);
    }

    @Override
    public T selectByPrimaryKey(Long orgId) {
        return getBaseDao().selectByPrimaryKey(orgId);
    }

    @Override
    public int updateByPrimaryKeySelective(T t) {
        return getBaseDao().updateByPrimaryKeySelective(t);
    }

    @Override
    public int updateByPrimaryKey(T t) {
        return getBaseDao().updateByPrimaryKey(t);
    }


}
