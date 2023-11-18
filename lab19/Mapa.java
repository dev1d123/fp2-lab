import java.util.*;
public class Mapa {
    int numeroEjercito1;
    int numeroEjercito2;
	private Ejercito e1 = new Ejercito();
	private Ejercito e2 = new Ejercito();
    private String[][] tablero = new String[10][10];
    private boolean[][] posiciones = new boolean[10][10];
    public Mapa(){
        generarEjercito(e1.getEspadachines(), e1.getArqueros(), e1.getCaballeros(), 1);
        generarEjercito(e2.getEspadachines(), e2.getArqueros(), e2.getCaballeros(), 2);
        System.out.println("La cantidad de espadachines, arqueros y caballeros del ejercito 1 es: " + 
        e1.getEspadachines().size()+" "+e1.getArqueros().size()+" "+e1.getCaballeros().size());
        System.out.println("La cantidad de espadachines, arqueros y caballeros del ejercito 2 es: " + 
        e2.getEspadachines().size()+" "+e2.getArqueros().size()+" "+e2.getCaballeros().size());
    }
    public void genTablero(){
		//inicializamos el tablero.
		for(int i = 0; i < 10; i++){
			for(int j = 0; j < 10; j++){
				tablero[i][j] = "";
			}
		}	
		for(int i = 0; i < e1.getEspadachines().size(); i++){
			int fila = e1.getEspadachines().get(i).getFila();
			int columna = e1.getEspadachines().get(i).getColumna();
			tablero[fila][columna] = "\u001B[32m" +'E'+ "" +e1.getEspadachines().get(i).getVidaActual() + "\u001B[0m";
		}
        for(int i = 0; i < e1.getArqueros().size(); i++){
			int fila = e1.getArqueros().get(i).getFila();
			int columna = e1.getArqueros().get(i).getColumna();
			tablero[fila][columna] = "\u001B[32m" +'A'+ "" +e1.getArqueros().get(i).getVidaActual() + "\u001B[0m";
		}
        for(int i = 0; i < e1.getCaballeros().size(); i++){
			int fila = e1.getCaballeros().get(i).getFila();
			int columna = e1.getCaballeros().get(i).getColumna();
			tablero[fila][columna] = "\u001B[32m" +'C'+ "" +e1.getCaballeros().get(i).getVidaActual() + "\u001B[0m";
		}

		for(int i = 0; i < e2.getEspadachines().size(); i++){
			int fila = e2.getEspadachines().get(i).getFila();
			int columna = e2.getEspadachines().get(i).getColumna();
			tablero[fila][columna] = "\u001B[31m" + 'E'+ "" +e2.getEspadachines().get(i).getVidaActual() + "\u001B[0m";
		}
        for(int i = 0; i < e2.getArqueros().size(); i++){
			int fila = e2.getArqueros().get(i).getFila();
			int columna = e2.getArqueros().get(i).getColumna();
			tablero[fila][columna] = "\u001B[31m" + 'A'+ "" +e2.getArqueros().get(i).getVidaActual() + "\u001B[0m";
		}
        for(int i = 0; i < e2.getCaballeros().size(); i++){
			int fila = e2.getCaballeros().get(i).getFila();
			int columna = e2.getCaballeros().get(i).getColumna();
			tablero[fila][columna] = "\u001B[31m" + 'C'+ "" +e2.getCaballeros().get(i).getVidaActual() + "\u001B[0m";
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
    private void generarEjercito(ArrayList<Espadachin> esp, ArrayList<Arquero> arq, ArrayList<Caballero> cab, int num){
        int numEspada = 0;
        int numArque =0;
        int numCaball = 0;
        
        do{
            numEspada = (int)(Math.random() * 10);
            numArque = (int)(Math.random() * 10);
            numCaball = (int)(Math.random() * 10);
        }while ((numEspada + numArque + numCaball) > 10);
        int numEjercito = numEspada + numArque + numCaball;
		if(num == 1){
        	this.numeroEjercito1 = numEjercito;
			genEspadachin(numEspada, esp, 1);
        	genArquero(numArque, arq, 1);
        	genCaballero(numCaball, cab, 1);
		}else{
			this.numeroEjercito2 = numEjercito;
			genEspadachin(numEspada, esp, 2);
        	genArquero(numArque, arq, 2);
        	genCaballero(numCaball, cab, 2);
		}
    }
    private void genEspadachin(int numero, ArrayList<Espadachin> ejer, int n){
        for(int i = 0; i < numero; i++){
            String nombre = "Espadachin"+i+"x"+n;
            int fila = 0;
			int columna = 0;
			do{
				fila = (int)(Math.random()*9);
				columna = (int)(Math.random()*9);
			}while(posiciones[fila][columna]);
			posiciones[fila][columna] = true;
            Espadachin s = new Espadachin(nombre, fila, columna);

			ejer.add(s);
        }
    }
    private void genArquero(int numero, ArrayList<Arquero> ejer, int n){
        for(int i = 0; i < numero; i++){
            String nombre = "Arquero"+i+"x"+n;
            int fila = 0;
			int columna = 0;
			do{
				fila = (int)(Math.random()*9);
				columna = (int)(Math.random()*9);
			}while(posiciones[fila][columna]);
			posiciones[fila][columna] = true;
            Arquero s = new Arquero(nombre, fila, columna);
			ejer.add(s);
        }
    }
    private void genCaballero(int numero, ArrayList<Caballero> ejer, int n){
        for(int i = 0; i < numero; i++){
            String nombre = "Caballero"+i+"x"+n;
            int fila = 0;
			int columna = 0;
			do{
				fila = (int)(Math.random()*9);
				columna = (int)(Math.random()*9);
			}while(posiciones[fila][columna]);
			posiciones[fila][columna] = true;
            Caballero s = new Caballero(nombre, fila, columna);
			ejer.add(s);
        }
    }
	public Ejercito getEjercito1(){
		return e1;
	}
	public Ejercito getEjercito2(){
		return e2;
	}
}
