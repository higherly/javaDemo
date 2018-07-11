import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/*
* 0.深入浅出CAS(https://blog.csdn.net/ls5718/article/details/52563959)，业务场景及实现方式。
* 独占锁是一种悲观锁，synchronized就是一种独占锁，会导致其它所有需要锁的线程挂起，等待持有锁的线程释放锁。而另一个更加有效的锁就是乐观锁。
* 所谓乐观锁就是，每次不加锁而是假设没有冲突而去完成某项操作，如果因为冲突失败就重试，直到成功为止。乐观锁用到的机制就是CAS.
* CAS 操作包含三个操作数 —— 内存位置（V）、预期原值（A）和新值(B)。 如果内存位置的值与预期原值相匹配，
* 那么处理器会自动将该位置值更新为新值 。否则，处理器不做任何操作。
* 1.ABA问题
* 2. 循环时间长开销大
*
* 1.ArrayList内部是如何实现的？
* ArrayList不是线程安全的，只能用在单线程环境下。
实现了Serializable接口，因此它支持序列化，能够通过序列化传输；
实现了RandomAccess接口，支持快速随机访问，实际上就是通过下标序号进行快速访问；
实现了Cloneable接口，能被克隆。
*   ArrayList 的底层是数组。构造函数有三种，不带参数，带int型参数以及Collection的子类。
*   add()有两个重载，一个是在最后添加数据，一个是再指定的位置添加数据，如果添加数据时，size()超过了分配的空间，将会按照1.5倍进行扩容。
*
*   * 2.集合的安全性如何实现？
*       Vector和HashTable是线程安全的。其实就是在核心方法上添加了synchronized关键字。
*       其他的都不是。Collections工具类提供了方法将集合变为安全的。原理也是核心方法上添加了synchronized关键字。
* 3.Set的三个子类的特点？
*       HashSet
*       LinkedHashSet
*       TreeSet
* 4.Map的三个子类的特点？
*       HashMap 是基于hash表Map接口的非同步实现，不是线程安全的，以键值对的形式存储数据，两者都可以为null,hashMap不保证数据的插入顺序。
*       LinkedHashMap
*　　　 TreeMap　　　　
*       HashTable HashTable和HashMap的实现原理几乎一样，差别无非是1.HashTable不允许key和value为null；2.HashTable是线程安全的。     　　　　　　　　　　
* 5.List的三个子类的特点？
*       ArrayList是的底层是数组，查询快，插入和删除慢。
*       LinkedList的底层是链表，查询慢，插入和删除快。
*       Vector的底层是数组，查询，插入和删除都慢。
* 5.HashMap 和 ConcurrentHashMap 的区别
* 6.HashMap 的工作原理及代码实现(http://www.cnblogs.com/chengxiao/p/6059914.html#t2)
*   HashMap是基于hash表和链表以及红黑树实现的。利用key的hashCode()进行hash计算从而找出在hash表的位置，如果这个位置为null,则直接存储，如果不为空，
*   则判断是否存在此key,有的话，就替换，没有的话，就插入到链表中。在jdk1.8以上，如过链表长度超过8时，则会转换为红黑树。
* hashmap为什么进行扩容？
*   当hashmap中的元素越来越多的时候，碰撞的几率也就越来越高（因为数组的长度是固定的），
*   所以为了提高查询的效率，就要对hashmap的数组进行扩容。最消耗性能的点就出现了：原数组中的数据必须重新计算其在新数组中的位置，并放进去，这就是resize。
* ，默认情况下，数组大小为16，那么当hashmap中元素个数超过16*0.75=12的时候，就把数组的大小扩展为2*16=32，即扩大一倍，然后重新计算每个元素在新数组中的位置
* 7.ConcurrentHashMap 的工作原理及代码实现（https://www.cnblogs.com/chengxiao/p/6842045.html ）OminiGraffle 制图软件
*       ConcurrentHashMap是Java并发包中提供的一个线程安全且高效的HashMap实现。
*       在1.8版本以前，ConcurrentHashMap采用分段锁的概念，采用Segment + HashEntry的方式进行实现，初始化时，
*       计算出Segment数组的大小ssize和每个Segment中HashEntry数组的大小cap，由于Segment在实现上继承了ReentrantLock，这样就自带了锁的功能。
*       当进行put时，利用key的hashCode()进行hash计算从而找出在segment中的位置，如果该Segment还没有初始化，即通过CAS操作进行赋值。
*       然后进行第二次hash操作，找到相应的HashEntry的位置，
*       通过继承ReentrantLock的tryLock（）方法尝试去获取锁，如果获取成功就直接插入相应的位置，如果已经有线程获取该Segment的锁，那当前线程会以自旋的方式去继续的调用tryLock（）方法去获取锁，超过指定次数就挂起，等待唤醒。
*       接着执行Segment对象的put方法通过加锁机制插入数据。
*       size操作：
*       如果前后两次计算结果相同，则说明计算出来的元素个数是准确的；
*       如果前后两次计算结果都不同，则给每个Segment进行加锁，再计算一次元素的个数；
*       但是1.8已经改变了这种思路，而是利用CAS+Synchronized来保证并发更新的安全，当然底层采用Node数组+链表+红黑树的存储结构。
*       第一次put方法时会调用initTable()初始化Node数组,数组大小为16.（https://blog.csdn.net/fjse51/article/details/55260493）
*       put方法插入数据时，根据key的hash值，在Node数组中找到相应的位置,如果这个位置为空，直接新建节点。
*       如果这个位置的hash值为-1的话，则表示正在扩容。返回扩容后的Node数组。
*       否则对这个位置加上synchronized锁，如果当在链表中key存在的话，则更新值，不存在的话，就添加一个节点。当节点数不小于8时，该链表就转换为红黑树。
*       如果插入了一个新节点，则更新size。
* */
public class CollectionTest {
    public static void main(String[] args){
        /*
        * <<=
        * >>=
        * */
       /* int a=1;
        System.out.println(1<<2);
        Vector v = new Vector();
        HashMap hashMap = new HashMap();
        hashMap.put("1","2");
        HashMap m =(HashMap) Collections.synchronizedMap(new HashMap());
        ArrayList list1 =(ArrayList) Collections.synchronizedList(new ArrayList());
        HashSet set =(HashSet) Collections.synchronizedSet(new HashSet());
        ConcurrentHashMap  cMap = new ConcurrentHashMap();
        cMap.put("1","2");
        cMap.get("1");*/
        ArrayList list = new ArrayList();
        list.add(1);
        list.add(2);
        list.get(1);
        System.out.println(list);
    }
}
