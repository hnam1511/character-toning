package character;

import java.awt.List;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;
import java.util.TreeMap;



public class test1 {

	public static void main(String[] args) throws Exception{ 
		FileInputStream fis =new FileInputStream("C:\\Users\\admin\\workspace\\dec-18\\src\\character\\map-character-detoning");
		Map<Integer, Integer> lut=Nh.loadDetoneMap(fis);
		FileInputStream fit = new FileInputStream("C:\\Users\\admin\\workspace\\dec-18\\src\\character\\Truyen-Kieu");
    
		Map<String,List<String>> detone = Nh.creatMapDetone(fit,lut);
		for(String sk: detone.keySet()){
			System.out.println(sk+" -> "+ detone.get(sk));
		}
		
		BufferedReader dataIn = new BufferedReader(new InputStreamReader( System.in) );
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
		            Nh.print(detone,word);
		        }
	}

}
