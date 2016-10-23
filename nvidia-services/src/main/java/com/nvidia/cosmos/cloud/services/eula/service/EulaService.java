package com.nvidia.cosmos.cloud.services.eula.service;

import com.nvidia.cosmos.cloud.exceptions.EulaExistsException;
import com.nvidia.cosmos.cloud.services.eula.model.Eula;

public interface EulaService {

	void createEula(Eula eula) throws EulaExistsException;
	
	Eula findEulaByName(Eula eula);

}
