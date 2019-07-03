package com.example.demo.Core.Abstract;

import com.example.demo.Core.Interface.InterfaceExecution;
import com.example.demo.bean.ExecutionData;
import org.springframework.stereotype.Service;

/**
 * 聚合接口抽象类
 *
 * @param <T> 请求返回参数类
 * @param <E> 执行参数类型
 */

@Service
public abstract class AbstractExecute<T, E extends ExecutionData> implements InterfaceExecution<E> {

  // 抽象执行方法
  protected abstract T executeResponse(E data);

  // 抽象验证方法
  protected abstract void ExecuteVerification(T response, E data);
}
