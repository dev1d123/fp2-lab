import java.util.ArrayList;
public class Videojuego3 {
    public static void main(String[] args) {
        int numSoldados1 = (int) (Math.random() * 10 + 1);
        int numSoldados2 = (int) (Math.random() * 10 + 1);
        Soldado[] ejercito1 = new Soldado[numSoldados1];
        Soldado[] ejercito2 = new Soldado[numSoldados2];
        ArrayList<ArrayList<Character>> tablero = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            ArrayList<Character> fila = new ArrayList<>();
	    for(int j= 0;j < 10; j++){
	    	fila.add('.');
	    }
            tablero.add(fila);
        }
        genSoldados(ejercito1, numSoldados1, ejercito2, numSoldados2, tablero);
		System.out.println("Soldado con mayor vida del ejercito #");
		System.out.println(soldadoMayorVida(ejercito1));
		System.out.println("Soldado con mayor vida del ejercito *");
		System.out.println(soldadoMayorVida(ejercito2));
		System.out.print("Promedio de vida del ejercito #: ");
		System.out.println(promedioVida(ejercito1));
		System.out.print("Promedio de vida del ejercito *: ");
		System.out.println(promedioVida(ejercito2));
		System.out.println("Datos en orden del ejercito #: ");
		imprimirOrden(ejercito1);
		System.out.println("Datos en orden del ejercito *: ");
		imprimirOrden(ejercito2);
		System.out.println("BubbleSort");
		System.out.println("Mostrando ranking de los soldados del ejercito #: ");
		ordenarRankingBubble(ejercito1);
		mostrar(ejercito1);
		System.out.println("Mostrando ranking de los soldados del ejercito *: ");
		ordenarRankingBubble(ejercito2);
		mostrar(ejercito2);
		System.out.println("SelectionSort");
		System.out.println("Mostrando ranking de los soldados del ejercito #: ");
		ordenarRankingSelection(ejercito1);
		mostrar(ejercito1);
		System.out.println("Mostrando ranking de los soldados del ejercito *: ");
		ordenarRankingSelection(ejercito2);
		mostrar(ejercito2);
		winner(ejercito1, ejercito2);
    }

    public static void genSoldados(Soldado[] e1, int n1, Soldado[] e2, int n2, ArrayList<ArrayList<Character>> tablero) {
        boolean[][] pos = new boolean[10][10];
        char[] colToNum = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J'};
        for (int i = 0; i < n1; i++) {
            int fila = (int) (Math.random() * 10);
            int columna = (int) (Math.random() * 10);
            while (pos[fila][columna]) {
                fila = (int) (Math.random() * 10);
                columna = (int) (Math.random() * 10);
            }
            pos[fila][columna] = true;
            tablero.get(fila).set(columna, '#');
            String nombre = "Soldado" + i + "X1";
            e1[i] = new Soldado(nombre, fila+1, colToNum[columna]);
        }
        for (int i = 0; i < n2; i++) {
            int fila = (int) (Math.random() * 10);
            int columna = (int) (Math.random() * 10);
            while (pos[fila][columna]) {
                fila = (int) (Math.random() * 10);
                columna = (int) (Math.random() * 10);
            }
            pos[fila][columna] = true;
            tablero.get(fila).set(columna, '*');
            String nombre = "Soldado" + i + "X2";
            e2[i] = new Soldado(nombre, fila+1, colToNum[columna]);
        }

        // Imprimir el tablero
		
		for(char c: colToNum){
			System.out.print("\t" + c);
		}
		System.out.println();
		int f = 1;
        for (ArrayList<Character> fila : tablero) {
            System.out.print(f + "\t");
			for (Character c : fila) {	
                System.out.print(c + "\t");
            }
            System.out.println();
			f++;
        }
    }
    public static Soldado soldadoMayorVida(Soldado[] e){
    	int max = 0;
		int index = 0;
		for(int i = 0; i < e.length; i++){
			max = Math.max(max, e[i].getVida());
			if(max == e[i].getVida()){
				index = i;
			}
		}
		return e[index];
    }
    public static double promedioVida(Soldado[] e){
    	double sumVida = 0;
		for(Soldado s: e){
			sumVida += s.getVida();
		}

		return sumVida/e.length;
    }
	public static void imprimirOrden(Soldado[] e){
		for(Soldado s: e){
			System.out.println(s);
			
		}
	}
	public static void winner(Soldado[] e1, Soldado[]e2){
		//Gana el ejercito que tenga el mayor promedio de vida.
		if(promedioVida(e1) > promedioVida(e2)){
			System.out.println("El ejercito # gana la batalla");
		}else if(promedioVida(e1) < promedioVida(e2)){
			System.out.println("El ejercito * gana la batalla");
		}else{
			System.out.println("Empate");
		}
	}
    public static void ordenarRankingBubble(Soldado[] e) {
        int n = e.length;
        boolean swapped;
        for (int i = 0; i < n; i++) {
			for(int j = 0; j < n - 1- i; j++){
					if (e[j].getVida() < e[j+1].getVida()) {
					   Soldado temp = e[j];
					   e[j] = e[j+1];
					   e[j+1] = temp;
					}
			}		
        }
    }

    // Método para mostrar el ranking de los soldados
    public static void mostrar(Soldado[] e) {
        for (int i = 0; i < e.length; i++) {
            System.out.println(e[i].getNombre() + ", Vida: " + e[i].getVida());
        }
    }

    // Método para ordenar el ranking de los soldados utilizando el algoritmo SelectionSort
    public static void ordenarRankingSelection(Soldado[] e) {
        int n = e.length;
        for (int i = 0; i < n - 1; i++) {
            int maxIndex = i;
            for (int j = i + 1; j < n; j++) {
                if (e[j].getVida() > e[maxIndex].getVida()) {
                    maxIndex = j;
                }
            }
            Soldado temp = e[maxIndex];
            e[maxIndex] = e[i];
            e[i] = temp;
        }
    }	


}

