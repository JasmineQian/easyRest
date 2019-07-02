package com.example.demo.Util;

public class StringReplaceHelper {
  /**
   * 获取{中的值}
   *
   * @param valueSeparator
   * @return {中的值}
   */
  public static String getKeywords(String valueSeparator) {
    String reg = "(?<=(?<!\\\\)\\{)(.*?)(?=(?<!\\\\)\\})";
    RegExp re = new RegExp();
    return re.findString(reg, valueSeparator);
  }

  /**
   * @param valueSeparator 源字符串
   * @param start 开始标志
   * @param end 结束标志
   * @return 获取第一个开始和结束的字符
   */
  public static String getKeywords(String valueSeparator, String start, String end) {
    String reg = getRegString(start, end);
    RegExp re = new RegExp();
    return re.findString(reg, valueSeparator);
  }

  private static String getRegString(String start, String end) {
    return "(?<=(?<!\\\\)\\" + start + ")(.*?)(?=(?<!\\\\)\\" + end + ")";
  }
}
