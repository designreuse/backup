package com.nvidia.cosmos.cloud.rest.controller;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.POST;
import javax.xml.bind.ValidationException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.nvidia.cosmos.cloud.dtos.ResponseDTO;
import com.nvidia.cosmos.cloud.exceptions.BaseException;
import com.nvidia.cosmos.cloud.exceptions.EulaExistsException;
import com.nvidia.cosmos.cloud.rest.exception.NotAuthorizeException;
import com.nvidia.cosmos.cloud.services.eula.model.Eula;
import com.nvidia.cosmos.cloud.services.eula.service.EulaService;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;
import com.wordnik.swagger.annotations.ApiResponse;
import com.wordnik.swagger.annotations.ApiResponses;

@RestController
public class EulaController extends BaseController {
	/**
	* 
	*/
	private static Logger logger = LoggerFactory.getLogger(EulaController.class);
	/**
     * 
     */
    @Autowired
	MessageSource messageSource;
	/**
	 * 
	 */
	EulaService service;

	/**
	 * 
	 */
	public EulaController() {
		service = factory.getEulaService();

	}

	/**
	 * RBAC and tenant rules
	 * This method is allowed for user with role Super Admin
	 * Super Admin can create EULA
	 * @param request
	 * @return
	 * @throws EulaExistsException
	 * @throws BaseException
	 */
	 @javax.ws.rs.Path("/api/saveEula")
	    @POST
		@ApiOperation( value = "Create a new End User License Agreement", notes = "User can create a new EULA. User with role Super Admin is allowed. "
				+ "Super Admin can create EULA", response = Eula.class)
	    @ApiResponses( {
		    @ApiResponse( code = 200, message = "EULA has been created sucessfully" )
		} )
	@Transactional(rollbackFor = { ValidationException.class,EulaExistsException.class, BaseException.class })
	@RequestMapping(value = "/api/saveEula", method = RequestMethod.POST)
	 @PreAuthorize("hasAuthority('Super Admin')")
	public ResponseDTO saveEula(@ApiParam(name="File", value="The license agreement file ", required=true ) @RequestParam(value="file",required=false) CommonsMultipartFile file, 
			@ApiParam(name="EULA type name", value="The type of the EULA that needs to be created ", required=true) @RequestParam(value="name", required=false) String name, HttpServletRequest request)
			throws NotAuthorizeException,EulaExistsException, ValidationException,BaseException {

		String message = null;
		String statusCode = HttpStatus.OK.toString();
		if (file != null && file.getSize() > 0) {
			logger.info("Adding a new eula entry with information:" + file.getName());
			if((name !=null && name !="") && (name.equalsIgnoreCase("CUSTOMEREULA") || name.equalsIgnoreCase("APPLIANCEEULA"))){
			Eula eula = new Eula(file.getOriginalFilename(), file.getBytes(), name);
			service.createEula(eula);
			message = environment.getProperty("eula.uploaded");
			} else{
				throw new ValidationException(environment.getProperty("eula.name.notempty"));
			}
		} else {
			message = environment.getProperty("eula.not.uploaded");
			throw new ValidationException(environment.getProperty("eula.file.notenpty"));
		}
		ResponseDTO response = new ResponseDTO(message, Integer.parseInt(statusCode));
		return response;
	}
}
