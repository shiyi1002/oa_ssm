package com.qf.service;

import com.qf.entity.SysPurchase;

import java.util.List;

public interface ISysPurchaseService extends IBaseService<SysPurchase> {
    void addPurse(SysPurchase sysPurchase);

    List<SysPurchase> selectAllPurchase();

    SysPurchase getPurchase(Long taskId);
}
