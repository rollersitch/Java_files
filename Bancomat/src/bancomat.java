import java.io.*;
import javax.swing.*;
import java.lang.*;
import java.awt.*;
class bancomat
{





	static void schermata_iniziale(){
	int scelta=0;
	int contatore_codice=0;
	String prompt=(	"                                               \n"+
	"BANCOMAT                                       \n"+
	"1: Funzione Bancomat                           \n"+
	"2: Ricariche Telefoniche                       \n"+
	"3: Fine                                        \n"+
	"                                               \n");

	
		do{//eseguira' il blocco per una volta senza considerare la condizione del while
			String s=JOptionPane.showInputDialog(prompt);
			scelta=Integer.parseInt(s);
			String bancomat="Bancomat";
			String ricarica="Ricarica";
			switch(scelta){
				case 1:schermata_bancomat(); ;break;
				case 2:JOptionPane.showInputDialog(ricarica); ;break;
				case 3: System.exit(1);// metodo System per uscire fuori dal progamma accetta parametri int
				default: JOptionPane.showMessageDialog(null, "Scelta Non Valida", "ERROR!", JOptionPane.ERROR_MESSAGE); 
			}
		}while(scelta!=3);
	
		
	
	}//metodo schermata_iniziale
	
	static void schermata_bancomat(){
		int scelta=0;
	String prompt=(	"\n"+
	"BANCOMAT\n"+
	"1: Prelievo\n"+
	"2: Saldo\n"+
	"3: Lista Movimenti\n"+
	"\n");
	
	do{//eseguira' il blocco senza considerare la condizione del while
			String s=JOptionPane.showInputDialog(prompt);
			scelta=Integer.parseInt(s);
			String bancomat="Bancomat";
			String ricarica="Ricarica";
			switch(scelta){
				case 1:JOptionPane.showInputDialog(bancomat); ;break;
				case 2:JOptionPane.showInputDialog(ricarica); ;break;
				case 3: return;
				default: JOptionPane.showMessageDialog(null, "Scelta Non Valida", "ERROR!", JOptionPane.ERROR_MESSAGE); 
			
			}
		}while(scelta!=3);//chiusura do

	}//schermata_bancomat
	
	static void password(){


		get_password frame = new get_password();
		frame.setTitle("Pin Bancomat");
		Toolkit kit = Toolkit.getDefaultToolkit();
		Dimension screenSize = kit.getScreenSize();
		int screenHeight = screenSize.height;
		int screenWidth = screenSize.width;
		frame.setSize(screenWidth / 2, screenHeight / 2);
		frame.setLocation(screenWidth / 4, screenHeight / 4);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		frame.pack();
	}




static void controllo(){
		int tentativi=0;
		String codice="INSERIRE CODICE UTENTE";
		String sicurezza=JOptionPane.showInputDialog(codice);
		String confermaCodice="1234";
		bancomat.password();
		if(sicurezza.equals(confermaCodice))
		{
		JOptionPane.showMessageDialog(null, "Codice Corretto ", "ERROR!", JOptionPane.INFORMATION_MESSAGE);
		bancomat.schermata_iniziale();
		}

		else{
				while(tentativi<3)
				{
					tentativi++;
					switch (tentativi)
				{
				case 1: JOptionPane.showMessageDialog(null, "Codice non  Corretto", "", JOptionPane.INFORMATION_MESSAGE); 
						String sicurezza2=JOptionPane.showInputDialog(codice);
					if (sicurezza2.equals(confermaCodice))
					{
					JOptionPane.showMessageDialog(null, "Codice Corretto ", "ERROR!", JOptionPane.INFORMATION_MESSAGE);
					bancomat.schermata_iniziale();
					}
					
					
				
					case 2: JOptionPane.showMessageDialog(null, "Codice Non Corretto", "", JOptionPane.INFORMATION_MESSAGE); 
					String sicurezza1=JOptionPane.showInputDialog(codice);
					if (sicurezza1.equals(confermaCodice))
					{
					JOptionPane.showMessageDialog(null, "Codice Corretto ", "ERROR!", JOptionPane.INFORMATION_MESSAGE);
					bancomat.schermata_iniziale();
					}//if
					case 3: return;
					
				} 	
				
				}
				
				
				
		}
}

}//class bancomat


class test_bancomat
{
	public static void main(String[] args) throws Exception
	{
	bancomat.password();

	

	
	
	}//main
}//class test_bancomat
