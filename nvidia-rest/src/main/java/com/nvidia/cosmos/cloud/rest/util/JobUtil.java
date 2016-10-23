package com.nvidia.cosmos.cloud.rest.util;

import static com.nvidia.cosmos.cloud.rest.util.JobServiceConstant.BRIDGE;
import static com.nvidia.cosmos.cloud.rest.util.JobServiceConstant.CHARACTER_COLON;
import static com.nvidia.cosmos.cloud.rest.util.JobServiceConstant.CHARACTER_EMPTY;
import static com.nvidia.cosmos.cloud.rest.util.JobServiceConstant.CONTAINER_PORT;
import static com.nvidia.cosmos.cloud.rest.util.JobServiceConstant.DEVICE;
import static com.nvidia.cosmos.cloud.rest.util.JobServiceConstant.DEV_NVIDIA;
import static com.nvidia.cosmos.cloud.rest.util.JobServiceConstant.DEV_NVIDIACTL;
import static com.nvidia.cosmos.cloud.rest.util.JobServiceConstant.DEV_NVIDIA_UVM;
import static com.nvidia.cosmos.cloud.rest.util.JobServiceConstant.DOCKER;
import static com.nvidia.cosmos.cloud.rest.util.JobServiceConstant.DOCKERCFG;
import static com.nvidia.cosmos.cloud.rest.util.JobServiceConstant.HOSTNAME;
import static com.nvidia.cosmos.cloud.rest.util.JobServiceConstant.HOST_PORT;
import static com.nvidia.cosmos.cloud.rest.util.JobServiceConstant.LABEL;
import static com.nvidia.cosmos.cloud.rest.util.JobServiceConstant.LOCAL_VOLUME;
import static com.nvidia.cosmos.cloud.rest.util.JobServiceConstant.NFS;
import static com.nvidia.cosmos.cloud.rest.util.JobServiceConstant.NFS_VOLUME;
import static com.nvidia.cosmos.cloud.rest.util.JobServiceConstant.NO_PRIVILEGES;
import static com.nvidia.cosmos.cloud.rest.util.JobServiceConstant.NVIDIA_VOLUMES;
import static com.nvidia.cosmos.cloud.rest.util.JobServiceConstant.R0_P;
import static com.nvidia.cosmos.cloud.rest.util.JobServiceConstant.SECURITY_OPT;
import static com.nvidia.cosmos.cloud.rest.util.JobServiceConstant.SERIAL_NUMBER;
import static com.nvidia.cosmos.cloud.rest.util.JobServiceConstant.TAR_GZ;
import static com.nvidia.cosmos.cloud.rest.util.JobServiceConstant.TCP;
import static com.nvidia.cosmos.cloud.rest.util.JobServiceConstant.UNIQUE;
import static com.nvidia.cosmos.cloud.rest.util.JobServiceConstant.USER;
import static com.nvidia.cosmos.cloud.rest.util.JobServiceConstant.VOLUME;
import static com.nvidia.cosmos.cloud.rest.util.JobServiceConstant.VOLUMES_FROM;
import static com.nvidia.cosmos.cloud.rest.util.JobServiceConstant.VOLUME_DRIVER;
import static com.nvidia.cosmos.cloud.rest.util.TimeBasedUUIDGenerator.generateId;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.apache.commons.collections.map.MultiValueMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.nvidia.cosmos.cloud.dtos.JobRequestDataDTO;
import com.nvidia.cosmos.cloud.dtos.job.Container;
import com.nvidia.cosmos.cloud.dtos.job.Docker;
import com.nvidia.cosmos.cloud.dtos.job.Fetch;
import com.nvidia.cosmos.cloud.dtos.job.Labels;
import com.nvidia.cosmos.cloud.dtos.job.Parameters;
import com.nvidia.cosmos.cloud.dtos.job.PortDefinitions;
import com.nvidia.cosmos.cloud.dtos.job.RequestMarathonJobDTO;
import com.nvidia.cosmos.cloud.dtos.job.choronos.ChronosContainer;
import com.nvidia.cosmos.cloud.dtos.job.choronos.RequestChronosJobDTO;
import com.nvidia.cosmos.cloud.services.node.model.Node;
import com.nvidia.cosmos.cloud.services.user.model.User;

public class JobUtil {

	private static Logger logger = LoggerFactory.getLogger(JobUtil.class);

