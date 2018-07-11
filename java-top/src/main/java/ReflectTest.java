/**https://www.jianshu.com/p/8dc84550296f
 * 谈谈你对java中反射的理解？
 *在运行状态中，对于任意一个类，都能够知道这个类的所有属性和方法；
 * 对于任意一个对象，都能够访问它的任意方法和属性；这种动态获取信息以及动态调用对象方法的功能称为java语言的反射机制。
 *  .class文件在运行时会被ClassLoader(类加载器)加载到Java虚拟机（JVM）中，当一个.class文件被加载后，
 *  JVM会为之生成一个Class对象(java.lang.Class)，也就是我们所说的字节码。
 *  要使用反射首先要获取这个Class对象，有三种方法分别1.Class.forName(className),2。类.class，3this.getClass()
 *  然后我们就可以获取Method,Constructor,Field等信息。
 *  */
public class ReflectTest {
    public static void main(String[] args) throws Exception{
        //1.拿到Class对象
        Class cls = Fruit.class;
        //Class cls1 =  Class.forName("ReflectTest.Fruit");
        //根据拿到的Class对象创建我们所需的对象：
        Fruit fruit = (Fruit) cls.newInstance();//注意该方法返回的Object对象，所以我们需要强制类型转换；
    }
}
class Fruit {
    public Fruit(){
        System.out.println(111);
    }
}
