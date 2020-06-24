package com.lcn29.kit.util;

/**
 * <pre>
 * a util for Java's String
 * </pre>
 *
 * @author LCN
 * @date 2019-12-21 00:05
 */
public class StringUtil {

    /**
     * remove all the white space in the string
     * @param str
     * @return
     */
    public static String trimAllWhitespace(String str) {
        if (!isNotEmpty(str)) {
            return str;
        }
        return str.replace(" ", "");
    }

    /**
     * str == null or str.length == 0
     * @param str
     * @return
     */
    public static boolean isEmpty(String str) {
        if (str == null || str.isEmpty()) {
            return true;
        }
        return false;
    }

    /**
     * str != null and str.length > 0
     * @param str
     * @return
     */
    public static boolean isNotEmpty(String str) {
        return !isEmpty(str);
    }

    /**
     * str == null or str.trim().length == 0
     * @param str
     * @return
     */
    public static boolean isBlank(String str) {
        if (str == null || str.isBlank()) {
            return true;
        }
        return false;
    }

    /**
     * str != null and str.length > 0 and str.trim().length > 0
     * @param str
     * @return
     */
    public static boolean isNotBlack(String str) {
        return !isBlank(str);
    }

}
