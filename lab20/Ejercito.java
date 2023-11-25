import java.util.*;
public class Ejercito{
	private ArrayList<Soldado> misSoldados = new ArrayList<Soldado>();
	public ArrayList<Soldado> getSoldados(){
		return misSoldados;
	}
	public int getCantidadEsp(){
		int ans = 0;
		for(Soldado s: misSoldados){
			if(s instanceof Espadachin){
				ans++;
			}
		}
		return ans;
	}
	public int getCantidadArq(){
		int ans = 0;
		for(Soldado s: misSoldados){
			if(s instanceof Arquero){
				ans++;
			}
		}
		return ans;
	}
	public int getCantidadCab(){
		int ans = 0;
		for(Soldado s: misSoldados){
			if(s instanceof Caballero){
				ans++;
			}
		}
		return ans;
	}
	public int getCantidadLan(){
		int ans = 0;
		for(Soldado s: misSoldados){
			if(s instanceof Lancero){
				ans++;
			}
		}
		return ans;
	}
	public String toString(){
		String datos = "El ejercito posee " + misSoldados.size()+"\n";
		String datosSol= genDatosSoldado();
		return datos + datosSol;
	}
	private String genDatosSoldado() {
		String ans ="Datos de los soldados\n";
		for(Soldado c: misSoldados){
			ans+=c+"\n";
		}
		return ans;
	}

	public double promedioPuntosEjercito(){
		double sum=0;
		for(Soldado s: misSoldados){
			sum+=s.getVidaActual();
		}
		return (sum/misSoldados.size());
	}
	public String mayorVidaEjercito(){
		int max = 0;
		int index = 0;
		int count = 0;
		for(Soldado s: misSoldados){
			max = Math.max(max, s.getVidaActual());
			if(max == s.getVidaActual()){
				index = count;
			}
			count++;
		}
		return ("El soldado con mayor vida es " + misSoldados.get(index));
	}
	public void ranking(){
		int n = misSoldados.size();
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n - 1 - i; j++) {
				if(misSoldados.get(j).getVidaActual() < misSoldados.get(j+1).getVidaActual()){
					Soldado temp = misSoldados.get(j);
					misSoldados.set(j, misSoldados.get(j + 1));
					misSoldados.set(j + 1, temp);

				}
			}
		}
		System.out.println("Soldados ordenados por ranking de vida");
		for(Soldado s: misSoldados){
			System.out.println("Nombre: "+ s.getNombre() + "\tVida: " + s.getVidaActual());
		}
	}
}

