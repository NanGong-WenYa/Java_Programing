public class AppleDealer {
    private int apple = 1000;
    private boolean selling = true;
    private Object lock = new Object();

    private Thread sell = new Thread(new Runnable() {
        public void run() {
            while (selling) {
                synchronized (lock) {
                    if (apple >= 100) {
                        apple = apple - 100;
                        System.out.println("����������" + apple);
                        lock.notify();
                        System.out.println("runһ��");
                            try {
								lock.wait();
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
                    } else {
                        selling = false;
                        System.out.println("�����ˣ�");
                        lock.notify();
                    }
                }
            }
           
        }
    });

    private Thread getter = new Thread(new Runnable() {
        public void run() {
            while (selling) {
                synchronized (lock) {
                    apple = apple + 50;
                    System.out.println("����������" + apple);
                    lock.notify();
                    System.out.println("runһ��");
                       try {
						lock.wait();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
                }
            }
            
        }
      
    });

    public AppleDealer(int a) throws InterruptedException {
        this.apple = a;
        sell.start();
        getter.start();
        sell.join();
        getter.join();
    }

    public static void main(String args[]) throws InterruptedException {
        AppleDealer a = new AppleDealer(500);
    }
}
