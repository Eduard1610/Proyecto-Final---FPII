import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class Map extends JFrame {
	private static final int ANCHO = 1200;
	private static final int ALTO = 700;
	private static JButton[][] tablero = new JButton[9][9];
	private static Color[] elements = { Color.RED, Color.BLUE, Color.GREEN };
	private static Random rd = new Random();
	private static int turno = 1;
	private static boolean startedPlay = false;// indicador si el juego inició
	private static boolean nombre1 = false;// indicador si el nombre 1 está lleno
	private static boolean nombre2 = false;// indicador si el nombre 2 está lleno
	private static Color porDefecto;
	private HashMap<String, Coordenada> coordenadasTemporales;// Este map cambia sus valores
	private JTextField name1;
	private JTextField name2;
	private JButton start;
	private int filaActualPulsado;
	private int columnaActualPulsado;
	private int vecesPulsado = 0;

	public Map() {
		setTitle("BATALLA");
		setSize(ANCHO, ALTO);
		setResizable(false);
		setLayout(new BorderLayout());
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		createContents();
		setVisible(true);
	}

	private void createContents() {
		Listener listener = new Listener();

		// PRIMER PANEL-NORTE
		JPanel superior = new JPanel();
		superior.setBackground(Color.yellow);
		name1 = new JTextField();
		name1.addActionListener(listener);

		name2 = new JTextField();
		name2.addActionListener(listener);

		superior.setLayout(new GridLayout(3, 3));
		superior.add(new JLabel("ELEMENTS INVASION", SwingConstants.CENTER));
		superior.add(new JLabel("Ingresen los nombres de los jugadores:", SwingConstants.CENTER));
		superior.add(new JLabel("Jugador 1:", SwingConstants.CENTER));
		superior.add(name1);
		superior.add(new JLabel("Jugador 2:", SwingConstants.CENTER));
		superior.add(name2);

//		SEGUNDO PANEL-CENTRO
		JPanel mapa = new JPanel();
		mapa.setBackground(Color.BLACK);
		mapa.setLayout(new GridLayout(9, 9, 5, 5));
		for (int i = 0; i < tablero.length; i++) {
			for (int j = 0; j < tablero[i].length; j++) {
				tablero[i][j] = new JButton();
				tablero[i][j].addActionListener(listener);
				tablero[i][j].setForeground(Color.WHITE);
				mapa.add(tablero[i][j]);
			}
		}
		porDefecto = tablero[0][0].getBackground();

// 		TERCER PANEL-OESTE

		JPanel izquierdo = new JPanel();
		izquierdo.setBackground(Color.LIGHT_GRAY);
		izquierdo.setLayout(new GridLayout(3, 3, 1, 50));
		JButton[] btnFuego = new JButton[3];
		JButton[] btnPlanta = new JButton[3];
		JButton[] btnAgua = new JButton[3];

		for (int i = 0; i < 3; i++) {
			btnFuego[i] = new JButton();
			btnPlanta[i] = new JButton();
			btnAgua[i] = new JButton();
			btnFuego[i].setBackground(Color.RED);
			btnPlanta[i].setBackground(Color.GREEN);
			btnAgua[i].setBackground(Color.BLUE);
		}

		izquierdo.add(new JLabel("Fuego"));
		izquierdo.add(btnFuego[0]);
		izquierdo.add(new JLabel("Debil"));
		izquierdo.add(btnAgua[0]);
		izquierdo.add(new JLabel("Fuerte"));
		izquierdo.add(btnPlanta[0]);
		izquierdo.add(new JLabel("Planta"));
		izquierdo.add(btnPlanta[1]);
		izquierdo.add(new JLabel("Debil"));
		izquierdo.add(btnFuego[1]);
		izquierdo.add(new JLabel("Fuerte"));
		izquierdo.add(btnAgua[1]);
		izquierdo.add(new JLabel("Agua"));
		izquierdo.add(btnAgua[2]);
		izquierdo.add(new JLabel("Debil"));
		izquierdo.add(btnPlanta[2]);
		izquierdo.add(new JLabel("Fuerte"));
		izquierdo.add(btnFuego[2]);

//		CUARTO PANEL-ESTE
		JPanel derecho = new JPanel();
		derecho.setBackground(new Color(7, 106, 186));

//		QUINTO PANEL-SUR
		JPanel control = new JPanel();
		control.setBackground(Color.yellow);
		start = new JButton("Start");
		start.addActionListener(listener);
		start.setEnabled(false);
		control.add(start);

		add(superior, BorderLayout.NORTH);
		add(mapa, BorderLayout.CENTER);
		add(izquierdo, BorderLayout.WEST);
		add(derecho, BorderLayout.EAST);
		add(control, BorderLayout.SOUTH);
	}

	private class Listener implements ActionListener {
		public void actionPerformed(ActionEvent e) {

			// BOTÓN START
			if (e.getSource() == start) {
				startedPlay = true;
				JOptionPane.showMessageDialog(getContentPane(), "JUEGO INICIADO - TURNO DEL JUGADOR 1");
				start.setEnabled(false);
			}

			// PARA CHECKBOX 1
			if (e.getSource() == name1) {
				if (nombre2) {
					start.setEnabled(true);
				}
				name1.setEnabled(false);
				nombre1 = true;

				// PARA JUGADOR 1
				// *Base
				tablero[4][0].setBackground(new Color(236, 21, 164));
				tablero[4][0].setText(name1.getText());

				// elementos
				for (int i = 0; i < 10; i += 2) {
					tablero[i][1].setBackground(elements[rd.nextInt(3)]);
					tablero[i][1].setText(name1.getText());
					tablero[i][3].setBackground(elements[rd.nextInt(3)]);
					tablero[i][3].setText(name1.getText());

				}
				for (int i = 1; i < 9; i += 2) {
					tablero[i][2].setBackground(elements[rd.nextInt(3)]);
					tablero[i][2].setText(name1.getText());
//					tablero[i][4].setBackground(elements[rd.nextInt(3)]);
//					tablero[i][4].setText(name1.getText());
				}
			}

			// PARA CHECKBOX 2
			else if (e.getSource() == name2) {
				if (nombre1) {
					start.setEnabled(true);
				}
				name2.setEnabled(false);
				nombre2 = true;
				// PARA JUGADOR 2
				// *Base
				tablero[4][8].setBackground(new Color(133, 106, 242));
				tablero[4][8].setText(name2.getText());

				// elementos
				for (int i = 0; i < 10; i += 2) {
					tablero[i][5].setBackground(elements[rd.nextInt(3)]);
					tablero[i][5].setText(name2.getText());
					tablero[i][7].setBackground(elements[rd.nextInt(3)]);
					tablero[i][7].setText(name2.getText());
				}
				for (int i = 1; i < 9; i += 2) {
					tablero[i][6].setBackground(elements[rd.nextInt(3)]);
					tablero[i][6].setText(name2.getText());
				}
			}

			// PARA LOS BOTONES
			else {
				// Para antes del juego(antes que se aprete el botón de iniciar)
				if (startedPlay == false) {
					JOptionPane.showMessageDialog(getContentPane(),
							"Ingrese los nombres y luego pulse en el botón start para iniciar");
				} else {// LISTENERS DEL JUEGO
					for (int i = 0; i < tablero.length; i++) {
						for (int j = 0; j < tablero[i].length; j++) {
							// LISTENERS DE BASES
							if (e.getSource() == tablero[4][0] || e.getSource() == tablero[4][8] && vecesPulsado == 0) {
								JOptionPane.showMessageDialog(getContentPane(), "LAS BASES NO JUEGAN");
								// para que no itere constantemente
								i = tablero.length - 1;
								j = tablero[i].length - 1;
							}

							// LISTENERS BOTONES DEL CAMPO
							if (e.getSource() == tablero[i][j]) {
								// LISTENERS DE JUGADOR 1
								if (e.getActionCommand().equals(name1.getText())) {

									if (turno == 1) {
										coordenadasTemporales = retornarCoordenadasAdyacentes(i, j);
										filaActualPulsado = i;
										columnaActualPulsado = j;
										colorearMovimientosPosibles(i, j);
										vecesPulsado++;

									} else {
										JOptionPane.showMessageDialog(getContentPane(), "Es el turno del jugador 2");
									}

								}

								// LISTENERS DE JUGADOR 2
								if (e.getActionCommand().equals(name2.getText())) {

									if (turno == 2) {
										coordenadasTemporales = retornarCoordenadasAdyacentes(i, j);
										filaActualPulsado = i;
										columnaActualPulsado = j;
										colorearMovimientosPosibles(i, j);
										vecesPulsado++;
									} else {
										JOptionPane.showMessageDialog(getContentPane(), "Es el turno del jugador 1");
									}

								}

								// ________________________________
							}

						}
					}
				}
			}

			// Si el click es del mismo botón
			if (e.getSource() == tablero[filaActualPulsado][columnaActualPulsado] && vecesPulsado == 2) {
				descolorearMovimientosPosibles(filaActualPulsado, columnaActualPulsado);
				vecesPulsado = 0;
			}

			// Si el click viene de botones coloreados
			if (coordenadasTemporales != null) {
				boolean movCorrecto = false;
				for (String x : coordenadasTemporales.keySet()) {
					int filaTemp = coordenadasTemporales.get(x).getFila();
					int columnaTemp = coordenadasTemporales.get(x).getColumna();
					if (e.getSource() == tablero[filaTemp][columnaTemp]) {
						mover(filaTemp, columnaTemp);
						if (turno == 1) {
							JOptionPane.showMessageDialog(getContentPane(), "Turno del jugador 2");
							turno = 2;
						} else {
							JOptionPane.showMessageDialog(getContentPane(), "Turno del jugador 1");
							turno = 1;
						}
						movCorrecto = true;
					}
				}
				if (movCorrecto == false && e.getSource() != tablero[filaActualPulsado][columnaActualPulsado]) {
					JOptionPane.showMessageDialog(getContentPane(), "Movimiento Inválido");
				}

			}

			// _____________________________________________________
		}
	}

	private HashMap<String, Coordenada> retornarCoordenadasAdyacentes(int fila, int columna) {
		ArrayList<JButton> botonesAdyacentes = new ArrayList<JButton>();
		HashMap<String, Coordenada> coordenadasAdyacentes = new HashMap<String, Coordenada>();

		// BOTONES SUPERIORES
		if (fila != 0) {
			if (columna > 0) {
				botonesAdyacentes.add(tablero[fila - 1][columna - 1]);// superior-izquierdo
				coordenadasAdyacentes.put("Superior-izquierdo", new Coordenada(fila - 1, columna - 1));
			}
			if (columna < 8) {
				botonesAdyacentes.add(tablero[fila - 1][columna + 1]);// superior-derecho
				coordenadasAdyacentes.put("Superior-derecho", new Coordenada(fila - 1, columna + 1));
			}
			botonesAdyacentes.add(tablero[fila - 1][columna]);// superior
			coordenadasAdyacentes.put("Superior", new Coordenada(fila - 1, columna));
		}
		// BOTONES INFERIORES
		if (fila != 8) {
			if (columna > 0) {
				botonesAdyacentes.add(tablero[fila + 1][columna - 1]);// inferior-izquierdo
				coordenadasAdyacentes.put("Inferior-izquierdo", new Coordenada(fila + 1, columna - 1));
			}
			if (columna < 8) {
				botonesAdyacentes.add(tablero[fila + 1][columna + 1]);// inferior-derecho
				coordenadasAdyacentes.put("Inferior-derecho", new Coordenada(fila + 1, columna + 1));
			}
			botonesAdyacentes.add(tablero[fila + 1][columna]);// inferior
			coordenadasAdyacentes.put("Inferior", new Coordenada(fila + 1, columna));
		}
		// IZQUIERDA
		if (columna != 0) {
			botonesAdyacentes.add(tablero[fila][columna - 1]);// izquierdo
			coordenadasAdyacentes.put("Izquierdo", new Coordenada(fila, columna - 1));
		}
		// DERECHA
		if (columna != 8) {
			botonesAdyacentes.add(tablero[fila][columna + 1]);// derecho
			coordenadasAdyacentes.put("Derecho", new Coordenada(fila, columna + 1));
		}
//		for (String x : coordenadasAdyacentes.keySet()) {
//			System.out.println(x);
//			System.out.println("Fila:" + coordenadasAdyacentes.get(x).getFila());
//			System.out.println("Columna:" + coordenadasAdyacentes.get(x).getColumna() + "\n");
//		}
		return coordenadasAdyacentes;
	}

	private void colorearMovimientosPosibles(int fila, int columna) {
		String textoBotonElegido = tablero[fila][columna].getText();
		String nombreEnemigo;

		if (textoBotonElegido.equals(name1.getText())) {
			nombreEnemigo = name2.getText();
		} else {
			nombreEnemigo = name1.getText();
		}

		for (String x : coordenadasTemporales.keySet()) {
			int filaBtn = coordenadasTemporales.get(x).getFila();
			int columnaBtn = coordenadasTemporales.get(x).getColumna();

			if (tablero[filaBtn][columnaBtn].getText().equals(nombreEnemigo)) {
				tablero[filaBtn][columnaBtn].setText(nombreEnemigo + " (atacar)");
			}
			if (tablero[filaBtn][columnaBtn].getText().equals("")) {
				tablero[filaBtn][columnaBtn].setBackground(new Color(93, 224, 180));
			}
		}
	}

	private void descolorearMovimientosPosibles(int fila, int columna) {

		String textoBotonElegido = tablero[fila][columna].getText();
		String nombreEnemigo;

		if (textoBotonElegido.equals(name1.getText())) {
			nombreEnemigo = name2.getText();
		} else {
			nombreEnemigo = name1.getText();
		}

		for (String x : coordenadasTemporales.keySet()) {
			int filaBtn = coordenadasTemporales.get(x).getFila();
			int columnaBtn = coordenadasTemporales.get(x).getColumna();
			JButton btnAdyacente = tablero[filaBtn][columnaBtn];
			String textoBtnAdyacente = btnAdyacente.getText();

			if (!textoBtnAdyacente.equals(textoBotonElegido)) {
				if (textoBtnAdyacente.equals("")) {
					tablero[filaBtn][columnaBtn].setBackground(porDefecto);
				} else {
					tablero[filaBtn][columnaBtn].setText(nombreEnemigo);
				}
			}
		}
	}

	private void mover(int fila, int columna) {
		Color fuego = Color.RED;
		Color agua = Color.BLUE;
		Color planta = Color.GREEN;

		descolorearMovimientosPosibles(filaActualPulsado, columnaActualPulsado);

		JButton btnPosActual = tablero[filaActualPulsado][columnaActualPulsado];
		JButton btnPosMover = tablero[fila][columna];

		if (btnPosActual.getBackground() == fuego) {
			if (btnPosMover.getBackground() == planta || btnPosMover.getBackground() == porDefecto) {
				btnPosMover.setBackground(btnPosActual.getBackground());// La posición a mover toma el color
				btnPosMover.setText(btnPosActual.getText());// La posición a mover toma el texto

				btnPosActual.setBackground(porDefecto);
				btnPosActual.setText("");
				vecesPulsado = 0;
			} else if (btnPosMover.getBackground() == agua) {// el botón se elimina
				btnPosActual.setBackground(porDefecto);
				btnPosActual.setText("");
				vecesPulsado = 0;
			} else if (btnPosMover.getBackground() == fuego) {
				JOptionPane.showMessageDialog(getContentPane(), "Movimiento Inválido");
				// para que no cuente este movimiento
				if (turno == 1) {
					turno = 2;
					vecesPulsado = 0;
				} else {
					turno = 1;
					vecesPulsado = 0;
				}
			} else {
				vecesPulsado = 0;
				JOptionPane.showMessageDialog(getContentPane(), "Felicidades GANADOR!");
				System.exit(0);
			}
		}

		if (btnPosActual.getBackground() == agua) {
			if (btnPosMover.getBackground() == fuego || btnPosMover.getBackground() == porDefecto) {
				btnPosMover.setBackground(btnPosActual.getBackground());// La posición a mover toma el color
				btnPosMover.setText(btnPosActual.getText());// La posición a mover toma el texto

				btnPosActual.setBackground(porDefecto);
				btnPosActual.setText("");
				vecesPulsado = 0;
			} else if (btnPosMover.getBackground() == planta) {// el botón se elimina
				btnPosActual.setBackground(porDefecto);
				btnPosActual.setText("");
				vecesPulsado = 0;
			} else if (btnPosMover.getBackground() == agua) {
				JOptionPane.showMessageDialog(getContentPane(), "Movimiento Inválido");
				// para que no cuente este movimiento
				if (turno == 1) {
					turno = 2;
					vecesPulsado = 0;
				} else {
					turno = 1;
					vecesPulsado = 0;
				}
			} else {
				vecesPulsado = 0;
				JOptionPane.showMessageDialog(getContentPane(), "Felicidades GANADOR!");
				System.exit(0);
			}
		}

		if (btnPosActual.getBackground() == planta) {
			if (btnPosMover.getBackground() == agua || btnPosMover.getBackground() == porDefecto) {
				btnPosMover.setBackground(btnPosActual.getBackground());// La posición a mover toma el color
				btnPosMover.setText(btnPosActual.getText());// La posición a mover toma el texto

				btnPosActual.setBackground(porDefecto);
				btnPosActual.setText("");
				vecesPulsado = 0;
			} else if (btnPosMover.getBackground() == fuego) {// el botón se elimina
				btnPosActual.setBackground(porDefecto);
				btnPosActual.setText("");
				vecesPulsado = 0;
			} else if (btnPosMover.getBackground() == planta) {
				JOptionPane.showMessageDialog(getContentPane(), "Movimiento Inválido");
				// para que no cuente este movimiento
				if (turno == 1) {
					turno = 2;
					vecesPulsado = 0;
				} else {
					turno = 1;
					vecesPulsado = 0;
				}
			} else {
				vecesPulsado = 0;
				JOptionPane.showMessageDialog(getContentPane(), "Felicidades GANADOR!");
				System.exit(0);
			}
		}

	}

	public static void main(String[] args) {
		new Map();
	}
}
