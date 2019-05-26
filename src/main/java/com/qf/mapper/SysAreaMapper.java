package com.qf.mapper;

import com.qf.dao.IBaseDao;
import com.qf.entity.SysArea;

import java.util.List;

public interface SysAreaMapper extends IBaseDao<SysArea>{

    List<SysArea> getAllProvince();

    List<SysArea> getAllCity();

    List<SysArea> getAllCountry();

    List<SysArea> selCityById(String provinceName);
}