package manager;

import model.Doctor;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ManagerDoctor {
    File dataFileDoctor = new File("dataDoctor");

    public List<Doctor> doctorList = new ArrayList<>();

    public void readFileDoctorList() {
        if (dataFileDoctor.length() > 0) {
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(dataFileDoctor))) {
                doctorList = (List<Doctor>) ois.readObject();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void writeFileDoctorList() {
        readFileDoctorList();
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(dataFileDoctor))) {
            oos.writeObject(doctorList);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void addDoctor(Doctor doctor) {
        readFileDoctorList();
        doctorList.add(doctor);
        writeFileDoctorList();
    }

    public List<String> displayAllDoctor() {
        readFileDoctorList();
        List<String> listDoctorName = new ArrayList<>();
        if (doctorList.size() > 0) {
            for (int i = 0; i < doctorList.size(); i++) {
                listDoctorName.add(doctorList.get(i).getFullName());
            }
        }
        return listDoctorName;
    }

    public void addCustomer(String codeDoctor, String nameCustomer) {
        Doctor doctor = findDoctorByCodeAccount(codeDoctor);
        List<String> customerListOfDoctor = doctor.getCustomerList();
        customerListOfDoctor.add(nameCustomer);
        System.out.println(doctorList.toString());
        writeFileDoctorList();
    }

    public Doctor findDoctorByCodeAccount(String codeDoctor) {
        readFileDoctorList();
        for (Doctor doctor : doctorList) {
            if (doctor.getCodeAccount().equals(codeDoctor)) {
                return doctor;
            }
        }
        return null;
    }

    public Doctor findDoctorByAccountName(String accountName) {
        readFileDoctorList();
        for (Doctor doctor : doctorList) {
            if (doctor.getAccountName().equals(accountName)) {
                return doctor;
            }
        }
        return null;
    }

    public String writeCustomerList(String accountName) {
        readFileDoctorList();
        Doctor doctor = findDoctorByAccountName(accountName);
        List<String> customerList = doctor.getCustomerList();
        String content = "";
        for (int i = 0; i < customerList.size(); i++) {
            content += customerList.get(i);
        }
        return content;
    }

}
