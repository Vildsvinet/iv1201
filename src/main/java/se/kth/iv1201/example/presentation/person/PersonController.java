package se.kth.iv1201.example.presentation.person;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import se.kth.iv1201.example.application.RecruitmentService;
import se.kth.iv1201.example.domain.IllegalDatabaseAccessException;
import se.kth.iv1201.example.domain.Person; // temp solution, should autowire service layer instead, see bank sample
import se.kth.iv1201.example.domain.PersonDTO;
import se.kth.iv1201.example.presentation.person.LoginForm;

import javax.validation.Valid;


@Controller
@Scope("session")
public class PersonController {

    static final String DEFAULT_PAGE_URL = "/";

    static final String LOGIN_PAGE_URL = "login";
    static final String CREATE_USER_PAGE_URL = "createUser";

    @Autowired
    private RecruitmentService service;

    private PersonDTO person;

    /**
     * No page is specified, redirect to the login page.
     *
     * @return A response that redirects the browser to the login page.
     */
    @GetMapping(DEFAULT_PAGE_URL)
    public String showDefaultView() {
        //LOGGER.trace("Call to context root.");
        return "redirect:" + LOGIN_PAGE_URL;
    }

    /**
     * A get request for the login page.
     *
     * @param loginForm Used in the login form.
     * @return The loginn page url.
     */
    @GetMapping("/" + LOGIN_PAGE_URL)
    public String showLoginView(LoginForm loginForm) {
        //LOGGER.trace("Call to account selection view.");
        return LOGIN_PAGE_URL;
    }

    @PostMapping("/" + LOGIN_PAGE_URL)
    public String login(@ModelAttribute(name="loginForm") Person person, Model m) {
        System.out.println(person.getUsername());
        String username = person.getUsername();
        String password = person.getPassword();
        // make db call here
        if(username.equals("Admin") && password.equals("Admin@123")) {
            m.addAttribute("username", username);
            m.addAttribute("password", password);
            return "application";
        }
        m.addAttribute("error", "Incorrect Username & Password");
        return LOGIN_PAGE_URL;

    }

    @GetMapping("/" + CREATE_USER_PAGE_URL)
    public String showLoginView(CreateUserForm createUserForm) {
        //LOGGER.trace("Call to account selection view.");
        return CREATE_USER_PAGE_URL;
    }

    @PostMapping("/" + CREATE_USER_PAGE_URL)
    public String createUser(@ModelAttribute(name="createUserForm") CreateUserForm createUserForm, Model m) throws IllegalDatabaseAccessException {
        System.out.println(createUserForm.getUsername());
        String username = createUserForm.getUsername();
        String password = createUserForm.getPassword();
        int role_id = 2;
        // make db call here
        person = service.createPerson(createUserForm.getName(), createUserForm.getSurname(), createUserForm.getPnr(), createUserForm.getEmail(), createUserForm.getPassword(), role_id, createUserForm.getUsername());
        if(person == null) {
            m.addAttribute("error", "Incorrect Username & Password");
        }
        System.out.println(person.getUsername());
        System.out.println(person.getPassword());

        return LOGIN_PAGE_URL;

    }


    @GetMapping("/greeting")
    public String greeting(@RequestParam(name="name", required=false, defaultValue="World") String name, Model model) {
        model.addAttribute("name", name);
        return "greeting";
    }


}
