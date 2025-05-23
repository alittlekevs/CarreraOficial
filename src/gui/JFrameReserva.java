/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package gui;

import datos.DAOReserva;
import datos.DAOSillas;
import entidades.Silla;
import java.util.List;
import java.util.stream.Collectors;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import entidades.Reserva;
import entidades.Tramo;
import entidades.Usuario;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
/**
 *
 * @author Kevin
 */
public class JFrameReserva extends javax.swing.JFrame {

    //En el toggle utilizo compareTo
    //En filtroPorTramo utilizo .stream con .equals + .collect(collection.sort) para ordenar por defecto
    
    private List<Silla> listaSillasDisponibles; // Lista completa de sillas disponibles
    private List<Silla> listaSillasReservables;
    private List<Silla> listaSillasReservadas;
    private DefaultTableModel modeloSillasDisponibles; //Tabla con los datos de las sillas disponibles
    private DefaultTableModel modeloSillasReservables; //Tabla con los datos de las sillas que se encuentran para reservar
    private static Usuario usuario;
    /**
     * Creates new form Reserva
     */
    public JFrameReserva(Usuario usuario) {
        this.usuario = usuario;
        setTitle("Reservar sillas");
        //Centrar
        initComponents();
        cargarTramos(); //Cargamos los nombres de los tramos en el comboBox
        cargarSillasSinReserva();
        filtroPorTramo();
        this.setLocationRelativeTo(null);
    }

    //Métodos
    //Método para cargar las sillas sin reserva
    private void cargarSillasSinReserva(){
        DAOSillas daoSillas = new DAOSillas();
        listaSillasDisponibles = daoSillas.consultarSillasSinReserva(); 
        
        actualizarTabla(listaSillasDisponibles);
    }
    
    //Método para cargar los datos de la consulta tramos
    private void cargarTramos(){
        DAOSillas daoSillas = new DAOSillas();
        List<String> tramos = daoSillas.consultarTramos();
        
        //Limpiamos la lista para que no se acumule
        tramoComboBox.removeAllItems();
        
        //Nombres de los tramos
        tramoComboBox.addItem("Todos"); // Así podemos volver a ver todas las sillas disponibles
        for (String tramo : tramos) {
            tramoComboBox.addItem(tramo);
        }
    }
    
    private void filtroPorTramo() {
        tramoComboBox.addActionListener(e -> {
            String tramoSeleccionado = (String) tramoComboBox.getSelectedItem();
            // Filtrar la lista de sillas según el tramo seleccionado
            List<Silla> sillasFiltradas;
            if ("Todos".equals(tramoSeleccionado)) {
                sillasFiltradas = listaSillasDisponibles; // Mostrar todas las sillas
            } else {
                sillasFiltradas = listaSillasDisponibles.stream() //Aplicamos stream para filtrar las sillas
                        .filter(silla -> silla.getTramo().getTramo().equals(tramoSeleccionado))
                        .collect(Collectors.toList()); //Ordenamiento por defecto (alfabético)
            }

            // Actualizar la tabla con las sillas filtradas
            actualizarTabla(sillasFiltradas);
        });
    }
    
    private void actualizarTabla(List<Silla> sillas) {
        modeloSillasDisponibles = (DefaultTableModel) tablaSillasDisponibles.getModel(); //Casteamos la table porque no es compatible
        modeloSillasDisponibles.setRowCount(0); // Aquí limpiamos la tabla

        for (Silla silla : sillas) {
            modeloSillasDisponibles.addRow(new Object[] { silla.getNumero(), silla.getTramo().getTramo(), 
                silla.getTramo().getPrecio() });
        }
    }
    
