import java.util.*;
public class Videojuego{
	public static int puntajeInglaterra = 0;
	public static int puntajeFrancia = 0;

	public static void main(String[] args){
		boolean[][] posiciones = new boolean[10][10];
		Soldado[] inglaterra = genArreglo(posiciones, "Inglaterra", true);
		Soldado[] francia = genArreglo(posiciones, "Francia", true);
		iniciarJuego(inglaterra, francia);
	}
	public static Soldado[] genArreglo(boolean[][] posiciones, String bando, boolean esReino) {
		int num = (int)(Math.random() * 9 + 1);
        Soldado[] arreglo = new Soldado[num];
        for (int i = 0; i < num; i++) {
			int fila = 0;
			int columna = 0;
			do{
            	fila = (int)(Math.random() * 9 + 1);
            	columna = (int)(Math.random() * 9 + 1);
			}while(posiciones[fila][columna]);
			posiciones[fila][columna] = true;
			String nombre;
			if(esReino){
				nombre = bando + "" + (i+1);
			}else{
            	nombre = "Soldado" + i + "x" + bando;
			}
            Soldado e = new Soldado(nombre, fila, columna);
			arreglo[i] = e;
        }
        return arreglo;
    }

	//recibe dos arreglos.
	public static void genTablero(Soldado[] arr1, Soldado[] arr2){
		char[][]tablero = new char[10][10];
		//inicializamos el tablero.
		for(int i = 0; i < 10; i++){
			for(int j = 0; j < 10; j++){
				tablero[i][j] = '*';
			}
		}	
		//ubicamos al primer ejercito
		for(int i = 0; i < arr1.length; i++){
			if(arr1[i].estaVivo()){

				int fila = arr1[i].getFila();
				int columna = arr1[i].getColumna();
				tablero[fila][columna] = 'I';
			}
		}
		for(int i = 0; i < arr2.length; i++){
			if(arr2[i].estaVivo()){
				int fila = arr2[i].getFila();
				int columna = arr2[i].getColumna();
				tablero[fila][columna] = 'F';
		
			}	
		}
		//impresion del tablero
		char[] aux = {'A','B','C','D','E','F','G','H','I', 'J'};
		for(char c: aux){
			System.out.print("\t"+c);
		}
		System.out.println();
		for(int i = 0; i < 10; i++){
			System.out.print(i+1 +"\t");
			for(int j = 0; j < 10; j++){
				System.out.print(tablero[i][j] + "\t");
			}
			System.out.println();
		}
	}

	public static void iniciarJuego(Soldado[] inglaterra, Soldado[] francia){
		Scanner sc = new Scanner(System.in);
		System.out.println("Bienvenido...");
		genTablero(inglaterra, francia);
		int aux = 0;
		while(true){
			int filaInicial = -1;
			int columnaInicial = -1;
			int filaFinal = -1;
			int columnaFinal = -1;
			if(aux%2 == 0){
				//turno de inglaterra
				System.out.println("Turno de Inglaterra");
				System.out.println("Indique las coordenadas del Ejercito a mover: ");
				filaInicial = sc.nextInt() - 1;
				columnaInicial = sc.nextInt() - 1;
				System.out.println("Indique las coordenadas del objetivo: ");
				filaFinal = sc.nextInt() - 1;
				columnaFinal = sc.nextInt() - 1;
				moverEjercito(inglaterra, francia, filaInicial, columnaInicial, filaFinal, columnaFinal);
			}else{
				//turno de francia
				System.out.println("Turno de Francia");
				System.out.println("Indique las coordenadas del Ejercito a mover: ");
				filaInicial = sc.nextInt() - 1;
				columnaInicial = sc.nextInt() - 1;
				System.out.println("Indique las coordenadas del objetivo: ");
				filaFinal = sc.nextInt() - 1;
				columnaFinal = sc.nextInt() - 1;
				moverEjercito(francia, inglaterra, filaInicial, columnaInicial, filaFinal, columnaFinal);

			}
			genTablero(inglaterra, francia);
			if(winner(inglaterra, francia)){
				break;
			}
			aux++;
		}
	}

