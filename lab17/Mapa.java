import java.util.*;
public class Mapa {
    private ArrayList<Ejercito> reino1 = new ArrayList<Ejercito>();
    private ArrayList<Ejercito> reino2 = new ArrayList<Ejercito>();
    private String tipoReino1;
    private String tipoReino2;
    private String[][] tablero = new String[10][10];
    private String tipoTerreno;

    //Se utiliza un arreglo bidimensional como atributo para generar filas y columnas no repetidas para ambos reino
    private boolean[][] posiciones = new boolean[10][10];
    

    public Mapa(){
        generarEjercito(reino1);
        generarEjercito(reino2);
        generarReinos(reino1, reino2);

        int num = (int) (Math.random() * 5 + 1);
        if(num == 1){
            tipoTerreno = "Bosque";
        }else if(num == 2){
            tipoTerreno = "Campo Abierto";
        }else if(num == 3){
            tipoTerreno = "Montaña";
        }else if(num == 4){
            tipoTerreno = "Desierto";
        }else{
            tipoTerreno = "Playa";
        }
        setVentaja(tipoTerreno);

    }
    
    public void genTablero(){
		//inicializamos el tablero.
		for(int i = 0; i < 10; i++){
			for(int j = 0; j < 10; j++){
				tablero[i][j] = "";
			}
		}	
		for(int i = 0; i < reino1.size(); i++){
			int fila = reino1.get(i).getFila();
			int columna = reino1.get(i).getColumna();

			tablero[fila][columna] = imprimirDatos(reino1.get(i)) + '#';
		}

		for(int i = 0; i < reino2.size(); i++){
			int fila = reino2.get(i).getFila();
			int columna = reino2.get(i).getColumna();
			tablero[fila][columna] = imprimirDatos(reino2.get(i)) + '*';
		}
	}
    public void imprimirMapa(){
		char[] aux = {'A','B','C','D','E','F','G','H','I', 'J'};
        System.out.print("       ");
		for(char c: aux){
			System.out.print("\t  "+c);
		}
        System.out.println();

        for(int i = 0; i < 90; i++){
			System.out.print("-");
		}
		System.out.println();
		for(int i = 0; i < 10; i++){
			System.out.print(i+1 +"\t|");
			for(int j = 0; j < 10; j++){
				System.out.print(tablero[i][j] + "\t|");
			}
            System.out.println();
            for(int j = 0; j < 90; j++){
			    System.out.print("-");
		    }
			System.out.println();
		}
    }
    public String imprimirDatos(Ejercito e){
        int totalVida = 0;
        for(Soldado s: e.getSoldados()){
            totalVida+= s.getVidaActual();
        }
        String ans = e.getSoldados().size() + "-" +totalVida;
        return ans;

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
            int nivelAtaque = (int)(Math.random() * 5 + 1);
            int nivelDefensa = (int)(Math.random() * 5 + 1);
            int vidaActual = (int)(Math.random() * 5 + 1);
			Soldado s = new Soldado(nombre,vidaActual, nivelAtaque, nivelDefensa);
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
    public void setReino1(int s) {
        if (s == 1) {
            tipoReino1 = "Inglaterra";
        } else if (s == 2) {
            tipoReino1 = "Francia";
        } else if (s == 3) {
            tipoReino1 = "Castilla-Aragon";
        } else if (s == 4) {
            tipoReino1 = "Moros";
        } else {
            tipoReino1 = "SIRG";
        }
    }
    public void setReino2(int s) {
        if (s == 1) {
            tipoReino2 = "Inglaterra";
        } else if (s == 2) {
            tipoReino2 = "Francia";
        } else if (s == 3) {
            tipoReino2 = "Castilla-Aragon";
        } else if (s == 4) {
            tipoReino2 = "Moros";
        } else {
            tipoReino2 = "SIRG";
        }
    }

    public void setVentaja(String tipo){
        if (tipo.equals("Bosque")) {
            if (tipoReino1.equals("Inglaterra")) {
                aplicarBonusVida(reino1);
            }
            if (tipoReino2.equals("Inglaterra")) {
                aplicarBonusVida(reino2);
            }
            if (tipoReino1.equals("SIRG")) {
                aplicarBonusVida(reino1);
            }
            if (tipoReino2.equals("SIRG")) {
                aplicarBonusVida(reino2);
            }
        } else if (tipo.equals("Campo Abierto")) {
            if (tipoReino1.equals("Francia")) {
                aplicarBonusVida(reino1);
            }
            if (tipoReino2.equals("Francia")) {
                aplicarBonusVida(reino2);
            }
            if (tipoReino1.equals("SIRG")) {
                aplicarBonusVida(reino1);
            }
            if (tipoReino2.equals("SIRG")) {
                aplicarBonusVida(reino2);
            }
        } else if (tipo.equals("Montaña")) {
            if (tipoReino1.equals("Castilla-Aragon")) {
                aplicarBonusVida(reino1);
            } else if (tipoReino2.equals("Castilla-Aragon")) {
                aplicarBonusVida(reino2);
            }
        } else if (tipo.equals("Desierto")) {
            if (tipoReino1.equals("Moros")) {
                aplicarBonusVida(reino1);
            } else if (tipoReino2.equals("Moros")) {
                aplicarBonusVida(reino2);
            }
        } else if (tipo.equals("Playa")) {
            if (tipoReino1.equals("SIRG")) {
                aplicarBonusVida(reino1);
            } else if (tipoReino2.equals("SIRG")) {
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
    public ArrayList<Ejercito> getReino1() {
        return reino1;
    }
    public ArrayList<Ejercito> getReino2() {
        return reino2;
    }
    public String getTipoReino1(){
        return tipoReino1;
    }
    public String getTipoReino2(){
        return tipoReino2;
    }
    public String getTipoTerreno(){
        return tipoTerreno;
    }
}
