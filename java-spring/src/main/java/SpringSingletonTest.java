/**
 * 单例模式 类只有一个实例
 */
public class SpringSingletonTest {
    public static void main(String [] args){

    }
}
/*饿汉式*/
class Singleton1{
    public static Singleton1 singleton1 = new Singleton1();
    public static Singleton1 getInstance(){
        return singleton1;
    }
}
/*懒汉式*/
class Singleton2{
    private static Singleton2 instance = null;
    public static Singleton2 getInstance(){
        if (instance==null){
            synchronized(Singleton2.class){
                instance = new Singleton2();
            }
        }
        return instance;
    }
}
