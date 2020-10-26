package com.ssh.utils;

import java.util.UUID;

public class UploadUtils {
	/**
	 * 
	 * @Title: getUuidFileName
	 * @Description: ��ȡ�����Ψһ�ļ���
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
		int d1 = code1 & 0xf;//��Ϊһ��Ŀ¼
		int code2 = code1 >>>4;
		int d2 = code2 & 0xf;//��Ϊ����Ŀ¼
		return "/"+d1+"/"+d2;
	}

}
