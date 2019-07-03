package com.example.demo;


import com.example.demo.Core.Interface.InterfaceDataProvider;
import com.example.demo.Core.Interface.InterfaceExecution;
import com.example.demo.bean.ExecutionData;
import io.qameta.allure.Step;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Iterator;


@RunWith(SpringRunner.class)
@SpringBootTest
public class JasmineTest extends AbstractTestNGSpringContextTests {

    // 数据发动机
    @Autowired
    InterfaceDataProvider<Object> interfaceDataProvider;
    // 执行驱动器
    @Autowired
    InterfaceExecution<ExecutionData> interfaceExecution;

    @DataProvider(name = "getdata")
    public Iterator<Object[]> getdata() {
        return interfaceDataProvider.ImplementDataProvider();
    }

    @Test(testName = "esayrestTests", dataProvider = "getdata")
    @Step("测试用例名称：{executionData.caseDescription}")
    public void EasyRestApplicationContext(ExecutionData executionData) {

        System.out.println("executionDataexecutionDataexecutionDatais "+executionData);

        interfaceExecution.execution(executionData);
    }
}
