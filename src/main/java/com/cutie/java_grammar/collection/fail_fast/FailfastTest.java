package com.cutie.java_grammar.collection.fail_fast;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Cutie on 2018/1/16.
 */
public class FailfastTest {
    public static List<Student> students = new ArrayList<>();

    public List<Student> getStudents() {
        return students;
    }

    class Student{
        private int id;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }
    }



    /**
     * 使用增强的for循环
     * 在循环过程中从List中删除非基本数据类型以后，继续循环List时会报ConcurrentModificationException
     */
    public void listRemove() {
        List<Student> students = this.getStudents();
        for (Student stu : students) {
            if (stu.getId() == 2)
                students.remove(stu);
        }
    }



    /**
     * 像这种使用增强的for循环对List进行遍历删除，但删除之后马上就跳出的也不会出现异常
     */
    public void listRemoveBreak() {
        List<Student> students = this.getStudents();
        for (Student stu : students) {
            if (stu.getId() == 2) {
                students.remove(stu);
                break;
            }
        }
    }


    /**
     * 这种不使用增强的for循环的也可以正常删除和遍历,
     * 这里所谓的正常是指它不会报异常，但是删除后得到的
     * 数据不一定是正确的，这主要是因为删除元素后，被删除元素后
     * 的元素索引发生了变化。假设被遍历list中共有10个元素，当
     * 删除了第3个元素后，第4个元素就变成了第3个元素了，第5个就变成
     * 了第4个了，但是程序下一步循环到的索引是第4个，
     * 这时候取到的就是原本的第5个元素了。
     */
    public void listRemove2() {
        List<Student> students = this.getStudents();
        for (int i=0; i<students.size(); i++) {
            if (students.get(i).getId()%3 == 0) {
                Student student = students.get(i);
                students.remove(student);
            }
        }
    }



    /**
     * 使用Iterator的方式可以顺利删除和遍历
     */
    public void iteratorRemove() {
        List<Student> students = this.getStudents();
        System.out.println(students);
        Iterator<Student> stuIter = students.iterator();
        while (stuIter.hasNext()) {
            //next()方法必须在调用remove()方法之前调用。如果在循环过程中先调用remove()，再调用next()，就会导致异常ConcurrentModificationException。
            Student student = stuIter.next();
            if (student.getId() % 3 == 0)
                stuIter.remove();//这里要使用Iterator的remove方法移除当前对象，如果使用List的remove方法，则同样会出现ConcurrentModificationException
        }
        System.out.println(students);
    }

    public static void main(String[] args) {
        FailfastTest failfastTest = new FailfastTest();
        for (int i = 0; i < 10; i++) {
            Student student = failfastTest.new Student();
            student.setId(i);
            students.add(student);
        }
        for (Student student:students) {
            System.out.print(student.getId() +",");
        }
        System.out.println();
//        failfastTest.listRemove();
//        failfastTest.listRemoveBreak();
//        failfastTest.listRemove2();
        failfastTest.iteratorRemove();//只有这个是正确的，删除的时候建议使用迭代器

        for (Student student:students) {
            System.out.print(student.getId() +",");
        }
        System.out.println();
        System.out.println(students.size());
    }
}
