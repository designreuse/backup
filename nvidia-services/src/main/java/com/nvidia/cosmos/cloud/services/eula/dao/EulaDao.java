package com.nvidia.cosmos.cloud.services.eula.dao;

import com.nvidia.cosmos.cloud.services.eula.model.Eula;

public interface EulaDao {
    void createEula(Eula eula);
	
	Eula findEulaByName(Eula eula);
	
	void mergeEula(Eula eula);

}
