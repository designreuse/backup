
/**
 * 
 */
package com.nvidia.cosmos.cloud.rest.util;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;

import com.nvidia.cosmos.cloud.auth.encryptor.EncryptorFactory;
import com.nvidia.cosmos.cloud.auth.encryptor.IEncryptor;
import com.nvidia.cosmos.cloud.dtos.node.NodeDTO;
import com.nvidia.cosmos.cloud.dtos.node.NodeStatusDTO;
import com.nvidia.cosmos.cloud.exceptions.EntitlementNotFoundException;
import com.nvidia.cosmos.cloud.factory.ServicesFactory;
import com.nvidia.cosmos.cloud.rest.common.util.DateUtil;
import com.nvidia.cosmos.cloud.services.cluster.model.Cluster;
import com.nvidia.cosmos.cloud.services.customer.model.Customer;
import com.nvidia.cosmos.cloud.services.entitlement.model.Entitlement;
import com.nvidia.cosmos.cloud.services.entitlement.service.EntitlementService;
import com.nvidia.cosmos.cloud.services.node.model.Node;
import com.nvidia.cosmos.cloud.services.user.service.UserService;

/**
 * @author prbatta
 *
 */
public class NvidiaUtil {
	
	private static final String EMPTY="EMPTY";
	private static Logger logger = LoggerFactory.getLogger(JobsClient.class);
	
	private static EntitlementService entlservice = null;
	
	 static {
		ServicesFactory factory = null;
		try {
			factory = ServicesFactory.getInstance();
			entlservice = factory.getEntitlementService();
			
		} catch (Exception e) {
			logger.error("Error while intializing services");
		}
		
		
	}
	
	public static void main(String[] args) {
		Timestamp time = new Timestamp(1464795052);
		logger.info("time",time);
	}

	/**
	 * 
	 */
	//@Resource
	//private static Environment environment;

	@Autowired
	static Environment environment;
	/**
	 * @param mailIds
	 * @param subject
	 * @param model
	 * @param templateName
	 * @throws Exception
	 */
	public static void executeSendMailsThread(String mailIds, String subject, Map<String, Object> model, String templateName) throws Exception {
		ExecutorService executorService = Executors.newScheduledThreadPool(1);

		executorService.execute(new EmailThread(mailIds.toLowerCase(), subject, model, templateName));

		executorService.shutdown();
	}

	public static String getEncrypt(String userId, String email, boolean isRegistration, String count , int hours, long time) {
		String encryptEmail = null;
		try {
			IEncryptor iencrypt = EncryptorFactory.getEncryptor();

			//Calendar calendar = Calendar.getInstance();
			//calendar.add(Calendar.HOUR, hours);
			//encryptEmail = userId + "^" + email + "^" + calendar.getTimeInMillis() + "^" + isRegistration + "^" + count + "^" + time;
			encryptEmail = userId + "^" + email + "^" + time + "^" + isRegistration + "^" + count;
			encryptEmail = iencrypt.encrypt(encryptEmail);
			encryptEmail = encryptEmail.replaceAll("\n", "");
			encryptEmail = encryptEmail.replaceAll("/", "~");
		} catch (Exception e) {
			e.printStackTrace();
		}

		return encryptEmail;
	}



	public static String[] getDecrypt(String encrypted) {
		String[] decryptedArray = null;
		try {
			IEncryptor iencrypt = EncryptorFactory.getEncryptor();

			encrypted = encrypted.replaceAll("~", "/");
			System.out.println("ENCRYPTED:"+encrypted);
			encrypted = iencrypt.decrypt(encrypted);
			decryptedArray = encrypted.split("\\^");
		} catch (Exception e) {
			e.printStackTrace();
		}

		return decryptedArray;
	}

	public static String getEmailVerificationEncrypt(String userId, String email, String description) {
		String encryptEmail = null;
		try {
			IEncryptor iencrypt = EncryptorFactory.getEncryptor();

			encryptEmail = userId + "^" + email + "^" + description;
			encryptEmail = iencrypt.encrypt(encryptEmail);
			encryptEmail = encryptEmail.replaceAll("\n", "");
			encryptEmail = encryptEmail.replaceAll("/", "~");
		} catch (Exception e) {
			e.printStackTrace();
		}

		return encryptEmail;
	}

	public static Long getCalenderTime(int hours){
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.MINUTE, hours);
		//calendar.add(Calendar.HOUR, hours);
		return calendar.getTimeInMillis();
	}
	
	public static String[] getEmailVerificationDecrypt(String encrypted) {
		String[] decryptedArray = null;
		try {
			IEncryptor iencrypt = EncryptorFactory.getEncryptor();

			encrypted = encrypted.replaceAll("~", "/");
			encrypted = iencrypt.decrypt(encrypted);
			decryptedArray = encrypted.split("\\^");

		} catch (Exception e) {
			e.printStackTrace();
		}

		return decryptedArray;
	}

	public static LicenceKey getLicenceKeyInfo(String pLicenceKey) {
		LicenceKey licenceKeyData = new LicenceKey();
		// String licenceKey = "CST123-CLU123-NODE123";
		if (pLicenceKey != null && !pLicenceKey.isEmpty()) {
			String[] strArry = pLicenceKey.split("-");
			if (strArry != null && strArry.length != 0) {
				licenceKeyData.setCustomerName(strArry[0]);
				licenceKeyData.setClusterName(strArry[1]);
				licenceKeyData.setNodeName(strArry[2]);
			}
		}
		return licenceKeyData;

	}

