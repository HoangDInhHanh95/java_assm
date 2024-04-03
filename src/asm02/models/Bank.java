package asm02.models;
import java.util.ArrayList;
import java.util.UUID;

public class Bank {
    private String id;
    private ArrayList<Customer> customers;
    public Bank(){
        this.customers = new ArrayList<>();
        final String uuid = UUID.randomUUID().toString().replace("-", "");
        this.id = uuid;
    }

    public Bank(ArrayList<Customer> customers) {
        final String uuid = UUID.randomUUID().toString().replace("-", "");
        this.id = uuid;
        this.customers = customers;
    }

    public String getId(){
        return id;
    }

//    van chua so sanh duoc
    public boolean isCustomerExisted(String customerID){
        for (int i = 0; i < customers.size(); i++){
             if (customers.get(i).getCustomerId().equals(customerID)){
                 return true;
             }
        }
        return false;
    }
    public ArrayList<Customer> getCustomers1(){
        return customers;
    }


    //  thêm khach hàng vào danh sach customer
    public void addCustomer(Customer newCustomer){
        if(!isCustomerExisted(newCustomer.getCustomerId())){
            customers.add(newCustomer);
        }

    }
//    Chỉ thêm vào nếu khách hàng tồn tại, tận dụng hàm addAccount của Customer
//    thêm account vào khach hàng thứ i
    public void addAccounts(Account account,String customerId){
            if(isCustomerExisted(customerId)){
                for (int i = 0; i < customers.size(); i++) {
                    if(customers.get(i).getCustomerId().equals(customerId)){
                        customers.get(i).addAccount(account);
                    }
                }
            }
    }

//    kiem tra xem da co tai khoan ton tai kho
    public boolean checkAccountExist(String accountNumber){
        for (int i = 0; i < customers.size(); i++) {
            for (int j = 0; j < customers.get(i).getAccounts().size(); j++) {
                if(customers.get(i).getAccounts().get(j).getAccountNumber().equals(accountNumber)){
                    return true;
                }
            }
        }
        return false;
    }

//hien thị thông tin tai khoản
    public void showCustomer(){

        for (int i=0;i < customers.size(); i++){
            customers.get(i).displayInformation();
        }
    }

//    ham tim kiem bang so chung minh nhan dan
    public void searchCustomerByCCCD(String CCCD){
        for (int i = 0; i < customers.size(); i++) {
            if (customers.get(i).getCustomerId().equals(CCCD)){
                customers.get(i).displayInformation();
            }
        }
    }

//    ham tìm kiếm theo tên khach hàng => đang loi => co the là xuat chua trinh xác
    public void searchCustomerByName(String myName){
        for (int i = 0; i < customers.size(); i++) {
            if (customers.get(i).getName().toLowerCase().contains(myName.toLowerCase())){
                customers.get(i).displayInformation();
            }
        }


    }
}
