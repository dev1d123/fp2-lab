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
    private String[][] tablero = new String[10][10];
    private boolean[][] posiciones = new boolean[10][10];
    public Mapa(){
		Random random = new Random();
        int numero1 = random.nextInt(5) + 1;
        int numero2;
        do {
            numero2 = random.nextInt(5) + 1;
        } while (numero2 == numero1);
		e1.setReino(numero1);
		e2.setReino(numero2);

		tipoMapa = (int)(Math.random() * 5 + 1);
        generarEjercito(e1, 1);
        generarEjercito(e2, 2);

		System.out.println("¡Bienvenido al simulador!");
    }
    public void genTablero(){
		for(int i = 0; i < 10; i++){
			for(int j = 0; j < 10; j++){
				tablero[i][j] = "";
			}
		}	
		for (int i = 0; i < e1.getSoldados().size(); i++) {
			int fila = e1.getSoldados().get(i).getFila();
			int columna = e1.getSoldados().get(i).getColumna();
			tablero[fila][columna] = "\u001B[32m" + e1.getSoldados().get(i).impresionTabla() + "\u001B[0m";
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

    private void generarEjercito(Ejercito e, int num){
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
        this.numeroEjercito1 = numEjercito;
		genSoldados(e, numEspada, numArque, numCaball, numLanc, 2);
		aumentarVida();

    }

    private void genSoldados(Ejercito e, int n1, int n2, int n3, int n4, int ejer){
		boolean isEsp = true;
        for(int i = 0; i < n1; i++){
            String nombre = "Espadachin"+i+"x"+ejer;
            int fila = 0; int columna = 0;
			do{
				fila = (int)(Math.random()*9);
				columna = (int)(Math.random()*9);
			}while(posiciones[fila][columna]);
			posiciones[fila][columna] = true;
			Espadachin s = null;
			if(e.getReino() == 1 && isEsp){
				nombre = "EspadachinReal"+i+"x"+ejer;
				s = new EspadachinReal(nombre, fila, columna, 1);
				isEsp = false;
			}else if(e.getReino() == 3 && isEsp){
				nombre = "EspadachinConquistador"+i+"x"+ejer;
				s = new EspadachinConquistador(nombre, fila, columna, 3);
				isEsp = false;
			}else if(e.getReino() == 5 && isEsp){
				nombre = "EspadachinTeutonico"+i+"x"+ejer;
				s = new EspadachinTeutonico(nombre, fila, columna, 5);
				isEsp = false;
			}else{								
				s = new Espadachin(nombre, fila, columna, e.getReino());
			}
			e.getSoldados().add(s);

        }
        for(int i = 0; i < n2; i++){
            String nombre = "Arquero"+i+"x"+ejer;
            int fila = 0; int columna = 0;
			do{
				fila = (int)(Math.random()*9);
				columna = (int)(Math.random()*9);
			}while(posiciones[fila][columna]);
			posiciones[fila][columna] = true;
            Arquero s = new Arquero(nombre, fila, columna, e.getReino());
			e.getSoldados().add(s);			
        }
		boolean isC = true;
        for(int i = 0; i < n3; i++){
            String nombre = "Caballero"+i+"x"+ejer;
            int fila = 0; int columna = 0;
			do{
				fila = (int)(Math.random()*9);
				columna = (int)(Math.random()*9);
			}while(posiciones[fila][columna]);
			posiciones[fila][columna] = true;
			Caballero s = null;
			if(e.getReino() == 2 && isC){
				nombre = "CaballeroFranco"+i+"x"+ejer;
            	s = new CaballeroFranco(nombre, fila, columna, 2);
				isC = false;
			}else if(e.getReino() == 4 && isC){
				nombre = "CaballeroMoro"+i+"x"+ejer;
            	s = new CaballeroMoro(nombre, fila, columna, 4);
				isC = false;
			}else{
            	s = new Caballero(nombre, fila, columna, e.getReino());
			}
			e.getSoldados().add(s);
        }
        for(int i = 0; i < n4; i++){
            String nombre = "Lancero"+i+"x"+ejer;
            int fila = 0; int columna = 0;
			do{
				fila = (int)(Math.random()*9);
				columna = (int)(Math.random()*9);
			}while(posiciones[fila][columna]);
			posiciones[fila][columna] = true;
            Lancero s = new Lancero(nombre, fila, columna, e.getReino());
			e.getSoldados().add(s);
        }
    }
	public Ejercito getEjercito1(){
		return e1;
	}
	public Ejercito getEjercito2(){
		return e2;
	}
	public void aumentarVida(){
		if(e1.getReino() == this.tipoMapa || (e1.getReino() == 5 && this.tipoMapa == 1 || e1.getReino() == 5 && this.tipoMapa == 2)){
			for(Soldado s: e1.getSoldados()){
				s.setVidaActual(s.getVidaActual() + 1);
			}
		}
		if(e1.getReino() == this.tipoMapa || (e1.getReino() == 5 && this.tipoMapa == 1 || e1.getReino() == 5 && this.tipoMapa == 2)){
			for(Soldado s: e2.getSoldados()){
				s.setVidaActual(s.getVidaActual() + 1);
			}
		}
	}

	public void imprimirDatosFinales(){
		System.out.println();
		System.out.println("\u001B[32m" + "Ejercito 1: " + reinos.get(e1.getReino())+ "\u001B[0m");
		System.out.println("Cantidad total de soldados creados: " + e1.getSoldados().size());
		System.out.println("Espadachines: " + e1.getCantidadEsp());
		System.out.println("Arqueros: " + e1.getCantidadArq());
		System.out.println("Caballeros: " + e1.getCantidadCab());
		System.out.println("Lanceros: " + e1.getCantidadLan());

		System.out.println();

		System.out.println("\u001B[31m" + "Ejercito 2: " + reinos.get(e2.getReino()) + "\u001B[0m");
		System.out.println("Cantidad total de soldados creados: " + e2.getSoldados().size());
		System.out.println("Espadachines: " + e2.getCantidadEsp());
		System.out.println("Arqueros: " + e2.getCantidadArq());
		System.out.println("Caballeros: " + e2.getCantidadCab());
		System.out.println("Lanceros: " + e2.getCantidadLan());

	}
	public void imprimirDatosTerreno(){	
		System.out.println("El territorio es: " + terr.get(this.tipoMapa));
		if(e1.getReino() == this.tipoMapa || (e1.getReino() == 5 && this.tipoMapa == 1 || e1.getReino() == 5 && this.tipoMapa == 2)){
			System.out.println(reinos.get(e1.getReino()) + " obtiene ventaja!");
		}
		if(e2.getReino() == this.tipoMapa || (e2.getReino() == 5 && this.tipoMapa == 1 || e2.getReino() == 5 && this.tipoMapa == 2)){
			System.out.println(reinos.get(e2.getReino()) + " obtiene ventaja!");
		}

	}
}




