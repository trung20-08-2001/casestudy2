package model;

import java.io.Serializable;

public class Account implements Serializable {
    private String codeAccount;
    private String accountName;
    private String password;
    private String fullName;
    private int age;
    private String address;
    private String phone;
    private Enum<Role> role;
    public Account() {
    }

    public Account(String codeAccount,String accountName, String password, String fullName, int age, String address,String phone, Enum<Role> role) {
        this.accountName = accountName;
        this.password = password;
        this.fullName = fullName;
        this.age = age;
        this.address = address;
        this.role = role;
        this.phone=phone;
        this.codeAccount=codeAccount;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Enum<Role> getRole() {
        return role;
    }

    public void setRole(Enum<Role> role) {
        this.role = role;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCodeAccount() {
        return codeAccount;
    }

    public void setCodeAccount(String codeAccount) {
        this.codeAccount = codeAccount;
    }

    @Override
    public String toString() {
        return
                "useName=" + accountName +
                ", password=" + password +
                ", fullName=" + fullName +
                ", age=" + age +
                ", address=" + address +
                ", phone=" + phone +
                ", codeAccount=" + codeAccount +
                ", role=" + role;
    }
}
