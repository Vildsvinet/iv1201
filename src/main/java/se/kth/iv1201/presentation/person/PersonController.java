package se.kth.iv1201.presentation.person;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import se.kth.iv1201.application.RecruitmentService;
import se.kth.iv1201.domain.IllegalDatabaseAccessException;
import se.kth.iv1201.domain.PersonDTO;
import se.kth.iv1201.presentation.forms.CreateUserForm;
import se.kth.iv1201.presentation.forms.LoginForm;

@Controller
@Scope("session")
public class PersonController {
    public static final String DEFAULT_PAGE_URL = "/";
    public static final String LOGIN_PAGE_URL = "login";
    public static final String CREATE_USER_PAGE_URL = "createUser";
    public static final String HOME_APPLICANT_URL = "homeApplicant";
    public static final String HOME_RECRUITER_URL = "homeRecruiter";

    @Autowired
    private RecruitmentService service;

    private PersonDTO person;

    /**
     * Redirection to default page
     * @return Response, redirection to default page
     */
    @GetMapping(DEFAULT_PAGE_URL)
    public String showDefaultView() {
        return "redirect:" + LOGIN_PAGE_URL;
    }

    /**
     * Redirection to applicant home page.
     * @return Redirect to home page.
     */
    @GetMapping("/" + HOME_APPLICANT_URL)
    public String showHomeView() {
        return HOME_APPLICANT_URL;
    }

    /**
     * Redirection to recruiter home page.
     * @return Redirect to hello page.
     */
    @GetMapping("/" + HOME_RECRUITER_URL)
    public String showReviewView() {
        return HOME_RECRUITER_URL;
    }

    /**
     * A get request for the login page.
     *
     * @param loginForm Used in the login form.
     * @return The login page url.
     */
    @GetMapping("/" + LOGIN_PAGE_URL)
    public String showLoginView(LoginForm loginForm) {
        return LOGIN_PAGE_URL;
    }

    /*
    *   Post requests to login page are handled by Spring Security
     */

    /**
     * Show create account view.
     * @return CreateAccount URL.
     */
    @GetMapping("/" + CREATE_USER_PAGE_URL)
    public String showCreateUserView(CreateUserForm createUserForm) {
        System.out.println("GET from createUser");
        return CREATE_USER_PAGE_URL;
    }

    /**
     * Endpoint for handling the submission and validation of the form entered by the user.
     * @param createUserForm The form to be validated and processed.
     * @param bindingResult Used for checking validation errors.
     * @param m Used for binding attributes to the view that can be rendered.
     * @return  returns the form with status messages.
     * @throws IllegalDatabaseAccessException thrown whenever business logic of action is not permitted.
     */
    @PostMapping("/" + CREATE_USER_PAGE_URL)
    public String createUser(@Valid CreateUserForm createUserForm, BindingResult bindingResult, Model m) throws IllegalDatabaseAccessException {
        if(bindingResult.hasErrors()){
            m.addAttribute("createUserForm", createUserForm);
            return CREATE_USER_PAGE_URL;
        }

        int role_id = 2;
        try {
            person = service.createPerson(createUserForm.getName(), createUserForm.getSurname(), createUserForm.getPnr(), createUserForm.getEmail(), createUserForm.getPassword(), role_id, createUserForm.getUsername());
        } catch(Exception e){
            if(e.getMessage().equals("Username already exist.")) {
                m.addAttribute("userNameError", "Username already exist.");
            } else
                m.addAttribute("error", "Something went wrong. Try again later.");
            return CREATE_USER_PAGE_URL;
        }
        m.addAttribute("success", "Registration successful!");
        return CREATE_USER_PAGE_URL;

    }

}