import java.io.File;

public class FileStatistics {
    public static void main(String[] args) {
        
        String folderPath = "C:/Users/Lenovo/Desktop/java";
                
        File folder = new File(folderPath);
                
        if (folder.exists() && folder.isDirectory()) {
            
            FileStatistics fileStats = new FileStatistics();
            fileStats.countFiles(folder);
            System.out.println("文件数目：" + fileStats.getFileCount());
            System.out.println("文件夹数目：" + fileStats.getFolderCount());
            System.out.println("文件总大小：" + fileStats.getTotalSize() + " 字节");
        } else {
            System.out.println("文件夹不存在或不是一个有效的文件夹路径。");
        }
    }

    private int fileCount = 0;
     
    private int folderCount = 0;
    
    private long totalSize = 0;

    public void countFiles(File folder) {
        
        File[] files = folder.listFiles();
        
        if (files != null) {
            
            for (File file : files) {
                if (file.isFile()) {
                   
                    fileCount++;
                    totalSize += file.length();
                } else if (file.isDirectory()) {
                    
                    folderCount++;
                    countFiles(file);
                }
            }
        }
    }

    public int getFileCount() {
        return fileCount;
    }
  
    public int getFolderCount() {
        return folderCount;
    }

    public long getTotalSize() {
        return totalSize;
    }
}
