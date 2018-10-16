package com.cutie.java8;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.io.File;
import java.io.IOException;
import java.lang.annotation.*;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.*;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Java 8新特性终极指南
 * https://wizardforcel.gitbooks.io/java8-tutorials/content/Java%208%20%E6%96%B0%E7%89%B9%E6%80%A7%E7%BB%88%E6%9E%81%E6%8C%87%E5%8D%97.html
 * Created by Cutie on 2018/3/2.
 */
public class Starter {

    public static void main(String[] args) {
//        doSomeTest();

        Defaulable defaulable = DefaultableFactory.create(DefaultableImpl::new);
        System.out.println(defaulable.notRequired());

        defaulable = DefaultableFactory.create(OverridableImpl::new);
        System.out.println(defaulable.notRequired());
    }

    //2.1 Lambda表达式与Functional接口
    public static void doSomeTest() {
        Arrays.asList("a", "b", "c").forEach(e -> System.out.println(e));
        Arrays.asList("a", "b", "c").forEach((String e) -> System.out.println(e));
        // separator 会隐式转换成final
        String separator = ",";
        Arrays.asList("a", "b", "c").forEach((String e) -> {
            System.out.println(e + separator);
        });

        //下面的两个是等价的
        Arrays.asList("a", "b", "c").sort((e1, e2) -> e1.compareTo(e2));
        Arrays.asList("a", "b", "c").sort(Comparator.naturalOrder());
        Arrays.asList("a", "b", "c").sort(String::compareTo);
        Arrays.asList("a", "b", "c").sort((e1, e2) -> {
            int result = e1.compareTo(e2);
            return result;
        });
    }

    //默认方法与静态方法并不影响函数式接口的契约，可以任意使用
    @FunctionalInterface
    public interface Functional {
        void method();

        default void defaultMethod() {
            System.out.println("defaultMethod");
        }
    }

    //2.2 接口的默认方法与静态方法
    //可以重写 default 方法
    private interface Defaulable {
        default String notRequired() {
            return "Defaulable notRequired";
        }
    }

    private static class DefaultableImpl implements Defaulable {
    }

    private static class OverridableImpl implements Defaulable {
        @Override
        public String notRequired() {
            return "OverridableImpl Overridden notRequired";
        }
    }

    //Java 8带来的另一个有趣的特性是接口可以声明（并且可以提供实现）静态方法
    private interface DefaultableFactory {
        static Defaulable create(Supplier<Defaulable> supplier) {
            return supplier.get();
        }
    }

    public static class Car {
        //第一种方法引用是构造器引用，它的语法是Class::new，或者更一般的Class< T >::new。请注意构造器没有参数。
        public static Car create(final Supplier<Car> supplier) {
            return supplier.get();
        }

        //第二种方法引用是静态方法引用，它的语法是Class::static_method。请注意这个方法接受一个Car类型的参数。
        public static void collide(final Car car) {
            System.out.println("Collide" + car.toString());
        }

        //第三种方法引用是特定类的任意对象的方法引用，它的语法是Class::method。请注意，这个方法没有参数。
        public void repair() {
            System.out.println("Repaired " + this.toString());
        }

        //第四种方法引用是特定对象的方法引用，它的语法是instance::method。请注意，这个方法接受一个Car类型的参数
        public void follow(final Car another) {
            System.out.println("Following the " + another.toString());
        }

        public static void main(String[] args) {
            //1 static Supplier parameter
            final Car car = Car.create(Car::new);
            //2 static
            final List<Car> cars = Arrays.asList(car);
            cars.forEach(Car::collide);
            //3 no parameters
            cars.forEach(Car::repair);
            //4 none static ,have one parameter
            final Car police = Car.create(Car::new);
            cars.forEach(police::follow);
        }
    }

    //2.4 重复注解
    //Java 8打破了这条规则，引入了重复注解机制，这样相同的注解可以在同一地方声明多次。
    public static class RepeatingAnnotations {
        @Target(ElementType.TYPE)
        @Retention(RetentionPolicy.RUNTIME)
        public @interface Filters {
            Filter[] value();
        }

        @Target(ElementType.TYPE)
        @Retention(RetentionPolicy.RUNTIME)
        @Repeatable(Filters.class)
        public @interface Filter {
            String value();
        }

        ;

        @Filter("filter1")
        @Filter("filter2")
        public interface Filterable {
        }

        public static void main(String[] args) {
            for (Filter filter : Filterable.class.getAnnotationsByType(Filter.class)) {
                System.out.println(filter.value());
            }
        }
    }


    //2.5 更好的类型推测机制
    //Value.defaultValue()的参数类型可以被推测出，所以就不必明确给出。在Java 7中，相同的例子将不会通过编译，正确的书写方式是 Value.< String >defaultValue()。
    public static class Value<T> {
        public static <T> T defaultValue() {
            return null;
        }

