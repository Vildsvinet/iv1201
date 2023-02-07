package se.kth.iv1201.presentation.person;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import se.kth.iv1201.presentation.forms.LoginForm;

@Controller
// TODO Scope?
public class PersonController {
    @GetMapping("/")
    public String showDefaultView() {
        return "redirect:/login";
    }

    @GetMapping("/login")
    public String showLoginView(Model model, LoginForm loginForm) {
        model.addAttribute("loginForm", loginForm);
        return "login";
    }
}