package toningword;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.Random;

public class nh {

	
	public static Map<Integer, Integer> loadDetoneMap(BufferedReader reader) throws Exception
	{
		Map<Integer, Integer> lut = new TreeMap<Integer, Integer>();
        
		while(true)
		{
			String line = reader.readLine();
			if(line == null) break;
			String[] cells = line.replace("->", " ").split("\\s+[ ?!:;]+");
			if(cells.length < 0) continue;
			lut.put((int)cells[0].charAt(0), (int)cells[1].charAt(0));
		}
		
		return lut;
	}
	
	public static void add(Map<String, List<String>> lut, String s, String t)
    {
        List<String> ls = lut.get(s);
        if(ls == null) {
            ls=new ArrayList<String>();
            ls.add(t);
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
    

	public static Map<String, List<String>> creatMapDetone(BufferedReader fit,Map lut) throws IOException {
		Map<String, List<String>> res=new TreeMap<String, List<String>>();
		BufferedReader reader = new BufferedReader(new BufferedReader(fit));
		String line;
		List<String> lis =new ArrayList<String>();
		while(true)
		{
	            line = reader.readLine();
	            if(line == null) break;
	            if(!lis.isEmpty())lis.removeAll(lis);
	            for(String sk: line.split("[ ,.:;!?~']+"))
	            {
	            	lis.add(sk);
	            }
	            for(int i=0;i<lis.size();i++){
	            	String sa1 = lis.get(i);
	            	String sb1=detoning(sa1,lut);
	            	add(res,sb1,sa1);
	                if (i+1<lis.size()) 
	                {
	                    String sa2=lis.get(i+1);
	                    sa1=sa1.concat(" "+sa2);
	                    String sb2=detoning(sa2,lut);
	                    sb1=sb1.concat(" "+sb2);
	                    add(res,sb1,sa1);
	                    if (i+2<lis.size())
	                    {
	                        String sa3=lis.get(i+2);
	                        sa1=sa1.concat(" "+sa3);
		                    String sb3=detoning(sa3,lut);
		                    sb1=sb1.concat(" "+sb3);
		                    add(res,sb1,sa1);
	                        if (i+3<lis.size())
	                        {
	                        	String sa4=lis.get(i+3);
	                        	sa1=sa1.concat(" "+sa4);
			                    String sb4=detoning(sa4,lut);
			                    sb1=sb1.concat(" "+sb4);
			                    add(res,sb1,sa1);
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

	public static int similar(String A, String B, Map<String, List<String>> m) {
		boolean ak;
		int x=0;
		List<String> lis =new ArrayList<String>();
 		for(String tk: A.split("[ ,.:;!?~']+")){
        	lis.add(tk);                        	// list A: word need to toning
		}
        List<String> lut =new ArrayList<String>();
    	for(String xk: B.split("[ ,.:;!?~']+")){
            lut.add(xk);							// list B: word toned but need to compare
    	}
    	List<String> keyset = new ArrayList<String>();
    	double sk =Math.min(lis.size(), lut.size());
    	for(int i=0;i<sk;i++){
    		String s=lis.get(i);					// List A:	s
    		String t=lut.get(i);					// List B:	t
    		if(m.containsKey(s)){
    			keyset=m.get(s);ak=compare(t,keyset);
        		if (ak&&i+1<sk) 
                {
                    String sa2=lis.get(i+1);			// List A:	sa2
                    String s2=s.concat(" "+sa2);				
                    String ta2=lut.get(i+1);			// List B:	ta2
                    String t2=t.concat(" "+ta2);
                    if(m.containsKey(s2))
                    { 
                    	keyset=m.get(s2);
                        ak=compare(t2,keyset);
                        if(ak) x++;
                        if (ak&&i+2<sk)
                        {
                            String sa3=lis.get(i+2);		// List A:	sa3
                            String s3=s2.concat(" "+sa3);			
                            String ta3=lut.get(i+2);		// List B:	ta3
                            String t3=t2.concat(" "+ta3);
                            if(m.containsKey(s3)){
                            	keyset=m.get(s3);
                                ak=compare(t3,keyset);
                                if(ak) x++;
                        		if (ak&&i+3<sk)
                                {
                                	String sa4=lis.get(i+3);			// List A:	sa4
                                	String s4=s3.concat(" "+sa4);		//s3: 4 tu lien tiep
                                	String ta4=lut.get(i+3);			// List B:	ta4
                                	String t4=t3.concat(" "+ta4);		//t3: 4 tu lien tiep
                                	if(m.containsKey(s4)){
                                		keyset=m.get(s4);
                                        ak=compare(t4,keyset);
                                        if(ak) x++;
                                		
                                	}
                                	
                                }
                            }
                        }
                    }
                }
            }
    	}
		return x;
	}



private static  boolean compare(String t, List<String> keyset) {
	
	for(int ik=0;ik<keyset.size();ik++){
		if(keyset.get(ik).equals(t)) {
			return true;
		}
	}
	return false;
}

	public static void finaldetone(File f3, File f4, Map<String, List<String>> creatmapdetone) throws Exception {
		PrintWriter out = DataFolder.openWriterUtf8(f4);
		BufferedReader in = DataFolder.openReaderUtf8(f3);
		
		while(true)
		{
			//Khởi tạo Set toning và list size mới chính là các từ trong 1 dòng đọc vào của readline() để đánh dấu
			String s =in.readLine();
			if(s==null)break;
			String tone ="";
			List<String> sizeline = new ArrayList<String>();
			List<String> detone = new ArrayList<String>();
			for(String sk: s.split("[ ,.:;!?~']+")){
				sizeline.add(sk);
			}
			int size = sizeline.size();
			for(int i=0;i<size/4;i++){
				Set<String> finaldetoning=new TreeSet();
				String connect=new String();
				int maxsimilar=0;
				detone.removeAll(detone);
				for(int j=0;j<4;j++){
					String t = sizeline.get(4*i+j);
					detone.add(t);
					connect+=t;
					connect+=" ";
				}
				int t=-1;
				creat(detone,creatmapdetone,t,connect,finaldetoning);
				
				String z =new String();
				for(String sk:finaldetoning){
					int x=nh.similar(connect, sk, creatmapdetone);
					if(x>maxsimilar){
						maxsimilar=x;
						z=sk;
					}
				}
				tone+=z;
				if(size%4!=0&&i==size/4-1){
					i++;
					connect="";
					maxsimilar=0;
					int x=size/4;
					int x2=x*4;
					detone.removeAll(detone);
					finaldetoning.removeAll(finaldetoning);
					List<String> lis =new ArrayList<String>();
			 		for(String tk: tone.split("[ ,.:;!?~']+")){
			        	lis.add(tk);                        	// list A: word need to toning
					}
					for(int j=0;j<4;j++){
						if(size-4+j<x2) {lis.remove(size-4);}
						String xk = sizeline.get(size-4+j);
						detone.add(xk);
						connect+=xk;
						connect+=" ";
					}
					tone="";
					for(int j=0;j<lis.size();j++){
						tone+=lis.get(j);
						tone+=" ";
					}
					t=-1;
					creat(detone,creatmapdetone,t,connect,finaldetoning);
					String z2 =new String();
					for(String sk:finaldetoning){
						int x3=nh.similar(connect, sk, creatmapdetone);
						if(x3>maxsimilar){
							maxsimilar=x3;
							z2=sk;
						}
					}
					tone+=z2;
				}
			}
			System.out.println(tone);
			out.println(tone);
			
		}
		out.close();

	}

	private static void creat(List<String> detone, Map<String, List<String>> creatmapdetone, int t, String connect,Set<String>finaldetoning) {
		// TODO Auto-generated method stub
		t++;
		while(t<detone.size()&&!creatmapdetone.containsKey(detone.get(t))) t++;
		if(t<detone.size()){
			if(creatmapdetone.containsKey(detone.get(t)))

			{
				String z=detone.get(t);

				for(String sk: creatmapdetone.get(detone.get(t))) 
				{
//					String t1=connect.replaceFirst(z, sk);
					List<String> a=new ArrayList<String>();
					for(String tk: connect.split("[ ,.:;!?~']+")){
			        	a.add(tk);                        	// list A: word need to toning
					}
					a.remove(t);
					a.add(t, sk);
//					System.out.println(detone);
					String t1="";
					for(int s=0;s<a.size();s++) {
						t1+=a.get(s);
						t1+=" ";
					}
					int xk=t;
					creat(detone,creatmapdetone, xk,t1,finaldetoning);
				}
			}
			
		}
		if(t==detone.size()){
			finaldetoning.add(connect);
			t=-1;
		}
	}

	public static void test(File f2, File f3) throws Exception {
		// TODO Auto-generated method stub
		BufferedReader in = DataFolder.openReaderUtf8(f2);
		BufferedReader out = DataFolder.openReaderUtf8(f3);
		double x=0;
		String a=in.readLine();
		String b=out.readLine();
		double z=0;
		while(true){
			z++;
			a=in.readLine();
			b=out.readLine();
			if(a==null||b==null) break;
			int size=a.length();
			for(int i=0;i<size;i++){
				if(a.charAt(i)==',')a=a.replaceAll(",", " ");
			}
			for(int i=0;i<size;i++){
				if(a.charAt(i)=='.')a=a.replace('.', ' ');
			}
			for(int i=0;i<size;i++){
				if(a.charAt(i)==':')a=a.replaceAll(":", " ");
			}
			for(int i=0;i<size;i++){
				if(a.charAt(i)==';')a=a.replaceAll(";", " ");
			}
			for(int i=0;i<size;i++){
				if(a.charAt(i)=='?')a=a.replace('?',' ');
			}
			for(int i=0;i<size;i++){
				if(a.charAt(i)=='!')a=a.replaceAll("!", " ");
			}
			a=a.replaceAll(" ", "");
			b=b.replaceAll(" ", "");
			if(a.equals(b)) x++;
		}
		System.out.println (x/z);
	}
}
