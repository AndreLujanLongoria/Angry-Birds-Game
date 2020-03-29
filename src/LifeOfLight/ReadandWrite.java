/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LifeOfLight;

import java.io.BufferedReader;
import java.io.PrintWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;


/**
 *
 * @author antoniomejorado
 */
public class ReadandWrite {
    
    //Add 
    
    public static void Save(String strFileName, int lifes, int points) {

        try {
            PrintWriter writer = new PrintWriter(new FileWriter(strFileName));
            int vidas = lifes;
            int score = points;
            writer.println("" + vidas + "/" + score);
            writer.close();
            System.out.println("Game Saved");
        } catch (IOException ioe) {
            System.out.println("File Not found CALL 911");
        }
    }

    public static void Load(String strFileName, int[] data) {
        try {
            FileReader file = new FileReader(strFileName);
            BufferedReader reader = new BufferedReader(file);
            String line;
            String datos[];
            line = reader.readLine();
            datos = line.split("/");
            int vidas = Integer.parseInt(datos[0]);
            int score = Integer.parseInt(datos[1]);
            data[0] = vidas;
            data[1] = score;
            System.out.println("Se leyo  vidas = "+ vidas + " y score = " + score);
            reader.close();
        } catch (IOException e) {
            System.out.println("File Not found CALL 911");
        }
    }

}