        public T getOrDefault(T value, T defaultValue) {
            return (value != null) ? value : defaultValue;
        }

        public static void main(String[] args) {
            final Value<String> value = new Value<>();
            System.out.println(value.getOrDefault("22", Value.defaultValue()));
        }
    }

    //2.6 扩展注解的支持
    //Java 8扩展了注解的上下文。现在几乎可以为任何东西添加注解：局部变量、泛型类、父类与接口的实现，就连方法的异常也能添加注解。
    //ElementType.TYPE_USE和ElementType.TYPE_PARAMETER是两个新添加的用于描述适当的注解上下文的元素类型。在Java语言中，注解处理API也有小的改动来识别新增的类型注解。
    public static class Annotation {
        @Retention(RetentionPolicy.RUNTIME)
        @Target({ElementType.TYPE_USE, ElementType.TYPE_PARAMETER})
        public @interface NonEmpty {

        }

        public static class Holder<@NonEmpty T> extends @NonEmpty Object {
            public void method() throws @NonEmpty Exception {

            }
        }

        public static void main(String[] args) {
            final Holder<String> holder = new @NonEmpty Holder<>();
            @NonEmpty Collection<@NonEmpty String> strings = new ArrayList<>();
        }
    }


    //3. Java编译器的新特性
    // 3.1 参数名字
    //Java程序员一直在发明不同的方式使得方法参数的名字能保留在Java字节码中，并且能够在运行时获取它们（比如，Paranamer类库）。
    // 最终，在Java 8中把这个强烈要求的功能添加到语言层面（通过反射API与Parameter.getName()方法）与字节码文件（通过新版的javac的–parameters选项）中。
    public static class ParameterNames {
        public static void main(String[] args) throws NoSuchMethodException {
            Method method = ParameterNames.class.getMethod("main", String[].class);
            for (final Parameter parameter : method.getParameters()) {
                System.out.println("parameterName:" + parameter.getName());
            }
        }
    }

    //4. Java 类库的新特性
    //Java 8 通过增加大量新类，扩展已有类的功能的方式来改善对并发编程、函数式编程、日期/时间相关操作以及其他更多方面的支持。

    //4.1 Optional
    //到目前为止，臭名昭著的空指针异常是导致Java应用程序失败的最常见原因。以前，为了解决空指针异常，Google公司著名的Guava项目引入了Optional类，
    // Guava通过使用检查空值的方式来防止代码污染，它鼓励程序员写更干净的代码。受到Google Guava的启发，Optional类已经成为Java 8类库的一部分。
    //Optional实际上是个容器：它可以保存类型T的值，或者仅仅保存null。Optional提供很多有用的方法，这样我们就不用显式进行空值检测。更多详情请参考官方文档。
    //我们下面用两个小例子来演示如何使用Optional类：一个允许为空值，一个不允许为空值。
    public static class ForOptionl {
        public static void main(String[] args) {
            //如果Optional类的实例为非空值的话，isPresent()返回true，否从返回false。为了防止Optional为空值，orElseGet()方法通过回调函数来产生一个默认值。map()函数对当前Optional的值进行转化，
            // 然后返回一个新的Optional实例。orElse()方法和orElseGet()方法类似，但是orElse接受一个默认值而不是一个回调函数。
            Optional<String> fullName = Optional.ofNullable(null);
            System.out.println("Full Name is set? " + fullName.isPresent());
            System.out.println("Full Name: " + fullName.orElseGet(() -> "[none]"));
            System.out.println(fullName.map(s -> "Hey " + s + "!").orElse("Hey Stranger!"));

            fullName = Optional.ofNullable("cutie");
            System.out.println("Full Name is set? " + fullName.isPresent());
            System.out.println("Full Name: " + fullName.orElseGet(() -> "[none]"));
            System.out.println(fullName.map(s -> "Hey " + s + "!").orElse("Hey Stranger!"));
        }
    }


    //4.2 Stream
    //最新添加的Stream API（java.util.stream） 把真正的函数式编程风格引入到Java中。这是目前为止对Java类库最好的补充，
    // 因为Stream API可以极大提供Java程序员的生产力，让程序员写出高效率、干净、简洁的代码。
    //Stream API极大简化了集合框架的处理（但它的处理的范围不仅仅限于集合框架的处理，这点后面我们会看到）。
    public static class Streams {
        private enum Status {
            OPEN, CLOSED
        }

        private static final class Task {
            private Status status;
            private Integer points;

            Task(final Status status, final Integer points) {
                this.status = status;
                this.points = points;
            }

            public Integer getPoints() {
                return points;
            }

            public Status getStatus() {
                return status;
            }

