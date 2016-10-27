package com.refactor.bridging;

import java.util.Map;

/**
 * Created by wangzhilong on 2016/8/31.
 */
public interface IQuery {
    String getExpression();

    Map<String,Object> getParameters();
}
