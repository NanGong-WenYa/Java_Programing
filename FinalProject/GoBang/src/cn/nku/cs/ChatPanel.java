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
        Font font = new Font("SimSun", Font.PLAIN, 16); // �������壬Arial ���������ƣ�Font.PLAIN �Ƿ��16 �������С
        chatArea.setFont(font); // ������õ�����Ӧ�õ� JTextArea
        
        
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
            // ������������ʾ���͵���Ϣ
            chatArea.append("�ң� " + message + "\n");

            // ��������Խ���Ϣ���͸��Է�
            Vars.net.sendChat(message);
            
            // ��������
            inputField.setText("");
        }
    }

    // ���ڽ��նԷ����͵���Ϣ����ʾ����������
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

