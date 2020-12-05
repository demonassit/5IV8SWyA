/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rsaimportallave;

/**
 *
 * @author demon
 */

import java.io.*;
import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import javax.crypto.*;

//vamos a generar un archivo fis y fos para poder cargar con las llaves


public class RSAImportaLlave {
    
    private static Cipher rsa;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception{
        // TODO code application logic here
        //generar las claves
        KeyPairGenerator generador = KeyPairGenerator.getInstance("RSA");
        KeyPair llavecitas = generador.generateKeyPair();
        
        //ahora necesitamos la llave publica y la privada
        PublicKey llavepublica = llavecitas.getPublic();
        PrivateKey llaveprivada = llavecitas.getPrivate();
        
        /*
        vamos a enfrentarnos a un problema, y el problema es que al momento
        de nosotros importar o expotar las llaves, deben de tener un certificado
        mejor dicho un formato para que pueda ser validada, entonces es cuando
        se usa el estandar  de ciframiento
        */
        
        //vamos a guardar y cargar un archivo con el contenido de la llave publica
        guardarKey(llavepublica, "publickey.key");
        llavepublica = cargarPublicaKey("publickey.key");
        
        //vamos a guardar y cargar un archivo con el contenido de la llave privada
        guardarKey(llaveprivada, "privatekey.key");
        llaveprivada = cargarPrivadaKey("privatekey.key");
        
        //ya que tengo las llaves, entonces empezamos por el cifrado
        
        rsa = Cipher.getInstance("RSA/ECB/PKCS1Padding");
        
        //metemos el texto a cifrar
        String texto = "Habia una vez un patito que decia miau miau";
        
        //cifrar
        rsa.init(Cipher.ENCRYPT_MODE, llavepublica);
        byte[] encriptado = rsa.doFinal(texto.getBytes());
        
        //tenemos que escribir el texto cifrado para poder ver caracteres visibles
        //atraves de una codigicacion
        
        
        for(byte b : encriptado){
            System.out.println(Integer.toHexString(0xFF & b));
        }
        System.out.println("");
        
        //para descifrar
        rsa.init(Cipher.DECRYPT_MODE, llaveprivada);
        byte[] descifrado = rsa.doFinal(encriptado);
        
        //generar una cadena
        String textodescifrado = new String(descifrado);
        System.out.println(textodescifrado);
    }

    private static void guardarKey(Key llave, String archivo) throws FileNotFoundException, IOException {
        //generarme un archivo .dat
        byte[] llavepublic = llave.getEncoded();
        FileOutputStream fos = new FileOutputStream(archivo);
        fos.write(llavepublic);
        fos.close();
        
    }

    private static PublicKey cargarPublicaKey(String archivo) throws FileNotFoundException, IOException, NoSuchAlgorithmException, InvalidKeySpecException {
        /*
        para poder exportar la llave publica es necesario codificarla mediante una codificacion
        certificada por X509 es para la certificacion de la llave
        */
        
        FileInputStream fis = new FileInputStream(archivo);
        //comprobacion si es valida 
        int numBytes = fis.available();
        byte[] bytes = new byte[numBytes];
        fis.read(bytes);
        fis.close();
        
        //para comprobar la llave
        KeyFactory keyfactory = KeyFactory.getInstance("RSA");
        //generar la subllaves
        KeySpec spec = new X509EncodedKeySpec(bytes);
        
        PublicKey llavePublic = keyfactory.generatePublic(spec);
        
        return llavePublic;
        
    }

    private static PrivateKey cargarPrivadaKey(String archivo) throws FileNotFoundException, IOException, NoSuchAlgorithmException, InvalidKeySpecException {
        
        FileInputStream fis = new FileInputStream(archivo);
        //comprobacion si es valida 
        int numBytes = fis.available();
        byte[] bytes = new byte[numBytes];
        fis.read(bytes);
        fis.close();
        
        /*porque para la comprobacion de la llave privada, es necesario el 
        certificado por parte del estandar PKCS8 el cual nos dice el tipo 
        de codificacion que acepta una llave privada en RSA
        */
         //para comprobar la llave
        KeyFactory keyfactory = KeyFactory.getInstance("RSA");
        KeySpec spec = new PKCS8EncodedKeySpec(bytes);
        PrivateKey llavePrivate = keyfactory.generatePrivate(spec);
        return llavePrivate;
        
    }
    
}
