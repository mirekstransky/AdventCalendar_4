import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) throws IOException {

        List<String> listInput = readAllLines("input4test.txt");

        int shoda = 0;
        int vysledek = 0;
        for (int i = 0; i < listInput.size(); i++) {

            String row = listInput.get(i);

            int idChar = row.indexOf(":");
            String idName = row.substring(0,idChar);
            int idNum = findNumber(idName);
            row = row.substring(idChar+1,row.length());

            String levaStrana = row.substring(1,row.indexOf("|")-1).trim();
            String pravaStrana = row.substring(row.indexOf("|")+1,row.length()).trim();


            String[] hodnotyLeva = levaStrana.split("\\s+");
            String[] hodnotyPrava = pravaStrana.split("\\s+");


            shoda = zjistiShodu(hodnotyLeva,hodnotyPrava);
            if (shoda==1){

            }

            System.out.printf("\nŘádek %d má shodu v počtu: %d",i+1,shoda);

            vysledek += shoda;
        }
        System.out.printf("\nVýsledek je %d",vysledek);

    }








    public static List<String> readAllLines(String resource)throws IOException {
        ClassLoader classLoader=Thread.currentThread().getContextClassLoader();

        try(InputStream inputStream=classLoader.getResourceAsStream(resource);
            BufferedReader reader=new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8))){

            return reader.lines().collect(Collectors.toList());
        }
    }
    public static int zjistiShodu(String[] vyherni, String[] kontrolni){
        int sum = 0;
        for (int i = 0; i < kontrolni.length; i++) {
            for (int j = 0; j < vyherni.length; j++) {
                if (kontrolni[i].equals(vyherni[j])){
                    sum += 1;
                }
            }
        }
        return sum;
    }

    public static int findNumber(String string){
        String result = "";
        for (int i = 0; i < string.length(); i++) {
            String character = String.valueOf(string.charAt(i));
            if (isNumeric(character)){
                result += character;
            }
        }
        if (!result.isEmpty()){
            return Integer.parseInt(result);
        }else {
            return -1;
        }
    }
    public static boolean isNumeric(String num) {
        if (num == null) {
            return false;
        }
        try {
            double d = Integer.parseInt(num);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }
}