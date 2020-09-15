package com.hu4java.util;

/**
 * @author chenzhenhu
 */
public class StrUtils {

    private static final char UNDERLINE = '_';

    /**
     * 驼峰转下划线
     * @param string        字符串
     * @param toUpperCase   是否大写
     * @return              下划线字符串
     */
    public static String camelToUnderline(String string, boolean toUpperCase) {
        if (null == string || "".equals(string.trim())) {
            return "";
        }
        int length = string.length();
        StringBuilder sb = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            char c = string.charAt(i);
            if (Character.isUpperCase(c)) {
                sb.append(UNDERLINE);
            }
            if (toUpperCase) {
                sb.append(Character.toUpperCase(c));
            } else {
                sb.append(Character.toLowerCase(c));
            }
        }
        return sb.toString();
    }

    /**
     * 下划线转驼峰
     * @param string    字符串
     * @return          驼峰字符串
     */
    public static String underlineToCamel(String string, boolean upperCamelCase) {
        if (null == string || "".equals(string.trim())) {
            return "";
        }
        int length = string.length();
        StringBuilder sb = new StringBuilder(length);
        // "_" 后转大写标志,默认字符前面没有"_"
        boolean flag = false;
        for (int i = 0; i < length; i++) {
            char c = string.charAt(i);
            if (c == UNDERLINE) {
                flag = true;
                continue;
            }
            if (flag) {
                //表示当前字符前面是"_" ,当前字符转大写
                sb.append(Character.toUpperCase(string.charAt(i)));
                //重置标识
                flag = false;
            } else {
                if (i == 0 && upperCamelCase) {
                    sb.append(Character.toUpperCase(string.charAt(i)));
                } else {
                    sb.append(Character.toLowerCase(string.charAt(i)));
                }
            }
        }
        return sb.toString();
    }
}
