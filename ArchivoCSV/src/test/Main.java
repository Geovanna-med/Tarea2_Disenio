/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import Control.ControlCSV;
import modelo.ArchivoCSV;
import vista.Vista;
import Pdf_Encrypter.Encryp_Decryp;
import vista.Login;

/**
 *
 * @author Andrea Ortega
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        ArchivoCSV csv = new ArchivoCSV();
        Vista view = new Vista();
        ControlCSV control = new ControlCSV(csv,view);
       
        
       view.setVisible(true);
       
        /*final String secretKey = "JHKLXABYZC!!!!";
	     
		    String originalString = "Java Web Developer";
		    String encryptedString = Encryp_Decryp.encrypt(originalString, secretKey) ;
		    String decryptedString = Encryp_Decryp.decrypt(encryptedString, secretKey) ;
		     
		    System.out.println("String Before Encryption is :");
		    System.out.println(originalString);
		    System.out.println("String After Encryption is :");
		    System.out.println(encryptedString);
		    System.out.println("String After Decryption is:");
		    System.out.println(decryptedString);
        */
    }
    
}
