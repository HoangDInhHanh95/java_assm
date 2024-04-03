package asm03;

import asm02.models.Account;
import asm02.models.Customer;
import asm03.models.DigitalBank;
import asm03.models.LoanAccount;
import asm03.models.SavingsAccount;
import asm03.models.Transaction;

import java.util.Scanner;
import java.util.regex.Pattern;

public class Asm03 {
    private static final int EXIT_COMMAND_CODE = 0;
    private static final int EXIT_ERROR_CODE = -1;
    private static String VERSON = "v1.0.0";
    private static final int ACOUNT_MAX_BALANCE = 100000000;
    private static final Scanner scanner = new Scanner(System.in);
    private static final DigitalBank activeBank = new DigitalBank();
    private static final String CUSTOMER_ID = "001215000001";
    private static final String CUSTOMER_NAME = "FUNIX";
    private static final String OUTPUT_ERROR = "Ban nhap chua chinh xac vui long nhap lai";
//  lấy khách hàng theo id của khách khàng
    Customer activeCustomer =  activeBank.getCustomerById(CUSTOMER_ID);

    public static void showTable(){
        System.out.println("+---------*--------------*-----------+");
        System.out.println("|NGAN HANG SO | vFX02966" + VERSON + "      |");
        System.out.println("+---------*--------------*-----------+");
        System.out.println("|1. Thong tin khach hang             |");
        System.out.println("|2. Them tai khoan Savings           |");
        System.out.println("|3. Them tai khoan Loan              |");
        System.out.println("|4. Rut tien                        |");
        System.out.println("|5. Lich su giao dich                |");
        System.out.println("|0. Thoat                            |");
        System.out.println("+---------*--------------*-----------+");
    }

    public static void mainMenu(){
        activeBank.addDigitalCustomer(CUSTOMER_NAME,CUSTOMER_ID);

        showTable();
        while (true){
            switch (getOptionValueMenu()){
                case 1:
//                  thong tin tai khoan
                    showCustomer();
                    showTable();
                    break;
                case 2:
                    addSavingsAccount();
                    showTable();
                    break;
                case 3:
//                    them tai khoan loan
                    addLoanAcount();
                    showTable();
                    break;
                case 4:
//                   rut tien
                    withdraw();
                    showTable();
                    break;
                case 5:
                    lookUpAccountInformation();
                    showTable();
                    break;
                case 0:
                    System.exit(EXIT_COMMAND_CODE);
                default:
                    System.out.println(OUTPUT_ERROR);
            }
        }
    }

//    ham kiem tra xem so nhap vào co phai là double k => bat nhap  lai khi khong phai
    public static double isNumberDoubleBalence(double amount){
        amount = 0.0;
        while (true){
            try{
                amount = Double.parseDouble(scanner.nextLine());
                break;
            }catch (Exception e){
                System.out.println(OUTPUT_ERROR);
            }
        }
        return amount;
    }

//    kiem tra xem khi nhập vào có phải kiểu số không
    public static String isNumberInput(String numberAccount){
        Pattern p = Pattern.compile("^[0-9]{6}$");
        while (true){
            if (p.matcher(numberAccount).find()){
                break;
            }else {
                System.out.println(OUTPUT_ERROR);
                numberAccount = scanner.nextLine();
            }
        }
        return numberAccount;
    }

    //    kiem tra lua chon cua khach hang co hop le ko
    public static int getOptionValueMenu(){
        int valueOption = 0;
        while (true){
            try {
                System.out.println("Chuc nang: ");
                valueOption = Integer.parseInt(scanner.nextLine());
                while (valueOption > 6) {
                    System.out.println();
                    valueOption = Integer.parseInt(scanner.nextLine());
                }
                break;
            } catch (Exception e) {
                System.out.println(OUTPUT_ERROR);
            }
        }
        return valueOption;
    }

    public static void showCustomer(){
        Customer customers = activeBank.getCustomerById(CUSTOMER_ID);
        if (customers != null){
            customers.displayInformation();
        }

    }

    // add Savings Account
    public static void addSavingsAccount(){
        SavingsAccount account = new SavingsAccount();


        System.out.println("nhap so nhap nhap tai khoan gom 6 chu so");
        String myAccountSavings = scanner.nextLine();

//        kiem tra xem account co ton tai ko phai la so
        while (activeBank.checkAccountExist(isNumberInput(myAccountSavings))){
            System.out.println("tai khoan da ton tai. vui long chon tai khoan khac");
            myAccountSavings = scanner.nextLine();
        }
        account.setAccountNumber(myAccountSavings);

//        nhap số tiền
        System.out.println("nhap so du tai khoan");
        double myBalance = scanner.nextDouble();

        while (myBalance < 50000) {
            System.out.println("so du tai khoan phai lon hon 50.000 vnd");
            myBalance = scanner.nextDouble();
        }

        account.setBalance(myBalance);

//        them account vào list account
        activeBank.addSavingAccount(account, CUSTOMER_ID);
        System.out.println("them tai khoan savings thanh cong");
    }

// add Loan Account
//  balance account khong duoc qua 100tr => aout loi neu nhap qua
    public static void addLoanAcount(){
        LoanAccount account = new LoanAccount();
        System.out.println("nhap so nhap nhap tai khoan gom 6 chu so");
        String myAccountSavings = scanner.nextLine();

//        kiem tra xem account co ton tai ko phai la so
        while (activeBank.checkAccountExist(isNumberInput(myAccountSavings))){
            System.out.println("tai khoan da ton tai. vui long chon tai khoan khac");
            myAccountSavings = scanner.nextLine();
        }
        account.setAccountNumber(myAccountSavings);

//        nhap số tiền VAF
        System.out.println("nhap so du tai khoan");
        double myBalance = scanner.nextDouble();
//       so tien ko dươc nho hon 50000 va lon hon 100tr
        while (myBalance < 50000||myBalance > ACOUNT_MAX_BALANCE) {
            System.out.println("so du tai khoan phai lon hon 50.000 vnd và nho hon 100.000.000 vnd");
            myBalance = scanner.nextDouble();
        }
        account.setBalance(myBalance);

//        them account vào list account
        activeBank.addLoanAccount(account, CUSTOMER_ID);
        System.out.println("them tai khoan Loan thanh cong");
    }

//  hàm rut tiên theo tai khoan nhập vào
    public static void withdraw() {
        System.out.println("nhap tai khoan ma ban muon rut");
        String myAccount = scanner.nextLine();
        isNumberInput(myAccount);
//         co them ham kiem tra xem co phai la so double khong => khong thi ra thong bao
        System.out.println("nhap so tien ban muon rut");
        double myWithdraw = scanner.nextDouble();
        activeBank.withdraw(CUSTOMER_ID,myAccount,myWithdraw);

    }

    public static void lookUpAccountInformation(){
        activeBank.getTransactionInformation(CUSTOMER_ID);
    }

    public static void main(String[] args) {
        mainMenu();

    }


}
