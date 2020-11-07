/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package des5iv8;

/**
 *
 * @author demon
 */

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.crypto.*;

import javax.crypto.interfaces.*;
//de aqui vamos a ocupar el tipo de algoritmo para generar la interfaz

import javax.crypto.spec.*;
//es un elemento que nos ayuda a generar la llave o las subllaves

import java.security.*;
//esta libreria nos ayuda a definir el tipo de algoritmo simetrico o asimetrico a programar

public class DES5IV8 {

    /**
     * @param args the command line arguments
     * 
     * Vamos a crear un programa que se encargue de leer un fichero o archivo de texto
     * el cual con formato txt, pueda cifrar su contenido y asi ser protegido
     */
    public static void main(String[] args) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, FileNotFoundException, IOException, IllegalBlockSizeException, BadPaddingException {
        // TODO code application logic here
        
        if(args[0].length() != 1 ){
            mensajeAyuda();
            System.exit(1);
            
        }
        
        /*
        Paso 1 para sobrevivir a los cifrados
        
        Se necesita cargar por parte de security un "provider", que es el tipo
        de cifrado a programar, a partir de la generacion una instancia de la clase
        principal de Security y del Generador de llaves
        */
        
        System.out.println("1.- Generar llave DES: ");
        //para generar llaves es necesario utilizar KeyGenerator
        KeyGenerator generadorDES = KeyGenerator.getInstance("DES");
        
        //hay que inicializar la llave con el tamaño de 56 bits
        generadorDES.init(56);
        
        //vamos a generar las subllaves a partir del objeto
        SecretKey clave = generadorDES.generateKey();
        
        System.out.println("Clave : "+clave);
        //hay que darle un tratamiento porque al final son bits
        
        mostrarBytes(clave.getEncoded());
        
        System.out.println();
        
        //Paso 2 vamos a cifrar
        /*
        Para poder cifrar en un algoritmo simetrico tenemos que establecer ciertos puntos
        los cuales son el modo de operacion
        
        Modo : ECB (electronic code book)
        
        algortimo DES
        
        Rellene o no? 
        
        PKCS5Padding    Norma para el tipo de relleno para los cifrados 
        */
        
        Cipher cifrador = Cipher.getInstance("DES/ECB/PKCS5Padding");
        
        //vamos a cifrar wiiiii
        
        System.out.println("2.- Cifrar con DES el archivo : "+args[0]+ " y "
                + "el resultado cifrado esta en: "+args[0]+".cifrado");
        
        //ahora si wiiiii redes de feistel, y las tablas y todo ese hermoso proceso *w*
        
        cifrador.init(Cipher.ENCRYPT_MODE, clave);
        
        //wiii ya acabe
        //ahora tenemos que leer el archivo y cifrar los bites del archivo
        
        byte[] buffer = new byte[1000];
        byte[] buffercifrado;
        
        FileInputStream in = new FileInputStream(args[0]);
        FileOutputStream out = new FileOutputStream(args[0]+".cifrado");
        
        //mientras no se llegue la final del fichero o del archivo
        
        int bytesleidos = in.read(buffer, 0, 1000);
        while(bytesleidos != -1 ){
            buffercifrado = cifrador.update(buffer, 0, bytesleidos);
            out.write(buffercifrado);
            bytesleidos = in.read(buffer, 0, 1000);
        }
        //ya completo toda la lectura y escrita del archivo
        buffercifrado = cifrador.doFinal();
        out.write(buffercifrado);
        
        
        in.close();
        out.close();
        
        
        //ahora vamos a descifrar
        
        System.out.println("3.- Descifrar con DES el archivo : "+args[0]+ ".cifrado"
                + "el archivo descifrado esta en: "+args[0]+".descifrado");
        
        //ahora si wiiiii redes de feistel, y las tablas y todo ese hermoso proceso *w*
        
        cifrador.init(Cipher.DECRYPT_MODE, clave);
        
        //wiii ya acabe
        //ahora tenemos que leer el archivo y cifrar los bites del archivo
        
        //byte[] buffer = new byte[1000];
        byte[] bufferplano;
        
        in = new FileInputStream(args[0]+".cifrado");
        out = new FileOutputStream(args[0]+".descifrado");
        
        //mientras no se llegue la final del fichero o del archivo
        
        bytesleidos = in.read(buffer, 0, 1000);
        while(bytesleidos != -1 ){
            bufferplano = cifrador.update(buffer, 0, bytesleidos);
            out.write(bufferplano);
            bytesleidos = in.read(buffer, 0, 1000);
        }
        //ya completo toda la lectura y escrita del archivo
        bufferplano = cifrador.doFinal();
        out.write(bufferplano);
        
        
        in.close();
        out.close();
        
    }
    
    

    public static void mensajeAyuda() {
        System.out.println("Ejemplo de cifrado de un archivo mediante DES");
        System.out.println("Coopere para el xbox serie x por fis uwu");
        System.out.println("Pon el maldito archivo por eso no cifra nada T_T");
    }

    public static void mostrarBytes(byte[] buffer) {
        System.out.write(buffer, 0, buffer.length);
    }
    
}
