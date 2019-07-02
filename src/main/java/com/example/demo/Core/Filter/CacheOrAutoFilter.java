package com.example.demo.Core.Filter;

import com.example.demo.Core.Abstract.abstractParameterFilter;
import com.example.demo.Core.Interface.CacheDataSource;
import com.example.demo.Core.imp.DataCacheOrAuto;
import com.example.demo.bean.ExecutionData;

import java.lang.reflect.Field;
import java.lang.reflect.Type;

/*参数拦截器默认实现类  支持 缓存读取 和函数实现*/
public class CacheOrAutoFilter extends abstractParameterFilter {
  public CacheOrAutoFilter() {
    CacheDataSource = new DataCacheOrAuto();
  }

  /** @return 排序规则 0优先级最高 1第二 以此类推 */
  @Override
  public int getOrder() {
    return 0;
  }

  public void setCacheDataSource(CacheDataSource<String> cacheDataSource) {
    CacheDataSource = cacheDataSource;
  }

  private CacheDataSource<String> CacheDataSource;

  /**
   * @param data 请求参数拦截
   * @return 返回请求拦截参数
   */
  @Override
  public ExecutionData dynamicParameterListener(ExecutionData data) {
    Field[] declaredFields = data.getClass().getDeclaredFields();
    for (Field field : declaredFields) {
      final Type genericType = field.getGenericType();
      String typeName = genericType.getTypeName();
      if (typeName.equals("java.lang.String")) {
        field.setAccessible(true);
        try {
          String fieldValue = (String) field.get(data);
          String CacheData = CacheDataSource.getData(fieldValue);
          field.set(data, CacheData);

        } catch (IllegalAccessException e) {
          e.printStackTrace();
        }
      }
    }

    return data;
  }
}
