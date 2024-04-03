package asm01;
import java.lang.String;
import java.util.*;
import java.util.regex.Pattern;


public class Asm01 {
    private static final String AUTHOR = "| NGAN HANG SO |";
    private static final String VERSION = " vFX02966@v1.0.0               |";
    public static final String POINT_PLUS ="+--------+-------------------------+-----------+ \n";
    public static  final String OUTPUT_ERROR = "ban nhap sai. vui long chon lai";
    public static Scanner scanner;

    static {
        scanner = new Scanner(System.in);
    }

//    kiem tra dang chon phim main menu
    private static int geTInputValues(){
            int parameter = 0;

            while (true){
                try {
                    System.out.println("vui long nhap de tieo tuc: ");
                    parameter = Integer.parseInt(scanner.nextLine());
                    while (parameter !=0 && parameter !=1){
                        System.out.println(OUTPUT_ERROR);
                        parameter = Integer.parseInt(scanner.nextLine());
                    }
                    break;
                }catch (Exception e){
                    System.out.println(OUTPUT_ERROR);
                }
            }

        return parameter;
    }

    public static void mainMenu(){
        System.out.print(POINT_PLUS);
        System.out.println(AUTHOR + VERSION);
        System.out.print(POINT_PLUS);
        System.out.println("| 1. nhap CCCD                                 |");
        System.out.println("| 0. thoat                                     |");
        System.out.print(POINT_PLUS);

        int a = geTInputValues();

        while (true){
            switch (a){
                case 1:
                    securityCode();
                    break;
                case 0:
                    System.exit(0);
                default:
                    geTInputValues();
                    break;
            }
        }

    }

    public static void childMenu(){
        System.out.println("| 1. kiem tra noi sinh");
        System.out.println("| 2. kiem tra tuoi, gioi tinh");
        System.out.println("| 3. kiem tra so ngau nhien");
        System.out.println("| 0. thoat");
        System.out.println("Ban nhap so de chon chuc nang");


    }

// Ham xac thuc bao mat
    public static void securityCode(){
        try {

            String myRandom = RandomString();
            System.out.println(myRandom);

            System.out.println("vui long nhap ma bao mat");
            String checkCode = scanner.nextLine();

            while (!checkCode.equals(myRandom)){
                System.out.println(OUTPUT_ERROR);
                checkCode = scanner.nextLine();
            }

//          ktra ma CCCD
            checkIdentityCard();

        }catch (Exception e){
            System.out.println(OUTPUT_ERROR);
        }

    }

