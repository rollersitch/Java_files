import java.io.*;
public class java2html {
	public static void main(String[] args) throws Exception{
	    for(int i=0; i<args.length ; i++) {
		String nome_in = args[i].toString();
		String nome_out = args[i].toString() + ".html";
		String riga = null;
		BufferedReader input = new BufferedReader(new InputStreamReader(new FileInputStream(new File(nome_in))));
		PrintStream output = new PrintStream(new FileOutputStream(new File(nome_out)));
		output.println("<html>");
		output.println("");
		output.println("<body>");
		output.println("<hr>");
		while ((riga = input.readLine())!=null){
			
			output.println("<h3>"+riga+"<br>");
		}
		output.println("<hr>");
		output.println("</body>");
		output.println("");
		output.println("</html>");
		System.out.println("Ho appena creato il file " +  nome_out + " contenente la copia del file " + nome_in);
	    }
	}//main
}//class es02
