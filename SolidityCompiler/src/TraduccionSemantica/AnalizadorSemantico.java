
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TraduccionSemantica;

import java.util.HashMap;
import java.util.LinkedList;

/**
 *
 * @author adria
 */
public class AnalizadorSemantico {
    public static HashMap<String,LinkedList<String>> tablaSimbolos = new HashMap<>();
    public static String type="";
    public static String error=null;
    public static LinkedList<RS> pilaSemantica=new LinkedList<>();
    
    public static boolean anadirSimbolo(String simbolo,String scope,int linea){
        if(!existsSimbolo(simbolo)){
            LinkedList<String> listaCaract=new LinkedList<>();
            listaCaract.add(type);
            listaCaract.add(scope);
            tablaSimbolos.put(simbolo,listaCaract);
            return true;
        }
        if(error==null){
            error="Simbolo doble definido: "+simbolo+"\nLinea: "+(linea+1);
        }
        return false;
    }
    
    public static boolean existsSimbolo(String simbolo){
        return (tablaSimbolos.containsKey(simbolo));
    }
    
    public static boolean comprobarSimbolo(String simbolo){
        if(existsSimbolo(simbolo)){
            if (type.equals(getCarSimbolo(simbolo).get(0))){
                return true;
            }
            if(error==null){
                error="Simbolo con tipo incorrecto";
            }
        }
        
        return false;
    }
    
    public static LinkedList<String> getCarSimbolo(String simbolo){
        if(existsSimbolo(simbolo)){
            return tablaSimbolos.get(simbolo);
        }
        LinkedList<String> listaVacia=new LinkedList<>();
        return listaVacia;
    }
    
    public static boolean limpiarPila(){
        //pilaSemantica.pop();
        return true;
    }
}
