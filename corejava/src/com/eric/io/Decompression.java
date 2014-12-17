package com.eric.io;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.tools.zip.ZipEntry;
import org.apache.tools.zip.ZipFile;
import org.apache.tools.zip.ZipOutputStream;

public class Decompression {
	private static final int BUFFEREDSIZE = 1024;

	public Decompression() {
	}

	/**
	 * 解压zip格式的压缩文件到当前文件夹
	 * 
	 * @param zipFileName
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public synchronized void unzipFile(String zipFileName) throws Exception {
		try {
			File f = new File(zipFileName);
			ZipFile zipFile = new ZipFile(zipFileName);
			if ((!f.exists()) && (f.length() <= 0)) {
				throw new Exception("要解压的文件不存在!");
			}
			System.out.println(zipFileName + " was uncompressing....");
			String strPath, gbkPath, strtemp;
			File tempFile = new File(f.getParent());
			strPath = tempFile.getAbsolutePath();
			java.util.Enumeration e = zipFile.getEntries();
			while (e.hasMoreElements()) {
				org.apache.tools.zip.ZipEntry zipEnt = (ZipEntry) e
						.nextElement();
				gbkPath = zipEnt.getName();
				System.out.println("uncomress:" + zipEnt.getName());
				if (zipEnt.isDirectory()) {
					strtemp = strPath + "/" + gbkPath;
					File dir = new File(strtemp);
					dir.mkdirs();
					continue;
				} else {
					// 读写文件
					InputStream is = zipFile.getInputStream(zipEnt);
					BufferedInputStream bis = new BufferedInputStream(is);
					gbkPath = zipEnt.getName();
					strtemp = strPath + "/" + gbkPath;

					// 建目录
					String strsubdir = gbkPath;
					for (int i = 0; i < strsubdir.length(); i++) {
						if (strsubdir.substring(i, i + 1).equalsIgnoreCase("/")) {
							String temp = strPath + "/"
									+ strsubdir.substring(0, i);
							File subdir = new File(temp);
							if (!subdir.exists())
								subdir.mkdir();
						}
					}
					FileOutputStream fos = new FileOutputStream(strtemp);
					BufferedOutputStream bos = new BufferedOutputStream(fos);
					int c;
					while ((c = bis.read()) != -1) {
						bos.write((byte) c);
					}
					bos.close();
					fos.close();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	/**
	 * 解压zip格式的压缩文件到指定位置
	 * 
	 * @param zipFileName
	 *            压缩文件
	 * @param extPlace
	 *            解压目录
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public synchronized void unzip(String zipFileName, String extPlace)
			throws Exception {
		try {
			(new File(extPlace)).mkdirs();
			File f = new File(zipFileName);
			ZipFile zipFile = new ZipFile(zipFileName);
			if ((!f.exists()) && (f.length() <= 0)) {
				throw new Exception("要解压的文件不存在!");
			}
			String strPath, gbkPath, strtemp;
			File tempFile = new File(extPlace);
			strPath = tempFile.getAbsolutePath();
			java.util.Enumeration e = zipFile.getEntries();
			while (e.hasMoreElements()) {
				org.apache.tools.zip.ZipEntry zipEnt = (ZipEntry) e
						.nextElement();
				gbkPath = zipEnt.getName();
				if (zipEnt.isDirectory()) {
					strtemp = strPath + File.separator + gbkPath;
					File dir = new File(strtemp);
					dir.mkdirs();
					continue;
				} else {
					// 读写文件
					InputStream is = zipFile.getInputStream(zipEnt);
					BufferedInputStream bis = new BufferedInputStream(is);
					gbkPath = zipEnt.getName();
					strtemp = strPath + File.separator + gbkPath;

					// 建目录
					String strsubdir = gbkPath;
					for (int i = 0; i < strsubdir.length(); i++) {
						if (strsubdir.substring(i, i + 1).equalsIgnoreCase("/")) {
							String temp = strPath + File.separator
									+ strsubdir.substring(0, i);
							File subdir = new File(temp);
							if (!subdir.exists())
								subdir.mkdir();
						}
					}
					FileOutputStream fos = new FileOutputStream(strtemp);
					BufferedOutputStream bos = new BufferedOutputStream(fos);
					int c;
					while ((c = bis.read()) != -1) {
						bos.write((byte) c);
					}
					bos.close();
					fos.close();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	/**
	 * 压缩zip格式的压缩文件
	 * 
	 * @param inputFilename
	 *            压缩的文件或文件夹及详细路径
	 * @param zipFilename
	 *            输出文件名称及详细路径
	 * @throws IOException
	 */
	public synchronized void zip(String inputFilename, String zipFilename)
			throws IOException {
		zip(new File(inputFilename), zipFilename);
	}

	/**
	 * 压缩zip格式的压缩文件
	 * 
	 * @param inputFile
	 *            需压缩文件
	 * @param zipFilename
	 *            输出文件及详细路径
	 * @throws IOException
	 */
	public synchronized void zip(File inputFile, String zipFilename)
			throws IOException {
		ZipOutputStream out = new ZipOutputStream(new FileOutputStream(
				zipFilename));
		try {
			zip(inputFile, out, "");
			System.out.println(zipFilename + " was genrated successful....");
		} catch (IOException e) {
			throw e;
		} finally {
			out.close();
		}
	}

	/**
	 * 压缩zip格式的压缩文件
	 * 
	 * @param inputFile
	 *            需压缩文件
	 * @param out
	 *            输出压缩文件
	 * @param base
	 *            结束标识
	 * @throws IOException
	 */
	@SuppressWarnings("unused")
	private synchronized void zip(File inputFile, ZipOutputStream out,
			String base) throws IOException {
		System.out.println("compress " + inputFile + " ...");
		if (inputFile.isDirectory()) {
			File[] inputFiles = inputFile.listFiles();
			out.putNextEntry(new ZipEntry(base + "/"));
			base = base.length() == 0 ? "" : base + "/";
			for (int i = 0; i < inputFiles.length; i++) {
				System.out.println("compress file:" + inputFiles[i].getName());
				zip(inputFiles[i], out, base + inputFiles[i].getName());
			}
		} else {
			if (base.length() > 0) {
				out.putNextEntry(new ZipEntry(base));
			} else {
				out.putNextEntry(new ZipEntry(inputFile.getName()));
			}
			FileInputStream in = new FileInputStream(inputFile);
			try {
				int c;
				byte[] by = new byte[BUFFEREDSIZE];
				while ((c = in.read(by)) != -1) {
					out.write(by, 0, c);
				}
			} catch (IOException e) {
				throw e;
			} finally {
				in.close();
			}
		}
	}

	// public static void main(String[] args) {
	// Decompression decompression = new Decompression();
	// try {
	// decompression.unzipFile("c:/test.zip");
	// decompression.unzip("c:/test.zip", "c:/test22");
	// decompression.zip("c:/test22", "c:/test222.zip");
	// } catch (Exception e) {
	// // TODO Auto-generated catch block
	// e.printStackTrace();
	// }
	// }
}