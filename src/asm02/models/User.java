package asm02.models;
import java.util.Scanner;

public class User {
    private String name;
    private  String customerId;

    public User(){

    }

    public User(String name, String customerId) {
        this.name = name;
        this.customerId = customerId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getCustomerId() {
        return customerId;
    }


    public boolean checkValdateCCCID(String CCCD){
//        array cccd
        String[] myCCCDArray = { "001","002","004","006","008","010","011","012","014","015","017","019","020","022",
        "024","025","026","027","030","031","033","034","035","036","037","038","040","042","044","045","046","048",
        "049","051","052","054","056","058","060","062","064","066","067","068","070","072","074","075","077","079",
        "080","082","083","084","086","087","089","091","092","093","094","095","096"};


        String myThreeCCCD = "";
        for (int i = 0; i < 3 ; i++) {
            myThreeCCCD += CCCD.charAt(i);
        }
        int b = Integer.parseInt(myThreeCCCD);

        for (int i = 0; i < myCCCDArray.length; i++) {
            int temp = Integer.parseInt(myCCCDArray[i]);
            if (b == temp){
               return true;
            }
        }
        return false;
    }


}
