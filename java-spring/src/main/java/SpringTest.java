/*http://www.runoob.com/design-pattern/template-pattern.html 设计模式
1.谈谈你对spring的理解
* 2.Spring中的设计模式
*   a.代理模式
*   b.单例模式       spring配置文件中的bean默认是单例，一个类只有一个实例，饿汉式和懒汉式。
*   c.模板方法模式  用来解决代码重复问题，父类定义框架，子类定义具体的实现细节。
*   d.工厂模式   1）简单工厂模式（Simple Factory） 2）工厂方法模式（Factory Method） 3）抽象工厂模式（Abstract Factory）
 *   创建对象过程不会暴露出来，而是通过一个接口指向新创建的对象。https://www.cnblogs.com/yumo1627129/p/7197524.html
*   e,观察者模式   定义对象之间一对多的依赖关系,当一个对象的状态发生变化时，所有依赖它的对象将得到通知并自动更新。如Listener，发布与订阅。
*   f.前端控制器模式 Spring 提供了DispatcherServlet 来对请求进行统一管理。
* 3.spring中的常用注解？
*   @Component ：标准一个普通的spring Bean类。
    @Repository：标注一个DAO组件类。
    @Service：标注一个业务逻辑组件类。
    @Controller：标注一个控制器组件类。
    @Autowired顾名思义，就是自动装配，其作用是为了消除代码Java代码里面的getter/setter与bean属性中的property。
    依赖对象必须存在，如果要允许null值，可以设置它的required属性为false   @Autowired(required=false)
    @Qualifier（指定注入Bean的名称）
    @Autowired和@Resource的相同和不同点
    两者都可以写在字段和setter方法上。两者如果都写在字段上，那么就不需要再写setter方法。
    @Resource默认按照名称方式进行bean匹配，@Autowired默认按照类型方式进行bean匹配
    @Resource(import
    javax.annotation.Resource;)是J2EE的注解，@Autowired(
    import org.springframework.beans.factory.annotation.Autowired;)是Spring的注解
* 4.简单介绍一下Spring bean 的生命周期？
* 5.
* 6.spring可以帮助我们做什么？
*   a.spring控制反转可以根据配置文件自动创建和组装对象之间的依赖关系。
*   b.spring面向切面编程可以帮助我们实现日志记录，权限控制，使我们专注代码逻辑实现。
*   c.spring可以帮助我们管理数据库事务。
*   d.spring可以与第三方框架，如mybatis，struts2无缝衔接。
* 7.描述一下spring的事务？
* 11.spring配置文件有什么用？
*   是个xml文件，描述了类信息，以及他们之间的依赖关系。
* 12.什么是spring的ioc容器？
*  ioc ，控制反转，可以创建对象，然后通过DI,负责组装对象之间的依赖关系，并且管理这些对象的整个生命周期。
*  16.什么是spring的依赖注入？
*  17.有哪些不同方式的DI方式？
*           1. setter注入，2 构造器注入，3 静态工厂方法注入，4 实例工厂方法注入
*  18.什么是spirng beans?
*       Spring主干应用的 java对象。它们被Spring IOC容器初始化，装配和管理。在 XML 文件中 以<bean/><bean/> 的形式定义。
*  20.你怎样定义类的作用域？
*  22.spring中的单例bean是线程安全的么？
*     不是线程安全的。如果你的代码所在的进程中有多个线程在同时运行，而这些线程可能会同时运行这段代码。如果每次运行结果和单线程运行的结果是一样的，而且其他的变量的值也和预期的是一样的，
就是线程安全的。线程安全问题都是由全局变量及静态变量引起的。
   25.什么是bean的自动装配？
    无需在配置文件中描述java bean的依赖关系，spring ioc 容器会自动建立bean之间的依赖关系。
    32.spring支持的ORM框架有哪些？
        Hibernate,iBatis，
    33.

* */
public class SpringTest {
    public static void main(String[] args){
        System.out.println(1);
    }
}
/*   33.简单解释下spring aop？
        aop面向切面编程，他使用一种“横切的技术”，将那些与业务无关，却为业务模块所共同调用的逻辑或责任封装起来，
        便于减少系统的重复代码，降低模块之间的耦合度。比如日志记录和权限控制的实现。其原理是动态代理。
*    34.在spring aop 中，关注点和横切关注点的区别式是什么？切面和切入点又是什么？以及目标对象，代理，织入，和引入呢？
*       关注点 更像是我们要实现的一个功能。
*       对哪些方法进行拦截，拦截后怎么处理，这些关注点称之为横切关注点
*       切面就是横切关注点的抽象。
*       切入点就是连接点进行拦截的定义，
*       目标对象就是被代理的对象。
*       代理对象就是通知目标对象后创建的对象。
*       织入就是切面应用到目标对象并导致代理对象创建的过程
*       引入就是在不修改代码的前提下，可以在运行期为类动态地添加一些方法或字段
*    35.什么是连接点？
*         被拦截到的点，spring中连接点是指被拦截到的方法，其实也可以是字段和构造器。
*    36.Spring中的通知是什么？有哪几种类型？
*        通知是方法执行前或者执行后要做的动作，
*        before,after,afterReturning,afterThrowing,around
* */
