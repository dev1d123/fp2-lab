import java.util.*;
public class Videojuego{
	public static void main(String[] args){
		Scanner sc = new Scanner(System.in);
		String end = "";
		do{
			Mapa terreno = new Mapa();
			terreno.genTablero();
			terreno.imprimirDatosTerreno();
			terreno.imprimirMapa();
			System.out.println("Ejercito 1");
			System.out.println("\u001B[32m" + terreno.getEjercito1().toStringArray() + "\u001B[0m");
			System.out.println("Ejercito 2");
			System.out.println("\u001B[31m" +terreno.getEjercito2()+ "\u001B[0m");
			System.out.println("\u001B[32m" + "Ejercito 1: \n " + terreno.getEjercito1().mayorVidaEjercitoArray()+"\u001B[0m");
			System.out.println("\u001B[31m" + "Ejercito 2: \n" + terreno.getEjercito2().mayorVidaEjercito()+"\u001B[0m");
			System.out.println("\u001B[32m" + "Ranking de poder ejercito 1"+"\u001B[0m");
			terreno.getEjercito1().rankingArray();
			System.out.println("\u001B[31m" + "Ranking de poder ejercito 2"+"\u001B[0m");
			terreno.getEjercito2().ranking();
			terreno.imprimirDatosFinales();
			terreno.imprimirDatosTerreno();
			ganador(terreno.getEjercito1().getVidaArray(), terreno.getEjercito2().getVida());
			System.out.println("Desea salir?");
			end = sc.next();
		}while (!end.equals("si"));
	}
    public static void ganador(int vida1, int vida2) {
        System.out.println("La suma de vida del ejército 1 es " + vida1);
        System.out.println("La suma de vida del ejército 2 es " + vida2);
        double totalVida = vida1 + vida2;
        double probabilidad = ((vida1 / totalVida) * 100);
        System.out.print("E1 (" + probabilidad + "%) vs E2(" + (100-probabilidad) + "%) ->");
		double num = Math.random()*100;
        if (num <= probabilidad) {
            System.out.println("Ganador: E1");
        } else {
            System.out.println("Ganador: E2");
        }
		System.out.println("Aleatorio generado: " + num);

    }
}


