package com.iudigital.funcionarios.iudigital.presentacion;

import com.iudigital.funcionarios.iudigital.controller.EstadoCivilController;
import com.iudigital.funcionarios.iudigital.controller.FuncionariosController;
import com.iudigital.funcionarios.iudigital.controller.SexoController;
import com.iudigital.funcionarios.iudigital.controller.TipoIdentificacionController;
import com.iudigital.funcionarios.iudigital.dominio.Funcionario;
import com.iudigital.funcionarios.iudigital.dominio.GeneralPojo;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.SQLException;
import java.util.List;
import java.util.StringTokenizer;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
/**
 *
 * @author Cesar
 */
public class Main2 extends javax.swing.JFrame {
    private final FuncionariosController funcionariosController;
    private final SexoController sexoController;
    private final TipoIdentificacionController tipoIdentificacionController;
    private final EstadoCivilController estadoCivilController;
    private static final String[] COLUMNS= {"DOCUMENTO", "NOMBRE", "APELLIDOS", "DIRECCION", "TELEFONO", "FECHA DE NACIMIENTO", "SEXO", "ESTADO CIVIL"};
    private static final String SELECCIONE= "--Seleccione--";
    List<Funcionario> funcionarios;
    List<GeneralPojo> generos;
    List<GeneralPojo> tiposIdentificacion;
    List<GeneralPojo> estadosCivil;
    
    public Main2() {
        initComponents();
        funcionariosController= new FuncionariosController();
        sexoController= new SexoController();
        tipoIdentificacionController= new TipoIdentificacionController();
        estadoCivilController= new EstadoCivilController();
        cargarFuncionarios();
        cargarOpcionesSexo();
        cargarOpcionesTipoIdentificacion();
        cargarOpcionesEstadoCivil();
        addListener();
        
            /*
           TFNombres.setText("Leidy");
           TFApellidos.setText("Garcia");
           TFDireccion.setText("Circunvalar");
           TFFechaNacimiento.setText("2022");
           TFTelefono.setText("302");
        */
    }
    