            @Override
            public String toString() {
                return String.format("[%s, %d]", status, points);
            }
        }

        public static void main(String[] args) throws IOException {
            final Collection<Task> taskCollection = Arrays.asList(
                    new Task(Status.OPEN, 5),
                    new Task(Status.OPEN, 13),
                    new Task(Status.CLOSED, 8)
            );
            //我们下面要讨论的第一个问题是所有状态为OPEN的任务一共有多少分数？在Java 8以前，一般的解决方式用foreach循环，但是在Java 8里面我们可以使用stream：一串支持连续、并行聚集操作的元素。
            final long openPoints = taskCollection
                    .stream()
                    .filter(task -> task.getStatus().equals(Status.OPEN))
                    .mapToInt(Task::getPoints)
                    .sum();
            System.out.println("openPoints" + openPoints);
            /*这里有几个注意事项。
            第一，task集合被转换化为其相应的stream表示。然后，filter操作过滤掉状态为CLOSED的task。
            下一步，mapToInt操作通过Task::getPoints这种方式调用每个task实例的getPoints方法把Task的stream转化为Integer的stream。
            最后，用sum函数把所有的分数加起来，得到最终的结果。
            在继续讲解下面的例子之前，关于stream有一些需要注意的地方（详情在这里）.stream操作被分成了中间操作与最终操作这两种。
            中间操作返回一个新的stream对象。中间操作总是采用惰性求值方式，运行一个像filter这样的中间操作实际上没有进行任何过滤，
            相反它在遍历元素时会产生了一个新的stream对象，这个新的stream对象包含原始stream 中符合给定谓词的所有元素。
            像forEach、sum这样的最终操作可能直接遍历stream，产生一个结果或副作用。当最终操作执行结束之后，stream管道被认为已经被消耗了，
            没有可能再被使用了。在大多数情况下，最终操作都是采用及早求值方式，及早完成底层数据源的遍历。
            stream另一个有价值的地方是能够原生支持并行处理。让我们来看看这个算task分数和的例子。*/
            final double totalPoints = taskCollection
                    .stream()
                    .parallel()
                    //or map(Task::getPoints)
                    .map(task -> task.getPoints())
                    .reduce(0, Integer::sum);
            System.out.println("totalPoints:" + totalPoints);
            //经常会有这个一个需求：我们需要按照某种准则来对集合中的元素进行分组。Stream也可以处理这样的需求，下面是一个例子：
            final Map<Status, List<Task>> statusListMap = taskCollection.stream().collect(Collectors.groupingBy(Task::getStatus));
            System.out.println(statusListMap);
            //我们来计算整个集合中每个task分数（或权重）的平均值来结束task的例子。
            final Collection<String> result = taskCollection
                    .stream()                                        // Stream< String >
                    .mapToInt(Task::getPoints)                     // IntStream
                    .asLongStream()                                  // LongStream
                    .mapToDouble(points -> points / totalPoints)   // DoubleStream
                    .boxed()                                         // Stream< Double >
                    .mapToLong(weigth -> (long) (weigth * 100)) // LongStream
                    .mapToObj(percentage -> percentage + "%")      // Stream< String>
                    .collect(Collectors.toList());                 // List< String >
            System.out.println(result);
            //最后，就像前面提到的，Stream API不仅仅处理Java集合框架。像从文本文件中逐行读取数据这样典型的I/O操作也很适合用Stream API来处理。下面用一个例子来应证这一点。
            String fineName = "C:\\Users\\Administrator\\Desktop\\courier_settlement_sql_change.txt";
            final Path path = new File(fineName).toPath();
            try (Stream<String> lines = Files.lines(path, StandardCharsets.UTF_8)) {
                lines.onClose(() -> System.out.println("Done!")).forEach(System.out::println);
            }

        }
    }

