package View;

import com.sun.javafx.image.BytePixelSetter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NameWindow extends JFrame implements ActionListener {

    private static final String GET_NAME = "get_name";

    private String PlayerName;
    private JTextField tfd1;
    private int win;

    public NameWindow(int width, int height, String title,int won)
    {
        this.win = won;
        initUI(width,height,title);
    }

    private void initUI(int width, int height, String title)
    {
        setTitle(title);
        setPreferredSize(new Dimension(width,height));
        setMaximumSize(new Dimension(width,height));
        setMinimumSize(new Dimension(width,height));

        setResizable(false);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        setLayout(new BorderLayout());
        setLocationRelativeTo(getRootPane());
        JPanel namepanel = new JPanel();
        JLabel winorlose = new JLabel("",JLabel.CENTER);
        winorlose.setFont(new Font("Aharoni", Font.PLAIN, 20));


        if(this.win > 0)
        {
            winorlose.setText("You won! you have Beat Snorlax and his Vicious turtles!");
            winorlose.setForeground(Color.GREEN);

        }
        else
        {
            winorlose.setText("Snorlax and his turtles beat you... better luck next time!");
            winorlose.setForeground(Color.RED);
        }

         tfd1 = new JTextField("",20);
        JButton jbt = new JButton("Enter");
        jbt.setActionCommand(GET_NAME);
        jbt.addActionListener(this);
        namepanel.add(winorlose);
        namepanel.add(tfd1);
        namepanel.add(jbt);
        add(namepanel,BorderLayout.CENTER);
        setVisible(true);



    }

    public String getPlayerName()
    {
        return PlayerName;
    }

    public void actionPerformed(ActionEvent e)
    {

        String action = e.getActionCommand();

        if (action.equals(GET_NAME)) {
            PlayerName = tfd1.getText();

            setVisible(false); //you can't see me!
            dispose();
        }


    }




}
