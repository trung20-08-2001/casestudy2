package model;

import java.util.ArrayList;
import java.util.List;

public class Customer extends Account {
    private List<ExaminesDay> examineDays;

    public Customer() {
        this.examineDays = new ArrayList<>();
    }

    public Customer(String codeAccount, String accountName, String password, String fullName, int age, String address, String phone, Enum<Role> role) {
        super(codeAccount, accountName, password, fullName, age, address, phone, role);
        this.examineDays = new ArrayList<>();
    }

    public List<ExaminesDay> getExamineDays() {
        return examineDays;
    }

    public void setExamineDays(List<ExaminesDay> examineDays) {
        this.examineDays = examineDays;
    }

    @Override
    public String toString() {
        String examineList="";
        for(ExaminesDay examinesDay: examineDays){
            examineList+=examinesDay.toString();
        }
        return super.toString()+ ((examineList.length()>0)?", examinesDays= " + examineList:"");
    }

}
