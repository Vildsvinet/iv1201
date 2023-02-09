package se.kth.iv1201.presentation.person;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import se.kth.iv1201.application.RecruitmentService;
import se.kth.iv1201.domain.IllegalDatabaseAccessException;
import se.kth.iv1201.domain.PersonDTO;
import se.kth.iv1201.presentation.forms.CreateUserForm;
import se.kth.iv1201.presentation.forms.LoginForm;

@Controller
// TODO Scope?
public class PersonController {
    static final String DEFAULT_PAGE_URL = "/";
    static final String LOGIN_PAGE_URL = "login";
    static final String CREATE_USER_PAGE_URL = "createUser";

    @Autowired
    private RecruitmentService service;

    private PersonDTO person;

    /**
     * Redirection to default page
     * @return Response, redirection to default page
     */
    @GetMapping("/")
    public String showDefaultView() {
        return "redirect:" + LOGIN_PAGE_URL;
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
     * Form submission on login page
     * @param
     * @param loginForm
     * @return Login page URL.
     */
    @PostMapping("/" + LOGIN_PAGE_URL)
    public String login(@ModelAttribute(name="loginForm") LoginForm loginForm, Model m) {
        System.out.println(loginForm.getUsername());
        String username = loginForm.getUsername();
        String password = loginForm.getPassword();
        // make db call here
        try{
            service.loginPerson(username, password);
        } catch(IllegalDatabaseAccessException ide){
            m.addAttribute("error", ide.getMessage());
            return LOGIN_PAGE_URL;
        }

        m.addAttribute("username", username);
        m.addAttribute("password", password);
        return "application";
    }

    /**
     * Show create account view.
     * @return CreateAccount URL.
     */
    @GetMapping("/createAccount")
    public String showCreateAccountView() {
        System.out.println("GET from createAccount");
        return "createAccount";
    }

    /**
     * createUserForm has been submitted.
     * @return
     */
    @PostMapping("/createAccount")
    public String createAccount(Model model, CreateUserForm createUserForm) {
        // TODO Create account in DB. Possibly checking if account already exists with separate call / method.
        // TODO Hash the password when stored in DB.
        System.out.println("POST from createAccount");
        return "login";
    }

}