package com.piggy.coffee.common.util.io;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.List;

public class IoUtils {

	public static final byte[] getByteByInputStream(InputStream in) {
		ByteArrayOutputStream bout = null;
		try {
			bout = new ByteArrayOutputStream();
			byte[] tmpByte = new byte[1024];
			try {
				int length = 0;
				while ((length = in.read(tmpByte)) != -1) {
					bout.write(tmpByte, 0, length);
				}
			} catch (IOException e) {
				throw new RuntimeException(e);
			}

			return bout.toByteArray();
		} finally {
			try {
				if (bout != null) {
					bout.close();
				}
			} catch (IOException e) {
				// ignore quietly
			}

		}

	}

	public static final String getStringInputStream(InputStream in) {
		try {
			return new String(getByteByInputStream(in), "UTF-8");
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * 把指定path下的文件，存放到 files列表里
	 * 
	 * @param path
	 * @param files
	 */
	public static final void getAllFiles(String path, List<File> files) {
		getAllFilesByProfix(path, null, files);
	}

	/**
	 * 把指定path下的指定suffix的文件，存放到 files列表里
	 * 
	 * @param path
	 * @param suffix
	 * @param files
	 */
	public static final void getAllFilesByProfix(String path, String suffix, List<File> files) {
		File file = new File(path);
		if (file.isDirectory()) {
			for (File subFile : file.listFiles()) {
				if (subFile.isDirectory()) {
					getAllFilesByProfix(subFile.getPath(), suffix, files);
				} else {
					getFileByProfix(subFile, suffix, files);
				}
			}

		} else {
			getFileByProfix(file, suffix, files);
		}
	}

	private static final void getFileByProfix(File file, String suffix, List<File> files) {
		if (suffix == null) {
			files.add(file);
		} else if (file.getName().endsWith(suffix)) {
			files.add(file);
		}

	}

}