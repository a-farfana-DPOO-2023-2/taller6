package model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class InputManager {
    private static final BufferedReader reader = new BufferedReader(new BufferedReader(new InputStreamReader(System.in)));

    public static String input(String message){
        System.out.print(message+": ");

        try{
            return reader.readLine();
        } catch (IOException e) {
            System.out.println("An error occurred in the console.");
            e.printStackTrace();
            return null;
        }
    }

    public void close(){
        try{
            reader.close();
        }catch (IOException e){
            e.printStackTrace();
        }
    }

}