	public static void moverEjercito(Soldado[] amigo, Soldado[] enemigo, int x, int y, int tox, int toy){
		Soldado ejercitoMover = buscar(amigo, x, y);
		if(buscar(enemigo, tox, toy) == null){
			ejercitoMover.setFila(tox);
			ejercitoMover.setColumna(toy);
		}else{
			Soldado ene = buscar(enemigo, tox, toy);
			System.out.println("Dos ejercitos se encontraron, se libera una batalla!!!");
			System.out.println(ejercitoMover.getNombre() + " vs "+ ene.getNombre());
			
			batallaSoldados();
			ejercitoMover.morir();
			ene.morir();
			System.out.println("Puntaje del Reino de Francia: " + puntajeFrancia);
			System.out.println("Puntaje del Reino de Inglaterra: " + puntajeInglaterra);
		}
	}
	public static Soldado buscar(Soldado[] reino, int x, int y){
		for(int i = 0; i < reino.length; i++){
			if(reino[i].estaVivo() && reino[i].getFila() == x && reino[i].getColumna() == y){
				return reino[i];
			}
		}
		return null;
	}
	public static void batallaSoldados(){
		System.out.println("Generando campo de batalla...");
		boolean[][] posicionesEjercito = new boolean[10][10];
		Soldado[] ejercitoIng = genArreglo(posicionesEjercito, "Inglaterra", false);
		Soldado[] ejercitoFra = genArreglo(posicionesEjercito, "Francia", false);
		//juegoRapido...
		System.out.println("\n");
		genTablero(ejercitoIng, ejercitoFra);
		//La metrica de ganador es determinada por los valores totales de vida
		//Hay un score por cada ejercito este es determinado de la siguiente manera vida + defensa, y luego se halla el daño total
		//el ganador es determinado por la mayor cantidad de vida restante luego de una resta.
		int score1 = 0;
		int damage1 = 0;
		int score2 = 0;
		int damage2 = 0;
		for(Soldado e: ejercitoIng){
			score1 += e.getVidaActual() + e.getNivelDefensa();
			damage1 += e.getNivelAtaque();
		}
		for(Soldado e: ejercitoFra){
			score2 += e.getVidaActual() + e.getNivelDefensa();
			damage2 += e.getNivelAtaque();
		}
		System.out.println("La cantidad de soldados de Inglaterra es " + ejercitoIng.length);
		System.out.println("La puntuacion de Inglaterra es " + score1);
		System.out.println("El daño de Inglaterra es " + damage1);
		
		System.out.println("La cantidad de soldados de Francia es " + ejercitoFra.length);
		System.out.println("La puntuacion de Francia es " + score2);
		System.out.println("El daño de Francia es " + damage2);
		score1 -= damage2;
		score2 -= damage1;
		if(score1 < score2){
			System.out.println("Gano el ejercito de Francia");
			puntajeFrancia++;
		}else if(score2 < score1){
			System.out.println("Gano el ejercito de Inglaterra");
			puntajeInglaterra++;
		}else{
			System.out.println("Empate");
		}
	}
	public static boolean winner(Soldado[] e1, Soldado[] e2){
		boolean vivosE1 = false;
		boolean vivosE2 = false;
		for(Soldado e: e1){
			if(e.estaVivo()){
				vivosE1 = true;
			}
		}
		for(Soldado e: e2){
			if(e.estaVivo()){
				vivosE2 = true;
			}
		}
		if(vivosE1 && !vivosE2){
			System.out.println("Gana el reino de Inglaterra");
			return true;
		}else if(!vivosE1 && vivosE2){
			System.out.println("Gana el reino de Francia");
			return true;
		}else if(!vivosE1 && !vivosE2){
			if(puntajeFrancia > puntajeInglaterra){
				System.out.println("Gana el reino de Francia");
			}else if(puntajeInglaterra > puntajeFrancia){
				System.out.println("Gana el reino de Inglaterra");
			}else{
				System.out.println("Empate");
			}
			return true;
		}
		return false;
	}
}
