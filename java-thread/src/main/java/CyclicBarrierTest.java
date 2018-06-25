import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * 说说 CyclicBarrier 原理
 * 在CyclicBarrier的内部定义了一个Lock对象，
 * 每当一个线程调用CyclicBarrier的await方法时，将剩余拦截的线程数减1，
 * 然后判断剩余拦截数是否为0，如果不是，进入Lock对象的条件队列等待。
 * 如果是，执行barrierAction对象的Runnable方法，然后将锁的条件队列中的所有线程放入锁等待队列中，这些线程会依次的获取锁、释放锁，
 */
public class CyclicBarrierTest {
    public static void main(String[] args) throws Exception{
        CyclicBarrier cyclicBarrier = new CyclicBarrier(5,new Runnable(){
            @Override
            public void run() {
                try{
                    Thread.sleep(3000);
                    System.out.println("都已经准备好了");
                }catch (Exception e){

                }
            }
        });
        for (int i=0;i<5;i++){
            new Thread4(cyclicBarrier).start();
        }
    }
}
class Thread4 extends Thread{
    private  CyclicBarrier cyclicBarrier;
    public Thread4(CyclicBarrier cyclicBarrier){
        this.cyclicBarrier =cyclicBarrier;
    }
    @Override
    public void run() {
        try{
            System.out.println(Thread.currentThread().getName()+"wait");
            cyclicBarrier.await();
            System.out.println(Thread.currentThread().getName()+"continue");
        }catch (BrokenBarrierException e){

        }catch (InterruptedException e){

        }
    }
}