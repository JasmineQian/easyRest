package com.example.demo.Util;

import io.restassured.filter.log.LogDetail;
import io.restassured.http.Cookies;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.internal.support.Prettifier;
import io.restassured.response.ResponseBody;
import io.restassured.response.ResponseOptions;
import org.apache.commons.lang3.SystemUtils;

import static io.restassured.filter.log.LogDetail.*;
import static org.apache.commons.lang3.StringUtils.isBlank;

public class ResponseReport {

  private static final String HEADER_NAME_AND_VALUE_SEPARATOR = ": ";

  /**
   * Prints the response to the print stream
   *
   * @return A string of representing the response
   */
  public static String print(
      ResponseOptions responseOptions,
      ResponseBody responseBody,
      LogDetail logDetail,
      boolean shouldPrettyPrint) {
    final StringBuilder builder = new StringBuilder();
    if (logDetail == ALL || logDetail == STATUS) {
      builder.append(responseOptions.statusLine());
    }
    if (logDetail == ALL || logDetail == HEADERS) {
      final Headers headers = responseOptions.headers();
      if (headers.exist()) {
        appendNewLineIfAll(logDetail, builder).append(toString(headers));
      }
    } else if (logDetail == COOKIES) {
      final Cookies cookies = responseOptions.detailedCookies();
      if (cookies.exist()) {
        appendNewLineIfAll(logDetail, builder).append(cookies.toString());
      }
    }
    if (logDetail == ALL || logDetail == BODY) {
      String responseBodyToAppend;
      if (shouldPrettyPrint) {
        responseBodyToAppend =
            new Prettifier().getPrettifiedBodyIfPossible(responseOptions, responseBody);
      } else {
        responseBodyToAppend = responseBody.asString();
      }
      if (logDetail == ALL && !isBlank(responseBodyToAppend)) {
        builder.append(SystemUtils.LINE_SEPARATOR).append(SystemUtils.LINE_SEPARATOR);
      }

      builder.append(responseBodyToAppend);
    }
    return builder.toString();
  }

  private static String toString(Headers headers) {
    if (!headers.exist()) {
      return "";
    }

    final StringBuilder builder = new StringBuilder();
    for (Header header : headers) {
      builder
          .append(header.getName())
          .append(HEADER_NAME_AND_VALUE_SEPARATOR)
          .append(header.getValue())
          .append(SystemUtils.LINE_SEPARATOR);
    }
    builder.delete(builder.length() - SystemUtils.LINE_SEPARATOR.length(), builder.length());
    return builder.toString();
  }

  private static StringBuilder appendNewLineIfAll(LogDetail logDetail, StringBuilder builder) {
    if (logDetail == ALL) {
      builder.append(SystemUtils.LINE_SEPARATOR);
    }
    return builder;
  }
}
