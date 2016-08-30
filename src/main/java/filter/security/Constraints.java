package filter.security;


import model.Role;

public class Constraints {

    private Role role;
    private String urlPattern;

    public Constraints() {
    }

    public Constraints(Role role, String urlPattern) {
        super();
        this.role = role;
        this.urlPattern = urlPattern;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String getUrlPattern() {
        return urlPattern;
    }

    public void setUrlPattern(String urlPattern) {
        this.urlPattern = urlPattern;
    }

    @Override
    public String toString() {
        return "Constraints[role: " + role + "; url-pattern: " + urlPattern + "]";
    }

}
