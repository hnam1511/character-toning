/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package detone;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;
import java.io.File;
import java.io.FileWriter;
 

/**
 *
 * @author admin
 */
public class demo2 {
    private String s=null;
    private String s1=null;
    private HashMap loadmap;
    File f1 = new File("C:\\Users\\admin\\Desktop\\study\\1\\detoning\\character-detoning");
    private Map user = new HashMap();
    public void creatmap() throws FileNotFoundException{
        Scanner input = new Scanner(f1);
        s=input.next();
        while(input.hasNext()){
            s1=s;
            s=input.next();
            user.put(s1,s);
        }
    }
    public void loadmap(HashMap a){
        a.putAll(user);
    }
    
}