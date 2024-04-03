package asm03.models;

import asm02.models.Account;
import asm02.models.Customer;
import asm03.Withdraw;

import java.text.DecimalFormat;

public class DigitalCustomer extends Customer {

    public DigitalCustomer(String name, String id){
        super(name, id);
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
        System.out.println(super.getCustomerId() + "|       " +
                super.getName() + "| " + isPremiumAccount + " | " +
                decimalFormat.format(getBalanceTotal()));
        for (int i= 0; i< getAccounts().size(); i++){
            orderNumerical++; // so thu tu
            System.out.println(orderNumerical + "|" + isPremiumAccount + getAccounts().get(i).toString());
        }

    }

    public void withdraw(String numberAccount, double amount){
        for (int i = 0; i < getAccounts().size(); i++) {
            if (getAccounts().get(i).getAccountNumber().equals(numberAccount)){
                getAccounts().get(i).withdraw(amount);
            }
        }
    }
}



