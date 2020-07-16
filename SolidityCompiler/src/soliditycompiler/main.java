/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package soliditycompiler;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
/**
 *
 * @author juand
 */
public class main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException, Exception {
        //String path = "D:/GitHub/Project-C/SolidityCompiler/src/soliditycompiler/scanner.flex";
        //String path = "C:/Users/U1/Documents/GitHub/Project-C/SolidityCompiler/src/soliditycompiler/scanner.flex";
        
        //STRINGS DE LAS RUTAS
        String pathPackage = "C:\\Users\\adria\\Documents\\OneDrive - Estudiantes ITCR\\I SEMESTRE 2020\\COMPI\\Proyecto\\Project-C\\SolidityCompiler\\src\\soliditycompiler\\";
        String pathJavaCup = "C:\\Users\\adria\\Documents\\OneDrive - Estudiantes ITCR\\I SEMESTRE 2020\\COMPI\\Proyecto\\Project-C\\SolidityCompiler\\";
        
        //pathSC es del scanner para cup, pathSF es del scanner normal de flex para mostrar el analisis lexico y pathP del parser
        String pathSC = pathPackage + "ScannerC.flex";
        String pathSF = pathPackage + "ScannerF.flex";
        String[] pathP = {"-parser","Parser", pathPackage + "parser.cup"};        
        generar(pathSF ,pathSC ,pathP, pathPackage, pathJavaCup); //llama generar con el path del scanner normal de flex, el del scanner flex para cup, el path de cup, el path del paquete y el path de donde deja los archivos el cup
    }
    /**
     * @param pathScannerF ruta del scanner con tokens.java normal de flex
     * @param pathScannerC ruta del scanner para cup
     * @param pathParser    ruta del parser
     * @param pathPackage   ruta del package
     * @param pathJavaCup   ruta de donde se generan los archivos del .cup
     * @throws IOException
     * @throws Exception 
     */
    public static void generar(String pathScannerF, String pathScannerC, String[] pathParser, String pathPackage, String pathJavaCup) throws IOException, Exception{
        File file = new File(pathScannerF);
        JFlex.Main.generate(file);      //genera el codigo del scaner en LexerF.java
        file = new File(pathScannerC);  
        JFlex.Main.generate(file);      //genera el codigo del scaner en LexerC.java
     
        java_cup.Main.main(pathParser); //genera el codigo del parser en sym.java (simbolos) y Sintax.java 
        //ESTOS ARCHIVOS SE GENERAN AFUERA DE src/SolidityCompiler por lo que hay que pasarlos a la carpeta del package con el siguiente codigo:
        
        //OPTIENE EL PATH QUE TENDRIA EL ARCHIVO sym.java DENTRO DEL PACKAGE
        Path pathSymbols = Paths.get(pathPackage + "sym.java");
        if (Files.exists(pathSymbols)) { 
            Files.delete(pathSymbols);  //SI YA EXISTE ESE PATH LO BORRA PARA QUE EL Files.move() PUEDA PEGAR EL ARCHIVO NUEVO DE SIMMBOLOS
        }
        Files.move(                 //MUEVE EL ARCHIVO
                Paths.get(pathJavaCup+"sym.java"), 
                Paths.get(pathPackage+"sym.java")
        );
        //OPTIENE EL PATH QUE TENDRIA EL ARCHIVO Sintax.java DENTRO DEL PACKAGE
        Path pathSintax = Paths.get(pathPackage+"Parser.java");
        if (Files.exists(pathSintax)) {
            Files.delete(pathSintax);  //SI YA EXISTE ESE PATH LO BORRA PARA QUE EL Files.move() PUEDA PEGAR EL ARCHIVO NUEVO DE SINTAXIS
        }
        Files.move(                 //MUEVE EL ARCHIVO
                Paths.get(pathJavaCup+"Parser.java"), 
                Paths.get(pathPackage+"Parser.java")
        );
        
    }
}