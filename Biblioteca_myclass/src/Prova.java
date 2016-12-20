public class Prova {
	public static void main(String[] args) {
		Autore dante = new Autore("Dante", "Alighieri", "italiano");
		dante.descrivi();
		


		Libro divinaComm = new Libro("La Divina Commedia",dante);
		

		divinaComm.setIsbn("333435");
		divinaComm.insertNote("L'opera del massimo autore italiano");
		divinaComm.stampaDati();
		Autore foscolo = new Autore("Ugo","Foscolo","italiano");	
		System.out.println();
		System.out.println();
		foscolo.descrivi();
		
		Libro sepolcri = new Libro("I Sepolcri",foscolo);
		sepolcri.setIsbn("22223346");
		sepolcri.insertNote("L'opera di maggior successo di Foscolo");

		
		sepolcri.stampaDati();
	}
}
	
