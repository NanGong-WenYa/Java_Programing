
 public class Detergent extends Cleanser {
	public Kit k;
	Detergent(){
		
	}
	Detergent(int m,int n){
		k=new Kit();
		k.b=n;
	}
	
	public void scrub(){
		append("Detergent.scrub()");
		super.scrub();
	}
	public static void main(String[] args){
		Detergent d=new Detergent(1,2);
		
	}
}