    private List<Silla> obtenerSillasReservables() {
        if (listaSillasReservables == null) {
            listaSillasReservables = new ArrayList<>();
        } else {
        listaSillasReservables.clear();
        }

        for (int i = 0; i < modeloSillasReservables.getRowCount(); i++) {
            int numero = Integer.parseInt(modeloSillasReservables.getValueAt(i, 0).toString());
            String tramoStr = modeloSillasReservables.getValueAt(i, 1).toString();
            double precio = Double.parseDouble(modeloSillasReservables.getValueAt(i, 2).toString());

            Tramo tramo = new Tramo(tramoStr, precio);
            Silla silla = new Silla(numero, tramo, null, null);
            listaSillasReservables.add(silla);
        }
        return listaSillasReservables;
    }
    
    private void actualizarPrecioTotal() {
        obtenerSillasReservables();

        Reserva reservaTemporal = new Reserva(LocalDate.now(), null, listaSillasReservables);
        double precioTotal = reservaTemporal.getPrecioTotal();
        campoPrecioTotal.setText(String.format("%.2f €", precioTotal));
    }
    
    
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tablaSillasDisponibles = new javax.swing.JTable();
        botonReservar = new javax.swing.JButton();
        etiquetaSillasDisponibles = new javax.swing.JLabel();
        tramoComboBox = new javax.swing.JComboBox<>();
        botonAgregar = new javax.swing.JButton();
        botonQuitar = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tablaReservables = new javax.swing.JTable();
        toggleOrdenPrecio = new javax.swing.JToggleButton();
        etiquetaTotal = new javax.swing.JLabel();
        campoPrecioTotal = new javax.swing.JLabel();
        jDateChooser1 = new com.toedter.calendar.JDateChooser();
        botonOrdenarPorNumero = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        menuInicio = new javax.swing.JMenu();
        menuItemSalir = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        tablaSillasDisponibles.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Número", "Tramo", "Precio"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Float.class, java.lang.String.class, java.lang.Double.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tablaSillasDisponibles.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(tablaSillasDisponibles);
        if (tablaSillasDisponibles.getColumnModel().getColumnCount() > 0) {
            tablaSillasDisponibles.getColumnModel().getColumn(0).setPreferredWidth(5);
            tablaSillasDisponibles.getColumnModel().getColumn(1).setPreferredWidth(140);
            tablaSillasDisponibles.getColumnModel().getColumn(2).setPreferredWidth(5);
        }

