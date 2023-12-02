import java.util.*;
public class Ejercito{
	private ArrayList<Soldado> ejercito = new ArrayList<Soldado>();
	private int reino;
	public ArrayList<Soldado> getSoldados(){
		return ejercito;
	}
	public int getCantidadEsp(){
		int ans = 0;
		for(Soldado s: ejercito){
			if(s instanceof Espadachin){
				ans++;
			}
		}
		return ans;
	}
	public int getCantidadArq(){
		int ans = 0;
		for(Soldado s: ejercito){
			if(s instanceof Arquero){
				ans++;
			}
		}
		return ans;
	}
	public int getCantidadCab(){
		int ans = 0;
		for(Soldado s: ejercito){
			if(s instanceof Caballero){
				ans++;
			}
		}
		return ans;
	}
	public int getCantidadLan(){
		int ans = 0;
		for(Soldado s: ejercito){
			if(s instanceof Lancero){
				ans++;
			}
		}
		return ans;
	}
	public String toString(){
		String datos = "El ejercito posee " + ejercito.size()+"\n";
		String datosSol= genDatosSoldado();
		return datos + datosSol;
	}
	private String genDatosSoldado() {
		String ans ="Datos de los soldados\n";
		for(Soldado c: ejercito){
			ans+=c+"\n";
		}
		return ans;
	}
	public int getReino(){
		return reino;
	}
	public int getVida(){
		int ans = 0;
		for(Soldado s: ejercito){
			ans += s.getVidaActual();
		}
		return ans;
	}
	public void setReino(int r){
		reino = r;
	}

	//metodos para realizar el juego
	private static Soldado encontrarSoldado(Ejercito e, int fila, int columna){
		for(Soldado s: e.getSoldados()){
			if(s.getFila() == fila && s.getColumna() == columna){
				return s;
			}
		}
		return null;
	}

	public static boolean validar(Ejercito amigo, Ejercito enemigo, int x, int y, int tox, int toy){
		if ((x < 0 || x > 9) || (y < 0 || y > 9)){
			System.out.println("Limites del mapa excedidos");
			return true;
		}
        if ((tox < 0 || tox > 9) || (toy < 0 || toy > 9)){
			System.out.println("Limites del mapa excedidos");
			return true;
		}		
		if(encontrarSoldado(enemigo, x, y) != null){
			System.out.println("Ha seleccionado a un enemigo!. Ingrese de nuevo");
			return true;
		}
		if(encontrarSoldado(amigo, tox, toy) != null){
			System.out.println("Se encontro a un aliado en esa direccion!. Ingrese de nuevo");
			return true;
		}
		if(encontrarSoldado(amigo, x, y) == null){
			System.out.println("No hay ningun soldado aliado en esa seleccion!. Ingrese de nuevo");
			return true;
		}
		return false;
	}

	public static void mover(Ejercito mover, Ejercito noMover, int x, int y, int tox, int toy) {
		Soldado solEnemigo = encontrarSoldado(noMover, tox, toy);
		Soldado solEleccion = encontrarSoldado(mover, x, y);
	
		if (solEnemigo == null) {
			solEleccion.setFila(tox);
			solEleccion.setColumna(toy);
		} else {
			realizarBatalla(mover, noMover, solEleccion, solEnemigo,tox,toy);
		}
		
		
	}
	
	private static void realizarBatalla(Ejercito mover, Ejercito noMover, Soldado soldadoEleccion, Soldado soldadoEnemigo, int tox, int toy) {
			System.out.println("BATALLA!!!!");
			boolean resultado = batalla(soldadoEleccion, soldadoEnemigo, soldadoEleccion.getNombre(), soldadoEnemigo.getNombre());
			if (resultado) {
				noMover.getSoldados().remove(soldadoEnemigo);
				soldadoEleccion.setFila(tox);
				soldadoEleccion.setColumna(toy);
				soldadoEleccion.setVidaActual(soldadoEleccion.getVidaActual() + 1);
			} else {
				mover.getSoldados().remove(soldadoEleccion);
				soldadoEnemigo.setVidaActual(soldadoEnemigo.getVidaActual() + 1);

			}
	}
	
