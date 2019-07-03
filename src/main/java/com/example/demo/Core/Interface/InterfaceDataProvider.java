package com.example.demo.Core.Interface;

import com.example.demo.Enum.DataType;

import java.util.Iterator;

public interface InterfaceDataProvider<T> {

  Iterator<T[]> ImplementDataProvider();

  DataType DatasourceName();
}
