package com.genscript.gsscm.common;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.LineNumberReader;

import org.apache.commons.lang.xwork.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * bioInfo 相关
 * 
 * @author zouyulu
 * 
 */
@Service
public class BioInfoService {
	@Autowired
	private FileService fileService;

	public BioInfoService() {
	}

	/**
	 * 计算序列复杂度
	 * 
	 * @param sequence
	 * @return
	 */
	public Integer calculateGeneDifficulity(final String sequence) {
		if (sequence != null) {
			String fileName = this.exportFile(sequence);
			String resName = this.generateResName(fileName);
			Process p = null;
			try {
				String realPath = ServletActionContext.getServletContext()
						.getRealPath("/").replace("webapp", "");
				String plPath = realPath
						+ "WEB-INF/classes/bioinfo/GeneStandardAnalysis.pl";
				ProcessBuilder pb = new ProcessBuilder("perl", plPath,
						"--seqfile", fileName, "--outfile", resName);
				pb.redirectErrorStream(true);
				p = pb.start();
				InputStreamReader ir = new InputStreamReader(p.getInputStream());
				LineNumberReader input = new LineNumberReader(ir);

				String line;
				while ((line = input.readLine()) != null) {
					System.out.println(line);
				}
				Integer flag = readFile(resName);
				return flag;
			} catch (Exception e) {
				if (p != null) {
					p.destroy();
				}
				System.out.println("Error exec!");
			}
		}
		return null;
	}
//	public Integer calculateGeneDifficulity(final String sequence) {
//		return 1;
//	}
	
	public void calculateGeneDangerous(final String keyId, final String customerId, final String keyType) {
		Process p = null;
		try {
//			String realPath = ServletActionContext.getServletContext()
//					.getRealPath("/").replace("webapp", "");
//			String plPath = realPath
//					+ "WEB-INF/classes/bioinfo/pathogen_detector.pl";
//			String plPath ="/home/chenjinguo/pathogen_screening/pathogen_detector.pl";
//			ProcessBuilder pb = new ProcessBuilder("perl", plPath,
//					"-key_id", keyId, "-customer_id", customerId, "-key_type", keyType);
			String plPath ="/home/chenjinguo/remoteRun.sh";
			ProcessBuilder pb = new ProcessBuilder(plPath, keyId, keyType, customerId);
			pb.redirectErrorStream(true);
			p = pb.start();
//			InputStreamReader ir = new InputStreamReader(p.getInputStream());
//			LineNumberReader input = new LineNumberReader(ir);

		} catch (Exception e) {
			if (p != null) {
				p.destroy();
			}
			System.out.println("Error exec!");
		}
	}
	
	/**
	 * 计算序列复杂度
	 * 
	 * @param sequence
	 * @return
	 */
	public Integer calculatePeptideDifficulity(final String sequence, String purity, String modification) {
		if (StringUtils.isNotBlank(sequence)) {
			String fileName = this.exportFile(sequence, purity, modification);
			String resName = this.generateResName(fileName);
			Process p = null;
			try {
				String realPath = ServletActionContext.getServletContext()
						.getRealPath("/").replace("webapp", "");
				String plPath = realPath
						+ "WEB-INF/classes/bioinfo/PeptideAnalysis.pl";
				ProcessBuilder pb = new ProcessBuilder("perl", plPath,
						"--seqfile", fileName, "--outfile", resName);
				pb.redirectErrorStream(true);
				p = pb.start();
				InputStreamReader ir = new InputStreamReader(p.getInputStream());
				LineNumberReader input = new LineNumberReader(ir);

				String line;
				while ((line = input.readLine()) != null) {
					System.out.println(line);
				}
				Integer flag = readFile(resName);
				return flag;
			} catch (Exception e) {
				if (p != null) {
					p.destroy();
				}
				System.out.println("Error exec!");
			}
		}
		return null;
	}

	/**
	 * 读入文件
	 * 
	 * @param resName
	 * @return
	 */
	private Integer readFile(final String resName) {
		FileReader fr = null;
		BufferedReader br = null;
		String aline;
		try {
			fr = new FileReader(resName);
			br = new BufferedReader(fr);
			while ((aline = br.readLine()) != null) {
				if (aline.contains("1")) {
					return 1;
				}
			}
			return 0;
		} catch (Exception e) {
			throw new RuntimeException("read file error!");
		}
	}

	/**
	 * 根据sequence生成文件
	 * 
	 * @param sequence
	 * @return
	 */
	private String exportFile(final String... sequences) {
		FileWriter writer = null;
		File file = null;
		String fileName = generateGeneFileName();
		String dirName = fileName.substring(0, fileName.lastIndexOf("/")+1);
		try {
			System.out.println("============"+dirName+"-------------"+fileName);
			file = new File(dirName);
			boolean bFile = file.exists();
			if(bFile == false){
				bFile = file.mkdirs();
				if( bFile == true )
				{
				System.out.println("Create successfully!");
				}
				else
				{
				System.out.println("Disable to make the folder,please check the disk is full or not.");
				}
			}
			writer = new FileWriter(new File(fileName));
			if (sequences != null) {
				for (String sequence : sequences) {
					writer.write((StringUtils.isBlank(sequence)?"":sequence.replace("\n", ""))+"\n");
				}
			}
			writer.flush();
			writer.close();
			return fileName;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		} finally {
			try {
				if (writer != null)
					writer.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 生成目标文件
	 * 
	 * @param fileName
	 * @return
	 */
	private String generateResName(String fileName) {
		int i = fileName.indexOf(".");
		String res = fileName.substring(0, i + 1) + "res";
		return res;
	}

	/**
	 * 
	 * 生成随机文件
	 * 
	 * @return
	 */
	private String generateGeneFileName() {
		StringBuilder sb = new StringBuilder();
		String baseTmpDir = fileService.getUploadTmpPath();
		return sb.append(baseTmpDir).append(System.currentTimeMillis())
				.append(".").append("seq").toString();
	}
}
