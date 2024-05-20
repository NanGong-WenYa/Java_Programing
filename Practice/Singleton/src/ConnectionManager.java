	public class ConnectionManager {
	    private static Connection[] connectionArray = new Connection[3];
	    static {
	        for (int i = 0; i < connectionArray.length; i++) {
	            connectionArray[i] = Connection.makeConnection();
	        }
	    }
	    private static int index=0;
	    
	    public static Connection getConnectionArray(){
	    	
	        return connectionArray[index++];
	    }
	    public static void main(String[] args){
	    	ConnectionManager manager = new ConnectionManager();
	        for (Connection c : ConnectionManager.getConnectionArray()) {
	            System.out.println(c==null);
	    }
	        }
	}