	private static boolean batalla(Soldado a1, Soldado a2, String nombre1, String nombre2) {
		int vida1 = a1.getVidaActual();
		int vida2 = a2.getVidaActual();
		if(a1 instanceof Caballero && a2 instanceof Arquero){
			if(a1 instanceof CaballeroFranco || a1 instanceof CaballeroMoro){
				vida1+=2;
			}else{
				vida1++;
			}
		}
		if(a1 instanceof Caballero && a2 instanceof Lancero){
			if(!(a1 instanceof CaballeroFranco || a1 instanceof CaballeroMoro)){
				vida2++;
			}
		}
		if(a1 instanceof Arquero && a2 instanceof Lancero){
			vida1++;
		}
		if(a1 instanceof Caballero && a2 instanceof Espadachin){
			vida1++;
		}
		if(a1 instanceof Espadachin && a2 instanceof Lancero){
			if(a1 instanceof EspadachinReal || a1 instanceof EspadachinTeutonico || a1 instanceof EspadachinConquistador){
				vida1+=2;
			}else{
				vida1++;
			}
		}
		if(a1 instanceof Espadachin && (a2 instanceof EspadachinReal || a2 instanceof EspadachinTeutonico || a2 instanceof EspadachinConquistador)){
			vida2++;
		}
		if(a1 instanceof Caballero && (a2 instanceof CaballeroFranco || a2 instanceof CaballeroMoro)){
			vida2++;
		}
		if(a2 instanceof Caballero && a1 instanceof Arquero){
			if(a2 instanceof CaballeroFranco || a2 instanceof CaballeroMoro){
				vida2 += 2;
			} else {
				vida2++;
			}
		}
		if(a2 instanceof Caballero && a1 instanceof Lancero){
			if(!(a2 instanceof CaballeroFranco || a2 instanceof CaballeroMoro)){
				vida1++;
			}
		}
		if(a2 instanceof Arquero && a1 instanceof Lancero){
			vida2++;
		}
		if(a2 instanceof Caballero && a1 instanceof Espadachin){
			vida2++;
		}
		if(a2 instanceof Espadachin && a1 instanceof Lancero){
			if(a2 instanceof EspadachinReal || a2 instanceof EspadachinTeutonico || a2 instanceof EspadachinConquistador){
				vida2 += 2;
			} else {
				vida2++;
			}
		}
		if(a2 instanceof Espadachin && (a1 instanceof EspadachinReal || a1 instanceof EspadachinTeutonico || a1 instanceof EspadachinConquistador)){
			vida1++;
		}
		if(a2 instanceof Caballero && (a1 instanceof CaballeroFranco || a1 instanceof CaballeroMoro)){
			vida1++;
		}
		double probabilidadGanar = (double) vida1 / (vida1 + vida2);
		double probabilidadPerder = 1.0 - probabilidadGanar;
		double resultadoAleatorio = Math.random();
	
		System.out.println(nombre1 + ": " + vida1 + "\t\t" +  nombre2 +": " + vida2);
		System.out.println("Probabilidades de victoria:");
		System.out.println(nombre1+": " + (probabilidadGanar * 100) + "%\t" + nombre2 +": " + (probabilidadPerder * 100) + "%");
	
		if (resultadoAleatorio < probabilidadGanar) {
			System.out.println(nombre1 + " gana la batalla");
			return true;
		} else {
			System.out.println(nombre2 + " gana la batalla");
			return false;
		}
	}

	public static boolean winner(Ejercito e1, Ejercito e2){
		if(e2.getSoldados().size() == 0){
			System.out.println("¡¡¡VICTORIA DEL EJERCITO 1!!!");
			return false;
		}
		if(e1.getSoldados().size() == 0){
			System.out.println("¡¡¡VICTORIA DEL EJERCITO 2!!!");
			return false;
		}
		return true;
	}
}

