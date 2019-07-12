package View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NameWindow extends JFrame implements ActionListener {

    private static final String GET_NAME = "get_name";

    private String PlayerName;
    private JTextField tfd1;

    public NameWindow(int width, int height, String title)
    {
        initUI(width,height,title);
    }

    private void initUI(int width, int height, String title)
    {
        setTitle(title);
        setPreferredSize(new Dimension(width,height));
        setMaximumSize(new Dimension(width,height));
        setMinimumSize(new Dimension(width,height));

        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        setLayout(new BorderLayout());
        setLocationRelativeTo(getRootPane());
        JPanel namepanel = new JPanel();
         tfd1 = new JTextField("Enter Name",20);
        JButton jbt = new JButton("Enter");
        jbt.setActionCommand(GET_NAME);
        jbt.addActionListener(this);

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
