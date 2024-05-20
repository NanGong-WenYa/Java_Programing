
public class EatingAction {
	public static void  main(String args[]){
		Rodent[] rod = new Rodent[2];
		rod[0]=new Mouse();
		rod[1]=new Gerbil();
		
		rod[0].eat();
		rod[1].eat();
	}
}
