package com.wzl.junit.core;

import org.mortbay.jetty.Server;
import org.mortbay.jetty.handler.ResourceHandler;
import org.mortbay.jetty.servlet.Context;

/**
 * Created by wangzhilong on 2016/9/7.
 */
public class JettySample {

    public static void main(String[] args) throws Exception {
        Server server = new Server(8080);
        Context root = new Context(server,"/");
        root.setResourceBase("./pom.xml");
        root.setHandler(new ResourceHandler());
        server.start();

    }
}
