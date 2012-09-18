package com.genscript.gsscm.common.util;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

import com.genscript.gsscm.order.dto.ErpOrderDtlDTO;

/*
 *2010-8-2 上午08:42:42
 *by mingrs
 */
public class StringToXMLUtil {

	/**
	 * 将STRING 转化成XML文件。
	 * 
	 * @param args
	 * @throws IOException
	 */

	public static void strChangeXML(String str, String path, String fileName)
			throws IOException {
		SAXReader saxReader = new SAXReader();
		Document document;
		try {
			document = saxReader.read(new ByteArrayInputStream(str.getBytes()));
			OutputFormat format = OutputFormat.createPrettyPrint();
			/** 指定XML字符集编码 */
			format.setEncoding("utf-8");
			/** 将document中的内容写入文件中 */
			XMLWriter writer = new XMLWriter(new FileWriter(new File(path + "/"
					+ fileName)), format);
			writer.write(document);
			writer.close();
		} catch (DocumentException e) {
			// TODOAuto-generatedcatchblock
			e.printStackTrace();
		}
	}
/**
 * 分析XML文件是否存在
 * @param path
 * @param fileName
 * @return 文件存在返回文件里的内容，否则返回NULL；
 */
	public static Document checkingXML(String path, String fileName) {
		SAXReader saxReader = new SAXReader();
		Document doc = null;
		try {
			File file = new File(path + "/" + fileName);
			if (file.exists()) {
				doc = saxReader.read(new File(path + "/" + fileName));
			}
		} catch (DocumentException e) {
			e.printStackTrace();
		}
		return doc;
	}

	/**
	 * 删除文件，可以是单个文件或文件夹
	 * 
	 * @param fileName
	 *            待删除的文件名
	 * @return 文件删除成功返回true,否则返回false
	 */
	public static boolean delete(String path,String fileName) {
		fileName = path+"/"+fileName;
		File file = new File(fileName);
		if (!file.exists()) {
			System.out.println("删除文件失败：" + fileName + "文件不存在");
			return false;
		} else {
			if (file.isFile()) {

				return deleteFile(fileName);
			} else {
				return deleteDirectory(fileName);
			}
		}
	}

	/**
	 * 删除单个文件
	 * 
	 * @param fileName
	 *            被删除文件的文件名
	 * @return 单个文件删除成功返回true,否则返回false
	 */
	public static boolean deleteFile(String fileName) {
		File file = new File(fileName);
		if (file.isFile() && file.exists()) {
			file.delete();
			System.out.println("删除单个文件" + fileName + "成功！");
			return true;
		} else {
			System.out.println("删除单个文件" + fileName + "失败！");
			return false;
		}
	}

	/**
	 * 删除目录（文件夹）以及目录下的文件
	 * 
	 * @param dir
	 *            被删除目录的文件路径
	 * @return 目录删除成功返回true,否则返回false
	 */
	public static boolean deleteDirectory(String dir) {
		// 如果dir不以文件分隔符结尾，自动添加文件分隔符
		if (!dir.endsWith(File.separator)) {
			dir = dir + File.separator;
		}
		File dirFile = new File(dir);
		// 如果dir对应的文件不存在，或者不是一个目录，则退出
		if (!dirFile.exists() || !dirFile.isDirectory()) {
			System.out.println("删除目录失败" + dir + "目录不存在！");
			return false;
		}
		boolean flag = true;
		// 删除文件夹下的所有文件(包括子目录)
		File[] files = dirFile.listFiles();
		for (int i = 0; i < files.length; i++) {
			// 删除子文件
			if (files[i].isFile()) {
				flag = deleteFile(files[i].getAbsolutePath());
				if (!flag) {
					break;
				}
			}
			// 删除子目录
			else {
				flag = deleteDirectory(files[i].getAbsolutePath());
				if (!flag) {
					break;
				}
			}
		}

		if (!flag) {
			System.out.println("删除目录失败");
			return false;
		}

		// 删除当前目录
		if (dirFile.delete()) {
			System.out.println("删除目录" + dir + "成功！");
			return true;
		} else {
			System.out.println("删除目录" + dir + "失败！");
			return false;
		}
	}

	/**
	 * 用于将XML串解析成LIST的通用方法
	 * 
	 * @param String
	 * @return List
	 */
	@SuppressWarnings("unchecked")
	public static List<HashMap<String, String>> XMLToList(String XMLStr) {
		List<HashMap<String, String>> XMLList = new ArrayList<HashMap<String, String>>();
		if (XMLStr != null && (!"".equals(XMLStr.trim()))
				&& (!"error".equals(XMLStr.trim()))) {
			try {
				Document document = DocumentHelper.parseText(XMLStr);// 将XML串进行解析
				Element root = document.getRootElement();// 取得根结点

				for (Iterator i = root.elementIterator(); i.hasNext();) {
					HashMap<String, String> hashMap = new HashMap<String, String>();
					Element element = (Element) i.next();// 取得各条记录
					for (Iterator j = element.elementIterator(); j.hasNext();) {
						Element ele = (Element) j.next();// 取得各条记录里的各个字段
						// System.out.print("name="+ele.getName()+";  ");
						// System.out.println("value="+ele.getStringValue());
						hashMap.put(ele.getName(), ele.getStringValue().trim());
					}
					XMLList.add(hashMap);

				}

			} catch (DocumentException e) {
				e.printStackTrace();
			}
		} else {
			return null;
		}
		return XMLList;
	}

