package com.genscript.gsscm.common.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;

import jcifs.smb.SmbFile;
import jcifs.smb.SmbFileInputStream;
import jcifs.smb.SmbFileOutputStream;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.genscript.core.util.ReflectionUtils;

/**
 * @description: Remote shared read and write file operation tools
 * @author: Golf
 * @createDate: 2010/4/15 4:42 PM
 */
@Service
@Transactional
public class RemoteFileUtil {

//	public static final String REMOTE_HOST_IP = "10.168.2.229";
//	public static final String LOGIN_ACCOUNT = "www";
//	public static final String LOGIN_PASSWORD = "scm";
//	public static final String SHARE_DOC_NAME = "www";
//	private static final String localPath="Dtemp";
	private String localPath;
	private String remoteHostIp;
	private String account;
	private String password;
	private String shareDocName;

	/**
	 * Default constructor
	 */
	public RemoteFileUtil() {
		super();
	}

	/**
	 * Constructor
	 * 
	 * @param remoteHostIp
	 *            Remote Host IP
	 * @param account
	 *            Login account
	 * @param password
	 *            Password
	 * @param sharePath
	 *            Shared folder path
	 */
	public RemoteFileUtil(String remoteHostIp, String account, String password,
			String shareDocName) {
		this.remoteHostIp = remoteHostIp;
		this.account = account;
		this.password = password;
		this.shareDocName = shareDocName;
	}

	/**
	 * Read remote shared files on all lines
	 * 
	 * @param remoteFileName
	 *            File Name Description: The parameter is the relative path to
	 *            shared directory If the remote file path：shareDoc\test.txt,
	 *            The parameter is test.txt(Name for the shared directory in
	 *            which shareDoc); If the remote file
	 *            path：shareDoc\doc\text.txt, The parameter is doc\text.txt;
	 * @return File 
	 */
	public File readFile(String remoteFileName) {
		SmbFile smbFile = null;
		File localfile=null;
		InputStream bis=null;     
        OutputStream bos=null;
		String conStr = null;
		conStr = "smb://" + account + ":" + password + "@" + remoteHostIp + "/"
				+ shareDocName + "/" + remoteFileName;
		try {
			smbFile = new SmbFile(conStr);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		byte[] buffer = new byte[1024 * 8];

		try {
			String filename = smbFile.getName();
			bis=new BufferedInputStream(new SmbFileInputStream(smbFile));
			localfile=new File(localPath + File.separator + filename); 
			bos=new BufferedOutputStream(new FileOutputStream(localfile));
			while ((bis.read(buffer)) != -1) {
				bos.write(buffer);
			}
		} catch (Exception e) {
			throw ReflectionUtils.convertReflectionExceptionToUnchecked(e);
		}finally{
			try {
				bos.close();
				bis.close();
			}catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		return localfile;
	}

	/**
	 * Sharing files on a remote write
	 * 
	 * @param is
	 *            Local file input stream
	 * @param remoteFileName
	 *            Remote file name Note: parameters for the shared directory
	 *            relative path If the remote file path：shareDoc\test.txt,The
	 *            parameter is test.txt(Name for the shared directory in which
	 *            shareDoc); If the remote file path：shareDoc\doc\text.txt,The
	 *            parameter is doc\text.txt;
	 * @return
	 */
	public boolean writeFile(InputStream is, String remoteFileName) {
		SmbFile smbFile = null;
		OutputStream os = null;
		byte[] buffer = new byte[1024 * 8];
		String conStr = null;
		conStr = "smb://" + account + ":" + password + "@" + remoteHostIp + "/"
				+ shareDocName + "/" + remoteFileName;
		try {
			smbFile = new SmbFile(conStr);
		} catch (MalformedURLException e) {
			e.printStackTrace();
			return false;
		}

		// Get remote file output stream and writing files to the remote shared
		// folder
		try {
			os = new BufferedOutputStream(new SmbFileOutputStream(smbFile));
			while ((is.read(buffer)) != -1) {
				os.write(buffer);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		return true;
	}

	/**
	 * Sharing files on a remote write overloaded
	 * 
	 * @param localFileName
	 *            To write the full name of the local file
	 * @param remoteFileName
	 *            Remote file name Note: parameters for the shared directory
	 *            relative path
	 * @return
	 */
	public boolean writeFile(String localFileFullName, String remoteFileName) {
		try {
			return writeFile(new FileInputStream(new File(localFileFullName)),
					remoteFileName);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * Sharing files on a remote write overloaded
	 * 
	 * @param localFileName
	 *            To write to local files
	 * @param remoteFileName
	 *            Remote file name Note: parameters for the shared directory
	 *            relative path
	 * @return
	 */
	public boolean writeFile(File localFile, String remoteFileName) {
		try {
			return writeFile(new FileInputStream(localFile), remoteFileName);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return false;
		}
	}

	public void setLocalPath(String localPath) {
		this.localPath = localPath;
	}

	public void setRemoteHostIp(String remoteHostIp) {
		this.remoteHostIp = remoteHostIp;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setShareDocName(String shareDocName) {
		this.shareDocName = shareDocName;
	}
	
}
