import java.util.*;
public class Mapa {
    private ArrayList<Ejercito> reino1 = new ArrayList<Ejercito>();
    private ArrayList<Ejercito> reino2 = new ArrayList<Ejercito>();
    private String reino1Tipo;
    private String reino2Tipo;
    private char[][] tablero = new char[10][10];
    private String tipoTerreno;

    //Se utiliza un arreglo bidimensional como atributo para generar filas y columnas no repetidas para ambos reino
    private boolean[][] posiciones = new boolean[10][10];
    private boolean[][] posicionesSoldados = new boolean[10][10];


    public Mapa(){
        generarEjercito(reino1);
        generarEjercito(reino2);
        generarReinos(reino1, reino2);
        int num = (int) (Math.random() * 5) + 1;
        if(num == 1){
            tipoTerreno = "Bosque";
        }else if(num == 2){
            tipoTerreno = "Campo Abierto";
        }else if(num == 3){
            tipoTerreno = "Montaña";
        }else if(num == 4){
            tipoTerreno = "Desierto";
        }else if(num == 5){
            tipoTerreno = "Playa";
        }
        setVentaja(tipoTerreno);
        genTablero();
    }
    public void genTablero(){
		//inicializamos el tablero.
		for(int i = 0; i < 10; i++){
			for(int j = 0; j < 10; j++){
				tablero[i][j] = '.';
			}
		}	
		for(int i = 0; i < reino1.size(); i++){
			int fila = reino1.get(i).getFila();
			int columna = reino1.get(i).getColumna();
			tablero[fila][columna] = '#';
		}
		for(int i = 0; i < reino2.size(); i++){
			int fila = reino2.get(i).getFila();
			int columna = reino2.get(i).getColumna();
			tablero[fila][columna] = '*';
		}

	}
    public void imprimirMapa(){
		char[] aux = {'A','B','C','D','E','F','G','H','I', 'J'};
		for(char c: aux){
			System.out.print("\t"+c);
		}
		System.out.println();
		for(int i = 0; i < 10; i++){
			System.out.print(i+1 +"\t");
			for(int j = 0; j < 10; j++){
				System.out.print(tablero[i][j] + "\t");
			}
			System.out.println();
		}
    }

    //Debe ubicar aleatoriamente a los ejercitos de los reinos y a sus soldados.
    private void generarEjercito(ArrayList<Ejercito> reino1){
        //Generar numero de Ejercitos
        int numEjercito = (int)(Math.random()*9 + 1);
        for(int i = 0; i < numEjercito; i++){
            //Generamos todos los atributos de un Ejercito.
            int fila = 0;
			int columna = 0;
			do{
				fila = (int)(Math.random()*9);
				columna = (int)(Math.random()*9);
			}while(posiciones[fila][columna]);
			posiciones[fila][columna] = true;
			Ejercito e = new Ejercito(fila, columna);
            //Ahora se genera el numero de soldados y sus respectivos atributos
            generarSoldados(e.getSoldados());
			reino1.add(e);
        }
    }
    private void generarSoldados(ArrayList<Soldado> sol){
        int numSoldados = (int)(Math.random()*9 + 1);
        for(int i = 0; i < numSoldados; i++){
            //Generamos todos los atributos de un Soldado.
            String nombre = "Soldado"+i;
            int fila = 0;
			int columna = 0;
			do{
				fila = (int)(Math.random()*9);
				columna = (int)(Math.random()*9);
			}while(posicionesSoldados[fila][columna]);
			posicionesSoldados[fila][columna] = true;
            int nivelAtaque = (int)(Math.random() * 5 + 1);
            int nivelDefensa = (int)(Math.random() * 5 + 1);
            int vidaActual = (int)(Math.random() * 5 + 1);
			Soldado s = new Soldado(nombre, fila, columna, vidaActual, nivelAtaque, nivelDefensa);
			sol.add(s);
        }
    }

    private void generarReinos(ArrayList<Ejercito> reino1, ArrayList<Ejercito> reino2){
        int a1 = (int) (Math.random() * 5) + 1;
        int a2 = a1;
        while (a1 == a2) {
            a2 = (int) (Math.random() * 5) + 1;
        }
        setReino1(a1);
        setReino2(a2);
        for(Ejercito e: reino1){
            e.setReino(a1);
        }
        for(Ejercito e: reino2){
            e.setReino(a2);
        }
    }

    public void setVentaja(String tipo){
        // Verificar si el tipo de terreno proporciona un bonus a alguno de los reinos
        if (tipo.equals("Bosque")) {
            if (reino1Tipo.equals("Inglaterra") || reino2Tipo.equals("Sacro Imperio Romano-Germánico")) {
                aplicarBonusVida(reino1);
                aplicarBonusVida(reino2);
            }
        } else if (tipo.equals("Campo Abierto")) {
            if (reino1Tipo.equals("Francia") || reino2Tipo.equals("Sacro Imperio Romano-Germánico")) {
                aplicarBonusVida(reino1);
                aplicarBonusVida(reino2);
            }
        } else if (tipo.equals("Montaña")) {
            if (reino1Tipo.equals("Castilla-Aragon")) {
                aplicarBonusVida(reino1);
            } else if (reino2Tipo.equals("Castilla-Aragon")) {
                aplicarBonusVida(reino2);
            }
        } else if (tipo.equals("Desierto")) {
            if (reino1Tipo.equals("Moros")) {
                aplicarBonusVida(reino1);
            } else if (reino2Tipo.equals("Moros")) {
                aplicarBonusVida(reino2);
            }
        } else if (tipo.equals("Playa")) {
            if (reino1Tipo.equals("Sacro Imperio Romano-Germánico")) {
                aplicarBonusVida(reino1);
            } else if (reino2Tipo.equals("Sacro Imperio Romano-Germánico")) {
                aplicarBonusVida(reino2);
            }
        }
    }

    private void aplicarBonusVida(ArrayList<Ejercito> reino) {
        for (Ejercito ejercito : reino) {
            for (Soldado soldado : ejercito.getSoldados()) {
                int nuevaVida = soldado.getVidaActual() + 1;
                soldado.setVidaActual(nuevaVida);
            }
        }
    }
    public String getReino1Tipo() {
        return reino1Tipo;
    }

    public String getReino2Tipo() {
        return reino2Tipo;
    }
    public void setReino1(int s){
		if(s == 1){
			reino1Tipo = "Inglaterra";
		}else if(s == 2){
			reino1Tipo = "Francia";
		}else if(s == 3){
			reino1Tipo = "Castilla-Aragon";
		}else if(s == 4){
			reino1Tipo = "Moros";
		}else{
			reino1Tipo = "SIRG";
		}
	}
    public void setReino2(int s){
		if(s == 1){
			reino2Tipo = "Inglaterra";
		}else if(s == 2){
			reino2Tipo = "Francia";
		}else if(s == 3){
			reino2Tipo = "Castilla-Aragon";
		}else if(s == 4){
			reino2Tipo = "Moros";
		}else{
			reino2Tipo = "SIRG";
		}
	}
    public ArrayList<Ejercito> getReino1() {
        return reino1;
    }

    public ArrayList<Ejercito> getReino2() {
        return reino2;
    }
}
