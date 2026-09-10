package com.mycompany.codigosecreto;

public class CodigoSecreto extends JFrame {

    // Componentes de la ventana
    JLabel titulo;
    JLabel pista;

    JPasswordField digito1;
    JPasswordField digito2;
    JPasswordField digito3;

    JTextField cajaIntento;
    JButton botonRevelar;

    // Variables del juego
    int codigoSecreto;
    int cantidadIntentos = 0;
    int cantidadAyudas = 0;


        setTitle("El Código Secreto");
        setSize(600, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.setBackground(new Color(40, 80, 110));

        titulo = new JLabel("EL CÓDIGO SECRETO");
        titulo.setHorizontalAlignment(JLabel.CENTER);
        titulo.setForeground(Color.YELLOW);
        titulo.setFont(new Font("Arial", Font.BOLD, 25));

        panel.add(titulo, BorderLayout.NORTH);

        JPanel panelDigitos = new JPanel();
        panelDigitos.setBackground(Color.LIGHT_GRAY);

        digito1 = crearCampo(Color.PINK);
        digito2 = crearCampo(Color.YELLOW);
        digito3 = crearCampo(Color.GREEN);

        panelDigitos.add(digito1);
        panelDigitos.add(digito2);
        panelDigitos.add(digito3);

        panel.add(panelDigitos, BorderLayout.CENTER);

        JPanel panelInferior = new JPanel();
        panelInferior.setLayout(new GridLayout(4, 1));
        panelInferior.setBackground(new Color(40, 80, 110));

        pista = new JLabel("Escribe un número de 3 dígitos");
        pista.setHorizontalAlignment(JLabel.CENTER);
        pista.setForeground(Color.WHITE);
        pista.setFont(new Font("Arial", Font.BOLD, 16));

        panelInferior.add(pista);

        cajaIntento = new JTextField();
        cajaIntento.setHorizontalAlignment(JTextField.CENTER);
        cajaIntento.setFont(new Font("Arial", Font.BOLD, 25));

        panelInferior.add(cajaIntento);

        botonRevelar = new JButton("REVELAR AYUDA");
        panelInferior.add(botonRevelar);

        JLabel mensaje = new JLabel("Presiona ENTER para comprobar");
        mensaje.setHorizontalAlignment(JLabel.CENTER);
        mensaje.setForeground(Color.WHITE);

        panelInferior.add(mensaje);

        panel.add(panelInferior, BorderLayout.SOUTH);

        add(panel);

        crearCodigo();

        // Evento de teclado
        cajaIntento.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent evento) {

                if (evento.getKeyCode() == KeyEvent.VK_ENTER) {
                    comprobarNumero();
                }
            }
        });

        // Evento del botón
        botonRevelar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evento) {
                revelarNumero();
            }
        });
    }

    public JPasswordField crearCampo(Color color) {

        JPasswordField campo = new JPasswordField();

        campo.setPreferredSize(new Dimension(70, 80));
        campo.setHorizontalAlignment(JPasswordField.CENTER);
        campo.setFont(new Font("Arial", Font.BOLD, 40));
        campo.setBackground(color);
        campo.setEchoChar('$');
        campo.setEditable(false);

        return campo;
    }

    public void crearCodigo() {

        Random aleatorio = new Random();

        codigoSecreto = aleatorio.nextInt(900) + 100;

        String codigo = String.valueOf(codigoSecreto);

        digito1.setText("" + codigo.charAt(0));
        digito2.setText("" + codigo.charAt(1));
        digito3.setText("" + codigo.charAt(2));
    }

    public void comprobarNumero() {

        String textoEscrito = cajaIntento.getText();

        if (textoEscrito.length() != 3 ||
                !textoEscrito.matches("[0-9]+")) {

            pista.setText("Debes escribir exactamente 3 números");
            return;
        }

        cantidadIntentos++;

        int numeroEscrito = Integer.parseInt(textoEscrito);

        String codigo = String.valueOf(codigoSecreto);

        if (textoEscrito.charAt(0) == codigo.charAt(0)) {
            digito1.setEchoChar((char) 0);
        }

        if (textoEscrito.charAt(1) == codigo.charAt(1)) {
            digito2.setEchoChar((char) 0);
        }

        if (textoEscrito.charAt(2) == codigo.charAt(2)) {
            digito3.setEchoChar((char) 0);
        }

        if (numeroEscrito == codigoSecreto) {

            digito1.setEchoChar((char) 0);
            digito2.setEchoChar((char) 0);
            digito3.setEchoChar((char) 0);

            pista.setText("¡GANASTE!");

            botonRevelar.setEnabled(false);
            cajaIntento.setEnabled(false);

            JOptionPane.showMessageDialog(
                    this,
                    "¡Ganaste!\n"
                    + "Código correcto: " + codigoSecreto + "\n"
                    + "Intentos: " + cantidadIntentos
            );

        } else if (numeroEscrito < codigoSecreto) {

            pista.setText("El código secreto es MÁS ALTO");

        } else {

            pista.setText("El código secreto es MÁS BAJO");
        }

        cajaIntento.selectAll();
    }

    public void revelarNumero() {

        if (cantidadAyudas == 0) {

            digito1.setEchoChar((char) 0);
            cantidadAyudas++;

            pista.setText("Primera ayuda utilizada");

        } else if (cantidadAyudas == 1) {

            digito2.setEchoChar((char) 0);
            cantidadAyudas++;

            pista.setText("Segunda ayuda utilizada");

        } else {

            JOptionPane.showMessageDialog(
                    this,
                    "No hay más revelaciones"
            );

            botonRevelar.setEnabled(false);
        }
    }
}

    
}
