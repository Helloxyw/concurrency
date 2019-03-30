# 高并发笔记

![](http://pp631mwfb.bkt.clouddn.com/%E7%BA%BF%E7%A8%8B%E5%AE%89%E5%85%A8.jpg)

<h3>线程安全性</h3>

定义：当多个线程访问某个类时，不管运行时环境采用<b>何种调度方式</b>或者这些进程将如何交替执行，并且在主调代码中<b>不需要任何额外的同步或协同</b>，这个类都能表现出<b>正确的行为</b>，那么就称这个类是线程安全的。

* 原子性：提供了互斥访问，同一时刻只能有一个线程来对它进行操作。
* 可见性：一个线程对主内存的修改可以及时的被其他线程观察到。
* 有序性：一个线程观察其他线程中的指令执行顺序，由于指令重排序的存在，该观察结果一般杂乱无序。



<b>原子性——Atomic包</b>

* AtomicXXX：CAS、Unsafe.compareAndSwapInt
* AtomicLong、LongAdder
* AtomicReference、AtomicReferenceFieldUpdater
* AtomicStampReference：CAS的ABA问题

<b>原子性——锁</b>

* synchronized：依赖JVM
* Lock：依赖特殊的CPU指令，代码实现，ReentrantLock

<b>原子性——synchronize</b>

* 修饰代码块：大括号括起来的代码，作用于<b>调用对象</b>
* 修饰方法：整个方法，作用于<b>调用的对象</b>
* 修饰静态方法：整个静态方法，作用于这个<b>类的所有对象</b>
* 修饰类：括号括起来的部分，作用于这个<b>类的所有对象</b>

<b>原子性——对比</b>

* Synchronized：不可中断锁，适合竞争不激烈，可读性好
* Lock：可中断锁，多样化同步，竞争激烈时能维持常态
* Atomic：竞争激烈时能维持常态，比Lock性能好；只能同步一个值

<b>可见性</b>

* 线程交叉执行
* 重排序结合线程交叉执行
* 共享变量更新后的值没有在工作内存及主存空间及时更新

<b>可见性</b>

<b>JMM(Java Memory Model) 关于synchorized 的两条规定:</b>

* 线程解锁前，必须把共享变量的最新值刷新到主内存
* 线程加锁时，将清空工作内存中共享变量的值，从而使用共享变量时需要从主内存中重新读区最新的值（注意，加锁与解锁是同一把锁)

<b>可见性——volatile</b>

通过加入<b>内存屏障</b>和<b>禁止重排序</b>优化来实现

* 对volatile变量写操作时，会在写操作后加入一条store 屏障指令，将本地内存中的共享变量值刷新到主内存![](http://pp631mwfb.bkt.clouddn.com/volatile%E5%86%99.png)
* 对volatile变量读操作时，会在读操作前加入一条load屏障指令，从主内存中读取共享变量![](http://pp631mwfb.bkt.clouddn.com/volatile%E8%AF%BB.png)

<b>可见性——volatile使用（适合作为状态标识量）</b>

```java
volatile boolean inited = false;

//线程1
context = loadContext();
inited = true;

//线程2
while(!inited){
  sleep();
}
doSomethingWithConfig(context);
```

<b>有序性</b>

* java内存模型中，允许编译器和处理器对指令进行<b>重排序</b>，但是重排序过程不会影响到<b>单线程</b>程序的执行，却会影响到多线程并发执行的正确性
* volatile、synchronized、Lock

<b>有序性-happens-before原则</b>

<b>较重要的原则</b>

* 程序次序规则：一个线程内，按照代码顺序，书写在前面的操作先行发生于书写在后面的操作
* 锁定原则：一个unLock操作先行发生于后面对同一个锁的lock操作
* volatile变量规则：对一个变量的写操作先行发生于后面这个变量的读操作
* 传递规则：如果操作A先行发生于操作B，而操作B又先行发生于操作C，则可以得出操作A先行发生于操作C

<b>显而易见的原则</b>

* 线程启动规则：Thread对象的start()方法先行发生于此线程的每一个动作
* 线程中断规则：对线程的interrupt()方法的调用先行发生于被中断线程的代码检测到中断事件的发生
* 线程终结规则：线程中所有的操作都先行发生于线程的终止检测，我们可以通过Thread.join()方法结束、Thread.isAlive()的返回值手段检测到线程已经终止执行
* 对象终结规则：一个对象的初始化完成先行发生于他的finalize()方法的开始



<h3>线程安全性——总结</h3>

* 原子性：Atomic包、CAS算法、synchronized、Lock
* 可见性：synchronized、volatile
* 有序性：happens-before







