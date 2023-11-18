import java.util.*;
public class Ejercito{
	private ArrayList<Arquero> misArqueros = new ArrayList<Arquero>();
	private ArrayList<Espadachin> misEspadachines = new ArrayList<Espadachin>();
	private ArrayList<Caballero> misCaballeros = new ArrayList<Caballero>();

	private ArrayList<Integer>vidas=new ArrayList<Integer>();
	private ArrayList<String>nombres=new ArrayList<String>();
	public ArrayList<Arquero> getArqueros(){
		return misArqueros;
	}
	public ArrayList<Espadachin> getEspadachines(){
		return misEspadachines;
	}
	public ArrayList<Caballero> getCaballeros(){
		return misCaballeros;
	}

	public String toString(){
		String datos = "El ejercito posee " + (misArqueros.size() + misCaballeros.size() + misEspadachines.size())+" soldados\n";
		String datosArq= genDatosArq();
		String datosEsp = genDatosEsp();
		String datosCab = genDatosCab();
		return datos + datosArq + datosEsp + datosCab;
	}
	private String genDatosCab() {
		String ans ="Datos de Caballeros\n";
		for(Caballero c: misCaballeros){
			ans+="Nombre: " + c.getNombre() + " \tVida: " + c.getVidaActual()+"\n";

			vidas.add(c.getVidaActual());
			nombres.add(c.getNombre());
		}
		return ans;
	}
	private String genDatosEsp() {
		String ans ="Datos de Espadachines\n";
		for(Espadachin c: misEspadachines){
			ans+="Nombre: " + c.getNombre() + " \tVida: " + c.getVidaActual()+"\n";

			vidas.add(c.getVidaActual());
			nombres.add(c.getNombre());
		}
		return ans;
	}
	private String genDatosArq() {
		String ans ="Datos de Arqueros\n";
		for(Arquero c: misArqueros){
			ans+="Nombre: " + c.getNombre() + " \tVida: " + c.getVidaActual()+"\n";

			vidas.add(c.getVidaActual());
			nombres.add(c.getNombre());
		}
		return ans;
	}
	public String rankingPoder(){
		String ans = "";
		ranking();
		for(int i = 0; i < (misArqueros.size() + misCaballeros.size() + misEspadachines.size()); i++){
			ans+=nombres.get(i) + " \t " + vidas.get(i)+"\n";
		}
		return ans;

	}
	public String soldadoMayorVida(){
		int index = 0;
		int maxVida = 0;
		for(int i = 0; i < vidas.size(); i++){
			maxVida = Math.max(maxVida, vidas.get(i));
			if(maxVida == vidas.get(i)){
				index = i;
			}
		}
		return "El soldado con mayor vida es " + nombres.get(index);
	}
	//Para hacer el ranking, se utilizaron 2 arreglos, el arreglo de nombres, y el arreglo de sus vidas
	//se ordena en base a las vidas y cualquier cambio que se haga en este se hace tambien en vida
	private void ranking() {
		int n = vidas.size();
	
		for (int i = 0; i < n - 1; i++) {
			for (int j = 0; j < n - i - 1; j++) {
				if (vidas.get(j) < vidas.get(j + 1)) {
					// Intercambiar vidas
					int tempVida = vidas.get(j);
					vidas.set(j, vidas.get(j + 1));
					vidas.set(j + 1, tempVida);
	
					// Intercambiar nombres
					String tempNombre = nombres.get(j);
					nombres.set(j, nombres.get(j + 1));
					nombres.set(j + 1, tempNombre);
				}
			}
		}
	}
	public double promedioVida(){
		double sum = 0;
		for(int i: vidas){
			sum+=i;
		}
		return sum/vidas.size();
	}

}
