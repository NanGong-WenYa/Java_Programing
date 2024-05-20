import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Calculator extends JFrame implements ActionListener {
    private JTextField displayField;
    private double num1, num2, result;
    private String operator;

    public Calculator() {
        setTitle("¼òµ¥¼ÆËãÆ÷");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 400);
        setLayout(new BorderLayout());

        displayField = new JTextField();
        displayField.setEditable(false);
        add(displayField, BorderLayout.NORTH);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(5, 4));

        String[] buttonLabels = {
                "7", "8", "9", "/",
                "4", "5", "6", "*",
                "1", "2", "3", "-",
                "0",  "=", "+",
                "C","^2" ,"1/x"// Clear button
        };

        for (String label : buttonLabels) {
            JButton button = new JButton(label);
            button.addActionListener(this);
            buttonPanel.add(button);
        }

        add(buttonPanel, BorderLayout.CENTER);

        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        String action = e.getActionCommand();
        
        if (action.equals("0")||action.equals("1")||action.equals("2")||
        		action.equals("3")||action.equals("4")||action.equals("5")||
        		action.equals("6")||action.equals("7")||action.equals("8")
        		||action.equals("9")||action.equals("^2")||action.equals("1/x")) {
        	
        	  if(action.equals("^2")){
        		  num1=Double.parseDouble(displayField.getText());
              	result=num1*num1;
              	displayField.setText(num1+"^2"+"="+String.valueOf(result));
              	
              }
              else if(action.equals("1/x")){
            	num1=Double.parseDouble(displayField.getText());
              	result=1/num1;
              	displayField.setText("1/"+num1+"="+String.valueOf(result));
              }
              
              else if(operator==null){
            	  displayField.setText(displayField.getText()+action);
              }
              else{
            	  displayField.setText(action);
              }
          
            
        } else if (action.matches("[\\+\\-\\*/]")) {
            operator = action;
            num1 = Double.parseDouble(displayField.getText());
            displayField.setText(displayField.getText()+action);
        } else if (action.equals("=")) {
            num2 = Double.parseDouble(displayField.getText());
            switch (operator) {
                case "+":
                    result = num1 + num2;
                    break;
                case "-":
                    result = num1 - num2;
                    break;
                case "*":
                    result = num1 * num2;
                    break;
                case "/":
                    if (num2 != 0) {
                        result = num1 / num2;
                    } else {
                        displayField.setText("Error");
                        return;
                    }
                    break;
            }
            displayField.setText(num1+operator+num2+"="+String.valueOf(result));
        } else if (action.equals("C")) {
            displayField.setText(""); // Clear the display
        }
    }

    public static void main(String[] args) {

    	Calculator c=new Calculator();

    }
  
}


  

