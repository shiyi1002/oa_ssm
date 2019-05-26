package com.qf.mapper;

import com.qf.dao.IBaseDao;
import com.qf.entity.SysPurchase;

import java.util.List;

public interface SysPurchaseMapper extends IBaseDao<SysPurchase> {

    List<SysPurchase> selectAllPurchase();
}