package com.sectorseven.web.controller.admin;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.sectorseven.model.common.ResponseObject;
import com.sectorseven.model.util.BadRequestException;
import com.sectorseven.model.util.CommonException;
import com.sectorseven.model.util.InternalServerException;
import com.sectorseven.model.util.UserDefinedException;
import com.sectorseven.service.common.StudentResponseCodes;

@ControllerAdvice
public class UserExceptionController extends ResponseEntityExceptionHandler  {
	
     
    @ExceptionHandler(UserDefinedException.class)
    public final ResponseEntity<ResponseObject> handleUserNotFoundException
                        (UserDefinedException ex,WebRequest request) 
    {
        ResponseObject resp = new ResponseObject();
        resp.setStatusMessage(ex.getLocalizedMessage());
        resp.setStatusCode(StudentResponseCodes.NOTFOUND_CODE);
        return new ResponseEntity<>(resp, HttpStatus.NOT_FOUND);
    }
     
    @ExceptionHandler(InternalServerException.class)
    public final ResponseEntity<ResponseObject> handleInternalServerException
                        (InternalServerException ex, WebRequest request) {
    	 ResponseObject resp = new ResponseObject();
         resp.setStatusMessage(ex.getLocalizedMessage());
         resp.setStatusCode(StudentResponseCodes.INTERNALSERVER_CODE);
        return new ResponseEntity<>(resp, HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @ExceptionHandler(BadRequestException.class)
    public final ResponseEntity<ResponseObject> handleBadRequestException
                        (BadRequestException ex, WebRequest request) {
    	 ResponseObject resp = new ResponseObject();
         resp.setStatusMessage(ex.getLocalizedMessage());
         resp.setStatusCode(StudentResponseCodes.BADREQUEST_CODE);
        return new ResponseEntity<>(resp, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(CommonException.class)
    public final ResponseEntity<ResponseObject> handleCommonException
                        (CommonException ex, WebRequest request) {
    	 ResponseObject resp = new ResponseObject();
         resp.setStatusMessage(ex.getLocalizedMessage());
         resp.setStatusCode(StudentResponseCodes.FAILED_CODE);
        return new ResponseEntity<>(resp,HttpStatus.OK);
    }
}
