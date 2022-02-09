/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import com.csvreader.CsvReader;
import com.csvreader.CsvWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
/**
 *
 * @author Andrea Ortega
 */
public class ArchivoCSV {
    private static ArrayList<Alumno> alumnado= new ArrayList<Alumno>();

    public ArrayList<Alumno> getAlumnado() {
        return alumnado;
    }
    
    public void Importar() throws NotFileException, EmptyException{
        try{
            boolean existe = new File("Alumnos.csv").exists();
            if(existe){
            CsvReader lector = new CsvReader("Alumnos.csv");

            leerdoc(lector);

            }else{
                throw new NotFileException("No existe el archivo");
            }
        }catch(FileNotFoundException e){
            e.getMessage();
        }
    }
    
    public void leerdoc(CsvReader lector) throws EmptyException{
        try{
            int length=0;
            lector.readHeaders();
            
            if(lector.getHeaderCount()!=4){
                throw new EmptyException("El archivo no es correcto");
            }
            
            while(lector.readRecord()){
                String matricula=lector.get(0);
                String apellido1=lector.get(1);
                String apellido2=lector.get(2);
                String nombre=lector.get(3);
                
                alumnado.add(new Alumno(nombre,apellido1,apellido2,matricula));
                length++;
            }
            lector.close();
            
           if(length==0){
               throw new EmptyException("El archivo está vacío");
           } 
        }catch(IOException e){
            e.getMessage();
        }
    }
    
    public void Exportar(){
        String salida = "Alumnado.csv";
        boolean existe = new File(salida).exists();
        
        if(existe){
            File antiguo = new File(salida);
            antiguo.delete();
        }
        
        try{
            CsvWriter nuevoCSV = new CsvWriter(new FileWriter(salida,true), ',');
            
            nuevoCSV.write("Matricula");
            nuevoCSV.write("Primer Apellido");
            nuevoCSV.write("Segundo Apellido");
            nuevoCSV.write("Nombre");
            nuevoCSV.write("Calificaciones");
            nuevoCSV.endRecord();
            
            for(int i=0;i<alumnado.size();i++){
                nuevoCSV.write(alumnado.get(i).getMatricula());
                nuevoCSV.write(alumnado.get(i).getPrimerApellido());
                nuevoCSV.write(alumnado.get(i).getSegundoApellido());
                nuevoCSV.write(alumnado.get(i).getNombre());
                nuevoCSV.write(alumnado.get(i).getCalificacion());
                
                nuevoCSV.endRecord();
            }
            
            nuevoCSV.close();
            
        }catch(IOException e){
            e.getMessage();
        }
    }
}
