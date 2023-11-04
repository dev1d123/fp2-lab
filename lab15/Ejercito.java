import java.util.*;
public class Ejercito{
	private String reino;
	private ArrayList<Soldado> misSoldados= new ArrayList<Soldado>();
	public Ejercito(String r){
		reino = r;
	}
	public Ejercito(){}
	public void setReino(String r){
		this.reino = r;
	}
	public String getReino(){
		return reino;
	}
	public String toString(){
		String datosSoldados = "";
		for(Soldado s: misSoldados){
			datosSoldados += "Nombre: " + s.getNombre() +
							"\nVida: " + s.getVidaActual() +
				   			"\nFila: " + s.getFila() +
							"\nColumna: " + s.getColumna() + "\n";
		}
		return "Nombre del Reino: " + reino + 
				"\nDatos de los soldados: \n" +
				datosSoldados;
	}
	public void generarAuto(){
		boolean[][] posiciones = new boolean[10][10];
		int num = (int)(Math.random()*9 + 1);
		for(int i = 0; i < num; i++){
			int fila = 0;
			int columna = 0;
			do{
				fila = (int)(Math.random()*9 + 1);
				columna = (int)(Math.random()*9 + 1);
			}while(posiciones[fila][columna]);
			posiciones[fila][columna] = true;
			String nombreSol = "Soldado"+(i+1)+"x"+this.reino;
			Soldado s = new Soldado(nombreSol, fila, columna);
			misSoldados.add(s);
		}	
	}
	public void generarMan(){
		Scanner sc = new Scanner(System.in);
		System.out.print("Ingrese cuantos soldados desea ingresar: ");
		int num = sc.nextInt();
		boolean[][] posiciones = new boolean[10][10];
		for(int i = 0; i < num; i++){
			System.out.print("Ingrese la fila del soldado "+(i+1) +": ");
			int fila = sc.nextInt();
			System.out.print("Ingrese la columna del soldado "+(i+1) +": ");
			int columna = sc.nextInt();
			System.out.print("Ingrese el ataque del soldado "+(i+1) +": ");
			int ataque = sc.nextInt();
			System.out.print("Ingrese la vida del soldado "+(i+1) +": ");
			int vida = sc.nextInt();
			System.out.print("Ingrese la defensa del soldado "+(i+1) +": ");
			int defensa = sc.nextInt();
			System.out.print("Ingrese el nombre del soldado "+(i+1) +": ");
			String nombreSol = sc.next();
			Soldado s = new Soldado(nombreSol, fila, columna, vida, ataque, defensa);
			misSoldados.add(s);
		}	
	}
	public int buscarNombre(String nombre){
		int count = 0;
		for(Soldado s: misSoldados){
			if(s.getNombre().equals(nombre)){
				return count;
			}
			count++;
		}
		System.out.println("No se encontro a ese soldado");
		return -1;
	}
	public Soldado getSoldadoIndice(int i){
		return misSoldados.get(i);
	}
	public void removerSoldado(int i){
		misSoldados.remove(i);
	}
	public Soldado soldadoMayorVida(){
		int vida = 0;
		int indice = 0;
		int aux = 0;
		for(Soldado s: misSoldados){
			vida = Math.max(s.getVidaActual(), vida);
			if(vida == s.getVidaActual()){
				indice = aux;
			}
			aux++;
		}
		return misSoldados.get(indice);
	}
	public void ordenarBubble(){
		int n = misSoldados.size();
		for(int i = 0; i < n; i++){
			for(int j = 0; j < n - i - 1; j++){
				if(misSoldados.get(j).getVidaActual() < misSoldados.get(j+1).getVidaActual()){
					Soldado temp = misSoldados.get(j);
					misSoldados.set(j, misSoldados.get(j+1));
					misSoldados.set(j+1, temp);
				}
			}
		}
	}
	public void imprimirSoldados(){
		for(Soldado s: misSoldados){
			System.out.println("Nombre: "+ s.getNombre() + "\tVida: " + s.getVidaActual());
		}
	}
	
}
