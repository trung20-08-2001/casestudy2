package model;

import java.util.ArrayList;
import java.util.List;

public class Doctor extends Account {

    private List<String> customerList;
    public Doctor() {
        this.customerList=new ArrayList<>();
    }

    public Doctor(String codeAccount, String accountName, String password, String fullName, int age, String address, String phone, Enum<Role> role) {
        super(codeAccount, accountName, password, fullName, age, address, phone, role);
        this.customerList=new ArrayList<>();
    }

    public List<String> getCustomerList() {
        return customerList;
    }

    public void setCustomerList(List<String> customerList) {
        this.customerList = customerList;
    }


    @Override
    public String toString() {
        return super.toString()+
                ", customerList=" + customerList.toString() +
                '}';
    }
}
