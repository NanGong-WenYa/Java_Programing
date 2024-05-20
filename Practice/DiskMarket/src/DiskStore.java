import java.util.Scanner;



public class DiskStore {
	private Disk[] data = new Disk[100];
	Scanner in = new Scanner(System.in);
	
	public void print(){
		for(Disk d : data){
			if( d !=null){
				System.out.println(d);
			}
		}
	}
	public void print(int id){
		System.out.println(data[id]);
	}
	public void remove(Disk d){
		// d(id,,,num)
		// zugou×ã¹»,
		//²»¹»
		if(data[d.getId()]==null){
			System.out.println("no such disk");
			return;
		}
		if(data[d.getId()].getNum()-d.getNum()<0){
			System.out.println("no so much number of disk can be delete");
			return;
		}
		data[d.getId()].setNum(data[d.getId()].getNum()-d.getNum());
		
	}
	public void add(Disk d){
		int id = d.getId();
		if(data[id] == null){
			data[id] = d;
		}else{
			int num = data[id].getNum();
			num = num+d.getNum();
			data[id].setNum(num);
		}
	}
	public Disk find(int diskId) {
		if(data[diskId]==null){
			System.out.println("DiskStore has no such disk now");
			return null;		}
		return data[diskId];
	}
}
