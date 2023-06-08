package manager;

import model.Nurse;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ManagerNurse {
    List<Nurse> nurseList = new ArrayList<>();
    File dataFileNurse = new File("dataNurse");

    public void readFileNurseList() {
        if (dataFileNurse.length() > 0) {
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(dataFileNurse))) {
                nurseList = (List<Nurse>) ois.readObject();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void writeFileNurseList() {
        readFileNurseList();
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(dataFileNurse))) {
            oos.writeObject(nurseList);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Nurse findNurseByAccountName(String accountName){
        readFileNurseList();
        for(Nurse nurse:nurseList){
            if(nurse.getAccountName().equals(accountName)){
                return nurse;
            }
        }
        return null;
    }
}
