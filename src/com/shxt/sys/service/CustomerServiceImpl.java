package com.shxt.sys.service;

import java.util.List;

import com.shxt.base.dao.PageBean;
import com.shxt.base.service.BaseService;
import com.shxt.sys.model.Customer;
import com.shxt.sys.model.CustomerQuery;
import com.shxt.sys.model.MemberType;
import com.shxt.sys.model.User;

public class CustomerServiceImpl extends BaseService implements ICustomerService {

	public PageBean find(PageBean pageBean , CustomerQuery query) {
		
		String hql = "from Customer c where 1=1";
		
		if (query!=null) {
			if (query.getCus_name()!=null&&query.getCus_name().trim().length()>0) {
				hql +=" and c.cus_name like '"+query.getCus_name().trim()+"%'";
			}
			if (query.getCus_sex()!=null&&query.getCus_sex().length()>0) {
				hql +=" and c.cus_sex='"+query.getCus_sex()+"'";
			}
			if (query.getGua_name()!=null&&query.getGua_name().trim().length()>0) {
				hql +=" and c.gua_name like '"+query.getGua_name().trim()+"%'";
			}
			
		}
		hql +=" order by c.cus_id desc";
		
		return this.baseDao.find(hql, pageBean);
		
		
	}

	public List<MemberType> memberTypeList() {
		
		String hql = "from MemberType m";
		
		return (List<MemberType>) this.baseDao.list(hql);
	}

	public Long checkCard(String cusIdCard) {
		String hql = "select count(*) from Customer c where c.cus_id_card=?";
		return (Long) this.baseDao.query(hql, cusIdCard);
	}

	public void add(Customer customer) {
		this.baseDao.save(customer);
		
	}

	public MemberType memberType(String memberType) {
		
		return (MemberType) this.baseDao.load(MemberType.class, Integer.parseInt(memberType));
	}

	public Customer getCustomerById(Integer cusId) {
		
		return (Customer) this.baseDao.load(Customer.class, cusId);
	}

	public void update(Customer customer) {
		
		Customer oldCustomer = (Customer) this.baseDao.load(Customer.class, customer.getCus_id());
		
		oldCustomer.setCus_address(customer.getCus_address());
		
		oldCustomer.setCus_driver_code(customer.getCus_driver_code());
		
		oldCustomer.setCus_name(customer.getCus_name());
		
		oldCustomer.setCus_sex(customer.getCus_sex());
		
		oldCustomer.setCus_tel(customer.getCus_tel());
		
		oldCustomer.setCus_work_address(customer.getCus_work_address());
		
		oldCustomer.setGua_address(customer.getGua_address());
		
		oldCustomer.setGua_id_card(customer.getGua_id_card());
		
		oldCustomer.setGua_name(customer.getGua_name());
		
		oldCustomer.setGua_sex(customer.getGua_sex());
		
		oldCustomer.setGua_tel(customer.getGua_tel());
		
		oldCustomer.setGua_work_address(customer.getGua_work_address());
		
		oldCustomer.setRelation(customer.getRelation());
		
		if (customer.getMemberType()!=null) {
			oldCustomer.setMemberType(customer.getMemberType());
		}else{
			oldCustomer.setMemberType(null);
		}
		
		this.baseDao.update(oldCustomer);
		
		
	}

	public void updateStatus(Integer cusId) {
		Customer customer = (Customer) this.baseDao.load(Customer.class, cusId);
		if(customer.getCus_status().equals("1")){
			customer.setCus_status("2");
		}else{
			customer.setCus_status("1");
		}
		
		this.baseDao.update(customer);
		
	}

}
