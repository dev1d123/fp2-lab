import java.util.*;
public class Videojuego{
	public static void main(String[] args){
		Scanner sc = new Scanner(System.in);
		String end = "";
		do{
			Mapa terreno = new Mapa();
			terreno.genTablero();
			terreno.imprimirMapa();
			System.out.println("Ejercito 1");
			System.out.println("\u001B[32m" + terreno.getEjercito1() + "\u001B[0m");
			System.out.println("Ejercito 2");
			System.out.println("\u001B[31m" +terreno.getEjercito2()+ "\u001B[0m");
			System.out.println("Ranking de poder el ejercito 1");
			System.out.println("\u001B[32m" + terreno.getEjercito1().rankingPoder() +  "\u001B[0m");
			System.out.println("Ranking de poder el ejercito 2");
			System.out.println("\u001B[31m" +terreno.getEjercito2().rankingPoder()+ "\u001B[0m");
			System.out.println("\u001B[32m" + "Ejercito 1: " + terreno.getEjercito1().soldadoMayorVida()+"\u001B[0m");
			System.out.println("\u001B[31m" + "Ejercito 2: "+terreno.getEjercito2().soldadoMayorVida()+"\u001B[0m");

			//Se usa una metrica simple basada en el promedio de vida
			ganador(terreno.getEjercito1().promedioVida(), terreno.getEjercito2().promedioVida());
			System.out.println("Desea salir?");
			end = sc.next();


		}while (!end.equals("si"));
	}
	public static void ganador(double vida1, double vida2){
		System.out.println("El promedio de vida del ejercito 1 es " + vida1);
		System.out.println("El promedio de vida del ejercito 2 es " + vida2);
		if(vida1 > vida2){
			System.out.println("El ejercito 1 gana el juego");
		}else if(vida2 > vida1){
			System.out.println("El ejercito 2 gana el juego");
		}else{
			System.out.println("Empate");
		}
		
	}
}


