import java.util.*;
public class E1{
	public static void main(String[] args){
		Scanner sc = new Scanner(System.in);
		int opt = -1;
		do{
			System.out.println("*******Aplicacion - Simulador de batalla********");
			System.out.println("1) Juego Rapido");
			System.out.println("2) Juego Personalizado");
			System.out.println("3) Salir");
			System.out.println("*************************************************");
			System.out.print("Ingrese su opcion ");
			opt = sc.nextInt();
			if(opt == 1){
				boolean ans = false;
				do{	
					boolean[][] posOcupadas = new boolean[10][10];
					ArrayList<Soldado> ejercito1 = genEjercito(1, posOcupadas);
					ArrayList<Soldado> ejercito2 = genEjercito(2, posOcupadas);
					char[][] tablero = new char[10][10];
					inicializar(tablero);
					genTablero(tablero, ejercito1, ejercito2);

					ans = juegoRapido(tablero, ejercito1, ejercito2);	
				}while(ans);
			}else if(opt == 2){
					boolean[][] posOcupadas = new boolean[10][10];
					ArrayList<Soldado> ejercito1 = genEjercito(1, posOcupadas);
					ArrayList<Soldado> ejercito2 = genEjercito(2, posOcupadas);
					char[][] tablero = new char[10][10];
					inicializar(tablero);
					genTablero(tablero, ejercito1, ejercito2);
					int bomb = -1;
					do{
						System.out.println("Ingrese el signo del ejercito a personalizar");
						char c = sc.next().charAt(0);
						if(c == '#'){
							bomb = personalizacion(ejercito1, ejercito2);
						}else{
							bomb = personalizacion(ejercito2, ejercito1);
						}	
					}while(bomb == 0);
					if(bomb == 1){
						boolean ans = false;
						do{	
							ans = juegoRapido(tablero, ejercito1, ejercito2);	
						}while(ans);
					}else{
						//no hacer nAda
					}
			}else if (opt == 3){
				System.out.println("Saliendo de la aplicacion");
			}else{
				System.out.println("Opcion no valida");
			}
		}while(opt != 3);
	}

