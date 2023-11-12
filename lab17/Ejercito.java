import java.util.*;
public class Ejercito{
	private String reino;
	private ArrayList<Soldado> misSoldados = new ArrayList<Soldado>();
	private int numSol;
	private int fila;
	private int columna;
	public Ejercito(int f, int c){
		fila = f;
		columna = c;
	}
	public String toString(){
		return "Nombre del Reino: " + reino + 
				"\nNumeros de Soldados: "+numSol;
	}
	public ArrayList<Soldado> getSoldados(){
		return misSoldados;
	}
	public void setReino(int s){
		if(s == 1){
			reino = "Inglaterra";
		}else if(s == 2){
			reino = "Francia";
		}else if(s == 3){
			reino = "Castilla-Aragon";
		}else if(s == 4){
			reino = "Moros";
		}else{
			reino = "SIRG";
		}
	}
	public int getFila(){
		return fila;
	}	
	public int getColumna(){
		return columna;
	}
	
    public void setFila(int fila) {
        this.fila = fila;
    }
    public void setColumna(int columna) {
        this.columna = columna;
    }
	public String getReino(){
		return reino;
	}

}
