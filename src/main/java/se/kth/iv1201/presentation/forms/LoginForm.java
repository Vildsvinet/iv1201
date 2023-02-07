package se.kth.iv1201.presentation.forms;

import javax.validation.constraints.NotBlank;

public class LoginForm {
    private final String notBlankMessage = "Please write something.";
    @NotBlank(message = notBlankMessage)
    private String username;
    @NotBlank(message = notBlankMessage)
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}