
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TraduccionSemantica;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;


/**
 *
 * @author Adrian, Juandi, Ariel
 */
public class AnalizadorSemantico {
    public static HashMap<String,LinkedList<String>> tablaSimbolos = new HashMap<>();
    public static String type="";
    public static String error=null;
    public static LinkedList<RS> pilaSemantica=new LinkedList<>();
    public static int cantIf = 0;
    public static int cantWhile = 0;
    public static LinkedList<LinkedList <LinkedList <String>>> contenidoArchivo=new LinkedList<>();
    
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
    
    public static void inicializarContenidoArchivo(){
        LinkedList<LinkedList<String>> variablesDefinidas=new LinkedList<>();
        contenidoArchivo.add(variablesDefinidas);
        LinkedList<LinkedList<String>> variablesNoDefinidas=new LinkedList<>();
        contenidoArchivo.add(variablesNoDefinidas);
        LinkedList<LinkedList<String>> codigo=new LinkedList<>();
        contenidoArchivo.add(codigo);
    }
    
    public static void pasarSimbolosAArchivo(){
        for (String simbolo: tablaSimbolos.keySet()){
            LinkedList<String> lista=getCarSimbolo(simbolo);
            if(lista.size()>2){
                //Esta inicializada
                LinkedList<String> nuevaLista=new LinkedList<>();
                
                nuevaLista.add(nuevaLista.get(0));
                nuevaLista.add(simbolo);
                nuevaLista.add(nuevaLista.get(2));
                contenidoArchivo.get(0).add(nuevaLista);
            }else{
                //No esta inicializada
                LinkedList<String> nuevaLista=new LinkedList<>();
                nuevaLista.add(nuevaLista.get(0));
                nuevaLista.add(simbolo);
                contenidoArchivo.get(0).add(nuevaLista);
            }
        }
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
    
    private static RS getLastIf(){
        if(pilaSemantica.isEmpty()){
            return null;
        }
        else{
            for(RS registroSemantico : pilaSemantica){
                if(registroSemantico instanceof RS_IF){
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
            LinkedList<String> instruccion = new LinkedList<>();
            instruccion.add("jmp " + whileLabel);
            contenidoArchivo.get(2).add(instruccion);
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
            LinkedList<String> instruccion = new LinkedList<>();
            instruccion.add("jmp " + whileExitLabel);
            contenidoArchivo.get(2).add(instruccion);
        }
        else{
            if(error==null){
                error="Break en un bloque no valido" + "\nLinea: "+linea;
            }
        }
    }
    
    public static void accionGuardarConstante(String valor){
        RS_DO nuevo = new RS_DO(valor);
        nuevo.setConst();
        pilaSemantica.push(nuevo);
    }
    
    public static void accionGuardarVariable(String nombre, int linea){
        RS_DO nuevo = new RS_DO(nombre);
        nuevo.setDir();
        if(existsSimbolo(nombre)){
            pilaSemantica.add(nuevo);
        }
        else{
            if(error==null){
                error= nombre + ": variable no definida" + "\nLinea: "+linea;
            }
        }
    }
    
    public static void accionGuardarOperador(String operador){
        RS_OPERADOR nuevo = new RS_OPERADOR(operador);
        pilaSemantica.push(nuevo);
    }
    
    public static void evalBinaryAritmetico(){
    
    }
    
    public static void evalBinaryBooleano(){
        
    }
    
    public static void accionGuardarTipo(String tipo){
        type=tipo;
        RS_TIPO nuevo=new RS_TIPO(tipo);
        pilaSemantica.push(nuevo);
    }
    
    public static void accionGuardarId(String Id){
        RS_ID nuevo=new RS_ID(Id);
        pilaSemantica.push(nuevo);
    }
    
    public static void accionGuardarEnTablaSim(String Id, String scope, int linea){
        if(!existsSimbolo(Id)){
            LinkedList<String> listaCaract=new LinkedList<>();
            listaCaract.add(type);
            listaCaract.add(scope);
            tablaSimbolos.put(Id,listaCaract);
            //System.out.println(tablaSimbolos);
        }
        if(error==null){
            error="Simbolo doble definido: "+Id+"\nLinea: "+(linea+1);
        }
    }
    
    public static void accionStartIf(){
        String exitLabel=""+cantIf;
        String elseLabel=""+cantIf;
        RS_IF nuevo=new RS_IF(exitLabel,elseLabel);
        pilaSemantica.push(nuevo);
    }
    
    public static void accionTestIf(){
        //Codigo para evaluar el condicional
    }
    
    public static void accionStartElse(){
        RS_IF rsIf = (RS_IF) getLastIf();
        String ifExitLabel = rsIf.getExit_label();
        String ifElseLabel = rsIf.getElse_label();
        LinkedList<String> instruccion = new LinkedList<>();
        instruccion.add("jmp " + ifExitLabel);
        contenidoArchivo.get(2).add(instruccion);
        instruccion.clear();
        instruccion.add(ifElseLabel + ":");
        contenidoArchivo.get(2).add(instruccion);
    }
    
    public static void accionEndIf(){
        RS_IF rsIf = (RS_IF) getLastIf();
        String ifExitLabel = rsIf.getExit_label();
        LinkedList<String> instruccion = new LinkedList<>();
        instruccion.add(ifExitLabel + ":");
        contenidoArchivo.get(2).add(instruccion);
        pilaSemantica.pop();
    }
    
    public static void accionStartWhile(){
        String whileLabel=""+cantWhile;
        String exitLabel=""+cantWhile;
        RS_WHILE nuevo=new RS_WHILE(whileLabel,exitLabel);
        pilaSemantica.push(nuevo);
    }
    
    public static void accionTestWhile(){
        //Codigo de la evaluacion del while
    }
    
    public static void accionEndWhile(){
        //Jump while
        //label exit
        pilaSemantica.pop();
    }


    public static void translateToNasm(){
        String pathPackage = "D:\\GitHub\\Project-C\\";
        String pathASM = pathPackage + "code.asm";
        
        try {
            
            File translated =  new File(pathASM);
            if(translated.exists()){
                translated.delete();
            } else{
                if(translated.createNewFile()){
                    System.out.println("File created: " + translated.getName());
                } else {
                    System.out.println("File already exists.");
                }
            }

        } catch (IOException e) {
            System.out.println("An error Ocurred creating the file.");
            e.printStackTrace();
        }

        try {
            FileWriter translator = new FileWriter(pathASM);
            translator.write("segment .data\n \n");

            //Write Variables inicializadas
            for(LinkedList<String> node : contenidoArchivo.get(0)){
                if(node.get(0)=="int"){
                    String value = node.get(1)+":\t dd\t" + node.get(2);
                    
                    System.out.println(value);
                    translator.write(value);
                    translator.write("\n");
                }
                if(node.get(0)== "string"){
                    String value = node.get(1)+":\t db\t" + "\"" + node.get(2) + "\"" + ",0";
                    
                    System.out.println(value);
                    translator.write(value);
                    translator.write("\n");
                }
            }
            
            translator.write("\n \n");
            translator.write("segment .bss\n \n");

            //Write variables sin inicializar
            for(LinkedList<String> node : contenidoArchivo.get(1)){
                if(node.get(0)=="int"){
                    String value = node.get(1)+":\t resd\t 1";
                    
                    System.out.println(value);
                    translator.write(value);
                    translator.write("\n");
                }
                if(node.get(0)== "string"){
                    String value = node.get(1)+":\t resb\t 255"; //faltan las comillas en el string
                    
                    System.out.println(value);
                    translator.write(value);
                    translator.write("\n");
                }

            }

            translator.write("\n \n");
            translator.write("segment .text\n \n");

            //Write Code
            for(LinkedList<String> node : contenidoArchivo.get(2)){
                String value = node.get(0);
                    
                    System.out.println(value);
                    translator.write(value);
                    translator.write("\n");
            }

            translator.write("\n \n");

            translator.close();
            System.out.println("Successfully wrote to translated file.");
        } catch (IOException e) {
            System.out.println("An error occurred while writing to file.");
            e.printStackTrace();
        }

    }


}
