package com.study.studyspringboot.test.serializable;

import java.io.*;

/**
 * 说明：
 *
 * @author xiaoyuqin
 * @date 2020/5/20 17:18
 */
public class TestPersonSerialize {
    public static void main(String[] args) throws Exception {
        serializePerson();
        Person p = deserializePerson();
        System.out.println(p.getName() + "," + p.getAge());

        System.out.println("-----------------------------------");
        serializeBook();
        Book book = deserializeBook();
        System.out.println(book.getPrice() + "," + book.getAuthor());

    }

    private static void serializePerson() throws IOException {
        Person person = new Person();
        person.setName("张三");
        person.setAge(25);
        person.setSex("male");

        ObjectOutputStream oo = new ObjectOutputStream(new FileOutputStream(new File("E:/person.txt")));
        oo.writeObject(person);
        System.out.println("序列化Person成功");
        oo.close();
    }

    private static void serializeBook() throws IOException {
        Book book = new Book(20, "xyq");

        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(new File("E:/book.txt")));
        oos.writeObject(book);

        System.out.println("序列化Book成功");
        oos.close();
    }

    private static Person deserializePerson() throws IOException, ClassNotFoundException {
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream(new File("E:/person.txt")));
        Person person = (Person) ois.readObject();
        System.out.println("反序列化Person成功");
        return person;
    }

    private static Book deserializeBook() throws IOException, ClassNotFoundException {
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream(new File("E:/book.txt")));
        Book book = (Book) ois.readObject();

        System.out.println("反序列化Book成功");
        return book;
    }

}
