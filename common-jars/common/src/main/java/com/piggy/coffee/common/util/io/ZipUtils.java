package com.piggy.coffee.common.util.io;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

public class ZipUtils {

	public static void unZip(String src, String target) throws IOException {
		ZipInputStream zipIs = null;
		ZipFile zipFile = null;
		try {
			zipFile = new ZipFile(src); 
			zipIs = new ZipInputStream(new FileInputStream(src));
			ZipEntry zipEntry = null;	
			while ((zipEntry = zipIs.getNextEntry()) != null) {
				File file = new File(target + zipEntry.getName());
				if (zipEntry.getName().endsWith("/")) {
					file.mkdirs();
				} else {
					byte[] b = IoUtils.getByteByInputStream(zipFile.getInputStream(zipEntry));
					if (!file.getParentFile().exists()) {
						file.getParentFile().mkdirs();
					}
					FileOutputStream fout = new FileOutputStream(file);
					fout.write(b);
					fout.close();
				}
			}
			zipFile.close();
		} finally {
			if (zipIs != null) {
				zipIs.close();
			}
			if (zipFile != null) {
				zipFile.close();
			}
		}
	}

	public static void inZip(List<File> files, String basePath, String target) throws IOException {
		List<File> cfiles = new ArrayList<File>();
		for (File file : files) {
			cfiles.add(file);
		}
		for (File file : cfiles) {
			if (file.isDirectory()) {
				IoUtils.getAllFiles(file.toString(), files);
			}
		}
		ZipOutputStream zos = new ZipOutputStream(new FileOutputStream(target));
		for (File file : files) {
			System.out.println(file);
			ZipEntry entry = null;
			byte[] b = new byte[1024];
			int len = 0;
			if (file.isFile()) {
				entry = new ZipEntry(file.toString().substring(basePath.length()));
				zos.putNextEntry(entry);
				InputStream is = new BufferedInputStream(new FileInputStream(file));
				while ((len = is.read(b, 0, b.length)) != -1) {
					zos.write(b, 0, len);
				}
				is.close();
			} else {

			}
		}
		zos.close();

	}

}