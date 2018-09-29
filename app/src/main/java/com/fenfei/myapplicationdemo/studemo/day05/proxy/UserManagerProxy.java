package com.fenfei.myapplicationdemo.studemo.day05.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Created by shefenfei on 2017/12/25.
 */

public class UserManagerProxy implements InvocationHandler {

    private Object targetObject;

    public Object newProxyInstance(Object object) {
        this.targetObject = targetObject;

        // 第一个参数指定产生代理对象的类加载器，需要将其指定为和目标对象同一个类加载器
        // 第二个参数要实现和目标对象一样的接口，所以只需要拿到目标对象的实现接口
        // 第三个参数表明这些被拦截的方法在被拦截时需要执行哪个InvocationHandler的invoke方法
        // 根据传入的目标返回一个代理对象

        return Proxy.newProxyInstance(targetObject.getClass().getClassLoader() , targetObject.getClass().getInterfaces() , this);
    }

    /**
     * 关联的这个实现类的方法被调用时将被执行
     * @param proxy     代理
     * @param method    原对象被调用的方法
     * @param args      方法的参数
     * @return          原方法的返回值
     * @throws Throwable
     */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Object o = null;
        try {
            o = method.invoke(targetObject , args);

        }catch (Exception e) {

        }
        return o;
    }
}
