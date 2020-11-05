/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vigenere5iv8;

/**
 *
 * @author demon
 */
public class VigeOperacion {
    
    
    //vamos a crear nuestras variables para el algoritmo
    char[] mensaje;
    char[] clave;
    char[] resultado;
    char matriz[][];    //tablero del abc
    
    //vamos a crear el constructor de la clase
    public VigeOperacion(){
    }
    
    public VigeOperacion(String msj, String clave){
        this.mensaje = msj.toCharArray();
        char[] claveTemp = clave.toCharArray();
        this.clave = new char[mensaje.length];
        
        int cont = 0;
        
        //recorrer el tamaño de la clave
        for(int i=0; i<mensaje.length; i++){
            this.clave[i] =claveTemp[cont];
            //tenemos que recorrer el mensaje y colocar tantas veces como sea necesaria la clave
            cont++;
            if(cont==claveTemp.length){
                cont=0;
            }
        }
        
        this.matriz = generarMatrizABC();
        Cifrar();
    }

    private char[][] generarMatrizABC() {
        
        int contador;
        char abcTemp[] = this.generarAbecedario();
        char abc[] = new char[abcTemp.length*2];
        
        for(int c=0; c<26; c++){
            abc[c]=abcTemp[c];
            abc[c+26] = abcTemp[c];
        }
        
        //utilizar la matriz
        char[][] matriz = new char[26][26];
        for(int i=0; i<26; i++){
            contador = 0;
            for(int j = 0; j<26; j++){
                matriz[i][j] = abc[contador+i];
                contador++;
            }
            
        }
        
        return matriz;
        
        
    }

    public void Cifrar() {
        //variables
        char[] cifrado = new char[mensaje.length];
        int i,j;
        
        //necesitamos identificar los caracteres si son letras
        for(int cont = 0; cont<mensaje.length; cont++){
            //identifique las letras atraves del codigo ASCII
            i = (int)this.mensaje[cont]-97;
            j = (int)this.clave[cont]-97;
            
            cifrado[cont] = this.matriz[i][j];
        }
        this.resultado = cifrado;
        //recorrer los elementos
        for(int k =0; k<26; k++){
            System.out.println(this.matriz[k]);
            System.out.println(this.mensaje);
            System.out.println(this.clave);
            System.out.println(cifrado);
        }
    }
    
    public String getMensajeCifrado(){
        String resultado = "";
        for(int i = 0; i<resultado.length(); i++){
            resultado += this.resultado[i];
        }
        return resultado;
    }

    private char[] generarAbecedario() {
        char[] abc = new char[26];
        for(int i = 97; i<=122; i++){
            abc[i-97]=(char)i;
        }
        return abc;
    }
    
    
}
