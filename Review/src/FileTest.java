import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Scanner;

public class FileTest {
    public static void main(String args[]) throws IOException {
        File file = new File("fileTest.txt");

        
            if (file.exists()) {
                file.delete();
            }
            file.createNewFile();
            System.out.println(file.getAbsolutePath());

            FileInputStream fis = new FileInputStream(file);
            Scanner scanner = new Scanner(System.in);
            FileOutputStream fos = new FileOutputStream(file);

            while (true) {
                String input = scanner.nextLine();

                try {
                    fos.write((input + "\n").getBytes());
                } catch (IOException e) {
                    System.out.println("д���ļ�ʱ��������: " + e.getMessage());
                    break; // ���ִ�������ѭ��
                }
            }

            // �ر���Դ
            fis.close();
            fos.close();
            scanner.close();
        
    }
}