	public static void main(String[] args) throws ClassNotFoundException,
			SecurityException, NoSuchFieldException, IllegalArgumentException,
			IllegalAccessException, DocumentException {
		
		/*
		String path = "D:\\";
		Document pathStr = StringToXMLUtil.checkingXML(path, "27.xml");
		List<HashMap<String, String>> xmlMap = StringToXMLUtil
				.XMLToList(pathStr.asXML());
		List<PrivilegeDTO> pList = new ArrayList<PrivilegeDTO>();
		Class<?> clazz = Class
				.forName("com.genscript.gsscm.privilege.dto.PrivilegeDTO");
		for (HashMap<String, String> map : xmlMap) {
			PrivilegeDTO p = new PrivilegeDTO();
			for (Entry<String, String> entry : map.entrySet()) {
				if (!(entry.getKey().equals("operationType"))
						&& !(entry.getKey().equals("temp"))) {
					if (entry.getKey().equals("privilegeId")) {
						if (!(map.get("privilegeId") == null
								|| map.get("privilegeId").equals("undefined")
								|| map.get("privilegeId").equals("null") || map
								.get("privilegeId").equals(""))) {
							p.setPrivilegeId(Integer.valueOf(map
									.get("privilegeId")));
						}
					} else {
						Field field = clazz.getDeclaredField(entry.getKey());
						field.setAccessible(true);
						field.set(p, entry.getValue());
					}
				}
			}
			if (map.get("operationType").equals("EDIT")) {
				p.setOperationType(OperationType.EDIT);
			} else if (map.get("operationType").equals("DEL")) {
				p.setOperationType(OperationType.DEL);
			} else {
				p.setOperationType(OperationType.ADD);
			}
			pList.add(p);
		}
		for (PrivilegeDTO p1 : pList) {
			System.out.println(p1.getPrivilegeName());
		}
		
		*/
		String xmlStr = "<?xml version='1.0' encoding='utf-8'?><Ship><ShipHead><trackingNo>20111104002</trackingNo></ShipHead>";
		for(int i=1;i<56;i++){
			xmlStr +="<ShipDtl>" +
					"<number01>88807300</number01>" +
					"<number02>"+i+"</number02>" +
					"<PartNum>SC1507</PartNum>" +
					"<UOM>EA</UOM>" +
					"<qty>1</qty>" +
					"</ShipDtl>";
		}
		xmlStr += "</Ship>";
		try {
			StringToXMLUtil.strChangeXML(xmlStr, "d:/", "xm.xml");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		/*String xmlStr = "<?xml version='1.0' encoding='UTF-8'?><SalesOrderDataSet><OrderDtl><ItemNum>1</ItemNum><UnitPrice>159.0</UnitPrice><Cost>89.0</Cost><VtRatio>1</VtRatio><BtRatio>0</BtRatio></OrderDtl></SalesOrderDataSet>";
		//List<HashMap<String, String>> xmlMap = StringToXMLUtil
		//.XMLToList(xmlStr);
		Document document = DocumentHelper.parseText(xmlStr);// 将XML串进行解析
		Element root = document.getRootElement();// 取得根结点
		HashMap<String, ErpOrderDtlDTO> hashMap = new HashMap<String, ErpOrderDtlDTO>();
		for (Iterator i = root.elementIterator(); i.hasNext();) {
			Element element = (Element) i.next();// 取得各条记录
			ErpOrderDtlDTO erpOrderDtlDTO = new ErpOrderDtlDTO();
			
			for (Iterator j = element.elementIterator(); j.hasNext();) {
				Element ele = (Element) j.next();// 取得各条记录里的各个字段
				//if(ele.getName().equalsIgnoreCase(anotherString))
				Field field = erpOrderDtlDTO.getClass().getDeclaredField(ele.getName());
				field.setAccessible(true);
				field.set(erpOrderDtlDTO, ele.getStringValue().trim());
				// System.out.print("name="+ele.getName()+";  ");
				// System.out.println("value="+ele.getStringValue());
			}
			hashMap.put(erpOrderDtlDTO.getItemNum().toString(), erpOrderDtlDTO);
			System.out.println(erpOrderDtlDTO);
		}
		System.out.println(hashMap);*/
		/*
		 * String path = "D:\\"; Integer userId = 100000; String fileName =
		 * userId + ".xml"; String xmlBody =
		 * "<operation_info><operation_result>0</operation_result><operation>ADD</operation></operation_info>"
		 * ; Document strXML = StringToXMLUtil.checkingXML(path, fileName); if
		 * (strXML == null) { String xmlHeader =
		 * "<?xml version=\"1.0\" encoding=\"utf-8\"?><root>"; String xmlFoot =
		 * "</root>"; String xml = xmlHeader + xmlBody + xmlFoot; try {
		 * StringToXMLUtil.strChangeXML(xml, path, fileName); } catch
		 * (IOException e) { // TODO Auto-generated catch block
		 * e.printStackTrace(); } } else { String str = strXML.asXML(); str =
		 * str.replaceAll("</root>", xmlBody + "</root>");
		 * System.out.println(str); try { StringToXMLUtil.strChangeXML(str,
		 * path, fileName); } catch (IOException e) { // TODO Auto-generated
		 * catch block e.printStackTrace(); } }
		 */
	}
}