	public static RequestMarathonJobDTO createMarathonJobJson(JobRequestDataDTO pJobRequestDTO, Node pNode, User pLoggedInUser, String pTomcatUri) {
		logger.debug("Entering into createMarathonJobJson() {}", pJobRequestDTO.toString());
		RequestMarathonJobDTO marathonJobDTO = new RequestMarathonJobDTO();
		marathonJobDTO.setId(pJobRequestDTO.getName());
		List<Fetch> fetchList = new ArrayList<Fetch>();
		marathonJobDTO.setFetch(fetchList);
		/*
		 * var ta = '{"id":"' + job_name +
		 * '","fetch":[],"labels":'+labelParams+',"portDefinitions":[],"cpus":'+cpus+',"cmd":null,"args":'+args+',"uris":["
		 * '+jobUriEndpoint+'"], ' +
		 * '"container":{"docker":{"forcePullImage":'+imagevalue+',"image":"' +
		 * docker_url + '","parameters":' + params +
		 * ',"privileged":null,"network":"BRIDGE" ' + ',"portMappings":[' +
		 * portMappingArray +']},' +
		 * '"volumes":null,"type":"DOCKER"},"disk":0,"mem":'+memory+',
		 * "instances":1,"constraints":[["hostname","UNIQUE",""]]}';
		 */
		// /var labels = {'owner_name': name, 'serial_number':
		// serial_number,'label':uuid};

		Labels labels = new Labels();
		UUID uuid = generateId();
		if (uuid != null) {
			labels.setLabel(uuid.toString());
		}
		labels.setOwnerName(pLoggedInUser.getName());
		labels.setSerialNumber(pJobRequestDTO.getSerialNumber());
		marathonJobDTO.setLabels(labels);

		List<PortDefinitions> portDefinitions = new ArrayList<PortDefinitions>();
		marathonJobDTO.setPortDefinitions(portDefinitions);

		// cpus=parseInt($scope.job.node.node_status.total_cpu_cores)*0.8;
		Integer cpus = 0;
		try {
			if (pNode.getTotalCpuCores() != null && !pNode.getTotalCpuCores().isEmpty()) {
				cpus = (int) (Integer.parseInt(pNode.getTotalCpuCores()) * 0.8);
				if (cpus > 1) {
					cpus = Math.round(cpus);
				}
				else {
					cpus = 1;
				}
				marathonJobDTO.setCpus(cpus);
			}
		} catch (Exception e) {
			logger.error("Unable to parse {},Total Cpu Cores:" + pNode.getTotalCpuCores(), e.getMessage());
			marathonJobDTO.setCpus(cpus);
		}
		if (pJobRequestDTO.getSpaceParameters() != null && !pJobRequestDTO.getSpaceParameters().isEmpty()) {
			String[] args = pJobRequestDTO.getSpaceParameters().split(" ");
			marathonJobDTO.setArgs(args);
		}
		// jobUriEndpoint=$scope.tomcatUrl+username+'.tar.gz';
		String[] uris = new String[1];
		if (pTomcatUri != null && !pTomcatUri.isEmpty()) {
			String uri = "http://localhost/" + pLoggedInUser.getUserName() + TAR_GZ;
			uris[0] = new String(uri);
		} else {
			// jobUriEndpoint=$scope.tomcatUrl+'dockercfg.tar.gz';
			String uri = "http://localhost/" + DOCKERCFG + TAR_GZ;
			uris[0] = new String(uri);
		}
		marathonJobDTO.setUris(uris);

		Container container = new Container();
		Docker docker = new Docker();
		if (pJobRequestDTO.getImage() != null) {
			docker.setForcePullImage(pJobRequestDTO.getImage());
		} else {
			docker.setForcePullImage(false);
		}
		docker.setImage(pJobRequestDTO.getDockerUrl());
		// } else if(value.key=='NFSVolume'){
		// {
		// var nfsparm1 = {};
		// nfsparm1.key = 'volume-driver';
		// nfsparm1.value = 'nfs';
		// parameters.push(nfsparm1);
		//
		// var nfsparm2 = {};
		// nfsparm2.key = 'volume';
		// nfsparm2.value = value.value;
		//
		// // "value": "10.20.13.62/opt/nfs/data:/data"
		// parameters.push(nfsparm2);
		// }
		// } else if(value.key=='LocalVolume'){
		// var parameter = {};
		// parameter.key = 'volume';
		// parameter.value = value.value;
		// parameters.push(parameter);
		// }

		// After once we added All parameters to list set it as final values to
		// JSON
		List<Parameters> parameterList = new ArrayList<Parameters>();
		parameterList.addAll(createJobPrameterList(pJobRequestDTO, pNode, pLoggedInUser));
		if (uuid != null) {
			Parameters parametersObject = new Parameters();
			parametersObject.setKey(LABEL);
			parametersObject.setValue(uuid.toString());
			parameterList.add(parametersObject);
		}
		docker.setParameters(parameterList);
		docker.setNetwork(BRIDGE);

		// var portMapping = job_container_port != '' ?
		// '{"port":0,"protocol":"tcp","labels":null,"containerPort":' /* != ''
		// ? */
		// + job_container_port + ',"hostPort":0,"servicePort":0}'
		// :'}';
		//

		MultiValueMap multiValueMap = createJobPortDefinitions(pJobRequestDTO, pNode, pLoggedInUser);
		docker.setPortMappings(createJobPortDefinitions(multiValueMap));

		container.setType(DOCKER);
		container.setDocker(docker);

		marathonJobDTO.setContainer(container);
		marathonJobDTO.setDisk(0);
		marathonJobDTO.setInstances(1);
		Integer memeory = 0;
		try {
			if (pNode.getMemory() != null && !pNode.getMemory().isEmpty()) {
				memeory = (int) (Integer.parseInt(pNode.getMemory()) * 0.8);
				memeory = Math.round(memeory);
				marathonJobDTO.setMem(memeory);
			}
		} catch (Exception e) {
			logger.error("Unable to parse {},Memory:" + pNode.getMemory(), e.getMessage());
			marathonJobDTO.setMem(memeory);
		}

		List<List<String>> constraints = new ArrayList<List<String>>();
		List<String> constraint = new ArrayList<String>();
		constraint.add(HOSTNAME);
		constraint.add(UNIQUE);
		constraint.add(CHARACTER_EMPTY);
		constraints.add(constraint);

		marathonJobDTO.setConstraints(constraints);
		marathonJobDTO.setBackoffSeconds(1);
		marathonJobDTO.setBackoffFactor(3);
		marathonJobDTO.setMaxLaunchDelaySeconds(3600);

		logger.debug("Exiting from createMarathonJobJson() {}", marathonJobDTO.toString());
		return marathonJobDTO;
	}

