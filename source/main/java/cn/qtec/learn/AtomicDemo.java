package cn.qtec.learn;

import java.util.concurrent.atomic.*;

/**
 * Created by duhc on 2018/4/12.
 */
public class AtomicDemo {
    public static AtomicReference<User> atomicReference =new AtomicReference<>();
    public static void main(String[] args) {
        AtomicInteger index = new AtomicInteger();
        index.incrementAndGet();
        index.getAndIncrement();
        int i1 = index.get();
        int i = index.intValue();
        index.decrementAndGet();
        index.getAndDecrement();
        index.compareAndSet(1,1);
        index.addAndGet(1);
        index.lazySet(2);
        AtomicBoolean bool = new AtomicBoolean();
        bool.getAndSet(true);
        index.getAndSet(10);
        int[] ss = new int[]{1,3,2,12,33};
        AtomicIntegerArray integerArray = new AtomicIntegerArray(ss);
        System.out.println("###################");
        User user = new User("abc",10);
        atomicReference.set(user);
        User update = new User("chis",32);
        atomicReference.compareAndSet(user,update);
        User user1 = atomicReference.get();
        int age = atomicReference.get().getAge();
        System.out.println(atomicReference.get().age);

        AtomicStampedReference<Integer> stampedReference = new AtomicStampedReference<>(1,1);
//        stampedReference.compareAndSet()
    }
    static class User{
        private String name;
        private int age;

        public User(String name, int age) {
            this.name = name;
            this.age = age;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }

        @Override
        public boolean equals(Object obj) {
            return false;
        }
    }
}
