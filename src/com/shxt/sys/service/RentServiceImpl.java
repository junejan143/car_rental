package com.shxt.sys.service;

import java.util.List;

import com.shxt.base.dao.PageBean;
import com.shxt.base.service.BaseService;
import com.shxt.sys.model.CarInfo;
import com.shxt.sys.model.CarInfoQuery;
import com.shxt.sys.model.CarType;
import com.shxt.sys.model.Customer;
import com.shxt.sys.model.CustomerQuery;
import com.shxt.sys.model.RentCar;

public class RentServiceImpl extends BaseService implements IRentService {

	public List<CarType> carTypeList() {
		
		String hql = "from CarType ct ";
		
		return (List<CarType>) this.baseDao.list(hql);
	}

	public PageBean find(PageBean pageBean, CarInfoQuery query) {
		String hql = "from CarInfo ci where 1=1 and ci.car_status = 1";
		if (query!=null) {
			if (query.getCar_name()!=null&&query.getCar_name().trim().length()>0) {
				hql +=" and ci.car_name like '"+query.getCar_name().trim()+"%'";
			}
			
			if(query.getType_id()!=null&&query.getType_id().length()>0){
				hql +=" and ci.carType.type_id ="+query.getType_id();
			}
			
		}
		hql +=" order by ci.car_id desc";
		return this.baseDao.find(hql, pageBean);
	}
	
	public CarInfo getCarInfoById(Integer carId){
		
		return (CarInfo) this.baseDao.load(CarInfo.class, carId);
		
	}

	public Customer getCustomerById(String idCard) {
		String hql = "from Customer c where c.cus_id_card=?";
		return (Customer) this.baseDao.query(hql, idCard);
	}

	public void add(RentCar rentCar) {
		this.baseDao.save(rentCar);
		
	}

	public Customer getCustomerById(Integer cusId) {
		
		return (Customer) this.baseDao.load(Customer.class, cusId);
	}

	public void updateCarInfo(Integer carId) {
		String hql = "update CarInfo ci set ci.car_status = 5 where ci.car_id=?";
		this.baseDao.updateByHql(hql,carId);
	}

	public void update(CarInfo carInfo) {
		CarInfo oldCarInfo = (CarInfo) this.baseDao.load(CarInfo.class, carInfo.getCar_id());
 		oldCarInfo.setReserve_date(carInfo.getReserve_date());
 		oldCarInfo.setReserve_tel(carInfo.getReserve_tel());
 		oldCarInfo.setReserve_user_name(carInfo.getReserve_user_name());
		oldCarInfo.setCar_status(carInfo.getCar_status());
		this.baseDao.update(oldCarInfo);
		
		
	}

	public PageBean findreturn(PageBean pageBean, CarInfoQuery query) {
		String hql = "from CarInfo ci where  1=1 and ci.car_status = 2 ";
		if (query!=null) {
			if (query.getReserve_user_name()!=null&&query.getReserve_user_name().trim().length()>0) {
				hql +=" and ci.reserve_user_name like '"+query.getReserve_user_name().trim()+"%'";
			}
			
			if(query.getCar_code()!=null&&query.getCar_code().length()>0){
				hql +=" and ci.car_code like '"+query.getCar_code()+"%'";
			}
			
		}
		hql +=" order by ci.car_id desc";
		return this.baseDao.find(hql, pageBean);
	}

	public void updateReturn(CarInfo carInfo) {
		
		CarInfo oldCarInfo = (CarInfo) this.baseDao.load(CarInfo.class, carInfo.getCar_id());
		
		oldCarInfo.setCar_status(carInfo.getCar_status());
		
		this.baseDao.update(oldCarInfo);
		
	}

	public PageBean findBack(PageBean pageBean) {
		String hql = "from RentCar rc where rc.real_date is null order by rc.rent_id desc";
		return this.baseDao.find(hql, pageBean);
	}

	public RentCar getRentCar(Integer rentId) {
		
		return (RentCar) this.baseDao.load(RentCar.class, rentId);
		
	}

	public RentCar updateBack(RentCar rentCar) {
		RentCar oldrentCar = (RentCar) this.baseDao.load(RentCar.class, rentCar.getRent_id());
		
		oldrentCar.setReal_date(rentCar.getReal_date());
		
		oldrentCar.setDays_number(rentCar.getDays_number());
		
		oldrentCar.setReceivable_price(rentCar.getReceivable_price());
		
		oldrentCar.setReal_price(rentCar.getReal_price());
		
		oldrentCar.setRent_desc(rentCar.getRent_desc());
		
		return null;
	}
	
	public void updateCarInfoStatus(Integer carId) {
		String hql = "update CarInfo ci set ci.car_status = 1 where ci.car_id=?";
		this.baseDao.updateByHql(hql,carId);
	}
	
}
