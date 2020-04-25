package library.library.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Test {

    public static void  main(String [] args){
        int j=0;
        for(int i=0;i<10;i++){
            j=++j;
        }
        System.out.println(j);
        Book book=new Book();
        book.setAuthor("34");
        book.setBookName("计算机");
        List<Book> list=new ArrayList<>();
        List<Book> l=new ArrayList<>();
        list.add(book);
        Book b=new Book();
        b.setBookName("高等数学");
        b.setAuthor("weiwei");
        Book c=new Book();
        c.setAuthor("luol");
       c.setBookNo("wer");
       list.add(b);
       list.add(c);
       list.stream().forEach(System.out::println);
       long g=list.stream().filter(d->!d.getAuthor().isEmpty()).count();
        System.out.println(g);


    }

}
