import java.util.*;
public class Videojuego{
	public static void main(String[] args){
		Scanner sc = new Scanner(System.in);
		String end = "";
		do{
			Mapa terreno = new Mapa();
			terreno.genTablero();
			juego(terreno);
			System.out.println("Desea salir");
			end = sc.next();

		}while (!end.equals("si"));
	}
	public static void juego(Mapa terreno){
		Scanner scanner = new Scanner(System.in);
		ArrayList<Ejercito> reino1 = terreno.getReino1();
		ArrayList<Ejercito> reino2 = terreno.getReino2();
		int turno = 0;
		System.out.println("¡Bienvenido al videojuego!");
		System.out.println("El tipo de terreno es: " + terreno.getTipoTerreno());
		System.out.println("Se enfrentan... ");
		System.out.println(terreno.getTipoReino1() + " vs " + terreno.getTipoReino2());
		imprimirVentaja(terreno.getTipoReino1(), terreno.getTipoReino2(), terreno.getTipoTerreno());
		
		terreno.imprimirMapa();
		do{
			if(turno%2==0){
				System.out.println("Turno del reino 1 (#) " + terreno.getTipoReino1());
				//Comprobar los indices
				int x=0, y=0, tox=0, toy=0;
				do{
					System.out.print("Ingrese el valor de x: ");
					x = scanner.nextInt();
			
					System.out.print("Ingrese el valor de y: ");
					y = scanner.nextInt();
			
					System.out.print("Ingrese el valor de tox: ");
					tox = scanner.nextInt();
			
					System.out.print("Ingrese el valor de toy: ");
					toy = scanner.nextInt();
					x--;
					y--;
					tox--;
					toy--;
				}while(validar(reino1, reino2, x, y, tox, toy));
				//Se ubica al ejercito a mover
				Ejercito ejercitoMover = encontrarEjercito(reino1, x, y);
				mover(ejercitoMover,reino1, reino2, tox, toy);
			}else{
				System.out.println("Turno del reino 2 (*) " + terreno.getTipoReino2());
				int x=0, y=0, tox=0, toy=0;

				do{
					System.out.print("Ingrese el valor de x: ");
					x = scanner.nextInt();
			
					System.out.print("Ingrese el valor de y: ");
					y = scanner.nextInt();
			
					System.out.print("Ingrese el valor de tox: ");
					tox = scanner.nextInt();
			
					System.out.print("Ingrese el valor de toy: ");
					toy = scanner.nextInt();
					x--;
					y--;
					tox--;
					toy--;
				}while(validar(reino2, reino1, x, y, tox, toy));
				Ejercito ejercitoMover = encontrarEjercito(reino2, x, y);
				mover(ejercitoMover, reino2, reino1, tox, toy);
			}
			terreno.genTablero();
			terreno.imprimirMapa();
			turno++;
		}while(winner(reino1, reino2));
	}
	//Realiza el movimiento de un soldado selccionado
	private static void mover(Ejercito ejercitoMover, ArrayList<Ejercito> reinoAmigo, ArrayList<Ejercito> reinoEnemigo, int tox, int toy) {
		//Verificar que el destino sea un enemigo o un espacio vacio
		Ejercito posibleEnemigo = encontrarEjercito(reinoEnemigo, tox, toy);
		if(posibleEnemigo == null){
			//Es un espacio en blanco, solo se mueve
			ejercitoMover.setFila(tox);
			ejercitoMover.setColumna(toy);
		}else{
			//Hay un ejercito enemigo y se genera una batalla.
			boolean resultado = batalla(ejercitoMover, posibleEnemigo);
			//Se remueve al perdedor
			if(resultado){
				reinoEnemigo.remove(posibleEnemigo);
				aumentarVida(ejercitoMover);
				ejercitoMover.setFila(tox);
				ejercitoMover.setColumna(toy);
			}else{
				reinoAmigo.remove(ejercitoMover);
				aumentarVida(posibleEnemigo);
				posibleEnemigo.setFila(tox);
				posibleEnemigo.setColumna(toy);
			}
		}
	}
	private static void aumentarVida(Ejercito e ) {
		for(Soldado s: e.getSoldados()){
			s.setVidaActual(s.getVidaActual() + 1);
		}

	}
	//Retorna true, si el ejercito movido gana, y false si este pierde
	public static boolean batalla(Ejercito ejercitoMover, Ejercito enemigo) {
		int vida1 = obtenerVidaEjercito(ejercitoMover);
		int vida2 = obtenerVidaEjercito(enemigo);
		double probabilidadGanar = (double) vida1 / (vida1 + vida2);
		double probabilidadPerder = 1.0 - probabilidadGanar;
		double resultadoAleatorio = Math.random();
	
		System.out.println("E1: " + vida1 + "\t" + "E2: " + vida2);
		System.out.println("Probabilidades de victoria:");
		System.out.println("E1: " + (probabilidadGanar * 100) + "%\t" + "E2: " + (probabilidadPerder * 100) + "%");
	
		if (resultadoAleatorio < probabilidadGanar) {
			System.out.println("El ejército " + ejercitoMover.getReino() + " gana la batalla");
			return true;
		} else {
			System.out.println("El ejército " + enemigo.getReino() + " gana la batalla");
			return false;
		}
	}
	
		
	
