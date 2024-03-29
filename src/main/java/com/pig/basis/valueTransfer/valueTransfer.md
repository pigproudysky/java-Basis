# **_值传递知识点记录_**

# __1.形参与实参__

        1.形参：方法被调用时需要传递进来的参数，如：func(int a)中的a，它只有在func被调用期间a才有意义，也就是会被分配内存空间，在方法func执行完成后，a就会被jvm销毁并释放空间，也就是不存在了
        2.实参：方法被调用时是传入的实际值，它在方法被调用前就已经被初始化并且在方法被调用时传入。
        
# __2.Java的数据类型__

        所谓数据类型，是编程语言中对内存的一种抽象表达方式，我们知道程序是由代码文件和静态资源组成，在程序被运行前，这些代码存在在硬盘里，程序开始运行，这些代码会被转成计算机能识别的内容放到内存中被执行。
        定义：数据类型实质上是用来定义编程语言中相同类型的数据的存储形式，也就是决定了如何将代表这些值的位存储到计算机的内存中。
        
        所以，数据在内存中的存储，是根据数据类型来划定存储形式和存储位置的。
        
        1.基本类型：编程语言中内置的最小粒度的数据类型。它包括四大类八种类型：
               4种整数类型：byte、short、int、long
               2种浮点数类型：float、double
               1种字符类型：char
               1种布尔类型：boolean
               
        2.引用类型：引用类型：引用也叫句柄，引用类型，是编程语言中定义的在句柄中存放着实际内容所在地址的地址值的一种数据形式。它主要包括：
               类
               接口
               数组
               
# __3.jvm内存的职能及划分__

        Java语言本身是不能操作内存的，它的一切都是交给JVM来管理和控制的，因此Java内存区域的划分也就是JVM的区域划分，Java代码被编译器编译成字节码之后，JVM开辟一片内存空间（也叫运行时数据区），通过类加载器加到到运行时数据区来存储程序执行期间需要用到的数据和相关信息，在这个数据区中，它由以下几部分组成：
        
        1.虚拟机栈
            虚拟机栈是Java方法执行的内存模型，栈中存放着栈帧，每个栈帧对应着一个被调用的方法，包括main方法，方法的调用就对应着栈帧在虚拟机中的入栈到出栈
            栈是线程私有的，线程之间的栈是隔离的，当线程开始执行一个方法的时候，线程就会创建一个栈帧并入栈(位于栈顶)，同时虚拟机栈就会分配给它内存空间，方法结束后，栈帧出栈
            
            栈帧：是用于支持虚拟机进行方法调用和方法执行的数据结构，它是虚拟机运行时数据区中的虚拟机栈的栈元素。
            
            栈帧中包括：
                1.局部变量表:**用来存储方法中的局部变量（非静态变量、函数形参）。当变量为基本数据类型时，直接存储值，当变量为引用类型时，存储的是指向具体对象的引用。**
                2.操作数栈:Java虚拟机的解释执行引擎被称为"基于栈的执行引擎"，其中所指的栈就是指操作数栈。
                3.指向运行时常量池的引用:存储程序执行时可能用到常量的引用。
                4.方法返回地址:存储方法执行完成后的返回地址。

        2.堆
            堆是用来存储对象本身和数组的，在JVM中只有一个堆，因此，堆是被所有线程共享的。Java的垃圾回收特性就是操作这个数据区来回收对象进而释放内存。
           
        3.方法区
            方法区是一块所有线程共享的内存逻辑区域，在JVM中只有一个方法区，用来存储一些线程可共享的内容，它是线程安全的，多个线程同时访问方法区中同一个内容时，只能有一个线程装载该数据，其它线程只能等待。
            方法区可存储的内容有：类的全路径名、类的直接超类的权全限定名、类的访问修饰符、类的类型（类或接口）、类的直接接口全限定名的有序列表、常量池（字段，方法信息，静态变量，类型引用（class））等。
            
       4.本地方法栈
            本地方法栈的功能和虚拟机栈是基本一致的，并且也是线程私有的，它们的区别在于虚拟机栈是为执行Java方法服务的，而本地方法栈是为执行本地方法服务的。Java官方对于本地方法的定义为methods written in a language other than the Java programming language，就是使用非Java语言实现的方法，但是通常我们指的一般为C或者C++，因此这个栈也有着C栈这一称号。一个不支持本地方法执行的JVM没有必要实现这个数据区域。本地方法栈基本和JVM栈一样，其大小也是可以设置为固定值或者动态增加，因此也会对应抛出StackOverflowError和OutOfMemoryError错误。
            
       5.程序计数器
            线程私有的。
            记录着当前线程所执行的字节码的行号指示器，在程序运行过程中，字节码解释器工作时就是通过改变这个计数器的值来选取下一条需要执行的字节码指令，分支、循环、异常处理、线程恢复等基础功能都需要依赖计数器完成。
       
