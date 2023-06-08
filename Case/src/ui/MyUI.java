package ui;

import manager.*;
import model.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.util.List;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;


import static model.Role.*;

public class MyUI extends JFrame {
    Customer customer;
    Doctor doctor;
    Nurse nurse;
    ManagerAccount managerAccount = new ManagerAccount();
    ManagerCustomer managerCustomer = new ManagerCustomer();
    ManagerDoctor managerDoctor = new ManagerDoctor();
    ManagerNurse managerNurse = new ManagerNurse();

    Validate validate = new Validate();

    private JPanel caseStudy;
    private JPanel pageLoginOrRegister;
    private JPanel JPanel;
    private JPanel pageCustomer;
    private JPasswordField inputPassword;
    private JTextField inputAccount;
    private JButton buttonRegister;
    private JButton buttonLogin;
    private JLabel notePassword1;
    private JLabel notePassword2;
    private JLabel noteAccount;
    private JPanel pageAdmin;
    private JPanel pageRegister;
    private JTextField inputNewAccount;
    private JPasswordField inputNewPassword;
    private JLabel newAccountName;
    private JLabel newPassword;
    private JButton register;
    private JLabel noteNewAccount;
    private JLabel noteNewPassword1;
    private JLabel noteNewPassword2;
    private JButton exit;
    private JButton clear;
    private JTextField inputFullName;
    private JTextField inputAddress;
    private JTextField inputAge;
    private JLabel fullName;
    private JLabel address;
    private JLabel age;
    private JLabel noteFullName;
    private JLabel noteAddress;
    private JLabel noteAge;
    private JTextField inputPhone;
    private JLabel phone;
    private JLabel notePhone;
    private JTextField inputCodeEmployee;
    private JLabel codeEmployee;
    private JLabel noteCodeEmployee;
    private JPanel menuAdmin;
    private JButton createdCodeEmployee;
    private JButton showListCustomer;
    private JButton showListEmployee;
    private JButton deleteEmployee;
    private JButton createdCodeNurse;
    private JButton createdCodeDoctor;
    private JTextField displayCodeEmployee;
    private JPanel pageCreatedEmployee;
    private JLabel accountText;
    private JButton displayInformation;
    private JLabel passwordText;
    private JPanel pageDoctor;
    private JPanel menuDoctor;
    private JButton addMedicine;
    private JButton showCalendarDoctor;
    private JButton logoutDoctor;
    private JTextField inputCodeCustomer;
    private JLabel findCodeCustomerText;
    private JLabel noteInputCodeCustomer;
    private JButton clearCodeCustomer;
    private JButton findCodeCustomer;
    private JPanel pageFindCustomer;
    private JTextArea medicineList;
    private JLabel medicineListText;
    private JPanel pageWriteMedicineList;
    private JButton buttonConfirmMedicine;
    private JLabel re_examinationDate;
    private JTextField inputRe_examinationDate;
    private JPanel pageInformationCustomer;
    private JPanel menuCustomer;
    private JPanel pageCustomerHistory;
    private JButton bookDoctor;
    private JButton logoutCustomer;
    private JLabel emptyHistory;
    private JPanel pageDisplaytable;
    private JPanel pageInformationDoctor;
    private JPanel pageNurse;
    private JButton resultExamine;
    private JButton logoutNurse;
    private JPanel menuNurse;
    private JTextField inputCodeCustomerPageNurse;
    private JTextArea writeResultExamnice;
    private JButton finish;
    private JLabel codeCustomerPageNurse;
    private JPanel pageWorkOfNurse;
    private JLabel noteCodeCustomerOfNurse;
    private JButton remove;
    private JTextField inputPaymentAmount;
    private JLabel payment;
    private JPanel pageListCustomer;
    private JButton logoutAdmin;
    private JPanel pageListEmployee;
    private JPanel pageDeleteEmployee;
    private JTextField inputCodeEmployeeDelete;
    private JButton delete;
    private JLabel note;
    private JPanel pageBookDoctor;
    private JTextField inputCodeDoctor;
    private JButton buttonBookDoctor;
    private JButton xoaButton;
    private JPanel pageCalendar;
    private JTextArea calendarDoctor;
    private JButton button;

