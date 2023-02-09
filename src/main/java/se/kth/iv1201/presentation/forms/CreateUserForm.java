package se.kth.iv1201.presentation.forms;

import javax.validation.constraints.NotBlank;

/**
 * A form bean for creating accounts.
 */
public class CreateUserForm {
    private final String notBlankMessage = "Please write something.";
    @NotBlank(message = notBlankMessage)
    // TODO Add more restrictions
    private String name;
    private String surname;
    private String pnr;
    private String email;
    private String password;
    private String username;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPnr() {
        return pnr;
    }

    public void setPnr(String pnr) {
        this.pnr = pnr;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }


}
