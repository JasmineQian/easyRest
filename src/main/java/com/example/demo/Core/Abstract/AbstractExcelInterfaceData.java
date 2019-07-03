package com.example.demo.Core.Abstract;

import com.example.demo.Core.Interface.InterfaceDataProvider;
import com.example.demo.Enum.DataType;
import org.springframework.stereotype.Service;

import java.util.Iterator;

/** 抽象实现数据提供接口 */
@Service
public abstract class AbstractExcelInterfaceData implements InterfaceDataProvider<Object> {
  /** 文件路径 */
  private String filepath;

  @Override
  public DataType DatasourceName() {
    return DataType.Excel;
  }

  @Override
  public Iterator<Object[]> ImplementDataProvider() {
    return impDataProvider();
  }

  protected String getFilepath() {
    return filepath;
  }

  protected void setFilepath(String filepath) {
    this.filepath = filepath;
  }

  /** @return 回调函数由子类实现 */
  protected abstract Iterator<Object[]> impDataProvider();
}
