/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import controller.RegistroController;
import entity.Funcionario;
import entity.Registro;
import java.awt.Frame;
import java.awt.HeadlessException;
import java.awt.Toolkit;
import java.awt.Window;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.DefaultTableModel;
import model.CaptionUtils;

/**
 *
 * @author Carlos
 */
public class ListagemRegistros extends javax.swing.JDialog{

    /**
     * Creates new form ListagemRegistros
     */
    public ListagemRegistros(Frame owner, ModalityType modalityType) {
        super(owner, modalityType);
        initComponents();
        preencherTabela();
        centralizar();
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
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();

        setIconImages(IconUtils.getIcons());
        addWindowFocusListener(new java.awt.event.WindowFocusListener() {
            public void windowGainedFocus(java.awt.event.WindowEvent evt) {
                focusGained(evt);
            }
            public void windowLostFocus(java.awt.event.WindowEvent evt) {
            }
        });

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, CAPTIONS.getString("CHECKINLIST"), javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 14))); // NOI18N

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "", "", "", ""
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);
        jTable1.getColumnModel().getColumn(0).setHeaderValue(CAPTIONS.getString("ID"));
        jTable1.getColumnModel().getColumn(1).setHeaderValue(CAPTIONS.getString("WORKER"));
        jTable1.getColumnModel().getColumn(2).setHeaderValue(CAPTIONS.getString("TYPE"));
        jTable1.getColumnModel().getColumn(3).setHeaderValue(CAPTIONS.getString("DATE"));

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel1.setText(CAPTIONS.getString("ALLCHECKINS"));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 894, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 248, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        if(evt.getClickCount() == 2){
            int index = jTable1.getSelectedRow();
            Registro r = registros.get(index);
        }
    }//GEN-LAST:event_jTable1MouseClicked

    private void focusGained(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_focusGained
        if(funcionario ==null){        
            registros = controller.getAll();
            preencherTabela();
            jLabel1.setText("Todos os Registros");
        } else {
            jLabel1.setText("Registros de " + funcionario.getNome());
            registros = controller.getByParameter("funcionario", funcionario.getId());
            preencherTabela();
            funcionario = null;
        }
    }//GEN-LAST:event_focusGained

    public Funcionario getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;
    }

    /**
     * @param args the command line arguments
     */
    
    public void preencherTabela(){
        
        Object [] [] tabela = new Object[registros.size()][4];
        for(int i = 0; i< registros.size(); i++){
            int c = 0;
            tabela [i] [c++] = registros.get(i).getId();
            tabela [i] [c++] = registros.get(i).getFuncionario().getNome();
            tabela [i] [c++] = registros.get(i).getTipo().name();
            tabela [i] [c++] = dateFormat.format(registros.get(i).getData());
        }
        DefaultTableModel model;
        
        model = new DefaultTableModel(tabela, colunas){
               boolean[] canEdit = new boolean [] {
              false, false, false, false, false, false
          };

          @Override
          public boolean isCellEditable(int rowIndex, int columnIndex) {
              return canEdit [columnIndex];
          }
        };
        
        jTable1.setModel(model);
        jTable1.updateUI();
    }    
    
    private void centralizar() throws HeadlessException {
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        int x = (int) toolkit.getScreenSize().getWidth() / 2 - getWidth() / 2;
        int y = (int) toolkit.getScreenSize().getHeight()/ 2 - getHeight() / 2;
        setLocation(x, y);
    }
    
    public static ListagemRegistros getInstance(){
        if (INSTANCE == null){
            synchronized(ListagemRegistros.class){
                if(INSTANCE == null){
                    INSTANCE = new ListagemRegistros(null, ModalityType.DOCUMENT_MODAL);
                }
            }
        }
        return INSTANCE;
    }
    
    private static ListagemRegistros INSTANCE;
    private Funcionario funcionario;
    private RegistroController controller  = new RegistroController();
    
    private List<Registro> registros = new ArrayList<Registro>();
    private String [] colunas =  new String[]{"ID","Funcionario","Tipo","Data"};
    private SimpleDateFormat dateFormat = new SimpleDateFormat("EEE dd/MM/yyyy HH:mm:ss");
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables
    private static CaptionUtils CAPTIONS;

}
