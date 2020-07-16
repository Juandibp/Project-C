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
        
        //pathS es del scanner y pathP del parser
        String pathS = "C:\\Users\\adria\\Documents\\OneDrive - Estudiantes ITCR\\I SEMESTRE 2020\\COMPI\\Proyecto\\Project-C\\SolidityCompiler\\src\\soliditycompiler\\scanner.flex";
        String[] pathP = {"-parser","Parser","C:\\Users\\adria\\Documents\\OneDrive - Estudiantes ITCR\\I SEMESTRE 2020\\COMPI\\Proyecto\\Project-C\\SolidityCompiler\\src\\soliditycompiler\\parser.cup"};        
        generar(pathS,pathP);
    }
    public static void generar(String pathScanner, String[] pathParser) throws IOException, Exception{
        File file = new File(pathScanner);  
        JFlex.Main.generate(file);      //genera el codigo del scaner en Lexer.java
        java_cup.Main.main(pathParser); //genera el codigo del parser en sym.java (simbolos) y Sintax.java 
        //ESTOS ARCHIVOS SE GENERAN AFUERA DE src/SolidityCompiler por lo que hay que pasarlos a la carpeta del package con el siguiente codigo:
        
        //OPTIENE EL PATH QUE TENDRIA EL ARCHIVO sym.java DENTRO DEL PACKAGE
        Path pathSymbols = Paths.get("C:\\Users\\adria\\Documents\\OneDrive - Estudiantes ITCR\\I SEMESTRE 2020\\COMPI\\Proyecto\\Project-C\\SolidityCompiler\\src\\soliditycompiler\\sym.java");
        if (Files.exists(pathSymbols)) { 
            Files.delete(pathSymbols);  //SI YA EXISTE ESE PATH LO BORRA PARA QUE EL Files.move() PUEDA PEGAR EL ARCHIVO NUEVO DE SIMMBOLOS
        }
        Files.move(                 //MUEVE EL ARCHIVO
                Paths.get("C:\\Users\\adria\\Documents\\OneDrive - Estudiantes ITCR\\I SEMESTRE 2020\\COMPI\\Proyecto\\Project-C\\SolidityCompiler\\sym.java"), 
                Paths.get("C:\\Users\\adria\\Documents\\OneDrive - Estudiantes ITCR\\I SEMESTRE 2020\\COMPI\\Proyecto\\Project-C\\SolidityCompiler\\src\\soliditycompiler\\sym.java")
        );
        //OPTIENE EL PATH QUE TENDRIA EL ARCHIVO Sintax.java DENTRO DEL PACKAGE
        Path pathSintax = Paths.get("C:\\Users\\adria\\Documents\\OneDrive - Estudiantes ITCR\\I SEMESTRE 2020\\COMPI\\Proyecto\\Project-C\\SolidityCompiler\\src\\soliditycompiler\\Parser.java");
        if (Files.exists(pathSintax)) {
            Files.delete(pathSintax);  //SI YA EXISTE ESE PATH LO BORRA PARA QUE EL Files.move() PUEDA PEGAR EL ARCHIVO NUEVO DE SINTAXIS
        }
        Files.move(                 //MUEVE EL ARCHIVO
                Paths.get("C:\\Users\\adria\\Documents\\OneDrive - Estudiantes ITCR\\I SEMESTRE 2020\\COMPI\\Proyecto\\Project-C\\SolidityCompiler\\Parser.java"), 
                Paths.get("C:\\Users\\adria\\Documents\\OneDrive - Estudiantes ITCR\\I SEMESTRE 2020\\COMPI\\Proyecto\\Project-C\\SolidityCompiler\\src\\soliditycompiler\\Parser.java")
        );
        
    }
}