    //    ham xử lý số ngẫu nhiên
    public static void RandCodeCCCD(String numberRandomCode){
        String myRandomCode = numberRandomCode.substring(6);
        System.out.println("so ngau nhien: " + myRandomCode);
    }

// Nhap va ktra CCCD
    public static void checkIdentityCard(){
        try{
            Scanner scanner = new Scanner(System.in);
            System.out.println("vui long nhap CCCD");

            String myNumbercard = scanner.nextLine().trim();
            Pattern p  = Pattern.compile("^0[0-9]{11}$");

            while (!p.matcher(myNumbercard).find()){
                System.out.println("ban nhap so CCCD khong hop le. Phai la 12 so va bat dau bang 0");
                myNumbercard = scanner.nextLine().trim();
            }

//          mennu con
            childMenu();

            while (true){
                switch (CheckInputMenu()){
                    case 1:
//                       xu ly ma vung Integer.valueOf(firstThreeCCCD);
                        String firstThreeCCCD = myNumbercard.substring(0,3);
                        int mumberCCCD = Integer.parseInt(firstThreeCCCD);
                        areaCode(mumberCCCD);
                        childMenu();
                        break;
                    case 2:
//                      hàm sử lý nam sinh,Giới tính
                        getMaleGender(myNumbercard);
                        childMenu();
                        break;
                    case 3:
//                       ham xử lý số ngẫu nhiên
                        RandCodeCCCD(myNumbercard);
                        childMenu();
                        break;
                    case 0:
                        System.exit(0);
//                    default:
//                        System.out.println(OUTPUT_ERROR);
                }

            }

        }catch (Exception e){
            System.out.println("ban nhap khong dung cu phap vui long nhap lai");
        }
    }

//    ham xu ly nam sinh và gioi tinh
    public static void getMaleGender(String myNumbercard){
        String gender = myNumbercard.substring(3,4);
        int genderNumber = Integer.parseInt(gender);
        String yearOfbirth = myNumbercard.substring(4,6);

        switch (genderNumber){
            case 0:
                System.out.println("Gioi tinh: Nam | 19" + yearOfbirth);
                break;
            case 1:
                System.out.println("Gioi tinh: Nu | 19" + yearOfbirth);
                break;
            case 2:
                System.out.println("Gioi tinh: Nam | 20" + yearOfbirth);
                break;
            case 3:
                System.out.println("Gioi tinh: Nu | 20" + yearOfbirth);
                break;
            case 4:
                System.out.println("Gioi tinh: Nam | 21" + yearOfbirth);
                break;
            case 5:
                System.out.println("Gioi tinh: Nu | 21" + yearOfbirth);
                break;
            case 6:
                System.out.println("Gioi tinh: Nam | 22" + yearOfbirth);
                break;
            case 7:
                System.out.println("Gioi tinh: Nu | 22" + yearOfbirth);
                break;
            case 8:
                System.out.println("Gioi tinh: Nam | 23" + yearOfbirth);
                break;
            case 9:
                System.out.println("Gioi tinh: Nu | 23" + yearOfbirth);
                break;
        }

    }

//  hàm sử lý random
    private static String RandomString(){
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnoqrstuvwyz123456789";
        String randString = "";
        int length = 5;

        Random rand =  new Random();

        char[] textStringrand = new char[length];
        for (int i = 0;i<length;i++){
            textStringrand[i] = characters.charAt(rand.nextInt(characters.length()));
        }

        for (int i = 0; i < textStringrand.length;i++ ){
            randString += textStringrand[i];

        }

        return randString;
    }

//   ham xu ly ma vung => return ma CCCD va gioi tinh, ma ngau nhien
    public static void areaCode(int parameter){

         Map map = new HashMap();

        String[] area = {"001 Ha Noi", "002 Ha Giang","004 Cao Bang", "006 Bac Can","008 Tuyen Quang","010 Lao Cai",
                "011 Dien Bien","012 Lai Chau","014 Son La","015 Yen Bai","017 Hoa Binh","013 Thai Nguyen",
                "020 Lang Son","022 Quang Ninh","024 Bac Giang","025 Phu Tho","026 Vinh Phuc","027 Bac Ninh",
                "030 Hai Duong","031 Hai Phong","033 Hung Yen","034 Thai Binh","035 Ha Nam","036 nam Dinh",
                "037 Ninh Binh","038 Thanh Hoa","040 Nghe An","042 Ha Tinh","044 Quang Binh","045 Quang Tri",
                "046 Thua Thien Hue","048  Da Nang", "049 Quang Nam","051 Quang Ngai","052 Binh Dinh","054 Phu yen",
                "056 Khanh Hoa","058 Ninh Thuan","060 Binh Thuan","062 Kum Tom","064  Gia Lai","066 Dak Lak",
                "067  Dak Nong","068 Lam Dong","070 Binh Phuoc","072 Tay Ninh","074 Binh Duong","075 Dong Nai",
                "077 Ba Ria - Vung Tau","079 Ho Chi Minh","080 Long An","082 Tien Giang","083 Ben Tre",
                "084 Tra Vinh","086 Vinh Long","087 Dong Thap","089 An Giang","091 Kien Giang","092 Can Tho",
                "093 Hau Giang","094 Soc Trang","095 Bac Lieu","096 Ca Mau"};

         for(int i = 0; i < area.length; i++){
             String element = area[i];
             String areCode = element.substring(0,3);
             String nameArea = element.substring(3).trim();
             map.put(areCode, nameArea);
         }

        Set set = map.keySet();
        for (Object key : set) {
           int h = Integer.parseInt((String) key);
           if(parameter == h){
               System.out.println("Noi Sinh: " + map.get(key));
           }
        }

    }

    private static int CheckInputMenu(){
        int myNumber = 0;
        while (true){
            try{
                myNumber = Integer.parseInt(scanner.nextLine());
                if(myNumber > 3){
                    System.out.println(OUTPUT_ERROR);
                }
                break;
            }catch (Exception e){
                System.out.println(OUTPUT_ERROR);
            }
        }
        return myNumber;
    }

// ham test
    public static String nhap(){
        String temp = "";
        System.out.println("enter string");
        temp = scanner.nextLine();
        return temp;
    }

    public static void kiemtra(String parameter){

        System.out.println(" day la string a " + parameter );

        char[] b = new char[100];

        parameter.getChars(0,parameter.length(), b,0 );

        for (int i =0; i< parameter.length(); i++){
            System.out.println("day la mang b: " + b[i]);
            for (int j= i+1; j < parameter.length(); j++){
                if(b[i] == b[j]){
                    System.out.println("co gia tri rung nhau: " + b[j]);
                    nhap();
                    break;
                }
            }
        }

    }

    public static void main(String[] args) {
//           mainMenu();

    }
}
