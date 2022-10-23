
package presentacion;

import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class Vista extends javax.swing.JFrame {

    private final Modelo modelo;
    private final int NUM_PISOS = 10;
    private final int PISOS_SUBTERRANEOS = 3;
    
    Vista(Modelo aThis) {
        modelo=aThis;
        initComponents();
        this.setLocationRelativeTo(null);
        creacionLblPisos();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlPrincipal = new javax.swing.JPanel();
        lblTitulo = new javax.swing.JLabel();
        lblInfoPiso = new javax.swing.JLabel();
        pnlPisos = new javax.swing.JPanel();
        lblInfoEstados = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        lblInfoOcupantes = new javax.swing.JLabel();
        lblInfoCapacidad = new javax.swing.JLabel();
        lblOcupantes = new javax.swing.JLabel();
        lblCapacidad = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("SERVIDOR");
        setPreferredSize(new java.awt.Dimension(800, 900));

        pnlPrincipal.setBackground(new java.awt.Color(255, 255, 255));
        pnlPrincipal.setLayout(null);

        lblTitulo.setBackground(new java.awt.Color(204, 204, 255));
        lblTitulo.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 24)); // NOI18N
        lblTitulo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTitulo.setText("Ascensor Monitor");
        lblTitulo.setOpaque(true);
        pnlPrincipal.add(lblTitulo);
        lblTitulo.setBounds(234, 16, 328, 66);

        lblInfoPiso.setFont(new java.awt.Font("Arial Rounded MT Bold", 2, 14)); // NOI18N
        lblInfoPiso.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblInfoPiso.setText("PISOS");
        lblInfoPiso.setToolTipText("");
        pnlPrincipal.add(lblInfoPiso);
        lblInfoPiso.setBounds(80, 60, 120, 40);

        javax.swing.GroupLayout pnlPisosLayout = new javax.swing.GroupLayout(pnlPisos);
        pnlPisos.setLayout(pnlPisosLayout);
        pnlPisosLayout.setHorizontalGroup(
            pnlPisosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 120, Short.MAX_VALUE)
        );
        pnlPisosLayout.setVerticalGroup(
            pnlPisosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 600, Short.MAX_VALUE)
        );

        pnlPrincipal.add(pnlPisos);
        pnlPisos.setBounds(80, 100, 120, 600);

        lblInfoEstados.setFont(new java.awt.Font("Arial Rounded MT Bold", 2, 14)); // NOI18N
        lblInfoEstados.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblInfoEstados.setText("ESTADO ELEVADOR");
        lblInfoEstados.setToolTipText("");
        pnlPrincipal.add(lblInfoEstados);
        lblInfoEstados.setBounds(450, 140, 180, 40);

        lblInfoOcupantes.setText("PERSONAS:");

        lblInfoCapacidad.setText("CAPACIDAD:");

        lblOcupantes.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblOcupantes.setText("N");

        lblCapacidad.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblCapacidad.setText("N");

        jLabel2.setText("jLabel2");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(lblInfoOcupantes, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblCapacidad, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblOcupantes, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(258, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addGap(30, 30, 30)
                    .addComponent(lblInfoCapacidad, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(344, Short.MAX_VALUE)))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(lblCapacidad, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblInfoOcupantes, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblOcupantes, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(36, 36, 36)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(171, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addGap(32, 32, 32)
                    .addComponent(lblInfoCapacidad, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(319, Short.MAX_VALUE)))
        );

        pnlPrincipal.add(jPanel1);
        jPanel1.setBounds(300, 180, 460, 390);

        jLabel1.setText("jLabel1");
        pnlPrincipal.add(jLabel1);
        jLabel1.setBounds(400, 670, 280, 50);

        getContentPane().add(pnlPrincipal, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel lblCapacidad;
    private javax.swing.JLabel lblInfoCapacidad;
    private javax.swing.JLabel lblInfoEstados;
    private javax.swing.JLabel lblInfoOcupantes;
    private javax.swing.JLabel lblInfoPiso;
    private javax.swing.JLabel lblOcupantes;
    private javax.swing.JLabel lblTitulo;
    private javax.swing.JPanel pnlPisos;
    private javax.swing.JPanel pnlPrincipal;
    // End of variables declaration//GEN-END:variables
    private JLabel lblPisos[];
    private JLabel lblEstadoPisos[];
    
    private void creacionLblPisos(){
        lblPisos = new JLabel[NUM_PISOS];
        lblEstadoPisos = new JLabel[NUM_PISOS];
        
        int y = 0;
        int x;
        int dimension = 60;
        for(int i=NUM_PISOS-1;i>=0;i--){
            x = 0;
            //Creacion de los labels
            lblPisos[i] = new JLabel();
            pnlPisos.add(lblPisos[i]);
            lblPisos[i].setBackground(new java.awt.Color(255,51,51));
            lblPisos[i].setFont(new java.awt.Font("Consolas", 1, 36)); // NOI18N
            lblPisos[i].setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            if(i>PISOS_SUBTERRANEOS-1){
                lblPisos[i].setText(String.valueOf(i-PISOS_SUBTERRANEOS+1));
            }
            else{
                lblPisos[i].setText("S"+(PISOS_SUBTERRANEOS-i));
            }
            lblPisos[i].setOpaque(true);
            lblPisos[i].setBounds(x, y, dimension, dimension);
            
            //Creacion iconos de los pisos
            x+=dimension;
            lblEstadoPisos[i] = new JLabel();
            pnlPisos.add(lblEstadoPisos[i]);
            lblEstadoPisos[i].setBackground(new java.awt.Color(255, 255, 255));
            lblEstadoPisos[i].setBounds(x, y, dimension, dimension);
            lblEstadoPisos[i].setIcon(new ImageIcon(new ImageIcon(getClass().getResource("/presentacion/icons/inactive-icon.jpg")).getImage().getScaledInstance(lblEstadoPisos[i].getWidth(), lblEstadoPisos[i].getHeight(), Image.SCALE_DEFAULT)));
            lblEstadoPisos[i].setOpaque(true);
            y+=dimension;
            
        }
    }
    
    public void cambiarEstadoLblPiso(int piso, boolean habilitado){
        if(habilitado){
            lblPisos[piso].setBackground(new java.awt.Color(233, 255, 233));
            
        }
        else{
            lblPisos[piso].setBackground(new java.awt.Color(255,51,51));
            
        }
    }
    
    public void cambiarIconoLblPisos(int piso,String nombre){
        lblEstadoPisos[piso].setIcon(null);
        lblEstadoPisos[piso].setIcon(new ImageIcon(new ImageIcon(getClass().getResource("/presentacion/icons/"+nombre+"-icon.jpg")).getImage().getScaledInstance(lblEstadoPisos[piso].getWidth(), lblEstadoPisos[piso].getHeight(), Image.SCALE_DEFAULT)));
    }
    
}
