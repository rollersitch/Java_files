import java.io.*;

class leggitastiera{
	public static void main(String[] args)  throws Exception
	{
		String risposta = null;
		System.out.println("scrivi qualcosa:");
		BufferedReader input_tastiera = new BufferedReader(new InputStreamReader(System.in));
		risposta = input_tastiera.readLine();
		System.out.println(risposta + risposta);
	}
}
