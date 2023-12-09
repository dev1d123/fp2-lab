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

	public void imprimirDatosFinales() {
        String content = "\n" +
                "Ejercito 1: " + reinos.get(e1.getReino()) + "\n" +
                "Cantidad total de soldados creados: " + e1.getSoldados().size() + "\n" +
                "Espadachines: " + e1.getCantidadEsp() + "\n" +
                "Arqueros: " + e1.getCantidadArq() + "\n" +
                "Caballeros: " + e1.getCantidadCab() + "\n" +
                "Lanceros: " + e1.getCantidadLan() + "\n" +
                "\n" +
                "Ejercito 2: " + reinos.get(e2.getReino()) + "\n" +
                "Cantidad total de soldados creados: " + e2.getSoldados().size() + "\n" +
                "Espadachines: " + e2.getCantidadEsp() + "\n" +
                "Arqueros: " + e2.getCantidadArq() + "\n" +
                "Caballeros: " + e2.getCantidadCab() + "\n" +
                "Lanceros: " + e2.getCantidadLan() + "\n";

        new InfoFrame("Datos Finales", content).setLocation(1000, 500);
    }

    public void imprimirDatosTerreno() {
        String content = "El territorio es: " + terr.get(this.tipoMapa) + "\n";

        if (e1.getReino() == this.tipoMapa || (e1.getReino() == 5 && this.tipoMapa == 1 || e1.getReino() == 5 && this.tipoMapa == 2)) {
            content += reinos.get(e1.getReino()) + " obtiene ventaja!\n";
        }

        if (e2.getReino() == this.tipoMapa || (e2.getReino() == 5 && this.tipoMapa == 1 || e2.getReino() == 5 && this.tipoMapa == 2)) {
            content += reinos.get(e2.getReino()) + " obtiene ventaja!\n";
        }

        new InfoFrame("Datos del Terreno", content).setLocation(1000, 0);
    }
}




