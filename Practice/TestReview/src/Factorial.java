import java.util.Scanner;


public class Factorial {
	int res=1;
	public int calcFac(int num){
		if(num==0||num==1){
			return res;
		}
		else{
			res=res*num;
		}
		return calcFac(num-1);
	}
	
//	public static void main(String args[]){
//		Factorial f=new Factorial();
//		Scanner scanner=new Scanner(System.in);
//		int num=scanner.nextInt();
//		System.out.println("fac:"+f.calcFac(num));
//	}
}
