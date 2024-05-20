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
                System.out.println("����8000����ǰ��" + money);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if (money < 0) {
                    isBankrupt = true;
                    System.out.println("�Ʋ�����");
                    // ���ѵȴ����߳�
                    
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
                        System.out.println("����10000����ǰ��" + money);
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
