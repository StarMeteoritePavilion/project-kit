package com.lcn29.kit.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * <pre>
 *
 * </pre>
 *
 * @author LCN
 * @date 2020-01-31 13:39
 */
public class FileUtil {

    /**
     * determine if the file or path exists
     * @param path
     * @return
     */
    public static boolean fileOrPathExist(String path) {
        return new File(path).exists();
    }

    /**
     * create folder
     * @param foldPath
     * @return
     */
    public static boolean createFolder(String foldPath) {

        if (StringUtil.isBlank(foldPath)) {
            return false;
        }
        if (fileOrPathExist(foldPath)) {
            return true;
        }
        String[] splitPath = OSUtil.isWindow() ? foldPath.split("\\\\") : foldPath.split("/");

        File folder;
        StringBuilder pathBuilder = new StringBuilder();
        for (String path : splitPath) {
            pathBuilder.append(path).append(File.separator);
            folder = new File(pathBuilder.toString());
            if (!folder.exists() && !folder.mkdir()) {
                return false;
            }
        }
        return true;
    }

    /**
     * create file
     * @param filePath
     * @return
     */
    public static boolean createFile(String filePath) throws IOException {
        if (StringUtil.isBlank(filePath) || filePath.endsWith(File.separator)) {
            return false;
        }
        if (fileOrPathExist(filePath)) {
            return true;
        }
        String fileName = filePath.substring(filePath.lastIndexOf(File.separator) + 1);
        System.out.println(fileName);
        // the fullPath = foldPath + file.separator + fileName
        String foldPath = filePath.substring(0, filePath.length() - File.separator.length() - fileName.length());

        // the isBlank() is used to handler base path in linux, such as /test.txt, the foldPath is blank
        if (!foldPath.isBlank() && !createFolder(foldPath)) {
            return false;
        }
        File file = new File(filePath);
        return file.createNewFile();
    }

    /************************************************************************************/

    /**
     * TODO
     */

    /**
     * 复制文件或文件夹
     *
     * @param srcPath
     * @param destDir
     *            目标文件所在的目录
     * @return
     */
    public static boolean copyGeneralFile(String srcPath, String destDir) {
        boolean flag = false;
        File file = new File(srcPath);
        if (!file.exists()) {
            System.out.println("源文件或源文件夹不存在!");
            return false;
        }
        // 源文件
        if (file.isFile()) {
            System.out.println("下面进行文件复制!");
            flag = copyFile(srcPath, destDir);
        } else if (file.isDirectory()) {
            System.out.println("下面进行文件夹复制!");
            flag = copyDirectory(srcPath, destDir);
        }

        return flag;
    }

    /**
     * 复制文件
     *
     * @param srcPath
     *            源文件绝对路径
     * @param destDir
     *            目标文件所在目录
     * @return boolean
     */
    private static boolean copyFile(String srcPath, String destDir) {
        boolean flag = false;

        File srcFile = new File(srcPath);
        // 源文件不存在
        if (!srcFile.exists()) {
            System.out.println("源文件不存在");
            return false;
        }
        // 获取待复制文件的文件名
        String fileName = srcPath
            .substring(srcPath.lastIndexOf(File.separator));
        String destPath = destDir + fileName;
        // 源文件路径和目标文件路径重复
        if (destPath.equals(srcPath)) {
            System.out.println("源文件路径和目标文件路径重复!");
            return false;
        }
        File destFile = new File(destPath);
        // 该路径下已经有一个同名文件
        if (destFile.exists() && destFile.isFile()) {
            System.out.println("目标目录下已有同名文件!");
            return false;
        }

        File destFileDir = new File(destDir);
        destFileDir.mkdirs();
        try {
            FileInputStream fis = new FileInputStream(srcPath);
            FileOutputStream fos = new FileOutputStream(destFile);
            byte[] buf = new byte[1024];
            int c;
            while ((c = fis.read(buf)) != -1) {
                fos.write(buf, 0, c);
            }
            fis.close();
            fos.close();

            flag = true;
        } catch (IOException e) {
            //
        }

        if (flag) {
            System.out.println("复制文件成功!");
        }

        return flag;
    }

