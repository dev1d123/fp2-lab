import java.util.*;
public class E1{
	public static void main(String[] args){
		Scanner input = new Scanner(System.in);
		String res ="";
		do{
			juego();
			System.out.println("Desea simular otra batalla: ");
			res = input.next();
		}while(res.equals("si"));
	}
	public static void juego(){
		Scanner sc = new Scanner(System.in);
		//el tablero sera un matriz de caracteres
		char[][] tablero = new char[10][10];
		//lo inicialiazmos con puntos
		inicializar(tablero);
		//los dos ejercitos seran arreglos de Soldados
		boolean[][] posOcupadas = new boolean[10][10];
		Soldado[] ejercito1;
		ejercito1 = genEjercito(1, posOcupadas);
		Soldado[] ejercito2;
		ejercito2 = genEjercito(2, posOcupadas);
		genTablero(tablero, ejercito1, ejercito2);
		imprimirTablero(tablero);

		System.out.println("Soldado con mayor vida del ejercito #");
		System.out.println(soldadoMayorVida(ejercito1));
		System.out.println("Soldado con mayor vida del ejercito $");
		System.out.println(soldadoMayorVida(ejercito2));
		System.out.print("Promedio de vida del ejercito #: ");
		System.out.println(promedioVida(ejercito1));
		System.out.print("Promedio de vida del ejercito $: ");
		System.out.println(promedioVida(ejercito2));
		System.out.println("Datos en orden del ejercito #: ");
		imprimirOrden(ejercito1);
		System.out.println("Datos en orden del ejercito $: ");
		imprimirOrden(ejercito2);
		System.out.println("BubbleSort");
		System.out.println("Mostrando ranking de los soldados del ejercito #: ");
		ordenarRankingBubble(ejercito1);
		mostrar(ejercito1);
		System.out.println("Mostrando ranking de los soldados del ejercito $: ");
		ordenarRankingBubble(ejercito2);
		mostrar(ejercito2);
		System.out.println("SelectionSort");
		System.out.println("Mostrando ranking de los soldados del ejercito #: ");
		ordenarRankingSelection(ejercito1);
		mostrar(ejercito1);
		System.out.println("Mostrando ranking de los soldados del ejercito $: ");
		ordenarRankingSelection(ejercito2);
		mostrar(ejercito2);
		System.out.println("------------------------------------------------------------------");
		int aux = 0; //nos sirve para determinar que turno es.
		//se crea un mapa para habilitar la entrada de coordenadas en char
		Map<Character, Integer> m = new HashMap<Character, Integer>();
		m.put('A', 1);
		m.put('B', 2);
		m.put('C', 3);
		m.put('D', 4);
		m.put('E', 5);
		m.put('F', 6);
		m.put('G', 7);
		m.put('H', 8);
		m.put('I', 9);
		m.put('J', 10);
		imprimirTablero(tablero);
		//Todavia no se implemento la comprobacion de codigo al salir del tablero...
		while(true){
			if(aux%2 == 0){
				System.out.println("TURNO DEL EJERCITO #");
				int axisX;
				int axisY;
				int toaxisX;
				int toaxisY;
				System.out.print("Ingrese las coordenadas del soldado que desea mover: ");
				axisX = sc.nextInt() - 1;
				char help = sc.next().charAt(0);
				axisY = m.get(help) - 1;
				//realizando accion.
				System.out.println("El soldado a mover es ");
				Soldado soldadoSelec = findSoldado(axisX, axisY, ejercito1);
				System.out.println(soldadoSelec);
				System.out.println("Ahora ingrese las coordenadas de direccion");
				toaxisX = sc.nextInt() - 1;
				help = sc.next().charAt(0);
				toaxisY = m.get(help) - 1;
				//realizando movimiento

				moverSoldado(ejercito1, ejercito2, soldadoSelec, axisX, axisY, toaxisX, toaxisY);

				//imprimir el tablero modificado.

				inicializar(tablero);
				genTablero(tablero, ejercito1, ejercito2);
				imprimirTablero(tablero);

			}else{
				System.out.println("TURNO DEL EJERCITO $");
				int axisX;
				int axisY;
				int toaxisX;
				int toaxisY;
				System.out.print("Ingrese las coordenadas del soldado que desea mover: ");
				axisX = sc.nextInt() - 1;
				char help = sc.next().charAt(0);
				axisY = m.get(help) - 1;
				//realizando accion.
				System.out.println("El soldado a mover es ");
				Soldado soldadoSelec = findSoldado(axisX, axisY, ejercito2);
				System.out.println(soldadoSelec);
				System.out.println("Ahora ingrese las coordenadas de direccion");
				toaxisX = sc.nextInt() - 1;
				help = sc.next().charAt(0);
				toaxisY = m.get(help) - 1;
				//realizando movimiento

				moverSoldado(ejercito2, ejercito1, soldadoSelec, axisX, axisY, toaxisX, toaxisY);
				//imprimir el tablero modificado.
				inicializar(tablero);
				genTablero(tablero, ejercito1, ejercito2);
				imprimirTablero(tablero);
			}	

			if(winner(tablero, ejercito1, ejercito2)){
				break;
			}
			aux++;		
		}
	}
	public static Soldado[] genEjercito(int w, boolean[][] posOcupadas){
		int num = (int)(Math.random()*10 + 1);
		Soldado[] e = new Soldado[num];
		for(int i = 0; i < num; i++){
			String nombre = "Soldado"+i+"X"+w;
			int fila = 0;
			int columna = 0;
			do{
				fila = (int)(Math.random()*10);
				columna = (int)(Math.random()*10);
			}while(posOcupadas[fila][columna]);

			posOcupadas[fila][columna] = true;
			//esta vez, fila y columna seran dos enteros.
			Soldado s = new Soldado(nombre, fila, columna);
			e[i] = s;
		}	
		return e;
	}
	
