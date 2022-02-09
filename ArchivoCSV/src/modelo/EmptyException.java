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
public class EmptyException extends Exception{
    private String message;
    
    public EmptyException(String message) {
        super(message);
    }
}
