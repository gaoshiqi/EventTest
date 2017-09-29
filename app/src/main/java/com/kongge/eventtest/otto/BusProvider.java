package com.kongge.eventtest.otto;

import com.squareup.otto.Bus;


public class BusProvider {

    private volatile static Bus bus = null;   //使用volatile来修饰多线程访问变量的情况

    private BusProvider(){

    }

    public static Bus getInstance(){
        if(bus == null){
            synchronized (BusProvider.class) {  //加上sunchronized来防止并发
                if (bus == null) {
                    bus=new Bus();
                }
            }
        }
        return bus;
    }

}
