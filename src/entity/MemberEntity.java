package entity;

public class MemberEntity {
    private String memId;
    private String name;
    private String phone;
    private String userName;
    private String password;
    
    public MemberEntity() {
    }

    public MemberEntity(String memId, String name, String phone, String userName, String password) {
        this.memId = memId;
        this.name = name;
        this.phone = phone;
        this.userName = userName;
        this.password = password;
    }

    public String getMemId() {
        return memId;
    }

    public void setMemId(String memId) {
        this.memId = memId;
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

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "MemberEntity [memId=" + memId + ", name=" + name + ", phone=" + phone + ", userName=" + userName
                + ", password=" + password + "]";
    }

}
