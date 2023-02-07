package se.kth.iv1201.presentation.person;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import se.kth.iv1201.presentation.forms.CreateAccountForm;
import se.kth.iv1201.presentation.forms.LoginForm;

@Controller
// TODO Scope?
public class PersonController {
    /**
     * Redirection to default page
     * @return Response, redirection to default page
     */
    @GetMapping("/")
    public String showDefaultView() {
        return "redirect:login";
    }

    /**
     * Show the login page.
     * @return
     */
    @GetMapping("/login")
    public String showLoginView() {
        return "login";
    }

    /**
     * Form submission on login page
     * @param model
     * @param loginForm
     * @return Login page URL.
     */
    @PostMapping("/login")
    public String login(Model model, LoginForm loginForm) {
        // TODO Check credentials against DB.
        // Where is model reachable from? Should we store password here?
        model.addAttribute("loginForm", loginForm);
        return "login";
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
     * createAccountForm has been submitted.
     * @return
     */
    @PostMapping("/createAccount")
    public String createAccount(Model model, CreateAccountForm createAccountForm) {
        // TODO Create account in DB. Possibly checking if account already exists with separate call / method.
        // TODO Hash the password when stored in DB.
        System.out.println("POST from createAccount");
        return "login";
    }

}