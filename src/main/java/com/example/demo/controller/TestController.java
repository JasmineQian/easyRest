package com.example.demo.controller;

import com.example.demo.Core.Interface.InterfaceDataProvider;
import com.example.demo.Core.Interface.InterfaceExecution;
import com.example.demo.Core.imp.InterfaceExecutionProxy;
import com.example.demo.Data.ExcelInterfaceDataProd;
import com.example.demo.bean.ExecutionData;
import io.qameta.allure.Step;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Iterator;

@RestController
public class TestController {

    private InterfaceExecutionProxy<ExecutionData> executionimp;

    // 数据发动机
    @Autowired
    InterfaceDataProvider<Object> interfaceDataProvider;
    // 执行驱动器
    @Autowired
    InterfaceExecution<ExecutionData> interfaceExecution;

    @GetMapping(value = "/run")
    private void Test(){
        //ExecutionData executionData = (ExecutionData)
        Iterator<Object[]> lists = new ExcelInterfaceDataProd().ImplementDataProvider();
        for (Iterator<Object[]> it = lists; it.hasNext(); ) {
            Object executionData = it.next();
            System.out.println(executionData.toString());
            executionimp.execution((ExecutionData)executionData);
        }
    }

    @DataProvider(name = "getdata")
    public Iterator<Object[]> getdata() {
        return new ExcelInterfaceDataProd().ImplementDataProvider();
    }


    @Test(testName = "esayrestTests", dataProvider = "getdata")
    @Step("测试用例名称：{executionData.caseDescription}")
    public void TTTData(ExecutionData executionData) {
        executionimp.execution(executionData);
    }
}
