import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.List;

/*
* 1.java的动态代理实现
* 2.动静态代理的区别，
*   静态代理通常只能代理一个类，而动态代理是一个接口下的多个实现类。
*   静态代理需要事先知道代理的是什么，而动态代理不需要知道，运行时才知道。
* */
public class DymaticTest {
    public static void main(String[] args){
        final List<String> list = new ArrayList<String>();
        List<String> list1=(List<String> ) Proxy.newProxyInstance(list.getClass().getClassLoader(),list.getClass().getInterfaces(),new InvocationHandler(){
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
