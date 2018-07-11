import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;

/*GC：垃圾回收机制
* 1.为什么会有GC机制呢?
*   安全性考虑;减少内存泄漏;减少程序员工作量
* 2.java中GC那些内存需要回收？
*   无论是虚拟机栈,程序计数器以及本地方法栈均属于线程私有,
*   其生命周期与线程的生命周期一致,当线程执行完毕,其所占用的内存空间也就随之释放,
*   因此这三部分是不存在垃圾回收问题的.Java开发者平常所说的垃圾回收,主要针对的是堆区和方法区而言的,
*   对象实例在程序运行时被在存放在堆区.在堆区中,JVM为每个对象分配的内存空间大小并不确定,所以这部分存在垃圾回收问题。
*   方法区中主要需要回收的是一些常量和和无用的类。
* 3.JAVA的GC什么时候回收垃圾？
*   现在我们得知堆区中存在垃圾回收问题,可是如何确定堆区中哪些对象是有用的,哪些对象是垃圾,这就又涉及到了垃圾检测问题.一般垃圾检测有以下方法:
    1.引用计数器:为每一个对象添加一个引用计数器,当有地方引用该对象时,这个计数器+1,当引用失效是则-1,当计数器为0时则视该对象为垃圾对象.但这种检测方式存在问题,
    那就是两个对象互相访问,计数器不会为0,但实际上这两个对象已经无法访问,导致垃圾对象无法正常回收.
    2可达性分析算法:针对于上述垃圾检测机制的问题,所以还有另一种检测方式.
    以根集对象(这里讲的根集对象,一般指的是虚拟机栈中引用的对象,方法区常量池引用的对象,
    本地方法中引用的对象)为起始点进行搜索，如果发现有对象不可达的话,即视为垃圾对象.
    一般在JVM进行垃圾回收的时候,会检索堆中的所有对象是否会被这些根集对象引用,如果发现不能被引用的对象,则该对象就会被垃圾回收器进行回收.
    那么垃圾对象被检测到了,如何处理这些垃圾对象也是一门学问,这里就涉及到了一些回收算法.
    1.标记清除算法 先标记,后清除,标记所有需要进行回收的垃圾对象,然后进行统一回收。效率低,且容易造成大量内存碎片.
    2.复制算法 把内存分为两块，每次使用一块，垃圾回收时，把存活的对象复制到另一块，这一块清理掉。造成空间浪费。
    3.标记整理算法 把存活的对象压缩到一块,按顺序排放,直接回收边界以外的内存。这样就避免了内存碎片问题和空间浪费问题.
    4.分代回收  根据对象的存活时间把内存分为新生代和老年代，新生代采用复制算法，老年代采用标记-整理算法。
  4.java中四种引用类型？
    1.强引用 类似Object obj = new Object()这类的引用，只要强引用还存在，垃圾收集器永远不会回收掉被引用的对象。
    哪怕内存不足时，系统会直接抛出异常OutOfMemoryError。
    2.软引用 当内存足够时不会回收这种引用类型的对象，只有当内存不够用时才会回收
    3.弱引用 GC一运行就会把给回收了
    4.虚引用 (PhantomReference) 一创建这种类型的引用，那么他所引用类型就回收了
* 4.既然有GC,为什么还存在内存泄漏发生？
* */
public class GCTest {
    public static void main(String[] args){
        /*强引用*/
       /* GcPerson person = new GcPerson("jack");
        System.gc();
        System.out.println(person);*/
       /*软引用*/
      /* GcPerson person = new GcPerson("jack");
        SoftReference<GcPerson> softReference = new SoftReference<GcPerson>(person);
        System.out.println(softReference.get());
        System.gc();
        System.out.println(softReference.get());*/
        /*虚引用*/
        GcPerson jack = new GcPerson("Jack");
        WeakReference<GcPerson> personSoftReference = new WeakReference<GcPerson>(jack);
        System.out.println(personSoftReference.get());
        jack = null;
        System.gc();
        System.gc();
        System.out.println(personSoftReference.get());
    }
}
class GcPerson{
    private String name;
    public GcPerson(String name){
        this.name=name;
    }
    @Override
    public String toString() {
        return "GcPerson{" +
                "name='" + name + '\'' +
                '}';
    }
}
