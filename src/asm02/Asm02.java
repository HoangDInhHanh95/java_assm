package asm02;

import asm02.models.Account;
import asm02.models.Bank;
import asm02.models.Customer;

import java.util.*;
import java.util.regex.Pattern;

public class Asm02 {
    private static final Bank bank = new Bank();
    //    private String OUT_ERROR = "Ban nhap khong hop le vui long nhap lai";
    private static String massege = "vui long nhap de tieo tuc: ";
    private static String  OUTPUT_ERROR = "Bạn Nhập không chinh xac vui long nhap lai";
    private static Scanner sc = new Scanner(System.in);

    //  main menu
    public static void showTable() {
        System.out.println("+---------*--------------*-----------+");
        System.out.println("|NGAN HANG SO | vFX02966@v1.0.0      |");
        System.out.println("+---------*--------------*-----------+");
        System.out.println("|1. Them khach hang                  |");
        System.out.println("|2. Them tai khoan cho khach hang    |");
        System.out.println("|3. Hien thi danh sach khach hang    |");
        System.out.println("|4. Tim theo CCCD                    |");
        System.out.println("|5. Tim theo ten khach hang          |");
        System.out.println("|0. Thoat                            |");
        System.out.println("+---------*--------------*-----------+");

    }

    public static void mainMenu(){
        showTable();
        while (true){
         switch (getOptionValues()){
             case 1:
                 addCustomers();
                 showTable();
                 break;
             case 2:
                 addAccounts();
                 showTable();
                 break;
             case 3:
                 showListCustomer();
                 showTable();
                 break;
             case 4:
                 searchByCustomerID();
                 showTable();
                 break;
             case 5:
                 searchByNameCustomer();
                 showTable();
                 break;
             case 0:
                 System.exit(0);
             default:
                 System.out.println(OUTPUT_ERROR);
         }

        }
    }

    //    kiem tra xem có phai number không => ham nay chua duoc can xem lai
    public static boolean isNumberic(String parameter) {
        try {
            Integer.parseInt(parameter);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

//    public static boolean validateAccount(String myAccount) {
//        if (myAccount.length() == 6 && isNumberic(myAccount)) {
//            return true;
//        } else {
//            return false;
//        }
//    }


    private static String validateAccount(String valueAccount) {


            Pattern p = Pattern.compile("^[0-9]{6}$");

            while (true){
                if (p.matcher(valueAccount).find()){
                    break;
                }else {
                    System.out.println(OUTPUT_ERROR);
                    valueAccount = sc.nextLine();
                }
            }

        return valueAccount;
    }

    //    kiem tra lua chon cua khach hang co hop le ko
    private static int getOptionValues() {
        int parameter = 0;
        while (true) {
            try {
//                System.out.println(massege);
                System.out.println("Chuc nang: ");
                parameter = Integer.parseInt(sc.nextLine());
                while (parameter > 6) {
                    System.out.println();
                    parameter = Integer.parseInt(sc.nextLine());
                }
                break;
            } catch (Exception e) {
                System.out.println(OUTPUT_ERROR);
            }
        }
        return parameter;
    }


    //    kiem tra cccd cua khach hang co hop le ko
    private static String checkValuesCCCD(String valuesCCCD) {
        Pattern p = Pattern.compile("^0[0-9]{11}$");
        try {
            while (!p.matcher(valuesCCCD).find()) {
                System.out.println(OUTPUT_ERROR);
                valuesCCCD = sc.nextLine().trim();
            }

        } catch (Exception e) {
            System.out.println(OUTPUT_ERROR);
        }
        return valuesCCCD;
    }

    static void addCustomers() {
        Customer cs = new Customer();

        System.out.println("nhap ten khach hang");
        String name = sc.nextLine();
        cs.setName(name);

        System.out.println("Nhap so CCCD:");
        String id = sc.nextLine();
        while (!cs.checkValdateCCCID(checkValuesCCCD(id))){
            System.out.println(OUTPUT_ERROR);
            id = sc.nextLine();
        }
        cs.setCustomerId(checkValuesCCCD(id));

        bank.addCustomer(cs);
    }

    static void addAccounts() {

        Account acc = new Account();
        Customer cus = new Customer();

        System.out.println("nhap so CCCD de kiem tra");
        String numberCCCD = sc.nextLine();

        // kiem tra CCCD co ton tai ko
        while (!bank.isCustomerExisted(numberCCCD)) {
            System.out.println("so CCCD khong dung hoac khong ton tai. vui long nhap lai");
            numberCCCD = sc.nextLine();
        }

        System.out.println("nhap so nhap nhap tai khoan gom 6 chu so");
        String myAccount = sc.nextLine().trim();

//        kiem tra so tai khoan có du 6 so và là number ko => đang lỗi
        while (bank.checkAccountExist(validateAccount(myAccount))) {
            System.out.println("tai khoan da ton tai. vui long chon tai khoan khac");
            myAccount = sc.nextLine();
        }


        acc.setAccountNumber(myAccount);

//      them so du tai khoan => chưa kiểm tra được đầu vào
        System.out.println("nhap so du tai khoan");
        double myBalance = sc.nextDouble();

        while (myBalance < 50000) {
            System.out.println("so du tai khoan phai lon hon 50.000 vnd");
            myBalance = sc.nextDouble();
        }

        acc.setBalance(myBalance);
//      them account vào list account of file customer
        cus.addAccount(acc);
//      them account theo chúng mình nhân dân => tần dung hàm account
        System.out.println("them so du thanh cong");
        bank.addAccounts(acc, numberCCCD);
    }

    static void showListCustomer() {
        ArrayList<Customer> customers = bank.getCustomers1();

        for (Customer customer : customers) {
//            ArrayList<Account> accounts = customer.getAccounts();
//            for (var acc : accounts) {
//                System.out.println(acc.toString());
//            }
//            System.out.println(customer.toString());
            customer.displayInformation();
        }
    }

//    tim kiem theo id
    static void searchByCustomerID(){
        System.out.println("nhap CCCD de tim kiem");
        String myCCCD = sc.nextLine();
        bank.searchCustomerByCCCD(checkValuesCCCD(myCCCD));
    }

//    tìm kiếm theo tên
    static void searchByNameCustomer(){
        System.out.println("nhap ten khach hang");
        String myNameCustomer = sc.nextLine();
        bank.searchCustomerByName(myNameCustomer);
     }


    public static void main(String[] args) {
        mainMenu();

//        String myStringCCCD = String.valueOf(CCCD.length());
//        String areCode = myStringCCCD.substring(0,3);

//        System.out.println("pham mentor lam");

//        showListCustomer();

//        List<String> names = new ArrayList<>();
//        names.add("Hanh Ngyen");
//        names.add("Hanh Dinh");
//        names.add("Hanh Van");
//        names.add("Nguyen");
//
//        String name = "nguyen";
//
//        for (int i = 0; i < names.size(); i++) {
//            if (names.get(i).toLowerCase().contains(name.toLowerCase())) {
//                System.out.println(names.get(i));
//            }
//        }



    }

}
