package spring.aop.test;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.List;
/*
* JDK动态代理和CGLIB动态代理
* */
public class SpringAopTest {
    public static void main(String [] args){
        final List<String> list = new ArrayList();
        List<String> list1 = (List<String>)Proxy.newProxyInstance(list.getClass().getClassLoader(), list.getClass().getInterfaces(), new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                System.out.println("jdk动态代理开始");
                Object result = method.invoke(list,args);
                System.out.println("jdk动态代理结束");
                return result;
            }
        });
        list1.add("1");
    }
}
