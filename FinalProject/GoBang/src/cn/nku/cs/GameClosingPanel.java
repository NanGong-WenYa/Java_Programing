package cn.nku.cs;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameClosingPanel extends JPanel {
    private JLabel closingLabel;
    private Timer timer;

    public GameClosingPanel() {
        closingLabel = new JLabel("游戏即将关闭", SwingConstants.CENTER);
        add(closingLabel);

        // 创建一个 Timer，在3秒后执行关闭游戏的操作
        timer = new Timer(3000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                closeGame();
            }
        });
        timer.setRepeats(false); // 只执行一次
        timer.start();
    }

    private void closeGame() {
        // 关闭游戏的逻辑代码
        System.exit(0); // 这里使用退出应用程序的简单方式
    }

   
}
