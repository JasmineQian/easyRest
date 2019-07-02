package com.example.demo.Core.Abstract;

import cn.hutool.core.util.StrUtil;
import com.example.demo.bean.ExecutionData;
import io.restassured.response.Response;

/** 抽象执行集成接口聚合抽象类 */
public abstract class AbstractRestAssuredExecute extends AbstractExecute<Response, ExecutionData> {

  // 总执行接口聚合其他接口功能
  public void execution(ExecutionData executionData) {
    // 尝试解析分析参数中是否需要进行函数值替换或缓存替换需求
    executionData = tryGetCacheOrAutoSetValue(executionData);

    // 初始化定制化环境参数
    this.initEnvironment(executionData);
    // 初始化配置
    this.InitConfiguration();

    // 执行返回请求
    Response response = this.executeResponse(executionData);
    // 执行验证
    this.ExecuteVerification(response, executionData);
  }

  /**
   * 尝试解析分析参数中是否需要进行函数值替换或缓存替换需求
   *
   * @param executionData 数据驱动对象
   * @return 数据驱动对象
   */
  protected abstract ExecutionData tryGetCacheOrAutoSetValue(ExecutionData executionData);

  /**
   * 初始化环境此处主要是解决session cookie 是否需要清除
   *
   * @param executionData
   */
  protected abstract void initEnvironment(ExecutionData executionData);

  /**
   * @param executionData 数据驱动对象
   * @return Response 执行结果对象
   */
  protected Response executeResponse(ExecutionData executionData) {
    String url = executionData.getUrl();
    if (StrUtil.isEmpty(url)) {
      throw new RuntimeException("Url不能为空");
    } else if (!StrUtil.isEmpty(executionData.getMethod())) {
      return this.execute(executionData);
    } else {
      throw new RuntimeException("Method参数不能为空");
    }
  }

  /**
   * 执行验证过程
   *
   * @param response 执行结果对象
   * @param executionData 数据驱动对象
   */
  protected void ExecuteVerification(Response response, ExecutionData executionData) {
    RestAssuredExecuteVerification(response, executionData);
  }

  /** 初始化配置 只会执行一次 */
  protected abstract void InitConfiguration();

  // 执行请求并返回Response对象
  protected abstract Response execute(ExecutionData executionData);

  /**
   * 执行验证过程 抽象方法
   *
   * @param response 执行结果对象
   * @param executionData 数据驱动对象
   */
  protected abstract void RestAssuredExecuteVerification(
      Response response, ExecutionData executionData);
}
