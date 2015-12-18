/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package detoning;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;
import java.lang.String;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author admin
 */

public class Detoning extends demo2{

    /**
     * @param args the command line arguments
     */
    
    public static void main(String[] args) throws FileNotFoundException, IOException {
        HashMap user=new HashMap();
        demo2 a = new demo2();
        a.creatmap();
        user=a.loadmap();
        // TODO code application logic here
        char detoning;
        System.out.print("\t" + user);
        File f1 = new File("C:\\Users\\admin\\Desktop\\study\\1\\demo2\\demo2.txt");
        File f2 = new File("C:\\Users\\admin\\Desktop\\study\\1\\demo2\\timchu.txt");
        Scanner input = new Scanner(f1);
        FileWriter writer;
        writer = new FileWriter(f2);
        String s=null;
        String t=null;
        s=input.next();
        while(input.hasNext()){
        
            String res = "";
            for(int k=0; k<s.length(); k++) 
            {
                int sk = s.charAt(k);
                if( user.containsKey(sk)) res += (char)user.get(sk);
                else res += (char)sk;
                writer.write(res);
            }
            s=input.next();
        }
        input.close();
        writer.close();
        
    }
}

