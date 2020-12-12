/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package firmadigitalsimple;

/**
 *
 * @author demon
 */

import java.security.*;
import sun.misc.BASE64Encoder;

public class FirmaDigitalSimple {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception {
        // TODO code application logic here
        //generador de la inscia del rsa
        KeyPairGenerator generador = KeyPairGenerator.getInstance("RSA");
        
        //inicializar la llave
        generador.initialize(2048);
        
        ////creamos las llaves
        KeyPair llaves = generador.genKeyPair();
        
        //vamos a suponer que esto es el documento a firmar
        byte[] dato = "Habia una vez un patito que decia miau miau".getBytes();
        
        byte[] dato1 = "Habia una vez un patito que decia miau miau ".getBytes();
        
        //tenemos que utilizar a signature que es el que se encarga
        //para poder realizar la firma digital
        
        Signature firma = Signature.getInstance("SHA1WithRSA");
        //inicilizamos
        firma.initSign(llaves.getPrivate());
        
        firma.update(dato);
        
        //firmalo
        byte[] firmabytes = firma.sign();
        
        //para poder visualizar la firma
        System.out.println("Firma: " + new BASE64Encoder().encode(firmabytes));
        
        //el paso para verificarla
        firma.initVerify(llaves.getPublic());
        
        //volvemos a actualizar el documento
        firma.update(dato1);
        
        //verifico
        System.out.println(firma.verify(firmabytes));
    }
    
}
