import java.util.Scanner;

public class Prime40 {
    public static void main(String[] args) {

        System.out.print("Voglio contare i numeri primi fino a:" );
        int n = (new Scanner(System.in)).nextInt();
        for(int i = 0; i <= n;i++) {
            if (i == 0 || i == 1 || i == 2 || i == 3) {
                System.out.print(i);
                continue;
            }
            else {
                while (!(i % n == 0)) {
                    System.out.print(i);
                }
            }
        }
    }
}


