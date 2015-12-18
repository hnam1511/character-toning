/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package demo3;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 *
 * @author admin
 */
public class Demo3 {

    /**
     * @param args the command line arguments
     */
    public static String detone(String toned, Map<Integer, Integer> lut) 
	{
		String res = "";
		
		for(int k=0; k<toned.length(); k++)
		{
			int ck = toned.charAt(k);
			
			if(lut.containsKey(ck)) res += (char)(int)lut.get(ck);
			else res += (char)ck;
		}
		
		return res;
	}
    public static Map<Integer, Integer> loadDetoneMap(File file) throws Exception
	{
		Map<Integer, Integer> lut = new TreeMap<Integer, Integer>();
                FileInputStream fis = null;
		fis = new FileInputStream(file);
		BufferedReader reader = null;
                reader = new BufferedReader(new InputStreamReader(fis));
		while(true)
		{
			String line = reader.readLine();
			if(line == null) break;
			String[] cells = line.replace("->", " ").split("\\s+[ ?!:;]+");
			if(cells.length < 0) continue;
			lut.put((int)cells[0].charAt(0), (int)cells[1].charAt(0));
		}
		
		fis.close();
		return lut;
	}

    public static void add(Map<String, List<String>> lut, String s, String t)
    {
        List<String> ls = lut.get(s);
        if(ls == null) {
            ls=new ArrayList<String>();
            lut.put(s, ls);
        }
        else if(!ls.contains(t)){
            ls.add(t);
        }
    }
    public static void print(Map<String, List<String>> lut,String s)
    {
        if(lut.containsKey(s)){
            List<String> t = lut.get(s);
        }
        for(String t: lut.get(s))
        System.out.println(s + " -> " + t); 
    }
    public static void main(String[] args) throws FileNotFoundException, Exception {
        File fis = null;
        fis = new File("C:\\Users\\admin\\Desktop\\study\\1\\aaa");
        Map lut=loadDetoneMap(fis);
        FileInputStream fit = null;
	fit = new FileInputStream("C:\\Users\\admin\\Desktop\\study\\1\\abc");
	BufferedReader reader = null;
        reader = new BufferedReader(new InputStreamReader(fit));
        Map<String, List<String>> detone = new TreeMap<String, List<String>>();
        while(true)
	{
            String line = reader.readLine();
            if(line == null) break;
            String[] cells = line.replace("->", " ").split("\\s+[ ?!:;]+");
            String z1,z2,z3;
            for(int i=0;i<cells.length;i++){
                String z=detone(cells[i],lut);
                add(detone,z,cells[i]);
                if (i<cells.length) 
                {
                    z1=cells[i].concat(cells[i+1]);
                    add(detone,z,z1);
                    if (i+1<cells.length)
                    {
                        z2=z1.concat(cells[i+2]);
                        add(detone,z,z2);
                        if (i+2<cells.length)
                        {
                            z3=z2.concat(cells[i+3]);
                            add(detone,z,z3);
                        }
                    }
                
                }
            }
	}
        BufferedReader dataIn = new BufferedReader(new
        InputStreamReader( System.in) );
        String word = "";
        try
        {
            word = dataIn.readLine();
        }
        catch( IOException e )
        {
            System.out.println("Error!");
        }
        if(detone.containsKey(word)){
            print(detone,word);
        }
    }
    
}
