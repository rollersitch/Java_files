public class Prova {
	public static void main(String[] args) {
		QuadratoConAttributi q;
		q = new QuadratoConAttributi(10);
		q.aggiungiAttributo("colore","rosso");
		q.aggiungiAttributo("spessore","15");
		q.elencaAttributi();
	}
}
