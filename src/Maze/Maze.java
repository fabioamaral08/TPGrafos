/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Maze;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 *
 * @author fabio
 */
public class Maze {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Lab lab = new Lab(20, 20);

        String res = lab.exibe();

        File arquivo2 = new File("Maze20.txt");//definindo o nome do locar do arquivo
        try {
            arquivo2.createNewFile();
            /*ESCREVER*/
            FileWriter fileW = new FileWriter(arquivo2);//arquivo para escrita
            BufferedWriter buffW = new BufferedWriter(fileW);

            buffW.write("1\r\n25\r\n" + res);

            buffW.close();
        } catch (IOException io) {
        }
    }

}
