package org.awaa.controller.utils;
/**
 * 
 */

import javax.servlet.http.HttpServletRequest;

import org.awwa.utils.exeptions.BusinessExeption;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * @author john.lopez
 *
 */
@ControllerAdvice
public class ExceptionController {

	@ExceptionHandler(value = BusinessExeption.class)
	public ResponseEntity<BusinessExeption> methodNotSupportErrorHandler(HttpServletRequest req, BusinessExeption ex) {
		return new ResponseEntity<BusinessExeption>(ex, HttpStatus.BAD_REQUEST);
	}

}
