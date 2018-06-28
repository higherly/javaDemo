import java.io.*;

/**
 * 1.流的类型有哪几种？
 * 按照传输单位字节流和字符流，按照传输方向输入流和输出流，按照功能分为节点流和处理流，节点流是直接操纵数据源的流，而处理流是对已存在的流的封装和处理，提供更加强大的读写功能。
 * 2.字节流转换为字符流？InputStreamReader和OutputStreamWriter
 * 3.java对象序列化到文件里？
 * 5.如何实现对象克隆?
 * 实现Cloneable接口,并且重写Object类中的clone()方法,实现Serializable接口,然后通过对象的序列化和反序列化进行克隆。
 * 6.什么是java序列化？如何实现java序列化？
 *  处理java对象流的机制,对象流是将对象的内容进行流化.
 */
public class IoTest {
    public static void main(String[] args) throws Exception{
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("C:\\Users\\Administrator.SKY-20180207TKT\\Desktop\\1.txt"));//序列化
        oos.writeObject(new JavaTest("gx",17));
        oos.close();
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream("C:\\Users\\Administrator.SKY-20180207TKT\\Desktop\\1.txt"));//反序列化
        JavaTest jt = (JavaTest)ois.readObject();
        ois.close();
        System.out.println(jt);
    }

}
class JavaTest implements Serializable{
    private String name;
    private int age;

    public JavaTest(String name, int age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public String toString() {
        return "JavaTest{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}