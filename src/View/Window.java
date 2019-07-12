package View;


import java.awt.image.*;
import java.io.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import Controller.Game;
import Controller.HOFController;
import Model.BufferImageLoader;
import Model.HallOfFame;
import Model.Record;
import com.sun.istack.internal.localization.NullLocalizable;

public class Window extends JFrame implements ActionListener {
    private static final String ACTION_NEW_GAME = "newGame";
    private static final String ACTION_EXIT_GAME = "exitGame";
    private static final String ACTION_ABOUT = "helpAbout";
    private static final String HALL_OF_FAME = "HallOfFame";

    private int width;
    private int height;
    private JPanel backpic;
    private JPanel HOFPanel;
    private Game _game = null;
    private HOFController hofc = new HOFController();
    private ArrayList<Record> scoreslist;

    public Window(int width, int height, String title)
    {
        this.width = width;
        this.height = height;
        initUI(width,height,title);
    }

    private void initUI(int width, int height, String title) {
        BufferedImage image = null;
        setTitle(title);
        setPreferredSize(new Dimension(width,height));
        setMaximumSize(new Dimension(width,height));
        setMinimumSize(new Dimension(width,height));


        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        setLayout(new BorderLayout());
        setLocationRelativeTo(getRootPane());


        // This will contains the menu bar
        JMenuBar menuBar = new JMenuBar();
        add(menuBar, BorderLayout.NORTH);
        backpic = new JPanel();

            BufferImageLoader loader = new BufferImageLoader();
            //level = loader.loadImage("/wizard_level - Copy.png");
            BufferedImage backpicture = loader.loadImage("/revengeofthepicture.png");

            //image = ImageIO.read(new File("/revengeofthepicture.png"));

        JLabel label = new JLabel(new ImageIcon(backpicture));

        backpic.add(label);
        add(backpic,BorderLayout.CENTER);
        //backpic

        //add()


        //add(mainPanel,gamepanel);
        // Setup the menu and its action listeners
        initMenu(menuBar);
        //mainPanel.add(gamepanel, BorderLayout.CENTER);
        // Setup keyboard input listener

        //newGame(1);
        hofc.CreateHOFfile();
        setVisible(true);

    }

    private void initMenu(JMenuBar menuBar) {

        JMenu fileMenu = new JMenu("File");
        menuBar.add(fileMenu);

        JMenu helpMenu = new JMenu("Help");
        menuBar.add(helpMenu);

        JMenuItem HallOfFame = new JMenuItem("Hall Of Fame");
        HallOfFame.setActionCommand(HALL_OF_FAME);

        // File -> New game menu item
        JMenuItem newGame = new JMenuItem("new game");
        newGame.setToolTipText("Start level 1");
        newGame.setActionCommand(ACTION_NEW_GAME);

        // File -> Exit game menu item
        JMenuItem exitGame = new JMenuItem("Exit (Esc)");
        exitGame.setToolTipText("Exit the game");
        exitGame.setActionCommand(ACTION_EXIT_GAME);

        // Help -> About menu item
        JMenuItem about = new JMenuItem("About");
        about.setToolTipText("About the developers");
        about.setActionCommand(ACTION_ABOUT);


        // Help -> About menu item


        fileMenu.addActionListener(this);
        helpMenu.addActionListener(this);
        newGame.addActionListener(this);
        exitGame.addActionListener(this);
        about.addActionListener(this);
        HallOfFame.addActionListener(this);
        // Add the sub menu items to the file and help menu

        fileMenu.add(newGame);
        fileMenu.add(HallOfFame);
        fileMenu.add(exitGame);
        helpMenu.add(about);


    }

    private void newGame() {
        _game = new Game(getWidth(),getHeight());
        add(_game,BorderLayout.CENTER);
    }

    public void GetPlayerNameDisplay()
    {
        JFrame playernameF = new JFrame();
        playernameF.setVisible(true);


    }

    public void actionPerformed(ActionEvent e)
    {
        int loc = 0;
        String action = e.getActionCommand();

        if (action.equals(ACTION_NEW_GAME)) {
            System.out.println("New Game");

            if(HOFPanel != null)
            {
                remove(HOFPanel);
            }
            if(backpic != null)
            {
                remove(backpic);
            }

            if(_game != null){
                System.out.println("stop old game");

                if(_game.isRunning()){
                    _game.stop();
                }

                System.out.println("remove game component");

                remove(_game);

                System.out.println("starting another game");

            }

            newGame();
            _game.StartGame();


            setVisible(true);
        } else if (action.equals(ACTION_EXIT_GAME)) {
            System.out.println("Exit the game");
            System.exit(0);
        } else if (action.equals(ACTION_ABOUT)) {
            System.out.println("Show about dialog");
        }
        else if (action.equals(HALL_OF_FAME)) {
            System.out.println("Show Hall of Fame");
            if(backpic != null)
            {
                remove(backpic);
            }

            if(_game != null){
                System.out.println("stop old game");

                if(_game.isRunning()){
                    _game.stop();
                }

                System.out.println("remove game component");

                remove(_game);

            }

            if(HOFPanel != null)
            {
                remove(HOFPanel);
            }

            HOFPanel = new JPanel();
            JTextArea scores = new JTextArea();
            scoreslist = hofc.getScores();

            StringBuilder sb = new StringBuilder();
            for (Record i : scoreslist) {
                if(loc==0)
                {
                    sb.append("Rank" +"\t"+ i.getName() + "\t" + i.getScore() + "\t\t" + i.getDate() + "\n\n");

                }
                else
                {
                    sb.append(loc + "\t" + i.getName() + "\t" + i.getScore() + "\t" + i.getDate() + "\n\n");
                }

                if(loc==10)
                {
                    break;
                }
                loc++;

        }
            scores.setText(sb.toString());
            Font font = new Font("Verdana", Font.BOLD, 22);
            scores.setFont(font);
            scores.setForeground(Color.LIGHT_GRAY);
            loc = 0;
            scores.setPreferredSize(new Dimension(width,height));
            scores.setMaximumSize(new Dimension(width,height));
            scores.setMinimumSize(new Dimension(width,height));
            HOFPanel.add(scores);
            add(HOFPanel,BorderLayout.CENTER);

            pack();
            setVisible(true);
            pack();

        }
    }
}


