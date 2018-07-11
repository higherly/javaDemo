package spring.bean.test;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/*Spting beans的生命周期
* 1.创建对象
*2.通过DI,对bean进行依赖注入。
* 3.如果这个bean实现了BeanNameAware接口，会调用它的setBeanName方法，处传递的是Spring配置文件中Bean的ID
*4.如果这个Bean实现了BeanFactoryAware接口，会调用它实现的setBeanFactory()，传递的是Spring工厂本身（可以用这个方法获取到其他Bean）

5. 如果这个Bean实现了ApplicationContextAware接口，会调用setApplicationContext(ApplicationContext)方法，传入Spring上下文，该方式同样可以实现步骤4，但比4更好，以为ApplicationContext是BeanFactory的子接口，有更多的实现方法

6. 如果这个Bean关联了BeanPostProcessor接口，将会调用postProcessBeforeInitialization(Object obj, String s)方法，BeanPostProcessor经常被用作是Bean内容的更改，并且由于这个是在Bean初始化结束时调用After方法，也可用于内存或缓存技术

7. 如果这个Bean在Spring配置文件中配置了init-method属性会自动调用其配置的初始化方法

8. 如果这个Bean关联了BeanPostProcessor接口，将会调用postProcessAfterInitialization(Object obj, String s)方法

注意：以上工作完成以后就可以用这个Bean了，那这个Bean是一个single的，所以一般情况下我们调用同一个ID的Bean会是在内容地址相同的实例

9. 当Bean不再需要时，会经过清理阶段，如果Bean实现了DisposableBean接口，会调用其实现的destroy方法

10. 最后，如果这个Bean的Spring配置中配置了destroy-method属性，会自动调用其配置的销毁方法
*
* */
public class SpringBeanTest implements BeanNameAware,BeanFactoryAware,ApplicationContextAware,InitializingBean,DisposableBean{
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
    public static void main(String args[]) throws Exception{
        ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
        SpringBeanTest sp = (SpringBeanTest) ac.getBean("springBeanTest");
        sp.destroy();
    }
}

