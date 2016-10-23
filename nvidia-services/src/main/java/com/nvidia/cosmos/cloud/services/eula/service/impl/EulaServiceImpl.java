package com.nvidia.cosmos.cloud.services.eula.service.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nvidia.cosmos.cloud.exceptions.EulaExistsException;
import com.nvidia.cosmos.cloud.services.eula.dao.EulaDao;
import com.nvidia.cosmos.cloud.services.eula.model.Eula;
import com.nvidia.cosmos.cloud.services.eula.service.EulaService;

@Service("eulaService")
@Transactional
public class EulaServiceImpl implements EulaService {

	@Autowired
	MessageSource messageSource;
	/**
     * 
     */
    @Autowired
    private EulaDao dao;
	
	@Override
	public void createEula(Eula eula)throws EulaExistsException {
		Eula entity = dao.findEulaByName(eula);
		if (entity != null) {
			entity.setEulaContent(eula.getEulaContent());
			entity.setFileName(eula.getFileName());
			entity.setUpdatedDate(new Date());
			dao.mergeEula(entity);
		}else{
		dao.createEula(eula);
		}

	}
	
@Override
	public Eula findEulaByName(Eula eula){
		Eula entity = dao.findEulaByName(eula);
		return entity;
	}

	
}
