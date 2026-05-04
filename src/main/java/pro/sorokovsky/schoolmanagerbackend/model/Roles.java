package pro.sorokovsky.schoolmanagerbackend.model;

public enum Roles {
    USER("USER"),
    PARENT("ParentModel"),
    EMPLOYEE("EmployeeModel"),
    PUPIL("PupilModel");

    private final String role;

    Roles(String role) {
        this.role = role;
    }

    public String value() {
        return role;
    }
}