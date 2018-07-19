package com.aipipixia.file;

import org.junit.Test;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

/**
 * @author 郭垚辉
 * @date 2018/7/19
 */
public class FileUtilsTest {

    @Test
    public void listRecursiveFile() {
        List<Path> list = FileUtils.listRecursiveFile(Paths.get("D:\\dev_env\\maven\\repo\\antlr\\antlr"));
        System.out.println(list);
    }

}