package com.shxt.sys.service;

import java.util.List;
import com.shxt.base.service.BaseService;
import com.shxt.sys.model.MemberType;

public class MemberTypeServiceImpl extends BaseService implements IMemberTypeService {

	public List<MemberType> getList() {
		String hql = "from MemberType mt order by mt.type_id desc";
		return (List<MemberType>) this.baseDao.list(hql);
	}

	public void add(MemberType memberType) {
		this.baseDao.save(memberType);
		
	}

	public MemberType getMemberTypeById(Integer typeId) {
		return (MemberType) this.baseDao.load(MemberType.class, typeId);
	}

	public Long checkName(String name) {
		String hql = "select count(*) from MemberType mt where mt.type_name=?";
		return (Long) this.baseDao.query(hql, name.trim());
	}

	public void update(MemberType memberType) {
		MemberType oldMemberType = (MemberType) this.baseDao.load(MemberType.class, memberType.getType_id());
		
		oldMemberType.setType_name(memberType.getType_name());
		 oldMemberType.setDiscount(memberType.getDiscount());
		 if(memberType.getPhoto()!=null){
			 oldMemberType.setPhoto(memberType.getPhoto());
		 }
		 
		 this.baseDao.update(oldMemberType);
		
	}

	public void updateStatus(Integer typeId,String status) {
		
		String hql="update MemberType mt set mt.type_status=? where mt.type_id=?";
		
		this.baseDao.updateByHql(hql, new Object[]{status,typeId});
		
	}

	public void delete(Integer typeId) {
		
		this.baseDao.delete(MemberType.class, typeId);
		
	}

	
}
