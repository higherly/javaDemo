import java.util.concurrent.CountDownLatch;

/**https://www.cnblogs.com/skywang12345/category/455711.html
 *  CountDownLatch的实现原理
 *  CountDownLatch是通过“共享锁”实现的。
 *  在创建CountDownLatch中时，会传递一个int类型参数count，该参数是“锁计数器”的初始状态，表示该“共享锁”最多能被count个线程同时获取。
 *  当某线程调用该CountDownLatch对象的await()方法时，该线程会等待“共享锁”可用时，才能获取“共享锁”进而继续运行。
 *  而“共享锁”可用的条件，就是“锁计数器”的值为0！而“锁计数器”的初始值为count，每当一个线程调用该CountDownLatch对象的countDown()方法时，才将“锁计数器”-1；
 *  通过这种方式，必须有count个线程调用countDown()之后，“锁计数器”才为0，而前面提到的等待线程才能继续运行！
 */
public class CountDownLatchTest {
    public static void main(String[] args) throws Exception{
        CountDownLatch countDownLatch = new CountDownLatch(5);
        for (int i=0;i<5;i++){
            new Thread3(countDownLatch).start();
        }
        countDownLatch.await();
        System.out.println("所有线程已经执行完毕");
    }

}
 class Thread3 extends Thread{
    private  CountDownLatch countDownLatch;
    public Thread3(CountDownLatch countDownLatch){
        this.countDownLatch =countDownLatch;
    }
    @Override
    public void run() {
        try{
            Thread.sleep(1000);
            System.out.println(Thread.currentThread().getName()+" 1000ms");
            countDownLatch.countDown();
        }catch (InterruptedException  e){

        }
    }
}