# __4.数据如何在内存中存储__
       1.基本数据类型的局部变量
            定义基本数据类型的局部变量以及数据都是存储在虚拟机栈中，而且数据本身的值也是存储在栈中，这个值就是真实的值
            
            当我们写“int age=50；”，其实是分为两步的：
            int age;//定义变量
            age=50;//赋值复制代码
            首先JVM创建一个名为age的变量，存于局部变量表中，然后去栈中查找是否存在有字面量值为50的内容，如果有就直接把age指向这个地址，如果没有，JVM会在栈中开辟一块空间来存储“50”这个内容，并且把age指向这个地址。因此我们可以知道：我们声明并初始化基本数据类型的局部变量时，变量名以及字面量值都是存储在栈中，而且是真实的内容。
            我们再来看“int weight=50；”，
            按照刚才的思路：字面量为50的内容在栈中已经存在，因此weight是直接指向这个地址的。
            **由此可见：栈中的数据在当前线程下是共享的**。
            那么如果再执行下面的代码呢？
            1weight=40；复制代码
            当代码中重新给weight变量进行赋值时，JVM会去栈中寻找字面量为40的内容，发现没有，就会开辟一块内存空间存储40这个内容，并且把weight指向这个地址。由此可知：
            **基本数据类型的数据本身是不会改变的，当局部变量重新赋值时，并不是在内存中改变字面量内容，而是重新在栈中寻找已存在的相同的数据，若栈中不存在，则重新开辟内存存新数据，并且把要重新赋值的局部变量的引用指向新数据所在地址。**
           
       2.基本数据类型的成员变量
            基本数据类型的成员变量名和值都存储于堆中，其生命周期和对象的是一致的。
            
       3.基本数据类型的静态变量
            基本数据类型的静态变量名以及值存储于方法区的运行时常量池中，静态变量随类加载而加载，随类消失而消失 
       
       4.引用数据类型的存储
            对于引用数据类型的对象/数组，变量名存在栈中，变量值存储的是对象的地址，并不是对象的实际内容。
       
# __5.值传递和引用传递__
       1.值传递
            在方法被调用时，实参通过形参把它的内容副本传入方法内部，此时形参接收到的内容是实参值的一个拷贝，因此在方法内对形参的任何操作，都仅仅是对这个副本的操作，不影响原始值的内容。
            
            当调用一个方法时，jvm为其往虚拟机栈中压入一个栈帧，其中存放了该方法中的局部变量，调用change方法时，通过形参传入的实参其实是原来实参的副本，或者说是复制品，所以改变值的其实是这个副本，当方法结束以后，该副本随着方法结束被销毁。
            
            值传递传递的是真实内容的一个副本，对副本的操作不影响原内容，也就是形参怎么变化，不会影响实参对应的内容。
       
       2.引用传递
            ”引用”也就是指向真实内容的地址值，在方法调用时，实参的地址通过方法调用被传递给相应的形参，在方法体内，形参和实参指向通愉快内存地址，对形参的操作会影响的真实内容。
            
            new一个对象的时候，对象引用保存在虚拟机栈里，他的值是对象在堆里的地址，
            
            引用传递，在Java中并不存在
            
            无论是基本类型和是引用类型，在实参传入形参时，都是值传递，也就是说传递的都是一个副本，而不是内容本身
            

**如果是对基本数据类型的数据进行操作，由于原始内容和副本都是存储实际值，并且是在不同的栈区，因此形参的操作，不影响原始内容。
如果是对引用类型的数据进行操作，分两种情况，一种是形参和实参保持指向同一个对象地址，则形参的操作，会影响实参指向的对象的内容。一种是形参被改动指向新的对象地址（如重新赋值引用），则形参的操作，不会影响实参指向的对象的内容。**

       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       