	public static int obtenerVidaEjercito(Ejercito ejercito){
		int vida = 0;
		for(Soldado s: ejercito.getSoldados()){
			vida+=s.getVidaActual();
		}
		return vida;
	}
	//Metodo para encontrar al un ejercito por medio de sus coordenadas.
	private static Ejercito encontrarEjercito(ArrayList<Ejercito> reino, int x, int y) {
		for(Ejercito e: reino){
			if(e.getFila() == x && e.getColumna() == y){
				return e;
			}
		}
		return null;
	}

	//Metodo para comprobar si los datos son correctos
	private static boolean validar(ArrayList<Ejercito> reino1, ArrayList<Ejercito> reino2, int x, int y, int tox, int toy) {
		//Primero se validan los limites
        if ((x < 0 || x > 9) || (y < 0 || y > 9)){
			System.out.println("Limites del mapa excedidos");
			return true;
		}
        if ((tox < 0 || tox > 9) || (toy < 0 || toy > 9)){
			System.out.println("Limites del mapa excedidos");
			return true;
		}
		//Como primera validacion ante todo, es necesario buscar si la seleccion es un ejercito enemigo
		Ejercito posibleEnemigo = encontrarEjercito(reino2, x, y);
		if(posibleEnemigo != null){
			System.out.println("Ha seleccionado a un enemigo!. Ingrese de nuevo");
			return true;
		}
		//Se busca a un Ejercito amigo en la direccion de movimiento, pues esto generaria un error
		Ejercito posibleAmigo = encontrarEjercito(reino1, tox, toy);
		if(posibleAmigo != null){
			System.out.println("Se encontro a un aliado en esa direccion!. Ingrese de nuevo");
			return true;
		}
		//Se busca a un Ejercito amigo en la eleccion, si hay un Ejercito este es el elegido para mover, si no es invalido
		posibleAmigo = encontrarEjercito(reino1, x, y);
		if(posibleAmigo == null){
			System.out.println("No hay ningun ejercito aliado en esa seleccion!. Ingrese de nuevo");
			return true;
		}
		//Tras validar todos los casos posibles, las entradas son correctas
		return false;
	}
	public static boolean winner(ArrayList<Ejercito> reino1, ArrayList<Ejercito> reino2){
		if(reino2.size() == 0){
			System.out.println("El reino " + reino1.get(0).getReino()+" gano el juego");
			return false;
		}
		if(reino1.size() == 0){
			System.out.println("El reino " + reino2.get(0).getReino()+" gano el juego");
			return false;
		}
		return true;
	}
	public static void imprimirVentaja(String reino1, String reino2, String tipoTerreno) {
		boolean ventajaReino1 = false;
		boolean ventajaReino2 = false;
	
		if (tipoTerreno.equals("Bosque")) {
			if (reino1.equals("Inglaterra")) {
				ventajaReino1 = true;
			}
			if (reino2.equals("Inglaterra")) {
				ventajaReino2 = true;
			}
			if (reino1.equals("SIRG")) {
				ventajaReino1 = true;
			}
			if (reino2.equals("SIRG")) {
				ventajaReino2 = true;
			}

		} else if (tipoTerreno.equals("Campo Abierto")) {
			if (reino1.equals("Francia")) {
				ventajaReino1 = true;
			}
			if (reino2.equals("Francia")) {
				ventajaReino2 = true;
			}
			if (reino1.equals("SIRG")) {
				ventajaReino1 = true;
			}
			if (reino2.equals("SIRG")) {
				ventajaReino2 = true;
			}
		} else if (tipoTerreno.equals("Montaña")) {
			if (reino1.equals("Castilla-Aragon")) {
				ventajaReino1 = true;
			} else if (reino2.equals("Castilla-Aragon")) {
				ventajaReino2 = true;
			}
		} else if (tipoTerreno.equals("Desierto")) {
			if (reino1.equals("Moros")) {
				ventajaReino1 = true;
			} else if (reino2.equals("Moros")) {
				ventajaReino2 = true;
			}
		} else if (tipoTerreno.equals("Playa")) {
			if (reino1.equals("SIRG")) {
				ventajaReino1 = true;
			} else if (reino2.equals("SIRG")) {
				ventajaReino2 = true;
			}
		}
		if(ventajaReino1 && ventajaReino2){
			System.out.println("Los dos reinos tienen ventaja");
		}else if (ventajaReino1 && !ventajaReino2) {
			System.out.println("El reino "+reino1 +" tiene ventaja en " + tipoTerreno);
		} else if (ventajaReino2 && !ventajaReino1) {
			System.out.println("El reino "+reino2 +" tiene ventaja en " + tipoTerreno);
		} else {
			System.out.println("Ningún reino tiene ventaja en " + tipoTerreno);
		}
	}
}
