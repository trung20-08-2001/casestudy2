package model;

public class Nurse extends Account {
    private String codeDoctorToday;

    public Nurse() {
    }

    public Nurse(String codeDoctorToday) {
        this.codeDoctorToday = codeDoctorToday;
    }

    public Nurse(String codeAccount, String accountName, String password, String fullName, int age, String address, String phone, Enum<Role> role) {
        super(codeAccount, accountName, password, fullName, age, address, phone, role);
        this.codeDoctorToday ="";
    }



    public String getCodeDoctorToday() {
        return codeDoctorToday;
    }

    public void setCodeDoctorToday(String codeDoctorToday) {
        this.codeDoctorToday = codeDoctorToday;
    }
}
