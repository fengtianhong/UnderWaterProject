package com.diveinfo.model;

import java.util.List;

public interface DiveInfoDAO_interface {
    public void insert(DiveInfoVO diveInfoVO);
    public void update(DiveInfoVO diveInfoVO);
    public DiveInfoVO findByPrimaryKey(Integer pointSN);
    public List<DiveInfoVO> getAll();
}
