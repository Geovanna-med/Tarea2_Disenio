/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

/**
 *
 * @author Andrea Ortega
 */
public class Alumno {
    private String nombre;
    private String PrimerApellido;
    private String SegundoApellido;
    private String matricula;
    private String Calificacion;
    
    public Alumno(String n, String PA, String SA, String m){
        this.nombre=n;
        this.PrimerApellido=PA;
        this.SegundoApellido=SA;
        this.matricula=m;
    }
    
    public String getCalificacion() {
        return Calificacion;
    }
    
    public String getNombre() {
        return nombre;
    }

    public String getPrimerApellido() {
        return PrimerApellido;
    }

    public String getSegundoApellido() {
        return SegundoApellido;
    }

    public String getMatricula() {
        return matricula;
    }
    
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setPrimerApellido(String PrimerApellido) {
        this.PrimerApellido = PrimerApellido;
    }

    public void setSegundoApellido(String SegundoApellido) {
        this.SegundoApellido = SegundoApellido;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public void setCalificacion(String Calificacion) {
        this.Calificacion = Calificacion;
    }
}
