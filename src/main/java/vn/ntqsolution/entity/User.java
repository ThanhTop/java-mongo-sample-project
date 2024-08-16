package vn.ntqsolution.entity;

public class User {
    private int id;
    private String name;
    private String role;
    private boolean isEmployee;

    public User(int id, String name, String role, boolean isEmployee) {
        this.id = id;
        this.name = name;
        this.role = role;
        this.isEmployee = isEmployee;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public boolean isEmployee() {
        return isEmployee;
    }

    public void setEmployee(boolean employee) {
        isEmployee = employee;
    }

    public static User createUser() {
        return new User(2, "ThanhNguyen", "dev", true);
    }
}
