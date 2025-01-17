/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package soliditycompiler;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Reader;
import java.io.StringReader;
import java.util.logging.Level;
import java.util.logging.Logger;
import java_cup.runtime.Symbol;
import javax.swing.JFileChooser;
import TraduccionSemantica.*;
import java.util.HashMap;

/**
 *
 * @author Charly Ponce
 */
public class FormScanner extends javax.swing.JFrame {

    /**
     * Creates new form FrmPrincipal
     */
    public FormScanner() {
        initComponents();
        this.setLocationRelativeTo(null);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnAnalizar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtResultado = new javax.swing.JTextArea();
        btnSalir = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtResultadoSintactico = new javax.swing.JTextArea();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        buttonSintactico = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        textSemantico = new javax.swing.JTextArea();
        jLabel3 = new javax.swing.JLabel();
        buttonSemantico = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        btnAnalizar.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        btnAnalizar.setText("Analizar");
        btnAnalizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAnalizarActionPerformed(evt);
            }
        });

        txtResultado.setColumns(20);
        txtResultado.setRows(5);
        jScrollPane1.setViewportView(txtResultado);

        btnSalir.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        btnSalir.setText("Salir");
        btnSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalirActionPerformed(evt);
            }
        });

        txtResultadoSintactico.setColumns(20);
        txtResultadoSintactico.setRows(5);
        jScrollPane2.setViewportView(txtResultadoSintactico);

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Analisis Léxico");

        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Análisis Sintáctico");

        buttonSintactico.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        buttonSintactico.setText("Analizar");
        buttonSintactico.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonSintacticoActionPerformed(evt);
            }
        });

        textSemantico.setColumns(20);
        textSemantico.setRows(5);
        jScrollPane3.setViewportView(textSemantico);

        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Analisis Semántico");

        buttonSemantico.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        buttonSemantico.setText("Analizar");
        buttonSemantico.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonSemanticoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 327, Short.MAX_VALUE)
                            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 257, Short.MAX_VALUE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnAnalizar, javax.swing.GroupLayout.PREFERRED_SIZE, 327, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(buttonSintactico, javax.swing.GroupLayout.DEFAULT_SIZE, 257, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 263, Short.MAX_VALUE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(buttonSemantico, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
            .addComponent(btnSalir, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 532, Short.MAX_VALUE)
                    .addComponent(jScrollPane2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnAnalizar)
                        .addComponent(buttonSintactico))
                    .addComponent(buttonSemantico, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 9, Short.MAX_VALUE)
                .addComponent(btnSalir))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnAnalizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAnalizarActionPerformed
        // TODO add your handling code here:
        
        JFileChooser seleccionador = new JFileChooser(); //crea el file chooser
        seleccionador.showOpenDialog(null);
        
        try {
            Reader lector = new BufferedReader(new FileReader(seleccionador.getSelectedFile()));
            LexerF lexer = new LexerF(lector);
            String resultado = "";
            
            while (true) {
                Tokens tokens = lexer.yylex();
                if (tokens == null) {
                    resultado += "FIN";
                    txtResultado.setText(resultado);
                    return;
                }
                switch (tokens) {
                    case Error:
                        resultado += "Simbolo no definido." + "\t | Linea: " + (lexer.line +1) + " \tColumna: "+ (lexer.column +1) +"\n";
                        break;
                    case Identificador: case Numero: case Cadena: case Hexadecimal: case Flotante: case Cientifico: 
                        resultado += lexer.lexeme + ": \t | " + tokens + "\t | Linea: " + (lexer.line +1) + " \tColumna: "+ (lexer.column +1) + "\n";
                        break;
                    default:
                        resultado += "Token: " + tokens + "\t\t | Linea: " + (lexer.line +1) + " \tColumna: "+ (lexer.column +1)+ "\n";
                        break;
                }
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(FormScanner.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(FormScanner.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnAnalizarActionPerformed

    private void btnSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalirActionPerformed
        // TODO add your handling code here:
        dispose();
    }//GEN-LAST:event_btnSalirActionPerformed

  private void buttonSintacticoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonSintacticoActionPerformed
    // TODO add your handling code here:
    //CODIGO SINTACTICO
        Parser.SEList.clear();
        JFileChooser seleccionador = new JFileChooser(); //crea el file chooser
        seleccionador.showOpenDialog(null);
        Symbol j;
        Parser s;
        try{
            Reader lector = new BufferedReader(new FileReader(seleccionador.getSelectedFile()));
            LexerC lexer = new LexerC(lector);
            s=new Parser(lexer);
            try {
                  s.parse();
                  if(!Parser.SEList.isEmpty()){
                    String error="Errores:\n";
                    for (SError SEList : Parser.SEList) {
                      error+="Error: ";
                      error+=SEList.getDescripcion();
                      error+=", linea: ";
                      error+=(SEList.getLine()+1);
                      error+=", Columna: ";
                      error+=SEList.getColumna();
                      error+=", Simbolo: ";
                      error+=SEList.getLexema();
                      error+="\n";
                      
                    }
                    txtResultadoSintactico.setText(error);
                    
                  }else{
                    txtResultadoSintactico.setText("Analisis realizado exitosamente");
                    System.out.println(AnalizadorSemantico.tablaSimbolos.keySet());
                    System.out.println(AnalizadorSemantico.type);
                  }
                //}
            } catch (Exception e) {
                Symbol sym=s.getS();
                if(sym.value!=null || !Parser.SEList.isEmpty()){
                  txtResultadoSintactico.setText("Error de sintaxis. Linea: "+(sym.right+1)+" Columna: "+(sym.left + 1)+", Texto: "+sym.value);
                }else{
                  txtResultadoSintactico.setText("Analisis realizado exitosamente");
                }
            }
        }catch (Exception ex){
            
        }
  }//GEN-LAST:event_buttonSintacticoActionPerformed

    private void buttonSemanticoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonSemanticoActionPerformed
        // TODO add your handling code here:
        Parser.SEList.clear();
        txtResultadoSintactico.setText("");
        
        AnalizadorSemantico.reiniciarVariables();
        JFileChooser seleccionador = new JFileChooser(); //crea el file chooser
        seleccionador.showOpenDialog(null);
        Symbol j;
        Parser s;
        try{
            Reader lector = new BufferedReader(new FileReader(seleccionador.getSelectedFile()));
            LexerC lexer = new LexerC(lector);
            s=new Parser(lexer);
            try {
                  s.parse();
                  if(!Parser.SEList.isEmpty()){
                    String error="Errores:\n";
                    for (SError SEList : Parser.SEList) {
                      error+="Error: ";
                      error+=SEList.getDescripcion();
                      error+=", linea: ";
                      error+=(SEList.getLine()+1);
                      error+=", Columna: ";
                      error+=SEList.getColumna();
                      error+=", Simbolo: ";
                      error+=SEList.getLexema();
                      error+="\n";
                      
                    }
                    txtResultadoSintactico.setText(error);
                  }else{
                    if(AnalizadorSemantico.error!=null){
                        textSemantico.setText(AnalizadorSemantico.error);
                    }else{
                        if(!AnalizadorSemantico.pilaSemantica.isEmpty()){
                            //System.out.println(AnalizadorSemantico.pilaSemantica);
                            RS tope=AnalizadorSemantico.pilaSemantica.pop();
                        
                            if(tope instanceof RS_FUNCION){
                                textSemantico.setText("No se cerro el return de la función.");
                            }else{
                                //System.out.println(AnalizadorSemantico.pilaSemantica);
                                textSemantico.setText("Ha ocurrido un error.");
                            }
                        }
                        else{
                            AnalizadorSemantico.inicializarContenidoArchivo();
                            AnalizadorSemantico.pasarSimbolosAArchivo();
                            System.out.println("CONTENIDO ARCHIVO:");
                            System.out.println(AnalizadorSemantico.contenidoArchivo);
                            System.out.println("EMPIEZA A TRADUCIR");
                            AnalizadorSemantico.translateToNasm();
                            System.out.println("TERMINO DE TRADUCIR");
                            textSemantico.setText("Analisis concluido y código generado exitosamente. \nTabla de simbolos:\n"+AnalizadorSemantico.tablaSimbolos.toString());
                        }
                    }
                    //System.out.println(AnalizadorSemantico.tablaSimbolos.keySet());
                    //System.out.println(AnalizadorSemantico.type);
                  }
                //}
            } catch (Exception e) {
                Symbol sym=s.getS();
                if(sym.value!=null || !Parser.SEList.isEmpty()){
                  txtResultadoSintactico.setText("Error de sintaxis. Linea: "+(sym.right+1)+" Columna: "+(sym.left + 1)+", Texto: "+sym.value);
                }else{
                  txtResultadoSintactico.setText("Error no determinado");
                }
            }
        }catch (Exception ex){
            
        }
        
    }//GEN-LAST:event_buttonSemanticoActionPerformed

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
            java.util.logging.Logger.getLogger(FormScanner.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FormScanner.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FormScanner.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FormScanner.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FormScanner().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAnalizar;
    private javax.swing.JButton btnSalir;
    private javax.swing.JButton buttonSemantico;
    private javax.swing.JButton buttonSintactico;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTextArea textSemantico;
    private javax.swing.JTextArea txtResultado;
    private javax.swing.JTextArea txtResultadoSintactico;
    // End of variables declaration//GEN-END:variables
}
