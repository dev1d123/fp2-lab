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
	private static boolean encontrarSoldado(Ejercito e, int fila, int columna){
		for(Arquero a: e.getArqueros()){
			if(a.getFila() == fila && a.getColumna() == columna){
				return true;
			}
		}
		for(Espadachin a: e.getEspadachines()){
			if(a.getFila() == fila && a.getColumna() == columna){
				return true;
			}
		}
		for(Caballero a: e.getCaballeros()){
			if(a.getFila() == fila && a.getColumna() == columna){
				return true;
			}
		}
		return false;
	}
	private static Arquero encontrarArquero(Ejercito e, int fila, int columna){
		for(Arquero s: e.getArqueros()){
			if(s.getFila() == fila && s.getColumna() == columna){
				return s;
			}
		}
		return null;
	}
	private static Espadachin encontrarEspadachin(Ejercito e, int fila, int columna){
		for(Espadachin s: e.getEspadachines()){
			if(s.getFila() == fila && s.getColumna() == columna){
				return s;
			}
		}
		return null;
	}
	private static Caballero encontrarCaballero(Ejercito e, int fila, int columna){
		for(Caballero s: e.getCaballeros()){
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
		if(encontrarSoldado(enemigo, x, y)){
			System.out.println("Ha seleccionado a un enemigo!. Ingrese de nuevo");
			return true;
		}
		if(encontrarSoldado(amigo, tox, toy)){
			System.out.println("Se encontro a un aliado en esa direccion!. Ingrese de nuevo");
			return true;
		}
		if(!encontrarSoldado(amigo, x, y)){
			System.out.println("No hay ningun soldado aliado en esa seleccion!. Ingrese de nuevo");
			return true;
		}
		return false;
	}
	public static void mover(Ejercito mover, Ejercito noMover, int x, int y, int tox, int toy) {
		Arquero arqEnemigo = encontrarArquero(noMover, tox, toy);
		Espadachin espEnemigo = encontrarEspadachin(noMover, tox, toy);
		Caballero cabEnemigo = encontrarCaballero(noMover, tox, toy);
	
		Arquero arqEleccion = encontrarArquero(mover, x, y);
		Espadachin espEleccion = encontrarEspadachin(mover, x, y);
		Caballero cabEleccion = encontrarCaballero(mover, x, y);
	
		if (arqEleccion != null) {
			if (arqEnemigo == null && espEnemigo == null && cabEnemigo == null) {
				arqEleccion.setFila(tox);
				arqEleccion.setColumna(toy);
			} else {
				if(arqEnemigo != null){
					realizarBatalla(mover, noMover, arqEleccion, arqEnemigo,tox,toy);
				}else if(espEnemigo != null){
					realizarBatalla(mover, noMover, arqEleccion, espEnemigo,tox,toy);
				}else{
					realizarBatalla(mover, noMover, arqEleccion, cabEnemigo,tox,toy);
				}
			}
		} else if (espEleccion != null) {
			if (arqEnemigo == null && espEnemigo == null && cabEnemigo == null) {
				espEleccion.setFila(tox);
				espEleccion.setColumna(toy);
			} else {
				// Batalla(esp)
				if(arqEnemigo != null){
					realizarBatalla(mover, noMover, espEleccion, arqEnemigo,tox,toy);
				}else if(espEnemigo != null){
					realizarBatalla(mover, noMover, espEleccion, espEnemigo,tox,toy);
				}else{
					realizarBatalla(mover, noMover, espEleccion, cabEnemigo,tox,toy);
				}			
			}
		} else if (cabEleccion != null) {
			if (arqEnemigo == null && espEnemigo == null && cabEnemigo == null) {
				cabEleccion.setFila(tox);
				cabEleccion.setColumna(toy);
			} else {
				// Batalla(cab)
				if(arqEnemigo != null){
					realizarBatalla(mover, noMover, cabEleccion, arqEnemigo,tox,toy);
				}else if(espEnemigo != null){
					realizarBatalla(mover, noMover, cabEleccion, espEnemigo,tox,toy);
				}else{
					realizarBatalla(mover, noMover, cabEleccion, cabEnemigo,tox,toy);
				}			
			}
		}
	}
	
	private static void realizarBatalla(Ejercito mover, Ejercito noMover, Soldado soldadoEleccion, Soldado soldadoEnemigo, int tox, int toy) {
			// Batalla
			System.out.println("BATALLA!!!!");
			boolean resultado = batalla(soldadoEleccion.getVidaActual(), soldadoEnemigo.getVidaActual(), soldadoEleccion.getNombre(), soldadoEnemigo.getNombre());
			if (resultado) {
				if (soldadoEnemigo instanceof Arquero) {
					noMover.getArqueros().remove(soldadoEnemigo);
				} else if (soldadoEnemigo instanceof Espadachin) {
					noMover.getEspadachines().remove(soldadoEnemigo);
				} else if (soldadoEnemigo instanceof Caballero) {
					noMover.getCaballeros().remove(soldadoEnemigo);
				}
				soldadoEleccion.setFila(tox);
				soldadoEleccion.setColumna(toy);
				soldadoEleccion.setVidaActual(soldadoEleccion.getVidaActual() + 1);
			} else {
				if (soldadoEleccion instanceof Arquero) {
					mover.getArqueros().remove(soldadoEleccion);
				} else if (soldadoEleccion instanceof Espadachin) {
					mover.getEspadachines().remove(soldadoEleccion);
				} else if (soldadoEleccion instanceof Caballero) {
					mover.getCaballeros().remove(soldadoEleccion);
				}
				soldadoEnemigo.setVidaActual(soldadoEnemigo.getVidaActual() + 1);

			}
	}
	


	private static boolean batalla(int vida1, int vida2, String nombre1, String nombre2) {
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
		if((e2.getArqueros().size() + e2.getCaballeros().size() + e2.getEspadachines().size()) == 0){
			System.out.println("¡¡¡VICTORIA DEL EJERCITO 1!!!");
			return false;
		}
		if((e1.getArqueros().size() + e1.getCaballeros().size() + e1.getEspadachines().size()) == 0){
			System.out.println("¡¡¡VICTORIA DEL EJERCITO 2!!!");
			return false;
		}
		return true;
	}
}

