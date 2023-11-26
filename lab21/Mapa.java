import java.util.*;
public class Mapa {
    int numeroEjercito1;
    int numeroEjercito2;
	private Ejercito e1 = new Ejercito();

	private Ejercito e2 = new Ejercito();

	private int tipoMapa;

    private HashMap<Integer, String> terr = new HashMap<Integer, String>() {{
        put(1, "Bosque");
        put(2, "Campo Abierto");
        put(3, "Montañas");
        put(4, "Desierto");
        put(5, "Playa");
    }};
    private HashMap<Integer, String> reinos = new HashMap<Integer, String>() {{
        put(1, "Inglaterra");
        put(2, "Francia");
        put(3, "Castilla-Aragon");
        put(4, "Moros");
        put(5, "Sacro Imperio Romano-Germanico");
    }};
	/*
	 * 1-> Bosque
	 * 2 -> Campo Abierto
	 * 3 -> Montaña
	 * 4 -> Desierto
	 * 5 -> Playa
	 */
    private String[][] tablero = new String[10][10];
    private boolean[][] posiciones = new boolean[10][10];
    public Mapa(){
		tipoMapa = (int)(Math.random() * 5 + 1);
        generarEjercito(tipoMapa, 1);
        generarEjercito(tipoMapa, 2);
		System.out.println("¡Bienvenido al simulador!");
    }

    public void genTablero(){
		for(int i = 0; i < 10; i++){
			for(int j = 0; j < 10; j++){
				tablero[i][j] = "";
			}
		}	
		for (int i = 0; i < e1.getSoldadosArray().length; i++) {
			int fila = e1.getSoldadosArray()[i].getFila();
			int columna = e1.getSoldadosArray()[i].getColumna();
			tablero[fila][columna] = "\u001B[32m" + e1.getSoldadosArray()[i].impresionTabla() + "\u001B[0m";
		}
		for(int i = 0; i < e2.getSoldados().size(); i++){
			int fila = e2.getSoldados().get(i).getFila();
			int columna = e2.getSoldados().get(i).getColumna();
			tablero[fila][columna] = "\u001B[31m" + e2.getSoldados().get(i).impresionTabla() + "\u001B[0m";
		}
	}
    public void imprimirMapa(){
		char[] aux = {'A','B','C','D','E','F','G','H','I', 'J'};
        System.out.print("       ");
		for(char c: aux){
			System.out.print("\t  "+c);
		}
        System.out.println();

        for(int i = 0; i < 90; i++){
			System.out.print("-");
		}
		System.out.println();
		for(int i = 0; i < 10; i++){
			System.out.print(i+1 +"\t|");
			for(int j = 0; j < 10; j++){
				System.out.print(tablero[i][j] + "\t|");
			}
            System.out.println();
            for(int j = 0; j < 90; j++){
			    System.out.print("-");
		    }
			System.out.println();
		}
    }

