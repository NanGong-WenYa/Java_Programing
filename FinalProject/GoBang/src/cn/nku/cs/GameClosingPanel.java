package cn.nku.cs;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameClosingPanel extends JPanel {
    private JLabel closingLabel;
    private Timer timer;

    public GameClosingPanel() {
        closingLabel = new JLabel("��Ϸ�����ر�", SwingConstants.CENTER);
        add(closingLabel);

        // ����һ�� Timer����3���ִ�йر���Ϸ�Ĳ���
        timer = new Timer(3000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                closeGame();
            }
        });
        timer.setRepeats(false); // ִֻ��һ��
        timer.start();
    }

    private void closeGame() {
        // �ر���Ϸ���߼�����
        System.exit(0); // ����ʹ���˳�Ӧ�ó���ļ򵥷�ʽ
    }

   
}
