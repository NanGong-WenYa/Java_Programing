
import javax.swing.JLabel;

public class NorthLabel extends JLabel {
	private static NorthLabel instance=new NorthLabel();
	private NorthLabel()
	{
		
		super("0");
		setText("demo");
		setHorizonalAlignment(JLabel)
		}
	public static NorthLabel getInstance(){
		return instance;
	}
}
