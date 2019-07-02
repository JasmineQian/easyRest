package com.example.demo.Core.imp;

import com.example.demo.Config.EasyRestConfig;
import com.example.demo.Core.Abstract.AbstractInitialConfiguration;
import com.example.demo.Core.Filter.GlobalCacheFilter;
import com.example.demo.Core.Filter.LogFilter;
import com.example.demo.Core.Filter.RestCookieFilter;
import com.example.demo.Core.Filter.RestSessionFilter;
import io.restassured.RestAssured;
import io.restassured.config.LogConfig;
import io.restassured.config.RestAssuredConfig;
import io.restassured.filter.Filter;


import java.util.ArrayList;

/** 初始化config配置接口实现 */
public class InitialConfigurationImp extends AbstractInitialConfiguration {


  protected void GlobalBaseUrlSetting(String baseUrl) {
    RestAssured.baseURI = baseUrl;
    System.out.println("加载配置{}=+{}"+ EasyRestConfig.EASYREST_RESTASSURED_BASEURL+ EasyRestConfig.getBaseUrl());

  }

  protected void GlobalLogSetting(String isOpenLog) {

    //    声明缓存拦截器
    ArrayList<Filter> filters = new ArrayList<>();
    filters.add(new GlobalCacheFilter());
    filters.add(new LogFilter());
    filters.add(new RestSessionFilter());
    filters.add(new RestCookieFilter());
    RestAssured.filters(filters);
    if (isOpenLog.equals("true")) {
      if (RestAssured.config != null) {
        RestAssured.config =
            RestAssuredConfig.config()
                .logConfig(
                    LogConfig.logConfig().enableLoggingOfRequestAndResponseIfValidationFails());
      }

      System.out.println("加载日志配置配置{}=+{}"+ EasyRestConfig.EASYREST_RESTASSURED_LOG+ isOpenLog);
    } else {
      System.out.println("未启用日志配置请在application.properties中配置easyrest.restassured.log=true启用失败日志");

    }
  }
}
