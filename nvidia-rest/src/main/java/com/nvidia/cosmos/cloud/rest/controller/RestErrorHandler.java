package com.nvidia.cosmos.cloud.rest.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.xml.bind.ValidationException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.nvidia.cosmos.cloud.dtos.ErrorResponseDTO;
import com.nvidia.cosmos.cloud.dtos.JWTResponseDTO;
import com.nvidia.cosmos.cloud.dtos.error.ValidationErrorDTO;
import com.nvidia.cosmos.cloud.exceptions.BaseException;
import com.nvidia.cosmos.cloud.exceptions.ClusterNotFoundException;
import com.nvidia.cosmos.cloud.exceptions.CustomerExistsException;
import com.nvidia.cosmos.cloud.exceptions.CustomerNotFoundException;
import com.nvidia.cosmos.cloud.exceptions.DBUniqueResultException;
import com.nvidia.cosmos.cloud.exceptions.EntitlementExistsException;
import com.nvidia.cosmos.cloud.exceptions.EntitlementNotFoundException;
import com.nvidia.cosmos.cloud.exceptions.EulaNotFoundException;
import com.nvidia.cosmos.cloud.exceptions.GraphanaFailedException;
import com.nvidia.cosmos.cloud.exceptions.JWTUserNotFoundException;
import com.nvidia.cosmos.cloud.exceptions.JobNotFoundException;
import com.nvidia.cosmos.cloud.exceptions.QuayFailedException;
import com.nvidia.cosmos.cloud.exceptions.RegistrationFailedException;
import com.nvidia.cosmos.cloud.exceptions.SerialNumberExitsException;
import com.nvidia.cosmos.cloud.exceptions.TokenExistsException;
import com.nvidia.cosmos.cloud.exceptions.TokenNotFoundException;
import com.nvidia.cosmos.cloud.exceptions.UserAuthExistsException;
import com.nvidia.cosmos.cloud.exceptions.UserAuthNotFoundException;
import com.nvidia.cosmos.cloud.exceptions.UserExistsException;
import com.nvidia.cosmos.cloud.exceptions.UserNameExitsException;
import com.nvidia.cosmos.cloud.exceptions.UserNotFoundException;
import com.nvidia.cosmos.cloud.rest.exception.NotAuthorizeException;

/**
 * This the RestError Handler handles all exceptions and returns proper DTO back.
 * @author bprasad
 */
@ControllerAdvice
public class RestErrorHandler {

    private static Logger LOGGER = LoggerFactory.getLogger(RestErrorHandler.class);

    
    @Autowired
	Environment environment;
    
    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ErrorResponseDTO constraintviolation(ConstraintViolationException ex) {
        Set<ConstraintViolation<?>> constraintViolations =  ex.getConstraintViolations();

    	Map<String, String> message= new HashMap<String, String>();
        for (ConstraintViolation<?> constraintViolation : constraintViolations) {        	        	
      	  message.put(constraintViolation.getPropertyPath().toString(), constraintViolation.getMessage());
        }
       	LOGGER.error("Failed with message {} ",message.toString());
    	return new ErrorResponseDTO(message.toString(), HttpStatus.BAD_REQUEST.value());
    }   
    
    /**
     *
     * @param ex
     * @return
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ValidationErrorDTO processValidationError(MethodArgumentNotValidException ex) {
        BindingResult result = ex.getBindingResult();
        List<FieldError> fieldErrors = result.getFieldErrors();

        return processFieldErrors(fieldErrors);
    }

    private ValidationErrorDTO processFieldErrors(List<FieldError> fieldErrors) {
        ValidationErrorDTO dto = new ValidationErrorDTO();

        for (FieldError fieldError: fieldErrors) {
            //String localizedClidResponseDTO = resolveLocalizedClidResponseDTO(fieldError);
        	LOGGER.error("Failed with message {} ",fieldError.getDefaultMessage());
            dto.addFieldError(fieldError.getField(), fieldError.getDefaultMessage());
        }

        return dto;
    }

/*    private String resolveLocalizedClidResponseDTO(FieldError fieldError) {
        Locale currentLocale =  LocaleContextHolder.getLocale();
        String localizedClidResponseDTO = messageSource.getMessage(fieldError, currentLocale);

        //If the message was not found, return the most accurate field error code instead.
        //You can remove this check if you prefer to get the default error message.
        if (localizedClidResponseDTO.equals(fieldError.getDefaultMessage())) {
            String[] fieldErrorCodes = fieldError.getCodes();
            localizedClidResponseDTO = fieldErrorCodes[0];
        }

        return localizedClidResponseDTO;
    }*/

