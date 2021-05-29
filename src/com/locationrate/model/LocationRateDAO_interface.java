package com.locationrate.model;

import java.util.*;

public interface LocationRateDAO_interface {
	public void insert(LocationRateVO locationRateVO);
	public void update(LocationRateVO locationRateVO);
	public void delete(Integer SN);
	public List<LocationRateVO> getByPointSN(Integer pointSN);	// for diveinfo
	public LocationRateVO getByUser(Integer userID);
}
