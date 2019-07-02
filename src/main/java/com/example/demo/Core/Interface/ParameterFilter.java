package com.example.demo.Core.Interface;

public interface ParameterFilter<T> {

  int getOrder();

  T dynamicParameterListener(T t);
}
