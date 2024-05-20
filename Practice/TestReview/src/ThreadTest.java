
public class ThreadTest {
	class PrimeFinderThread extends Thread{
		int start ,end;
		
		public PrimeFinderThread(int a,int b){
			this.start=a;
			this.end=b;
			
		}
		
		public void run(){
			for(int i=start;i<end;i++){
				if(isPrime(i))
				System.out.println(Thread.currentThread().getName()+" find prime:"+i);
			}
		}
		
		public boolean isPrime(int num){
			if(num<=1){
				return false;
			}
			else{
				for(int i=2;i<Math.sqrt(i);i++){
					if(num%i==0){
						return false;
					}
				}
			return true;
			}
		}
	}
	
	int start,end;
	int numOfThread=3;
	
	public ThreadTest(int start,int end){
		this.start=start;
		this.end=end;
		
		for(int i=0;i<numOfThread;i++){
			PrimeFinderThread thread=new PrimeFinderThread(start,end);
			thread.start();
			
			start=end+1;
			end=end+1000;
		}
	}
	
//	public static void main(String args[]){
//		ThreadTest t=new ThreadTest(2,3000);
//		
//	}
}
