/**
 * Created by Administrator on 2018/6/5.
 */
public class SnowflakeTest {

    public static void main(String[] args){
        SnowflakeIdWorker idWorker = new SnowflakeIdWorker(0, 0);
        for (int i = 0; i < 1000; i++) {
            long id = idWorker.nextId();
            //System.out.println(Long.toBinaryString(id));
            System.out.println(id);
        }
    }
}
