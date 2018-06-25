import java.util.concurrent.Semaphore;

/**
 * 说说 Semaphore 原理
 *首先定义信号量数值的操作,
 * 在信号量上我们定义两种操作： acquire（获取） 和 release（释放）。当一个线程调用acquire操作时，
 * 它要么通过成功获取信号量（信号量减1），要么一直等下去，直到有线程释放信号量，或超时。release（释放）实际上会将信号量的值加1，然后唤醒等待的线程。
 信号量主要用于两个目的，一个是用于多个共享资源的互斥使用，另一个用于并发线程数的控制。
 */
public class SemaphoreTest {
    public static void main(String[] args){
        final Semaphore windows = new Semaphore(3);
        for(int i=0;i<5;i++){
            new Thread(){
                @Override
                public void run() {
                    try {
                        windows.acquire();
                        System.out.println(Thread.currentThread().getName()+"  开始购票");
                        Thread.sleep(2000);
                        windows.release();
                        System.out.println(Thread.currentThread().getName()+"  购票成功");
                    }catch(Exception e){

                    }
                }
            }.start();
        }
    }
}
