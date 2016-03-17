package toningword;

import java.io.BufferedReader;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class test4 {

	public static void main(String[] args) throws Exception {
		File f2 =  DataFolder.desktop("Truyen-Kieu.txt");
		BufferedReader fit1 = DataFolder.openReaderUtf8(f2);
		File f3 = DataFolder.desktop("TruyenKieu-toned.txt");
		BufferedReader fit2 = DataFolder.openReaderUtf8(f3);
		nh.test(f2,f3);
		
	}
}