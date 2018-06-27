package spring.aop.test;

import org.springframework.aop.MethodBeforeAdvice;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

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
        /*Jdk动态代理*/
        /*final List<String> list = new ArrayList<String>();
        List<String> list1= (List)Proxy.newProxyInstance(list.getClass().getClassLoader(),list.getClass().getInterfaces(),new InvocationHandler(){
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                System.out.println("jdk动态代理开始");
                Object result = method.invoke(list,args);
                System.out.println("jdk动态代理结束");
                return result;
            }
        });
        list1.add("1");*/

        /*HelloWorldImpl hl = (HelloWorldImpl)Enhancer.create(HelloWorldImpl.class, new MethodInterceptor() {
            @Override
            public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
                System.out.println("Cglib动态代理开始");
                Object result = methodProxy.invokeSuper(o,objects);
                System.out.println("Cglib动态代理结束");
                return result;
            }
        });
        hl.say();*/
        ProxyFactory proxyFactory = new ProxyFactory();//创建代理工厂
        proxyFactory.setTarget(new ArrayList<String>());
        proxyFactory.addAdvice(new MethodBefore());
        List<String> list1 = (List<String>)proxyFactory.getProxy();//获取代理
        list1.add("1");
    }
}
class HelloWorldImpl{
    public void say(){
        System.out.println("CGLIB动态代理");
    }
}
class MethodBefore implements MethodBeforeAdvice{
    @Override
    public void before(Method method, Object[] objects, Object o) throws Throwable {
        System.out.println("aop动态代理");
    }
}
