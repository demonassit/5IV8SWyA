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
public class VigiMensaje {
    
    private String textoCifrado = "";
    private String textoClaro = "";
    
    //crear los objetos para llamar a los metodos de cifrado y descifrado
    
    CifrarVigenere cifravigi = new CifrarVigenere();
    DescifrarVigenere descifravigi = new DescifrarVigenere();
    
    //vamos a crear un metodo que se encargue del encriptado de datos para que hagan su interfaz
    
    public String encriptarTextoClaro(String textoClaro, String textoClave){
        String claveCompleta="";
        int indice = 0;
        
        //aqui lo que estamos haciendo es rellenar n veces la clave respecto del mensaje
        for(int i = 0; i<textoClaro.length(); i++){
            for(int j = 0; j<textoClave.length(); j++){
                //vamos a comparar el tamaño del mensaje vs clave
                if(claveCompleta.length() < textoClaro.length()){
                    if(textoClaro.charAt(indice) != ' '){
                        claveCompleta += textoClave.charAt(j) + "";
                    }else{
                        claveCompleta += " ";
                        j--;
                    }
                    indice++;
                }
            }
        }
        
        for(int i = 0; i<textoClaro.length(); i++){
            char TextoClaro = textoClaro.charAt(i);
            char TextoClave = claveCompleta.charAt(i);
            
            if(TextoClaro != ' '){
                textoCifrado = cifravigi.getTextoCifrado(TextoClaro, TextoClave)+"";
            }else{
                textoCifrado += "";
            }
        }
        return textoCifrado;
    }
    
    
    public String desencriptarTextoClaro(String textoCifrado, String textoClave){
        String claveCompleta="";
        int indice = 0;
        
        //aqui lo que estamos haciendo es rellenar n veces la clave respecto del mensaje
        for(int i = 0; i<textoCifrado.length(); i++){
            for(int j = 0; j<textoClave.length(); j++){
                //vamos a comparar el tamaño del mensaje vs clave
                if(claveCompleta.length() < textoCifrado.length()){
                    if(textoCifrado.charAt(indice) != ' '){
                        claveCompleta += textoClave.charAt(j) + "";
                    }else{
                        claveCompleta += " ";
                        j--;
                    }
                    indice++;
                }
            }
        }
        
        for(int i = 0; i<textoCifrado.length(); i++){
            char TextoCifrado = textoCifrado.charAt(i);
            char TextoClave = claveCompleta.charAt(i);
            
            if(TextoCifrado != ' '){
                textoClaro = descifravigi.getTextoDescifrado(TextoCifrado, TextoClave)+"";
            }else{
                textoClaro += "";
            }
        }
        return textoClaro;
    }
    
}
