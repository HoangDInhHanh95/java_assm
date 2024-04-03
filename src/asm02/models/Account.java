package asm02.models;

import asm03.ReportService;
import asm03.Withdraw;
import asm03.models.Transaction;
import java.text.DecimalFormat;
import java.util.ArrayList;

public class Account implements ReportService, Withdraw {
    private String accountNumber;
    private ArrayList<Transaction> transactions = new ArrayList<>();
    private double balance;
    private static String space = "|                   ";
    public Account(){

    }

//    nap tien
    public void setBalance(double balance) {
        this.balance = balance;
    }
    public void log(double amount){
        System.out.println("");
    }
//    kiem tra hàm rut tien
    public boolean isAccepted(double amount){
        if ((this.balance - amount) < 0 ){
            return true;
        }
        return false;
    }
// rut tien
    public boolean withdraw(double amount){

        if(isAccepted(amount)){
            System.out.println("so tien ban rut vuot qua hoi han tai khoan");
        }else {
            this.balance -= amount;
            System.out.println("so tien ban rut la: " + amount + "tai khoan cua ban con lai la: "+ this.balance);
            return true;
        }
        return false;
    }


    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getAccountNumber() {
        return accountNumber;
    }


    public double getBalance() {
        return balance;
    }

    public boolean isPremium(){
//        return this.balance >= 10000000;
        if (this.balance >= 10000000){
           return true;
        }
        return false;
    }

    @Override
    public String toString() {
        String pattern = "###,###.###";
        DecimalFormat decimalFormat = new DecimalFormat(pattern);
        return getAccountNumber() + space + decimalFormat.format(getBalance());
    }

    public ArrayList<Transaction> getTransactions() {
        return transactions;
    }

    public void addTransection(Transaction transection){
        this.transactions.add(transection);
    }


}
