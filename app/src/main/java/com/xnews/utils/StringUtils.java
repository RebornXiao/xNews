package com.xnews.utils;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.HanyuPinyinVCharType;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by 辰 on 2015-07-17.
 */
public class StringUtils {
    private static final String TAG = StringUtils.class.getSimpleName();
    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("MM-dd HH:mm");
    private static HanyuPinyinOutputFormat formater;

    /**
     * 将服务器返回的从1970 年 1 月 1 日的毫秒数转为指定显示形式
     *
     * @param millionSecond
     * @return
     */
    public static String formatDateStr(String millionSecond) {
        if (formater == null) {
            formater = new HanyuPinyinOutputFormat();
            formater.setToneType(HanyuPinyinToneType.WITHOUT_TONE); // 不要音标
            formater.setVCharType(HanyuPinyinVCharType.WITH_V);
            formater.setCaseType(HanyuPinyinCaseType.LOWERCASE);
        }
        try {
            String tmp = DATE_FORMAT.format(new Date(Long.parseLong(millionSecond)));
            return tmp;
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return "";
        }
    }

    /**
     * 输入 pinyinFormat("吴桂发") 输出 wu吴 gui桂 fa发
     *
     * @param hanziStr
     * @return
     * @see //http://blog.csdn.net/jimzhai/article/details/7530299
     * @see //http://blog.csdn.net/hfhwfw/article/details/6030816
     */
    public static String pinyinFormat(String hanziStr) {
        if (formater == null) {
            formater = new HanyuPinyinOutputFormat();
            formater.setToneType(HanyuPinyinToneType.WITHOUT_TONE); // 不要音标
            formater.setVCharType(HanyuPinyinVCharType.WITH_V);
            formater.setCaseType(HanyuPinyinCaseType.LOWERCASE);
        }
        try {
            StringBuilder sb = new StringBuilder();
            char[] charArray = hanziStr.toCharArray();
            for (char ch : charArray) {
                if (Character.toString(ch).matches("[\\u4E00-\\u9FA5]+")) {
                    String[] strArray = PinyinHelper.toHanyuPinyinStringArray(ch, formater);
                    sb.append(strArray[0]);
                    sb.append(ch);
                    sb.append(" ");
                } else {
                    sb.append(ch);
                }
            }
            return sb.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    public static boolean isEmpty(String str) {
        if (str == null || "".equals(str)) {
            return true;
        }
        return false;
    }

    /**
     * 是否是浮点数
     *
     * @param str
     * @return ^\d+\.\d+$
     */
    public static boolean isFloat(String str) {
        if (str == null || str.trim().equals("") || str.endsWith(".")) {
            return false;
        }
        Pattern pattern = Pattern.compile("^\\d+\\.\\d+$");
        Matcher isNum = pattern.matcher(str);
        if (!isNum.matches()) {
            return false;
        }
        return true;
    }

    /**
     * 是否是整数
     *
     * @param str
     * @return "[0-9]*"
     */
    public static boolean isInt(String str) {
        if (str == null || str.trim().equals("")) {
            return false;
        }
        Pattern pattern = Pattern.compile("[0-9]*");
        Matcher isNum = pattern.matcher(str);
        if (!isNum.matches()) {
            return false;
        }
        return true;
    }

    /**
     * 是否是数字(包括小数,正负)
     *
     * @param str
     * @return "[0-9]*"
     */
    public static boolean isIntOrFloat(String str) {
        if (str == null || str.trim().equals("") || str.endsWith(".")) {
            return false;
        }
        Pattern pattern = Pattern.compile("^[-+]?\\d*\\.?\\d*$");
        Matcher isNum = pattern.matcher(str);
        if (!isNum.matches()) {
            return false;
        }
        return true;
    }

    /**
     * 是否需要格式化
     *
     * @param str
     * @return "[0-9]*"
     */
    public static boolean isNeedFormat(int endLen, String str) {
        if (str == null || str.trim().equals("")) {
            return false;
        }
        if (str.contains(".")) {
            return !((str.length() - str.lastIndexOf(".")) <= (endLen + 1));
        } else if (!str.contains(".")) {
            return false;
        }
        return true;
    }


}
