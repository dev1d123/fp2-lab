import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.CountDownLatch;

public class Tablero extends JFrame {
    private static final int ANCHO = 800;
    private static final int ALTO = 1000;
    private static final int FILAS = 10;
    private static final int COLUMNAS = 10;

    private Ejercito e1;
    private Ejercito e2;

    public Tablero(Ejercito e1, Ejercito e2) {
        setTitle("Campo de batalla");
        setSize(ANCHO, ALTO);
        setLayout(new GridLayout(FILAS + 2, COLUMNAS + 1, 2, 2));
        this.e1 = e1;
        this.e2 = e2;
        createContents();
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }

    public void createContents() {

        JLabel[] columnas = new JLabel[COLUMNAS];
        char[] cols = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J'};
        for (int i = 0; i < COLUMNAS; i++) {
            columnas[i] = new JLabel(cols[i] + "");
            columnas[i].setHorizontalAlignment(JLabel.CENTER);
            columnas[i].setVerticalAlignment(JLabel.CENTER);
            Border border = BorderFactory.createLineBorder(Color.GREEN, 2);

            columnas[i].setFont(new Font("Arial", Font.BOLD, 30));
            columnas[i].setBorder(border);
            add(columnas[i]);
        }

        JLabel[] filas = new JLabel[FILAS];
        for (int i = 0; i < FILAS; i++) {
            filas[i] = new JLabel((i + 1) + "");
            filas[i].setVerticalAlignment(JLabel.CENTER);
            filas[i].setHorizontalAlignment(JLabel.CENTER);
            filas[i].setFont(new Font("Arial", Font.BOLD, 30));
            Border border = BorderFactory.createLineBorder(Color.GREEN, 2);
            filas[i].setBorder(border);
            add(filas[i]);
        }
        for (int i = 0; i < COLUMNAS + 1; i++) {
            if (i == 0) {
                add(new JLabel());
            } else {
                add(columnas[i - 1]);
            }
        }

        for (int i = 0; i < FILAS; i++) {
            add(filas[i]);
            for (int j = 0; j < COLUMNAS; j++) {
                JButton boton = new JButton("");
                for (Soldado s : e1.getSoldados()) {
                    if (s.getFila() == i && s.getColumna() == j) {
                        boton = new JButton(s.impresionTabla());
                        boton.setBackground(Color.blue);
                        break;
                    }
                }
                for (Soldado s : e2.getSoldados()) {
                    if (s.getFila() == i && s.getColumna() == j) {
                        boton = new JButton(s.impresionTabla());
                        boton.setBackground(Color.red);
                        break;
                    }
                }
                boton.addActionListener(new ButtonListener(i, j));
                add(boton);
            }
        }
    }
    private int fila = -1;
    private int columna = -1;
    private CountDownLatch latch = new CountDownLatch(1);

    private class ButtonListener implements ActionListener {
        private int f;
        private int c;
        public ButtonListener(int f, int c) {
            this.f = f;
            this.c = c;
        }
        public void actionPerformed(ActionEvent e) {
            fila = f;
            columna = c;
            latch.countDown(); 
        }
    }
    public int[] getCoordenadas() {
        try {
            latch.await(); 
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        int[] arr = new int[2];
        arr[0] = fila + 1;
        arr[1] = columna + 1;
        fila = -1;
        columna = -1;
        latch = new CountDownLatch(1);
        return arr;
    }

    public void repintarTablero() {
        getContentPane().removeAll(); 
        createContents(); 
        revalidate(); 
        repaint();
    }
}