	// ta='{"schedule": "R0//P","name":
	// "'+job_name+'","retries":0,"description":"serial_number:'+serial_number+'","ownerName":"'+name+'","container":
	// {"type": "DOCKER","image": "'+docker_url+'","network": "BRIDGE",'
	// + '"forcePullImage":'+imagevalue+',"parameters" : '+params+'},'
	// + '"fetch" : [{ "uri" : "'+jobUriEndpoint+'","extract" : true}],"cpus":
	// '+cpus+',"mem": '+memory+',"command" : "'+spaceParameters+'"}';

	public static RequestChronosJobDTO createChronosJobJson(JobRequestDataDTO pJobRequestDTO, Node pNode, User pLoggedInUser, String pTomcatUri) {
		logger.debug("Entering into createChronosJobJson() {}", pJobRequestDTO.toString());
		RequestChronosJobDTO chronosJobDTO = new RequestChronosJobDTO();
		chronosJobDTO.setSchedule(R0_P);
		chronosJobDTO.setName(pJobRequestDTO.getName());
		chronosJobDTO.setRetries(0);
		String description = SERIAL_NUMBER + CHARACTER_COLON + pJobRequestDTO.getSerialNumber();
		chronosJobDTO.setDescription(description);
		if (pJobRequestDTO.getSpaceParameters() != null && !pJobRequestDTO.getSpaceParameters().isEmpty()) {
			chronosJobDTO.setCommand(pJobRequestDTO.getSpaceParameters());
		}
		
		ChronosContainer chronosContainer = new ChronosContainer();
		
		if(pJobRequestDTO.getImage()!=null){
		  chronosContainer.setForcePullImage(pJobRequestDTO.getImage());
		}else{
			chronosContainer.setForcePullImage(false);
		}
		
		chronosContainer.setImage(pJobRequestDTO.getDockerUrl());
		chronosContainer.setNetwork(BRIDGE);
		List<Parameters> parameterList = new ArrayList<Parameters>();
		parameterList.addAll(createJobPrameterList(pJobRequestDTO, pNode, pLoggedInUser));
		UUID uuid = generateId();
		
		if (uuid != null) {
			Parameters parametersObject = new Parameters();
			parametersObject.setKey(LABEL);
			parametersObject.setValue(uuid.toString());
			parameterList.add(parametersObject);
		}
		chronosContainer.setParameters(parameterList);
		chronosContainer.setType(DOCKER);
		chronosJobDTO.setContainer(chronosContainer);

		List<Fetch> fetchList = new ArrayList<Fetch>();
		Fetch fetch = new Fetch();
		String uris = new String();
		if (pTomcatUri != null && !pTomcatUri.isEmpty()) {
			String uri = "http://localhost/" + pLoggedInUser.getUserName() + TAR_GZ;
			uris = new String(uri);
		} else {
			// jobUriEndpoint=$scope.tomcatUrl+'dockercfg.tar.gz';
			String uri = "http://localhost/" + DOCKERCFG + TAR_GZ;
			uris = new String(uri);
		}
		fetch.setUri(uris);
		fetch.setExtract(true);
		fetchList.add(fetch);
		chronosJobDTO.setFetch(fetchList);

		Integer memeory = 0;
		try {
			if (pNode.getMemory() != null && !pNode.getMemory().isEmpty()) {
				memeory = (int) (Integer.parseInt(pNode.getMemory()) * 0.8);
				memeory = Math.round(memeory);
				chronosJobDTO.setMem(memeory);
			}
		} catch (Exception e) {
			logger.error("Unable to parse {},Memory:" + pNode.getMemory(), e.getMessage());
			chronosJobDTO.setMem(memeory);
		}

		Integer cpus = 0;
		try {
			if (pNode.getTotalCpuCores() != null && !pNode.getTotalCpuCores().isEmpty()) {
				cpus = (int) (Integer.parseInt(pNode.getTotalCpuCores()) * 0.8);
				if(cpus > 1)
				{
					cpus = Math.round(cpus);
				}
				else
				{
					cpus = 1;
				}
				chronosJobDTO.setCpus(cpus);
			}
		} catch (Exception e) {
			logger.error("Unable to parse {},Total Cpu Cores:" + pNode.getTotalCpuCores(), e.getMessage());
			chronosJobDTO.setCpus(cpus);
		}
		chronosJobDTO.setOwnerName(pLoggedInUser.getName());

		logger.debug("Exiting from createChronosJobJson() {}", chronosJobDTO.toString());
		return chronosJobDTO;
	}

