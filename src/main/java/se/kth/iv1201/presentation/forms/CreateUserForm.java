package se.kth.iv1201.presentation.forms;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

/**
 * A form bean for creating user accounts.
 * Contains validation rules.
 */
public class CreateUserForm {
    private final String notBlankMessage = "Please write something.";
    @NotBlank(message = notBlankMessage)
    @Size(min = 2, max = 50)
    private String name;

    @NotBlank(message = notBlankMessage)
    @Size(min = 2, max = 50)
    private String surname;

    @NotBlank(message = notBlankMessage)
    @Pattern(regexp = "^(19|20)\\d{2}(0[1-9]|1[012])(0[1-9]|[12][0-9]|3[01])[-\\.]\\d{4}$")
    private String pnr;

    @NotBlank(message = notBlankMessage)
    @Email
    @Size(min = 3, max = 50)
    private String email;

    @NotBlank(message = notBlankMessage)
    @Size(min = 4, max = 25)
    private String password;

    @NotBlank(message = notBlankMessage)
    @Size(min = 4, max = 50)
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
