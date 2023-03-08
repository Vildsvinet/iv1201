package se.kth.iv1201.presentation.error;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;

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
        System.out.println("Exception: " + exception.getMessage());
        model.addAttribute(ERROR_TYPE, "Exception handler caught an exception, amazing! Please contact support.");
        return ERROR_PAGE_URL;
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
                model.addAttribute(ERROR_TYPE, "Page not found, try accessing a page that does exist instead. If you expected it to exist, please contact support.");

            } else if (statusCode == HttpStatus.FORBIDDEN.value()) {
                model.addAttribute(ERROR_TYPE, "Access denied. Get rekt or log in with proper access. If you are a recruiter, please contact support.");

            } else if (statusCode == HttpStatus.INTERNAL_SERVER_ERROR.value()) {
                model.addAttribute(ERROR_TYPE, "Internal server error, please contact support.");

            } else {
                model.addAttribute(ERROR_TYPE, "Unhandled error, please contact support.");

            }
        }
        else {
            model.addAttribute(ERROR_TYPE, "HTTP Status was null, how did you even manage that? Please contact support or try again.");
        }

        return ERROR_PAGE_URL;
    }

}
