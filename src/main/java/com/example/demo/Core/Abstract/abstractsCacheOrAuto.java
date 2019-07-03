package com.example.demo.Core.Abstract;

import org.slf4j.Logger;
import org.springframework.stereotype.Service;

@SuppressWarnings("ALL")
@Service
public abstract class abstractsCacheOrAuto extends abstractCacheDatasource {

  private static Logger log;
  @Override
  protected String CacheOrAutoSetValue(String keywords) {
    String[] split = keywords.split("\\.");
    String result;
    if (split.length > 3) {
      throw new RuntimeException("引用参数错误请按照request.cookies.key");
    }
    if (split.length == 3) {

      // domainName request or response
      String domainName = split[0];
      // type is ResponseCacheEnum or RequestCacheEnum
      String type = split[1];
      String keyword = split[2];
      // 精确搜索模式 启动

      return getCacheFromStringPath(domainName, type, keyword);
    }
    if (split.length == 1) {
      // 只有一个关键字的场景 优先找 自动匹配函数中有没有匹配的函数
      // 如果没有函数对应匹配 那么进行转化搜索
      result = AutoSetValue(keywords);
      return result != null ? result : searchCacheFromTypeKeywordOrKeyword(null, keywords);
    } else {
      // type is ResponseCacheEnum or RequestCacheEnum
      String type = split[0];
      String keyword = split[1];
      return searchCacheFromTypeKeywordOrKeyword(type, keyword);
    }
  }

  /**
   * 精确搜索模式 精确搜索 模式 和searchCacheFromTypeKeywordOrKeyword 场景不一样、 精确搜索是用户 已知域的搜索 也就是说只需要搜索一个域 就可以
   * 而不需要搜索更多的域
   *
   * @param domainName 域名称
   * @param type 值类型
   * @param keyword 关键字
   * @return 缓存值
   */
  private String getCacheFromStringPath(String domainName, String type, String keyword) {
    if (domainName.equals("request")) {
      String RequestCache = SearchDataFromRequestCache(type, keyword);
      if (RequestCache != null) {
        return RequestCache;
      } else {
        throw new RuntimeException("无法从Request缓存中找到类型是" + type + "关键字是" + keyword + "的数据请核对！");
      }
    }
    if (domainName.equals("response")) {
      String ResponseCache = SearchDataFromResponseCache(type, keyword);

      if (ResponseCache != null) {
        return ResponseCache;
      } else {
        throw new RuntimeException("无法从Response缓存中找到类型是" + type + "关键字是" + keyword + "的数据请核对！");
      }
    } else {
      throw new RuntimeException("getCacheFromStringPath表达式解析失败请按照规则填写 必须是request or response ！！");
    }
  }

  /**
   * 搜索数据根据数据类型 或者根据关键字 仅仅根据 类型名称和关键字搜索 未知域名的情况需要搜索2个域，优先匹配Response域数据
   *
   * @param keyword 关键字
   * @return 命中缓存数据
   */
  private String searchCacheFromTypeKeywordOrKeyword(String type, String keyword) {
    String result;

    result = SearchDataFromResponseCache(type, keyword);
    if (result != null) {
      return result;
    } else {
      result = SearchDataFromRequestCache(type, keyword);
      if (result != null) {
        return result;
      } else {
        log.error("缓存中找不到对应的值无法进行匹配！！{}", keyword);
        throw new RuntimeException("缓存中找不到对应的值无法进行匹配！！");
      }
    }
  }

  /**
   * 从Request 域中搜索 数据
   *
   * @param type 搜索的数据类型 可以为空 为空就是模糊搜索
   * @param keyword 搜索关键字
   * @return 缓存搜索结果
   */
  protected abstract String SearchDataFromRequestCache(String type, String keyword);

  /**
   * 从Response 域中搜索数据
   *
   * @param type 搜索的数据类型 可以为空 为空就是模糊搜索
   * @param keyword 缓存目标钥匙
   * @return 命中目标数据
   */
  protected abstract String SearchDataFromResponseCache(String type, String keyword);

  /**
   * 从支持的函数中获取数据
   *
   * @param keyword 关键字
   * @return 函数数据
   */
  protected abstract String AutoSetValue(String keyword);
}
