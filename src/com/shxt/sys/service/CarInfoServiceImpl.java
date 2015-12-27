package com.shxt.sys.service;

import java.util.List;

import com.shxt.base.dao.PageBean;
import com.shxt.base.service.BaseService;
import com.shxt.sys.model.CarInfo;
import com.shxt.sys.model.CarInfoQuery;
import com.shxt.sys.model.CarType;

public class CarInfoServiceImpl extends BaseService implements ICarInfoService {
	
	public PageBean find(PageBean pageBean, CarInfoQuery query) {
		String hql = "from CarInfo ci where 1=1";
		if (query!=null) {
			if (query.getCar_name()!=null&&query.getCar_name().trim().length()>0) {
				hql +=" and ci.car_name like '"+query.getCar_name().trim()+"%'";
			}
			
			if(query.getType_id()!=null&&query.getType_id().length()>0){
				hql +=" and ci.carType.type_id ="+query.getType_id();
			}
			
			if(query.getCar_status()!=null&&query.getCar_status().length()>0){
				hql +=" and ci.car_status ="+query.getCar_status();
			}
			
		}
		hql +=" order by ci.car_id desc";
		return this.baseDao.find(hql, pageBean);
	}
	
	public List<CarType> carTypeList() {
		String hql = "from CarType ct ";
		return (List<CarType>) this.baseDao.list(hql);
	}
	
	public void add(CarInfo carInfo) {
		
		this.baseDao.save(carInfo);
		
	}
	
	public Long checkCode(String carCode) {
		String hql = "select count(*) from CarInfo ci where ci.car_code=?";
		return (Long) this.baseDao.query(hql, carCode);
	}
	
	public CarType carType(String typeId) {
		
		return (CarType) this.baseDao .load(CarType.class, Integer.parseInt(typeId));
	}
	
	public CarInfo carInfo(String carId) {
		
		return (CarInfo) this.baseDao.load(CarInfo.class, Integer.parseInt(carId));
	}
	
	public void update(CarInfo carInfo) {
		
		CarInfo oldCarInfo = (CarInfo) this.baseDao.load(CarInfo.class, carInfo.getCar_id());
		
		oldCarInfo.setBuy_price(carInfo.getBuy_price());
		
		oldCarInfo.setCar_color(carInfo.getCar_color());
		
		oldCarInfo.setCar_desc(carInfo.getCar_desc());
		
		oldCarInfo.setCar_name(carInfo.getCar_name());
		
		oldCarInfo.setCar_status(carInfo.getCar_status());
		
		oldCarInfo.setCreate_user_name(carInfo.getCreate_user_name());
		
		oldCarInfo.setDeposit(carInfo.getDeposit());
		
		oldCarInfo.setKm(carInfo.getKm());
		
		oldCarInfo.setRent_price(carInfo.getRent_price());
		
		oldCarInfo.setPhoto(carInfo.getPhoto());
		
		if(carInfo.getCarType()!=null){
			oldCarInfo.setCarType(carInfo.getCarType());
		}else{
			oldCarInfo.setCarType(null);
		}
		
		this.baseDao.update(oldCarInfo);
	}

}
