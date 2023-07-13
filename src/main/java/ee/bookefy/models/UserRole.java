package ee.bookefy.models;

public enum UserRole {
    USER, ADMIN;

    public String toApplicationRole() {
        return "ROLE_" + this.name();
    }

    public boolean isAdmin() {
        return this == ADMIN;
    }
}