    //4.3 Date/Time API (JSR 310)
    public static class DateUtilz {
        public static void main(String[] args) {
            //用例子来看一下新版API主要类的使用方法。第一个是Clock类，它通过指定一个时区，然后就可以获取到当前的时刻，日期与时间。Clock可以替换System.currentTimeMillis()与TimeZone.getDefault()
            final Clock clock = Clock.systemUTC();
            System.out.println(clock.instant());
            System.out.println(clock.millis());
            //我们需要关注的其他类是LocaleDate与LocalTime。LocaleDate只持有ISO-8601格式且无时区信息的日期部分。
            // 相应的，LocaleTime只持有ISO-8601格式且无时区信息的时间部分。LocaleDate与LocalTime都可以从Clock中得到。
            final LocalDate localDate = LocalDate.now();
            final LocalDate dateFromClock = LocalDate.now(clock);
            System.out.println(localDate);
            System.out.println(dateFromClock);

            final LocalTime localTime = LocalTime.now();
            final LocalTime timeFromClock = LocalTime.now(clock);
            System.out.println(localTime);
            System.out.println(timeFromClock);

            final LocalDateTime datetime = LocalDateTime.now();
            final LocalDateTime datetimeFromClock = LocalDateTime.now(clock);
            System.out.println(datetime);
            System.out.println(datetimeFromClock);

            //时区
            final ZonedDateTime zonedDatetime = ZonedDateTime.now();
            final ZonedDateTime zonedDatetimeFromClock = ZonedDateTime.now(clock);
            final ZonedDateTime zonedDatetimeFromZone = ZonedDateTime.now(ZoneId.of("America/Los_Angeles"));
            System.out.println(zonedDatetime);
            System.out.println(zonedDatetimeFromClock);
            System.out.println(zonedDatetimeFromZone);

            //最后，让我们看一下Duration类：在秒与纳秒级别上的一段时间。Duration使计算两个日期间的不同变的十分简单。下面让我们看一个这方面的例子。
            final LocalDateTime from = LocalDateTime.of(2014, Month.APRIL, 16, 0, 0, 0);
            final LocalDateTime to = LocalDateTime.of(2015, Month.APRIL, 16, 23, 59, 59);

            final Duration duration = Duration.between(from, to);
            System.out.println("Duration in days: " + duration.toDays());
            System.out.println("Duration in hours: " + duration.toHours());

            System.out.println("new Date();" + new Date());
        }
    }

    //4.4 JavaScript引擎Nashorn
    //Nashorn，一个新的JavaScript引擎随着Java 8一起公诸于世，它允许在JVM上开发运行某些JavaScript应用。
    // Nashorn就是javax.script.ScriptEngine的另一种实现，并且它们俩遵循相同的规则，允许Java与JavaScript相互调用。
    public static class ForNashorn{
        public static void main(String[] args) throws ScriptException {
            ScriptEngineManager scriptEngineManager = new ScriptEngineManager();
            ScriptEngine scriptEngine = scriptEngineManager.getEngineByName("JavaScript");
            System.out.println("result:" + scriptEngine.eval("function f(){return 1;} f()+1;"));
        }
    }

    //4.5 Base64
    //在Java 8中，Base64编码已经成为Java类库的标准
    public static class ForBase64{
        public static void main(String[] args) {
            final String text = "Base64 finally in java!";
            final String encoded = Base64.getEncoder().encodeToString(text.getBytes(StandardCharsets.UTF_8));
            System.out.println("encoded"+encoded);
            final String decoded = new String(Base64.getDecoder().decode(encoded),StandardCharsets.UTF_8);
            System.out.println("decoded"+decoded);
        }
    }

    //4.6 并行（parallel）数组
    //Java 8增加了大量的新方法来对数组进行并行处理。可以说，最重要的是parallelSort()方法，因为它可以在多核机器上极大提高数组排序的速度
    public static class ForParallel{
        public static void main(String[] args) {
            long[] arrayOfLong = new long [ 20000 ];

            System.out.println("size:"+arrayOfLong.length+",arrayOfLong[19999]"+ arrayOfLong[19999]);
            Arrays.parallelSetAll( arrayOfLong, index -> ThreadLocalRandom.current().nextInt(100000));
            System.out.println("size:"+arrayOfLong.length+",arrayOfLong[19999]"+ arrayOfLong[19999]);
            Arrays.stream(arrayOfLong).limit(20).forEach(i -> System.out.print(i + " "));
            System.out.println();
            Arrays.parallelSort(arrayOfLong);
            Arrays.stream(arrayOfLong).limit(20).forEach(i -> System.out.print(i + " "));
            System.out.println();
        }
    }

    //5. 新的Java工具

    //Java 8也带来了一些新的命令行工具。在这节里我们将会介绍它们中最有趣的部分。
    //5.1 Nashorn引擎: jjs

    //jjs是个基于Nashorn引擎的命令行工具。它接受一些JavaScript源代码为参数，并且执行这些源代码

    //5.2 类依赖分析器jdeps
    //jdeps是一个很有用的命令行工具。它可以显示Java类的包级别或类级别的依赖。它接受一个.class文件，一个目录，或者一个jar文件作为输入。jdeps默认把结果输出到系统输出（控制台）上。
    //下面我们查看现阶段较流行的Spring框架类库的依赖报告，为了简化这个例子，我们只分析一个jar文件：org.springframework.core-3.0.5.RELEASE.jar
    //jdeps org.springframework.core-3.0.5.RELEASE.jar

    //6. Java虚拟机（JVM）的新特性
    //PermGen空间被移除了，取而代之的是Metaspace（JEP 122）。JVM选项-XX:PermSize与-XX:MaxPermSize分别被-XX:MetaSpaceSize与-XX:MaxMetaspaceSize所代替。

}

























