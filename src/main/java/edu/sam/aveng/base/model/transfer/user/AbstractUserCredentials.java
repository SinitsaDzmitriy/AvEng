package edu.sam.aveng.base.model.transfer.user;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public abstract class AbstractUserCredentials {

    @NotBlank(message = "{validation.back.email.blank}")
    @Size(max = 254, message = "{validation.back.email.size}")
    @Email(message = "{validation.back.email.pattern}")
    protected String email;

    /*
            RFC 2821: "The maximum total length of a reverse-path or
        forward-path is 256 characters, including the punctuation and
        element separators."
            The forward-path will contain at least a pair of angle
        brackets in addition to the Mailbox, which limits the email
        address to 254 characters.
    */

    @NotBlank(message = "{validation.back.password.blank}")
    @Size(min = 8, max = 64, message = "{validation.back.password.size}")
    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]*$", message = "{validation.back.password.pattern}")
    protected String password;

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

}
