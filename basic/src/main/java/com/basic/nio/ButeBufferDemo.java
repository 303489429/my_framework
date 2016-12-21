package com.basic.nio;

import java.nio.Buffer;
import java.nio.ByteBuffer;

/**
 * Created by wangzhilong on 2016/12/7.
 */
public class ButeBufferDemo {

    public static void main(String[] args) {
        //otherOpt();
        ByteBuffer buffer = ByteBuffer.allocate(10);
        System.out.println("初始化buffer:"+buffer+",剩余容量："+buffer.remaining());
        buffer.putInt(1);
        buffer.putInt(6);
        buffer.putChar('A');
        System.out.println("使用完成buffer:"+buffer+",剩余容量："+buffer.remaining());

        buffer.clear(); //清空，在调用 four.write(buff) 时，就将buff缓冲区中的数据写入到输出管道，
        // 此时调用ByteBuffer.clear()方法为下次从管道中读取数据做准备，
        // 但是调用clear方法并不将缓冲区的数据清空，而是设置position，mark，limit这三个变量的值

        System.out.println("clean后的buffer："+buffer+",剩余容量："+buffer.remaining());

        //System.out.println("clean one value:"+ buffer.getInt());
        //
        //System.out.println("clean get one 后的buffer："+buffer+",剩余容量："+buffer.remaining());
        //
        //System.out.println("clean one value:"+ buffer.getInt());
        //
        //System.out.println("clean get two 后的buffer："+buffer+",剩余容量："+buffer.remaining());

        buffer.putInt(3);
        //
        System.out.println("插入hello后的buffer："+buffer+",剩余容量："+buffer.remaining());
        //buffer.flip();
        //System.out.println("flip后的buffer："+buffer+",剩余容量："+buffer.remaining());
        System.out.println("one value:"+ buffer.getInt());
        System.out.println("getInt 1后的buffer："+buffer+",剩余容量："+buffer.remaining());
        //System.out.println("two value:"+ buffer.getInt());
        //System.out.println("getInt 2后的buffer："+buffer+",剩余容量："+buffer.remaining());

    }



    private static void otherOpt() {
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        System.out.println("完成分配的buffer:"+buffer);
        buffer.putChar('a');
        System.out.println("输入字符a的buffer:"+buffer);
        buffer.putInt(10);
        System.out.println("输入数字10的buffer:"+buffer);
        buffer.mark(); //记录mark的位置
        buffer.putLong(1L);
        System.out.println("输入long后的buffer:"+buffer);
        buffer.flip();//了解了这四个变量之后，再来看看前面的程序。
        // 之所以调用ByteBuffer.flip()方法是因为在向ByteBuffer写入数据后，position为缓冲区中刚刚读入的数据的最后一个字节的位置，
        // flip方法将limit值置为position值，position置0，这样在调用get*()方法从ByteBuffer中取数据时就可以取到ByteBuffer中的有效数据，
        // JDK中flip方法的代码如下:
        System.out.println("filp后的buffer值："+buffer+",剩余容量："+buffer.remaining());
        buffer.putInt(2);
        System.out.println("mark后操作的buffer:"+buffer);
        //buffer.reset() ;//重置 reset后的position=mark
        System.out.println("reset后的buffer:"+buffer);
        buffer.rewind(); //清除标记，position变成0，mark变成-1
        System.out.println("rewind后的buffer:"+buffer);
        while (true){
            buffer.putLong(1L);
            System.out.println("循环插入后的buffer:"+buffer+",剩余容量："+buffer.remaining());
        }
    }
}
