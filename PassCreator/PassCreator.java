import java.util.Scanner;

public class PassCreator {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        // try {
            System.out.println("Inserisci il tuo nome: ");
            String nome = input.nextLine();
            System.out.println("Inserisci la tua età: ");
            String age = input.nextLine();
            
            
            
            System.out.println("Il sistema ha generato la tua password: " 
                                                                            + nome.charAt(0) + nome.charAt(1) +
                                                                             age.charAt(0) + // Math.random()*99 +
                                                                             age.charAt(age.length()-1) 
                                                                             // nome.charAt(nome.length() -1).toUpperCase() +
                                                                             // nome.charAt(nome.length()).toUpperCase()
                               );
         // }
         /* catch(IOException exc) {
             exc.getMessage();
         } */
     }
     
}
                                                                            
