import javax.swing.text.Style;
import java.util.Locale;
import java.util.Scanner;

public class Main {
    static char[] alphabet = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'r', 's', 't', 'u', 'v', 'y', 'z'};

    public static void main(String[] args) {
        String str;
        Scanner scanner = new Scanner(System.in);
        System.out.print("Şifrelenecek metin  : ");
        str = scanner.nextLine();
        str=str.toLowerCase(Locale.ROOT);

        //girilen metni şifreleyip sıkıştırıyoruz.
        String encr=encryp(str);
        System.out.println("Şifrelenip sıkıştırılan metin : " +encr);

        //Şifrelenip sıkıştırılan metni açıp şifresini çözüyoruz.
        String decr=decrypt(encr);
        System.out.println("Şifrelenmiş metnin açık hali  : "+decr);


    }
    static String encryp(String str){
        String encyrpStr="";

        for(int i=0;i<str.length();i++){
            char a=str.charAt(i);
            for (int j=0;j<alphabet.length;j++){
                if(a==alphabet[j]){
                    if(j==22){
                        encyrpStr+=alphabet[0];
                    }else {
                        encyrpStr+=alphabet[j+1];
                    }

                }
            }

        }
        String son=zip(encyrpStr);
        return son;
    }
    static String decrypt(String encyrpt){
        String decrypStr="";
        String encyrptStr=deZip(encyrpt);
        for(int i=0;i<encyrptStr.length();i++){
            char ch=encyrptStr.charAt(i);
            for(int j=0;j<alphabet.length;j++){
                if(ch==alphabet[j]){
                    if(j==0){
                        decrypStr+=alphabet[alphabet.length-1];
                    }else {
                        decrypStr+=alphabet[j-1];
                    }

                }
            }
        }
        return  decrypStr;
    }
    static String zip(String str){
        String a="";
        for(int i=0;i<str.length();i++){
            int sayi=0;
            char ch=str.charAt(i);
            a+=ch;
            for(int j=i;j<str.length();j++){
                if(ch==str.charAt(j)){
                    sayi++;
                    if(j==str.length()-1){
                        i+=str.length();
                        break;
                    }
                }

                if(ch!=str.charAt(j)){
                    i+=sayi-1;
                    break;
                }
            }
            if(sayi>1){
                String b=String.valueOf(sayi);
                a+=b;
            }
        }

        return  a;
    }
    static String deZip(String zipEncryptStr){

        String deZipStr="";
        for(int i=0;i<zipEncryptStr.length();i++){
            char ch=zipEncryptStr.charAt(i);
            String a="";
            a+=ch;

            if (isNumeric(a)) {
                int sayi=Integer.parseInt(a);
                for(int j=1;j<sayi;j++){
                    deZipStr+=zipEncryptStr.charAt(i-1);
                }
            }else {
                deZipStr+=ch;
            }

        }



        return deZipStr;
    }
    static boolean isNumeric(String str){
        try {
            Integer.parseInt(str);

            return true;
        } catch (NumberFormatException e) {
            return false;
        }

    }
}