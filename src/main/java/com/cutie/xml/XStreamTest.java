package com.cutie.xml;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import com.thoughtworks.xstream.io.xml.StaxDriver;

/**
 * Created by cutie on 2018/9/20.
 */
public class XStreamTest {

    public static void main(String[] args) {
        XStream xstream2 = new XStream();
        XStream xstream = new XStream(new DomDriver()); // does not require XPP3 library
        XStream xstream1 = new XStream(new StaxDriver()); // does not require XPP3 library starting with Java 6

        XStreamTest xStreamTest = new XStreamTest();

        xstream.alias("person", Person.class);
        xstream.alias("phonenumber", PhoneNumber.class);

        Person joe = new Person("Joe", "Walnes");
        joe.setPhone(new PhoneNumber(123, "1234-456"));
        joe.setFax(new PhoneNumber(123, "9999-999"));

        String xml = xstream.toXML(joe);

        System.out.println(xml);
    }
}
