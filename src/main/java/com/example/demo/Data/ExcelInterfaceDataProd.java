package com.example.demo.Data;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.io.resource.ClassPathResource;
import cn.hutool.core.util.StrUtil;
import com.example.demo.Config.EasyRestConfig;
import com.example.demo.Core.Abstract.AbstractExcelInterfaceData;
import com.example.demo.Util.ExcelUtil;
import com.example.demo.Util.TestNgUtil;
import com.example.demo.bean.ExecutionData;
import org.slf4j.Logger;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ExcelInterfaceDataProd extends AbstractExcelInterfaceData {
  private static Logger log;

  private static List<Object> excelData = new ArrayList<>();
  private static Iterator<Object[]> objectList = new ArrayList<Object[]>().iterator();

  @Override
  protected Iterator<Object[]> impDataProvider() {
    String property = System.getProperty("easyrest.restassured.init");
    if (StrUtil.isEmpty(property)) {
      EasyRestConfig.initGlobalConfigSetting();
    }
    if (!StrUtil.isEmpty(EasyRestConfig.getFilepath())) {
      this.setFilepath(EasyRestConfig.getFilepath());
    }
    afterPropertiesSet();
    return objectList;
  }

  private void afterPropertiesSet() {
    File file;
    if (!StrUtil.isEmpty(this.getFilepath())) {
      file = new File(this.getFilepath());
      System.out.println("正在使用easyrest.exceldata.filepath 配置{}的文件路径进行加载！！"+ this.getFilepath());
    } else {
      file = new ClassPathResource("ExcelData.xlsx").getFile();
      System.out.println("正在使用ClasPath:ExcelData.xlsx数据驱动文件进行加载");
    }

    ExcelUtil excelUtil = new ExcelUtil();
    try {
      List<ExecutionData> executionDataList =
          excelUtil.readExcelReturnListBean(file, ExecutionData.class);
      if (!CollUtil.isEmpty(executionDataList)) {
        for (ExecutionData data : executionDataList) {
          excelData.add(data);
        }
        objectList = TestNgUtil.createObjectList(excelData);
        System.out.println(objectList.toString());
      }
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }
  }
}
