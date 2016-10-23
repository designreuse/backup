package com.nvidia.cosmos.cloud.services.eula.dao.impl;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.nvidia.cosmos.cloud.common.dao.AbstractDao;
import com.nvidia.cosmos.cloud.services.eula.dao.EulaDao;
import com.nvidia.cosmos.cloud.services.eula.model.Eula;

@Repository("eulaDao")
public class EulaDaoImpl extends AbstractDao<Long, Eula> implements EulaDao {

	@Override
	public void createEula(Eula eula) {
		persist(eula);

	}

	@Override
	public Eula findEulaByName(Eula eula) {
		Criteria criteria = createEntityCriteria();
        criteria.add(Restrictions.eq("name", eula.getName()));
        return (Eula) criteria.uniqueResult();
	}
	
	@Override
	public void mergeEula(Eula eula) {
		merge(eula);

	}
	

}
