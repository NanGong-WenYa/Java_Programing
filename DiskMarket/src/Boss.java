
import java.util.Scanner;

public class Boss {
	private DiskStore ds = new DiskStore();
	private UserStore us = new UserStore();
	
	Scanner in = new Scanner(System.in);
	
	public static void main(String[] args) {
		Boss boss = new Boss();
		boss.begin();
	}
	private void begin() {
		while(true){
			printMainMenu();
			int choice = in.nextInt();
			switch(choice){
			case 1:
				diskManage();
				break;
			case 2:
				userManage();
				break;
			case 3:
				borrowDisk();
				break;
			case 4:
				returnDisk();
			case 5:
				return;
			}
		}
	}
	private void returnDisk() {
		System.out.println("UserId?");
		int userId=in.nextInt();
		System.out.println("diskId?");
		int diskId=in.nextInt();
		User u=us.find(userId);
		Disk d=ds.find(diskId);
		Disk ud=u.getStore().find(diskId);
		d.setNum(d.getNum()+ud.getNum());
		ud.setNum(0);
		
		u.getStore().print();
		ds.print();return;
		
		
	}
	private void borrowDisk(){
		System.out.println("diskID?");
		int diskId = in.nextInt();
		ds.print(diskId);
		System.out.println("disk Num?");
		int diskNum = in.nextInt();
		System.out.println("userID?");
		int userID = in.nextInt();
		us.print(userID);
		System.out.println("zhegedian");
		Disk d  = new Disk(diskId, "", 0, diskNum);
		ds.remove(d);
		
		
		User u = us.find(userID);
		
		u.getStore().add(d);
		
		Disk old = ds.find(diskId);
		
		int money = old.getPrice() * diskNum;
		
		money = u.getMoney() - money;
		
		u.setMoney(money);
		
	}
	private void diskManage() {
		while(true){
			printDiskManageMenu();
			int choice = in.nextInt();
			switch(choice){
			case 1:
				addDisk();
				break;
			case 2:
				removeDisk();
				break;
			case 3:
				ds.print();
				break;
			case 4:
				return;
			}
		}
	}
	private void removeDisk() {
		//id,num
				System.out.println("id:?");
				int id = in.nextInt();
			
				System.out.println("num?");
				int num = in.nextInt();
				Disk d=ds.find(id);
				d.setNum(d.getNum()-num);
				ds.print();
				return;
	}
	private void addDisk() {
		//id,name,price,num
		System.out.println("id:?");
		int id = in.nextInt();
		System.out.println("name?");
		String name = in.next();
		System.out.println("price?");
		int price = in.nextInt();
		System.out.println("num?");
		int num = in.nextInt();
		Disk d = new Disk(id, name, price, num);
		ds.add(d);
		ds.print();
	}
	private void printDiskManageMenu() {
		System.out.println("1:add Disk");
		System.out.println("2:remove disk");
		System.out.println("3:print disk");
		System.out.println("4:return ");
	}
	private void userManage() {
		
		while(true){
			printUserManageMenu();
			int op=in.nextInt();
			switch(op){
			case 1:
				addUser();
				break;
			case 2:
				addUserMoney();
				break;
				
			case 3:
				
				removeUser();
				break;
			case 4:
				us.print();
				break;
			case 5:
				return;
			}
			
		}
		
	}
	private void printUserManageMenu() {
		System.out.println("1:add User");
		System.out.println("2:add User's money");
		System.out.println("3:remove User");
		System.out.println("4:print Users");
		System.out.println("5:return");
		
		
	}
	private void removeUser() {
		System.out.println("id?");
		int id=in.nextInt();
		if(us.find(id)==null){
			System.out.println("no such customer before");
			return;
		}
		us.remove(us.find(id));
		return;
		
	}
	private void  addUserMoney() {
		if(us==null)
		{
			System.out.println("you have no user now1");
			return;
		}
		System.out.println("What's the id of the User?");
		int id=in.nextInt();
		
		
		System.out.println("How Much money do you want to add or remove?");
		
		int change=in.nextInt();
		
		
		User u=us.find(id);
		u.setMoney(u.getMoney()+change);
		us.print();
		return;
	}
	private void addUser() {
		System.out.println("id?");
		int id=in.nextInt();
		System.out.println("name?");
		String name=in.next();
		System.out.println("money?");
		int money=in.nextInt();
		User u=new User(id, name, money);
		us.add(u);
		us.print();
		return;
		
	}
	private void printMainMenu() {
		System.out.println("1:Disk Manage");
		System.out.println("2:User Manage");
		System.out.println("3:Borrow Disk");
		System.out.println("4:Return Disk");
		System.out.println("5:EXIT");
	}
	
}




