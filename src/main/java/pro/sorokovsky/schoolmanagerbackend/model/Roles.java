package pro.sorokovsky.schoolmanagerbackend.model;

public enum Roles {
    USER("USER"),
    PARENT("Parent"),
    EMPLOYEE("Employee"),
    PUPIL("Pupil");

    private final String role;

    Roles(String role) {
        this.role = role;
    }

    public String value() {
        return role;
    }
}