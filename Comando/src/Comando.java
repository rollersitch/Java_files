public class Comando {
	public static void main(String[] args) {
		try {

			Runtime exec = Runtime.getRuntime();
			exec.exec("gnome-terminal -x bash -c \"ls\"");
		}
		catch (java.io.IOException exc) {
			exc.getMessage();
		}
	}
}
