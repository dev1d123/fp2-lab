import java.util.*;
public class Videojuego2{
	public static void main(String [] args){
		int numNaves = (int)(Math.random()*10 + 1);
		System.out.println("La cantidad de soldados es: " + numNaves);		
 		Nave [][] misNaves = new Nave[10][10];
		//Es necesario crear un arreglo para almacenar los soldados creados en orden.
		int[][] ordenCron = new int[numNaves][2];
			
		genNaves(misNaves, numNaves, ordenCron);
		System.out.println("Generando la tabla: ");
		imprimirTabla(misNaves);
		System.out.println("Datos del soldado con mayor vida: ");
		System.out.println(mayorVidaSoldado(misNaves));
		System.out.println("Promedio de la vida: "+ promedioVidaSoldado(misNaves));
		System.out.println("Total de la vida: "+ totalVidaSoldado(misNaves));
		
		System.out.println("Imprimiendo los soldados creado cronologicamente");
		imprimir(misNaves, ordenCron);

		System.out.println("Ranking (Burbuja)");
		ordenarBurbuja(misNaves, ordenCron);
		imprimir(misNaves, ordenCron);
		
		System.out.println("Ranking (seleccion), para desordenar");
		ordenarSeleccion1(misNaves, ordenCron);
		imprimir(misNaves, ordenCron);
		System.out.println("Ranking (seleccion)");
		ordenarSeleccion2(misNaves, ordenCron);
		imprimir(misNaves, ordenCron);
		
	}
	//genera en el arreglo las naves
	public static void genNaves(Nave[][] arr, int n, int[][] orden){
		char[] colNum = {'A','B','C','D','E','F','G','H','I','J'}; 
		boolean[] filOcupadas = new boolean[10];
		boolean[] colOcupadas = new boolean[10];
		for(int i = 0; i < n; i++){
			String nombre = "Soldado" + "" + i;
			int vidaAle = (int)(Math.random() * 5 + 1);
			int col = (int)(Math.random() * 10); //genera numeros del 0 al 9
			while(colOcupadas[col]){
				col = (int)(Math.random() * 10); //genera numeros del 0 al 9
			}
			colOcupadas[col] = true;
			int fil = (int)(Math.random() * 10); //genera numeros del 0 al 9
			while(filOcupadas[fil]){
				fil = (int)(Math.random() * 10); //genera numeros del 0 al 9
			}
			filOcupadas[col] = true;
			orden[i][0] = fil;
			orden[i][1] = col;
			arr[fil][col] = new Nave(nombre, vidaAle, fil, colNum[col] + ""); 
		}
	}
	public static void imprimirTabla(Nave[][] arr){
		char[] aux = {'A', 'B','C','D','E','F','G','H','I', 'J'};
		
		System.out.print("\t");
		for(int i = 0; i < 10; i++){
			System.out.print(aux[i] + "\t");
		}

		System.out.println();
		for(int i = 0; i < arr.length; i++){
			System.out.print((i+1) + "\t");
			for(int j = 0; j < arr[i].length; j++){
				if(arr[i][j] != null){
					System.out.print("||" + "\t");
				}else{
					System.out.print('=' + "\t");
				
				}
			}
			System.out.println();
		}
	}
	public static Nave mayorVidaSoldado(Nave[][] arr){
		int maxVida = 0;
		int index1 = 0;
		int index2 = 0;
		for(int i = 0; i < arr.length; i++){
			for(int j = 0; j < arr[i].length; j++){
				if(arr[i][j] != null){
					maxVida = Math.max(maxVida, arr[i][j].getVida());
					if(maxVida == arr[i][j].getVida()){
						index1 = i;
						index2= j;
					}
				}
			}
		}
		return arr[index1][index2];
	}
	public static double promedioVidaSoldado(Nave[][] arr){
		int vidaTotal = 0;
		int cantidad = 0;
		double prom = 0;
		for(int i = 0; i < arr.length; i++){
			for(int j = 0; j < arr[i].length; j++){
				if(arr[i][j] != null){
					vidaTotal += arr[i][j].getVida();
					cantidad++;
				}
			}
		}
		prom = vidaTotal / (cantidad + 0.0);
		return prom;
	}
	public static int totalVidaSoldado(Nave[][] arr){
		int vidaTotal = 0;
		for(int i = 0; i < arr.length; i++){
			for(int j = 0; j < arr[i].length; j++){
				if(arr[i][j] != null){
					vidaTotal += arr[i][j].getVida();
				}
			}
		}
		return vidaTotal;
	}
	public static void imprimir(Nave[][] arr, int[][] orden){
		for(int i = 0; i < orden.length; i++){
			System.out.println(arr[orden[i][0]][orden[i][1]]);
		}
	}
	public static void ordenarBurbuja(Nave[][] arr, int[][] orden){
		for(int i = 0 ; i < orden.length; i++){
			for(int j = 0; j < orden.length - 1 - i; j++){
				int vidaActual = arr[orden[j][0]][orden[j][1]].getVida();
				int vidaSiguiente = arr[orden[j + 1][0]][orden[j + 1][1]].getVida();
				if(vidaActual < vidaSiguiente){
					int temp1 = orden[j][0];
					int temp2 = orden[j][1];
					orden[j][0] = orden[j+1][0];
					orden[j][1] = orden[j+1][1];
					orden[j+1][0] = temp1;
					orden[j+1][1] = temp2;
				}
			}	
		}
	}
	public static void ordenarSeleccion1(Nave[][] arr, int[][] orden){
		for(int i = 0 ; i < orden.length - 1; i++){
			int minIndex = i;
			for(int j = i+1; j < orden.length; j++){
				int vidaActual = arr[orden[j][0]][orden[j][1]].getVida();
				int vidaMin = arr[orden[minIndex][0]][orden[minIndex][1]].getVida();
				if(vidaActual < vidaMin){
					minIndex = j;
				}
			}
			int temp1 = orden[minIndex][0];
			int temp2 = orden[minIndex][1];
			orden[minIndex][0] = orden[i][0];
			orden[minIndex][1] = orden[i][1];
			orden[i][0] = temp1;
			orden[i][1] = temp2;
		}
	}
	public static void ordenarSeleccion2(Nave[][] arr, int[][] orden){
		for(int i = 0 ; i < orden.length - 1; i++){
			int maxIndex = i;
			for(int j = i+1; j < orden.length; j++){
				int vidaActual = arr[orden[j][0]][orden[j][1]].getVida();
				int vidaMin = arr[orden[maxIndex][0]][orden[maxIndex][1]].getVida();
				if(vidaActual > vidaMin){
					maxIndex = j;
				}
			}
			int temp1 = orden[maxIndex][0];
			int temp2 = orden[maxIndex][1];
			orden[maxIndex][0] = orden[i][0];
			orden[maxIndex][1] = orden[i][1];
			orden[i][0] = temp1;
			orden[i][1] = temp2;
		}
	}

	

}

