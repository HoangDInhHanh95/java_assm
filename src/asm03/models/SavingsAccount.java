package asm03.models;

import asm02.models.Account;
import asm03.ReportService;
import asm03.Utils;
import asm03.Withdraw;

import java.text.DecimalFormat;

public class SavingsAccount extends Account implements ReportService, Withdraw {
    private static final double SAVINGS_ACCOUNT_WITHDRAW_PREMIUM_FEE = 0.0;
    private static final double SAVINGS_ACCOUNT_WITHDRAW_FEE = 0.0;
    private double SAVINGS_ACCOUNT_MAX_WITHDRAW = 5000000;
    private static final double SAVINGS_ACCOUNT_MIN_WITHDRAW = 50000;
    private static final double SAVINGS_ACCOUNT_MIN_BALANCE = 50000;
    public static String getTittleSavings(){
        return "BIEN LAI GIAO DICH SAVINGS";
    }

//    log ra thông báo khi giao dich
    public void log(double amount){
        System.out.println(Utils.getDivider());
        System.out.printf("%30s%n",getTittleSavings());
        System.out.printf("NGAY G/D %28S%n",Utils.getDateTime());
        System.out.printf("ATM ID: %30s%n", "DIGITAL-BANK-ATM 2023");
        System.out.printf("SO TK: %29s%n", getAccountNumber());
        System.out.printf("SO TIEN: %29s%n", Utils.formatBalance(amount)+"đ");
        // so tien rut sẽ được hiện thị tại day
        System.out.printf("SO DU: %31s%n", Utils.formatBalance(getBalance())+"đ");
        System.out.printf("PHI + VAT: %27s%n", getFee(amount)+"đ");
        System.out.println(Utils.getDivider());
    }

    public boolean withdraw(double amount){
        double newBalance = 0.0;
        if (isAccepted(amount)){
            if (isPremium()){
                newBalance = getBalance() - amount - getFee(amount);
                setBalance(newBalance);
                Transaction bill = new Transaction(this.getAccountNumber(), amount, getFee(amount), newBalance,
                        "Rut tien gui tiec kiem", true);
                addTransection(bill);
                log(amount);
                return true;
            }else {
                if (amount <= SAVINGS_ACCOUNT_MAX_WITHDRAW){
                    newBalance = getBalance() - amount - getFee(amount);
                    setBalance(newBalance);
                    Transaction bill = new Transaction(this.getAccountNumber(), amount, getFee(amount),
                            newBalance, "Rut tien gui tiec kiem", true);
                    addTransection(bill);
                    log(amount);
                    return true;
                }else {
                    System.out.println("Giao dich cua ban that bai");
                    return false;
                }
            }
        }else {
            System.out.println("Giao dich khong thanh cong");
            return false;
        }
    }

    public boolean isAccepted(double amount){
        boolean isAccept = true;
//      so tien sau khi rut phai lon hon 50k
        double balanceAfterWithdraw = getBalance() - amount - getFee(amount);
//      tài khoản thường khi rút tiền
        if((amount % 10000) != 0){
            System.out.println("so tien phai rut la boi so cua 10000");
            isAccept = false;
        } else if (amount > getBalance()) {
            System.out.println("so tien rut vuot qua so du hien tai " + String.format("%d",(long)getBalance()));
            isAccept = false;
        } else if (amount < SAVINGS_ACCOUNT_MIN_WITHDRAW) {
            System.out.println("so tien phai phai phai lon hon " +
                    String.format("%d",(long)SAVINGS_ACCOUNT_MIN_WITHDRAW) + "trong moi lan gia dich");
            isAccept = false;
        } else if (balanceAfterWithdraw < SAVINGS_ACCOUNT_MIN_BALANCE) {
            System.out.println("so du tai khoan con lai phai lon hon " + String.format("%d", (long)SAVINGS_ACCOUNT_MIN_BALANCE));
            isAccept = false;
        }
        return isAccept;
    }

    @Override
    public String toString() {
        String isPremiumAccount = "";
        if (isPremium() == true){
            isPremiumAccount = "Premium";
        }else {
            isPremiumAccount = "Normal";

        }
        String pattern = "###,###.###";
        DecimalFormat decimalFormat = new DecimalFormat(pattern);
        return getAccountNumber() + "         SAVINGS | " + isPremiumAccount + " | "+ decimalFormat.format(getBalance());
    }


    public double getFee(double amount){
        if(isPremium()){
            return SAVINGS_ACCOUNT_WITHDRAW_PREMIUM_FEE * amount;
        }else {
            return SAVINGS_ACCOUNT_WITHDRAW_FEE * amount;
        }
    }



}
