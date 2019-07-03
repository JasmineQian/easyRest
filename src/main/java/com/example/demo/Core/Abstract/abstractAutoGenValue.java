package com.example.demo.Core.Abstract;

import com.example.demo.Core.Interface.autoGenerationVaule;
import com.example.demo.Enum.functionEnum;
import org.springframework.stereotype.Service;

/** 1、解析字符串例如uuid(10),phone(13),time(yyyy-mm-dd:mm:hh:ss) 2、匹配对应实现类 进行执行参数解析并返回String */

@Service
public abstract class abstractAutoGenValue implements autoGenerationVaule<String> {

  @Override
  public String GenerationValue(String Keywords) {
    String Parameter = null;
    functionEnum functionEnum = getfunctionEnum(Keywords);
    Parameter = getParameter(Keywords);
    if (functionEnum != null) {
      if (Parameter != null) {
        return functionEnum.generateValue(Parameter);
      } else {
        return functionEnum.generateValue(null);
      }
    } else {
      return null;
    }
  }

  protected abstract functionEnum getfunctionEnum(String keywords);

  protected abstract String getParameter(String keywords);
}
