package com.qf.service.impl;

import com.qf.dao.IBaseDao;
import com.qf.entity.SysArea;
import com.qf.mapper.SysAreaMapper;
import com.qf.service.IBaseService;
import com.qf.service.ISysAreaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SysAreaServiceImpl extends BaseServiceImpl<SysArea> implements ISysAreaService {
    @Autowired(required = false)
    private SysAreaMapper sysAreaMapper;
    @Override
    public IBaseDao<SysArea> getBaseDao() {
        return sysAreaMapper;
    }

    @Override
    public List<SysArea> getAllProvince() {
        return sysAreaMapper.getAllProvince();
    }

    @Override
    public List<SysArea> getAllCity() {
        return sysAreaMapper.getAllCity();
    }

    @Override
    public List<SysArea> getAllCountry() {
        return sysAreaMapper.getAllCountry();
    }

    @Override
    public List<SysArea> selCityById(String provinceName) {
        return sysAreaMapper.selCityById(provinceName);
    }
}
