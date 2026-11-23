package pe.com.farmacia.app.vista;

import java.io.File;
import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.scene.Scene;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;

/**
 *
 * @author «Equipo Alfa Buena Maravilla Onda Dinamita Escuadrón Lobo»
 */
public class InicioDeSesion extends javax.swing.JFrame {

    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(InicioDeSesion.class.getName());

    /*
    JFXPanel es una libreia externa, sirve para insertar videos en el JFrame
     */
    private final JFXPanel jfxPanel = new JFXPanel();
// Guarda el reproductor de video para poder controlarlo desde otros métodos.
    private MediaPlayer oracleVid;

    public InicioDeSesion() {

        initComponents();
        //se llama al metodo
        configurarPanelVideo();
        escenaAND();

        txtContraseña.setEchoChar((char) 0);

        setTitle("Video ADN");
        setResizable(false);
        setLocationRelativeTo(null);

    }

    private void configurarPanelVideo() {
        // Configurar tamaño y posición exacta del jfxPanel cubriendo todo el fondo (600x320)
        jfxPanel.setBounds(0, 0, 600, 320);

        // Añadir el jfxPanel respetando el AbsoluteLayout de NetBeans
        FondoAND.add(jfxPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 600, 320));

        // Enviar el video al fondo absoluto (detrás de los botones y campos de texto)
        FondoAND.setComponentZOrder(jfxPanel, FondoAND.getComponentCount() - 1);
    }

    // Recupera la pantalla de inicio de sesión después de salir del panel de administrador.
    public void volverALogin(javax.swing.JPanel panelRegistro) {
        //Elimina el panel de registro recibido del contenedor principal de la ventana.
        getContentPane().remove(panelRegistro);
        //Vuelve a hacer visible el panel de inicio
        FondoAND.setVisible(true);
        setTitle("Video ADN");
        //Recalculca distribucion y redibuja la ventana
        revalidate();
        repaint();
    }
    
    public void volverDesdeVentas(javax.swing.JPanel panelVentas) {
        //Elimina el panel de ventas recibido del contenedor principal de la ventana
        getContentPane().remove(panelVentas);
        //Vuelve a hacer visible el panel de inicio
        FondoAND.setVisible(true);
        setTitle("Video ADN");
        //Restaura el tamaño exacto de FondoAND sin destruir el contexto de JavaFX
        javax.swing.SwingUtilities.invokeLater(() -> {
            setResizable(true);
            setExtendedState(javax.swing.JFrame.NORMAL);
            setSize(FondoAND.getWidth() + 16, FondoAND.getHeight() + 39);
            setLocationRelativeTo(null);
            setResizable(false);
            revalidate();
            repaint();
        });
    }

    

    public void volverDesdeAdmin() {

        // Obtiene la distribución de tarjetas de la ventana.
        java.awt.CardLayout tarjetas
                = (java.awt.CardLayout) getContentPane().getLayout();

        // Muestra nuevamente la pantalla de inicio de sesión.
        tarjetas.show(getContentPane(), "LOGIN");

        // Restablece el título de la ventana.
        setTitle("Video ADN");

        // Recupera el tamaño original de la ventana.
        setSize(600, 320);

        // Centra la ventana en la pantalla.
        setLocationRelativeTo(null);

        // Actualiza la distribución y redibuja la ventana.
        revalidate();
        repaint();
    }

    private void escenaAND() {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                // Comprueba si ya existe un reproductor anterior.
                if (oracleVid != null) {

                    // Detiene la reproducción anterior.
                    oracleVid.stop();

                    // Libera los recursos del reproductor anterior.
                    oracleVid.dispose();
                }
                File file = new File("media/iniciarSesion/videos/VideoADN.mp4");
                // Crea el reproductor de video y lo guarda en el atributo de la clase.
                oracleVid = new MediaPlayer(
                        new Media(file.toURI().toString())
                );
                // Muestra un mensaje cuando el video presenta un error.
                oracleVid.setOnError(() -> {
                    System.out.println("ERROR DEL VIDEO: " + oracleVid.getError());
                });

// Muestra un mensaje cuando el video termina de prepararse.
                oracleVid.setOnReady(() -> {
                    System.out.println("VIDEO LISTO PARA REPRODUCIR");
                });
// Muestra el estado del reproductor cuando ocurre un error.
                oracleVid.setOnError(() -> {
                    System.out.println("ERROR: " + oracleVid.getError());
                });

// Muestra cuando el video comienza a reproducirse.
                oracleVid.setOnPlaying(() -> {
                    System.out.println("VIDEO REPRODUCIENDOSE");
                });

// Muestra cuando el video se detiene.
                oracleVid.setOnStopped(() -> {
                    System.out.println("VIDEO DETENIDO");
                });
// Muestra un mensaje si ocurre un error durante la reproducción.
                oracleVid.setOnHalted(() -> {
                    System.out.println("EL REPRODUCTOR SE HA DETENIDO");
                });

                MediaView mediaView = new MediaView(oracleVid);

                // Ajustar dimensiones del video y mantener proporción centrada
                mediaView.setFitWidth(600);
                mediaView.setFitHeight(320);
                mediaView.setPreserveRatio(true);

                // StackPane centra el video automáticamente dentro del JFXPanel
                javafx.scene.layout.StackPane root = new javafx.scene.layout.StackPane();
                root.getChildren().add(mediaView);

                jfxPanel.setScene(new Scene(root));

                oracleVid.setVolume(0.7);
                oracleVid.setCycleCount(MediaPlayer.INDEFINITE);
                oracleVid.play();
                // Muestra el estado actual del reproductor.
                System.out.println("ESTADO DEL VIDEO: " + oracleVid.getStatus());
            }
        });
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        FondoAND = new javax.swing.JPanel();
        panelLogin = new javax.swing.JPanel() {
            @Override
            protected void paintComponent(java.awt.Graphics g) {
                super.paintComponent(g);
                g.setColor(new java.awt.Color(17, 30, 48, 180));
                g.fillRect(0, 0, getWidth(), getHeight());
            }
        };
        txtIngresarUsuario = new javax.swing.JTextField(){
            {
                setOpaque(false);
                setBorder(null);
                setForeground(new java.awt.Color(160, 175, 185));
                setCaretColor(java.awt.Color.WHITE);
                setMargin(new java.awt.Insets(0, 10, 0, 10));
            }
            @Override
            protected void paintComponent(java.awt.Graphics g) {
                java.awt.Graphics2D g2d = (java.awt.Graphics2D) g.create();
                g2d.setRenderingHint(java.awt.RenderingHints.KEY_ANTIALIASING, java.awt.RenderingHints.VALUE_ANTIALIAS_ON);
                g2d.setColor(new java.awt.Color(30, 41, 59));
                g2d.fillRoundRect(0, 0, getWidth(), getHeight(), 10, 10);
                g2d.dispose();
                super.paintComponent(g);
            }
        };
        txtContraseña = new javax.swing.JPasswordField(){
            {
                setOpaque(false);
                setBorder(javax.swing.BorderFactory.createEmptyBorder(5, 10, 5, 10));
                setForeground(java.awt.Color.WHITE);
            }
            @Override
            protected void paintComponent(java.awt.Graphics g) {
                java.awt.Graphics2D g2d = (java.awt.Graphics2D) g.create();
                g2d.setRenderingHint(java.awt.RenderingHints.KEY_ANTIALIASING, java.awt.RenderingHints.VALUE_ANTIALIAS_ON);
                g2d.setColor(new java.awt.Color(30, 41, 59, 180));
                g2d.fillRoundRect(0, 0, getWidth(), getHeight(), 10, 10);
                g2d.dispose();
                super.paintComponent(g);
            }
        };
        lblInciarSesion = new javax.swing.JLabel();
        btnContinuar = new javax.swing.JButton();
        btnSingUp = new javax.swing.JButton();
        btnAdmin = new javax.swing.JButton();
        lblContraseña = new javax.swing.JLabel();
        lblUsuario = new javax.swing.JLabel();
        btnVerContrseña = new javax.swing.JButton();
        lblLogo = new javax.swing.JLabel();
        lblNombre = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        FondoAND.setBackground(new java.awt.Color(102, 153, 255));
        FondoAND.setMaximumSize(new java.awt.Dimension(600, 320));
        FondoAND.setMinimumSize(new java.awt.Dimension(600, 320));
        FondoAND.setName(""); // NOI18N
        FondoAND.setPreferredSize(new java.awt.Dimension(600, 320));
        FondoAND.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        panelLogin.setBackground(new java.awt.Color(13, 33, 45));
        panelLogin.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(100, 120, 140)));
        panelLogin.setOpaque(false);

        txtIngresarUsuario.setBackground(new java.awt.Color(65, 82, 95));
        txtIngresarUsuario.setForeground(new java.awt.Color(255, 255, 255));
        txtIngresarUsuario.setText("Ingresa tu usuario");
        txtIngresarUsuario.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(130, 150, 165)));
        txtIngresarUsuario.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtIngresarUsuarioFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtIngresarUsuarioFocusLost(evt);
            }
        });
        txtIngresarUsuario.addActionListener(this::txtIngresarUsuarioActionPerformed);

        txtContraseña.setBackground(new java.awt.Color(65, 82, 95));
        txtContraseña.setForeground(new java.awt.Color(255, 255, 255));
        txtContraseña.setText("Ingresa tu contraseña");
        txtContraseña.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(130, 150, 165)));
        txtContraseña.setEchoChar('*');
        txtContraseña.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtContraseñaFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtContraseñaFocusLost(evt);
            }
        });
        txtContraseña.addActionListener(this::txtContraseñaActionPerformed);

        lblInciarSesion.setBackground(new java.awt.Color(0, 0, 0));
        lblInciarSesion.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lblInciarSesion.setForeground(new java.awt.Color(255, 255, 255));
        lblInciarSesion.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblInciarSesion.setText("Iniciar Sesión");

        btnContinuar.setBackground(new java.awt.Color(11, 158, 113));
        btnContinuar.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        btnContinuar.setForeground(new java.awt.Color(255, 255, 255));
        btnContinuar.setText("Continuar");
        btnContinuar.setBorderPainted(false);
        btnContinuar.addActionListener(this::btnContinuarActionPerformed);

        btnSingUp.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
        btnSingUp.setForeground(new java.awt.Color(190, 205, 215));
        btnSingUp.setText("<html><u>Registrarse</u><html>");
        btnSingUp.setBorderPainted(false);
        btnSingUp.setContentAreaFilled(false);
        btnSingUp.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnSingUp.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnSingUpMouseClicked(evt);
            }
        });
        btnSingUp.addActionListener(this::btnSingUpActionPerformed);

        btnAdmin.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
        btnAdmin.setForeground(new java.awt.Color(190, 205, 215));
        btnAdmin.setText("<html><u>Acceso Admin</u><html>");
        btnAdmin.setBorderPainted(false);
        btnAdmin.setContentAreaFilled(false);
        btnAdmin.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnAdmin.addActionListener(this::btnAdminActionPerformed);

        lblContraseña.setForeground(new java.awt.Color(230, 235, 240));
        lblContraseña.setText("Contraseña");

        lblUsuario.setForeground(new java.awt.Color(230, 235, 240));
        lblUsuario.setText("Usuario");

        btnVerContrseña.setText("ver");
        btnVerContrseña.addActionListener(this::btnVerContrseñaActionPerformed);

        javax.swing.GroupLayout panelLoginLayout = new javax.swing.GroupLayout(panelLogin);
        panelLogin.setLayout(panelLoginLayout);
        panelLoginLayout.setHorizontalGroup(
            panelLoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelLoginLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(panelLoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelLoginLayout.createSequentialGroup()
                        .addGap(13, 13, 13)
                        .addGroup(panelLoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(btnContinuar, javax.swing.GroupLayout.PREFERRED_SIZE, 256, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtIngresarUsuario, javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelLoginLayout.createSequentialGroup()
                                .addComponent(txtContraseña, javax.swing.GroupLayout.PREFERRED_SIZE, 222, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnVerContrseña, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(lblContraseña, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblUsuario, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblInciarSesion, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addContainerGap(29, Short.MAX_VALUE))
                    .addGroup(panelLoginLayout.createSequentialGroup()
                        .addComponent(btnAdmin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnSingUp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(14, 14, 14))))
        );
        panelLoginLayout.setVerticalGroup(
            panelLoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelLoginLayout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(lblInciarSesion, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtIngresarUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblContraseña, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelLoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtContraseña, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnVerContrseña, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnContinuar, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelLoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAdmin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSingUp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 12, Short.MAX_VALUE))
        );

        FondoAND.add(panelLogin, new org.netbeans.lib.awtextra.AbsoluteConstraints(265, 20, 320, 260));

        lblLogo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblLogo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pe/com/farmacia/app/media/imagenes/LogoHD.png"))); // NOI18N
        lblLogo.setMaximumSize(new java.awt.Dimension(150, 150));
        lblLogo.setMinimumSize(new java.awt.Dimension(150, 150));
        try {
            java.net.URL urlImg = getClass().getResource("/pe/com/farmacia/app/media/imagenes/LogoHD.png");
            if (urlImg != null) {
                javax.swing.ImageIcon iconOriginal = new javax.swing.ImageIcon(urlImg);

                // Usamos dimensiones fijas (130x130) para evitar que falle por el ancho en cero
                int tamaño = 130;
                java.awt.Image imgEscalada = iconOriginal.getImage().getScaledInstance(tamaño, tamaño, java.awt.Image.SCALE_SMOOTH);

                lblLogo.setIcon(new javax.swing.ImageIcon(imgEscalada));
                lblLogo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
                lblLogo.setVerticalAlignment(javax.swing.SwingConstants.CENTER);
            } else {
                System.out.println("No se encontró la imagen en la ruta.");
            }
        } catch (Exception e) {
            System.out.println("Error al cargar la imagen: " + e.getMessage());
        }
        FondoAND.add(lblLogo, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 20, 150, 150));

        lblNombre.setFont(new java.awt.Font("Segoe UI", 1, 30)); // NOI18N
        lblNombre.setForeground(new java.awt.Color(255, 255, 255));
        lblNombre.setText("<html><div style=´txt-aligin: center;´>FARMACIA <br>\"EL TILIN\"</div></html>");
        FondoAND.add(lblNombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 190, -1, -1));

        getContentPane().add(FondoAND, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnContinuarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnContinuarActionPerformed
        // TODO add your handling code here:

        /*
        ojo aca se uso un txt tipo password field, se usa ya que la clve lo va
        convertir en un arreglo de caracteres
         */
        String usuario = txtIngresarUsuario.getText().trim();
        String contraseña = new String(txtContraseña.getPassword()).trim();

        if (usuario.equals("roska") && contraseña.equals("1234")
                || usuario.equals("Ingresa tu usuario") && contraseña.equals("Ingresa tu contraseña")) {
            FondoAND.setVisible(false);
            PanelVentas panelVentas = new PanelVentas(this);

            getContentPane().add(panelVentas, java.awt.BorderLayout.CENTER);
            setExtendedState(javax.swing.JFrame.MAXIMIZED_BOTH);

            revalidate();
            repaint();

        } else {
            txtContraseña.setEchoChar((char) 0);
            txtContraseña.setForeground(java.awt.Color.RED);
            txtContraseña.setText("¡Usuario o Contraseña Incorrecto!");

        }

    }//GEN-LAST:event_btnContinuarActionPerformed

    private void btnAdminActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAdminActionPerformed

        // Oculta el panel de inicio de sesión sin destruirlo.
        FondoAND.setVisible(false);

        // Crea el panel de administrador y le pasa esta ventana.
        PanelAdmin ventanaAdmin = new PanelAdmin(this);
        // Permite ejecutar el botón CONTINUAR al presionar Enter.
        getRootPane().setDefaultButton(ventanaAdmin.getBtnContinuarAdmin());

        // Establece el tamaño de la ventana para el panel de administrador.
        setSize(400, 550);

