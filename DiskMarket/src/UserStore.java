import java.util.Scanner;



public class UserStore {
	private User[] users = new User[100];
	Scanner in = new Scanner(System.in);
	
	public void print(int id){
		if(users[id]==null){
			System.out.println("no such user");
			return;
		}
		System.out.println(users[id]);
	}
	public void print(){
		for (User u : users) {
			if(u!=null){
				System.out.println(u);
			}
			
		}
	}
	public User find(int id){
		return users[id];
	}
	public void addMoney(int userId,int money){
		if(users[userId].getMoney()+money<0){
			users[userId].setMoney(0);
		}
		users[userId].setMoney(users[userId].getMoney()+money);
	}
	public void remove(User u){
		if(users==null){
			System.out.println("no user can be delete");
			return;
		}
		
		users[u.getId()]=null;
	}
	public void add(User u){
		users[u.getId()]=u;
	}
}
