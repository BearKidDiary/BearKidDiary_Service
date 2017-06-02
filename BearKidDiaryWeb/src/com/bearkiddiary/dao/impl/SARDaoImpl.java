package com.bearkiddiary.dao.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.bearkiddiary.bean.SAttendRecord;
import com.bearkiddiary.common.dao.impl.BaseDaoHibernate;
import com.bearkiddiary.dao.SARDao;

public class SARDaoImpl extends BaseDaoHibernate<SAttendRecord> implements SARDao{

	@Override
	public List<Long> named(List<SAttendRecord> list) {
		List<Serializable> list_id = saveList(list);
		List<Long> list_result = new ArrayList<>();
		for(Serializable id : list_id){
			list_result.add((Long)id);
		}
		return list_result;
	}

}
