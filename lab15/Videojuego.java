import java.util.*;
public class Videojuego{
	public static void main(String[] args){
		Scanner sc = new Scanner(System.in);
		Ejercito prueba = new Ejercito("Francia");
		int opt = -1;
		do{
			opt = menu();
			switch(opt){
				case 1:
					prueba.generarMan();
					break;
				case 2:
					prueba.generarAuto();
					break;
				case 3:
					System.out.println("Ingrese el nombre del soldado que desea modificar: ");
					String nombre = sc.next();
					Soldado elegido = prueba.getSoldadoIndice(prueba.buscarNombre(nombre));
					if(elegido != null){
						int opcion = menuModificar();
						switch (opcion){
							case 1:
								System.out.println("Ingrese la nueva fila");
								int f = sc.nextInt();
								elegido.setFila(f);
								break;
							case 2:
								System.out.println("Ingrese la nueva columna");
								int c = sc.nextInt();
								elegido.setColumna(c);
								break;
							case 3:
								System.out.println("Ingrese la nueva vida");
								int v = sc.nextInt();
								elegido.setVidaActual(v);
								break;
							case 4:
								System.out.println("Ingrese la nueva defensa");
								int d = sc.nextInt();
								elegido.setNivelDefensa(d);
								break;
							case 5:
								System.out.println("Ingrese el nuevo nivel de ataque");
								int a = sc.nextInt();
								elegido.setNivelAtaque(a);
								break;
							case 6:
								System.out.println("Ingrese el nuevo nombre");
								String n = sc.next();
								elegido.setNombre(n);
								break;
						}
					}
					break;
				case 4:
					System.out.println("Ingrese el nombre del soldado que desea eliminar: ");
					nombre = sc.next();
					int indiceElegido = prueba.buscarNombre(nombre);
					prueba.removerSoldado(indiceElegido);
					break;
				case 5:
					System.out.println("El soldado con mayor vida");
					System.out.println(prueba.soldadoMayorVida());
					break;
				case 6:
					System.out.println("Mostrando soldados en orden");
					prueba.ordenarBubble();
					prueba.imprimirSoldados();
					break;
				case 7:
					System.out.println(prueba);
					break;
			}
		}
		while(opt != 8);
	}
	public static int menu(){
		Scanner sc = new Scanner(System.in);
		int opt = -1;
		System.out.println("****GESTION DE EJERCITO****");
		System.out.println("1) Generar ejercito manualmente");
		System.out.println("2) Generar ejercito aleatoriamente");
		System.out.println("3) Modificar soldado");
		System.out.println("4) Eliminar soldado");
		System.out.println("5) Consultar soldado mayor vida");
		System.out.println("6) Ranking de poder");
		System.out.println("7) Ver todos los datos del ejercito");
		System.out.println("8) SALIR");
		do{
			System.out.println("Ingrese una opcion");
			opt = sc.nextInt();
		}while(0 > opt || opt > 8);
		return opt;
	}
	public static int menuModificar(){
		Scanner sc = new Scanner(System.in);
		int opt = -1;
		System.out.println("****GESTION DE SOLDADO****");
		System.out.println("1) Modificar fila");
		System.out.println("2) Moficar columna");
		System.out.println("3) Modificar vida");
		System.out.println("4) Modificar ataque");
		System.out.println("5) Modificar defensa");
		System.out.println("6) Modificar nombre");
		do{
			System.out.println("Ingrese una opcion");
			opt = sc.nextInt();
		}while(0 > opt || opt > 6);
		return opt;
	}

}
