
import ch.qos.logback.core.net.SyslogOutputStream;
import com.qipai.domain.Book;
import jdk.nashorn.internal.runtime.options.Option;
import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Slf4j
public class LabmdaTest {
    @Test
    public void test(){
        books().stream().map(Book::getName).collect(Collectors.toList()).forEach(x->log.debug(x));
        log.debug("hello-------{}","king");
    }

    @Test
    public void list_to_map(){
        Map<Integer,Book> map = books().stream().collect(Collectors.toMap(Book::getId,book -> book));
        log.debug(map.toString());
    }

    @Test
    public void test2(){
        String a = books().stream().map(book -> "'"+book.getId()+"'").collect(Collectors.joining(",","(",")"));
        log.debug(a);
    }

    @Test
    public void  test3(){
        List<String> list = books().stream().map(Book::getType).collect(Collectors.toList());
        log.debug(list.toString());
    }

    @Test
    public void  价钱排序_从低到高(){
        books().stream().sorted((b1,b2) -> Double.compare(b1.getPrice(),b2.getPrice())).collect(Collectors.toList()).forEach(x-> log.debug(x.toString()));
    }

    @Test
    public void  价钱排序_从低到高_使用其他实现_从低到高(){
        books().stream().sorted(Comparator.comparing(Book::getPrice)).collect(Collectors.toList()).forEach(x-> log.debug(x.toString()));
    }
    @Test
    public void  价钱排序_从低到高_使用其他实现_从高到低(){
        books().stream().sorted(Comparator.comparing(Book::getPrice).reversed()).collect(Collectors.toList()).forEach(x-> log.debug(x.toString()));
    }

    @Test
    public void  平均价格(){
        Double avg = books().stream().collect(Collectors.averagingDouble(Book::getPrice));
        log.debug(avg.toString());
    }

    @Test
    public void  最高价格(){
        Optional<Book> avg =  books().stream().collect(Collectors.maxBy(Comparator.comparing(Book::getPrice)));
        log.debug(avg.toString());
    }

    @Test
    public void  最低价格(){
        Optional<Book> avg =  books().stream().collect(Collectors.minBy(Comparator.comparing(Book::getPrice)));
        log.debug(avg.toString());
    }

    @Test
    public void  过滤(){
        books().stream().filter(book -> book.getPrice()>80).collect(Collectors.toList()).forEach(book->log.debug(book.toString()));
    }

    @Test
    public void  过滤最高价格最早日期(){
        Optional<Book> book2 = books().stream().filter(book -> book.getPrice()>80).collect(Collectors.maxBy(Comparator.comparing(Book::getPrice).reversed()));
        log.debug(book2.toString());
    }

    @Test
    public void 多从比较() {
        //比较对象设置-->价格属性
        Comparator<Book> comp = Comparator.comparing(Book::getPrice);
        //比较对象设置--》时间排序（默认倒叙，从大到小）
        Comparator<Book> da = Comparator.comparing(Book::getPublishDate).reversed();
        Optional<Book> book2 = books().stream().collect(Collectors.maxBy(comp.thenComparing(da)));
        log.debug(book2.toString());
    }

    @Test
    public void 按类型做map和list() {
        Map<String,List<Book>> map = books().stream().filter(book -> book.getPrice()>60).collect(Collectors.groupingBy(Book::getType));
        log.debug(map.toString());
    }

    private List<Book> books(){
        List<Book> bs = new ArrayList<>();
        bs.add(new Book(1,"tomcat",50d,"服务器", LocalDate.parse("2014-05-17")));
        bs.add(new Book(2,"jetty",60d,"服务器", LocalDate.parse("2015-12-01")));
        bs.add(new Book(3,"nginx",65d,"服务器", LocalDate.parse("2016-10-18")));
        bs.add(new Book(4,"java",66d,"编程语言", LocalDate.parse("2011-04-09")));
        bs.add(new Book(5,"ruby",80d,"编程语言", LocalDate.parse("2013-05-18")));
        bs.add(new Book(6,"php",40d,"编程语言", LocalDate.parse("2018-08-18")));
        bs.add(new Book(7,"html",44d,"编程语言", LocalDate.parse("2011-01-06")));
        bs.add(new Book(8,"oracle",150d,"数据库", LocalDate.parse("2013-08-09")));
        bs.add(new Book(9,"mysql",66d,"数据库", LocalDate.parse("2015-10-08")));
        bs.add(new Book(10,"ssh",70d,"编程语言", LocalDate.parse("2016-12-18")));
        bs.add(new Book(11,"设计模式",81d,"其他", LocalDate.parse("2017-10-06")));
        bs.add(new Book(12,"重构",62d,"其他", LocalDate.parse("2012-10-11")));
        bs.add(new Book(13,"敏捷开发",72d,"其他", LocalDate.parse("2016-06-18")));
        bs.add(new Book(14,"从技术到管理",42d,"其他", LocalDate.parse("2019-10-18")));
        bs.add(new Book(15,"算法导论",58d,"其他", LocalDate.parse("2018-10-18")));
        bs.add(new Book(16,"oracle12c",150d,"数据库", LocalDate.parse("2017-08-09")));
        return bs;
    }
}
