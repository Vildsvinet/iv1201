package se.kth.iv1201.presentation.error;

import org.hibernate.JDBCException;
import org.hibernate.exception.JDBCConnectionException;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * This class is used to handle all exceptions.
 */
@Controller
@ControllerAdvice
public class ExceptionHandlers implements ErrorController {
    private static final String ERROR_PAGE_URL = "error";
    private static final String ERROR_TYPE = "errorType";


    /**
     * This method is used to handle generic exceptions,
     * when no more specific handler applies.
     * @param exception The exception to handle.
     * @return The generic error page.
     */
    @ExceptionHandler(Exception.class)
    public String handleException(Exception exception, Model model) {
        model.addAttribute(ERROR_TYPE, "any error whatsoever");
        return ERROR_PAGE_URL;
    }

    @ExceptionHandler(JDBCConnectionException.class)
    public String handleException(JDBCConnectionException e){
        // TODO add something to model?
        return ERROR_PAGE_URL;
    }



//    @ExceptionHandler(PSQLException.class)
//    public String handleException(PSQLException e){
//        return ERROR_PAGE_URL;
//    }

}
