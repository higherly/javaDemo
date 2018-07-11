package basic.thread;

import org.springframework.scheduling.annotation.Scheduled;

import java.util.concurrent.*;

/*
    1.什么是线程池？
        事先将多个线程放到一个容器中，使用的时候不用创建直接从线程池中获取，节省了子线程开辟的时间，提高了代码执行效率。
    2.为什么使用线程池？
        减少了线程创建和销毁的次数，线程可以重复的被使用。
        可以控制线程的数量，防止因内存消耗过多而死机。
     3.常用的线程池有哪几种？
        newFixedThreadPool
        newCachedThreadPool
        newScheduledThreadPool
        newSingleThreadExecutor
        真正的线程池接口是ExecutorService
* */
public class ThreadPoolExecutorTest {
    public static void main(String[] args){
        //创建一个定长线程池，可控制线程最大并发数，超出的线程会在队列中等待。
        /*ExecutorService executorService = Executors.newFixedThreadPool(3);
        for(int i =0;i<10;i++ ){
            executorService.execute(new Thread(){
                @Override
                public void run() {
                   System.out.println(this.currentThread().getName());
                }
            });
        }*/
        //创建一个可缓存线程池，如果线程池长度超过处理需要，可灵活回收空闲线程，若无可回收，则新建线程
        /*ExecutorService cachedExecutor = Executors.newCachedThreadPool();
        for(int i =0;i<10;i++ ) {
            cachedExecutor.execute(new Thread(){
                @Override
                public void run() {
                    System.out.println(this.currentThread().getName());                }
            });
        }*/
        // 创建一个定长线程池，支持定时及周期性任务执行。
      //  ScheduledExecutorService scheduledExecutor = Executors.newScheduledThreadPool(3);
   /* for (int i=0;i<10;i++){
            scheduledExecutor.schedule(new Thread(){
                @Override
                public void run() {
                    System.out.println(this.currentThread().getName()+"三秒后执行");
                }
            },5, TimeUnit.SECONDS);
       }*/
       /*for (int i=0;i<10;i++) {
            scheduledExecutor.scheduleAtFixedRate(new Thread(){
                @Override
                public void run() {
                    System.out.println(this.currentThread().getName()+"三秒后执行");
                }
            },3,5,TimeUnit.SECONDS);//延迟三秒后没五秒执行一次
        }
       )*/
        //创建一个单线程化的线程池，它只会用唯一的工作线程来执行任务，保证所有任务按照指定顺序(FIFO, LIFO, 优先级)执行
    }
}
