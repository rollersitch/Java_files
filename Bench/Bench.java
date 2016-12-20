class Bench {

    public static void main(String[] args) {
        int c = 0;
        for(int i=2;i<=100000;i++) if (isprime(i)) c++;
        System.out.println(c);
    }

    static boolean isprime(int n) {
        if (n == 2) return true;
        else if (n % 2 == 0) return false;
        else {
            for(int i=3;i<=(int)n/2;i+=2)
                if (n % i == 0) return false;
            return true ;
        }
    }

}
