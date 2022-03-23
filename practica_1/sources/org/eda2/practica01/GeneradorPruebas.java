package org.eda2.practica01;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class GeneradorPruebas {
	
	public static String cabecera="#;SeasonStart;PlayerName;PlayerSalary;Pos;Age;Tm;FG%;PTS";
	
	private static String filePath = System.getProperty("user.dir")
			+File.separator+"src"
			+File.separator+"org"
			+File.separator+"eda2"
			+File.separator+"practica01"
			+File.separator;
	

	public static void main(String[] args) throws FileNotFoundException {
		// TODO Auto-generated method stub
		jugadoresAcendente(1000);
		}


	private static void jugadoresAcendente(long N) throws FileNotFoundException {
		PrintWriter pw= new PrintWriter(new File(filePath+"Prueba.csv"));
		pw.println(cabecera);
		for (int i = 0; i < N; i++) {
			pw.println(";;Jugador " +i+";;;;;100;"+i);
	}
		pw.close();

}
}
