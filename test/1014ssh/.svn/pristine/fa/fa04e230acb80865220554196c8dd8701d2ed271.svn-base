package com.ssh.utils;

import java.util.UUID;

public class UploadUtils {
	/**
	 * 
	 * @Title: getUuidFileName
	 * @Description: 获取随机的唯一文件名
	 * @param fileName
	 * @return
	 */
	public static String getUuidFileNam(String FileName) {
		int indexOf = FileName.lastIndexOf(".");
		String exFile = FileName.substring(indexOf);
		String uuidFileName = UUID.randomUUID().toString().replace("-", "")+exFile;
		return uuidFileName;
	}
	
	public static String getPath(String uuidFileName) {
		int code1=uuidFileName.hashCode();
		int d1 = code1 & 0xf;//作为一级目录
		int code2 = code1 >>>4;
		int d2 = code2 & 0xf;//作为二级目录
		return "/"+d1+"/"+d2;
	}

}
