import java.util.*;
public class Ejercito{
	private Soldado[] ejercito1;
	private ArrayList<Soldado> ejercito2 = new ArrayList<Soldado>();

	private int reino1 = (int)(Math.random()*5 +1);
	private int reino2 = (int)(Math.random()*5 +1);
	/* 
	1 -> Inglaterra
	2 -> Francia
	3 -> Castilla-Aragon
	4 -> Moros
	5 -> SIRG
	*/
	public Ejercito() {
        do {
            reino2 = (int)(Math.random()*5 + 1);
        } while (reino1 == reino2);
    }

	public ArrayList<Soldado> getSoldados(){
		return ejercito2;
	}

	public int getCantidadEsp(){
		int ans = 0;
		for(Soldado s: ejercito2){
			if(s instanceof Espadachin){
				ans++;
			}
		}
		return ans;
	}
	public int getCantidadArq(){
		int ans = 0;
		for(Soldado s: ejercito2){
			if(s instanceof Arquero){
				ans++;
			}
		}
		return ans;
	}
	public int getCantidadCab(){
		int ans = 0;
		for(Soldado s: ejercito2){
			if(s instanceof Caballero){
				ans++;
			}
		}
		return ans;
	}
	public int getCantidadLan(){
		int ans = 0;
		for(Soldado s: ejercito2){
			if(s instanceof Lancero){
				ans++;
			}
		}
		return ans;
	}
	public String toString(){
		String datos = "El ejercito posee " + ejercito2.size()+"\n";
		String datosSol= genDatosSoldado();
		return datos + datosSol;
	}
	private String genDatosSoldado() {
		String ans ="Datos de los soldados\n";
		for(Soldado c: ejercito2){
			ans+=c+"\n";
		}
		return ans;
	}

	public double promedioPuntosEjercito(){
		double sum=0;
		for(Soldado s: ejercito2){
			sum+=s.getVidaActual();
		}
		return (sum/ejercito2.size());
	}
	public String mayorVidaEjercito(){
		int max = 0;
		int index = 0;
		int count = 0;
		for(Soldado s: ejercito2){
			max = Math.max(max, s.getVidaActual());
			if(max == s.getVidaActual()){
				index = count;
			}
			count++;
		}
		return ("El soldado con mayor vida es " + ejercito2.get(index));
	}
	public void ranking(){
		int n = ejercito2.size();
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n - 1 - i; j++) {
				if(ejercito2.get(j).getVidaActual() < ejercito2.get(j+1).getVidaActual()){
					Soldado temp = ejercito2.get(j);
					ejercito2.set(j, ejercito2.get(j + 1));
					ejercito2.set(j + 1, temp);

				}
			}
		}
		System.out.println("Soldados ordenados por ranking de vida");
		for(Soldado s: ejercito2){
			System.out.println("Nombre: "+ s.getNombre() + "\tVida: " + s.getVidaActual());
		}
	}
	public int getReino1(){
		return reino1;
	}
	public int getReino2(){
		return reino1;
	}
	//metodos para el arreglo estandar
	public Soldado[] getSoldadosArray(){
		return ejercito1;
	}
	public void setSoldadoArr(int pos, Soldado s) {
        ejercito1[pos] = s;
    }
	public void inicializar(int numeroSoldados){
		ejercito1 = new Soldado[numeroSoldados];
	}
    public int getCantidadEspArray() {
        int ans = 0;
        for (Soldado s : ejercito1) {
            if (s instanceof Espadachin) {
                ans++;
            }
        }
        return ans;
    }

    public int getCantidadArqArray() {
        int ans = 0;
        for (Soldado s : ejercito1) {
            if (s instanceof Arquero) {
                ans++;
            }
        }
        return ans;
    }

    public int getCantidadCabArray() {
        int ans = 0;
        for (Soldado s : ejercito1) {
            if (s instanceof Caballero) {
                ans++;
            }
        }
        return ans;
    }

    public int getCantidadLanArray() {
        int ans = 0;
        for (Soldado s : ejercito1) {
            if (s instanceof Lancero) {
                ans++;
            }
        }
        return ans;
    }

    public String toStringArray() {
        String datos = "El ejército posee " + ejercito1.length + "\n";
        String datosSol = genDatosSoldadoArray();
        return datos + datosSol;
    }

    private String genDatosSoldadoArray() {
        String ans = "Datos de los soldados\n";
        for (Soldado c : ejercito1) {
            ans += c + "\n";
        }
        return ans;
    }

    public double promedioPuntosEjercitoArray() {
        double sum = 0;
        for (Soldado s : ejercito1) {
            sum += s.getVidaActual();
        }
        return (sum / ejercito1.length);
    }

    public String mayorVidaEjercitoArray() {
        int max = 0;
        int index = 0;
        int count = 0;
        for (Soldado s : ejercito1) {
            max = Math.max(max, s.getVidaActual());
            if (max == s.getVidaActual()) {
                index = count;
            }
            count++;
        }
        return ("El soldado con mayor vida es " + ejercito1[index]);
    }

    public void rankingArray() {
        int n = ejercito1.length;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n - 1 - i; j++) {
                if (ejercito1[j].getVidaActual() < ejercito1[j + 1].getVidaActual()) {
                    Soldado temp = ejercito1[j];
                    ejercito1[j] = ejercito1[j + 1];
                    ejercito1[j + 1] = temp;
                }
            }
        }
        System.out.println("Soldados ordenados por ranking de vida");
        for (Soldado s : ejercito1) {
            System.out.println("Nombre: " + s.getNombre() + "\tVida: " + s.getVidaActual());
        }
    }
	public int getVidaArray(){
		int ans = 0;
		for(Soldado s: ejercito1){
			ans += s.getVidaActual();
		}
		return ans;
	}
	public int getVida(){
		int ans = 0;
		for(Soldado s: ejercito2){
			ans += s.getVidaActual();
		}
		return ans;
	}
}