     private void cargarOpcionesTipoIdentificacion(){
        CBTipoDocumento.removeAllItems();
        CBTipoDocumento.addItem(SELECCIONE);
        try {
            tiposIdentificacion=tipoIdentificacionController.obtenerOpciones();
            for(int i=0; i<tiposIdentificacion.size(); i++){
                CBTipoDocumento.addItem(tiposIdentificacion.get(i).getNombre());
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    
    private void cargarOpcionesEstadoCivil(){
        CBEstadoCivil.removeAllItems();
        CBEstadoCivil.addItem(SELECCIONE);
        try {
            estadosCivil= estadoCivilController.obtenerOpciones();
            for(int i=0; i<estadosCivil.size(); i++){
                CBEstadoCivil.addItem(estadosCivil.get(i).getNombre());
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    
    private void cargarOpcionesSexo(){
        CBSexo.removeAllItems();
        CBSexo.addItem(SELECCIONE);
        try {
            generos=sexoController.obtenerOpciones();
            for(int i=0; i<generos.size(); i++){
                CBSexo.addItem(generos.get(i).getNombre());
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    private void cargarFuncionarios(){
        CBFuncionarios.removeAllItems();
        Funcionario funcionario1 = new Funcionario();
        funcionario1.setNombre(SELECCIONE);
        CBFuncionarios.addItem(funcionario1);
        
        TableFuncionarios.removeAll();
        DefaultTableModel defaultTableModel= new DefaultTableModel();
        for (String COLUMN: COLUMNS){
            defaultTableModel.addColumn(COLUMN);
        }
        TableFuncionarios.setModel(defaultTableModel);
        try {
            funcionarios= funcionariosController.obtenerFuncionarios();
            if(funcionarios.isEmpty()){
                return;
            }
            defaultTableModel.setRowCount(funcionarios.size());
            int row=0;
            for(Funcionario funcionario: funcionarios){
                defaultTableModel.setValueAt(funcionario.getCedulafuncionario(), row, 0);
                defaultTableModel.setValueAt(funcionario.getNombre(), row, 1);
                defaultTableModel.setValueAt(funcionario.getApellidos(), row, 2);
                defaultTableModel.setValueAt(funcionario.getDireccion(), row, 3);
                defaultTableModel.setValueAt(funcionario.getTelefono(), row, 4);
                defaultTableModel.setValueAt(funcionario.getFechanacimiento(), row, 5);
                String estadoCivil= getNombreEstadoCivil(funcionario.getEstado_civil());
                System.out.println(estadoCivil);
                //defaultTableModel.setValueAt(funcionario.getApellidos(), row, 2);
                row++;
                CBFuncionarios.addItem(funcionario);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }  
    }
    
    private String getNombreEstadoCivil(int id){
     String estado="";
        for(int i=0; i<estadosCivil.size();i++){
            if(estadosCivil.get(i).getId()==id){
                estado=estadosCivil.get(i).getNombre();
            }
        }
     return estado;
    }
       
    private void addListener(){
        CBFuncionarios.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent arg0) {
                Funcionario selectedValue = (Funcionario) CBFuncionarios.getSelectedItem();
                StringTokenizer tokens=new StringTokenizer(selectedValue.toString(), ":");
                tokens.nextElement();
                String id=(String) tokens.nextElement();
                id=id.trim();
                if(id.equalsIgnoreCase("0")){
                    TFCedulaEdit.setText("");
                    TFApellidosEdit.setText("");
                    TFNombreEdit.setText("");
                }else{
                    for(int i=0; i<funcionarios.size(); i++){
                        if(funcionarios.get(i).getCedulafuncionario()==Integer.parseInt(id)){
                            TFCedulaEdit.setText(String.valueOf(funcionarios.get(i).getCedulafuncionario()));
                            TFNombreEdit.setText(funcionarios.get(i).getNombre());
                            TFApellidosEdit.setText(funcionarios.get(i).getApellidos());
                        }
                    }
                }
            }
        });
    } 
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        PanelCrear = new javax.swing.JTabbedPane();
        jPFuncionario = new javax.swing.JPanel();
        jPFuncionarios1 = new javax.swing.JPanel();
        LabelNombre = new javax.swing.JLabel();
        LabelApellidos = new javax.swing.JLabel();
        LabelCedula = new javax.swing.JLabel();
        TFNombres = new javax.swing.JTextField();
        TFApellidos = new javax.swing.JTextField();
        TFTelefono = new javax.swing.JTextField();
        BtnCrear = new javax.swing.JButton();
        CBEstadoCivil = new javax.swing.JComboBox<>();
        LabelSexo = new javax.swing.JLabel();
        LabelEstadoCivil = new javax.swing.JLabel();
        CBSexo = new javax.swing.JComboBox<>();
        LabelDireccion = new javax.swing.JLabel();
        TFDireccion = new javax.swing.JTextField();
        LabelFechaNacimiento = new javax.swing.JLabel();
        LabelTelefono = new javax.swing.JLabel();
        TFCedula1 = new javax.swing.JTextField();
        TFFechaNacimiento = new javax.swing.JTextField();
        LabelTipoDocumento = new javax.swing.JLabel();
        CBTipoDocumento = new javax.swing.JComboBox<>();
        jScrollPane1 = new javax.swing.JScrollPane();
        TableFuncionarios = new javax.swing.JTable();
        jLabel3 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        PanelEditar = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        LabelCedulaEdit = new javax.swing.JLabel();
        LabelNombreEdit = new javax.swing.JLabel();
        LabelApellidosEdit = new javax.swing.JLabel();
        TFCedulaEdit = new javax.swing.JTextField();
        TFNombreEdit = new javax.swing.JTextField();
        TFApellidosEdit = new javax.swing.JTextField();
        BtnEliminar = new javax.swing.JButton();
        BtnActualizar1 = new javax.swing.JButton();
        CBFuncionarios = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setText("Gestión funcionarios IUDigital");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 10, -1, -1));

        PanelCrear.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        jPFuncionarios1.setBorder(javax.swing.BorderFactory.createTitledBorder("Digite los siguientes campos"));

        LabelNombre.setText("Nombres");

        LabelApellidos.setText("Apellidos");

        LabelCedula.setText("N° Documento");

        TFNombres.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TFNombresActionPerformed(evt);
            }
        });

        TFApellidos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TFApellidosActionPerformed(evt);
            }
        });

        TFTelefono.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TFTelefonoActionPerformed(evt);
            }
        });

        BtnCrear.setText("Guardar");
        BtnCrear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnCrearActionPerformed(evt);
            }
        });

        LabelSexo.setText("Sexo");

        LabelEstadoCivil.setText("Estado civil");

        LabelDireccion.setText("Dirección");

        TFDireccion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TFDireccionActionPerformed(evt);
            }
        });

        LabelFechaNacimiento.setText("Fecha de nacimiento");

        LabelTelefono.setText("Telefono");

        TFCedula1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TFCedula1ActionPerformed(evt);
            }
        });

        TFFechaNacimiento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TFFechaNacimientoActionPerformed(evt);
            }
        });

        LabelTipoDocumento.setText("Tipo Documento");

        CBTipoDocumento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CBTipoDocumentoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPFuncionarios1Layout = new javax.swing.GroupLayout(jPFuncionarios1);
        jPFuncionarios1.setLayout(jPFuncionarios1Layout);
        jPFuncionarios1Layout.setHorizontalGroup(
            jPFuncionarios1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPFuncionarios1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPFuncionarios1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(LabelDireccion)
                    .addGroup(jPFuncionarios1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(TFDireccion, javax.swing.GroupLayout.PREFERRED_SIZE, 274, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPFuncionarios1Layout.createSequentialGroup()
                            .addGroup(jPFuncionarios1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(TFNombres, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(LabelNombre))
                            .addGap(18, 18, 18)
                            .addGroup(jPFuncionarios1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPFuncionarios1Layout.createSequentialGroup()
                                    .addComponent(LabelApellidos)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addComponent(TFApellidos)))))
                .addGap(28, 28, 28)
                .addGroup(jPFuncionarios1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPFuncionarios1Layout.createSequentialGroup()
                        .addComponent(CBTipoDocumento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPFuncionarios1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(jPFuncionarios1Layout.createSequentialGroup()
                                .addComponent(LabelCedula)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(LabelSexo, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPFuncionarios1Layout.createSequentialGroup()
                                .addComponent(TFCedula1, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(CBSexo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPFuncionarios1Layout.createSequentialGroup()
                        .addGroup(jPFuncionarios1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(TFFechaNacimiento, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(LabelFechaNacimiento)
                            .addComponent(BtnCrear)
                            .addComponent(LabelTipoDocumento))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPFuncionarios1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(TFTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(LabelTelefono))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPFuncionarios1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPFuncionarios1Layout.createSequentialGroup()
                                .addComponent(CBEstadoCivil, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(LabelEstadoCivil, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addGap(46, 46, 46))
        );
        jPFuncionarios1Layout.setVerticalGroup(
            jPFuncionarios1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPFuncionarios1Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(jPFuncionarios1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(LabelNombre)
                    .addComponent(LabelApellidos)
                    .addComponent(LabelCedula)
                    .addComponent(LabelTipoDocumento)
                    .addComponent(LabelSexo))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPFuncionarios1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(TFNombres, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(TFApellidos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(CBTipoDocumento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(TFCedula1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(CBSexo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(24, 24, 24)
                .addGroup(jPFuncionarios1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(LabelDireccion)
                    .addComponent(LabelFechaNacimiento)
                    .addComponent(LabelTelefono)
                    .addComponent(LabelEstadoCivil))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPFuncionarios1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(TFDireccion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(TFTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(TFFechaNacimiento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(CBEstadoCivil, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 16, Short.MAX_VALUE)
                .addComponent(BtnCrear)
                .addContainerGap())
        );

        TableFuncionarios.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane1.setViewportView(TableFuncionarios);

        jLabel3.setText("Funcionarios registrados");

        javax.swing.GroupLayout jPFuncionarioLayout = new javax.swing.GroupLayout(jPFuncionario);
        jPFuncionario.setLayout(jPFuncionarioLayout);
        jPFuncionarioLayout.setHorizontalGroup(
            jPFuncionarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPFuncionarioLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPFuncionarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane1)
                    .addComponent(jPFuncionarios1, javax.swing.GroupLayout.PREFERRED_SIZE, 725, Short.MAX_VALUE))
                .addContainerGap())
            .addGroup(jPFuncionarioLayout.createSequentialGroup()
                .addGap(298, 298, 298)
                .addComponent(jLabel3)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPFuncionarioLayout.setVerticalGroup(
            jPFuncionarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPFuncionarioLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPFuncionarios1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel3)
                .addGap(9, 9, 9)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        PanelCrear.addTab("Crear funcionario", jPFuncionario);

        jLabel2.setText("Funcionarios");

        LabelCedulaEdit.setText("Cedula");

        LabelNombreEdit.setText("Nombre");

        LabelApellidosEdit.setText("Apellidos");

        BtnEliminar.setText("Eliminar");

        BtnActualizar1.setText("Actualizar");

        javax.swing.GroupLayout PanelEditarLayout = new javax.swing.GroupLayout(PanelEditar);
        PanelEditar.setLayout(PanelEditarLayout);
        PanelEditarLayout.setHorizontalGroup(
            PanelEditarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelEditarLayout.createSequentialGroup()
                .addGroup(PanelEditarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(PanelEditarLayout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addGroup(PanelEditarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addGroup(PanelEditarLayout.createSequentialGroup()
                                .addComponent(TFCedulaEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(TFNombreEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(TFApellidosEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(PanelEditarLayout.createSequentialGroup()
                                .addComponent(LabelCedulaEdit)
                                .addGap(83, 83, 83)
                                .addComponent(LabelNombreEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(54, 54, 54)
                                .addComponent(LabelApellidosEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(CBFuncionarios, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(PanelEditarLayout.createSequentialGroup()
                        .addGap(159, 159, 159)
                        .addComponent(BtnActualizar1, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(32, 32, 32)
                        .addComponent(BtnEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(370, Short.MAX_VALUE))
        );
        PanelEditarLayout.setVerticalGroup(
            PanelEditarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelEditarLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addGroup(PanelEditarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(PanelEditarLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(CBFuncionarios, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(36, 36, 36)
                        .addGroup(PanelEditarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(LabelCedulaEdit)
                            .addComponent(LabelNombreEdit)
                            .addComponent(LabelApellidosEdit))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(PanelEditarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(TFCedulaEdit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(TFNombreEdit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(TFApellidosEdit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(PanelEditarLayout.createSequentialGroup()
                        .addGap(128, 128, 128)
                        .addGroup(PanelEditarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(BtnActualizar1)
                            .addComponent(BtnEliminar))))
                .addContainerGap(255, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(PanelEditar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(PanelEditar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        PanelCrear.addTab("Editar funcionario", jPanel2);

        getContentPane().add(PanelCrear, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 30, 750, 470));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void TFNombresActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TFNombresActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TFNombresActionPerformed

    private void TFApellidosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TFApellidosActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TFApellidosActionPerformed

    private void TFTelefonoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TFTelefonoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TFTelefonoActionPerformed

    private void BtnCrearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCrearActionPerformed
 
       if(validarCamposVacios()==9){
            String nombre = TFNombres.getText();
            String apellidos = TFApellidos.getText();
            String cedula = TFCedula1.getText();
            String fechanacimiento = TFFechaNacimiento.getText();
            String direccion = TFDireccion.getText();
            String telefono= TFTelefono.getText();
            int tel = 0;
            try {
                tel = Integer.parseInt(telefono);
             }
             catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Solo se aceptan números en el campo télefono!");
             }
            int ced = 0;
            try {
                ced = Integer.parseInt(cedula);
             }
             catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Solo se aceptan números en el campo cédula!");
             }
            int estadoC = 0;
             for(int i=0; i<estadosCivil.size();i++){
                 if(estadosCivil.get(i).getNombre()==CBEstadoCivil.getSelectedItem()){
                    estadoC=estadosCivil.get(i).getId();
                 }
             }
             int idSexo = 0;
             for(int i=0; i<generos.size();i++){
                 if(generos.get(i).getNombre()==CBSexo.getSelectedItem()){
                    idSexo=generos.get(i).getId();
                 }
             }
             int tipoI = 0;
             for(int i=0; i<tiposIdentificacion.size();i++){
                 if(tiposIdentificacion.get(i).getNombre()==CBTipoDocumento.getSelectedItem()){
                    tipoI=tiposIdentificacion.get(i).getId();
                 }
             }
            
            Funcionario funcionario = new Funcionario();
            funcionario.setNombre(nombre);
            funcionario.setApellidos(apellidos);
            funcionario.setCedulafuncionario(ced);
            funcionario.setFechanacimiento(fechanacimiento);
            funcionario.setDireccion(direccion);
            funcionario.setEstado_civil(estadoC);
            funcionario.setSexo(idSexo);
            funcionario.setTelefono(tel);
            funcionario.setTipoDocumento(tipoI);
            try{
                if(ced!=0){
                    funcionariosController.crearFuncionario(funcionario);
                    System.out.println("Funcionario creado!"); 
                }
            }catch (SQLException ex){
                ex.printStackTrace();
            } 
             cargarFuncionarios();
       }
        
    }//GEN-LAST:event_BtnCrearActionPerformed

    private void TFDireccionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TFDireccionActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TFDireccionActionPerformed

    private void TFCedula1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TFCedula1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TFCedula1ActionPerformed

    private void TFFechaNacimientoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TFFechaNacimientoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TFFechaNacimientoActionPerformed

    private void CBTipoDocumentoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CBTipoDocumentoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_CBTipoDocumentoActionPerformed

    private int validarCamposVacios() {
        int validados=0;
       if(TFCedula1.getText().trim().length()==0){
           JOptionPane.showMessageDialog(null, "Campo cédula vacio!");
        }else{
          validados++; 
       }
       if(TFNombres.getText().trim().length()==0){
           JOptionPane.showMessageDialog(null, "Campo nombres vacio!");
        }else{
           validados++;
       }
       if(TFApellidos.getText().trim().length()==0){
           JOptionPane.showMessageDialog(null, "Campo apellidos vacio!");
        }else{
          validados++;  
       }
       if(CBTipoDocumento.getSelectedItem()==SELECCIONE){
            JOptionPane.showMessageDialog(null, "Selecciona el tipo de documento!");
       }else{
           validados++;
       }
       if(CBSexo.getSelectedItem()==SELECCIONE){
            JOptionPane.showMessageDialog(null, "Selecciona el sexo!");
       }else{
           validados++;
       }
       if(TFDireccion.getText().toString().trim().length()==0){
            JOptionPane.showMessageDialog(null, "Campo dirección vacio!");
       }else{
           validados++;
       }
       if(TFFechaNacimiento.getText().toString().length()==0){
            JOptionPane.showMessageDialog(null, "Campo fecha de nacimiento vacio!");
       }else{
           validados++;
       }
       if(TFTelefono.getText().toString().length()==0){
            JOptionPane.showMessageDialog(null, "Campo télefono vacio!");
       }else{
           validados++;
       }
       if(CBEstadoCivil.getSelectedItem()==SELECCIONE){
            JOptionPane.showMessageDialog(null, "Selecciona el estado civil!");
       }else{
           validados++;
       }
       return validados;
    }
    
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Main2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Main2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Main2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Main2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Main2().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BtnActualizar1;
    private javax.swing.JButton BtnCrear;
    private javax.swing.JButton BtnEliminar;
    private javax.swing.JComboBox<String> CBEstadoCivil;
    private javax.swing.JComboBox<Funcionario> CBFuncionarios;
    private javax.swing.JComboBox<String> CBSexo;
    private javax.swing.JComboBox<String> CBTipoDocumento;
    private javax.swing.JLabel LabelApellidos;
    private javax.swing.JLabel LabelApellidosEdit;
    private javax.swing.JLabel LabelCedula;
    private javax.swing.JLabel LabelCedulaEdit;
    private javax.swing.JLabel LabelDireccion;
    private javax.swing.JLabel LabelEstadoCivil;
    private javax.swing.JLabel LabelFechaNacimiento;
    private javax.swing.JLabel LabelNombre;
    private javax.swing.JLabel LabelNombreEdit;
    private javax.swing.JLabel LabelSexo;
    private javax.swing.JLabel LabelTelefono;
    private javax.swing.JLabel LabelTipoDocumento;
    private javax.swing.JTabbedPane PanelCrear;
    private javax.swing.JPanel PanelEditar;
    private javax.swing.JTextField TFApellidos;
    private javax.swing.JTextField TFApellidosEdit;
    private javax.swing.JTextField TFCedula1;
    private javax.swing.JTextField TFCedulaEdit;
    private javax.swing.JTextField TFDireccion;
    private javax.swing.JTextField TFFechaNacimiento;
    private javax.swing.JTextField TFNombreEdit;
    private javax.swing.JTextField TFNombres;
    private javax.swing.JTextField TFTelefono;
    private javax.swing.JTable TableFuncionarios;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPFuncionario;
    private javax.swing.JPanel jPFuncionarios1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables

}
