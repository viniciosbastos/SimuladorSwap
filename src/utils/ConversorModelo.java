/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import modelo.Pagina;

/**
 *
 * @author Vinicios
 */
public class ConversorModelo {
    
    public static ArrayList<Pagina> convert(String[] operacoes) {
        ArrayList<Pagina> quadros = new ArrayList();
        String pagina;
        String num;
        
        Pattern pattern = Pattern.compile("(\\d+)|(W|R)");
        Matcher matcher;
        String[] matches = new String[2];
        int j;
        for (int i = 0; i < operacoes.length; i++) {
            matcher = pattern.matcher(operacoes[i]);                      
            j = 0;
            while (matcher.find()) {
                matches[j] = matcher.group();
                j++;
            }
            quadros.add(new Pagina(Integer.parseInt(matches[0]), matches[1].charAt(0)));
        }
        
        return quadros;
    }
}
