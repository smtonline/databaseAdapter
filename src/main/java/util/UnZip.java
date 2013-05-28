/*
 * Copyright (C) 2012 Shenzhen SMT online Co., Ltd.
 * 
 * 项目名称:SMT移动信息化解决方案系统
 * 创建日期:2013-1-28
 */
package util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

/**
 * zip压缩包解压类
 * 修改日期:2013-1-28
 * 
 * @author gus@sinomaster.com
 * @version 1.0.0
 */
public class UnZip {
	private final static Logger log = Logger.getLogger(UnZip.class);
	
	/**
	 * 解压指定文件,并生成到指定目录中
	 * @param zipFileName zip压缩包完整文件路径名
	 * @param outputDirectory 解压后的压缩文件生成到的指定文件夹
	 * @throws UnZipException 
	 */
	public static void unZip(String zipFileName, String outputDirectory)
			throws UnZipException {
		try {
			org.apache.tools.zip.ZipFile zipFile = new org.apache.tools.zip.ZipFile(
					zipFileName);
			java.util.Enumeration e = zipFile.getEntries();
			org.apache.tools.zip.ZipEntry zipEntry = null;
			createDirectory(outputDirectory, "");
			while (e.hasMoreElements()) {
				zipEntry = (org.apache.tools.zip.ZipEntry) e.nextElement();
				log.debug("unziping " + zipEntry.getName());
				if (zipEntry.isDirectory()) {
					String name = zipEntry.getName();
					name = name.substring(0, name.length() - 1);
					File f = new File(outputDirectory + File.separator + name);
					f.mkdir();
					// System.out.println("创建目录：" + outputDirectory
					// + File.separator + name);
				} else {
					String fileName = zipEntry.getName();
					fileName = fileName.replace('\\', '/');
					if (fileName.indexOf("/") != -1) {
						createDirectory(outputDirectory, fileName.substring(0,
								fileName.lastIndexOf("/")));
						fileName = fileName.substring(
								fileName.lastIndexOf("/") + 1,
								fileName.length());
					}

					File f = new File(outputDirectory + File.separator
							+ zipEntry.getName().replace('\\', '/'));

					f.createNewFile();
					InputStream in = zipFile.getInputStream(zipEntry);
					FileOutputStream out = new FileOutputStream(f);

					byte[] by = new byte[1024];
					int c;
					while ((c = in.read(by)) != -1) {
						out.write(by, 0, c);
					}
					out.close();
					in.close();
				}
			}
		} catch (IOException e) {
			throw new UnZipException("解压失败");
		}

	}

	private static void createDirectory(String directory, String subDirectory) {
		String dir[];
		File fl = new File(directory);
		if (StringUtils.isEmpty(subDirectory) && !fl.exists()) {
			fl.mkdir();
		} else if (!StringUtils.isEmpty(subDirectory)) {
			dir = subDirectory.replace('\\', '/').split("/");
			for (int i = 0; i < dir.length; i++) {
				File subFile = new File(directory + File.separator + dir[i]);
				if (!subFile.exists())
					subFile.mkdir();
				directory += File.separator + dir[i];
			}
		}
	}
	
	/**
	 * 删除文件夹以及文件夹下的子目录与文件
	 * @param file
	 */
	public static void deleteFile(File file){ 
		if (file.exists()) {
			if (file.isFile()) {
				file.delete();
			} else if (file.isDirectory()) {
				File files[] = file.listFiles();
				for (int i = 0; i < files.length; i++) {
					deleteFile(files[i]);
				}
			}
			file.delete();
		}
	}
}