// Centra la ventana en la pantalla.
        setLocationRelativeTo(null);

// Ajusta el tamaño del panel de administrador.
        ventanaAdmin.setBounds(0, 0, 400, 550);

        // Agrega el panel de administrador a la ventana.
        getContentPane().add(ventanaAdmin);

        // Cambia el título de la ventana.
        setTitle("Administrador - Farmacia El Tilín");

        // Actualiza la distribución y redibuja la ventana.
        revalidate();
        repaint();
    }//GEN-LAST:event_btnAdminActionPerformed

    private void btnSingUpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSingUpActionPerformed
        // TODO add your handling code here:

        // Oculta el panel del video/login sin destruirlo para mantener el video vivo
        FondoAND.setVisible(false);

        // Instancia el panel de registro pasándole esta ventana (InicioDeSesion)
        RegistroUsuario ventanaRegistro = new RegistroUsuario(this);
        ventanaRegistro.setBounds(0, 0, getWidth(), getHeight());

        // Agrega el panel de registro sobre la ventana actual
        getContentPane().add(ventanaRegistro);

        //Cambiar el titulo que esta puesto "Video ADN XDD"
        setTitle("Registro de Usuario - Farmacia El Tilín");

        //Refrescan ventanita para que se haga el nuevo panel
        revalidate();
        repaint();
    }//GEN-LAST:event_btnSingUpActionPerformed

    private void txtIngresarUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtIngresarUsuarioActionPerformed
        // TODO add your handling code here:
        txtContraseña.requestFocusInWindow();
    }//GEN-LAST:event_txtIngresarUsuarioActionPerformed

    private void txtIngresarUsuarioFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtIngresarUsuarioFocusGained
        // TODO add your handling code here:
        if (txtIngresarUsuario.getText().equals("Ingresa tu usuario")) {
            txtIngresarUsuario.setForeground(java.awt.Color.WHITE);
            txtIngresarUsuario.setText("");
        }
    }//GEN-LAST:event_txtIngresarUsuarioFocusGained

    private void txtContraseñaFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtContraseñaFocusLost
        // TODO add your handling code here:
        String contra = new String(txtContraseña.getPassword());
        if (contra.trim().isEmpty()) {
            txtContraseña.setForeground(java.awt.Color.gray);
            txtContraseña.setText("Ingresa tu contraseña");
            txtContraseña.setEchoChar((char) 0);
        }
    }//GEN-LAST:event_txtContraseñaFocusLost

    private void txtContraseñaFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtContraseñaFocusGained
        // TODO add your handling code here:
        String contra = new String(txtContraseña.getPassword());

        if (contra.equals("Ingresa tu contraseña") || contra.equals("¡Usuario o Contraseña Incorrecto!")) {
            txtContraseña.setForeground(java.awt.Color.WHITE);
            txtContraseña.setText("");
            txtContraseña.setEchoChar('*');
        }
    }//GEN-LAST:event_txtContraseñaFocusGained

    private void txtIngresarUsuarioFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtIngresarUsuarioFocusLost
        // TODO add your handling code here:
        if (txtIngresarUsuario.getText().trim().isEmpty()) {
            txtIngresarUsuario.setForeground(java.awt.Color.GRAY);
            txtIngresarUsuario.setText("Ingresa tu usuario");
        }
    }//GEN-LAST:event_txtIngresarUsuarioFocusLost

    private void txtContraseñaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtContraseñaActionPerformed
        // TODO add your handling code here:
        btnContinuar.requestFocusInWindow();
        btnContinuar.doClick();

    }//GEN-LAST:event_txtContraseñaActionPerformed

    private boolean contraseñaVisible = false;
    private void btnVerContrseñaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVerContrseñaActionPerformed
        // TODO add your handling code here:
        char contraOculta = '*';
        String contra = new String(txtContraseña.getPassword());

        if (contra.equals("Ingresa tu contraseña")) {
            txtContraseña.setEchoChar((char) 0);
            return;
        }

        contraseñaVisible = !contraseñaVisible;

        if (contraseñaVisible) {
            txtContraseña.setEchoChar((char) 0);
        } else {
            txtContraseña.setEchoChar(contraOculta);
        }
    }//GEN-LAST:event_btnVerContrseñaActionPerformed

    private void btnSingUpMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSingUpMouseClicked

    }//GEN-LAST:event_btnSingUpMouseClicked

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ReflectiveOperationException | javax.swing.UnsupportedLookAndFeelException ex) {
            logger.log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>


        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            //definimos las ventana
            InicioDeSesion ventanaLogin = new InicioDeSesion();
            //lo centramos
            ventanaLogin.setLocationRelativeTo(null);
            //se muestra la ventana
            ventanaLogin.setVisible(true);
        });

    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel FondoAND;
    private javax.swing.JButton btnAdmin;
    private javax.swing.JButton btnContinuar;
    private javax.swing.JButton btnSingUp;
    private javax.swing.JButton btnVerContrseña;
    private javax.swing.JLabel lblContraseña;
    private javax.swing.JLabel lblInciarSesion;
    private javax.swing.JLabel lblLogo;
    private javax.swing.JLabel lblNombre;
    private javax.swing.JLabel lblUsuario;
    private javax.swing.JPanel panelLogin;
    private javax.swing.JPasswordField txtContraseña;
    private javax.swing.JTextField txtIngresarUsuario;
    // End of variables declaration//GEN-END:variables
}
