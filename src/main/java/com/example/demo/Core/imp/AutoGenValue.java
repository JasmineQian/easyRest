package com.example.demo.Core.imp;

import com.example.demo.Core.Abstract.abstractAutoGenValue;
import com.example.demo.Enum.functionEnum;
import com.example.demo.Util.StringReplaceHelper;

/** 自动生成数据数据函数类 */
public class AutoGenValue extends abstractAutoGenValue {

  @Override
  protected functionEnum getfunctionEnum(String keywords) {

    // uuid
    // random(10)

    String parameter = getParameter(keywords);

    if (parameter != null) {
      String Method = keywords.split("\\(")[0];
      for (functionEnum functionEnum : functionEnum.values()) {
        if (functionEnum.name().equals(Method)) {
          return functionEnum;
        }
      }
    } else {
      for (functionEnum functionEnum : functionEnum.values()) {
        if (functionEnum.name().equals(keywords)) {
          return functionEnum;
        }
      }
    }
    return null;
  }

  /**
   * @param keywords 关键字
   * @return 返回第一个括号中的数据字符
   */
  @Override
  protected String getParameter(String keywords) {

    return StringReplaceHelper.getKeywords(keywords, "(", ")");
  }
}