	// var portMapping = job_container_port != '' ?
	// '{"port":0,"protocol":"tcp","labels":null,"containerPort":' /* != '' ? */
	// + job_container_port + ',"hostPort":0,"servicePort":0}'
	// :'}';
	private static MultiValueMap createJobPortDefinitions(JobRequestDataDTO pJobRequestDTO, Node pNode, User pLoggedInUser) {
		MultiValueMap multiValueMap = new MultiValueMap();
		if (pJobRequestDTO.getDropDownValues() != null && !pJobRequestDTO.getDropDownValues().isEmpty()) {
			for (Parameters parameters : pJobRequestDTO.getDropDownValues()) {
				if (parameters.getKey() != null && !parameters.getKey().isEmpty() && parameters.getValue() != null && !parameters.getValue().isEmpty()
						&& !parameters.getKey().equalsIgnoreCase(NFS_VOLUME) && !parameters.getKey().equalsIgnoreCase(LOCAL_VOLUME)) {
					if (parameters.getKey().equalsIgnoreCase(HOST_PORT) == true) {
						try {
							multiValueMap.put(HOST_PORT, Integer.parseInt(parameters.getValue()));
						} catch (Exception e) {
							logger.error("Unable to parse {},HostPort:" + parameters.getValue(), e.getMessage());
						}
					} else if (parameters.getKey().equalsIgnoreCase(CONTAINER_PORT) == true) {
						try {
							multiValueMap.put(CONTAINER_PORT, Integer.parseInt(parameters.getValue()));
						} catch (Exception e) {
							logger.error("Unable to parse {},ContainerPort:" + parameters.getValue(), e.getMessage());
						}
					}
				}
			}
		}
		return multiValueMap;
	}

