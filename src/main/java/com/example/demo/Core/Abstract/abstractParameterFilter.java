package com.example.demo.Core.Abstract;

import com.example.demo.Core.Interface.ParameterFilter;
import com.example.demo.bean.ExecutionData;
import org.springframework.stereotype.Service;

/** 抽象顶层接口参数拦截器 */
@Service
public abstract class abstractParameterFilter implements ParameterFilter<ExecutionData> {}