	public static void genTablero(char[][] tablero, Soldado[] e1, Soldado[] e2){
		//para el ejercito 1, se usa el caracter #.
		for(Soldado s: e1){
			int fila = s.getFila();
			int columna = s.getColumna();
			//Se añade la condicional para que no se generen "soldados muertos"
			if(s.estaVivo()){
				tablero[fila][columna] = '#';
			}
		}
		//para el ejercito 2, se usa el caracter $.
		for(Soldado s: e2){
			int fila = s.getFila();
			int columna = s.getColumna();
			//Se añade la condicional para que no se generen "soldados muertos"
			if(s.estaVivo()){
				tablero[fila][columna] = '$';
			}
		}
	}
	public static void imprimirTablero(char[][] tablero){
		char[] aux = {'A','B','C','D','E','F','G','H','I','J'};
		for(char c: aux){
			System.out.print("\t"+c);
		}
		System.out.println();
		for(int i = 0; i < 10; i++){
			System.out.print(i+1 + "\t");
			for(int j = 0; j < 10; j++){
				System.out.print(tablero[i][j]+"\t");
			}
			System.out.println();
		}
	}
	public static Soldado soldadoMayorVida(Soldado[] e){
    		int max = 0;
		int index = 0;
		for(int i = 0; i < e.length; i++){
			max = Math.max(max, e[i].getVidaActual());
			if(max == e[i].getVidaActual()){
				index = i;
			}
		}
		return e[index];
    }
   	 public static double promedioVida(Soldado[] e){
    		double sumVida = 0;
			for(Soldado s: e){
				sumVida += s.getVidaActual();
		}

		return sumVida/e.length;
    }
	public static void imprimirOrden(Soldado[] e){
		for(Soldado s: e){
			System.out.println(s);
			
		}
	}
	public static boolean winner(char[][] tablero, Soldado[] e1, Soldado[] e2){
    	boolean vivosE1 = false;
    	boolean vivosE2 = false;

    	for (Soldado s : e1) {
        	if (s.estaVivo()) {
            	vivosE1 = true;
            	break; 
        	}
    	}

    	for (Soldado s : e2) {
        	if (s.estaVivo()) {
            	vivosE2 = true;
            	break; 
        	}
    	}

    	if (vivosE1 && !vivosE2) {
        	System.out.println("El ejército # gana la batalla");
    	} else if (!vivosE1 && vivosE2) {
        	System.out.println("El ejército $ gana la batalla");
    	}
    	return !vivosE1 || !vivosE2;
	}

