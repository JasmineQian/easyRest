package com.example.demo.Core.imp;

import com.example.demo.Core.Abstract.abstractsCacheOrAuto;
import com.example.demo.Core.Cache.RequestCache;
import com.example.demo.Core.Cache.ResponseCache;
import com.example.demo.Core.Interface.autoGenerationVaule;
import com.example.demo.Enum.RequestCacheEnum;
import com.example.demo.Enum.ResponseCacheEnum;
import org.slf4j.Logger;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DataCacheOrAuto extends abstractsCacheOrAuto {

  private static Logger log;

  /** @param autoGenerationValue 自动生成函数接口实现类注入 */
  public void setAutoGenerationValue(autoGenerationVaule<String> autoGenerationValue) {
    this.autoGenerationValue = autoGenerationValue;
  }

  /** 自动生成函数接口 */
  private autoGenerationVaule<String> autoGenerationValue = new AutoGenValue();

  /**
   * @param keyword 函数关键字驱动例如uuid表示我要获取一个uuid
   * @return 获取关键字对应的函数取值
   */
  @Override
  protected String AutoSetValue(String keyword) {
    return autoGenerationValue.GenerationValue(keyword);
  }

  /**
   * @param type 搜索的数据类型 可以为空 为空就是模糊搜索
   * @param keyword 搜索关键字
   * @return 缓存搜索结果
   */
  @Override
  protected String SearchDataFromRequestCache(String type, String keyword) {
    boolean isCacheKey = false;
    RequestCacheEnum requestCacheEnum = null;
    if (type == null) {
      // 实现模糊查找逻辑
      return getCacheResultByTargetKey(keyword);
    } else {
      RequestCacheEnum[] values = RequestCacheEnum.values();
      for (RequestCacheEnum em : values)
        if (em.name().equals(type)) {
          requestCacheEnum = em;
          isCacheKey = true;
          break;
        }
      if (!isCacheKey) {
        log.error("填写的关键字={} 不规范系统已根据key={}在进行全局模糊查询", type, keyword);
      }
      // 如果符合规范就精准查询 没有就模糊查询
      return isCacheKey
          ? switchSelectRequestCache(requestCacheEnum, keyword)
          : getCacheResultByTargetKey(keyword);
    }
  }

  /**
   * @param type 搜索的数据类型 可以为空 为空就是模糊搜索
   * @param keyword 缓存目标钥匙
   * @return 缓存搜索结果
   */
  protected String SearchDataFromResponseCache(String type, String keyword) {
    boolean isCacheKey = false;
    ResponseCacheEnum responseCacheEnum = null;
    if (type == null) {
      // 模糊查询
      return getCacheResultByTargetKey(keyword);
    } else {
      ResponseCacheEnum[] values = ResponseCacheEnum.values();
      for (ResponseCacheEnum em : values) {
        if (em.name().equals(type)) {
          isCacheKey = true;
          responseCacheEnum = em;
          break;
        }
      }

      if (!isCacheKey) {
        log.error("填写的关键字={} 不规范系统会根据key={}在进行全局模糊查询", type, keyword);
      }

      return isCacheKey ? switchSelectResponseCache(responseCacheEnum, keyword) : null;
    }
  }

  /**
   * @param responseCacheEnum 枚举类型
   * @param targetKey 目标key
   * @return 缓存key的值
   */
  private String switchSelectResponseCache(ResponseCacheEnum responseCacheEnum, String targetKey) {
    String result = null;
    switch (responseCacheEnum) {
      case contentype:
        result = ResponseCache.ResponseCacheContentType();
        break;
      case headers:
        result = ResponseCache.responseCacheHeaders(targetKey);
        break;
      case status:
        result = ResponseCache.responseCacheStatus();
        break;
      case cookies:
        result = ResponseCache.responseCacheCookies(targetKey);
        break;
      case body:
        result = ResponseCache.responseCacheBody(targetKey);
        break;
      case time:
        result = ResponseCache.responseCacheTime();
        break;
    }
    return result;
  }

  /**
   * @param requestCacheEnum 枚举类型
   * @param targetKey 目标key
   * @return 缓存key的值
   */
  private String switchSelectRequestCache(RequestCacheEnum requestCacheEnum, String targetKey) {

    String result = null;
    switch (requestCacheEnum) {
      case method:
        result = RequestCache.RequestCacheMethod();
        break;
      case url:
        result = RequestCache.requestCacheUrl();
        break;
      case params:
        result = RequestCache.requestCacheParams(targetKey);
        break;
      case headers:
        result = RequestCache.requestCacheHeaders(targetKey);
        break;
      case cookies:
        result = RequestCache.requestCacheCookies(targetKey);
        break;
      case body:
        result = RequestCache.requestCacheBody(targetKey);
        break;
    }

    return result;
  }

  /**
   * 实现思路 优先找response中的值如果 找到了就返回 没找到 继续从request域中找
   *
   * @param targetKey 模糊查询缓存key的值
   * @return 缓存key的值
   */
  private String getCacheResultByTargetKey(String targetKey) {
    List<ResponseCacheEnum> responseCacheEnums =
        new ArrayList<>(Arrays.asList(ResponseCacheEnum.values()));
    if (!targetKey.equals(ResponseCacheEnum.time.name())) {
      responseCacheEnums.remove(ResponseCacheEnum.time);
    }

    if (!targetKey.equals(ResponseCacheEnum.status.name())) {
      responseCacheEnums.remove(ResponseCacheEnum.status);
    }

    if (!targetKey.equals(ResponseCacheEnum.contentype.name())) {
      responseCacheEnums.remove(ResponseCacheEnum.contentype);
    }

    for (ResponseCacheEnum responseCacheEnum : responseCacheEnums) {
      String result = switchSelectResponseCache(responseCacheEnum, targetKey);
      if (result != null) {
        return result;
      }
    }

    List<RequestCacheEnum> requestCacheEnums =
        new ArrayList<>(Arrays.asList(RequestCacheEnum.values()));

    if (!targetKey.equals(RequestCacheEnum.method.name())) {
      requestCacheEnums.remove(RequestCacheEnum.method);
    }
    if (!targetKey.equals(RequestCacheEnum.url.name())) {
      requestCacheEnums.remove(RequestCacheEnum.url);
    }
    for (RequestCacheEnum requestCacheEnum : requestCacheEnums) {
      String result = switchSelectRequestCache(requestCacheEnum, targetKey);
      if (result != null) {
        return result;
      }
    }

    return null;
  }
}
