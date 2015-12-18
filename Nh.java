package character;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class Nh {

	
	public static Map<Integer, Integer> loadDetoneMap(FileInputStream file) throws Exception
	{
		Map<Integer, Integer> lut = new TreeMap<Integer, Integer>();
        
		BufferedReader reader = null;
        reader = new BufferedReader(new InputStreamReader(file));
		while(true)
		{
			String line = reader.readLine();
			if(line == null) break;
			String[] cells = line.replace("->", " ").split("\\s+[ ?!:;]+");
			if(cells.length < 0) continue;
			lut.put((int)cells[0].charAt(0), (int)cells[1].charAt(0));
		}
		
		file.close();
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
        
        for(String t: lut.get(s))
        System.out.println(s + " -> " + t); 
    }
    
    public static String res(String toned, Map<Integer, Integer> lut) 
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

	public static Map<String, List<String>> creatMapDetone(FileInputStream fit,Map lut) throws IOException {
		Map<String, List<String>> res=new TreeMap<String, List<String>>();
		BufferedReader reader = new BufferedReader(new InputStreamReader(fit));
		while(true)
		{
	            String line = reader.readLine();
	            if(line == null) break;
	            String[] cells = line.replace("->", " ").split("\\s+[ ?!:;]+");
	            String z1,z2,z3;
	            for(int i=0;i<cells.length;i++){
	                String z=detoning(cells[i],lut);
	                add(res,z,cells[i]);
	                if (i<cells.length) 
	                {
	                    z1=cells[i].concat(cells[i+1]);
	                    add(res,z,z1);
	                    if (i+1<cells.length)
	                    {
	                        z2=z1.concat(cells[i+2]);
	                        add(res,z,z2);
	                        if (i+2<cells.length)
	                        {
	                            z3=z2.concat(cells[i+3]);
	                            add(res,z,z3);
	                        }
	                    }
	                
	                }
	            }
		}
		return res;
	}
	
	public static String detoning(String toned, Map<Integer, Integer> lut) 
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
}