	@SuppressWarnings("unchecked")
	private static List<PortDefinitions> createJobPortDefinitions(MultiValueMap multiValueMap) {
		List<PortDefinitions> portMappings = new ArrayList<PortDefinitions>();

		List<Integer> containerPorts = (List<Integer>) multiValueMap.get(CONTAINER_PORT);
		List<Integer> hostPorts = (List<Integer>) multiValueMap.get(HOST_PORT);

		if (containerPorts != null && hostPorts != null) {
			if (containerPorts.size() >= hostPorts.size()) {
				for (int size = 0; size < containerPorts.size(); size++) {
					PortDefinitions definitions = new PortDefinitions();

					int containerPort = containerPorts.get(size);
					int hostPort = 0;

					if (hostPorts.size() > size) {
						hostPort = hostPorts.get(size);
					}

					definitions.setProtocol(TCP);
					definitions.setContainerPort(containerPort);
					definitions.setHostPort(hostPort);
					portMappings.add(definitions);
				}
			} else if (hostPorts.size() >= containerPorts.size()) {
				for (int size = 0; size < hostPorts.size(); size++) {
					PortDefinitions definitions = new PortDefinitions();

					int hostPort = hostPorts.get(size);
					int containerPort = 0;

					if (containerPorts.size() > size) {
						containerPort = containerPorts.get(size);
					}

					definitions.setProtocol(TCP);
					definitions.setContainerPort(containerPort);
					definitions.setHostPort(hostPort);
					portMappings.add(definitions);
				}
			}
		} else {

			if (hostPorts != null && !hostPorts.isEmpty()) {
				for (int count : hostPorts) {
					PortDefinitions definitions = new PortDefinitions();
					definitions.setProtocol(TCP);
					definitions.setContainerPort(0);
					definitions.setHostPort(count);
					portMappings.add(definitions);
				}
			}

			if (containerPorts != null && !containerPorts.isEmpty()) {
				for (int count : containerPorts) {
					PortDefinitions definitions = new PortDefinitions();
					definitions.setProtocol(TCP);
					definitions.setContainerPort(count);
					definitions.setHostPort(0);
					portMappings.add(definitions);
				}
			}
		}

		return portMappings;
	}

	private static List<Parameters> createJobPrameterList(JobRequestDataDTO pJobRequestDTO, Node pNode, User pLoggedInUser) {
		List<Parameters> parameterList = new ArrayList<Parameters>();
		if (pJobRequestDTO.getDropDownValues() != null && !pJobRequestDTO.getDropDownValues().isEmpty()) {
			for (Parameters parameters : pJobRequestDTO.getDropDownValues()) {
				if (parameters.getKey() != null && !parameters.getKey().isEmpty()) {
					if (parameters.getKey().equalsIgnoreCase(NFS_VOLUME) == true && parameters.getValue() != null) {
						Parameters parameters2 = new Parameters();
						parameters2.setKey(VOLUME_DRIVER);
						parameters2.setValue(NFS);
						parameterList.add(parameters2);

						parameters2 = new Parameters();
						parameters2.setKey(VOLUME);
						parameters2.setValue(parameters.getValue());
						parameterList.add(parameters2);

					} else if (parameters.getKey().equalsIgnoreCase(LOCAL_VOLUME) == true && parameters.getValue() != null) {
						Parameters parameters2 = new Parameters();
						parameters2.setKey(VOLUME);
						parameters2.setValue(parameters.getValue());
						parameterList.add(parameters2);
					}
				}
			}
		}

		Parameters parametersObject = new Parameters();
		parametersObject.setKey(SECURITY_OPT);
		parametersObject.setValue(NO_PRIVILEGES);
		parameterList.add(parametersObject);

		parametersObject = new Parameters();
		parametersObject.setKey(VOLUMES_FROM);
		parametersObject.setValue(NVIDIA_VOLUMES);
		parameterList.add(parametersObject);

		parametersObject = new Parameters();
		parametersObject.setKey(DEVICE);
		parametersObject.setValue(DEV_NVIDIACTL);
		parameterList.add(parametersObject);

		parametersObject = new Parameters();
		parametersObject.setKey(DEVICE);
		parametersObject.setValue(DEV_NVIDIA_UVM);
		parameterList.add(parametersObject);

		parametersObject = new Parameters();
		parametersObject.setKey(USER);
		String uidAndGid = pLoggedInUser.getuId() + CHARACTER_COLON + pLoggedInUser.getgId();
		parametersObject.setValue(uidAndGid);
		parameterList.add(parametersObject);

		// var param11 = {};
		// param11.key = 'device';
		// param11.value = '/dev/nvidia'+xco;
		// parameters.push(param11);

		if (pNode.getGpuConfiguration() != null && !pNode.getGpuConfiguration().isEmpty()) {
			Integer gpuConfigurationCount = Integer.parseInt(pNode.getGpuConfiguration());
			for (int count = 0; count < gpuConfigurationCount; count++) {
				Parameters parameters = new Parameters();
				parameters.setKey(DEVICE);
				parameters.setValue(DEV_NVIDIA + count);
				parameterList.add(parameters);
			}
		}

		for (Parameters parameters : pJobRequestDTO.getParameters()) {
			if(!parameters.getKey().isEmpty() && !parameters.getValue().isEmpty()) {
				parameterList.add(parameters);
			}
		}
		return parameterList;
	}

}
