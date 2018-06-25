/**
 * 创建线程的方式及实现,最常用的有两种
 * 1，继承Thread类
 * 2.实现Runnable接口
 */


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

