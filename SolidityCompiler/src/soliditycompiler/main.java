/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package soliditycompiler;

import java.io.File;

/**
 *
 * @author juand
 */
public class main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        String path = "D:/GitHub/Project-C/SolidityCompiler/src/soliditycompiler/scanner.flex";
        generarLexer(path);
    }
    public static void generarLexer(String path){
        File file = new File(path);
        JFlex.Main.generate(file);
    }
    
}