    public MyUI() {
        super("Case");
        this.setSize(1600, 700);
        this.setLocationRelativeTo(null);
        this.setContentPane(pageLoginOrRegister);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        buttonLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String accountName = inputAccount.getText();
                String password = inputPassword.getText();
                boolean checkData = true;
                if (!validate.validateAccount(accountName)) {
                    noteAccount.setVisible(true);
                    checkData = false;
                }
                if (!validate.validatePassword(password)) {
                    notePassword1.setVisible(true);
                    notePassword2.setVisible(true);
                    checkData = false;
                }
                if (checkData) {
                    Account account = managerAccount.login(accountName, password);
                    if (account != null) {
                        if (account.getRole() == ADMIN) {
                            doctor = managerDoctor.findDoctorByAccountName(accountName);
                            pageLoginOrRegister.setVisible(false);
                            setContentPane(pageAdmin);
                        } else if (account.getRole() == DOCTOR) {
                            pageLoginOrRegister.setVisible(false);
                            doctor = (Doctor) account;
                            setContentPane(pageDoctor);
                        } else if (account.getRole() == NURSE) {
                            nurse = managerNurse.findNurseByAccountName(accountName);
                            pageLoginOrRegister.setVisible(false);
                            setContentPane(pageNurse);
                        } else if (account.getRole() == CUSTOMER) {
                            pageLoginOrRegister.setVisible(false);
                            customer = managerCustomer.findCustomerByNameAccount(accountName);
                            setContentPane(pageCustomer);
                        }
                        inputAccount.setText("");
                        inputPassword.setText("");
                        noteAccount.setVisible(false);
                        notePassword1.setVisible(false);
                        notePassword2.setVisible(false);
                    } else {
                        JOptionPane.showMessageDialog(null, "Tài khoản không tồn tại.", "Thông báo.", JOptionPane.INFORMATION_MESSAGE);
                    }
                }
            }
        });
        register.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String codeEmployee = inputCodeEmployee.getText();
                String accountName = inputNewAccount.getText();
                String password = inputNewPassword.getText();
                String fullName = inputFullName.getText();
                String ageStr = inputAge.getText();
                int age;
                if (!ageStr.isEmpty()) {
                    age = Integer.parseInt(inputAge.getText());
                } else {
                    age = -1;
                }
                String address = inputAddress.getText();
                String phone = inputPhone.getText();
                Enum<Role> role = null;
                boolean check = true;
                if (codeEmployee.isEmpty()) {
                    codeEmployee = managerAccount.createdCodeAccount(CUSTOMER);
                }
                if (managerAccount.isAnExistingCodeAccount(codeEmployee)) {
                    if ((codeEmployee.substring(0, 6)).equals("DOCTOR")) {
                        role = DOCTOR;
                    } else if ((codeEmployee.substring(0, 5)).equals("NURSE")) {
                        role = NURSE;
                    } else if ((codeEmployee.substring(0, 8)).equals("CUSTOMER")) {
                        role = CUSTOMER;
                    }
                } else {
                    check = false;
                    noteCodeEmployee.setVisible(true);
                }
                if (!validate.validateAccount(accountName)) {
                    noteNewAccount.setVisible(true);
                    check = false;
                }
                if (!validate.validatePassword(password)) {
                    noteNewPassword1.setVisible(true);
                    noteNewPassword2.setVisible(true);
                    check = false;
                }
                if (!validate.validateFullName(fullName)) {
                    noteFullName.setVisible(true);
                    check = false;
                }
                if (!validate.validateAddress(address)) {
                    noteAddress.setVisible(true);
                    check = false;
                }
                if (!validate.validateAge(String.valueOf(age))) {
                    noteAge.setVisible(true);
                    check = false;
                }
                if (!validate.validatePhone(phone)) {
                    notePhone.setVisible(true);
                    check = false;
                }
                if (check) {
                    if (managerAccount.register(codeEmployee, accountName, password, fullName, address, age, phone, role)) {
                        JOptionPane.showMessageDialog(null, "Đăng kí thành công.", "Thông báo.", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(null, "Tài khoản đã tồn tại chọn tên tài khoản khác.", "Thông báo.", JOptionPane.INFORMATION_MESSAGE);
                    }
                    noteNewAccount.setVisible(false);
                    noteNewPassword1.setVisible(false);
                    noteNewPassword2.setVisible(false);
                    noteFullName.setVisible(false);
                    noteAge.setVisible(false);
                    noteAddress.setVisible(false);
                    notePhone.setVisible(false);
                    noteCodeEmployee.setVisible(false);
                }
            }
        });
        exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pageRegister.setVisible(false);
                pageLoginOrRegister.setVisible(true);
                noteNewAccount.setVisible(false);
                noteNewPassword1.setVisible(false);
                noteNewPassword2.setVisible(false);
                notePhone.setVisible(false);
                noteAge.setVisible(false);
                noteFullName.setVisible(false);
                noteAddress.setVisible(false);
                noteCodeEmployee.setVisible(false);
                inputNewAccount.setText("");
                inputNewPassword.setText("");
                inputFullName.setText("");
                inputAge.setText("");
                inputAddress.setText("");
                inputPhone.setText("");
                inputCodeEmployee.setText("");
                setContentPane(pageLoginOrRegister);
            }
        });
        buttonRegister.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Object[] options = {"Nhân viên bệnh viện", "Khách hàng", "Thoát"};
                int choice = JOptionPane.showOptionDialog(null, "Bạn là:", "Xin mời chọn", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[2]);
                if (choice == 0 || choice == 1) {
                    pageLoginOrRegister.setVisible(false);
                    pageRegister.setVisible(true);
                    setContentPane(pageRegister);
                    if (choice == 0) {
                        codeEmployee.setVisible(true);
                        inputCodeEmployee.setVisible(true);
                    } else {
                        codeEmployee.setVisible(false);
                        inputCodeEmployee.setVisible(false);
                    }
                }
            }
        });
        clear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                inputNewAccount.setText("");
                inputNewPassword.setText("");
                inputAddress.setText("");
                inputAge.setText("");
                inputFullName.setText("");
                inputCodeEmployee.setText("");
                inputPhone.setText("");
                noteNewAccount.setVisible(false);
                noteNewPassword1.setVisible(false);
                noteNewPassword2.setVisible(false);
                noteFullName.setVisible(false);
                noteAge.setVisible(false);
                noteAddress.setVisible(false);
                notePhone.setVisible(false);
                noteCodeEmployee.setVisible(false);
            }
        });
        menuAdmin.addComponentListener(new ComponentAdapter() {
        });
        createdCodeEmployee.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pageListCustomer.setVisible(false);
                pageListEmployee.setVisible(false);
                pageDeleteEmployee.setVisible(false);
                if (pageCreatedEmployee.isVisible()) {
                    pageCreatedEmployee.setVisible(false);
                } else {
                    pageCreatedEmployee.setVisible(true);
                }
            }
        });
        createdCodeNurse.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String codeNurse = managerAccount.createdCodeAccount(NURSE);
                displayCodeEmployee.setText(codeNurse);
            }
        });
        createdCodeDoctor.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String codeDoctor = managerAccount.createdCodeAccount(DOCTOR);
                displayCodeEmployee.setText(codeDoctor);
            }
        });
        displayInformation.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pageInformationDoctor.setVisible(false);
                if (pageCustomerHistory.isVisible()) {
                    pageCustomerHistory.setVisible(false);
                    emptyHistory.setVisible(false);
                } else {
                    pageCustomerHistory.setVisible(true);
                    List<ExaminesDay> examinesDayList = customer.getExamineDays();
                    if (examinesDayList.size() == 0) {
                        emptyHistory.setVisible(true);
                    } else {
                        emptyHistory.setVisible(false);
                        String[] columnNames = {"Ngày", "Xét nghiệm những gì", "Danh sách đơn thuốc", "Số tiền thanh toán"};
                        DefaultTableModel model = new DefaultTableModel();
                        for (int i = 0; i < columnNames.length; i++) {
                            model.addColumn(columnNames[i]);
                        }
                        for (ExaminesDay examinesDay : examinesDayList) {
                            String[] data = examinesDay.toString().split(",");
                            model.addRow(data);
                        }
                        JTable table = new JTable(model);
                        table.setFont(new Font("Arial", Font.PLAIN, 20));
                        table.getColumnModel().getColumn(2).setCellRenderer(new MultiLineTableCellRender());
                        JScrollPane scrollPane = new JScrollPane(table);
                        JTableHeader header = table.getTableHeader();
                        header.setFont(new Font("Arial", Font.PLAIN, 20));
                        table.setRowHeight(50);
                        table.setPreferredScrollableViewportSize(new Dimension(700, 200));
                        table.setRowHeight(table.getPreferredSize().width, table.getPreferredSize().height);
                        DefaultTableCellRenderer defaultTableCellRenderer = new DefaultTableCellRenderer();
                        defaultTableCellRenderer.setHorizontalAlignment(JLabel.CENTER);
                        table.getColumnModel().getColumn(0).setCellRenderer(defaultTableCellRenderer);
                        table.getColumnModel().getColumn(1).setCellRenderer(defaultTableCellRenderer);
                        table.getColumnModel().getColumn(2).setCellRenderer(defaultTableCellRenderer);
                        table.getColumnModel().getColumn(3).setCellRenderer(defaultTableCellRenderer);
                        pageDisplaytable.add(scrollPane);
                        pageCustomerHistory.revalidate();
                    }
                }
            }
        });
        addMedicine.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pageCalendar.setVisible(false);
                pageWriteMedicineList.setVisible(false);
                pageFindCustomer.setVisible(true);
            }
        });
        clearCodeCustomer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                inputCodeCustomer.setText("");
                noteInputCodeCustomer.setVisible(false);
                pageWriteMedicineList.setVisible(false);
            }
        });
        findCodeCustomer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String codeCustomer = inputCodeCustomer.getText();
                if (codeCustomer.isEmpty()) {
                    noteInputCodeCustomer.setVisible(true);
                } else {
                    Customer currentCustomer = managerCustomer.findCustomerByCodeAccount(codeCustomer);
                    System.out.println(currentCustomer);
                    if (currentCustomer != null) {
                        pageWriteMedicineList.setVisible(true);
                        noteInputCodeCustomer.setVisible(false);
                        String[] columnsNamesTableInformationCustomer = {"Họ tên", "Tuổi", "Địa chỉ"};
                        Object[][] information = {{currentCustomer.getFullName(), Integer.toString(currentCustomer.getAge()), currentCustomer.getAddress()}};
                        DefaultTableModel model = new DefaultTableModel(information, columnsNamesTableInformationCustomer);
                        JTable table = new JTable(model);
                        table.setFont(new Font("Arial", Font.PLAIN, 20));
                        JTableHeader header = table.getTableHeader();
                        header.setFont(new Font("Arial", Font.PLAIN, 20));
                        table.setPreferredScrollableViewportSize(new Dimension(table.getPreferredSize().width, table.getPreferredSize().height));
                        table.setRowHeight(table.getPreferredSize().width, table.getPreferredSize().height);
                        DefaultTableCellRenderer defaultTableCellRenderer = new DefaultTableCellRenderer();
                        defaultTableCellRenderer.setHorizontalAlignment(JLabel.CENTER);
                        table.getColumnModel().getColumn(0).setCellRenderer(defaultTableCellRenderer);
                        table.getColumnModel().getColumn(1).setCellRenderer(defaultTableCellRenderer);
                        table.getColumnModel().getColumn(2).setCellRenderer(defaultTableCellRenderer);
//                        model.fireTableDataChanged();
                        pageInformationCustomer.add(new JScrollPane(table));
                        pageInformationCustomer.revalidate();
                        pageWriteMedicineList.setVisible(true);
                    } else {
                        noteInputCodeCustomer.setVisible(true);
                        pageWriteMedicineList.setVisible(false);
                    }
                }
            }
        });
        buttonConfirmMedicine.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Customer currentCustomer = managerCustomer.findCustomerByCodeAccount(inputCodeCustomer.getText());
                System.out.println(currentCustomer);
                if (currentCustomer != null) {
                    List<ExaminesDay> examinesDayList = currentCustomer.getExamineDays();
                    ExaminesDay examinesDay = managerCustomer.findExaminesToDay(examinesDayList);
                    if (examinesDay == null) {
                        managerCustomer.addExamineDate(inputCodeCustomer.getText(), medicineList.getText());
                    }
                }
                Object[] options = {"Thoát"};
                int choice = JOptionPane.showOptionDialog(null, "Đơn thuốc được tạo thành công", "Thông báo", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);
                if (choice == 0) {
                    pageWriteMedicineList.setVisible(false);
                    pageFindCustomer.setVisible(false);
                    inputCodeCustomer.setText("");
                }
            }
        });
        logoutCustomer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                customer = null;
                pageCustomer.setVisible(false);
                pageLoginOrRegister.setVisible(true);
                setContentPane(pageLoginOrRegister);
            }
        });
        bookDoctor.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pageInformationDoctor.setVisible(true);
                pageBookDoctor.setVisible(true);
                pageCustomerHistory.setVisible(false);
                DefaultTableModel model = new DefaultTableModel();
                model.addColumn("Tên bác sĩ");
                model.addColumn("Mã");
                managerDoctor.readFileDoctorList();
                for (Doctor doctorCurrent : managerDoctor.doctorList) {
                    String[] data = {doctorCurrent.getFullName(), doctorCurrent.getCodeAccount()};
                    model.addRow(data);
                }
                JTable table=new JTable(model);
                table.setFont(new Font("Arial", Font.PLAIN, 20));
                JScrollPane scrollPane = new JScrollPane(table);
                JTableHeader header = table.getTableHeader();
                header.setFont(new Font("Arial", Font.PLAIN, 20));
                table.setRowHeight(30);
                table.setPreferredScrollableViewportSize(new Dimension(700, 200));
                DefaultTableCellRenderer defaultTableCellRenderer = new DefaultTableCellRenderer();
                defaultTableCellRenderer.setHorizontalAlignment(JLabel.CENTER);
                table.getColumnModel().getColumn(0).setCellRenderer(defaultTableCellRenderer);
                table.getColumnModel().getColumn(1).setCellRenderer(defaultTableCellRenderer);
                pageInformationDoctor.add(scrollPane);
                pageInformationDoctor.revalidate();
            }
        });
        resultExamine.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (pageWorkOfNurse.isVisible()) {
                    pageWorkOfNurse.setVisible(false);
                } else {
                    pageWorkOfNurse.setVisible(true);
                }
            }
        });
        logoutNurse.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                nurse = null;
                pageNurse.setVisible(false);
                pageLoginOrRegister.setVisible(true);
                setContentPane(pageLoginOrRegister);
            }
        });
        finish.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String codeCustomer = inputCodeCustomerPageNurse.getText();
                String resultExamine = writeResultExamnice.getText();
                Customer currentCustomer = managerCustomer.findCustomerByCodeAccount(codeCustomer);
                if (currentCustomer == null) {
                    noteCodeCustomerOfNurse.setVisible(true);
                } else {
                    managerCustomer.addMedicineListAndResultExamine(inputCodeCustomerPageNurse.getText(), resultExamine, inputPaymentAmount.getText());
                    int choice = JOptionPane.showConfirmDialog(null, "Hoàn thành");
                    if (choice == JOptionPane.YES_OPTION) {
                        inputCodeCustomerPageNurse.setText("");
                        writeResultExamnice.setText("");
                        inputPhone.setText("");
                        noteCodeCustomerOfNurse.setVisible(false);
                    }
                }
            }
        });
        remove.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                noteCodeCustomerOfNurse.setVisible(false);
                inputCodeCustomerPageNurse.setText("");
                writeResultExamnice.setText("");
                inputPaymentAmount.setText("");
            }
        });
        logoutDoctor.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                doctor = null;
                pageDoctor.setVisible(false);
                pageLoginOrRegister.setVisible(true);
                setContentPane(pageLoginOrRegister);
            }
        });
        showListCustomer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pageCreatedEmployee.setVisible(false);
                pageListCustomer.setVisible(true);
                pageListEmployee.setVisible(false);
                pageDeleteEmployee.setVisible(false);
                managerCustomer.readFileCustomerList();
                DefaultTableModel model = new DefaultTableModel();
                model.addColumn("Họ tên");
                model.addColumn("Tuổi");
                model.addColumn("Địa chỉ");
                for (Customer customerShow : managerCustomer.customerList) {
                    String[] data = {customerShow.getFullName(), String.valueOf(customerShow.getAge()), customerShow.getAddress()};
                    model.addRow(data);
                }
                JTable table = new JTable(model);
                table.setFont(new Font("Arial", Font.PLAIN, 20));
                table.getColumnModel().getColumn(2).setCellRenderer(new MultiLineTableCellRender());
                JScrollPane scrollPane = new JScrollPane(table);
                JTableHeader header = table.getTableHeader();
                header.setFont(new Font("Arial", Font.PLAIN, 20));
                table.setRowHeight(50);
                table.setPreferredScrollableViewportSize(new Dimension(700, 200));
                DefaultTableCellRenderer defaultTableCellRenderer = new DefaultTableCellRenderer();
                defaultTableCellRenderer.setHorizontalAlignment(JLabel.CENTER);
                table.getColumnModel().getColumn(0).setCellRenderer(defaultTableCellRenderer);
                table.getColumnModel().getColumn(1).setCellRenderer(defaultTableCellRenderer);
                table.getColumnModel().getColumn(2).setCellRenderer(defaultTableCellRenderer);
                pageListCustomer.add(scrollPane);
                pageListCustomer.revalidate();
            }
        });
        logoutAdmin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pageAdmin.setVisible(false);
                pageLoginOrRegister.setVisible(true);
                setContentPane(pageLoginOrRegister);
            }
        });


        showListEmployee.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pageListCustomer.setVisible(false);
                pageCreatedEmployee.setVisible(false);
                pageListEmployee.setVisible(true);
                pageDeleteEmployee.setVisible(false);
                managerAccount.readFileListAccount();
                DefaultTableModel model = new DefaultTableModel();
                model.addColumn("Họ tên");
                model.addColumn("Tuổi");
                model.addColumn("Địa chỉ");
                model.addColumn("Chức vụ");
                model.addColumn("Mã nhân viên");
                model.addColumn("SĐT");
                for (Account account : managerAccount.accounts) {
                    if (account instanceof Doctor || account instanceof Nurse) {
                        String[] data = {account.getFullName(), String.valueOf(account.getAge()), account.getAddress(), String.valueOf(account.getRole()), account.getCodeAccount(), account.getPhone()};
                        model.addRow(data);
                    }
                }
                JTable table = new JTable(model);
                table.setFont(new Font("Arial", Font.PLAIN, 20));
                table.getColumnModel().getColumn(2).setCellRenderer(new MultiLineTableCellRender());
                JScrollPane scrollPane = new JScrollPane(table);
                JTableHeader header = table.getTableHeader();
                header.setFont(new Font("Arial", Font.PLAIN, 20));
                table.setRowHeight(50);
                table.setPreferredScrollableViewportSize(new Dimension(700, 200));
                DefaultTableCellRenderer defaultTableCellRenderer = new DefaultTableCellRenderer();
                defaultTableCellRenderer.setHorizontalAlignment(JLabel.CENTER);
                table.getColumnModel().getColumn(0).setCellRenderer(defaultTableCellRenderer);
                table.getColumnModel().getColumn(1).setCellRenderer(defaultTableCellRenderer);
                table.getColumnModel().getColumn(2).setCellRenderer(defaultTableCellRenderer);
                table.getColumnModel().getColumn(3).setCellRenderer(defaultTableCellRenderer);
                table.getColumnModel().getColumn(4).setCellRenderer(defaultTableCellRenderer);
                pageListEmployee.add(scrollPane);
                pageListEmployee.revalidate();
            }
        });
        delete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String codeEmployee = inputCodeEmployeeDelete.getText();
                int index = managerAccount.findAccount(codeEmployee);
                if (index == -1 || codeEmployee.isEmpty()) {
                    note.setVisible(true);
                } else {
                    note.setVisible(false);
                    managerAccount.deleteAccount(codeEmployee);
                    JOptionPane.showMessageDialog(null, "Xóa thành công.");
                }
            }
        });
        deleteEmployee.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pageDeleteEmployee.setVisible(true);
                pageCreatedEmployee.setVisible(false);
                pageListEmployee.setVisible(false);
                pageListCustomer.setVisible(false);
                note.setVisible(false);
            }
        });
        buttonBookDoctor.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String codeDoctor=inputCodeDoctor.getText();
                Doctor doctorCurrent=managerDoctor.findDoctorByCodeAccount(codeDoctor);
                if(doctorCurrent==null){
                    JOptionPane.showMessageDialog(null,"Mã không chính xác.");
                }else{
                    managerDoctor.addCustomer(doctorCurrent.getCodeAccount(),customer.getFullName());
                    JOptionPane.showMessageDialog(null,"Đặt lịch thành công.");
                }
            }
        });
        xoaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                inputCodeDoctor.setText("");
            }
        });
        showCalendarDoctor.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pageFindCustomer.setVisible(false);
                pageWriteMedicineList.setVisible(false);
                pageCalendar.setVisible(true);
                String listCustomer=managerDoctor.writeCustomerList(doctor.getAccountName());
                calendarDoctor.setText(listCustomer);
            }
        });
    }


    private void createUIComponents() {
        // TODO: place custom component creation code here
    }
}