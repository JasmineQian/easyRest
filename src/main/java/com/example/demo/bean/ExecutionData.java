package com.example.demo.bean;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.metadata.BaseRowModel;


/** 数据参数模型对象 */

public class ExecutionData extends BaseRowModel {

  @ExcelProperty(index = 0, value = "用例名称")
  private String caseName;

  @ExcelProperty(index = 1, value = "用例描述")
  private String caseDescription;

  @ExcelProperty(index = 2, value = "地址")
  private String url;

  @ExcelProperty(index = 3, value = "头信息")
  private String headers;

  @ExcelProperty(index = 4, value = "参数信息")
  private String parameters;

  @ExcelProperty(index = 5, value = "方法")
  private String method;

  @ExcelProperty(index = 6, value = "返回值类型")
  private String responseContentType;

  @ExcelProperty(index = 7, value = "返回值校验")
  private String resultAllStringCheck;

  @ExcelProperty(index = 8, value = "jsonPath校验")
  private String resultJsonPathCheck;

  @ExcelProperty(index = 9, value = "字符包含校验")
  private String resultContainsString;

  /** 请填写clean表示清除 不填写表示延续 */
  @ExcelProperty(index = 10, value = "是否清除Cookie")
  private String cleanCookie;

  /** 请填写clean表示清除 不填写表示延续 */
  @ExcelProperty(index = 11, value = "是否清除Session")
  private String cleanSession;

  public String getCaseName() {
    return caseName;
  }

  public void setCaseName(String caseName) {
    this.caseName = caseName;
  }

  public String getCaseDescription() {
    return caseDescription;
  }

  public void setCaseDescription(String caseDescription) {
    this.caseDescription = caseDescription;
  }

  public String getUrl() {
    return url;
  }

  public void setUrl(String url) {
    this.url = url;
  }

  public String getHeaders() {
    return headers;
  }

  public void setHeaders(String headers) {
    this.headers = headers;
  }

  public String getParameters() {
    return parameters;
  }

  public void setParameters(String parameters) {
    this.parameters = parameters;
  }

  public String getMethod() {
    return method;
  }

  public void setMethod(String method) {
    this.method = method;
  }

  public String getResponseContentType() {
    return responseContentType;
  }

  public void setResponseContentType(String responseContentType) {
    this.responseContentType = responseContentType;
  }

  public String getResultAllStringCheck() {
    return resultAllStringCheck;
  }

  public void setResultAllStringCheck(String resultAllStringCheck) {
    this.resultAllStringCheck = resultAllStringCheck;
  }

  public String getResultJsonPathCheck() {
    return resultJsonPathCheck;
  }

  public void setResultJsonPathCheck(String resultJsonPathCheck) {
    this.resultJsonPathCheck = resultJsonPathCheck;
  }

  public String getResultContainsString() {
    return resultContainsString;
  }

  public void setResultContainsString(String resultContainsString) {
    this.resultContainsString = resultContainsString;
  }

  public String getCleanCookie() {
    return cleanCookie;
  }

  public void setCleanCookie(String cleanCookie) {
    this.cleanCookie = cleanCookie;
  }

  public String getCleanSession() {
    return cleanSession;
  }

  public void setCleanSession(String cleanSession) {
    this.cleanSession = cleanSession;
  }

  @Override
  public String toString() {
    return "ExecutionData{" +
            "caseName='" + caseName + '\'' +
            ", caseDescription='" + caseDescription + '\'' +
            ", url='" + url + '\'' +
            ", headers='" + headers + '\'' +
            ", parameters='" + parameters + '\'' +
            ", method='" + method + '\'' +
            ", responseContentType='" + responseContentType + '\'' +
            ", resultAllStringCheck='" + resultAllStringCheck + '\'' +
            ", resultJsonPathCheck='" + resultJsonPathCheck + '\'' +
            ", resultContainsString='" + resultContainsString + '\'' +
            ", cleanCookie='" + cleanCookie + '\'' +
            ", cleanSession='" + cleanSession + '\'' +
            '}';
  }
}
