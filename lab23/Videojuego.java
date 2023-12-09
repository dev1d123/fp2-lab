import java.awt.Window;

import javax.swing.JOptionPane;
public class Videojuego{
	public static void main(String[] args){
		int option;
		do{
			Mapa terreno = new Mapa();
			Tablero tabla = new Tablero(terreno.getEjercito1(), terreno.getEjercito2());
			terreno.imprimirDatosTerreno();
			terreno.imprimirDatosFinales();
			juego(terreno, tabla);
            option = JOptionPane.showConfirmDialog(null, "¿Desea salir?", "Salir", JOptionPane.YES_NO_OPTION);

			Window[] windows = Window.getWindows();
			for (Window window : windows) {
				window.dispose();
			}

		}while (option == JOptionPane.NO_OPTION);
	
        System.exit(0);
	}
	public static void juego(Mapa terreno, Tablero tabla){
		Ejercito e1 = terreno.getEjercito1();
		Ejercito e2 = terreno.getEjercito2();
		int turno = 0;
		JOptionPane.showMessageDialog(null, "Bienvenido al simulador");
		tabla.repintarTablero();
		do{
			if(turno%2==0){
				int x=0, y=0, tox=0, toy=0;
				do{
					JOptionPane.showMessageDialog(null, "Turno del reino Azul");
					int arr[] = tabla.getCoordenadas();
					x = arr[0];
					y = arr[1];
					int toarr[] = tabla.getCoordenadas();
					tox = toarr[0];
					toy = toarr[1];
					x--;y--;tox--;toy--;
				}while(Ejercito.validar(e1, e2, x,y,tox,toy));
				Ejercito.mover(e1, e2, x,y,tox, toy);
			}else{
				int x=0, y=0, tox=0, toy=0;
				do{
					JOptionPane.showMessageDialog(null, "Turno del reino Rojo");
					int arr[] = tabla.getCoordenadas();
					x = arr[0];
					y = arr[1];
					int toarr[] = tabla.getCoordenadas();
					tox = toarr[0];
					toy = toarr[1];		
					x--;y--;tox--;toy--;
				}while(Ejercito.validar(e2, e1, x,y,tox,toy));
				Ejercito.mover(e2, e1, x,y,tox, toy);
			}
			tabla.repintarTablero();
			turno++;
		}while(Ejercito.winner(e1, e2));
	}
}


