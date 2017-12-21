package biz.matt.concurrency;

public class HelloRunnableDriver implements Runnable {

    public void run() {
        System.out.println("Hello from a runnable!");
    }

    public static void main(String args[]) {
        (new Thread(new HelloRunnableDriver())).start();
    }

}