
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

import static TraduccionSemantica.TIPO_DO.*;


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
    public static RS_OPERADOR op;
    public static LinkedList<LinkedList <LinkedList <String>>> contenidoArchivo=new LinkedList<>();
    public static LinkedList <LinkedList <String>> codigo=new LinkedList<>();
    
    public static void reiniciarVariables(){
        tablaSimbolos=new HashMap<>();
        type="";
        error=null;
        pilaSemantica.clear();
        cantIf=0;
        cantWhile=0;
        op=null;
        contenidoArchivo=new LinkedList<>();
        codigo=new LinkedList<>();
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
        RS tope=pilaSemantica.pop();//VAlor
        pilaSemantica.pop();//=
        pilaSemantica.pop();//Type
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
        contenidoArchivo.add(codigo);
    }

    
    public static void pasarSimbolosAArchivo(){
        for (String simbolo: tablaSimbolos.keySet()){
            LinkedList<String> lista=getCarSimbolo(simbolo);
            if(lista.size()>2){
                //Esta inicializada
                LinkedList<String> nuevaLista=new LinkedList<>();
                nuevaLista.add(lista.get(0));
                nuevaLista.add(simbolo);
                nuevaLista.add(lista.get(2));
                contenidoArchivo.get(0).add(nuevaLista);
            }else{
                //No esta inicializada
                LinkedList<String> nuevaLista=new LinkedList<>();
                nuevaLista.add(lista.get(0));
                nuevaLista.add(simbolo);
                contenidoArchivo.get(1).add(nuevaLista);
            }
        }
    }
    
    public static String obtenerValorSimbolo(String simbolo){
        LinkedList<String> lista=getCarSimbolo(simbolo);
        if(lista.size()>2){
            return lista.get(2);
        }
        return "";
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
                RS topeS=pilaSemantica.pop();
                if(topeS instanceof RS_OPERADOR){
                    RS_OPERADOR convertS = (RS_OPERADOR)topeS;
                    if(convertS.toString().equals("=")){
                        String tipo=getCarSimbolo(convert.getNombre()).get(0);
                        //validarAssignment(linea,(tope);
                    }else{
                        if(convertS.toString().equals("+") || convertS.toString().equals("-")){
                            pilaSemantica.push(topeS);
                            pilaSemantica.push(tope);
                            evalBinaryAritmetico();
                            return true;
                        }else{
                            pilaSemantica.push(topeS);
                            pilaSemantica.push(tope);
                            evalBinaryBooleano();
                            return true;
                        }
                    }
                }
                //String tipo=(getCarSimbolo(convert.getNombre())).getFirst();
                //return limpiarPila(linea,tipo);
            }
        }
        if(tope instanceof RS_DO){
            RS_DO convert=(RS_DO)tope;
            RS topeS=pilaSemantica.pop();
            if(topeS instanceof RS_OPERADOR){
                RS_OPERADOR convertS = (RS_OPERADOR)topeS;
                if(convertS.toString().equals("=")){
                    validarAssignment(linea,convert);
                }else{
                    if(convertS.toString().equals("+") || convertS.toString().equals("-")){
                        pilaSemantica.push(topeS);
                        pilaSemantica.push(tope);
                        evalBinaryAritmetico();
                        return true;
                    }else{
                        pilaSemantica.push(topeS);
                        pilaSemantica.push(tope);
                        evalBinaryBooleano();
                        return true;
                    }
                }
            }
        }
        if(tope instanceof RS_OPERADOR){
            if(error==null){
                error="No se ha cerrado la operacion";
            }
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
            if(topeS instanceof  RS_FUNCION){
                RS_FUNCION convert=(RS_FUNCION) topeS;
                if(convert.returns){
                    return true;
                }
                else{
                    
                }
            }else{
                pilaSemantica.push(topeS);
                RS funcion=getLastFuncion();
                if(funcion==null){
                    if(error==null){
                        error="Return fuera de función.\nLinea: "+linea;
                    }
                    return false;
                }
                RS_FUNCION convert=(RS_FUNCION) funcion;
                if(convert.returns){
                    return true;
                }else{
                    if(error==null){
                        error="Return en función sin return.\nLinea: "+linea;
                    }
                    return false;
                }
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
        if(tope instanceof RS_TIPO){
            
        }
        return true;
    }
    
    public static boolean validarAssignment(int linea, RS_DO rsdo1){
        if(pilaSemantica.isEmpty()){
            return false;
        }
        RS tope = pilaSemantica.pop();
        /*if(tope instanceof RS_ID){
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
        }*/
        
        if(tope instanceof RS_DO){
            RS_DO convert=(RS_DO)tope;
            if(!existsSimbolo(convert.getValor())){
                if(error==null){
                    error="Simbolo no definido: "+convert.getValor()+"\nLinea: "+linea;
                }
                return false;
            }else{
                String tipoN=(getCarSimbolo(convert.getValor())).getFirst();
                if(rsdo1.getTipo()==TIPO_DO.CONST){
                    if(rsdo1.getValor().startsWith("\"")){
                        if(tipoN.equals("string")){
                            LinkedList<String> instruccion = new LinkedList<>();
                            String jmpins = "mov " + convert.getValor() + ", "  + rsdo1.getValor();
                            instruccion.add(jmpins);
                            codigo.add(instruccion);
                            return true;
                        }
                        return false;
                    }else{
                        if(tipoN.equals("int")){
                            LinkedList<String> instruccion = new LinkedList<>();
                            String jmpins = "mov " + convert.getValor() + ", "  + rsdo1.getValor();
                            instruccion.add(jmpins);
                            codigo.add(instruccion);
                            return true;
                        }
                        return false;
                    }
                }else{
                    String tipo =(getCarSimbolo(rsdo1.getValor())).getFirst();
                    if(!tipo.equals(tipoN)){
                        if(error==null){
                            error="Simbolo no compatible: "+convert.getValor()+"\nLinea: "+linea;
                        }
                        return false;
                    }
                    //GENERAR CODIGO
                    LinkedList<String> instruccion = new LinkedList<>();
                    String jmpins = "mov " + convert.getValor() + ", "  + rsdo1.getValor();
                    instruccion.add(jmpins);
                    codigo.add(instruccion);
                    return true;
                }
            }
        }else{
            if(error==null){
                error="Operacion no valida\nLinea: "+linea;
            }
            return false;
            
        }
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
    
    private static RS getLastFuncion(){
        if (pilaSemantica.isEmpty()){
            return null;
        }
        else{
            for (RS registroSemantico : pilaSemantica){
                if(registroSemantico instanceof RS_FUNCION){
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
            codigo.add(instruccion);
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
            codigo.add(instruccion);
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
            pilaSemantica.push(nuevo);
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
    
    public static void accionFuncion(boolean pReturns){
        RS_FUNCION nuevo=new RS_FUNCION(pReturns);
        pilaSemantica.push(nuevo);

    }

    public static void accionReturns(){
        System.out.println("RETURNS");
        System.out.println(pilaSemantica);
        RS_DO id=(RS_DO)pilaSemantica.pop();
        System.out.println(pilaSemantica);
        RS_RETURNS nuevo=new RS_RETURNS();
        LinkedList<String> instruccion3 = new LinkedList<>();
        String codeReturn = "push "+id.getValor();
        instruccion3.add(codeReturn);
        codigo.add(instruccion3);
        pilaSemantica.push(nuevo);
    }
    
    public static void evalBinaryAritmetico(){
        
        RS_DO rsdo2 = (RS_DO) pilaSemantica.pop();
        op = (RS_OPERADOR) pilaSemantica.pop();
        RS_DO rsdo1 = (RS_DO) pilaSemantica.pop();
        RS_DO newrsdo = null;
        RS topeS=null;
        int resultado;
        LinkedList<String> instruccion;
        String cmpins;
        if(rsdo1.getTipo() == TIPO_DO.CONST && rsdo2.getTipo() ==TIPO_DO.CONST){
            System.out.println("CONST-CONST");
            switch (op.getOperador()) {
                case "+":
                    resultado = Integer.parseInt(rsdo1.getValor()) + Integer.parseInt(rsdo2.getValor());
                    newrsdo = new RS_DO(String.valueOf(resultado));
                    newrsdo.setConst();
                    topeS=pilaSemantica.pop();
                    if(topeS instanceof RS_OPERADOR){
                        RS_OPERADOR convertS = (RS_OPERADOR)topeS;
                        if(convertS.toString().equals("=")){
                            validarAssignment(0,newrsdo);
                        }else{
                            if(convertS.toString().equals("+") || convertS.toString().equals("-")){
                                pilaSemantica.push(topeS);
                                pilaSemantica.push(newrsdo);
                                evalBinaryAritmetico();
                            }else{
                                pilaSemantica.push(topeS);
                                pilaSemantica.push(newrsdo);
                                evalBinaryBooleano();
                            }
                        }
                        break;
                    }else{
                        pilaSemantica.push(topeS);
                        break;
                    }
                    
                case "-":
                    resultado = Integer.parseInt(rsdo1.getValor()) - Integer.parseInt(rsdo2.getValor());
                    newrsdo = new RS_DO(String.valueOf(resultado));
                    newrsdo.setConst();
                    topeS=pilaSemantica.pop();
                    if(topeS instanceof RS_OPERADOR){
                        RS_OPERADOR convertS = (RS_OPERADOR)topeS;
                        if(convertS.toString().equals("=")){
                            validarAssignment(0,newrsdo);
                        }else{
                            if(convertS.toString().equals("+") || convertS.toString().equals("-")){
                                pilaSemantica.push(topeS);
                                pilaSemantica.push(newrsdo);
                                evalBinaryAritmetico();
                            }else{
                                pilaSemantica.push(topeS);
                                pilaSemantica.push(newrsdo);
                                evalBinaryBooleano();
                            }
                        }
                        break;
                    }else{
                        pilaSemantica.push(topeS);
                        break;
                    }
                default:
                    break;
            }
            
        } 
        else{
            if(rsdo1.getTipo() == TIPO_DO.DIR && rsdo2.getTipo()==TIPO_DO.CONST){
                System.out.println("DIR-CONST");
                if(existsSimbolo(rsdo1.getValor())){
                    //Falta obtener valor de rsdo1 de tabla de simbolos
                    switch (op.getOperador()) {
                        case "+":
                            instruccion = new LinkedList<>();
                            cmpins = "mov eax," + obtenerValorSimbolo(rsdo1.getValor())+ '\n' + "mov ebx," + rsdo2.getValor()+ '\n' + "add eax, ebx\n";
                            instruccion.add(cmpins);
                            codigo.add(instruccion);
                            // //ojo que no sabemos si es por referencia
                            topeS=pilaSemantica.pop();
                            if(topeS instanceof RS_OPERADOR){
                                RS_OPERADOR convertS = (RS_OPERADOR)topeS;
                                if(convertS.toString().equals("=")){
                                    String tipo=getCarSimbolo(rsdo1.getValor()).get(0);
                                    validarAssignment(0,rsdo1);
                                }else{
                                    if(convertS.toString().equals("+") || convertS.toString().equals("-")){
                                        pilaSemantica.push(topeS);
                                        pilaSemantica.push(rsdo1);
                                        evalBinaryAritmetico();
                                    }else{
                                        pilaSemantica.push(topeS);
                                        pilaSemantica.push(rsdo1);
                                        evalBinaryBooleano();
                                    }
                                }
                                break;
                            }else{
                                pilaSemantica.push(topeS);
                                break;
                            }
                        case "-":
                            instruccion = new LinkedList<>();
                            cmpins = "mov eax," + obtenerValorSimbolo(rsdo1.getValor())+ '\n' + "mov ebx," + rsdo2.getValor()+ '\n' + "sub eax, ebx\n";
                            instruccion.add(cmpins);
                            codigo.add(instruccion);
                            // //ojo que no sabemos si es por referencia
                            topeS=pilaSemantica.pop();
                            if(topeS instanceof RS_OPERADOR){
                                RS_OPERADOR convertS = (RS_OPERADOR)topeS;
                                if(convertS.toString().equals("=")){
                                    validarAssignment(0,rsdo1);
                                }else{
                                    if(convertS.toString().equals("+") || convertS.toString().equals("-")){
                                        pilaSemantica.push(topeS);
                                        pilaSemantica.push(rsdo1);
                                        evalBinaryAritmetico();
                                    }else{
                                        pilaSemantica.push(topeS);
                                        pilaSemantica.push(rsdo1);
                                        evalBinaryBooleano();
                                    }
                                }
                                break;
                            }else{
                                pilaSemantica.push(topeS);
                                break;
                            }
                        default:
                            break;
                    }
                }
                else{
                    if(error==null){
                        error= rsdo1.getValor() + ": variable no definida"; //falta linea
                    }
                }
            }else{
                System.out.println("CONST-DIR AUN NO HACE RECURSIVA");
                if(rsdo1.getTipo() == TIPO_DO.CONST && rsdo2.getTipo()==TIPO_DO.DIR){
                    if(existsSimbolo(rsdo2.getValor())){
                    //Falta obtener valor de rsdo2 de tabla de simbolos
                        switch (op.getOperador()) {
                            case "+":
                                instruccion = new LinkedList<>();
                                cmpins = "mov eax," + rsdo1.getValor()+ '\n' + "mov ebx," + obtenerValorSimbolo(rsdo2.getValor())+ '\n' + "add eax, ebx\n";
                                instruccion.add(cmpins);
                                codigo.add(instruccion);
                                // //ojo que no sabemos si es por referencia
                                break;
                            case "-":
                                instruccion = new LinkedList<>();
                                cmpins = "mov eax," + rsdo1.getValor()+ '\n' + "mov ebx," + obtenerValorSimbolo(rsdo2.getValor())+ '\n' + "sub eax, ebx\n";
                                instruccion.add(cmpins);
                                codigo.add(instruccion);
                                // //ojo que no sabemos si es por referencia
                                break;                    
                            default:
                                break;
                        }
                    }else{
                        if(error==null){
                            error= rsdo2.getValor() + ": variable no definida"; //falta linea
                        }
                    }
                }else{
                    System.out.println("DIR-DIR");
                    if(existsSimbolo(rsdo1.getValor()) && existsSimbolo(rsdo2.getValor())){
                    //Falta obtener valor de rsfo1 y rsdo2 de tabla de simbolos
                        switch (op.getOperador()) {
                            case "+":
                                instruccion = new LinkedList<>();
                                cmpins = "mov eax," + obtenerValorSimbolo(rsdo1.getValor())+ '\n' + "mov ebx," + obtenerValorSimbolo(rsdo2.getValor())+ '\n' + "add eax, ebx\n";
                                instruccion.add(cmpins);
                                codigo.add(instruccion);
                                // //ojo que no sabemos si es por referencia
                                topeS=pilaSemantica.pop();
                                if(topeS instanceof RS_OPERADOR){
                                    RS_OPERADOR convertS = (RS_OPERADOR)topeS;
                                    if(convertS.toString().equals("=")){
                                        validarAssignment(0,rsdo1);
                                    }else{
                                        if(convertS.toString().equals("+") || convertS.toString().equals("-")){
                                            pilaSemantica.push(topeS);
                                            pilaSemantica.push(rsdo1);
                                            evalBinaryAritmetico();
                                        }else{
                                            pilaSemantica.push(topeS);
                                            pilaSemantica.push(rsdo1);
                                            evalBinaryBooleano();
                                        }
                                    }
                                    break;
                                }else{
                                    pilaSemantica.push(topeS);
                                    break;
                                }
                            case "-":
                                instruccion = new LinkedList<>();
                                cmpins = "mov eax," +obtenerValorSimbolo(rsdo1.getValor())+ '\n' + "mov ebx," + obtenerValorSimbolo(rsdo2.getValor())+ '\n' + "sub eax, ebx\n";
                                instruccion.add(cmpins);
                                codigo.add(instruccion);
                                // //ojo que no sabemos si es por referencia
                                topeS=pilaSemantica.pop();
                                if(topeS instanceof RS_OPERADOR){
                                    RS_OPERADOR convertS = (RS_OPERADOR)topeS;
                                    if(convertS.toString().equals("=")){
                                        validarAssignment(0,rsdo1);
                                    }else{
                                        if(convertS.toString().equals("+") || convertS.toString().equals("-")){
                                            pilaSemantica.push(topeS);
                                            pilaSemantica.push(rsdo1);
                                            evalBinaryAritmetico();
                                        }else{
                                            pilaSemantica.push(topeS);
                                            pilaSemantica.push(rsdo1);
                                            evalBinaryBooleano();
                                        }
                                    }
                                    break;
                                }else{
                                    pilaSemantica.push(topeS);
                                    break;
                                }
                            default:
                                break;
                        }
                    }else{
                        if(error==null){
                            error= rsdo1.getValor() + " y " +rsdo2.getValor() + ": variables no definida"; //falta linea
                        }
                    }
                }
            }
        }
    }
    
    public static void evalBinaryBooleano(){
        RS_DO rsdo2 = (RS_DO) pilaSemantica.pop();
        op = (RS_OPERADOR) pilaSemantica.pop();
        RS_DO rsdo1 = (RS_DO) pilaSemantica.pop();

        LinkedList<String> instruccion = new LinkedList<>();
        String cmpins = "cmp " + rsdo1.getValor()+ ", " + rsdo2.getValor();
        instruccion.add(cmpins);
        codigo.add(instruccion);
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
            RS tope=pilaSemantica.pop();
            if(tope instanceof RS_DO){
                pilaSemantica.push(tope);
            }
            //System.out.println(tablaSimbolos);
        }else{
            if(error==null){
                error="Simbolo doble definido: "+Id+"\nLinea: "+(linea+1);
            }
        }
    }
    
    public static void accionStartIf(){
        String exitLabel="exitLabelIf"+cantIf;
        String elseLabel="elseLabel"+cantIf;
        RS_IF nuevo=new RS_IF(exitLabel,elseLabel);
        pilaSemantica.push(nuevo);
    }
    
    public static void accionTestIf(){
        //Codigo para evaluar el condicional
        String jumptype = "";
        switch (op.getOperador()) {
            case ">":
                
                jumptype = "jg";
                break;
            case "<":
                
                jumptype = "jl";
                break;
            case ">=":
                
                jumptype = "jge";
                break;
            case "<=":
                
                jumptype = "jle";
                break;
            case "==":
                
                jumptype = "je";
                break;
            case "!=":
                
                jumptype = "jne";
                break;
        
            default:
                break;
        }
        LinkedList<String> instruccion = new LinkedList<>();
        String jmpins = jumptype + " " + ((RS_IF) getLastIf()).getElse_label();
        instruccion.add(jmpins);
        codigo.add(instruccion);
    }
    
    public static void accionStartElse(){
        LinkedList<String> instruccion2 = new LinkedList<>();
        RS_IF rsIf = (RS_IF) getLastIf();
        String ifElseLabel = rsIf.getElse_label();
        instruccion2.add(ifElseLabel + ":");
        codigo.add(instruccion2);
    }
    
    public static void accionEndIf2(){
        RS_IF rsIf = (RS_IF) getLastIf();
        LinkedList<String> instruccion = new LinkedList<>();
        //LinkedList<String> instruccion2 = new LinkedList<>();
        LinkedList<String> instruccion3 = new LinkedList<>();
        String ifExitLabel = rsIf.getExit_label();
        instruccion3.add(ifExitLabel + ":");
        codigo.add(instruccion3);
        pilaSemantica.pop();
    }
    
    public static void accionEndIf1(){
        RS_IF rsIf = (RS_IF) getLastIf();
        LinkedList<String> instruccion = new LinkedList<>();
        String ifExitLabel = rsIf.getExit_label();
        String jmpExit="jmp "+ifExitLabel;
        instruccion.add(jmpExit);
        codigo.add(instruccion);
    }
    
    public static void accionStartWhile(){
        System.out.println("EMPIEZA WHILE");
        String whileLabel="whileLabel"+cantWhile;
        String exitLabel="exitLabelWhile"+cantWhile;
        RS_WHILE nuevo=new RS_WHILE(whileLabel,exitLabel);
        pilaSemantica.push(nuevo);
        System.out.println("PUSH WHILE");
    }
    
    public static void accionTestWhile(){
        //Codigo de la evaluacion del while
         //Codigo para evaluar el condicional
        System.out.println("EMPIEZA TEST WHILE");
         System.out.println("POP TEST WHILE");
         String jumptype = "";
         switch (op.getOperador()) {
             case ">":
                 
                 jumptype = "JG";
                 break;
             case "<":
                 
                 jumptype = "JL";
                 break;
             case ">=":
                 
                 jumptype = "JGE";
                 break;
             case "<=":
                 
                 jumptype = "JLE";
                 break;
             case "==":
                 
                 jumptype = "JE";
                 break;
             case "!=":
                 
                 jumptype = "JNE";
                 break;
         
             default:
                 break;
         }
         LinkedList<String> instruccion = new LinkedList<>();
         String jmpins = jumptype + " " + ((RS_WHILE) getLastWhile()).getExit_label();
         instruccion.add(jmpins);
         codigo.add(instruccion);
         System.out.println("TERMINA TEST WHILE");
    }
    
    public static void accionEndWhile(){
        //Jump while
        System.out.println("EMPIEZA END WHILE");
        LinkedList<String> instruccion = new LinkedList<>();
        LinkedList<String> instruccion2 = new LinkedList<>();
        String jmpins = "jmp " + ((RS_WHILE) getLastWhile()).getWhile_label();
        instruccion.add(jmpins);
        codigo.add(instruccion);
        
        jmpins = ((RS_WHILE) getLastWhile()).getExit_label() + ":";
        instruccion2.add(jmpins);
        codigo.add(instruccion2);
        
        
        //label exit
        pilaSemantica.pop();
        System.out.println("TERMINA END WHILE");
    }


    public static void translateToNasm(){
        //String pathPackage = "D:\\GitHub\\Project-C\\";
        //ARIEL:
        String pathPackage = "C:\\Users\\Ariel\\Documents\\GitHub\\Project-C\\";
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
                if("int".equals(node.get(0))){
                    String value = node.get(1)+":\t dd\t" + node.get(2);
                    
                    System.out.println(value);
                    translator.write(value);
                    translator.write("\n");
                }
                if("string".equals(node.get(0))){
                    String value = node.get(1)+":\t db\t" + node.get(2)+ ",0";
                    System.out.println(value);
                    translator.write(value);
                    translator.write("\n");
                }
            }
            
            translator.write("\n \n");
            translator.write("segment .bss\n \n");

            //Write variables sin inicializar
            for(LinkedList<String> node : contenidoArchivo.get(1)){
                if("int".equals(node.get(0))){
                    String value = node.get(1)+":\t resd\t 1";
                    
                    System.out.println(value);
                    translator.write(value);
                    translator.write("\n");
                }
                if("string".equals(node.get(0))){
                    String value = node.get(1)+":\t resb\t 255";
                    
                    System.out.println(value);
                    translator.write(value);
                    translator.write("\n");
                }

            }

            translator.write("\n \n");
            translator.write("segment .text\n\n");
            translator.write("global asm_main\n");
            translator.write("asm_main:\n");
            translator.write("enter\t 0,0\n");
            translator.write("pusha\n\n");
            translator.write(";;Empieza codigo generado\n");
            //Write Code
            for(LinkedList<String> node : contenidoArchivo.get(2)){
                String value = node.get(0);
                    
                    System.out.println(value);
                    translator.write(value);
                    translator.write("\n");
            }

            translator.write(";;Termina codigo generado");
            translator.write("\n \n");
            translator.write("popa\n");
            translator.write("mov\t eax,0\n");
            translator.write("leave\n");
            translator.write("ret");
            translator.close();
            System.out.println("Successfully wrote to translated file.");
        } catch (IOException e) {
            System.out.println("An error occurred while writing to file.");
            e.printStackTrace();
        }

    }


}
