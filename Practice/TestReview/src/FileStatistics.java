import java.io.File;


public class FileStatistics {
	int fileCount=0;
	int folderCount=0;
	int totalSize=0;
	
	public void countFile(File f){
		File[] files=f.listFiles();
		for(File file:files){
			if(file.isFile()){
				fileCount++;
				totalSize+=file.length();
			}
			else 
				if(file.isDirectory()){
					folderCount++;
					countFile(file);
				}
		}
	}
	
	public static void main(String args[]){
		String filePath="C:/Users/Lenovo/Desktop/java";
		File folder=new File(filePath);
		
		FileStatistics fileSta=new FileStatistics();
		
		fileSta.countFile(folder);
		System.out.println("File Count:"+fileSta.getFileCount());
		System.out.println("Folder Count:"+fileSta.getFolderCount());
		System.out.println("Total Size :"+fileSta.getTotalSize());
	}

	public int getFileCount() {
		return fileCount;
	}

	public int getFolderCount() {
		return folderCount;
	}

	public int getTotalSize() {
		return totalSize;
	}
}
