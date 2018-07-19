package com.aipipixia.file;

import java.io.IOException;
import java.nio.file.FileVisitOption;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;
import java.util.Set;
import lombok.extern.slf4j.Slf4j;

/**
 * @author 郭垚辉
 * @date 2018/7/19
 */
@Slf4j
public class FileUtils {

  /**
   * 遍历指定目录下的所有的文件的集合
   *
   * @param path 需要进行遍历的目录
   * @return 文件的集合
   */
  public static final List<Path> listRecursiveFile(Path path) {
    return listRecursiveFile(path, Integer.MAX_VALUE, EnumSet.noneOf(FileVisitOption.class));
  }

  /**
   * 遍历指定目录下的所有的文件的集合
   *
   * @param path 需要遍历的目录
   * @param maxDepth 遍历层级
   * @param options 遍历操作
   * @return 所有文件的集合
   */
  public static final List<Path> listRecursiveFile(Path path, int maxDepth, Set<FileVisitOption> options) {
    if (Files.notExists(path)) {
      return null;
    }
    List<Path> list = new ArrayList<>();
    if (options != null) {
      options = EnumSet.noneOf(FileVisitOption.class);
    }
    if (maxDepth < 0) {
      maxDepth = Integer.MAX_VALUE;
    }
    try {
      Files.walkFileTree(path, options, maxDepth, new SimpleFileVisitor<Path>() {
        @Override
        public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
          if (Files.isRegularFile(file)) {
            list.add(file);
          }
          return super.visitFile(file, attrs);
        }
      });
      if (list.size() > 0) {
        return list;
      }
    } catch (IOException e) {
      throw new RuntimeException("when your recursive the path 【" + path + "】 occur some error is ", e);
    }
    return null;
  }
}
