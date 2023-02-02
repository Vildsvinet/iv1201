package se.kth.iv1201.example.presentation.person;

import javax.validation.constraints.NotBlank;

/**
 * A form bean for the login form.
 */
class LoginForm {

    @NotBlank(message = "...")
    private String username;

    @NotBlank(message = "...")
    private String password;

    /**
     * @return The username of an existing user.
     */
    public String getUserName() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    /**
     * @param username The initial balance of the account that will be created.
     */
    public void setUsername(String username) {
        this.username = username;
    }


    /**
     * @param password The user's password.
     */
    public void setPassword(String password) {
        this.password = password;
    }
}
