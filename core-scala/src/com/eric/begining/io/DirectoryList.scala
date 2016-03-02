package com.eric.begining.io

import java.io.File
/**
 *列出文件夹下的所有文件
 * Created by Eric on 2016/2/18.
 */
object DirectoryList {
  def main(args: Array[String]): Unit = {
    val file = new File("D:\\work\\svn\\product_new_code\\TrueCloudServer");
    for (dir <- subDirs(file)) println(dir)
  }

  //列出所有的子目录
  def subDirs(rootDir: File): Iterator[File] = {
    var childDir = rootDir.listFiles.filter(_.isDirectory);
    childDir.toIterator ++ childDir.toIterator.flatMap(subDirs _)
  }
}
