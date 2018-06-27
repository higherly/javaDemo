package spring.bean.test;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/*Spting beans的生命周期
* 1.创建对象
*2.通过DI,对bean进行依赖注入。
* 3.如果这个bean实现了BeanNameAware接口，会调用它的setBeanName方法
*
*
* */
public class SpringBeanTest implements BeanNameAware,BeanFactoryAware,ApplicationContextAware,DisposableBean,InitializingBean{
    private int age;
    private String name;
    public SpringBeanTest(String name){
        System.out.println("构造器注入");
        this.name=name;
    }
    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        System.out.println("setter方法注入");
        this.age = age;
    }
    public void setBeanName(String id) {
        System.out.println("setBeanName方法实现");
       // System.out.println(id);
    }

    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        System.out.println("setBeanFactory方法实现");
    }

    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        System.out.println("setApplicationContext方法实现");
    }


    public void afterPropertiesSet() throws Exception {
        System.out.println("bean初始化");
    }
    public void init_method() throws Exception {
        System.out.println("bean初始化");
    }
    public void destroy() throws Exception {
        System.out.println("bean销毁1");/*两种方式*/
    }
    public void destroy_method(){
        System.out.println("bean销毁2");/*两种方式*/
    }

    public static void main(String args[]){
        ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
    }
}
