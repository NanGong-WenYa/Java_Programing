package music5;
interface Instrument{
	int VALUE=5;
	void play();
	void adjust();
}

class Playable implements Instrument{
	public void play(){
		System.out.println("play");
	}
	public void adjust(){
		
	}
}

public class Music5 {
	static void tune(Playable p){
		p.play();
	}
	public static void main(String[] args){
		Playable p=new Playable();
		Music5.tune(p);
	}
}
