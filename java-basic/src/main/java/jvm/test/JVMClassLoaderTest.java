package jvm.test;

/**
 * Created by Administrator on 2018/6/27.
 */
public class JVMClassLoaderTest {
    public static void main(String[] args) throws Exception {
        HelloWorld helloWorld = new HelloWorldImpl();
        ClassLoader cl = helloWorld.getClass().getClassLoader();
        String interfaceName = helloWorld.getClass().getInterfaces()[0].getName();//实现接口名称
        Class interfaceClass = Class.forName(interfaceName, false, cl);
        System.out.println(cl);
        System.out.println(interfaceName);
        System.out.println(interfaceClass);
    }
}

