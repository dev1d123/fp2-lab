import java.util.*;
public class VideoJuego5{
	public static void main(String[] args){
		//el tablero sera un arreglo bidimensional de strings.
		Scanner sc = new Scanner(System.in);
		String respuesta = "";
		do{
			String[][] tablero = new String[10][10];
			for(int i = 0 ; i < 10; i++){
				for(int j = 0 ; j < 10; j++){
					tablero[i][j] = ".";
				}
			}
			boolean[][] posOcupadas = new boolean[10][10];	
			Map<Integer,Soldado> ejercito1;
			ejercito1 = genEjercito(1, posOcupadas);
			Map<Integer,Soldado> ejercito2;
			ejercito2 = genEjercito(2, posOcupadas);
			genTablero(tablero, ejercito1, ejercito2);
			imprimir(tablero);	
			System.out.println("Mostrando al soldado con mayor vida del ejercito (1)");
			System.out.println(mayorVida(ejercito1));
			System.out.println("Mostrando al soldado con mayor vida del ejercito [2]");
			System.out.println(mayorVida(ejercito2));
			System.out.println("Mostrando el promedio vida del ejercito [2]: " + promedioVida(ejercito1));
			System.out.println("Mostrando el promedio de vida del ejercito [2]: " + promedioVida(ejercito2));
			
			System.out.println("Imprimiendo al ejercito (1), por creacion");
			imprimirCreacion(ejercito1);
			System.out.println("Imprimiendo al ejercito [2], por creacion");
			imprimirCreacion(ejercito2);
			System.out.print("¿Deseas simular el juego otra vez? (Sí/No): ");
            respuesta = sc.next().toLowerCase();
		}while(respuesta.equals("si"));
		System.out.println("Adios");
		
	}
	public static void genTablero(String[][] tablero, Map<Integer, Soldado> e1, Map<Integer, Soldado> e2){
		//Se genera el tablero con los dos mapas (no se utilizo la matriz de booleanos)
		//para el ejercito 1 ().
		char[] colToNum = {'A','B','C','D','E','F','G','H','I','J'};
		Map<Character, Integer> col = new HashMap<Character, Integer>();
		int aux = 0;
		for(char c: colToNum){
			col.put(c, aux);
			aux++;
		}
		for(Map.Entry<Integer, Soldado> pair: e1.entrySet()){
			int fila = pair.getValue().getFila();
			char colChar = pair.getValue().getColumna();
			int columna = col.get(colChar);
			int vida = pair.getValue().getVida(); 
			tablero[fila][columna] = "("+vida+")";
		}
		for(Map.Entry<Integer, Soldado> pair: e2.entrySet()){
			int fila = pair.getValue().getFila();
			char colChar	= pair.getValue().getColumna();
			int columna = col.get(colChar);
			int vida = pair.getValue().getVida(); 
			tablero[fila][columna] = "["+vida+"]";
		}
	}
	//w es el ejercito, el cual puede ser 1 o 2.
	public static Map<Integer, Soldado> genEjercito(int w, boolean[][] posOcupadas){
		//se genera el numero aleatorio
		int n = (int)(Math.random() * 10 + 1);
		Map<Integer,Soldado> e = new HashMap<Integer, Soldado>();
		char[] numToCol = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J'};
		for(int i = 0 ;  i < n; i++){
			//se autogeneran los valores de un soldado
			int vida = (int)(Math.random()*5 + 1);
			String nombre = "Soldado" + i + "X" + w;
			//se generan los valores de fila y columna del 1-9
			//para evitar errores con los indices
			int fila;
			int columna;
			do{
				fila = (int)(Math.random()* 10);
				columna = (int)(Math.random()* 10);
			}while(posOcupadas[fila][columna]);
			posOcupadas[fila][columna] = true;
			//ahora creamos el objeto soldado y lo agregamos al mapa.
			//las llaves seran los numeros de creacion, pues estos no pueden
			//repetirse, otra opcion pudo ser utilizar los nombres como llaves.
			Soldado s = new Soldado(nombre, vida, fila, numToCol[columna]);
			e.put(i, s);
		}
		return e;	
	}
	public static void imprimir(String[][] arr){
		char[] numToCol = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J'};
		
		for(char c: numToCol){
			System.out.print("\t"+c);	
		}

		System.out.println();	
		for(int i = 0 ; i < arr.length; i++){
			System.out.print((i+1)+"\t");	
			for(int j = 0; j < arr[i].length; j++){
				System.out.print(arr[i][j] + "\t");	
			}
			System.out.println();
		}
	}
	
	public static Soldado mayorVida(Map<Integer, Soldado> ejercito) {
		int mayorVida = 0;
		int key = 0;
			for (Map.Entry<Integer, Soldado> entry : ejercito.entrySet()) {
				int vidaSoldado = entry.getValue().getVida();
				if (vidaSoldado > mayorVida) {
					mayorVida = vidaSoldado;
					key = entry.getKey();
				}
			}
		return ejercito.get(key);
	}
	public static double promedioVida(Map<Integer, Soldado> ejercito) {
		double sumaVida = 0;
		double size = 0;
			for (Map.Entry<Integer, Soldado> entry : ejercito.entrySet()) {
				sumaVida += entry.getValue().getVida();
				size++;
			}
		return sumaVida/size;
	}
	public static void imprimirCreacion(Map<Integer, Soldado> ejercito) {
		for(int i = 0; i < ejercito.size(); i++){
			System.out.println(ejercito.get(i));
		}
	}


}
