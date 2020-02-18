package com.lcn29.kit.util;

import org.junit.jupiter.api.Test;

import java.io.IOException;

/**
 * <pre>
 *
 * </pre>
 *
 * @author LCN
 * @date 2020-01-31 15:10
 */
public class FileUtilTest {

    @Test
    public void createFileFoldTest() {
        String foldPath = "F:\\createFold";
        if (FileUtil.createFileFolder(foldPath)) {
            System.out.println("success");
        } else {
            System.out.println("fail");
        }
    }

    @Test
    public void createFile() throws IOException {
        String foldPath = "F:\\createFile";
        if (FileUtil.createFile(foldPath)) {
            System.out.println("success");
        } else {
            System.out.println("fail");
        }
    }
}
