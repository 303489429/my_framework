package com.spring.bean;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by wangzhilong on 2016/10/8.
 */
public class Print {

    @Autowired
    private Fee fee ;

    @Autowired
    private Project project ;

    public void printMsg(){
        if(project != null)
            System.out.println("项目："+ project.getProjectName());
        if (fee != null)
            System.out.println("费用："+fee.getFee()+"元");
        System.out.println("消息输出完成。");
    }

    public void printObj(){
        if(project != null)
            System.out.println("项目2："+ project.getProjectName());
        if (fee != null)
            System.out.println("费用2："+fee.getFee()+"元");
        System.out.println("消息输出完成2。");
    }
}