        botonReservar.setText("Reservar");
        botonReservar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                botonReservarMouseClicked(evt);
            }
        });

        etiquetaSillasDisponibles.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        etiquetaSillasDisponibles.setText("Reservar Sillas");

        tramoComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        tramoComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tramoComboBoxActionPerformed(evt);
            }
        });

        botonAgregar.setText("Agregar");
        botonAgregar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                botonAgregarMouseClicked(evt);
            }
        });
        botonAgregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonAgregarActionPerformed(evt);
            }
        });

        botonQuitar.setText("Quitar");
        botonQuitar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                botonQuitarMouseClicked(evt);
            }
        });
        botonQuitar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonQuitarActionPerformed(evt);
            }
        });

        tablaReservables.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Número", "Tramo", "Precio"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.Double.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(tablaReservables);

        toggleOrdenPrecio.setText("Ordenar por Precio");
        toggleOrdenPrecio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                toggleOrdenPrecioActionPerformed(evt);
            }
        });

        etiquetaTotal.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        etiquetaTotal.setText("TOTAL:");

        campoPrecioTotal.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        campoPrecioTotal.setText("0 €");

        botonOrdenarPorNumero.setText("Ordenar por Número");
        botonOrdenarPorNumero.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonOrdenarPorNumeroActionPerformed(evt);
            }
        });

        menuInicio.setText("Inicio");
        menuInicio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuInicioActionPerformed(evt);
            }
        });

        menuItemSalir.setText("Salir");
        menuItemSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuItemSalirActionPerformed(evt);
            }
        });
        menuInicio.add(menuItemSalir);

        jMenuBar1.add(menuInicio);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(toggleOrdenPrecio)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(botonOrdenarPorNumero, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(56, 56, 56)
                        .addComponent(etiquetaSillasDisponibles))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 430, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(botonAgregar)
                                    .addComponent(botonQuitar)))
                            .addComponent(tramoComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 301, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(etiquetaTotal)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(campoPrecioTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 272, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(jDateChooser1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addGap(18, 18, 18)
                                    .addComponent(botonReservar))
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 357, javax.swing.GroupLayout.PREFERRED_SIZE))))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(toggleOrdenPrecio)
                            .addComponent(botonOrdenarPorNumero))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addComponent(etiquetaSillasDisponibles)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 16, Short.MAX_VALUE)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jDateChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(tramoComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(botonReservar)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 275, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(botonAgregar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(botonQuitar)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(etiquetaTotal)
                    .addComponent(campoPrecioTotal))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void botonQuitarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonQuitarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_botonQuitarActionPerformed

    private void botonAgregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonAgregarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_botonAgregarActionPerformed

    private void botonAgregarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_botonAgregarMouseClicked
        int selectedRow = tablaSillasDisponibles.getSelectedRow();
        if (selectedRow != -1) {
            int numero = Integer.parseInt(tablaSillasDisponibles.getValueAt(selectedRow, 0).toString());
            String tramo = tablaSillasDisponibles.getValueAt(selectedRow, 1).toString();
            double precio = Double.parseDouble(tablaSillasDisponibles.getValueAt(selectedRow, 2).toString());

            // Busca la Silla correspondiente y elimínala de la lista lógica
            listaSillasDisponibles.removeIf(silla ->
                silla.getNumero() == numero &&
                silla.getTramo().getTramo().equals(tramo) &&
                silla.getTramo().getPrecio() == precio
        );

        // Agrega a la tabla visual de reservables
        modeloSillasReservables = (DefaultTableModel) tablaReservables.getModel();
        modeloSillasReservables.addRow(new Object[] { numero, tramo, precio });

        // Elimina de la tabla visual de disponibles
        modeloSillasDisponibles = (DefaultTableModel) tablaSillasDisponibles.getModel();
        modeloSillasDisponibles.removeRow(selectedRow);

        actualizarPrecioTotal();
        }
    }//GEN-LAST:event_botonAgregarMouseClicked

    private void botonQuitarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_botonQuitarMouseClicked
        int selectedRow = tablaReservables.getSelectedRow();
        if (selectedRow != -1) {
        int numero = Integer.parseInt(tablaReservables.getValueAt(selectedRow, 0).toString());
        String tramo = tablaReservables.getValueAt(selectedRow, 1).toString();
        double precio = Double.parseDouble(tablaReservables.getValueAt(selectedRow, 2).toString());

        //Arreglado el error que solo "ordenaba la lista visual y no la lógica"
        //Devolvemos la silla a la lista disponible
        Silla silla = new Silla(numero, new Tramo(tramo, precio), null, null);
        listaSillasDisponibles.add(silla);

        // Ordenamos por número asc
        listaSillasDisponibles.sort((s1, s2) -> s1.compareToNumero(s2));

        // Actualiza la tabla visual de disponibles
        actualizarTabla(listaSillasDisponibles);

        // Elimina de la tabla visual de reservables
        modeloSillasReservables = (DefaultTableModel) tablaReservables.getModel();
        modeloSillasReservables.removeRow(selectedRow);

        actualizarPrecioTotal();
        }
    }//GEN-LAST:event_botonQuitarMouseClicked

    private void botonReservarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_botonReservarMouseClicked
        if (tablaReservables.getRowCount()== 0) {
        JOptionPane.showMessageDialog(this,
                "No hay sillas seleccionadas para reservar.",
                "Advertencia",
                JOptionPane.WARNING_MESSAGE);
        return; 
        }
        int opcion = JOptionPane.showConfirmDialog(this,
            "¿Quieres confirmar la reserva?",
            "Confirmar Reserva",
            JOptionPane.YES_NO_OPTION);
        if (opcion == JOptionPane.YES_OPTION) {
        try {
            // Obtener la fecha seleccionada
            Date utilDate = jDateChooser1.getDate();
            if (utilDate == null) {
                JOptionPane.showMessageDialog(this,
                        "Selecciona una fecha para la reserva.",
                        "Advertencia",
                        JOptionPane.WARNING_MESSAGE);
                return;
            }
            LocalDate fechaReserva = utilDate.toInstant()
                .atZone(java.time.ZoneId.systemDefault())
                .toLocalDate();

            LocalDate hoy = LocalDate.now();
            if (!fechaReserva.isAfter(hoy)) {
                JOptionPane.showMessageDialog(this,
                        "La fecha de la reserva debe ser posterior a la fecha actual.",
                        "Advertencia",
                        JOptionPane.WARNING_MESSAGE);
                return;
            }

            // Obtener las sillas que se encuentran para reservar
            listaSillasReservadas = obtenerSillasReservables();

            Reserva reserva = new Reserva(fechaReserva, usuario, listaSillasReservables);

            // Insertamos la reserva
            DAOReserva daoReserva = new DAOReserva();
            daoReserva.insertarReserva(reserva);

            // Generar el archivo de la reserva
            reserva.generarReserva();

            // Confirmaci`´on
            String rutaArchivo = System.getProperty("user.home") + "/Desktop/reserva_" + reserva.getId() + ".txt";
            JOptionPane.showMessageDialog(this,
                    "Reserva realizada con éxito.\nID de Reserva: " + reserva.getId() +
                    "\nArchivo generado en:\n" + rutaArchivo,
                    "Éxito",
                    JOptionPane.INFORMATION_MESSAGE);

            cargarSillasSinReserva(); // Recargar las sillas disponibles
            modeloSillasReservables.setRowCount(0); // Limpiar la tabla de reservables
            actualizarPrecioTotal(); // Reiniciar el precio total

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this,
                    "Ocurrió un error al realizar la reserva.\n" + e.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }
    }//GEN-LAST:event_botonReservarMouseClicked
  
    private void toggleOrdenPrecioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_toggleOrdenPrecioActionPerformed
        if (toggleOrdenPrecio.isSelected()) {
              // Ordenar por precio descendente
            listaSillasDisponibles.sort((s1, s2) -> s1.compareToDesc(s2));
        } else {
            // Ordenar por precio ascendente
            listaSillasDisponibles.sort((s1, s2) -> s1.compareTo(s2));
        }
        actualizarTabla(listaSillasDisponibles);
    }//GEN-LAST:event_toggleOrdenPrecioActionPerformed

    private void tramoComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tramoComboBoxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tramoComboBoxActionPerformed

    private void menuInicioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuInicioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_menuInicioActionPerformed

    private void menuItemSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuItemSalirActionPerformed
        JFrameLogin newframe = new JFrameLogin();
        dispose();
        newframe.setVisible(true);
    }//GEN-LAST:event_menuItemSalirActionPerformed

    private void botonOrdenarPorNumeroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonOrdenarPorNumeroActionPerformed
        listaSillasDisponibles.sort((s1, s2) -> s1.compareToNumero(s2));
        actualizarTabla(listaSillasDisponibles);
    }//GEN-LAST:event_botonOrdenarPorNumeroActionPerformed

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
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(JFrameReserva.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(JFrameReserva.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(JFrameReserva.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(JFrameReserva.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new JFrameReserva(usuario).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton botonAgregar;
    private javax.swing.JButton botonOrdenarPorNumero;
    private javax.swing.JButton botonQuitar;
    private javax.swing.JButton botonReservar;
    private javax.swing.JLabel campoPrecioTotal;
    private javax.swing.JLabel etiquetaSillasDisponibles;
    private javax.swing.JLabel etiquetaTotal;
    private com.toedter.calendar.JDateChooser jDateChooser1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JMenu menuInicio;
    private javax.swing.JMenuItem menuItemSalir;
    private javax.swing.JTable tablaReservables;
    private javax.swing.JTable tablaSillasDisponibles;
    private javax.swing.JToggleButton toggleOrdenPrecio;
    private javax.swing.JComboBox<String> tramoComboBox;
    // End of variables declaration//GEN-END:variables
}
