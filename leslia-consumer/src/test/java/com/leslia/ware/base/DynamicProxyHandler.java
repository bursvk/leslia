package com.leslia.ware.base;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class DynamicProxyHandler implements InvocationHandler {

    private Object proxied;

    public DynamicProxyHandler(Object proxied){
        this.proxied=proxied;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("**** proxy: " + proxy.getClass() + ",method:" + method.getName() + ",args:" + args+"");
        Thread.sleep(2000);
       /* if (args != null) {
            for (Object arg : args) {
                System.out.println(" " + arg);
            }
        }*/
       return method.invoke(proxied,args);
    }

}


class SimpleDynamicProxy{
    public static void consumer(Interface face){
        face.doSomething();
        face.somethingElse("bonobo");
    }

    public static void main(String[] args){
        RealObject real=new RealObject();
        consumer(real);
        Interface proxy=(Interface)
                Proxy.newProxyInstance(RealObject.class.getClassLoader(),
                        RealObject.class.getInterfaces(),new DynamicProxyHandler(real));
        System.out.println("----------------------------------------------------------------------------------");
        consumer(proxy);
    }

}


interface  Interface{
    void doSomething();
    void somethingElse(String args);
}

class RealObject implements Interface{
    @Override
    public void doSomething() {
        System.out.println("doSomething");
    }

    @Override
    public void somethingElse(String args) {
        System.out.println("somethingElse "+args);
    }

}














