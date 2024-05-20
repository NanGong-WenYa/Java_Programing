package cn.nku.cs;

import javax.swing.JPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ChatPanel extends JPanel {
    private JTextArea chatArea;
    private JTextField inputField;

    public ChatPanel() {
        setLayout(new BorderLayout());

        chatArea = new JTextArea();
        chatArea.setEditable(false);
        Font font = new Font("SimSun", Font.PLAIN, 16); // 定义字体，Arial 是字体名称，Font.PLAIN 是风格，16 是字体大小
        chatArea.setFont(font); // 将定义好的字体应用到 JTextArea
        
        
        JScrollPane scrollPane = new JScrollPane(chatArea);
        add(scrollPane, BorderLayout.CENTER);

        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new BorderLayout());

        inputField = new JTextField();
        inputField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sendMessage();
            }
        });
        inputPanel.add(inputField, BorderLayout.CENTER);

        JButton sendButton = new JButton("Send");
        sendButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sendMessage();
            }
        });
        inputPanel.add(sendButton, BorderLayout.EAST);

        add(inputPanel, BorderLayout.SOUTH);
    }

    private void sendMessage() {
        String message = inputField.getText().trim();
        if (!message.isEmpty()) {
            // 在聊天区域显示发送的消息
            chatArea.append("我： " + message + "\n");

            // 这里你可以将消息发送给对方
            Vars.net.sendChat(message);
            
            // 清空输入框
            inputField.setText("");
        }
    }

    // 用于接收对方发送的消息并显示在聊天区域
    public void receiveMessage(String message) {
        chatArea.append("Opponent: " + message + "\n");
    }

	public JTextArea getChatArea() {
		return chatArea;
	}

	public JTextField getInputField() {
		return inputField;
	}
}

