package com.hgh;

public class TestThread implements Runnable {

    int sum = 0;
    @Override
    public void run() {


            for (int i = 1; i <= 10000; i++) {

                sum = sum + i;
            }
            System.out.println(sum);

    }


    public static void main(String[] args) {



        for (int i = 0; i <= 200; i++) {
           synchronized (TestThread.class){
               TestThread t1 = new TestThread();
               new Thread(t1).start();
           }
        }
        System.out.println("asdasdasdasd");

    }
}