    /**
     *
     * @param srcPath
     *            源文件夹路径
     * @param destDir
     *            目标文件夹所在目录
     * @return
     */
    private static boolean copyDirectory(String srcPath, String destDir) {
        System.out.println("复制文件夹开始!");
        boolean flag = false;

        File srcFile = new File(srcPath);
        // 源文件夹不存在
        if (!srcFile.exists()) {
            System.out.println("源文件夹不存在");
            return false;
        }
        // 获得待复制的文件夹的名字，比如待复制的文件夹为"E://dir"则获取的名字为"dir"
        String dirName = getDirName(srcPath);
        // 目标文件夹的完整路径
        String destPath = destDir + File.separator + dirName;
        // System.out.println("目标文件夹的完整路径为：" + destPath);

        if (destPath.equals(srcPath)) {
            System.out.println("目标文件夹与源文件夹重复");
            return false;
        }
        File destDirFile = new File(destPath);
        // 目标位置有一个同名文件夹
        if (destDirFile.exists()) {
            System.out.println("目标位置已有同名文件夹!");
            return false;
        }
        // 生成目录
        destDirFile.mkdirs();
        // 获取源文件夹下的子文件和子文件夹
        File[] fileList = srcFile.listFiles();
        // 如果源文件夹为空目录则直接设置flag为true，这一步非常隐蔽，debug了很久
        if (fileList.length == 0) {
            flag = true;
        } else {
            for (File temp : fileList) {
                if (temp.isFile()) {
                    // 文件
                    flag = copyFile(temp.getAbsolutePath(), destPath);
                } else if (temp.isDirectory()) {
                    // 文件夹
                    flag = copyDirectory(temp.getAbsolutePath(), destPath);
                }
                if (!flag) {
                    break;
                }
            }
        }

        if (flag) {
            System.out.println("复制文件夹成功!");
        }

        return flag;
    }

    /**
     * 获取待复制文件夹的文件夹名
     *
     * @param dir
     * @return String
     */
    private static String getDirName(String dir) {
        // 如果文件夹路径以"//"结尾，则先去除末尾的"//"
        if (dir.endsWith(File.separator)) {
            dir = dir.substring(0, dir.lastIndexOf(File.separator));
        }
        return dir.substring(dir.lastIndexOf(File.separator) + 1);
    }

    /**
     * 删除文件或文件夹
     *
     * @param path
     *            待删除的文件的绝对路径
     * @return boolean
     */
    public static boolean deleteGeneralFile(String path) {
        boolean flag = false;

        File file = new File(path);
        // 文件不存在
        if (!file.exists()) {
            System.out.println("要删除的文件不存在！");
        }
        // 如果是目录，则单独处理
        if (file.isDirectory()) {
            flag = deleteDirectory(file.getAbsolutePath());
        } else if (file.isFile()) {
            flag = deleteFile(file);
        }

        if (flag) {
            System.out.println("删除文件或文件夹成功!");
        }

        return flag;
    }

    /**
     * 删除文件
     *
     * @param file
     * @return boolean
     */
    private static boolean deleteFile(File file) {
        return file.delete();
    }

    /**
     * 删除目录及其下面的所有子文件和子文件夹，注意一个目录下如果还有其他文件或文件夹
     * 则直接调用delete方法是不行的，必须待其子文件和子文件夹完全删除了才能够调用delete
     *
     * @param path
     *            path为该目录的路径
     */
    private static boolean deleteDirectory(String path) {
        boolean flag = true;
        File dirFile = new File(path);
        if (!dirFile.isDirectory()) {
            return flag;
        }
        File[] files = dirFile.listFiles();
        // 删除该文件夹下的文件和文件夹
        for (File file : files) {
            // Delete file.
            if (file.isFile()) {
                flag = deleteFile(file);
            } else if (file.isDirectory()) {
                // Delete folder
                flag = deleteDirectory(file.getAbsolutePath());
            }
            // 只要有一个失败就立刻不再继续
            if (!flag) {
                break;
            }
        }
        // 删除空目录
        flag = dirFile.delete();
        return flag;
    }

    /**
     * 由上面方法延伸出剪切方法：复制+删除
     *
     * @param destDir
     *            同上
     */
    public static boolean cutGeneralFile(String srcPath, String destDir) {
        if (!copyGeneralFile(srcPath, destDir)) {
            System.out.println("复制失败导致剪切失败!");
            return false;
        }
        if (!deleteGeneralFile(srcPath)) {
            System.out.println("删除源文件(文件夹)失败导致剪切失败!");
            return false;
        }

        System.out.println("剪切成功!");
        return true;
    }

    public static void main2(String[] args) {
        copyGeneralFile("E://Assemble.txt", "E://New.txt"); // 复制文件
        copyGeneralFile("E://hello", "E://world"); // 复制文件夹
        deleteGeneralFile("E://onlinestockdb.sql"); // 删除文件
        deleteGeneralFile("E://woman"); // 删除文件夹
        cutGeneralFile("E://hello", "E://world"); // 剪切文件夹
        cutGeneralFile("E://Difficult.java", "E://Cow//"); // 剪切文件
    }
}