    private void generarEjercito(int tipoReino, int num){
        int numEspada = 0;
        int numArque =0;
        int numCaball = 0;
		int numLanc = 0;
        
        do{
            numEspada = (int)(Math.random() * 9 + 1);
            numArque = (int)(Math.random() * 9 + 1);
            numCaball = (int)(Math.random() * 9 + 1);
			numLanc= (int)(Math.random() * 9 + 1);

        }while ((numEspada + numArque + numCaball + numLanc) > 10);
        int numEjercito = numEspada + numArque + numCaball + numLanc;
		//asignar ventaja
		/*
		 * Inglaterra(1) -> Bosque(1)
		 * Francia(2) -> Campo Abierto(2)
		 * Castilla Aragon(3) -> Montaña (3)
		 * Moros(4)-> Desierto(4)
		 * SIRG(5) -> bosque(1), playa(5), CampoAbierto(2)
		 */
		if(num == 1){
        	this.numeroEjercito1 = numEjercito;
			genSoldados(numEspada, numArque, numCaball, numLanc, 1);

		}else{
			this.numeroEjercito2 = numEjercito;
			genSoldados(numEspada, numArque, numCaball, numLanc, 2);
		}
		aumentarVida();


    }
    private void genSoldados(int n1, int n2, int n3, int n4, int ejer){
		if(ejer == 1){
			//Inicializar arreglo
			e1.inicializar(n1+n2+n3+n4);
		}
		int count = 0; // solo para manejar los indices del arreglo estandar
        for(int i = 0; i < n1; i++){
            String nombre = "Espadachin"+i+"x"+ejer;
            int fila = 0; int columna = 0;
			do{
				fila = (int)(Math.random()*9);
				columna = (int)(Math.random()*9);
			}while(posiciones[fila][columna]);
			posiciones[fila][columna] = true;
            Espadachin s = new Espadachin(nombre, fila, columna);
			if(ejer == 1){
				e1.setSoldadoArr(count, s);
				count++;
			}else{
				e2.getSoldados().add(s);
			}
        }
        for(int i = 0; i < n2; i++){
            String nombre = "Arquero"+i+"x"+ejer;
            int fila = 0; int columna = 0;
			do{
				fila = (int)(Math.random()*9);
				columna = (int)(Math.random()*9);
			}while(posiciones[fila][columna]);
			posiciones[fila][columna] = true;
            Arquero s = new Arquero(nombre, fila, columna);
			if(ejer == 1){
				e1.setSoldadoArr(count, s);
				count++;
			}else{
				e2.getSoldados().add(s);
			}
        }
        for(int i = 0; i < n3; i++){
            String nombre = "Caballero"+i+"x"+ejer;
            int fila = 0; int columna = 0;
			do{
				fila = (int)(Math.random()*9);
				columna = (int)(Math.random()*9);
			}while(posiciones[fila][columna]);
			posiciones[fila][columna] = true;
            Caballero s = new Caballero(nombre, fila, columna);
			if(ejer == 1){
				e1.setSoldadoArr(count, s);
				count++;
			}else{
				e2.getSoldados().add(s);
			}
        }
        for(int i = 0; i < n4; i++){
            String nombre = "Lancero"+i+"x"+ejer;
            int fila = 0; int columna = 0;
			do{
				fila = (int)(Math.random()*9);
				columna = (int)(Math.random()*9);
			}while(posiciones[fila][columna]);
			posiciones[fila][columna] = true;
            Lancero s = new Lancero(nombre, fila, columna);
			if(ejer == 1){
				e1.setSoldadoArr(count, s);
				count++;
			}else{
				e2.getSoldados().add(s);
			}
        }
    }
	public Ejercito getEjercito1(){
		return e1;
	}
	public Ejercito getEjercito2(){
		return e2;
	}
	public void aumentarVida(){
		if(e1.getReino1() == this.tipoMapa || (e1.getReino1() == 5 && this.tipoMapa == 1 || e1.getReino1() == 5 && this.tipoMapa == 2)){
			for(Soldado s: e1.getSoldadosArray()){
				s.setVidaActual(s.getVidaActual() + 1);
			}
		}
		if(e1.getReino2() == this.tipoMapa || (e1.getReino2() == 5 && this.tipoMapa == 1 || e1.getReino2() == 5 && this.tipoMapa == 2)){
			for(Soldado s: e2.getSoldados()){
				s.setVidaActual(s.getVidaActual() + 1);
			}
		}
	}

	public void imprimirDatosFinales(){
		System.out.println();
		System.out.println("Ejercito 1: " + reinos.get(e1.getReino1()));
		System.out.println("Cantidad total de soldados creados: " + e1.getSoldadosArray().length);
		System.out.println("Espadachines: " + e1.getCantidadEspArray());
		System.out.println("Arqueros: " + e1.getCantidadArqArray());
		System.out.println("Caballeros: " + e1.getCantidadCabArray());
		System.out.println("Lanceros: " + e1.getCantidadLanArray());

		System.out.println();

		System.out.println("Ejercito 2: " + reinos.get(e2.getReino2()));
		System.out.println("Cantidad total de soldados creados: " + e2.getSoldados().size());
		System.out.println("Espadachines: " + e2.getCantidadEsp());
		System.out.println("Arqueros: " + e2.getCantidadArq());
		System.out.println("Caballeros: " + e2.getCantidadCab());
		System.out.println("Lanceros: " + e2.getCantidadLan());

	}
	public void imprimirDatosTerreno(){

		
		System.out.println("El territorio es: " + terr.get(this.tipoMapa));
		if(e1.getReino1() == this.tipoMapa || (e1.getReino1() == 5 && this.tipoMapa == 1 || e1.getReino1() == 5 && this.tipoMapa == 2)){
			System.out.println(reinos.get(e1.getReino1()) + " obtiene ventaja!");
		}
		if(e2.getReino2() == this.tipoMapa || (e2.getReino2() == 5 && this.tipoMapa == 1 || e2.getReino2() == 5 && this.tipoMapa == 2)){
			System.out.println(reinos.get(e2.getReino2()) + " obtiene ventaja!");
		}

	}
}




