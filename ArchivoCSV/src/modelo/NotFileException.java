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
public class NotFileException extends Exception{
    private String message;
    
    public NotFileException(String message) {
        super(message);
    }
}