    public static void ordenarRankingBubble(Soldado[] e) {
        int n = e.length;
        for (int i = 0; i < n; i++) {
			for(int j = 0; j < n - 1- i; j++){
				if (e[j].getVidaActual() < e[j+1].getVidaActual()) {
				   Soldado temp = e[j];
				   e[j] = e[j+1];
				   e[j+1] = temp;
				}
			}		
        }
    }

    public static void mostrar(Soldado[] e) {
        for (int i = 0; i < e.length; i++) {
            System.out.println(e[i]);
        }
    }

    public static void ordenarRankingSelection(Soldado[] e) {
        int n = e.length;
        for (int i = 0; i < n - 1; i++) {
            int maxIndex = i;
            for (int j = i + 1; j < n; j++) {
                if (e[j].getVidaActual() > e[maxIndex].getVidaActual()) {
                    maxIndex = j;
                }
            }
            Soldado temp = e[maxIndex];
            e[maxIndex] = e[i];
            e[i] = temp;
        }
    }
    public static Soldado findSoldado(int axisX, int axisY, Soldado[] e){
		for(Soldado s: e){
			if(s.getFila() == axisX && s.getColumna() == axisY && s.estaVivo()){
				return s;
			}
		}

		//nunca llega a este null, pues los datos ya estan validados.
		return null;
    }
    public static void inicializar(char [][] tablero){
		for(int i = 0; i < 10; i++){
			for(int j = 0; j < 10; j++){
				tablero[i][j] = '.';
			}
		}
    }
    public static void moverSoldado(Soldado[] aliado, Soldado[] enemigo, Soldado m, int x, int y, int toX, int toY){
    	//Este metodo mueve el soldado m ()
		//comprobamos primero si las columnas a mover coinciden con un aliado o enemigo
		boolean isFriend = false;
		boolean isEnemy = false;
		//IMPORTANTE: Asegurarse que el soldado este vivo
		for(Soldado s: aliado){
			if(s.getFila() == toX && s.getColumna() == toY && s.estaVivo()){
				isFriend = true;
			}
		}
		Soldado enem = null;
		for(Soldado s: enemigo){
			if(s.getFila() == toX && s.getColumna() == toY && s.estaVivo()){
				enem = s;
				isEnemy = true;
			}
		}

		if(isFriend){
			System.out.println("Existe un aliado en esa posicion, no se puede mover");
		}else if(isEnemy){
			System.out.println("Batalla! -> " + m.getNombre() + " vs " + enem.getNombre());
			batalla(m, enem, toX, toY);
		}else{
			//el soldado solo se mueve
			System.out.println("El soldado se mueve!!");
			m.setFila(toX);
			m.setColumna(toY);
		}
	
    }
    public static void batalla(Soldado amigo, Soldado enemigo, int x, int y){
		int vidaTotal = amigo.getVidaActual() + enemigo.getVidaActual();
		//se genera un numero entre 0 - vidaTotal
		//Si el numero esta entre 0 y vidaAmigo, gana el amigo; caso contrario gana el enemigo
		int prob = (int)(Math.random() * vidaTotal + 1);
    	if(prob < amigo.getVidaActual()){
			System.out.println("Gana el soldado " + amigo.getNombre());
			enemigo.morir();
			amigo.setFila(x);
			amigo.setColumna(y);
		}else if(prob < vidaTotal){
			System.out.println("Gana el soldado " + enemigo.getNombre());
			amigo.morir();
			//no es necesario cambiar posiciones ya que amigo es el que se mueve
		}else{
			int ale = (int)(Math.random() * 2);
			if(ale == 1){
				System.out.println("Gana el soldado " + amigo.getNombre());
				enemigo.morir();
				amigo.setFila(x);
				amigo.setColumna(y);
			}else{
				System.out.println("Gana el soldado " + enemigo.getNombre());
				amigo.morir();
		}
	}
	System.out.println("Probabilidades de victoria");
	System.out.println(amigo.getNombre() + ": " + (double)(amigo.getVidaActual()/(vidaTotal + 0.0)) + "%");
	System.out.println(enemigo.getNombre() + ": " + (double)(enemigo.getVidaActual()/(vidaTotal + 0.0)) + "%");
    }
} 

