public class MoneyManager {
    private static int money = 40000;
    private static boolean isBankrupt = false;

//    public static void main(String[] args) {
//        Thread addThread = new Thread(new AddMoney());
//        Thread subThread = new Thread(new SubMoney());
//
//        addThread.start();
//        subThread.start();
//    }

    static class AddMoney implements Runnable {
        public void run() {
            synchronized (MoneyManager.class) {
                money += 8000;
                System.out.println("增加8000，当前金额：" + money);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if (money < 0) {
                    isBankrupt = true;
                    System.out.println("破产啦！");
                    // 唤醒等待的线程
                    
                }
                MoneyManager.class.notify();
                
            }
        }
    }

    static class SubMoney implements Runnable {
        public void run() {
            synchronized (MoneyManager.class) {
                while (money >= 0 && !isBankrupt) {
                    while (money < 10000 && !isBankrupt) {
                        try {
                            MoneyManager.class.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }

                    if (money >= 0 && !isBankrupt) {
                        money -= 10000;
                        System.out.println("减少10000，当前金额：" + money);
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        MoneyManager.class.notify();
                    }
                }
            }
        }
    }
}