	//retorna true, si el juego se termino o se reinicia
	//retorna false si quiere regresar al menu
	public static boolean juegoRapido(char[][]tablero, ArrayList<Soldado> ejercito1, ArrayList<Soldado>ejercito2){
		Scanner sc = new Scanner(System.in);
		System.out.println("------------------------------------------------------------------");
		int aux = 0; //nos sirve para determinar que turno es.
		imprimirTablero(tablero);
		while(true){
			if(aux%2 == 0){
				System.out.println("TURNO DEL EJERCITO #");
				ArrayList<Integer> opt = recibirDatos(ejercito1, ejercito2);
				if(opt.size() == 1){
					if(opt.get(0) == 1){
						return true;
					}else{
						return false;
					}
				}
				int axisX = opt.get(0);
				int axisY = opt.get(1);
				int toaxisX = opt.get(2);
				int toaxisY = opt.get(3);
				System.out.println("El soldado a mover es ");
				Soldado soldadoSelec = findSoldado(axisX, axisY, ejercito1);
				System.out.println(soldadoSelec);
				moverSoldado(ejercito1, ejercito2, soldadoSelec, toaxisX, toaxisY);
				inicializar(tablero);
				genTablero(tablero, ejercito1, ejercito2);
				imprimirTablero(tablero);
			}else{
				System.out.println("TURNO DEL EJERCITO $");
				ArrayList<Integer> opt = recibirDatos(ejercito2, ejercito1);
				if(opt.size() == 1){
					if(opt.get(0) == 1){
						return true;
					}else{
						return false;
					}
				}
				int axisX = opt.get(0);
				int axisY = opt.get(1);
				int toaxisX = opt.get(2);
				int toaxisY = opt.get(3);
				System.out.println("El soldado a mover es ");
				Soldado soldadoSelec = findSoldado(axisX, axisY, ejercito2);
				System.out.println(soldadoSelec);
				moverSoldado(ejercito2, ejercito1, soldadoSelec, toaxisX, toaxisY);
				inicializar(tablero);
				genTablero(tablero, ejercito1, ejercito2);
				imprimirTablero(tablero);
			}	
			if(winner(tablero, ejercito1, ejercito2)){
				return true;
			}
			aux++;		
		}
	}
	public static int menu2(){
		System.out.println("Que accion desea realizar?");
		System.out.println("1) Empezar un juego totalmente nuevo");
		System.out.println("2) Salir al menu");
		Scanner sc = new Scanner(System.in);
		int opcion = sc.nextInt();
		return opcion;
	}
	public static ArrayList<Soldado> genEjercito(int w, boolean[][] posOcupadas){
		int num = (int)(Math.random()*10 + 1);
		ArrayList<Soldado> e = new ArrayList<Soldado>();
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
			e.add(s);
		}	
		return e;
	}
	
	public static void genTablero(char[][] tablero, ArrayList<Soldado> e1, ArrayList<Soldado> e2){
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
	public static boolean winner(char[][] tablero, ArrayList<Soldado> e1, ArrayList<Soldado> e2){
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


    public static void mostrar(ArrayList<Soldado> e) {
        for (int i = 0; i < e.size(); i++) {
            System.out.println(e.get(i));
        }
    }

    public static Soldado findSoldado(int axisX, int axisY, ArrayList<Soldado> e){
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
    public static void moverSoldado(ArrayList<Soldado> aliado, ArrayList<Soldado> enemigo, Soldado m, int toX, int toY){
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
	public static boolean validarOpcion(int x, int y, ArrayList<Soldado> amigos, ArrayList<Soldado> enemigos){
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

	public static boolean validarDestino(int x, int y, ArrayList<Soldado> amigos, ArrayList<Soldado> enemigos){
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
	public static boolean validarEleccion(int x, int y, ArrayList<Soldado> amigos, ArrayList<Soldado> enemigos){
		//retorna true si no hay soldados
		if(0 <= x && x <= 9 && 0 <= y && y <= 9){
			if(findSoldado(x, y, amigos) == null){
				System.out.println("Posicion vacia");
				return true;
			}else if(findSoldado(x,y, enemigos) == null){
				System.out.println("Posicion vacial");
				return true;
			}else{
				System.out.println("Ocupado");
				return false;
			}
		}else{
			System.out.println("Limites excedidos, ingrese valores adecuados!");
			return false;
		}
		
	}
	//retorna un arreglo de 4, si son validos
	//retorna un arreglo de 1 con un valor de 1 si se desea jugar denuevo
	//retorna un arreglo de 1 con un valor de 2 si se desea volver al menu
	public static ArrayList<Integer> recibirDatos(ArrayList<Soldado> amigo, ArrayList<Soldado> enemigo){
		ArrayList<Integer> datos = new ArrayList<Integer>();
		Scanner sc = new Scanner(System.in);
		int axisX = 0;
		int axisY = 0;
		int toaxisX = 0;
		int toaxisY = 0;
		boolean s = false;
		do{	
			System.out.print("Ingrese las coordenadas del soldado que desea mover. Si desea cancelar el juego, escriba salir: ");
			String g = sc.nextLine();
			if(g.equals("salir")){
				s = true;
				break;
			}else{
				axisX = Integer.parseInt(g.substring(0, g.indexOf(" ")));
				axisY = Integer.parseInt(g.substring(g.indexOf(" ")+1));
				axisX--;
				axisY--;
			}
		}while(!validarOpcion(axisX, axisY, amigo, enemigo));
			if(s){
				int op = menu2();
				datos.clear();
				datos.add(op);
				return datos; 
			}
		datos.add(axisX);
		datos.add(axisY);
		s = false;
		do{
			System.out.print("Ingrese las coordenadas del destino. Si desea cancelar el juego, escriba salir: ");
			String g = sc.nextLine();
			if(g.equals("salir")){
				s = true;
				break;
			}else{
				toaxisX = Integer.parseInt(g.substring(0, g.indexOf(" ")));
				toaxisY = Integer.parseInt(g.substring(g.indexOf(" ")+1));
				toaxisX--;
				toaxisY--;
			}
		}while(!validarDestino(toaxisX, toaxisY, amigo, enemigo));
			if(s){
				int op = menu2();
				datos.clear();
				datos.add(op);
				return datos; 
			}
		datos.add(toaxisX);
		datos.add(toaxisY);
		return datos;
	
	}
	//retorna 0, si se hace la personalizacion
	//retorna 1 si se escoje jugar
	//retorna 2 si se desea volver
	public static int personalizacion(ArrayList<Soldado> e, ArrayList<Soldado> o){
		Scanner sc = new Scanner(System.in);
		System.out.println("¡Hola, estimado usuario, elija una de las siguientes opciones");
        System.out.println("Menú Principal");
        System.out.println("1) Crear Soldado");
        System.out.println("2) Eliminar Soldado");
        System.out.println("3) Clonar Soldado");
        System.out.println("4) Modificar Soldado");
        System.out.println("5) Comparar Soldados");
        System.out.println("6) Intercambiar Soldados");
        System.out.println("7) Ver Soldado");
        System.out.println("8) Ver Ejército");
        System.out.println("9) Sumar Niveles");
        System.out.println("10) Jugar");
        System.out.println("11) Volver");
		int v = sc.nextInt();
		while(true){
			if(v == 1){
				if(e.size() < 10){
					System.out.println("Ingrese el nombre: ");
					String n = sc.next();
					int arr[] = recibirPos2(e, o);
					int x = arr[0];
					int y = arr[1];

					Soldado s = new Soldado(n, x, y);
					e.add(s);
				}else{
					System.out.println("El ejercito esta lleno!!!");
				}
				break;
			}else if(v == 2){
				if(e.size() > 1){
					int arr[] = recibirPos1(e, o);
					int x = arr[0];
					int y = arr[1];
					e.remove(findSoldado(x,y, e));
				}else{
					System.out.println("El ejercito no puede estar vacio!!!");
				}
				break;

			}else if(v == 3){
				if(e.size() < 10){
					int arr[] = recibirPos1(e, o);
					int x = arr[0];
					int y = arr[1];
					Soldado s = findSoldado(x,y,e).clonarSoldado();

					System.out.println("Ingrese la nueva posicion");
					int arr2[] = recibirPos2(e, o);
					x = arr2[0];
					y = arr2[1];
					s.setFila(x);
					s.setColumna(y);
					e.add(s);
				}else{
					System.out.println("El ejercito esta lleno!!!");
				}
				break;

			}else if(v == 4){
				System.out.println("Ingrese las coordenadas del soldado a verificar!");
				int arr[] = recibirPos1(e, o);
				int x = arr[0];
				int y = arr[1];
				Soldado s = findSoldado(x,y,e);
				menu3(s);
				break;
			}else if(v == 5){
				
				System.out.println("Ingrese las coordenadas del primer soldado");
				int arr1[] = recibirPos1(e, o);
				int x = arr1[0];
				int y = arr1[1];
				Soldado s1 = findSoldado(x, y, e);

				System.out.println("Ingrese las coordenadas del segundo soldado");
				int arr2[] = recibirPos1(e, o);
				x = arr2[0];
				y = arr2[1];
				Soldado s2 = findSoldado(x, y, e);

				s1.comparar(s2);
				break;

			}else if(v == 6){
				System.out.println("Ingrese las coordenadas del primer soldado");
				int arr1[] = recibirPos1(e, o);
				int x = arr1[0];
				int y = arr1[1];
				Soldado s1 = findSoldado(x, y, e);

				System.out.println("Ingrese las coordenadas del segundo soldado");
				int arr2[] = recibirPos1(e, o);
				x = arr2[0];
				y = arr2[1];
				Soldado s2 = findSoldado(x, y, e);

				int index1 = -1;
				int index2 = -1;
				for(int i = 0; i < e.size(); i++){
					if(e.get(i) == s1){
						index1 = i;
					}
					if(e.get(i) == s2){
						index2 = i;
					}
				}

				Soldado temp = s1.clonarSoldado();
				e.remove(index2);
				e.add(index1, s2);

				e.remove(index1);
				e.add(index2, temp);
				break;

			}else if(v == 7){
				System.out.println("Ingrese el nombre del soldado a buscar: ");	
				String n = sc.next();
				int index = -1;
				for(int i = 0; i < e.size(); i++){
					if(e.get(i).getNombre().equals(n)){
						index = i;
					}
				}	
				if(index != -1){
					System.out.println("Se encontro ese soldado.");
					System.out.println(e.get(index));
				}
				break;
			}else if(v == 8){
				System.out.println("Mostrando ejercito ");		
				mostrar(e);
				break;
			}else if(v == 9){
				Soldado soldTemp = new Soldado();
				for(Soldado s: e){
					//suma los puntos de los muertos tambien.
					soldTemp = soldTemp.sumar(s);
				}
				System.out.println("Suma de niveles:");
        		System.out.println("Nivel de Ataque: " + soldTemp.getNivelAtaque());
        		System.out.println("Nivel de Defensa: " + soldTemp.getNivelDefensa());
        		System.out.println("Nivel de Vida: " + soldTemp.getNivelVida());
				break;
			}else if(v == 10){
				return 1;
			}else if(v == 11){
				return 2;
			}
			System.out.println("Algo salio mal, intente de nuevo!");

		}
		//se confia que el usuario ingrese la opcion correcta...
		return 0;
	}
	//lee datos de un soldado en el ejercito
	public static int[] recibirPos1(ArrayList<Soldado> e, ArrayList<Soldado> o){
		Scanner sc = new Scanner(System.in);
		int arr[] = new int[2];
		do{
			System.out.print("Ingrese la fila del soldado: ");
			arr[0] = sc.nextInt();
			System.out.print("Ingrese la columna del soldado: ");
			arr[1] = sc.nextInt();
			arr[0]--;
			arr[1]--;
		}while(!validarOpcion(arr[0],arr[1],e,o));
		return arr;
	}
	//recibe datos de un espacio vacio
	public static int[] recibirPos2(ArrayList<Soldado> e, ArrayList<Soldado> o){
		Scanner sc = new Scanner(System.in);
		int arr[] = new int[2];
		do{
			System.out.print("Ingrese la fila del soldado: ");
			arr[0] = sc.nextInt();
			System.out.print("Ingrese la columna del soldado: ");
			arr[1] = sc.nextInt();
			arr[0]--;
			arr[1]--;
		}while(!validarEleccion(arr[0],arr[1],e,o));
		return arr;
	}

	public static void menu3(Soldado s){
		System.out.println("¿Que desea modificar?");
		System.out.println("1) Nivel de Ataque");
		System.out.println("2) Nivel de Defensa");
		System.out.println("3) Nivel de Vida");
		Scanner sc = new Scanner(System.in);
		int o = sc.nextInt();
		if(o == 1){
			System.out.print("Ingrese el nuevo valor del ataque: ");
			int n = sc.nextInt();
			s.setNivelAtaque(n);
			
		}else if(o == 2){
			System.out.print("Ingrese el nuevo valor de la defensa: ");
			int n = sc.nextInt();
			s.setNivelDefensa(n);
		}else if(o == 3){
		
			System.out.print("Ingrese el nuevo valor de la vida: ");
			int n = sc.nextInt();
			s.setVidaActual(n);
		}
	}
} 
