package asm03.models;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

public class Transaction {

    private String id;
    private String accountNumber;
//    Chỉ trả về khách hàng nếu như tồn tại CCCD trong hệ thống ngân hàng, ngược lại trả về null.
    private double amount;
    private double transectionFee; // phi giao dich
    private double currentBalance; // số dư hiện tại
    private String description; // sự miêu tả

    private String time;
    private boolean status;

    public Transaction(){

    }
    public Transaction(String accountNumber,double amount,double transectionFee,double currentBalance,String description, boolean status){
        this.id = String.valueOf(UUID.randomUUID());
        this.accountNumber = accountNumber;
        this.amount = amount;
        this.transectionFee = transectionFee;
        this.currentBalance = currentBalance;
        this.description = description;
        this.time = getDateTimes();
        this.status = status;
    }

    public String getAccountNumber(){
        return this.accountNumber;
    }

    public double getAmount(){
        return this.amount;
    }
//  lay thoi gian nhap vào của hien tai
    public static String getDateTimes(){
        DateFormat df = new SimpleDateFormat("dd-MM-yyyy HH-MM-SS");
        Date today = Calendar.getInstance().getTime();
        return df.format(today);
    }


}

