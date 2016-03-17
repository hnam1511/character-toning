package toningword;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;



public class test1 {

	public static void main(String[] args) throws Exception{ 
		File f1 =  DataFolder.desktop("map-character-detoning.txt");
		BufferedReader fis = DataFolder.openReaderUtf8(f1);
		Map<Integer, Integer> lut = nh.loadDetoneMap(fis);	//map luc chuyển ký tự có dấu sang không dấu
		fis.close();
		File f2 =  DataFolder.desktop("Truyen-Kieu.txt");
		BufferedReader fit = DataFolder.openReaderUtf8(f2);
		Map<String,List<String>> creatmapdetone = nh.creatMapDetone(fit,lut);
		//map detone là map từ điển dữ liệu nguồn
		//map(string list<string>) map dữ liệ nguồn
		File f3 =  DataFolder.desktop("toning-Truyen-Kieu.txt");
		File f4 =  DataFolder.desktop("TruyenKieu-toned.txt");
		nh.finaldetone(f3,f4,creatmapdetone);		
		/*
		 * file f3 là file truyện kiều không dấu
		 * file f4 là file in ra truyện kiều đã qua chương trình thêm dấu tự động
		 * hàm finaldetone là hàm thêm dấu tự động
		 */
	}
}