    /**
     *
     * @param ex
     * @param req
     * @return
     */
    @ExceptionHandler(Throwable.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public ErrorResponseDTO handleThrowable(Throwable ex, HttpServletRequest req) {
    	LOGGER.error("Unexcepted exception {}", ex.getMessage());
    	return new ErrorResponseDTO(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.value());
    }
    /**
     *
     * @param ex
     * @param req
     * @return
     */
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public ErrorResponseDTO handleException(Exception ex, HttpServletRequest req) {
    	LOGGER.error("Unexcepted exception {}", ex.getMessage());
    	return new ErrorResponseDTO(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.value());
    }
    /**
     *
     * @param ex
     * @param req
     * @return
     */
    @ExceptionHandler(RuntimeException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public ErrorResponseDTO handleRuntimeException(RuntimeException ex, HttpServletRequest req) {
    	LOGGER.error("Runtime exception {}", ex.getClass().getName());
    	return new ErrorResponseDTO(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.value());
    }
   
    /**
     *
     * @param ex
     * @param req
     * @return
     */
    @ExceptionHandler(BaseException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public ErrorResponseDTO handleBaseException(BaseException ex, HttpServletRequest req) {
    	LOGGER.error("Data base exception {}", ex.getMessage());
    	return new ErrorResponseDTO(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.value());
    }
    /**
     *
     * @param ex
     * @param req
     * @return
     */
    @ExceptionHandler(UserExistsException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    @ResponseBody
    public ErrorResponseDTO handleUserExistsException(UserExistsException ex, HttpServletRequest req) {
    	LOGGER.debug("User exists exception {}", ex.getMessage());
    	return new ErrorResponseDTO(ex.getMessage(), HttpStatus.CONFLICT.value());
    }
    /**
     *
     * @param ex
     * @param req
     * @return
     */
    @ExceptionHandler(UserNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    public ErrorResponseDTO handleUserNotFoundException(UserNotFoundException ex) {
    	LOGGER.debug("User not found exception {}", ex.getMessage());
    	return new ErrorResponseDTO(ex.getMessage(), HttpStatus.NOT_FOUND.value());
    }
     /**
    *
    * @param ex
    * @param req
    * @return
    */
   @ExceptionHandler(JWTUserNotFoundException.class)
   @ResponseStatus(HttpStatus.NOT_FOUND)
   @ResponseBody
   public JWTResponseDTO handleJWTUserNotFoundException(JWTUserNotFoundException ex) {
   	LOGGER.debug("User not found exception {}", ex.getMessage());
   	return new JWTResponseDTO(ex.getMessage(), HttpStatus.NOT_FOUND.value());
   }
   
   /**
   *
   * @param ex
   * @param req
   * @return
   */
  @ExceptionHandler(GraphanaFailedException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  @ResponseBody
  public ErrorResponseDTO handleGraphanaFailedException(GraphanaFailedException ex) {
  	LOGGER.debug("graphana failed exception {}", ex.getMessage());
  	return new ErrorResponseDTO(ex.getMessage(), HttpStatus.BAD_REQUEST.value());
  }
  
  /**
  *
  * @param ex
  * @param req
  * @return
  */
 @ExceptionHandler(QuayFailedException.class)
 @ResponseStatus(HttpStatus.BAD_REQUEST)
 @ResponseBody
 public ErrorResponseDTO handleQuayFailedException(QuayFailedException ex) {
 	LOGGER.debug("Quay failed exception {}", ex.getMessage());
 	return new ErrorResponseDTO(ex.getMessage(), HttpStatus.BAD_REQUEST.value());
 }
    /**
    *
    * @param ex
    * @param req
    * @return
    */
   @ExceptionHandler(DBUniqueResultException.class)
   @ResponseStatus(HttpStatus.GONE)
   @ResponseBody
   public ErrorResponseDTO handleDBUniqueResultException(DBUniqueResultException ex) {
   	LOGGER.debug("User not found exception {}", ex.getMessage());
   	return new ErrorResponseDTO(ex.getMessage(), HttpStatus.GONE.value());
   }

   /**
   *
   * @param ex
   * @param req
   * @return
   */
  @ExceptionHandler(RegistrationFailedException.class)
  @ResponseStatus(HttpStatus.FORBIDDEN)
  @ResponseBody
  public ErrorResponseDTO handleRegistrationFailedException(RegistrationFailedException ex) {
  	LOGGER.debug("User registration failed exception {}", ex.getMessage());
  	return new ErrorResponseDTO(ex.getMessage(), HttpStatus.FORBIDDEN.value());
  }

    /**
     *
     * @param ex
     * @param req
     * @return
     */
    @ExceptionHandler(UserAuthExistsException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    @ResponseBody
    public ErrorResponseDTO handleUserAuthExistsException(UserAuthExistsException ex, HttpServletRequest req) {
    	LOGGER.debug("UserAuth exists exception {}", ex.getMessage());
    	return new ErrorResponseDTO(ex.getMessage(), HttpStatus.CONFLICT.value());
    }
    /**
     *
     * @param ex
     * @param req
     * @return
     */
    @ExceptionHandler(UserAuthNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    public ErrorResponseDTO handleUserAuthNotFoundException(UserAuthNotFoundException ex) {
    	LOGGER.debug("UserAuth not found exception {}", ex.getMessage());
    	return new ErrorResponseDTO(ex.getMessage(), HttpStatus.NOT_FOUND.value());
    }
    /**
    *
    * @param ex
    * @param req
    * @return
    */
   @ExceptionHandler(CustomerExistsException.class)
   @ResponseStatus(HttpStatus.CONFLICT)
   @ResponseBody
   public ErrorResponseDTO handleCustomerExistsException(CustomerExistsException ex, HttpServletRequest req) {
   	LOGGER.debug("Customer exists exception {}", ex.getMessage());
   	return new ErrorResponseDTO(ex.getMessage(), HttpStatus.CONFLICT.value());
   }
   /**
    *
    * @param ex
    * @param req
    * @return
    */
   @ExceptionHandler(CustomerNotFoundException.class)
   @ResponseStatus(HttpStatus.NOT_FOUND)
   @ResponseBody
   public ErrorResponseDTO handleCustomerNotFoundException(CustomerNotFoundException ex) {
   	LOGGER.debug("Customer not found exception {}", ex.getMessage());
   	return new ErrorResponseDTO(ex.getMessage(), HttpStatus.NOT_FOUND.value());
   }
   /**
    *
    * @param ex
    * @param req
    * @return
    */
   @ExceptionHandler(EntitlementExistsException.class)
   @ResponseStatus(HttpStatus.CONFLICT)
   @ResponseBody
   public ErrorResponseDTO handleEntitlementExistsException(EntitlementExistsException ex, HttpServletRequest req) {
	   LOGGER.debug("Entitlement exists exception {}", ex.getMessage());
	   return new ErrorResponseDTO(ex.getMessage(), HttpStatus.CONFLICT.value());
   }
   
   /**
   *
   * @param ex
   * @param req
   * @return
   */
  @ExceptionHandler(SerialNumberExitsException.class)
  @ResponseStatus(HttpStatus.CONFLICT)
  @ResponseBody
  public ErrorResponseDTO handleSerialNumberExitsException(SerialNumberExitsException ex, HttpServletRequest req) {
	   LOGGER.debug("Entitlement exists exception {}", ex.getMessage());
	   return new ErrorResponseDTO(ex.getMessage(), HttpStatus.CONFLICT.value());
  }
   /**
    *
    * @param ex
    * @param req
    * @return
    */
   @ExceptionHandler(EntitlementNotFoundException.class)
   @ResponseStatus(HttpStatus.NOT_FOUND)
   @ResponseBody
   public ErrorResponseDTO handleEntitlementNotFoundException(EntitlementNotFoundException ex) {
	   LOGGER.debug("Entitlement not found exception {}", ex.getMessage());
	   return new ErrorResponseDTO(ex.getMessage(), HttpStatus.NOT_FOUND.value());
   }   
   /**
   *
   * @param ex
   * @param req
   * @return
   */
  @ExceptionHandler(ClusterNotFoundException.class)
  @ResponseStatus(HttpStatus.NOT_FOUND)
  @ResponseBody
  public ErrorResponseDTO handleClusterNotFoundException(ClusterNotFoundException ex) {
	   LOGGER.debug("Cluster empty exception {}", ex.getMessage());
	   return new ErrorResponseDTO(ex.getMessage(), HttpStatus.NOT_FOUND.value());
  }
  /**
  *
  * @param ex
  * @param req
  * @return
  */
 @ExceptionHandler(UserNameExitsException.class)
 @ResponseStatus(HttpStatus.CONFLICT)
 @ResponseBody
 public ErrorResponseDTO handleUserNameExitsException(UserNameExitsException ex, HttpServletRequest req) {
	   LOGGER.debug("UserName exits exception {}", ex.getMessage());
	   return new ErrorResponseDTO(ex.getMessage(), HttpStatus.CONFLICT.value());
 }
   /**
   *
   * @param ex
   * @param req
   * @return
   */
  @ExceptionHandler(TokenExistsException.class)
  @ResponseStatus(HttpStatus.CONFLICT)
  @ResponseBody
  public ErrorResponseDTO handleTokenExistsException(TokenExistsException ex, HttpServletRequest req) {
	   LOGGER.debug("Token exists exception {}", ex.getMessage());
	   return new ErrorResponseDTO(ex.getMessage(), HttpStatus.CONFLICT.value());
  }
  /**
   *
   * @param ex
   * @param req
   * @return
   */
  @ExceptionHandler(TokenNotFoundException.class)
  @ResponseStatus(HttpStatus.NOT_FOUND)
  @ResponseBody
  public ErrorResponseDTO handleTokenNotFoundException(TokenNotFoundException ex) {
	   LOGGER.debug("Token not found exception {}", ex.getMessage());
	   return new ErrorResponseDTO(ex.getMessage(), HttpStatus.NOT_FOUND.value());
  }
  
  /**
  *
  * @param ex
  * @param req
  * @return
  */
 @ExceptionHandler(EulaNotFoundException.class)
 @ResponseStatus(HttpStatus.NOT_FOUND)
 @ResponseBody
 public ErrorResponseDTO handleEulaNotFoundException(EulaNotFoundException ex) {
	   LOGGER.debug("Eula not found exception {}", ex.getMessage());
	   return new ErrorResponseDTO(ex.getMessage(), HttpStatus.NOT_FOUND.value());
 }
 
 
 @ExceptionHandler(JobNotFoundException.class)
 @ResponseStatus(HttpStatus.OK)
 @ResponseBody
 public ErrorResponseDTO handleJobNotFoundException(JobNotFoundException ex){
	 LOGGER.debug("Entitlement not found exception {}", ex.getMessage());
	 return new ErrorResponseDTO(ex.getMessage(), HttpStatus.OK.value()); 
 }

 /**
 *
 * @param ex
 * @param req
 * @return
 */
@ExceptionHandler(AccessDeniedException.class)
@ResponseStatus(HttpStatus.FORBIDDEN)
@ResponseBody
public ErrorResponseDTO handleRuntimeException(AccessDeniedException ex) {
LOGGER.error("AccessDenied exception {}", ex.getClass().getName());
return new ErrorResponseDTO(ex.getMessage(), HttpStatus.FORBIDDEN.value());
}
 
 /**
 *
 * @param ex
 * @return
 */
 @ExceptionHandler(ValidationException.class)
 @ResponseStatus(HttpStatus.BAD_REQUEST)
 @ResponseBody
 public ErrorResponseDTO processValidationError(ValidationException ex) {
 LOGGER.error("Failed with message {} ",ex.getMessage());
 return new ErrorResponseDTO(ex.getMessage(), HttpStatus.BAD_REQUEST.value());
 }
 
 
 /**
 *
 * @param ex
 * @return
 */
 @ExceptionHandler(HttpMessageNotReadableException.class)
 @ResponseStatus(HttpStatus.BAD_REQUEST)
 @ResponseBody
 public ErrorResponseDTO processValidationError(HttpMessageNotReadableException ex) {
 LOGGER.error("Failed with message {} ",ex.getMessage());
 return new ErrorResponseDTO(environment.getProperty("bad.request"), HttpStatus.BAD_REQUEST.value());
 }
 
 
 
 /**
 *
 * @param ex
 * @return
 */
 @ExceptionHandler(NotAuthorizeException.class)
 @ResponseStatus(HttpStatus.UNAUTHORIZED)
 @ResponseBody
 public ErrorResponseDTO handleNotAuthorizedException(NotAuthorizeException ex) {
 LOGGER.error("Failed with message {} ",ex.getMessage());
 return new ErrorResponseDTO(ex.getMessage(), HttpStatus.UNAUTHORIZED.value());
 }
  
}
