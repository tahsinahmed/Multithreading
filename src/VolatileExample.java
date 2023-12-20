public class VolatileExample {
    public static volatile boolean flag = false;

    public static void main(String[] args) {
        new Thread(() -> {
            for (int i=0; i<2000; i++)
                System.out.println("Value of i: "+ i);
            flag=true;
            System.out.println("The value of flag is: " + flag);
        }).start();

        new Thread(() -> {
            int i=0;
            while (!flag) {
                ++i;
            }
            System.out.println("The value of i in second Thread is: " + i);
        }).start();
    }
}
