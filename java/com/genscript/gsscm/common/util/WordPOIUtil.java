package com.genscript.gsscm.common.util;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
 
import org.apache.poi.poifs.filesystem.DirectoryEntry;
import org.apache.poi.poifs.filesystem.DocumentEntry;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;  


public class WordPOIUtil {
	public static boolean writeWordFile(String path, String content) {
		boolean w = false;
		try {
			byte b[] = content.getBytes("utf-8");
			ByteArrayInputStream bais = new ByteArrayInputStream(b);
			POIFSFileSystem fs = new POIFSFileSystem();
			DirectoryEntry directory = fs.getRoot();
			DocumentEntry de = directory.createDocument("WordDocument", bais);
			if (de != null) {
				FileOutputStream ostream = new FileOutputStream(path);
				fs.writeFilesystem(ostream);
				bais.close();
				ostream.close();
			}

		} catch (IOException e) {
			e.printStackTrace();
		}

		return w;
	}

	public static FileInputStream getFileInputStream(File file)
			throws FileNotFoundException {
		return new FileInputStream(file);
	}

	public static String readWordFileToString(String path) throws IOException {
		File file = new File(path); 
		StringBuffer sb = new StringBuffer();
		InputStream documentXMLIS = new FileInputStream(file);
		InputStreamReader reader = new InputStreamReader(documentXMLIS,
				"UTF-8");
		BufferedReader br = new BufferedReader(reader);

		String lineContent = null;
		while ((lineContent = br.readLine()) != null) {
			StringTokenizer st = new StringTokenizer(lineContent, " ");
			for (int t = 0; st.hasMoreElements(); t++) {
				String word = (String) st.nextElement();
				sb.append(word);
			}
		}
		br.close();

		return sb.toString(); 
	}  
}
