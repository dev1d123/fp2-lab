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
		System.out.println("#: " + ejercito1.length);
		System.out.println("$: " + ejercito2.length);
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
		//Las condiciones de victoria ahora seran, las siguientes, cuando ya no queden movimientos posibles de ataques se determina al ganador
		//por el promedio de vida de los soldados restantes.
		//Se intenta simular algunas acciones del juego.

		//El juego es por turnos.
		System.out.println("---------------------------------------------------");
		int aux = 0; //nos sirve para determinar que turno es.
		while(true){
			if(aux%2 == 0){
				//turno del jugador
				System.out.println("TURNO DEL EJERCITO #");
				String entrada = "";
				int accion = 0;
				do{
					System.out.print("Ingrese el nombre del soldado que desea mover: ");
					entrada = sc.next();
					System.out.println("Acciones disponibles para el soldado:");
					System.out.println("1. Atacar");
					System.out.println("2. Defender");
					System.out.println("3. Avanzar");
					System.out.println("4. Retroceder");
					System.out.println("5. Huir");
					System.out.print("Elije una acción (1-5): ");
					accion = sc.nextInt();
				}while(!esValido(entrada ,ejercito1, accion));
				//realizando accion.
				Soldado soldadoSelec = getSoldado(entrada, ejercito1);
				System.out.println("El soldado a mover es ");
				System.out.println(soldadoSelec);
				switch (accion){
					case 1: soldadoSelec.atacar(ejercito1, ejercito2);
						break;
					case 2: soldadoSelec.defender();
						break;
					case 3: soldadoSelec.avanzar(ejercito1, ejercito2);
						break;
					case 4: soldadoSelec.retroceder(ejercito1);
						break;
					case 5: soldadoSelec.huir();
						break;
				}
				//imprimir el tablero modificado.
				inicializar(tablero);
				genTablero(tablero, ejercito1, ejercito2);
				imprimirTablero(tablero);

			}else{
				//turno del enemigo
				System.out.println("TURNO DEL EJERCITO $");
				String entrada = "";
				int accion = 0;
				do{
					System.out.print("Ingrese el nombre del soldado que desea mover: ");
					entrada = sc.next();
					System.out.println("Acciones disponibles para el soldado:");
					System.out.println("1. Atacar");
					System.out.println("2. Defender");
					System.out.println("3. Avanzar");
					System.out.println("4. Retroceder");
					System.out.println("5. Huir");
					System.out.print("Elije una acción (1-5): ");
					accion = sc.nextInt();
				}while(!esValido(entrada ,ejercito2, accion));
				//realizando accion.
				Soldado soldadoSelec = getSoldado(entrada, ejercito2);
				System.out.println("El soldado a mover es ");
				System.out.println(soldadoSelec);
				switch (accion){
					case 1: soldadoSelec.atacar(ejercito2, ejercito1);
						break;
					case 2: soldadoSelec.defender();
						break;
					case 3: soldadoSelec.avanzar(ejercito2, ejercito1);
						break;
					case 4: soldadoSelec.retroceder(ejercito2);
						break;
					case 5: soldadoSelec.huir();
						break;
				}
				//imprimir el tablero modificado.
				inicializar(tablero);
				genTablero(tablero, ejercito1, ejercito2);
				imprimirTablero(tablero);
			}	

			//determimanos si ya no hay movimientos de ataque posible (si solo hay soldados de un solo tipo en una columna).
			//realizamos tal metodo con el tablero, por cuestiones de facilidad
			if(winner(tablero, ejercito1, ejercito2)){
				break;
			}
			aux++;		
		}
		//Conclusiones, a pesar de haber algunos errores, y la implementacion del videjuego en versiones primitivas
		//se obtuvo la mayor parte de este, es necesario mejorar los metodos avanzar y retroceder para que sean
		//mucho mas eficientes en su funcion, pues existen casos que por cuestion de tiempo no fueron abordados.
	}
	public static Soldado[] genEjercito(int w, boolean[][] posOcupadas){
		int num = (int)(Math.random()*10 + 1);
		Soldado[] e = new Soldado[num];
		for(int i = 0; i < num; i++){
			String nombre = "Soldado"+i+"X"+w;
			int vida = (int)(Math.random()*5 + 1);
			int fila = 0;
			int columna = 0;
			do{
				fila = (int)(Math.random()*10);
				columna = (int)(Math.random()*10);
			}while(posOcupadas[fila][columna]);

			posOcupadas[fila][columna] = true;
			//esta vez, fila y columna seran dos enteros.
			Soldado s = new Soldado(nombre, vida, fila, columna);
			s.setNivelAtaque((int)(Math.random()*5 + 1));
			s.setNivelDefensa((int)(Math.random()*5 + 1));
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
			if(s.getVidaActual() > 0){
				tablero[fila][columna] = '#';
			}
		}
		//para el ejercito 2, se usa el caracter $.
		for(Soldado s: e2){
			int fila = s.getFila();
			int columna = s.getColumna();
			//Se añade la condicional para que no se generen "soldados muertos"
			if(s.getVidaActual() > 0){
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
	public static boolean winner(char[][] tablero, Soldado[] e1, Soldado[]e2){
		//comprobamos si hay movimientos disponibles
		boolean condition = true;
		for(int c = 0; c < 10; c++){
			//nos ayudamos con un hashmap para cada columna
			Map<Character, Integer> carac = new HashMap<Character, Integer>();
			carac.put('$', 0);
			carac.put('#', 0);
			for(int f = 0; f < 10; f++){
				if(tablero[f][c] != '.'){
					carac.put(tablero[f][c], carac.get(tablero[f][c]) + 1);
				}	
			}
			condition = condition && (carac.get('#') == 0 || carac.get('$') == 0);
		}
		if(condition){
			//Gana el ejercito que tenga el mayor promedio de vida.
			if(promedioVida(e1) > promedioVida(e2)){
				System.out.println("El ejercito # gana la batalla");
			}else if(promedioVida(e1) < promedioVida(e2)){
				System.out.println("El ejercito * gana la batalla");
			}else{
				System.out.println("Empate");
			}
			return true;
		}else{
			System.out.println("El juego continua");
			return false;
		}
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
	public static boolean esValido(String nombre, Soldado[] ejercito, int accion) {
	    // Verificar si la acción está en el rango permitido (1 a 5)
	    if (accion < 1 || accion > 5) {
			System.out.println("Accion no valida");
			return false;
	    }

	    // Buscar el soldado por nombre en el ejército
	    Soldado soldadoSeleccionado = null;
	    for (Soldado soldado : ejercito) {
			if (nombre.equals(soldado.getNombre())) {
		    	soldadoSeleccionado = soldado;
		    	break; // Salir del bucle una vez que se encuentre el soldado
			}
	    }

	    // Validar si el soldado existe en el ejército
	    if (soldadoSeleccionado == null) {
			System.out.println("¡Ese soldado no existe!");
			return false;
	    }

	    // Validar si el soldado está vivo
	    if (soldadoSeleccionado.getVidaActual() <= 0) {
			System.out.println("¡Ese soldado está muerto!");
			return false;
	    }

	    // Si todas las condiciones se cumplen, la acción es válida
	    return true;
	}

    public static Soldado getSoldado(String name, Soldado[] e){
	for(Soldado s: e){
		if(name.equals(s.getNombre())){
			return s;
		}
	}
		//nunca llega a este null, pues los datos ya esta validados.
		return null;
    }
    public static void inicializar(char [][] tablero){
		for(int i = 0; i < 10; i++){
			for(int j = 0; j < 10; j++){
				tablero[i][j] = '.';
			}
		}
    }
}
