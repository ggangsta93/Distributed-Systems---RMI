/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cliente.presentacion;

import cliente.ClienteDeObjetos;
import java.awt.AWTException;
import java.awt.Image;
import java.awt.SystemTray;
import java.awt.Toolkit;
import java.awt.TrayIcon;
import java.awt.TrayIcon.MessageType;
import java.awt.event.KeyEvent;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author javier
 */
public class GUICliente extends javax.swing.JFrame {

    /**
     * Creates new form GUICliente
     */
    private ClienteDeObjetos clienteDeObjetos;
    private String nickname;

    public GUICliente(ClienteDeObjetos clienteDeObjetos) {
        initComponents();
        this.setLocationRelativeTo(null);
        this.clienteDeObjetos = clienteDeObjetos;
    }
    
    public void setNickname(String nickname){
        this.nickname = nickname;
        this.setTitle("Chat - "+this.nickname);
    }
    public void fijarContactos(String[] conectados){
        jTextArea_conectados.setText("");
        for (String nickname : conectados) {
                jTextArea_conectados.append(nickname+"\n");
        }
    }
    
        
    public void fijarTexto(String Mensaje, String nicknameEmisor){
            jTextArea_dialogo.append(nicknameEmisor+": "+Mensaje+"\n");
    }
    
    
    public void fijarContacto(String nuevoConectado){
        try {
            SystemTray tray = SystemTray.getSystemTray();
            
            // Si quieres crear un icono en la bandeja del sistemas como vista previa
            Image image = Toolkit.getDefaultToolkit().getImage("Recursos/add.png");
            // Alternativamente (si el icono está en el directorio de la clase):
            //Image image = Toolkit.getDefaultToolkit().createImage(getClass().getResource());
            
            TrayIcon trayIcon = new TrayIcon(image, "Java AWT Tray Demo");
            // Deja que el sistema auto escale si es necesario
            trayIcon.setImageAutoSize(true);
            // Definir texto de tooltip (descripción emergente)
            trayIcon.setToolTip("System tray icon demo");
            tray.add(trayIcon);
            
            // Mostrar notificación de información:
            trayIcon.displayMessage("Nuevo usuario conectado.", nuevoConectado+" está en linea.", MessageType.INFO);
        } catch (AWTException ex) {
            Logger.getLogger(GUICliente.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextArea_conectados = new javax.swing.JTextArea();
        jButton_enviar = new javax.swing.JButton();
        jButton_salir = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jTextField_mensaje = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea_dialogo = new javax.swing.JTextArea();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Chat - Cliente");
        setMinimumSize(new java.awt.Dimension(950, 470));
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        jPanel1.setMinimumSize(new java.awt.Dimension(950, 470));

        jTextArea_conectados.setEditable(false);
        jTextArea_conectados.setColumns(20);
        jTextArea_conectados.setFont(new java.awt.Font("sansserif", 1, 13)); // NOI18N
        jTextArea_conectados.setLineWrap(true);
        jTextArea_conectados.setRows(5);
        jTextArea_conectados.setBorder(null);
        jScrollPane2.setViewportView(jTextArea_conectados);

        jButton_enviar.setFont(new java.awt.Font("sansserif", 1, 13)); // NOI18N
        jButton_enviar.setForeground(new java.awt.Color(255, 255, 255));
        jButton_enviar.setIcon(new javax.swing.ImageIcon("/home/javier/NetBeansProjects/SD_PAR2/Recursos/logo_enviar.png")); // NOI18N
        jButton_enviar.setMaximumSize(new java.awt.Dimension(40, 31));
        jButton_enviar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_enviarActionPerformed(evt);
            }
        });

        jButton_salir.setFont(new java.awt.Font("sansserif", 1, 13)); // NOI18N
        jButton_salir.setForeground(new java.awt.Color(0, 0, 0));
        jButton_salir.setIcon(new javax.swing.ImageIcon("/home/javier/NetBeansProjects/SD_PAR2/Recursos/logo_salir.png")); // NOI18N
        jButton_salir.setText("Salir");
        jButton_salir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_salirActionPerformed(evt);
            }
        });

        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel1.setFont(new java.awt.Font("sansserif", 1, 13)); // NOI18N
        jLabel1.setText("En linea");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(40, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(34, 34, 34))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 23, Short.MAX_VALUE)
        );

        jTextField_mensaje.setFont(new java.awt.Font("sansserif", 1, 13)); // NOI18N
        jTextField_mensaje.setPreferredSize(new java.awt.Dimension(64, 31));
        jTextField_mensaje.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextField_mensajeKeyPressed(evt);
            }
        });

        jTextArea_dialogo.setEditable(false);
        jTextArea_dialogo.setColumns(20);
        jTextArea_dialogo.setFont(new java.awt.Font("Dialog", 1, 13)); // NOI18N
        jTextArea_dialogo.setLineWrap(true);
        jTextArea_dialogo.setRows(5);
        jScrollPane1.setViewportView(jTextArea_dialogo);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane1)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jTextField_mensaje, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton_enviar, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(jButton_salir))
                .addGap(28, 28, 28))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 323, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton_enviar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton_salir)
                    .addComponent(jTextField_mensaje, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(51, 51, 51))
        );

        jLabel2.setIcon(new javax.swing.ImageIcon("/home/javier/NetBeansProjects/SD_PAR2/Recursos/publicidad.png")); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 709, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton_salirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_salirActionPerformed
        if(clienteDeObjetos.desconectar());
            System.exit(0);
    }//GEN-LAST:event_jButton_salirActionPerformed

    private void jButton_enviarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_enviarActionPerformed
        String mensaje = jTextField_mensaje.getText();
        clienteDeObjetos.enviarMensaje(mensaje);       
        jTextField_mensaje.setText("");
    }//GEN-LAST:event_jButton_enviarActionPerformed

    private void jTextField_mensajeKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField_mensajeKeyPressed
        if(evt.getKeyCode() == KeyEvent.VK_ENTER) {
            String mensaje = jTextField_mensaje.getText();
            clienteDeObjetos.enviarMensaje(mensaje);       
            jTextField_mensaje.setText("");
        }
    }//GEN-LAST:event_jTextField_mensajeKeyPressed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        if(clienteDeObjetos.desconectar());
            System.exit(0);
    }//GEN-LAST:event_formWindowClosing

    /**
     * @param args the command line arguments
     */
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton_enviar;
    private javax.swing.JButton jButton_salir;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextArea jTextArea_conectados;
    private javax.swing.JTextArea jTextArea_dialogo;
    private javax.swing.JTextField jTextField_mensaje;
    // End of variables declaration//GEN-END:variables
}
