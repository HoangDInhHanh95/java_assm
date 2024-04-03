package asm02.models;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class Customer extends User {
    private ArrayList<Account> accounts;
    public Customer(){
        this.accounts = new ArrayList<>();
    }

    public Customer(String name, String customerId) {

        super(name, customerId);
        this.accounts = new ArrayList<>();
    }

//    lay danh sach tai khoan
    public ArrayList<Account> getAccounts() {
        return accounts;
    }

    public  boolean isPremium(){
        for (int i = 0; i< accounts.size(); i++){
            if(accounts.get(i).isPremium()){
                return true;
            }
        }
        return false;
    }
//    public boolean checkAccountExistence(String newAccount){
//        for (int i = 0; i < accounts.size(); i++) {
//            if (accounts.get(i).getAccountNumber().equals(newAccount)){
//                return false;
//            }
//        }
//        return true;
//    }

//   kiem tra xem da ton tai customerID chưa => đang loi
    public boolean checkCustomerID(Account newAccount){
        for (int i = 0; i < accounts.size(); i++){
            if (accounts.get(i).getAccountNumber().equals(newAccount.getAccountNumber())){
                return false;
            }
        }
        return true;
    }

//    kiem tra va them tai khoan cho khach
    public void addAccount(Account newAccount){
        if(checkCustomerID(newAccount)==true){
            accounts.add(newAccount);
        }
    }

//    tinh tong so du tat ca tai khoan
//    van lam sao de minh co the biet dc chinh sac tai khoan do cua minh de tinh tong
    public double getBalanceTotal(){
        double total = 0;
            for (int i = 0; i < accounts.size(); i++){
                total = total + accounts.get(i).getBalance();
            }
        return total;
    }

//    hien thi thong tin khach hang
    public void displayInformation(){

        String isPremiumAccount = "";
        int orderNumerical = 0;
        if (isPremium() == true){
            isPremiumAccount = "Premium";
        }else {
            isPremiumAccount = "Normal";
        }

//        Định dạng số trong java
        String pattern = "###,###.###";
        DecimalFormat decimalFormat = new DecimalFormat(pattern);

//        hien thi thong tin nguoi dung
        System.out.println(super.getCustomerId() + "|       " + super.getName() + "| " +
                isPremiumAccount + " | " + decimalFormat.format(getBalanceTotal()));

//        hien thi tai khoan va so du tai khoan
        for (int i= 0; i< accounts.size(); i++){
            orderNumerical++;
            System.out.println(orderNumerical + "|" + accounts.get(i).toString());
        }

    }

    public void transactionInformation() {
        String isPremiumAccount = "";
        if (isPremium()) {
            isPremiumAccount = "Premium";
        } else {
            isPremiumAccount = "Normal";
        }
        int orderNumerical = 0;
//        Định dạng số trong java
        String pattern = "###,###.###";
        DecimalFormat decimalFormat = new DecimalFormat(pattern);

//        hien thi thong tin nguoi dung
        System.out.println(super.getCustomerId() + "|      " +
                super.getName() + "| " + isPremiumAccount + " | " +
                decimalFormat.format(getBalanceTotal()));
//      Hiện thi account cua người dung
        for (int i = 0; i < getAccounts().size(); i++) {
            orderNumerical++; // so thu tu
            System.out.println(orderNumerical + "|" + getAccounts().get(i).toString());
        }

//        hien thi thong tin giao dich
        for (int i = 0; i < getAccounts().size(); i++) {
            for (int j = 0; j < getAccounts().get(i).getTransactions().size(); j++) {
                System.out.println(getAccounts().get(i).getTransactions().get(j).getAccountNumber() +
                        "  |  " + decimalFormat.format(getAccounts().get(i).getTransactions().get(j).getAmount()) +
                        "  |  " +getAccounts().get(i).getTransactions().get(j).getDateTimes());
            }
        }

    }

}
