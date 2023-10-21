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
		imprimirTablero(tablero);
		while(true){
			if(aux%2 == 0){
				System.out.println("TURNO DEL EJERCITO #");
				int axisX;
				int axisY;
				int toaxisX;
				int toaxisY;
				do{	
					System.out.print("Ingrese las coordenadas del soldado que desea mover: ");
					axisX = sc.nextInt() - 1;
					axisY = sc.nextInt() - 1;
				}while(!validarOpcion(axisX, axisY, ejercito1, ejercito2));
				System.out.println("El soldado a mover es ");
				Soldado soldadoSelec = findSoldado(axisX, axisY, ejercito1);
				System.out.println(soldadoSelec);
				do{
					System.out.println("Ahora ingrese las coordenadas de direccion");
					toaxisX = sc.nextInt() - 1;
					toaxisY = sc.nextInt() - 1;
				}while(!validarDestino(toaxisX, toaxisY, ejercito1, ejercito2));
				moverSoldado(ejercito1, ejercito2, soldadoSelec, toaxisX, toaxisY);
				inicializar(tablero);
				genTablero(tablero, ejercito1, ejercito2);
				imprimirTablero(tablero);
			}else{
				System.out.println("TURNO DEL EJERCITO $");
				int axisX;
				int axisY;
				int toaxisX;
				int toaxisY;
				do{
					System.out.print("Ingrese las coordenadas del soldado que desea mover: ");
					axisX = sc.nextInt() - 1;
					axisY = sc.nextInt() - 1;
				}while(!validarOpcion(axisX, axisY, ejercito2, ejercito1));
				System.out.println("El soldado a mover es ");
				Soldado soldadoSelec = findSoldado(axisX, axisY, ejercito2);
				System.out.println(soldadoSelec);
				do{
					System.out.println("Ahora ingrese las coordenadas de direccion");
					toaxisX = sc.nextInt() - 1;
					toaxisY = sc.nextInt() - 1;
				}while(!validarDestino(toaxisX, toaxisY, ejercito2, ejercito1));
				
				moverSoldado(ejercito2, ejercito1, soldadoSelec, toaxisX, toaxisY);
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
		for(Soldado s: e1){
			int fila = s.getFila();
			int columna = s.getColumna();
			if(s.estaVivo()){
				tablero[fila][columna] = '#';
			}
		}
		for(Soldado s: e2){
			int fila = s.getFila();
			int columna = s.getColumna();
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

		return null;
    }
    public static void inicializar(char [][] tablero){
		for(int i = 0; i < 10; i++){
			for(int j = 0; j < 10; j++){
				tablero[i][j] = '.';
			}
		}
    }
    public static void moverSoldado(Soldado[] aliado, Soldado[] enemigo, Soldado m, int toX, int toY){
    	//Este metodo mueve el soldado m ()
		//Ahora los datos esta correctamente validados.
		Soldado enem = findSoldado(toX, toY, enemigo);
		if(enem == null){
			//no hay enemigos, realizando movimiento
			m.setFila(toX);
			m.setColumna(toY);
		}else{
			System.out.println("Batalla! -> " + m.getNombre() + ": "+m.getVidaActual() +" vs " + enem.getNombre()+": "+enem.getVidaActual());
			batalla(m, enem, toX, toY);
		}
	
    }

	public static void batalla(Soldado amigo, Soldado enemigo, int x, int y) {
    	int vidaTotal = amigo.getVidaActual() + enemigo.getVidaActual();
    
   		// Calcula las probabilidades de victoria basadas en la vida actual de los soldados
    	double probAmigo = (double)amigo.getVidaActual() / vidaTotal * 100.0;
    	double probEnemigo = (double)enemigo.getVidaActual() / vidaTotal * 100.0;

    	System.out.println("Probabilidades de victoria:");
    	System.out.println(amigo.getNombre() + ": " + probAmigo + "%");
    	System.out.println(enemigo.getNombre() + ": " + probEnemigo + "%");
	
    	// Realiza la batalla y actualiza las vidas
    	if (Math.random() * 100 < probAmigo) {
        	System.out.println("Gana el soldado " + amigo.getNombre());
        	amigo.setVidaActual(amigo.getVidaActual() + 1);
        	enemigo.morir();
        	amigo.setFila(x);
        	amigo.setColumna(y);
    	} else {
        	System.out.println("Gana el soldado " + enemigo.getNombre());
        	enemigo.setVidaActual(enemigo.getVidaActual() + 1);
        	amigo.morir();
        	// No es necesario cambiar posiciones ya que el amigo es el que se mueve
    	}
	
    }
	public static boolean validarOpcion(int x, int y, Soldado[] amigos, Soldado[] enemigos){
		if(0 <= x && x <= 9 && 0 <= y && y <= 9){
			if(findSoldado(x, y, amigos) != null){
				System.out.println("El soldado escogido es correcto");
				return true;
			}else if(findSoldado(x,y, enemigos) != null){
				System.out.println("El soldado pertenece al ejercito enemigo!. Ingrese de nuevo");
				return false;
			}else{
				System.out.println("No existe!!!");
				return false;
			}
		}else{
			System.out.println("Limites excedidos, ingrese valores adecuados!");
			return false;
		}
	
	}
	public static boolean validarDestino(int x, int y, Soldado[] amigos, Soldado[] enemigos){
		if((0 <= x && x <= 9) && (0 <= y && y <= 9)){
			if(findSoldado(x, y, amigos) != null){
				System.out.println("Hay un aliado en el destino, ingrese de nuevo.");
				return false;
			}else if(findSoldado(x,y, enemigos) != null){
				System.out.println("Soldado enemigo encontrado, iniciando batalla!!!");
				return true;
			}else{
				System.out.println("Moviendose a la ubicacion");
				return true;
			}
		}else{
			System.out.println("Limites excedidos, ingrese valores adecuados!");
			return false;
		}
	}
} 