// Below fields We setting if any field is empty in UI---->EMPTY
//	    Key :
//		Node Serial Id :
//		Node host Name : PROD_01234
//		Node Network Information
//		Subnet :
//		Gateway :
//		Mode :
//		IP Address : 10.31.124.82
//		SW version : 0.5.25
//		FW version : 12345
//		Created Time :20-06-2016-15.49.21
//		Status : connected
//		Node firmware version
//		IPMI :
//		BIOS VERSION : 12345
	public static NodeDTO convertNodeToNodeDTO(Node node,NodeDTO nodeDTO, Cluster cluster) {
		if (node != null && cluster != null) {
			nodeDTO.setKey(verifyStringIsEmpty(node.getNodeKey()));
			nodeDTO.setSerialNumber(node.getSerialNumber());
			nodeDTO.setNodeId(node.getId());
			nodeDTO.setClusterId(cluster.getId());
			NodeStatusDTO nodeStatusDTO = new NodeStatusDTO();
			nodeStatusDTO.setBiosVersion(verifyStringIsEmpty(node.getBiosVersion()));
			nodeStatusDTO.setCloudGroup(verifyStringIsEmpty(node.getCloudGroup()));
			nodeStatusDTO.setCloudManaged(verifyStringIsEmpty(node.getCloudManaged()));
			nodeStatusDTO.setCloudStatus(verifyStringIsEmpty(node.getCloudStatus()));
			nodeStatusDTO.setClusterGroup(verifyStringIsEmpty(node.getClusterGroup()));
			nodeStatusDTO.setClusterId(verifyStringIsEmpty(node.getCluster().getName()));
			nodeStatusDTO.setCreatedTime(verifyStringIsEmpty(DateUtil.format(node.getCreatedDate(), DateUtil.DATE_TIME_PATTERN)));
			nodeStatusDTO.setDiskSpace(verifyStringIsEmpty(node.getDiskSpace()));
			nodeStatusDTO.setEulaAccepted(verifyStringIsEmpty(node.getEulaAccepted()));
			nodeStatusDTO.setFirstBoot(verifyStringIsEmpty(node.getFirstBoot()));
			nodeStatusDTO.setFwVersion(verifyStringIsEmpty(node.getFwVersion()));
			nodeStatusDTO.setGateway(verifyStringIsEmpty(node.getGateway()));
			nodeStatusDTO.setGpuConfiguration(verifyStringIsEmpty(node.getGpuConfiguration()));
			nodeStatusDTO.setIpAddress(verifyStringIsEmpty(node.getIpAddress()));
			nodeStatusDTO.setIpmi(verifyStringIsEmpty(node.getIpmi()));
			nodeStatusDTO.setIsLeader(verifyStringIsEmpty(node.getIsleader()));
			nodeStatusDTO.setKey(verifyStringIsEmpty(node.getNodeKey()));
			nodeStatusDTO.setMemory(verifyStringIsEmpty(node.getMemory()));
			nodeStatusDTO.setMode(verifyStringIsEmpty(node.getMode()));
			nodeStatusDTO.setModelName(verifyStringIsEmpty(node.getModelName()));
			nodeStatusDTO.setNodeName(verifyStringIsEmpty(node.getName()));
			nodeStatusDTO.setNodeId(verifyStringIsEmpty(node.getNodeId()));
			nodeStatusDTO.setSerialid(verifyStringIsEmpty(node.getSerialId()));
			nodeStatusDTO.setSerialNumber(verifyStringIsEmpty(node.getSerialNumber()));
			nodeStatusDTO.setStatus(verifyStringIsEmpty(node.getCloudStatus()));
			nodeStatusDTO.setSubnet(verifyStringIsEmpty(node.getSubNet()));
			nodeStatusDTO.setSwVersion(verifyStringIsEmpty(node.getSwVersion()));
			nodeStatusDTO.setTags(verifyStringIsEmpty(node.getTags()));
			nodeStatusDTO.setTotalCpuCores(verifyStringIsEmpty(node.getTotalCpuCores()));
			nodeStatusDTO.setTimeOfReboot(verifyStringIsEmpty(node.getTimeOfReboot()));
			nodeStatusDTO.setHealthy(verifyStringIsEmpty(node.getHealthy()));
			nodeDTO.setNodeStatus(nodeStatusDTO);
		}
		return nodeDTO;
	}

	
	private static String verifyStringIsEmpty(String value){
		return value!=null && !value.isEmpty()?value:EMPTY;
		
	}
	public static boolean checkInfluxDBForCustomer(Customer customer) {
		
		if (customer != null) {
			List<Entitlement> entitlements=null;
			try {
				entitlements = entlservice.findEntitlementByName(customer.getName());
			} catch (EntitlementNotFoundException e) {
				logger.error("Error while fetching entitlement {}"+e.getMessage());
			}
			Entitlement entitlement = null;
			List<Entitlement> etlList = null; 
			if (entitlements != null && entitlements.size() > 0)
				entitlement = entitlements.get(0);
			if (entitlement.getDatabaseName() !=null && !entitlement.getDatabaseName().isEmpty()) {
				try {
					etlList= entlservice.checkInfluxDBForCustomer(entitlement.getDatabaseName(),customer.getId());
				} catch (EntitlementNotFoundException e) {
					logger.error("Error while fetching entitlement {}"+e.getMessage());
				}
			} 
			if(etlList != null && etlList.size() > 0){
				return false;
			}
	   }
		return true;
	}
}
