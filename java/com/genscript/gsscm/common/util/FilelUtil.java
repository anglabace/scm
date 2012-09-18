package com.genscript.gsscm.common.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class FilelUtil {

	/**
	 * 逐行获得一个文件的内容.
	 * 
	 * @param filename
	 * @return
	 * @throws IOException
	 */
	public static List<String> getLineList(String fileName) throws IOException {
		List<String> lineList = new ArrayList<String>();
		BufferedReader bufferedreader = new BufferedReader(new FileReader(
				fileName));
		String stemp;
		while ((stemp = bufferedreader.readLine()) != null) {
			lineList.add(stemp);
		}
		return lineList;
	}

	public static List<String> getLineList(File file) throws IOException {
		List<String> lineList = new ArrayList<String>();
		BufferedReader bufferedreader = new BufferedReader(new FileReader(file));
		String stemp;
		while ((stemp = bufferedreader.readLine()) != null) {
			lineList.add(stemp);
		}
		return lineList;
	}

	public static boolean Move(File srcFile, String destPath) {
		// Destination directory
		File dir = new File(destPath);

		// Move file to new directory
		boolean success = srcFile.renameTo(new File(dir, srcFile.getName()));

		return success;
	}

	public static boolean Move(String srcFile, String destPath) {
		// File (or directory) to be moved
		File file = new File(srcFile);

		// Destination directory
		File dir = new File(destPath);

		// Move file to new directory
		boolean success = file.renameTo(new File(dir, file.getName()));

		return success;
	}

	public static void CopyDirectory(String oldPath, String newPath)
			throws Exception {
		File srcDir = new File(oldPath);
		if (!(srcDir.exists() && srcDir.isDirectory()))
			throw new Exception("目录不存在");
		File[] files = srcDir.listFiles(new FilenameFilter() {
			public boolean accept(File dir, String name) {
				return name.endsWith(".pdf");
			}

		});
		for (File f : files) {
			System.out.println(f.getName());
			FilelUtil.Copy(f, newPath+"/"+f.getName());
		}
	}

	public static void Copy(String oldPath, String newPath) {
		try {
			int bytesum = 0;
			int byteread = 0;
			File oldfile = new File(oldPath);
			if (oldfile.exists()) {
				InputStream inStream = new FileInputStream(oldPath);
				FileOutputStream fs = new FileOutputStream(newPath);
				byte[] buffer = new byte[1444];
				int length;
				while ((byteread = inStream.read(buffer)) != -1) {
					bytesum += byteread;
					System.out.println(bytesum);
					fs.write(buffer, 0, byteread);
				}
				inStream.close();
			}
		} catch (Exception e) {
			System.out.println("error  ");
			e.printStackTrace();
		}
	}

	public static void Copy(File oldfile, String newPath) {
		try {
			int bytesum = 0;
			int byteread = 0;
			// File oldfile = new File(oldPath);
			if (oldfile.exists()) {
				InputStream inStream = new FileInputStream(oldfile);
				FileOutputStream fs = new FileOutputStream(newPath);
				byte[] buffer = new byte[1444];
				while ((byteread = inStream.read(buffer)) != -1) {
					bytesum += byteread;
					System.out.println(bytesum);
					fs.write(buffer, 0, byteread);
				}
				inStream.close();
			}
		} catch (Exception e) {
			System.out.println("error  ");
			e.printStackTrace();
		}
	}
	
	/*
	 * 删除所有文件夹下面的所有文件。mingrs
	 */
	public static void deleteAllFile(String folderFullPath) {
		File file = new File(folderFullPath);
		if (file.exists()) {
			if (file.isDirectory()) {
				File[] fileList = file.listFiles();
				for (int i = 0; i < fileList.length; i++) {
					String filePath = fileList[i].getPath();
					deleteAllFile(filePath);
				}
			}
			if (file.isFile()) {
				file.delete();
			}
		}
	}
	
	/*
	 * copy to file .mingrs
	 */
	public static void copyToNewFile(String from_name, String to_name)

	throws IOException

	{

		File from_file = new File(from_name); // Get File objects from Strings

		File to_file = new File(to_name);

		// First make sure the source file exists, is a file, and is readable.

		if (!from_file.exists())

			abort("no such source file: " + from_name);

		if (!from_file.isFile())

			abort("can't copy directory: " + from_name);

		if (!from_file.canRead())

			abort("source file is unreadable: " + from_name);

		// If the destination is a directory, use the source file name

		// as the destination file name

		if (to_file.isDirectory())

			to_file = new File(to_file, from_file.getName());

		// If the destination exists, make sure it is a writeable file

		// and ask before overwriting it. If the destination doesn't

		// exist, make sure the directory exists and is writeable.

		if (to_file.exists()) {

			if (!to_file.canWrite())

				abort("destination file is unwriteable: " + to_name);

			// Ask whether to overwrite it

			System.out.print("Overwrite existing file " + to_file.getName() +

			"? (Y/N): ");

			System.out.flush();

			// Get the user's response.

			BufferedReader in =

			new BufferedReader(new InputStreamReader(System.in));

			String response = in.readLine();

			// Check the response. If not a Yes, abort the copy.
			if(response!=null){
				if (!response.equals("Y") && !response.equals("y"))

					abort("existing file was not overwritten.");
			}
			

		}

		else {

			// If file doesn't exist, check if directory exists and is

			// writeable. If getParent() returns null, then the directory is

			// the current dir. so look up the user.dir system property to

			// find out what that is.

			String parent = to_file.getParent(); // The destination directory

			if (parent == null) // If none, use the current directory

				parent = System.getProperty("user.dir");

			File dir = new File(parent); // Convert it to a file.

			if (!dir.exists())

				abort("destination directory doesn't exist: " + parent);

			if (dir.isFile())

				abort("destination is not a directory: " + parent);

			if (!dir.canWrite())

				abort("destination directory is unwriteable: " + parent);

		}

		// If we've gotten this far, then everything is okay.

		// So we copy the file, a buffer of bytes at a time.

		FileInputStream from = null; // Stream to read from source

		FileOutputStream to = null; // Stream to write to destination

		try {

			from = new FileInputStream(from_file); // Create input stream

			to = new FileOutputStream(to_file); // Create output stream

			byte[] buffer = new byte[4096]; // To hold file contents

			int bytes_read; // How many bytes in buffer

			// Read a chunk of bytes into the buffer, then write them out,

			// looping until we reach the end of the file (when read() returns

			// -1). Note the combination of assignment and comparison in this

			// while loop. This is a common I/O programming idiom.

			while ((bytes_read = from.read(buffer)) != -1)
				// Read until EOF

				to.write(buffer, 0, bytes_read); // write

		}

		// Always close the streams, even if exceptions were thrown

		finally {

			if (from != null)
				try {
					from.close();
				} catch (IOException e) {
					;
				}

			if (to != null)
				try {
					to.close();
				} catch (IOException e) {
					;
				}

		}

	}

	/** A convenience method to throw an exception */

	private static void abort(String msg) throws IOException {

		throw new IOException("FileCopy: " + msg);

	}

	public static void main(String[] args) throws Exception {
		FilelUtil.CopyDirectory("d:/tmp/794790693324", "d:/tmp/a");
	}

}
