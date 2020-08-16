
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
    public static int cantIf = 0;
    public static int cantWhile = 0;
    public static String assemblyFile = "";
    
    public static boolean anadirSimbolo(String simbolo,String scope,int linea){
        if(!existsSimbolo(simbolo)){
            LinkedList<String> listaCaract=new LinkedList<>();
            listaCaract.add(type);
            listaCaract.add(scope);
            tablaSimbolos.put(simbolo,listaCaract);
            //System.out.println(tablaSimbolos);
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
    
    public static void anadirValor(String simbolo){
        RS tope=pilaSemantica.pop();
        pilaSemantica.pop();
        System.out.println(tope);
        if(tope instanceof RS_DO){
            RS_DO convert=(RS_DO)tope;
            tablaSimbolos.get(simbolo).add(convert.getValor());
        }
        System.out.println(tablaSimbolos);
    }
    
    public static LinkedList<String> getCarSimbolo(String simbolo){
        if(existsSimbolo(simbolo)){
            return tablaSimbolos.get(simbolo);
        }
        LinkedList<String> listaVacia=new LinkedList<>();
        return listaVacia;
    }
    
    public static boolean limpiarPila(int linea){
        System.out.println(pilaSemantica);
        if(pilaSemantica.isEmpty()){
            return true;
        }
        RS tope = pilaSemantica.pop();
        if(tope instanceof RS_ID){
            RS_ID convert=(RS_ID)tope;
            if(!existsSimbolo(convert.getNombre())){
                if(error==null){
                    error="Simbolo no definido: "+convert.getNombre()+"\nLinea: "+linea;
                }
                return false;
            }else{
                String tipo=(getCarSimbolo(convert.getNombre())).getFirst();
                return limpiarPila(linea,tipo);
            }
        }
        if(tope instanceof RS_DO){
            RS_DO convert=(RS_DO)tope;
            if( convert.getValor().startsWith("\"") ){
                return limpiarPila(linea,"string");
            }else{
                //int
                return limpiarPila(linea,"int");
            }
            
        }
        if(tope instanceof RS_OPERADOR){
            limpiarPila(linea);
        }
        if(tope instanceof RS_IF){
            
        }
        if(tope instanceof RS_WHILE){
            
        }
        if(tope instanceof RS_RETURNS){
            if(pilaSemantica.isEmpty()){
                return false;
            }
            RS topeS = pilaSemantica.pop();
            if(tope instanceof  RS_RETURNS){
                return true;
            }
            else{
                pilaSemantica.push(topeS);
            }
        }
        if(tope instanceof RS_CONTROL){
            if(pilaSemantica.isEmpty()){
                return false;
            }
            RS topeS = pilaSemantica.pop();
            if(tope instanceof  RS_IF){
                pilaSemantica.push(topeS);
                return true;
            }
            else{
                if(error==null){
                    RS_CONTROL ctrl=(RS_CONTROL)tope;
                    error="Ubicacion no valida de simbolo: "+ctrl.getTipo()+"\nLinea: "+linea;
                }
                return false;
            }
            
        }
        return true;
    }
    
    public static boolean limpiarPila(int linea,String tipo){
        System.out.println(pilaSemantica);
        //System.out.println(tipo);
        if(pilaSemantica.isEmpty()){
            return true;
        }
        RS tope = pilaSemantica.pop();
        if(tope instanceof RS_ID){
            RS_ID convert=(RS_ID)tope;
            if(!existsSimbolo(convert.getNombre())){
                if(error==null){
                    error="Simbolo no definido: "+convert.getNombre()+"\nLinea: "+linea;
                }
                return false;
            }else{
                String tipoN=(getCarSimbolo(convert.getNombre())).getFirst();
                if(!tipo.equals(tipoN)){
                    if(error==null){
                        error="Simbolo no compatible: "+convert.getNombre()+"\nLinea: "+linea;
                    }
                    return false;
                }
                return limpiarPila(linea,tipoN);
            }
            
        }
        
        if(tope instanceof RS_DO){
            RS_DO convert=(RS_DO)tope;
            if( convert.getValor().startsWith("\"") ){
                if(!tipo.equals("string")){
                    if(error==null){
                        error="Simbolo no compatible: "+convert.getValor()+"\nLinea: "+linea;
                    }
                    return false;
                }
                return limpiarPila(linea,"string");
            }else{
                if(!tipo.equals("int")){
                    if(error==null){
                        error="Simbolo no compatible: "+convert.getValor()+"\nLinea: "+linea;
                    }
                    return false;
                }
                return limpiarPila(linea,"int");
            }
        }
        
        if(tope instanceof RS_OPERADOR){
            //System.out.println(tope.toString());
            RS_OPERADOR convert=(RS_OPERADOR)tope;
            if(tipo.equals("string")){
                if(convert.getOperador().equals("*") || convert.getOperador().equals("/") || 
                        convert.getOperador().equals("%") || convert.getOperador().equals("-")
                        || convert.getOperador().equals(">") || convert.getOperador().equals("<")
                        || convert.getOperador().equals(">=") || convert.getOperador().equals("<=")){
                    if(error==null){
                        error="Operacion no valida: "+convert.getOperador()+"\nLinea: "+linea;
                    }
                    return false;
                }
            }
            if(convert.getOperador().equals("=")){
                return validarAssignment(linea, tipo);
            }
            return limpiarPila(linea,tipo);
        }
        if(tope instanceof RS_IF){
            pilaSemantica.push(tope);
        }
        if(tope instanceof RS_WHILE){
            pilaSemantica.push(tope);
        }
        if(tope instanceof RS_RETURNS){
            pilaSemantica.push(tope);
        }
        return true;
    }
    
    public static boolean validarAssignment(int linea, String tipo){
        if(pilaSemantica.isEmpty()){
            return false;
        }
        RS tope = pilaSemantica.pop();
        
        if(tope instanceof RS_ID){
            RS_ID convert=(RS_ID)tope;
            if(!existsSimbolo(convert.getNombre())){
                if(error==null){
                    error="Simbolo no definido: "+convert.getNombre()+"\nLinea: "+linea;
                }
                return false;
            }else{
                String tipoN=(getCarSimbolo(convert.getNombre())).getFirst();
                if(!tipo.equals(tipoN)){
                    if(error==null){
                        error="Simbolo no compatible: "+convert.getNombre()+"\nLinea: "+linea;
                    }
                    return false;
                }
                return true;
            }
        }
        
        if(tope instanceof RS_DO){
            RS_DO convert=(RS_DO)tope;
            if(error==null){
                error="Simbolo no compatible: "+convert.getValor()+"\nLinea: "+linea;
            }
            return false;
        }
        
        if(tope instanceof RS_OPERADOR){
            //System.out.println(tope.toString());
            RS_OPERADOR convert=(RS_OPERADOR)tope;
            if(error==null){
                error="Operacion no valida: "+convert.getOperador()+"\nLinea: "+linea;
            }
            return false;
            
        }
        else{
            pilaSemantica.push(tope);
        }
        
        return true;
    }
    
    private static boolean exitsWhileAntes(){
        if (pilaSemantica.isEmpty()){
            return false;
        }
        else{
            for (RS registroSemantico : pilaSemantica){
                if(registroSemantico instanceof RS_WHILE){
                    return true;
                }
            }
        }
        return false;
    }
    
    private static RS getLastWhile(){
        if (pilaSemantica.isEmpty()){
            return null;
        }
        else{
            for (RS registroSemantico : pilaSemantica){
                if(registroSemantico instanceof RS_WHILE){
                    return registroSemantico;
                }
            }
        }
        return null;
    }
    
    /************************************************* ACCIONES SEMANTICAS ****************************************************/
    
    public static void accionContinue(int linea){
        if(exitsWhileAntes()){
            RS_WHILE registroWhile = (RS_WHILE) getLastWhile();
            String whileLabel = registroWhile.getWhile_label() + String.valueOf(cantWhile);
            assemblyFile += "jmp " + whileLabel + ":\n";
        }
        else{
            if(error==null){
                error="Continue en un bloque no valido" + "\nLinea: "+linea;
            }
        }
    }
    
    public static void accionBreak(int linea){
        if(exitsWhileAntes()){
            RS_WHILE registroWhile = (RS_WHILE) getLastWhile();
            String whileExitLabel = registroWhile.getExit_label()+ String.valueOf(cantWhile);
            assemblyFile += "jmp " + whileExitLabel + ":\n";
        }
        else{
            if(error==null){
                error="Break en un bloque no valido" + "\nLinea: "+linea;
            }
        }
    }
}
