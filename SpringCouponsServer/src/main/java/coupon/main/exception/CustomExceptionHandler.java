package coupon.main.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.NoHandlerFoundException;
@ControllerAdvice
public class CustomExceptionHandler {
  @ExceptionHandler( Throwable.class )
    public ResponseEntity<Object> handleThrowable( Throwable t) {
         
        ApiError apiError =
        new ApiError(HttpStatus.INTERNAL_SERVER_ERROR,
                "Something wrong happened... Please contact the admin.");
        return new ResponseEntity<Object>(apiError,HttpStatus.INTERNAL_SERVER_ERROR);
         
    }
     
    @ExceptionHandler( CouponSystemException.class )
    public ResponseEntity<Object> handleCouponSystemException( CouponSystemException e) {
         
        ApiError apiError =
        new ApiError(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        return new ResponseEntity<Object>(apiError,HttpStatus.INTERNAL_SERVER_ERROR);
         
    }
     
     
    @ExceptionHandler( NoHandlerFoundException.class )
    public ResponseEntity<Object> handleResourceNotFound( NoHandlerFoundException notFoundException) {
 
         
        ApiError apiError =
        new ApiError(HttpStatus.NOT_FOUND,
                "Could not found " + notFoundException.getRequestURL() + " (" + notFoundException.getHttpMethod() + ")");
        return new ResponseEntity<Object>(apiError,HttpStatus.NOT_FOUND);
         
    }
     
}

