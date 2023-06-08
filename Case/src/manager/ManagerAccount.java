package manager;

import model.*;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static model.Role.*;

public class ManagerAccount {
    Scanner scanner = new Scanner(System.in);
    public List<Account> accounts = new ArrayList<>();
    File file = new File("dataAccount");
    File fileCodeAccount = new File("codeAccount.txt");
    ManagerCustomer managerCustomer = new ManagerCustomer();
    ManagerDoctor managerDoctor = new ManagerDoctor();


    public boolean register(String codeAccount, String accountName, String password, String fullName, String address, int age, String phone, Enum<Role> role) {
        readFileListAccount();
        Account newAccount = null;
        if (isAnExistingAccount(accountName)) {
            return false;
        } else if (isAnExistingCodeAccount(codeAccount)) {
            if (role == ADMIN) {
                newAccount = new Account(codeAccount, accountName, password, fullName, age, address, phone, ADMIN);
            } else if (role == DOCTOR) {
                newAccount = new Doctor(codeAccount, accountName, password, fullName, age, address, phone, DOCTOR);
                managerDoctor.addDoctor((Doctor) newAccount);
            } else if (role == NURSE) {
                newAccount = new Nurse(codeAccount, accountName, password, fullName, age, address, phone, NURSE);
            } else if (role == CUSTOMER) {
                newAccount = new Customer(codeAccount, accountName, password, fullName, age, address, phone, CUSTOMER);
                managerCustomer.readFileCustomerList();
                managerCustomer.customerList.add((Customer) newAccount);
                managerCustomer.writeFileCustomerList();
            }
            accounts.add(newAccount);
            writeFileListAccount(accounts);
            return true;
        } else {
            return false;
        }
    }


    public void readFileListAccount() {
        if (file.length() > 0) {
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
                accounts = (List<Account>) ois.readObject();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void writeFileListAccount(List<Account> accounts) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file))) {
            oos.writeObject(accounts);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Account login(String accountName, String password) {
        readFileListAccount();
        System.out.println(accounts);
        for (Account account : accounts) {
            if (account.getPassword().equals(password) && account.getAccountName().equals(accountName)) {
                return account;
            }
        }
        return null;
    }

    public String createdCodeAccount(Enum<Role> role) {
        List<String> codeAccountList = readFileCodeAccount();
        int number = codeAccountList.size() + 1;

        if (role == DOCTOR) {
            String codeAccountDoctor = "DOCTOR" + number;
            writeFileCodeAccount(codeAccountDoctor);
            return codeAccountDoctor;

        } else if (role == NURSE) {
            String codeAccountNurse = "NURSE" + number;
            writeFileCodeAccount(codeAccountNurse);
            return codeAccountNurse;

        } else {
            String codeAccountCustomer = "CUSTOMER" + number;
            writeFileCodeAccount(codeAccountCustomer);
            return codeAccountCustomer;
        }
    }


    public boolean isAnExistingAccount(String accountName) {
        for (Account account : accounts) {
            if (account.getAccountName().equals(accountName)) {
                return true;
            }
        }
        return false;
    }

    public boolean isAnExistingCodeAccount(String codeAccount) {
        List<String> codeAccountList = readFileCodeAccount();
        for (String codeAcc : codeAccountList) {
            if (codeAcc.equals(codeAccount)) {
                return true;
            }
        }
        return false;
    }

    public void writeFileCodeAccount(String codeAccount) {
        List<String> codeAccountList = readFileCodeAccount();
        codeAccountList.add(codeAccount);
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(fileCodeAccount))) {
            for (int i = 0; i < codeAccountList.size(); i++) {
                bw.write(codeAccountList.get(i) + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<String> readFileCodeAccount() {
        List<String> codeAccountList = new ArrayList<>();
        if (fileCodeAccount.length() > 0) {
            try (BufferedReader br = new BufferedReader(new FileReader(fileCodeAccount))) {
                String line;
                while ((line = br.readLine()) != null) {
                    codeAccountList.add(line);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return codeAccountList;
    }

    public Customer findCustomer(String codeAccount) {
        readFileListAccount();
        for (Account account : accounts) {
            if (account.getCodeAccount().equals(codeAccount) && account instanceof Customer) {
                return (Customer) account;
            }
        }
        return null;
    }

    public int findAccount(String codeAccount) {
        readFileListAccount();
        for (int i = 0; i < accounts.size(); i++) {
            if (accounts.get(i).getCodeAccount().equals(codeAccount)) {
                return i;
            }
        }
        return -1;
    }

    public void deleteAccount(String codeAccount) {
        int index=findAccount(codeAccount);
        readFileListAccount();
        accounts.remove(index);
        writeFileListAccount(accounts);
    }

    public static void main(String[] args) {
        ManagerAccount managerAccount = new ManagerAccount();
        managerAccount.writeFileCodeAccount("ADMIN");
//        managerAccount.writeFileCodeAccount("DOCTOR2");
//        managerAccount.writeFileCodeAccount("DOCTOR3");
//        managerAccount.writeFileCodeAccount("DOCTOR4");
//        managerAccount.writeFileCodeAccount("NURSE5");
//        managerAccount.writeFileCodeAccount("NURSE6");
//        managerAccount.writeFileCodeAccount("NURSE7");
//        managerAccount.writeFileCodeAccount("CUSTOMER8");
//        managerAccount.writeFileCodeAccount("CUSTOMER9");
//        managerAccount.writeFileCodeAccount("CUSTOMER10");
        managerAccount.register("ADMIN", "trungadmin@gmail.com", "Trung!@12", "Nguyễn Văn Trung", "Việt Nam", 23, "0375073496", ADMIN);
//        managerAccount.register("DOCTOR2","doctor2@gmail.com","Trung!@12","Vũ Ngọc Tuấn","Hà Nội",20,"0345123459",DOCTOR);
//        managerAccount.register("DOCTOR3","doctor3@gmail.com","Trung!@12","Nguyễn Thị Hương","Hà Nội",23,"0345789412",DOCTOR);
//        managerAccount.register("DOCTOR4","doctor4@gmail.com","Trung!@12","Lê Văn Dương","Hà Nội",30,"0125745896",DOCTOR);
//        managerAccount.register("NURSE5","nurse5@gmail.com","Trung!@12","Nguyễn Thị Hồng","Hà Nội",20,"0375076123",NURSE);
//        managerAccount.register("NURSE6","nurse6@gmail.com","Trung!@12","Hà Phương Anh","Hà Nội",25,"0125478963",NURSE);
//        managerAccount.register("NURSE7","nurse7@gmail.com","Trung!@12","Đặng Hương","Hà Nội",23,"0125478963",NURSE);
//        managerAccount.register("CUSTOMER8","customer8@gmail.com","Trung!@12","Đào Ngọc Long","Hà Nội",25,"0456789412",CUSTOMER);
//        managerAccount.register("CUSTOMER9","customer9@gmail.com","Trung!@12","Nguyễn Văn Trung","Hà Nội",23,"0126459756",CUSTOMER);
//        managerAccount.register("CUSTOMER10","customer10@gmail.com","Trung!@12","Phạm Thị Hà","Hà Nội",15,"0126459756",CUSTOMER);

    }
}
