package manager;

import model.Customer;
import model.ExaminesDay;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ManagerCustomer {
    File dataFileCustomer = new File("dataCustomer");

    public List<Customer> customerList = new ArrayList<>();

    public void readFileCustomerList() {
        if (dataFileCustomer.length() > 0) {
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(dataFileCustomer))) {
                customerList = (List<Customer>) ois.readObject();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void writeFileCustomerList() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(dataFileCustomer))) {
            oos.writeObject(customerList);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public Customer findCustomerByCodeAccount(String codeAccount) {
        readFileCustomerList();
        for (Customer customer : customerList) {
            if (customer.getCodeAccount().equals(codeAccount)) {
                return customer;
            }
        }
        return null;
    }

    public Customer findCustomerByNameAccount(String nameAccount) {
        readFileCustomerList();
        for (Customer customer : customerList) {
            if (customer.getAccountName().equals(nameAccount)) {
                return customer;
            }
        }
        return null;
    }

    public void addMedicineListAndResultExamine(String codeAccount, String resultExamine, String payment) {
        Customer customer = findCustomerByCodeAccount(codeAccount);
        if (customer != null) {
            List<ExaminesDay> examinesDayList = customer.getExamineDays();
            ExaminesDay examinesToday = findExaminesToDay(examinesDayList);
            examinesToday.setExamineList(resultExamine);
            examinesToday.setPaymentAmount(Double.parseDouble(payment));
        }
        writeFileCustomerList();
    }


    public void addExamineDate(String codeAccount, String medicineList) {
        readFileCustomerList();
        for (Customer customer : customerList) {
            if (customer.getCodeAccount().equals(codeAccount)) {
                List<ExaminesDay> examinesDayListOfCustomer = customer.getExamineDays();
                ExaminesDay examinesDay = new ExaminesDay(medicineList);
                examinesDayListOfCustomer.add(examinesDay);
                break;
            }
        }
        writeFileCustomerList();
    }

    public ExaminesDay findExaminesToDay(List<ExaminesDay> examinesDayList) {
        LocalDate today = LocalDate.now();
        for (ExaminesDay examinesDay : examinesDayList) {
            if (examinesDay.getExaminesDay().isEqual(today)) {
                return examinesDay;
            }
        }
        return null;
    }

}