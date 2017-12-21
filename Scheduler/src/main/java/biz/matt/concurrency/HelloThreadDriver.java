package biz.matt.concurrency;

public class HelloThreadDriver extends Thread {

    public void run() {
        System.out.println("Hello from a thread!");
    }

    public static void main(String args[]) {
        (new HelloThreadDriver()).start();
    }

}
