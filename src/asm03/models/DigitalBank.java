package asm03.models;

import asm02.models.Account;
import asm02.models.Bank;
import asm02.models.Customer;
public class DigitalBank extends Bank{
    public  DigitalBank(){}

//  method return customer object
   public Customer getCustomerById(String customerID){
       Customer myCustomer = null;
       for (int i = 0; i < getCustomers1().size(); i++) {
           if (getCustomers1().get(i).getCustomerId().equals(customerID)){
               myCustomer = getCustomers1().get(i);
               break;
           }
       }
        return myCustomer;
   }

    public void addDigitalCustomer(String name, String customerID){
       if (getCustomerById(customerID) == null){
            DigitalCustomer newCustomer = new DigitalCustomer(name, customerID);
//            them khach hang vao list customer
            addCustomer(newCustomer);

       }
    }

    // savingsAccount
    public void addSavingAccount(Account account, String customerID){
        for (Customer customer : getCustomers1()){
            if (customer.getCustomerId().equals(customerID)){
                customer.addAccount(account);
                return;
            }
        }
    }


// loanAccount
    public void addLoanAccount(Account loanAccount, String customerID){
        if(isCustomerExisted(customerID)){
            for (int i = 0; i < getCustomers1().size() ; i++) {
                if (getCustomers1().get(i).getCustomerId().equals(customerID)){
                    getCustomers1().get(i).addAccount(loanAccount);
                }
            }
        }
    }

//    withdraw => rut tien => cho cả loanAccount và savingsAccount
//    cho phép rút tiền theo tài khoản

    public void withdraw(String customerID,String numberAccount,double amount){
        DigitalCustomer getCustmerByid = (DigitalCustomer) getCustomerById(customerID);
        getCustmerByid.withdraw(numberAccount, amount);
    }


//    xuat hoa don
    public void getTransactionInformation(String customerID){
        Customer getCustmerById = getCustomerById(customerID);
        getCustmerById.transactionInformation();
    }

}


