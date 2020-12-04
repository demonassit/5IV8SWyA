/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rsalib;

/**
 *
 * @author demon
 */

import java.security.*;
import javax.crypto.*;

import java.io.*;

/*
vamos a agregar la libreria de bouncycastle que nos va ayudar a generar
numero primos mas grandotes, para aumentar el nivel de seguridad del cifrado;
la desventaja de esta libreria es que al momento de cifrar los datos
solo maneja cifrador de flujo, y no tiene soporte para el cifrado por bloque
asi que de ser necesario que se utilicen bloques de informacion mas grandes
deberemos de implementar un metodo propio para segmentar en bloques de 64
la informacion que nos proporcionen

*/

import org.bouncycastle.jce.provider.BouncyCastleProvider;

public class RSAlib {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception {
        // TODO code application logic here
        
        //tenemos que agregar el provider a la clase security para poder
        //realizar instancias del obj
        Security.addProvider(new BouncyCastleProvider()); 
        
        System.out.println("1.- Creacion de las claves publicas y privada del algoritmo RSA");
        
        //necesitamos inicializar el generador de llaves,
        //es el encargado del calculo de e y d, publica y privada
        
        KeyPairGenerator generador = KeyPairGenerator.getInstance("RSA", "BC");
        
        //vamos a inicializar la llave a partir de 1024
        generador.initialize(8192);
        
        //calculamos las llaves privadas y publicas
        
        KeyPair clavesRSA = generador.generateKeyPair();
        
        //obtener la llave publica
        PublicKey llavepublica = clavesRSA.getPublic();
        
        //obtener la llave privada
        PrivateKey llaveprivada = clavesRSA.getPrivate();
        
        /*
        como habiamos dicho con BC no tenemos soporte para un cifrado por bloques
        asi que el cifrado sera por flujo, lo cual tenemos una limitante
        y es que el texto debe de ser de maximo 64 caracteres
        */
        
        System.out.println("Introduce el texto que deseas cifrar: ");
        byte[] bufferPlano = leerLinea(System.in);
        
        //paso para cifrar con llave publica
        
        //instancea del cifrado
        Cipher cifrador = Cipher.getInstance("RSA", "BC");
        
        //ciframos con clave publica
        cifrador.init(Cipher.ENCRYPT_MODE, llavepublica);
        
        System.out.println("Cifrando con la llave publica: ");
        byte[] buffercifrado = cifrador.doFinal(bufferPlano);
        System.out.println("El texto cifrado es: ");
        mostrarBytes(buffercifrado);
        System.out.println("\n");
        
        //desciframos con privada
        
        cifrador.init(Cipher.DECRYPT_MODE, llaveprivada);
        System.out.println("Descifrando con la llave privada: ");
        byte[] bufferdescifrado = cifrador.doFinal(buffercifrado);
        System.out.println("El texto descifrado es: ");
        mostrarBytes(bufferdescifrado);
        System.out.println("\n");
        
        
        //ahora vamos a cifrar con privada
        cifrador.init(Cipher.ENCRYPT_MODE, llaveprivada);
        
        System.out.println("Cifrando con la llave privada: ");
        buffercifrado = cifrador.doFinal(bufferPlano);
        System.out.println("El texto cifrado es: ");
        mostrarBytes(buffercifrado);
        System.out.println("\n");
        
        //desciframos con publica
        
        cifrador.init(Cipher.DECRYPT_MODE, llavepublica);
        System.out.println("Descifrando con la llave publica: ");
        bufferdescifrado = cifrador.doFinal(buffercifrado);
        System.out.println("El texto descifrado es: ");
        mostrarBytes(bufferdescifrado);
        System.out.println("\n");
        
    }

    public static byte[] leerLinea(InputStream in) throws IOException {
        byte[] buffer1 = new byte[1000];
        int i=0;
        byte c;
        c = (byte)in.read();
        while((c!='\n') && (i<1000)){
            buffer1[i]=c;
            c = (byte)in.read();
            i++;
        }
        
        byte[] buffer2 = new byte[i];
        for(int j = 0; j<i; j++){
            buffer2[j] = buffer1[j];
            
        }
        return buffer2;
    }

    public static void mostrarBytes(byte[] buffer) {
        System.out.write(buffer, 0, buffer.length);
    }
    
}
