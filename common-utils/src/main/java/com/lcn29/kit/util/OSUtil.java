package com.lcn29.kit.util;

/**
 * <pre>
 * OS Util
 * </pre>
 *
 * @author LCN
 * @date 2020-02-22 20:12
 */
public class OSUtil {

    private final static String WINDOW = "window";

    private final static String LINUX = "linux";

    private final static String MAC = "mac";

    private final static String OS_NAME = "os.name";

    public static String getOSName() {
        return System.getProperty(OS_NAME);
    }

    public static boolean isWindow() {
        return getOSName().toLowerCase().contains(WINDOW);
    }

    public static boolean isLinux() {
        return getOSName().toLowerCase().contains(LINUX);
    }

    public static boolean isMac() {
        return getOSName().toLowerCase().contains(MAC);
    }

}
