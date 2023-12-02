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
			terreno.imprimirDatosFinales();
			terreno.imprimirDatosTerreno();
			juego(terreno);
			System.out.println("Desea salir?");
			end = sc.next();
		}while (!end.equals("si"));
	}
	public static void juego(Mapa terreno){
		Scanner scanner = new Scanner(System.in);
		char in;
		Ejercito e1 = terreno.getEjercito1();
		Ejercito e2 = terreno.getEjercito2();
		int turno = 0;
		System.out.println("¡Bienvenido al videojuego!");
		terreno.imprimirMapa();
		do{
			if(turno%2==0){
				System.out.println("\u001B[32m" + "Turno del reino 1 (#) "+ "\u001B[0m");
				//Comprobar los indices
				int x=0, y=0, tox=0, toy=0;
				do{
					System.out.println("Ingrese los valores del soldado que desea mover");
					System.out.print("Ingrese la fila: ");
					x = scanner.nextInt();
					System.out.print("Ingrese la columna: ");
					in = scanner.next().charAt(0);
					y = Character.compare(in, 'A') + 1;

					System.out.println("Ingrese los valores del destino");
					System.out.print("Ingrese la fila: ");
					tox = scanner.nextInt();
					System.out.print("Ingrese la columna: ");
					in = scanner.next().charAt(0);
					toy = Character.compare(in, 'A') + 1;
					x--;y--;tox--;toy--;
				}while(Ejercito.validar(e1, e2, x,y,tox,toy));
				Ejercito.mover(e1, e2, x,y,tox, toy);
			}else{
				System.out.println("\u001B[31m" +"Turno del reino 2 (*) " + "\u001B[0m");
				int x=0, y=0, tox=0, toy=0;
				do{
					System.out.println("Ingrese los valores del soldado que desea mover");
					System.out.print("Ingrese la fila: ");
					x = scanner.nextInt();
					System.out.print("Ingrese la columna: ");
					in = scanner.next().charAt(0);
					y = Character.compare(in, 'A') + 1;

					System.out.println("Ingrese los valores del destino");
					System.out.print("Ingrese la fila: ");
					tox = scanner.nextInt();
					System.out.print("Ingrese la columna: ");
					in = scanner.next().charAt(0);
					toy = Character.compare(in, 'A') + 1;					
					x--;y--;tox--;toy--;
				}while(Ejercito.validar(e2, e1, x,y,tox,toy));
				Ejercito.mover(e2, e1, x,y,tox, toy);
			}
			terreno.genTablero();
			terreno.imprimirMapa();

			System.out.println("\u001B[32m"+e1 +"\u001B[0m");
			System.out.println("\u001B[31m"+e2 +"\u001B[0m");
			turno++;
		}while(Ejercito.winner(e1, e2));
	}
}


