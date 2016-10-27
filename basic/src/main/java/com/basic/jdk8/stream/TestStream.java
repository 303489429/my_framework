package com.basic.jdk8.stream;

import com.basic.utils.DateUtils;
import com.google.common.base.*;
import com.google.common.collect.Lists;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * Created by wangzhilong on 2016/8/30.
 */
public class TestStream {
    private static List<Person> getPersonCollection(){
        List<Person> persons = Lists.newArrayList(
                new Person("张三",33,1,"51018290128173","15948613529",null),
                new Person("李四",9,1,"51018290128113","15988613529",new Date(235)),
                new Person("张美",21,0,"51018290128172","15528613529",null),
                new Person("苏畅",16,0,"51018290128573","15628613529",new Date(121)),
                new Person("冰冰",18,0,"51018290128673","15428613529",null),
                new Person("王五",12,1,"51018290123173",null,new Date(234)),
                new Person("小燕",8,0,null,null,null)
        );
        return persons ;
    }


    private static void maxEndTime(){
        List<Person> persons = new ArrayList<>( );
       Optional<Person> person = persons
               .stream()
               .filter(v -> v.getEndTime() != null)
               .max(Comparator.comparing(v -> v.getEndTime())) ;
        if(person.isPresent()){
            System.out.println(person.get());
        }
    }



    private static Predicate<Person> pehoneNotNull(){
        return person -> person.getPhone() != null ;
    }

    private static Predicate<Person> isAdultMale(){
        return person -> person.getAge() >= 21 && person.getSex() == 1 ;
    }

    private static Predicate<Person> isAdultFemale(){
        return person -> person.getAge() >= 18 && person.getSex() == 0 ;
    }

    private static Predicate<Person> isJailbait(){
        return person -> person.getAge() < 18 && person.getSex() == 0;
    }


    public static List<Person> filterPerson(List<Person> persons , Predicate<Person> predicate){
        return persons.stream().filter(predicate).collect(Collectors.toList());
    }

    public static List<Person> filterPerson(List<Person> persons, Predicate<Person>... filters){
        Predicate<Person> personPredicate = Stream.of(filters).reduce(f -> true ,Predicate::and) ;
        return persons.stream().filter(personPredicate).collect(Collectors.toList());
    }

    public static void testPerson(){
        getPersonCollection()
                .stream()
                .reduce((p1,p2) -> p1.getAge() > p2.getAge() ? p1 : p2)
                .ifPresent(System.out::println);

        System.out.println("================");

        Person person = getPersonCollection().stream()
                .reduce(new Person("",0,0,"","",null),(p1,p2) -> {
                    p1.setAge(p1.getAge()+p2.getAge());
                    p1.setName(p1.getName()+p2.getName());
                    return p1 ;
                });
        System.out.println("person==="+person);

        int sumage = getPersonCollection()
                .stream()
                .mapToInt(p -> p.getAge())
                .sum();
        System.out.println("sum age:"+sumage);

        int totalAge = getPersonCollection()
                .parallelStream()
                .reduce(0,(sum,p) -> {
                    System.out.println("sum p");
                    return sum += p.getAge();
                }
                ,(sum1,sum2) ->{
                    System.out.println("sum1+sum2");
                    return sum1+sum2 ;
                }
                ) ;
        System.out.println("total age:"+totalAge);

    }

    public static void main(String[] args) throws Exception {
        //System.out.println(filterPerson(getPersonCollection(),isAdultFemale()));
        //List<Person> personList = filterPerson(getPersonCollection(), isJailbait(), pehoneNotNull());
        //System.out.println("size="+personList.size()+","+personList);

        //testMyList();
        //testStream();
        //testPerson();
        //basic();
        //maxEndTime();
        //calc();
        generate();
    }

    public static void generate(){
        //Stream.generate(Math::random).forEach(System.out::println);
        Stream.iterate(1,item -> item + 1).limit(10).peek(i -> {
            if(i < 5){
                System.out.println("i="+i);
            }
        }).forEach(System.out::println);

        List<Integer> nums = Lists.newArrayList(1,1,null,2,3,4,null,5,6,7,8,9,10);
        System.out.println("sum is:"+nums.stream().filter(num -> num !=null).distinct().mapToInt(num -> num * 2).peek(System.out::println).skip(2).limit(4).sum());
    }

    public static void calc() throws ParseException {

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date1 = dateFormat.parse("2017-1-1");
        Date date2 = dateFormat.parse("2018-1-1");
        System.out.println(DateUtils.dayBetWeeb(date1,date2));
    }

    public static void testMyList(){
        List<String> mylist = Arrays.asList("a1","a2","b1","b2","c1","d2");
        mylist.stream()
                .filter(s -> s.startsWith("c"))
                .map(String::toUpperCase) //调用实例方法
                .forEach(System.out::println);
    }

    public static void testStream(){
        Stream.of("a1","a2","a3")
                .findFirst()
                .ifPresent(System.out::println);

        IntStream.range(1,4).forEach(System.out::println);

        IntStream.rangeClosed(2,4).forEach(System.out::println);

        Arrays.stream(new int[]{1,2,3})
                .map(n -> 2 * n + 1)
                .forEach(System.out::println);

        Stream.of("a1","a2","a3")
                .map(s -> s.substring(1))
                .mapToInt(Integer::parseInt)
                .max()
                .ifPresent(System.out::println);
    }

    public static void basic() {
        Supplier<Stream<String>> stringStream =()->Stream.of(new String[]{"a","b","c"});
        List<String> list = stringStream.get().collect(Collectors.toCollection(ArrayList::new));
        Set<String> set1 = stringStream.get().collect(Collectors.toSet());
        Stack stack = stringStream.get().collect(Collectors.toCollection(Stack::new));

        String str = stringStream.get().collect(Collectors.joining());

        System.out.println("str="+str);

        IntStream.of(1,3,5,6).forEach(System.out::print);

         Stream<List<Integer>> inputStream = Stream.of(
                 Arrays.asList(1),
                 Arrays.asList(2,3),
                 Arrays.asList(4,5,6)
         );



    }



}
