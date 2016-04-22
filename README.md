# 简单了解Java集合类

* [1.Collection接口](#1)
* [2. List接口](#2)
* [2.1 ArrayList](#2.1)
* [2.2 LinkedList](#2.2)
* [2.3 Vector](#2.3)
* [2.4 Stack](#2.4)
* [3. Set接口](#3)
* [4. Map接口](#4)
* [4.1 HashMap](#4.1)
* [4.2 Hashtable](#4.2)
* [4.3 TreeMap](#4.3)
* [4.4 WeakHashMap](#4.4)
* [5. Fail-Fast机制简单了解](#5)
* [6. 遍历集合代码示例](#6)
  
**<h6 id="1">1. Collection接口</h6>**

  Collection接口是java集合类最基本的接口，而Collections是针对集合类的一个帮助类，他提供了一系列的静态方法实现对各种集合的搜索、排序、线程安全化等操作，对ArrayList、LinkedList安全化也是借助该工具类

  List和Set接口是继承自Collection接口的子接口，对于集合类中的boolean add(Object o)方法，返回的布尔值不表示添加的成功与否，而是表示执行add()操作后，集合的内容（元素的数量、位置）是否被改变了，其余addAll、remove、removeAll、remainAll方法的返回值也是如此。

  遍历集合可以使用集合类本身提供的iterator()方法，该方法返回当前集合类的迭代子，该迭代子是采用工厂方法产生的，典型的用法如下：
```
 Iterator it = collection.iterator();
while(it.hasNext()) {
    Object o = it.next();
}
```
  但要确保遍历过程顺利完成必须保证遍历过程中不更改集合中的内容，因此遍历时要保证只在一个线程中使用该集合或者在多线程中对遍历代码进行同步处理。

**<h6 id = "2">2. List接口</h6>**

  List是有序的Collection，有序是指元素的次序而非大小顺序，同时也是允许有相同元素的Collection。实现List接口的常用类有ArrayList、LinkedList、Vector、Stack。

**<h6 id="2.1">2.1 ArrayList</h6>**

  **扩容**： ArrayList是**动态数组**，可以根据插入的元素的数量自动扩充容量，而使用者不需要关心它是什么时候扩容的，只要把它当成足够大的数组来使用就行了。ArrayList在插入元素的时候都会检查当前的数组大小是否足够，如果不够，就会进行扩容，然后将原先数组中的元素全部复制到新数组中，这个操作比较耗时，因此在创建ArrayList对象时如果能预估元素的数量Capactity，最好指定ArrayList的初始容量（过大则带来了空间上的损耗）。

  **访问与插入删除操作**： ArrayList可以直接根据下标索引访问元素，因此get()方法是O(1)，而插入和删除时可能需要发生元素的移动，因此add()和remove()方法时间复杂度是O(n)。

  **非线程安全**： ArrayList没有同步，属于非线程安全，在多线程环境下如果出现多个线程需要同时访问ArrayList对象并且存在修改其中元素的操作时，需要自行实现同步机制或者创建ArrayList实例时通过Collections.synchronizedList(new ArrayList()); 方法返回一个同步的实例

**<h6 id= "2.2">2.2 LinkedList</h6>**

  **访问与插入删除操作： **LinkedList 内部实现是使用**双向链表**的方式来保存元素，因此插入与删除元素的性能较ArrayList好，但随机访问元素就需要遍历链表才能找到，性能上比ArrayList差。因此在需要频繁随机访问元素的情况下建议使用ArrayList，在需要频繁随机插入和删除的情况下建议使用LinkedList。

  **可用来实现栈、队列、双向队列：**LinkedList增加了addFirst()、addLast()、removeFirst()、removeLast()等方法，可以将LinkedList当成栈（后进先出）、队列（先进先出）、双向队列来使用，但此时不能使用多态，因为List接口没有定义这些方法.

  **非线程安全**：LinkedList与ArrayList同样属于非线程安全。

**<h6 id="2.3">2.3 Vector</h6>**

Vector是ArrayList的线程安全版本，支持多线程访问，默认初始容量与ArrayList一样是10，扩容算法上也不同

**<h6 id="2.4">2.4 Stack</h6>**

  Stack，即栈结构，其特点是后进先出，继承自Vector类，所以是顺序栈，同时也是线程安全的，要使用链栈可以使用LinkedList

  PS：ArrayList、Vector、Stack是顺序存储结构，LinkedList是链式存储结构,**都允许插入null元素**

**<h6 id="3">3. Set接口</h6>**

  Set是无序的Collection，同时也是不允许有相同元素的Collection（这里的相同是指调用equals方法返回true）。接口相当于一个契约，规定了实现该接口的类需要遵循的行为，Set接口规定了其实现类是"元素是不重复的集合"，而对顺序不做保证，不做限制，有序无序的实现都可以，比如**HashSet**在保存数据的时候是根据哈希函数计算按照一定顺序放入数组中的，但这个顺序不是用户能控制的，所以对用户来说就是无序的。

  又比如Set接口的一个子接口SortedSet接口就规定了其中的元素必须是有序的，能按照用户指定的方式排序的集合。**TreeSet**就是SortedSet接口的唯一实现，因此TreeSet就是有序的。另外一个常用的Set是LinkedHashSet，是HashSet的子类，迭代访问元素的速度比HashSet快，并且能按插入顺序排序（利用链表维护元素的次序），但插入的速度较HashSet慢。

  所有的Set**都是线程不安全的**，多线程并发访问情况下需要自行实现同步控制或者使用Collections的静态方法返回安全的实例。

  **HashSet的底层是基于HashMap实现的（LinkedHashSet基于LinkedHashMap，TreeSet基于TreeMap）**，其元素其实是放在了底层HashMap的Key上，而value是一个Object对象标志，相关的HashSet的操作也是直接调用HashMap来完成的。

**<h6 id="4">4. Map接口（没有继承Collection接口）</h6>**

  Map是一种"链表数组"的结构，提供key到value的映射。一个Map中key是唯一的，每个key只能映射一个value。你也可以把Map看出一组Key集合或一组Value集合或一组Key-Value集合。

  **HashMap存储键值对。**当程序试图将一个key-value对放入 HashMap 中时，程序首先根据该key的hashCode()返回值决定该Entry的存储位置：如果两个Entry的key的hashCode()返回值相同，那它们的存储位置相同。如果这两个Entry的key通过equals比较返回true，新添加Entry的value将覆盖集合中原有Entry的 value，但key不会覆盖。如果这两个Entry的key通过equals比较返回false，新添加的Entry将与集合中原有Entry形成Entry 链，而且新添加的 Entry 位于 Entry 链的头部(使用头插法插入效率会比较高，尾插法需要遍历链表或需要记住链尾)。常用的Map有HashMap、Hashtable。

**<h6 id="4.1">4.1 HashMap</h6>**

  HashMap是非线程安全的，允许Key和Value都为空，其Key使用的哈希值是内部另外实现的方法计算而来，默认初始容量是16，当容量达到负载因子大小时重新分配空间进行再散列扩容，而且一定是以2的指数去扩容。

  迭代HashMap的时间复杂度跟其容量是成正比关系，如果把HashMap的容量设置得过高，或者负载因子（load factor）过低会使迭代性能下降 。
遍历一个map常用的有两种方式：
```
    //方式一：先获取key,然后根据key去获取value，需要遍历两次不建议使用
     Iterator i = map.keySet().iterator();
		while(i.hasNext()) {
			Object key = i.next(); 
			Object value = map.get(key);
		}

    //方式二：只遍历一次，建议使用这种方式
    Iterator i = map.entrySet().iterator();
		while(i.hasNext()) {
			Entry entry = (Entry) i.next();
			Object key = entry.getKey();
			Object value = entry.getValue();
		}
```
**<h6 id="4.2">4.2 Hashtable</h6>**

  Hashtable是HashMap的线程安全版本，其不同之处在于其默认大小是11，不允许空的Key和Value，存储时使用的hash值是直接使用对象的hashCode，再散列扩容时增长的方式是原本的长度*2+1。

**<h6 id="4.3">4.3 TreeMap</h6>**

  TreeMap是jdk提供的唯一有序Map实现类,是利用红黑树来实现的,所以其最坏的插入、删除、查找的时间复杂度是O(logn);

  TreeMap默认情况下是根据key的自然顺序进行排序，也可以在构造函数中指定自己的比较器,同时它也是线程非安全的，支持fail-fast机制。
    
   TreeMap的键值不能为空，value值可以为空

**<h6 id="4.4">4.4 WeakHashMap</h6>**

  WeakHashMap与HashMap最大的不同是WeakHashMap的键是弱键（WeakReference）,当某个键不再被使用时，会从WeakHashMap中自动移除。

  WeakHashMap是通过 WeakReference 和 ReferenceQueue (队列)实现的：

> 
1. 新建WeakHashMap,将key-value添加进WeakHashMap中，即保存进WeakHashMap的table数组中
2. 当某个键不再被其他对象引用后，在GC进行回收的时候会将该弱键回收，并同时将该弱键加入到ReferenceQueue中,即queue属性中
3. 当我们再次操作WeakHashMap时，会先同步table和queue，table保存了全部的键值对，而queue中保存的是被GC回收的键值对，即删除table中被GC回收的键值对。

**<h6 id="5"> 5.Fail-Fast机制简单了解</h6>**

  Fail-Fast机制(快速失败机制)是一种集合类的错误检测机制,直白的说就是当出现遍历和修改操作同时进行时能快速的报错，比如当多个线程对同一个集合进行操作时，如果有线程对该集合进行添加或删除元素的操作 ,那么其他线程在访问该集合时就会抛出ConcurrentModificationException异常,产生Fail-Fast事件; 或者当使用Iterator遍历集合时对该集合进行添加或删除也会发生Fail-Fast事件。
 
  Fail-Fast与是否线程安全无直接关系

  产生Fail-Fast事件的原因：AbstractList中定义了一个modCount和expectedModCount属性，都表示该集合对象元素个数修改的次数，只要涉及到修改集合中的元素个数时,都会改变他们的值,在使用迭代器遍历的时候需要判断他们是否相等，如果不相等就会抛出ConcurrentModificationException，而在获取迭代子之后迭代子中的expectedModCount就不会再发生改变了。

  如果在多线程环境下使用fail-fast机制的集合，建议使用java.util. concurrent包下的线程安全类去取代java .util包下的类。因为这些类不会产生Fail-Fast事件：CopyOnWriteArrayList不会抛ConcurrentModificationException，是因为所有改变其内容的操作（add、remove、clear等），都会copy一份现有数据，在现有数据上修改好，再把原有数据的引用改成指向修改后的数据，而不是在读的时候copy。
