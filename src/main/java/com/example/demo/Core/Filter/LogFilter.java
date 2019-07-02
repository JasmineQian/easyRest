package com.example.demo.Core.Filter;

import com.example.demo.Util.ReportDetil;
import com.example.demo.Util.RequestReport;
import com.example.demo.Util.ResponseReport;
import io.restassured.filter.FilterContext;
import io.restassured.filter.OrderedFilter;
import io.restassured.filter.log.LogDetail;
import io.restassured.filter.log.UrlDecoder;
import io.restassured.response.Response;
import io.restassured.specification.FilterableRequestSpecification;
import io.restassured.specification.FilterableResponseSpecification;

import java.nio.charset.Charset;

public class LogFilter implements OrderedFilter {
  @Override
  public int getOrder() {
    return 4;
  }

  @Override
  public Response filter(
      FilterableRequestSpecification requestSpec,
      FilterableResponseSpecification responseSpec,
      FilterContext ctx) {
    String uri = requestSpec.getURI();

    uri =
        UrlDecoder.urlDecode(
            uri,
            Charset.forName(
                requestSpec.getConfig().getEncoderConfig().defaultQueryParameterCharset()),
            true);

    String RequestDetail =
        RequestReport.print(requestSpec, requestSpec.getMethod(), uri, LogDetail.ALL, true);
    ReportDetil.RequestBodyReport(RequestDetail);
    Response response = ctx.next(requestSpec, responseSpec);
    String ResponseDetail = ResponseReport.print(response, response, LogDetail.ALL, true);
    ReportDetil.RespondBodyReport(ResponseDetail);
    return response;
  }
}
