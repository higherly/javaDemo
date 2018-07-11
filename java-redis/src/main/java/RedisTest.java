/*
  1.redis的特点
* 2.redis有哪些类型？
*   string list set zSet(sorted set) hash
* 3.redis内部结构
* 4.redis常见性能问题和解决方案？
*   (1) Master最好不要做任何持久化工作，如RDB内存快照和AOF日志文件
    (Master写内存快照，save命令调度rdbSave函数，会阻塞主线程的工作，当快照比较大时对性能影响是非常大的，会间断性暂停服务，所以Master最好不要写内存快照;AOF文件过大会影响Master重启的恢复速度)
    (2) 如果数据比较重要，某个Slave开启AOF备份数据，策略设置为每秒同步一次
    (3) 为了主从复制的速度和连接的稳定性，Master和Slave最好在同一个局域网内
    (4) 尽量避免在压力很大的主库上增加从库
* 4.redis 使用场景
*   会话缓存 全页缓存 队列 发布/订阅 排行榜/计数器
* 5.Memcache和redis的区别
* 6.redis的优缺点
* 7.redis持久化机制
* 8.Redis 集群方案与实现
  9.Redis 为什么是单线程的
  10.缓存奔溃
    11.缓存降级
    12.使用缓存的合理性问题
   13. Redis的回收策略
        volatile-lru：从已设置过期时间的数据集（server.db[i].expires）中挑选最近最少使用的数据淘汰
        volatile-ttl：从已设置过期时间的数据集（server.db[i].expires）中挑选将要过期的数据淘汰
        volatile-random：从已设置过期时间的数据集（server.db[i].expires）中任意选择数据淘汰
        allkeys-lru：从数据集（server.db[i].dict）中挑选最近最少使用的数据淘汰
        allkeys-random：从数据集（server.db[i].dict）中任意选择数据淘汰
        no-enviction（驱逐）：禁止驱逐数据
*
*
*  http://www.runoob.com/redis/redis-strings.html redis中文官网
* */

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisShardInfo;

public class RedisTest {
    public static void main(String[] args){
        JedisShardInfo mydata = new JedisShardInfo("101.132.119.68", 6379);
        mydata.setPassword("123456");
        Jedis jedis = new Jedis(mydata);
        String data = jedis.getSet("string-key","getset");
        /*String
        * SET key value  设置指定 key 的值
        * GET key  获取指定 key 的值。
        * GETSET key value 将给定 key 的值设为 value ，并返回 key 的旧值(old value)。
        * */
        /*List
         *   LPUSH key value1 [value2] 	将一个或多个值插入到列表头部
         *   LPOP key  移出并获取列表的第一个元素
         *   RPUSH key value1 [value2] 在列表中添加一个或多个值
         *   RPOP key  移除并获取列表最后一个元素
         *   LRANGE key start stop 获取列表指定范围内的元素
         * */
        /*SET 无序和不会出现重复的数据。
        *	SADD key member1 [member2] 向集合添加一个或多个成员
        * 	SMEMBERS key 返回集合中的所有成员
        * */
        /*sorted SET 有序和不会出现重复的数据。
        * 	ZADD key score1 member1 [score2 member2] 向有序集合添加一个或多个成员，或者更新已存在成员的分数
        * ZRANGE key start stop [WITHSCORES]  通过索引区间返回有序集合成指定区间内的成员
        * */
        /* Hash 特别适合用于存储对象。
        *	HMSET key field1 value1 [field2 value2 ] 同时将多个 field-value (域-值)对设置到哈希表 key 中。
        *	HGETALL key 获取在哈希表中指定 key 的所有字段和值
        * */
        /*发布与订阅*/
        /* 	SUBSCRIBE channel [channel ...] 订阅给定的一个或多个频道的信息。
        *   UNSUBSCRIBE [channel [channel ...]] 指退订给定的频道。
        *   PUBLISH channel message 将信息发送到指定的频道
        * */
        System.out.println(data);

    }
}
