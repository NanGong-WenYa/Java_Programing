
public class Amphibian {
public final int a;
Amphibian(){
	a=5;
	System.out.print("a has been initiate");
}
public static void main(String[] args){
	System.out.println("class Frog has not been loaded");
	Frog f=new Frog();
	
}
}
