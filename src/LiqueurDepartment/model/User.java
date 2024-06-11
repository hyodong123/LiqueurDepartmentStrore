package LiqueurDepartment.model;

public class User {
    private String name;
    private String phone;
    
    public User(String name, String phone) {
        this.name = name;
        this.phone = phone;
    }

    // 기본 생성자
    public User() {
    }

    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public String getPhone() {
        return phone;
    }
    
    public void setPhone(String phone) {
        this.phone = phone;
    }
}
