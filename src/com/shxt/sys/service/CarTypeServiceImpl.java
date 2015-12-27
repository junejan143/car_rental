package com.shxt.sys.service;

import java.util.List;
import com.shxt.base.service.BaseService;
import com.shxt.sys.model.CarType;

public class CarTypeServiceImpl extends BaseService implements ICarTypeService {

	public List<CarType> getParentList() {
		String hql = "from CarType ct where ct.parent_id is null order by ct.type_id desc";
		return (List<CarType>) this.baseDao.list(hql);
	}

	public void add(CarType carType) {
		this.baseDao.save(carType);
	}

	public CarType getCarTypeById(Integer typeId) {
		return (CarType) this.baseDao.load(CarType.class, typeId);
	}

	public void update(CarType carType) {
		 CarType oldCarType = (CarType) this.baseDao.load(CarType.class, carType.getType_id());
		
		 oldCarType.setType_name(carType.getType_name());

		 if(carType.getIcon()!=null){
			 oldCarType.setIcon(carType.getIcon());
		 }
		 
		 this.baseDao.update(oldCarType);
		 
	}

	public Long checkName(String name) {
		String hql = "select count(*) from CarType ct where ct.type_name=?";
		return (Long) this.baseDao.query(hql, name.trim());
	}

	public void updateStatus(String status, Integer typeId) {
		
		String hql="update CarType ct set ct.type_status=? where ct.type_id=?";
		
		this.baseDao.updateByHql(hql, new Object[]{status,typeId});
	}

	public void delete(Integer typeId) {
		
		this.baseDao.delete(CarType.class, typeId);
		
	}

}
