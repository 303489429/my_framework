package com.refactor.bridging;

/**
 * 功能概述：使用参数传递改进后的桥接模式，“优先使用组合而不是继承”的设计
 * Created by wangzhilong on 2016/8/31.
 */
public interface QuerySupport {

    Object Query(IQuery iQuery);

}
