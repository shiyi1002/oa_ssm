package com.qf.service;

import com.qf.entity.SysArea;

import java.util.List;

public interface ISysAreaService extends IBaseService<SysArea> {
    List<SysArea> getAllProvince();

    List<SysArea> getAllCity();

    List<SysArea> getAllCountry();

    List<SysArea> selCityById(String provinceName);
}
