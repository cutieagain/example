/**
 * Created by Cutie on 2018/2/6.
 */
package com.cutie.java_grammar.concurrency.transfer_queue;
/*
 * article 1:
 * 阻塞队列之LinkedTransferQueue
 * http://blog.csdn.net/yjian2008/article/details/16951811
 * <p>
 * TransferQueue是是ConcurrentLinkedQueue、SynchronousQueue (公平模式下)、无界的LinkedBlockingQueues等的超集。
 * <p>
 * LinkedTransferQueue实现了一个重要的接口TransferQueue，该接口含有下面几个重要方法：
 * 1. transfer(E e)：若当前存在一个正在等待获取的消费者线程，即立刻移交之；否则，会插入当前元素e到队列尾部，并且等待进入阻塞状态，到有消费者线程取走该元素。
 * 2. tryTransfer(E e)：若当前存在一个正在等待获取的消费者线程（使用take()或者poll()函数），使用该方法会即刻转移/传输对象元素e；若不存在，则返回false，并且不进入队列。这是一个不阻塞的操作。
 * 3. tryTransfer(E e, long timeout, TimeUnit unit)：若当前存在一个正在等待获取的消费者线程，会立即传输给它;否则将插入元素e到队列尾部，并且等待被消费者线程获取消费掉；若在指定的时间内元素e无法被消费者线程获取，则返回false，同时该元素被移除。
 * 4. hasWaitingConsumer()：判断是否存在消费者线程。
 * 5. getWaitingConsumerCount()：获取所有等待获取元素的消费线程数量。
 * 6.size()：因为队列的异步特性，检测当前队列的元素个数需要逐一迭代，可能会得到一个不太准确的结果，尤其是在遍历时有可能队列发生更改。
 * 7.批量操作：类似于addAll，removeAll, retainAll, containsAll, equals, toArray等方法，API不能保证一定会立刻执行。因此，我们在使用过程中，不能有所期待，这是一个具有异步特性的队列。
 * <p>
 * 如果我们采用SynchronousQueue作为ThreadPoolExecuto的缓冲队列时，在没有线程执行poll时(即存在等待线程)，则workQueue.offer(command)返回false,这时ThreadPoolExecutor就会增加线程，
 * 最快地达到最大线程数。但也仅此而已，也因为SynchronousQueue本身不存在容量,也决定了我们一般无法采用SynchronousQueue作为ThreadPoolExecutor的缓存队列。而一般采用LinkedBlockingQueue
 * 的offer方法来实现。最新的LinkedTransferQueue也许可以帮我们解决这个问题。transfer算法比较复杂，大致的理解是采用所谓双重数据结构(dual data structures)。之所以叫双重，其原因是方法都
 * 是通过两个步骤完成：保留与完成。比如消费者线程从一个队列中取元素，发现队列为空，他就生成一个空元素放入队列,所谓空元素就是数据项字段为空。然后消费者线程在这个字段上旅转等待。这叫保留。
 * 直到一个生产者线程意欲向队例中放入一个元素，这里他发现最前面的元素的数据项字段为NULL，他就直接把自已数据填充到这个元素中，即完成了元素的传送。
 * <p>
 * 注意事项：
 * A、无论是transfer还是tryTransfer方法，在>=1个消费者线程等待获取元素时（此时队列为空），都会立刻转交，这属于线程之间的元素交换。注意，这时，元素并没有进入队列。
 * B、在队列中已有数据情况下，transfer将需要等待前面数据被消费掉，直到传递的元素e被消费线程取走为止。
 * C、使用transfer方法，工作者线程可能会被阻塞到生产的元素被消费掉为止。
 * D、消费者线程等待为零的情况下，各自的处理元素入队与否情况有所不同。
 * E、size()方法，需要迭代，可能不太准确，尽量不要调用。
 *
 * article 2:
 * TransferQueue实例 对比，使用场景
 * https://www.jianshu.com/p/b3e97770c551
 **/