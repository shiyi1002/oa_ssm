package com.qf.dao;

import com.qf.entity.SysOrg;

import java.util.List;

public interface IBaseDao<T> {
    int deleteByPrimaryKey(Long orgId);

    int insert(T t);

    int insertSelective(T t);

    T selectByPrimaryKey(Long orgId);

    int updateByPrimaryKeySelective(T t);

    int updateByPrimaryKey(T t);

}
