public class FastFoodRestaurant {
    private String lastClientName;
    private int numberOfBurgersSold;

    public void buyBurger(String clientName) {
        alongRunningBurger();
        this.lastClientName = clientName;
        numberOfBurgersSold++;
        System.out.println(clientName + " bought a burger");
    }

    public void alongRunningBurger() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public String getLastClientName() {
        return lastClientName;
    }

    public void setLastClientName(String lastClientName) {
        this.lastClientName = lastClientName;
    }

    public int getNumberOfBurgersSold() {
        return numberOfBurgersSold;
    }

    public void setNumberOfBurgersSold(int numberOfBurgersSold) {
        this.numberOfBurgersSold = numberOfBurgersSold;
    }

    public static void main(String[] args) throws InterruptedException {
        long startTime = System.currentTimeMillis();
        FastFoodRestaurant fastFoodRestaurant = new FastFoodRestaurant();
        Thread t1 = new Thread(() -> {
            fastFoodRestaurant.buyBurger("Mile");
        });

        Thread t2 = new Thread(() -> {
            fastFoodRestaurant.buyBurger("Syed");
        });

        Thread t3 = new Thread(() -> {
            fastFoodRestaurant.buyBurger("Jean");
        });

        Thread t4 = new Thread(() -> {
            fastFoodRestaurant.buyBurger("Amy");
        });

        t1.start();
        t2.start();
        t3.start();
        t4.start();

        t1.join();
        t2.join();
        t3.join();
        t4.join();

        System.out.println("Total number of burger sold: " + fastFoodRestaurant.numberOfBurgersSold);
        System.out.println("The last name of client is: "+ fastFoodRestaurant.lastClientName);
        System.out.println("Total execution time: " + (System.currentTimeMillis() - startTime) + " in ms");
    }
}
