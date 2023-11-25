import java.util.*;
public class Mapa {
    int numeroEjercito1;
    int numeroEjercito2;
	private Ejercito e1 = new Ejercito();
	private Ejercito e2 = new Ejercito();
    private String[][] tablero = new String[10][10];
    private boolean[][] posiciones = new boolean[10][10];
    public Mapa(){
        generarEjercito(e1.getSoldados(), 1);
        generarEjercito(e2.getSoldados(), 2);
        System.out.println("La cantidad de espadachines, arqueros, caballeros y lanceros del ejercito 1 es: " + 
        e1.getCantidadEsp()+" "+e1.getCantidadArq()+" "+e1.getCantidadCab() +" "+e1.getCantidadLan());
        System.out.println("La cantidad de espadachines, arqueros, caballeros y lanceros del ejercito 2 es: " + 
        e2.getCantidadEsp()+" "+e2.getCantidadArq()+" "+e2.getCantidadCab() + " " +e2.getCantidadLan());
    }
    public void genTablero(){
		for(int i = 0; i < 10; i++){
			for(int j = 0; j < 10; j++){
				tablero[i][j] = "";
			}
		}	
		for(int i = 0; i < e1.getSoldados().size(); i++){
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

    private void generarEjercito(ArrayList<Soldado> sol, int num){
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
		if(num == 1){
        	this.numeroEjercito1 = numEjercito;
			genSoldados(numEspada, numArque, numCaball, numLanc, 1);
		}else{
			this.numeroEjercito2 = numEjercito;
			genSoldados(numEspada, numArque, numCaball, numLanc, 2);

		}
    }
    private void genSoldados(int n1, int n2, int n3, int n4, int ejer){
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
				e1.getSoldados().add(s);
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
				e1.getSoldados().add(s);
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
				e1.getSoldados().add(s);
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
				e1.getSoldados().add(s);
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
}




