package model;

import java.io.Serializable;
import java.time.LocalDate;
public class ExaminesDay implements Serializable {
    private LocalDate examinesDay;
    private String medicineList;    //danh sách thuốc
    private String examineList;      //danh sách đã khám
    private double paymentAmount;


    public ExaminesDay() {
        this.medicineList = "";
        this.examineList = "";
        this.paymentAmount=0;
        this.examinesDay=LocalDate.now();
    }

    public ExaminesDay(String medicineList) {
        this.medicineList = medicineList;
        this.examineList = "";
        this.paymentAmount=0;
        this.examinesDay=LocalDate.now();
    }

    public String getMedicineList() {
        return medicineList;
    }

    public LocalDate getExaminesDay() {
        return examinesDay;
    }

    public void setExaminesDay(LocalDate examinesDay) {
        this.examinesDay = examinesDay;
    }

    public void setMedicineList(String medicineList) {
        this.medicineList += medicineList;
    }

    public String getExamineList() {
        return examineList;
    }

    public void setExamineList(String examineList) {
        this.examineList += examineList;
    }

    public double getPaymentAmount() {
        return paymentAmount;
    }

    public void setPaymentAmount(double paymentAmount) {
        this.paymentAmount = paymentAmount;
    }

    public String toString(){
        return examinesDay+","+examineList+","+medicineList+","+paymentAmount;
    }
}
