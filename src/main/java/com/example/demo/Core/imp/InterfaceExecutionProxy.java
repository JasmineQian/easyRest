package com.example.demo.Core.imp;

import com.example.demo.Core.Interface.InterfaceExecution;
import com.example.demo.bean.ExecutionData;

/** 代理模式运用 该类做为顶层代理面板对外执行所有实现InterfaceExecution接口的类 */
public class InterfaceExecutionProxy<E extends ExecutionData> implements InterfaceExecution<E> {

  private InterfaceExecution<E> execution;

  public InterfaceExecutionProxy(InterfaceExecution<E> execution) {
    this.execution = execution;
  }

  @Override
  public void execution(E executionData) {
    execution.execution(executionData);
  }
}
