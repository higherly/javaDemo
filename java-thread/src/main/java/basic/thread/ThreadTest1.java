package basic.thread;

/**
 * 1.创建线程的方式及实现,最常用的有两种
 *      继承Thread类，实现Runnable接口
 * 2.线程安全
 *     多个线程同时运行同一段代码时，每次运行结果和单个线程运行结果是一样的。线程安全主要是全局变量和静态变量引起的。
 * 3.线程同步
 *     A线程要请求某个资源，但是此资源正在被B线程使用中，因为同步机制存在，A线程请求不到，怎么办，A线程只能等待下去,为了解决多线程并发访问问题。
 * 4.ThreadLocal
 *     线程本地变量,ThreadLocal用于实现线程内的数据共享。https://www.cnblogs.com/dolphin0520/p/3920407.html
 *     提供了public T get(){} , public void set(T value){} public void remove(){}
 *     https://www.jianshu.com/p/807686414c11?utm_campaign=maleskine&utm_content=note&utm_medium=seo_notes&utm_source=recommendation
 *     关键词ThreadLocal ,ThreadLocalMap
 *     Spring就是利用ThreadLocal来实现一个线程中的Connection是同一个，从而保证了事务。
 * /


/* sleep() 、join（）、yield（）有什么区别
* 线程的生命周期
* */
public class ThreadTest1 {
    public static void main(String[] args){
        Thread1 t1 = new Thread1();
        Thread t = new Thread(new Thread2());
        t1.start();
        t.start();
        System.out.println(2222);
    }
}
class Thread1 extends Thread{
    @Override
    public void run(){
        System.out.println(111);
   }
}
class Thread2 implements Runnable{
    @Override
    public void run() {
        System.out.println(333);
    }
}

