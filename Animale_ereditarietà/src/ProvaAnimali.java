public class ProvaAnimali {
	public static void main(String[] args) {
		Cane cane = new Cane();
		cane.stampaDati();
		System.out.println(cane.siMuove());
		System.out.println(cane.verso());

		Delfino delfino = new Delfino();
		delfino.stampaDati();
		System.out.println(delfino.siMuove());
		System.out.println(delfino.verso());
	}
}
