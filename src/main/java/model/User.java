package model;

import util.Getter;
import util.ValidationAnnotation;

public class User {

    private int idUser;
    private String firstName;
    private String lastName;
    private String password;
    private String email;
    private String login;
    private Role role;


    public User(){}

    public User(int idUser, String firstName, String lastName, String password, String email, String login) {
        this.idUser = idUser;
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.email = email;
        this.login = login;
    }

    public int getIdUser() {
        return idUser;
    }


    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    @ValidationAnnotation(mask = "[A-Za-z-']{2,25}")
     public String getFirstName() {
        return firstName;
    }


    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @ValidationAnnotation(mask = "[A-Za-z-']{2,25}")
     public String getLastName() {
        return lastName;
    }


    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @ValidationAnnotation(mask = "[A-Za-z0-9_-]{5,25}")
    public String getPassword() {
        return password;
    }


    public void setPassword(String password) {
        this.password = password;
    }

    @ValidationAnnotation(mask = "^([a-z0-9_-]+\\.)*[a-z0-9_-]+@[a-z0-9_-]+(\\.[a-z0-9_-]+)*\\.[a-z]{2,6}$")
    @Getter(name = "email")
    public String getEmail() {
        return email;
    }


    public void setEmail(String email) {
        this.email = email;
    }

    @ValidationAnnotation(mask = "[A-Za-z0-9_-]{5,15}")
    public String getLogin() {
        return login;
    }


    public void setLogin(String login) {
        this.login = login;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (idUser != user.idUser) return false;
        if (!email.equals(user.email)) return false;
        return login.equals(user.login);

    }

    @Override
    public int hashCode() {
        int result = idUser;
        result = 31 * result + email.hashCode();
        result = 31 * result + login.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "User{" +
                "idUser=" + idUser +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", login='" + login + '\'' +
                '}';
    }


}
