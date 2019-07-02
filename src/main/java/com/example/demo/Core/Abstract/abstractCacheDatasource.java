package com.example.demo.Core.Abstract;

import com.example.demo.Core.Interface.CacheDataSource;
import com.example.demo.Util.StringReplaceHelper;

public abstract class abstractCacheDatasource implements CacheDataSource<String> {

  private static final String placeholderPrefix = "{";
  private static final String placeholderSuffix = "}";

  @Override
  public String getData(String data) {
    if (data != null && data.contains("$")) {
      return this.getCacheValue(data);
    } else {
      return data;
    }
  }

  /**
   * @param str ${}字符串
   * @return 获取缓存数据 或者自动生成的数据
   */
  private String getCacheValue(String str) {
    /** 线程安全使用StringBuilder 速度更快 */
    StringBuilder stringBuilder = new StringBuilder();
    if (str.length() > 0) {
      String[] split = str.split("\\$");
      for (String src : split) {
        if (src.contains(placeholderPrefix) && src.contains(placeholderSuffix)) {
          // 获取{}中的引用字符串
          // {test.hello}36db68f5","pho
          String keywords = StringReplaceHelper.getKeywords(src);
          String CacheValue = CacheOrAutoSetValue(keywords);
          if (CacheValue != null) {
            // 如果缓存中存在数据 就进行替换数据操作
            CacheValue = replaceStringtrim(src, CacheValue, keywords);
            stringBuilder.append(CacheValue);
          }
        } else {
          stringBuilder.append(src);
        }
      }
    }
    return stringBuilder.toString();
  }

  /**
   * @param src 原始数据
   * @param cacheValue 被替换的数据
   * @param keywords 需要替换的关键字
   * @return 被替换的数据
   */
  private String replaceStringtrim(String src, String cacheValue, String keywords) {

    return src.replace(placeholderPrefix + keywords + placeholderSuffix, cacheValue);
  }

  protected abstract String CacheOrAutoSetValue(String keywords);
}
