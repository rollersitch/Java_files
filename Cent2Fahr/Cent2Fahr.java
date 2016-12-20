import java.io.*;

public class Cent2Fahr {
    public static void main(String[] args) {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Inserci il valore in gradi Celsius");
        try {
            String ipt = input.readLine();
            double cent = Integer.parseInt(ipt);
            double fahr = 32 + ((cent/100)*180);
            System.out.println(cent + " gradi centigradi corrispondono a " + fahr + " gradi fahreneit");
        }
        catch(IOException exc) {
            exc.getMessage();
        }
    }
}