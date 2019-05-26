package com.qf.service;

import com.qf.entity.SysOrg;

import java.util.List;

public interface IBaseService<T> {
    int deleteByPrimaryKey(Long t);

    int insert(T t);

    int insertSelective(T t);

    T selectByPrimaryKey(Long t);

    int updateByPrimaryKeySelective(T t);

    int updateByPrimaryKey(T t);

}
