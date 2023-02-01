package se.kth.iv1201.example.repository.person;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.ui.Model;
import se.kth.iv1201.example.domain.Person; // temp solution, should autowire service layer instead, see bank sample

import java.io.Console;

@Controller
@Scope("session")
public class PersonController {

    static final String DEFAULT_PAGE_URL = "/";

    static final String LOGIN_PAGE_URL = "login";

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

    @GetMapping("/greeting")
    public String greeting(@RequestParam(name="name", required=false, defaultValue="World") String name, Model model) {
        model.addAttribute("name", name);
        return "greeting";
    }


}
