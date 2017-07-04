/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

/**
 *
 * @author Vinicios
 */
public class LeitorArquivo {
    
    public static String[] lerArquivo(File file) throws FileNotFoundException, IOException {
        Scanner scanner = new Scanner(file);
        
        String line;
        String[] elementosLidos = null;
        
        if(scanner.hasNextLine()) {
            line = scanner.nextLine();
            
            elementosLidos = line.split("-");
        }
        return elementosLidos;
    }
}
