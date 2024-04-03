package asm01;

public class testDeQuy {
    static void dequy(int n, char a, char b, char c){
        if(n == 1){
            System.out.println(" luc nay n se la: " + n + " ..from :" + a + " to :"  + b);
            return;
        }else {
            dequy(n-1, a, c, b);
            System.out.println(" luc nay n se la: " + n + " ..from :" + a + " to :"  + b);
            dequy(n-1, c, b, a);
            System.out.println(" luc nay n se la: " + n + " ..from :" + a + "  to :"  + b+ "lan 2");

        }

    }
    public static void main(String[] args) {
        int n = 5;
        dequy(n,'A', 'B', 'C');
    }

}
