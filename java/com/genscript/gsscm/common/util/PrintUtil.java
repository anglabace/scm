package com.genscript.gsscm.common.util;

import java.awt.Desktop;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.net.*;

import javax.activation.MimetypesFileTypeMap;
import javax.print.Doc;
import javax.print.DocFlavor;
import javax.print.DocPrintJob;
import javax.print.PrintException;
import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.print.SimpleDoc;
import javax.print.DocFlavor.INPUT_STREAM;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.print.attribute.standard.Copies;



public class PrintUtil {
	
	public static String printPanel(String fileName) throws PrintException,
			IOException {
		INPUT_STREAM input_stream = DocFlavor.INPUT_STREAM.AUTOSENSE;
		// String fileName= "e:\\a.html";
		String contentType = "";
		if (fileName != null) {
			String http = fileName.substring(0, 4);
			if (http.equals("http")) {

				URL u = new URL(fileName);
				URLConnection uc = null;
				uc = u.openConnection();
				if (uc == null) {
					return "nonFile";// 文件不存在
				}
				contentType = uc.getContentType();

			} else {
				File file = new File(fileName);
				if (file != null) {
					contentType = new MimetypesFileTypeMap().getContentType(file);
				} else {
					return "nonFile";// 文件不存在
				}
			}
			
		} else {
			return "nonFile";// 文件不存在
		}

		
		if (contentType != null&&!contentType.equals("")) {
			    
				if (contentType.equals("image/gif")) {
					input_stream = DocFlavor.INPUT_STREAM.GIF;
				} else if (contentType.equals("image/jpeg")) {
					input_stream = DocFlavor.INPUT_STREAM.JPEG;
				} else if (contentType.equals("application/vnd.hp-PCL")) {
					input_stream = DocFlavor.INPUT_STREAM.PCL;
				} else if (contentType.equals("application/pdf")) {
					input_stream = DocFlavor.INPUT_STREAM.PDF;
				} else if (contentType.equals("image/png")) {
					input_stream = DocFlavor.INPUT_STREAM.PNG;
				} else if (contentType.equals("application/postscript")) {
					input_stream = DocFlavor.INPUT_STREAM.POSTSCRIPT;
				} else if (contentType.equals("text/html")) {
					input_stream = DocFlavor.INPUT_STREAM.TEXT_HTML_HOST;
				} else if (contentType.equals("text/html; charset=us-ascii")) {
					input_stream = DocFlavor.INPUT_STREAM.TEXT_HTML_US_ASCII;
				} else if (contentType.equals("text/html; charset=utf-16")) {
					input_stream = DocFlavor.INPUT_STREAM.TEXT_HTML_UTF_16;
				} else if (contentType.equals("text/html; charset=utf-16be")) {
					input_stream = DocFlavor.INPUT_STREAM.TEXT_HTML_UTF_16BE;
				} else if (contentType.equals("text/html; charset=utf-16le")) {
					input_stream = DocFlavor.INPUT_STREAM.TEXT_HTML_UTF_16LE;
				} else if (contentType.equals("text/html; charset=utf-8")) {
					input_stream = DocFlavor.INPUT_STREAM.TEXT_HTML_UTF_8;
				} else if (contentType.equals("text/plain")) {
					input_stream = DocFlavor.INPUT_STREAM.TEXT_PLAIN_HOST;
				} else if (contentType.equals("text/plain; charset=us-ascii")) {
					input_stream = DocFlavor.INPUT_STREAM.TEXT_PLAIN_US_ASCII;
				} else if (contentType.equals("text/plain; charset=utf-16")) {
					input_stream = DocFlavor.INPUT_STREAM.TEXT_PLAIN_UTF_16;
				} else if (contentType.equals("text/plain; charset=utf-16be")) {
					input_stream = DocFlavor.INPUT_STREAM.TEXT_PLAIN_UTF_16BE;
				} else if (contentType.equals("text/plain; charset=utf-16le")) {
					input_stream = DocFlavor.INPUT_STREAM.TEXT_PLAIN_UTF_16LE;
				} else if (contentType.equals("text/plain; charset=utf-8")) {
					input_stream = DocFlavor.INPUT_STREAM.TEXT_PLAIN_UTF_8;
				}
		
		} 
		System.out.println("type="+contentType);
		/*
		 * String[] fileNames = fileName.split("\\."); String extension =
		 * fileNames[fileNames.length-1];
		 * 
		 * if(extension!=null){ extension = extension.toLowerCase();
		 * if(extension.equals("pdf")){ input_stream =
		 * DocFlavor.INPUT_STREAM.PDF; input_stream =
		 * DocFlavor.INPUT_STREAM.GIF; input_stream =
		 * DocFlavor.INPUT_STREAM.JPEG; input_stream =
		 * DocFlavor.INPUT_STREAM.PCL; input_stream =
		 * DocFlavor.INPUT_STREAM.PNG; input_stream =
		 * DocFlavor.INPUT_STREAM.POSTSCRIPT; input_stream =
		 * DocFlavor.INPUT_STREAM.TEXT_HTML_HOST; input_stream =
		 * DocFlavor.INPUT_STREAM.TEXT_HTML_US_ASCII; input_stream =
		 * DocFlavor.INPUT_STREAM.TEXT_HTML_UTF_16; }else
		 * if(extension.equals("")){
		 * 
		 * } }
		 */

		PrintRequestAttributeSet pras = new HashPrintRequestAttributeSet();
		pras.add(new Copies(1));
		PrintService pss[] = PrintServiceLookup.lookupPrintServices(
				input_stream, pras);
		if (pss.length == 0) {
			// throw new RuntimeException("No printer services available.");
			return "nonPrinter";// 打印机不存在。
		}

		PrintService ps = pss[0];
		System.out.println("Printing to " + ps);
		DocPrintJob job = ps.createPrintJob();
		FileInputStream fin = new FileInputStream(fileName);
		Doc doc = new SimpleDoc(fin, input_stream, null);
		job.print(doc, pras);
		fin.close();
		System.out.println("Print success");
		return "true";
	}
	
	
	public static  String printPanelDesktop(String fileName){
		try {
			System.out.println(fileName);
			Desktop desktop = null;
			if (Desktop.isDesktopSupported()) {
				System.out.println("5");
				desktop = Desktop.getDesktop();
			}
			System.out.println("1");
			File file = new File(fileName);
			if(file==null){
				return "nonFile";
			}
			System.out.println("2");
			desktop.print(file);
			System.out.println("3");
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 

		} catch (IOException ioe) {
			ioe.printStackTrace();
			return "nonPrinter";
		}
		return "true";
	}
	
	public static String printLinux(String fileNamePath,String fileName){
		try {
			/*String server = "192.168.1.116";
			System.out.println("server="+server);*/
			//String command = "/usr/bin/acroread -toPostScript -rotateAndCenter -shrink -pairs $itemlist[$k][3] /tmp/$psfile";

			//String scp = "/usr/bin/scp "+fileNamePath+" www@"+server+":/tmp/"+fileName;
			//String ssh = "/usr/bin/ssh www@"+server+" -q 'lp /tmp/"+fileName+"'";
			String scp = "lpr "+fileNamePath;
			System.out.println("scp"+scp);
			//System.out.println(ssh);
			//Runtime.getRuntime().exec (command);
			
			
			 Process process = Runtime.getRuntime().exec (scp);

			 InputStreamReader ir=new InputStreamReader(process.getInputStream());
			 LineNumberReader input = new LineNumberReader (ir);

			 String line;
			 while ((line = input.readLine ()) != null){
				 System.out.println(line);
			 }
			


			//Runtime.getRuntime().exec (ssh);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "nonPrinter";
		} 

		return "success";
	}
}
