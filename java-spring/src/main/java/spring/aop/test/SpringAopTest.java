package spring.aop.test;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.aop.MethodBeforeAdvice;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

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
        /*AOP代理*/
        /*ProxyFactory proxyFactory = new ProxyFactory();//创建代理工厂
        proxyFactory.setTarget(new ArrayList<String>());
        proxyFactory.addAdvice(new MethodBefore());
        List<String> list1 = (List<String>)proxyFactory.getProxy();//获取代理
        list1.add("1");*/

        /*Spring+ASpectJ基于注解*/
        /*ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
        HelloWorld hl=(HelloWorld) ac.getBean("helloWorldImpl1");
        hl.say();*/
        /*Spring+ASpectJ基于xml*/
        ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
        HelloWorld2 hl=(HelloWorld2) ac.getBean("helloWorldImpl2");
        hl.say();
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
interface HelloWorld{
    public void say();
}
@Service
class HelloWorldImpl1 implements HelloWorld{
    public void say() {
        System.out.println("Spring+ASpectJ注解代理");
    }
}
@Aspect
@Component
class HelloWorldImplAspect{
    @Around("execution(* spring.aop.test.HelloWorldImpl1.*(..))")
    public Object around(ProceedingJoinPoint pjp) throws Throwable{
        before();
        Object result = pjp.proceed();
        after();
        return result;
    }
    public void before(){
        System.out.println("Spring+ASpectJ注解代理开始");
    }
    public void after(){
        System.out.println("Spring+ASpectJ注解代理结束");
    }
}
/*基于配置*/
interface HelloWorld2{
    public void say();
}
class HelloWorldImpl2 implements HelloWorld2 {
    public void say() {
        System.out.println("Spring+ASpectJ配置");
    }
}
class HelloWorldImpl2Aspect{
    public void beforeSay(){
        System.out.println("Spring+ASpectJ配置开始");
    }
    public void afterSay(){
        System.out.println("Spring+ASpectJ配置结束");
    }
}

