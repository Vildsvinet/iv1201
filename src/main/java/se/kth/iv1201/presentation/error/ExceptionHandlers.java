package se.kth.iv1201.presentation.error;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServletRequest;
import org.hibernate.JDBCException;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.CannotCreateTransactionException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.net.ConnectException;

/**
 * This class is used to handle all exceptions.
 */
@Controller
@ControllerAdvice
public class ExceptionHandlers implements ErrorController {
    private static final String ERROR_PAGE_URL = "error";
    private static final String ERROR_TYPE = "errorType";
    private static final String NOT_FOUND_MESSAGE = "Page not found. If you expected it to exist, please contact support.";
    private static final String FORBIDDEN_MESSAGE = "You do not have permission to view this page!";
    private static final String INTERNAL_SERVER_ERROR_MESSAGE = "Something unexpected went wrong, please try again later or contact support.";
    private static final String SERVICE_UNAVAILABLE_MESSAGE = "The service is currently unavailable, please try again later.";
    private static final String GENERIC_ERROR_MESSAGE = "Something unexpected went wrong, please try again later or contact support.";
    private static final String DATABASE_ERROR_MESSAGE = "The database was unreachable, please try again later or contact support.";
    private static final String NO_HTTP_STATUS_MESSAGE = "No HTTP status code was found.";



    /**
     * This method is used to handle database exceptions.
     * @param exception
     * @param model
     * @return
     */
    @ExceptionHandler({JDBCException.class, CannotCreateTransactionException.class, ConnectException.class})
    @ResponseStatus(HttpStatus.SERVICE_UNAVAILABLE)
    public String handleJDBCException(JDBCException exception, Model model) {
        System.out.println("JDBCException: " + exception.getMessage());
        model.addAttribute(ERROR_TYPE, DATABASE_ERROR_MESSAGE);
        return ERROR_PAGE_URL;
    }

    /**
     * This method is used to handle generic exceptions,
     * when no more specific handler applies.
     * @param exception The exception to handle.
     * @return The generic error page.
     */
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public String handleGenericException(Exception exception, Model model) {
        System.out.println("Exception: " + exception.getMessage());
        model.addAttribute(ERROR_TYPE, GENERIC_ERROR_MESSAGE);
        return ERROR_PAGE_URL;
    }

    /*
    * TODO Remove this method.
     */
    @GetMapping("/forcedException")
    public String testException() throws Exception {
        throw new Exception("This is a test exception.");
    }

    /**
     * This method is used to handle http exceptions.
     * @param request The request that caused the exception.
     * @return The error page.
     */
    @GetMapping("/" + ERROR_PAGE_URL)
    public String handleHttpError(HttpServletRequest request, Model model) {
        Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);

        if (status != null) {
            int statusCode = Integer.parseInt(status.toString());

            if (statusCode == HttpStatus.NOT_FOUND.value()) {
                model.addAttribute(ERROR_TYPE, NOT_FOUND_MESSAGE);

            } else if (statusCode == HttpStatus.FORBIDDEN.value()) {
                model.addAttribute(ERROR_TYPE, FORBIDDEN_MESSAGE);

            } else if (statusCode == HttpStatus.INTERNAL_SERVER_ERROR.value()) {
                model.addAttribute(ERROR_TYPE, INTERNAL_SERVER_ERROR_MESSAGE);

            } else if (statusCode == HttpStatus.SERVICE_UNAVAILABLE.value()) {
                model.addAttribute(ERROR_TYPE, SERVICE_UNAVAILABLE_MESSAGE);
            } else {
                model.addAttribute(ERROR_TYPE, GENERIC_ERROR_MESSAGE);
            }
        }
        else {
            model.addAttribute(ERROR_TYPE, NO_HTTP_STATUS_MESSAGE);
        }

        return ERROR_PAGE_URL;
    }

}
