package se.kth.iv1201.presentation.error;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * This class is used to handle all exceptions.
 */
@ControllerAdvice
public class ExceptionHandlers implements ErrorController {
    private static final String ERROR_PAGE_URL = "error";

    /**
     * This method is used to handle generic exceptions,
     * when no more specific handler applies.
     * @param exception The exception to handle.
     * @return The generic error page.
     */
    @ExceptionHandler(Exception.class)
    public String handleException(Exception exception) {
        return ERROR_PAGE_URL;
    }

}
