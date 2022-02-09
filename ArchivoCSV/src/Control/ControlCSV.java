/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import modelo.ArchivoCSV;
import modelo.EmptyException;
import modelo.NotFileException;
import vista.Vista;

/**
 *
 * @author Andrea Ortega
 */
public class ControlCSV implements ActionListener{
    private ArchivoCSV modelo;
    private Vista vista;
    private static int num=0;
    
    public ControlCSV(ArchivoCSV model, Vista view){
        this.modelo=model;
        this.vista=view;
        
        
        this.vista.getBotonExportar().addActionListener(this);
        this.vista.getBotonImportar().addActionListener(this);
        this.vista.getBotonGuardar().addActionListener(this);
        
        this.vista.getBotonGuardar().setVisible(false);
        this.vista.getBotonExportar().setVisible(false);
    }
    
    public void actionPerformed(ActionEvent evento){
        
        if(this.vista.getBotonImportar()==evento.getSource()){
            this.vista.getAviso().setText(" ");
            try{
                this.modelo.Importar();
                int lim;
            lim=this.modelo.getAlumnado().size();

            for(int i=0;i<lim;i++){
                this.vista.getTabla().setValueAt(this.modelo.getAlumnado().get(i).getMatricula(),i,0);
                this.vista.getTabla().setValueAt(this.modelo.getAlumnado().get(i).getPrimerApellido(),i,1);
                this.vista.getTabla().setValueAt(this.modelo.getAlumnado().get(i).getSegundoApellido(),i,2);
                this.vista.getTabla().setValueAt(this.modelo.getAlumnado().get(i).getNombre(),i,3);
            }
            
            this.vista.getBotonGuardar().setVisible(true);
            this.vista.getLabelCalif().setText("Calificación #"+(num+1));
            this.vista.getBotonImportar().setVisible(false);
            }catch(NotFileException e){
                JOptionPane.showMessageDialog(null, e.getMessage());
            }catch(EmptyException e){
                JOptionPane.showMessageDialog(null, e.getMessage());
            }    
        }
        if(this.vista.getBotonGuardar()==evento.getSource()){
            this.vista.getAviso().setText(" ");
            int aux;
            
            aux=(int)Double.parseDouble(this.vista.getTextoCalif().getText());
            if(aux<1||aux>100){
                this.vista.getAviso().setText("Calificación inválida");
            }else{
                this.modelo.getAlumnado().get(num).setCalificacion(this.vista.getTextoCalif().getText());
                this.num++;

                if(num==this.modelo.getAlumnado().size()){
                    this.vista.getAviso().setText("Calificaciones Completas");
                    this.vista.getBotonExportar().setVisible(true);
                }
                else{
                    this.vista.getLabelCalif().setText("Calificación #"+(num+1));
                    this.vista.getTextoCalif().setText("Inserte la calificacion");
                }
            }
        }
        if(this.vista.getBotonExportar()==evento.getSource()){
            int lim;
            lim=this.modelo.getAlumnado().size();
            
            for(int i=0;i<lim;i++){
                this.vista.getTabla().setValueAt(this.modelo.getAlumnado().get(i).getCalificacion(), i, 4);
            }
            
            this.modelo.Exportar();
            
            //this.vista.getDefaultCloseOperation().
        }
    }
}
