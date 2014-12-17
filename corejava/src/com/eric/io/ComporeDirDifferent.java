package com.eric.io;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ComporeDirDifferent {
	public static final String PCE_VERSION_CONTROL = "@(#) $RCSfile: $, $Revision: $, $Date: $";
	static final int BUFFER = 2048;

	public static void main(String[] args) throws Exception {
		String zipPath = "F:/Eric/tools_work/test/test.zip";
		String unZipDest = "D:/devkit/1217/components2";
		String source = "D:/BuildDir/PCE_Ruegen_14/Projects/PaymentServer/Components";
		Decompression decompression = new Decompression();
		// decompression.unzipFile(unzip);
		// deleteDir(source, dest);
		// copyDir(source, dest);
		// decompression.zip("F:/Eric/tools", unzip);
		// copyPropertyFile();
	}

	private static void copyDir(String dir1, String dir2) throws IOException {
		File[] file = (new File(dir1)).listFiles();
		for (int i = 0; i < file.length; i++) {
			if (file[i].isFile()) {
				// 复制文件
				copyFile(file[i], new File(dir1 + file[i].getName()));
			}
			if (file[i].isDirectory()) {
				// 复制目录
				String sourceDir = dir1 + "/" + file[i].getName();
				String targetDir = dir2 + "/" + file[i].getName();
				System.out.println("source:" + sourceDir);
				System.out.println(" to:" + targetDir);
				copyDirectiory(sourceDir, targetDir);
			}
		}
	}

	private static void deleteDir(String dir1, String dir2) {
		File f1 = new File(dir1);
		File f2 = new File(dir2);
		List<String> list2 = new ArrayList<String>();
		Collections.addAll(list2, f2.list());
		ComporeDirDifferent cd = new ComporeDirDifferent();
		for (String f : f1.list()) {
			if (list2.contains(f)) {

				String deletePath = dir2 + "/" + f;
				System.out.println("deleting:" + deletePath);
				cd.deleteFile(new File(deletePath));
				list2.remove(f);
			}

		}
		System.out.println(">>>>>>>>>>>>>>different dir");
		for (String str : list2) {
			System.out.println(str);
		}
		System.out.println(">>>>>>>>>>>>>>different dir");
	}

	private void deleteFile(File file) {
		if (file.exists()) { // 判断文件是否存在
			if (file.isFile()) { // 判断是否是文件
				file.delete(); // delete()方法 你应该知道 是删除的意思;
			} else if (file.isDirectory()) { // 否则如果它是一个目录
				File files[] = file.listFiles(); // 声明目录下所有的文件 files[];
				for (int i = 0; i < files.length; i++) { // 遍历目录下所有的文件
					this.deleteFile(files[i]); // 把每个文件 用这个方法进行迭代
				}
			}
			file.delete();
		} else {
			System.out.println("所删除的文件不存在！" + '\n');
		}
	}

	// 复制文件
	public static void copyFile(File sourceFile, File targetFile)
			throws IOException {
		// 新建文件输入流并对它进行缓冲
		FileInputStream input = new FileInputStream(sourceFile);
		BufferedInputStream inBuff = new BufferedInputStream(input);

		// 新建文件输出流并对它进行缓冲
		FileOutputStream output = new FileOutputStream(targetFile);
		BufferedOutputStream outBuff = new BufferedOutputStream(output);

		// 缓冲数组
		byte[] b = new byte[1024 * 5];
		int len;
		while ((len = inBuff.read(b)) != -1) {
			outBuff.write(b, 0, len);
		}
		// 刷新此缓冲的输出流
		outBuff.flush();

		// 关闭流
		inBuff.close();
		outBuff.close();
		output.close();
		input.close();
	}

	// 复制文件夹
	public static void copyDirectiory(String sourceDir, String targetDir)
			throws IOException {
		// 新建目标目录
		(new File(targetDir)).mkdirs();
		// 获取源文件夹当前下的文件或目录
		File[] file = (new File(sourceDir)).listFiles();
		if (file != null) {

			for (int i = 0; i < file.length; i++) {
				if (file[i].isFile()) {
					// 源文件
					File sourceFile = file[i];
					// 目标文件
					File targetFile = new File(
							new File(targetDir).getAbsolutePath()
									+ File.separator + file[i].getName());
					copyFile(sourceFile, targetFile);
				}
				if (file[i].isDirectory()) {
					// 准备复制的源文件夹
					String dir1 = sourceDir + "/" + file[i].getName();
					// 准备复制的目标文件夹
					String dir2 = targetDir + "/" + file[i].getName();
					copyDirectiory(dir1, dir2);
				}
			}
		}
	}

}

/*
 * 
 * History:
 * 
 * 
 * 
 * $Log: $
 */
