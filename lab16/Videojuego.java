import java.util.*;
public class Videojuego{
	public static void main(String[] args){
		Scanner sc = new Scanner(System.in);
		String salir = "no";
		do{
			Mapa terreno = new Mapa();
			terreno.imprimirMapa();
			//Primera metrica -> Sumatoria de vida
			System.out.println("Primera metrica -> Vida");
			int vida1 = sumarVida(terreno.getReino1());
			int vida2 = sumarVida(terreno.getReino2());
			System.out.println(terreno.getReino1Tipo() + ": "+ vida1);
			System.out.println(terreno.getReino2Tipo() + ": "+ vida2);
			if(vida1 > vida2){
				System.out.println("Victoria del reino de " + terreno.getReino1Tipo());
			}else if(vida1 < vida2){
				System.out.println("Victoria del reino de " + terreno.getReino2Tipo());
			}else{
				System.out.println("Empate");
			}
			//Segunda metrica -> Cantidad de soldados
			System.out.println("Segunda metrica -> Cantidad de Soldados");
			int sol1 = contarSoldados(terreno.getReino1());
			int sol2 = contarSoldados(terreno.getReino2());
			System.out.println(terreno.getReino1Tipo() + ": "+ sol1 );
			System.out.println(terreno.getReino2Tipo() + ": "+ sol2);
			if(sol1 > sol2){
				System.out.println("Victoria del reino de " + terreno.getReino1Tipo());
			}else if(sol1 < sol2){
				System.out.println("Victoria del reino de " + terreno.getReino2Tipo());
			}else{
				System.out.println("Empate");
			}
			//Tercera metrica -> Batalla con valores de ataque y defensa.
			System.out.println("Tercera métrica -> Batalla con valores de Ataque y Defensa");
			int ataque1 = sumarAtaque(terreno.getReino1());
			int defensa1 = sumarDefensa(terreno.getReino1());
			int ataque2 = sumarAtaque(terreno.getReino2());
			int defensa2 = sumarDefensa(terreno.getReino2());
			System.out.println(terreno.getReino1Tipo() + ": Ataque=" + ataque1 + ", Defensa=" + defensa1);
			System.out.println(terreno.getReino2Tipo() + ": Ataque=" + ataque2 + ", Defensa=" + defensa2);
			// Calcular el resultado de la batalla
			if (ataque1 > defensa2) {
				System.out.println("Victoria del reino de " + terreno.getReino1Tipo());
			} else if (ataque1 < defensa2) {
				System.out.println("Victoria del reino de " + terreno.getReino2Tipo());
			} else {
				System.out.println("Empate");
			}
			System.out.println("Desea salir, (si) para salir");
			salir = sc.next();
		}while(!salir.equals("si"));
	}
	public static int sumarVida(ArrayList<Ejercito> r){
		int v = 0;
		for (Ejercito ejercito : r) {
            for (Soldado soldado : ejercito.getSoldados()) {
				v+=soldado.getVidaActual();
            }
        }
		return v;
	}
	public static int contarSoldados(ArrayList<Ejercito> r){
		int c = 0;
		for (Ejercito ejercito : r) {
			c+= ejercito.getSoldados().size();
        }
		return c;
	}
	public static int sumarAtaque(ArrayList<Ejercito> r) {
        int ataqueTotal = 0;
        for (Ejercito ejercito : r) {
            for (Soldado soldado : ejercito.getSoldados()) {
                ataqueTotal += soldado.getNivelAtaque();
            }
        }
        return ataqueTotal;
    }

    public static int sumarDefensa(ArrayList<Ejercito> r) {
        int defensaTotal = 0;
        for (Ejercito ejercito : r) {
            for (Soldado soldado : ejercito.getSoldados()) {
                defensaTotal += soldado.getNivelDefensa();
            }
        }
        return defensaTotal;
    }
}
