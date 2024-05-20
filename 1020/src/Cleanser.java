
public class Cleanser {
	public int a;
	private String s ="Cleanser";
	Cleanser(){
		
	}
	Cleanser(int a){
		System.out.println("This is a Cleanser");
	}
	public void append(String a){
		s+=a;
	}
	public void apply(){
		append("apply()");
	}
	public void scrub(){
		append("scrub()");
	}
	
}
