import java.io.File;

public class FileStatistics {
    public static void main(String[] args) {
        
        String folderPath = "C:/Users/Lenovo/Desktop/java";
                
        File folder = new File(folderPath);
                
        if (folder.exists() && folder.isDirectory()) {
            
            FileStatistics fileStats = new FileStatistics();
            fileStats.countFiles(folder);
            System.out.println("�ļ���Ŀ��" + fileStats.getFileCount());
            System.out.println("�ļ�����Ŀ��" + fileStats.getFolderCount());
            System.out.println("�ļ��ܴ�С��" + fileStats.getTotalSize() + " �ֽ�");
        } else {
            System.out.println("�ļ��в����ڻ���һ����Ч���ļ���·����");
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
