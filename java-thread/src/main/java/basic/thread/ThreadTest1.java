package basic.thread;
/*http://www.cnblogs.com/dolphin0520/p/3923167.html*/
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
 * 5.volatile (并发编程的三个问题:原子性问题，可见性问题:指当多个线程访问同一个变量时，一个线程修改了这个变量的值，其他线程能够立即看得到修改的值。，有序性问题:即程序执行的顺序按照代码的先后顺序执)
 *     https://www.cnblogs.com/dolphin0520/p/3920373.html
 *     1.保证了不同线程对这个变量进行操作时的可见性，即一个线程修改了某个变量的值，这新值对其他线程来说是立即可见的。
 *　　  2.禁止进行指令重排序。
 * 重排序:处理器为了提高程序运行效率，可能会对输入代码进行优化，它不保证程序中各个语句的执行先后顺序同代码中的顺序一致，
 * 但是它会保证程序最终执行结果和代码顺序执行的结果是一致的。
 *
 *   6.synchronized 是一个关键字，实现同步访问，用来解决多线程并发访问问题。
 *      Java中每一个对象都拥有一个锁标记（monitor），也称为监视器，多线程同时访问某个对象时，线程只有获取了该对象的锁才能访问。
 *      可以使用synchronized关键字来标记一个方法或者代码块，当某个线程调用该对象的synchronized方法或者访问synchronized代码块时，
 *      这个线程便获得了该对象的锁，其他线程暂时无法访问这个方法，只有等待这个方法执行完毕或者代码块执行完毕，
 *      这个线程才会释放该对象的锁，其他线程才能执行这个方法或者代码块。
 *
 *   7.synchronized和volatile的区别？
 *      1.volatile可以保证变量的可见性,但是不能保证原子性。synchronized可以保证变量的可见性和原子性.
 *      （不保证原子性原理:，每个线程在运行过程中都有自己的工作内存）
 *      2.volatile不会造成线程的阻塞。synchronized会造成线程的阻塞。
 *      3,volatile是修饰变量的，而synchronized修饰方法和代码块。
 *
 *   8.Lock 是一个接口，实现同步访问，用来解决多线程并发访问问题。
 *      lockInterruptibly()方法比较特殊，
 *      当通过这个方法去获取锁时，如果线程正在等待获取锁，则这个线程能够响应中断，即中断线程的等待状态。
 *      也就使说，当两个线程同时通过lock.lockInterruptibly()想获取某个锁时，假若此时线程A获取到了锁
 *      ，而线程B只有在等待，那么对线程B调用threadB.interrupt()方法能够中断线程B的等待过程。
 *   9.synchronized和Lock的区别？
 *      1.synchronized是一个关键字，Lock是一个接口。
 *      2.Lock可以知道线程有没有获取对象锁，synchronized不行。
 *      3.Lock必须手动释放锁，否则会造成死锁现象，synchronized则当方法或者代码块执行完毕后，系统会自动让线程释放对锁的占有。
 *
 *  10.ReentrantLock "可重入锁" Lock的唯一实现类。 锁具备可重入性，则称作为可重入锁。
 *  11.ReadWriterLock 读写锁
 *
 *  12.其他锁
 *     可重入锁: synchronized和ReentrantLock都是可重入锁.
 *     可中断锁:synchronized就不是可中断锁，而Lock是可中断锁。
 *     公平锁:即尽量以请求锁的顺序来获取锁
 *     synchronized就是非公平锁，它无法保证等待的线程获取锁的顺序。
 　　而对于ReentrantLock和ReentrantReadWriteLock，它默认情况下是非公平锁，但是可以设置为公平锁。
 * /
/* sleep() 、join（）、yield（）有什么区别
* 线程的生命周期
* */
public class ThreadTest1 {
    public static void main(String[] args) throws Exception{
        /*Thread1 t1 = new Thread1();
        Thread t = new Thread(new Thread2());
        t1.start();
        t.start();
        System.out.println(2222);*/
        final VolatileTest test = new VolatileTest();
        for (int i=0;i<1000;i++){
            new Thread(){
                @Override
                public void run() {
                    test.add();
                }
            }.start();
        }
        Thread.sleep(5000);
        System.out.println(test.num);
    }
}
class Thread1 extends Thread{
    @Override
    public void run(){
        System.out.println(111);
   }
}
class Thread2 implements Runnable{
    public void run() {
        System.out.println(333);
    }
}
/*volatile测试*/
class VolatileTest{
    public volatile int num=0;
    public  void add(){
        num++;
    }
}

