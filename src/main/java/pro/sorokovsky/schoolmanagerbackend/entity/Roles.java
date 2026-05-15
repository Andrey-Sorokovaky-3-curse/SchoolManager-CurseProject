package pro.sorokovsky.schoolmanagerbackend.entity;

public enum Roles {
    USER("USER"),
    PARENT("PARENT"),
    EMPLOYEE("EMPLOYEE"),
    PUPIL("PUPIL");

    private final String role;

    Roles(String role) {
        this.role = role;
    }

    public String value() {
        return role;
    }
}