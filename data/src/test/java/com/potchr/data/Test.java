package com.potchr.data;

import java.io.Serializable;

public record Test(String a, Integer b) implements Serializable {

    public Test() {
        this("a", 1);
    }

    public static sealed abstract class Pet permits Cat, Dog {

    }

    public static final class Cat extends Pet{

    }

    public static final class Dog extends Pet{

    }
}
