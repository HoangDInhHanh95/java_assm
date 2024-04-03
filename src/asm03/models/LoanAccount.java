package asm03.models;

import asm02.models.Account;
import asm03.ReportService;
import asm03.Withdraw;
import asm03.Utils;

import java.text.DecimalFormat;

public class LoanAccount extends Account implements ReportService, Withdraw {
    private double LOAN_ACCOUNT_WITHDRAW_FEE = 0.05;
    private double LOAN_ACCOUNT_WITHDRAW_PREMIUM_FEE = 0.01;
    private double LOAN_ACCOUNT_MAX_BALANCE = 100000000;
    private static final double LOAN_ACCOUNT_MIN_BALANCE = 50000;
    public LoanAccount(){
        super();
    }

//    xu ly rut tien
//
    public boolean withdraw(double amount){
        double newBalance = 0.0;
        if (isAccepted(amount)){
           newBalance = getBalance() - amount - getFee(amount);
           setBalance(newBalance);
            Transaction bill = new Transaction(this.getAccountNumber(), amount, getFee(amount), newBalance,
                    "rut tien tai khoan lan", true);
            addTransection(bill);
            log(amount);
            return true;
        } else {
            System.out.println("Giao dich cua ban that bai");
            return false;
       }

    }

    public static String getTittle(){
        return "BIEN LAI GIAO DICH LOAN";
    }

    public void log(double amount){
        System.out.println(Utils.getDivider());
        System.out.printf("%30s%n",getTittle());
        System.out.printf("NGAY G/D %28S%n",Utils.getDateTime());
        System.out.printf("ATM ID: %30s%n", "DIGITAL-BANK-ATM 2023");
        System.out.printf("SO TK: %29s%n", getAccountNumber());
        System.out.printf("SO TIEN: %31s%n", Utils.formatBalance(amount)+"đ");
        System.out.printf("SO DU: %31s%n", Utils.formatBalance(getBalance())+"đ");
        // so tien rut sẽ được hiện thị tại day
        System.out.printf("PHI + VAT: %27s%n", getFee(amount)+"đ");
        System.out.println(Utils.getDivider());

    }

    public boolean isAccepted(double amount){

    boolean isAccept = true;
        double balanceAfterWithdraw = getBalance() - amount - getFee(amount);
        if (amount <= 0) {
            System.out.println("so tien khong duoc be hon hoac bang 0");
            isAccept = false;
        }else if (amount > LOAN_ACCOUNT_MAX_BALANCE) {
            System.out.println("Tai khoan khong duoc rut qua " + String.format("%,d", (long) LOAN_ACCOUNT_MAX_BALANCE)  +
                    " mỗi giao dịch!");
            isAccept = false;
        }else if (amount > getBalance()) {
            System.out.println("tai khoan khong duoc rut qua so du hien tai  " + String.format("%,d", (long) getBalance())  + " !");
            isAccept = false;
        }else if (balanceAfterWithdraw < LOAN_ACCOUNT_MIN_BALANCE) {
            System.out.println("so du toi thieu phai lon hon " + String.format("%,d", (long) LOAN_ACCOUNT_MIN_BALANCE)   +
                    "!");
            isAccept = false;
        }
        return isAccept;
    }

    public double getFee(double amount) {
        if (isPremium()) {
            return LOAN_ACCOUNT_WITHDRAW_PREMIUM_FEE * amount;
        } else {
            return LOAN_ACCOUNT_WITHDRAW_FEE * amount;
        }
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
        return getAccountNumber() + "         LOANS | " + isPremiumAccount + "|" + decimalFormat.format(getBalance());
    }


}
