package se.kth.iv1201.presentation.person;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
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
    static final String CREATE_USER_PAGE_URL = "createUser";
    static final String HOME_PAGE_URL = "home";
    static final String HELLO_PAGE_URL = "hello";
    static final String REVIEW_PAGE_URL = "review";

    @Autowired
    private RecruitmentService service;

    private PersonDTO person;

    /**
     * Redirection to default page
     * @return Response, redirection to default page
     */
    @GetMapping("/")
    public String showDefaultView() {
        return "redirect:" + HOME_PAGE_URL;
    }

    /**
     * Redirection to home page.
     * @return Redirect to home page.
     */
    @GetMapping("/home")
    public String showHomeView() {
        return HOME_PAGE_URL;
    }

    /**
     * Redirection to hello page.
     * @return Redirect to hello page.
     */
    @GetMapping("/hello")
    public String showHelloView() {
        return HELLO_PAGE_URL;
    }

    /**
     * Redirection to hello page.
     * @return Redirect to hello page.
     */
    @GetMapping("/review")
    public String showReviewView() {
        return REVIEW_PAGE_URL;
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

    /**
     * Form submission on login page. Deprecated.
     * @param
     * @param loginForm
     * @return Login page URL.
     */
    @PostMapping("/" + LOGIN_PAGE_URL)
    public String login(@ModelAttribute(name="loginForm") LoginForm loginForm, Model m) {
        System.out.println("POST from login. Unexpected, should be handled by Spring Security.");
        return DEFAULT_PAGE_URL;
    }

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
        // make db call here
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