package org.example;

/**
 * Algorithm: x requests per y seconds
 *
 */
public class Main {

    public static void main(String[] args) throws InterruptedException {
        RateLimiter rate = new RateLimiter();

        System.out.println(rate.isAllowed(100));
        Thread.sleep(105);
        System.out.println(rate.isAllowed(100));
        System.out.println(rate.isAllowed(100));
        System.out.println(rate.isAllowed(100));
        System.out.println(rate.isAllowed(100));
        System.out.println(rate.isAllowed(100));
//        Thread.sleep(1500);
        System.out.println(rate.isAllowed(100));
//        Thread.sleep(1500);
//        System.out.println(rate.isAllowed(100));
//        System.out.println(rate.isAllowed(100));
//        System.out.println(rate.isAllowed(100));
//        System.out.println(rate.isAllowed(100));
//        System.out.println(rate.isAllowed(100));
    }
}