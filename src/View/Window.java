package View;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import Controller.Game;
import Model.HallOfFame;

public class Window extends JFrame implements ActionListener {
    private static final String ACTION_NEW_GAME = "newGame";
    private static final String ACTION_EXIT_GAME = "exitGame";
    private static final String ACTION_ABOUT = "helpAbout";
    private static final String HALL_OF_FAME = "HallOfFame";

    private Game _game = null;
    private HallOfFame hof = new HallOfFame();


    public Window(int width, int height, String title)
    {
        initUI(width,height,title);
    }

    private void initUI(int width, int height, String title) {

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


        //add(mainPanel,gamepanel);
        // Setup the menu and its action listeners
        initMenu(menuBar);
        //mainPanel.add(gamepanel, BorderLayout.CENTER);
        // Setup keyboard input listener

        //newGame(1);
        hof.createfile();
        setVisible(true);

    }

    private void initMenu(JMenuBar menuBar) {

        JMenu fileMenu = new JMenu("File");
        menuBar.add(fileMenu);

        JMenu helpMenu = new JMenu("Help");
        menuBar.add(helpMenu);

        JMenu HallOfFame = new JMenu("Hall Of Fame");
        menuBar.add(HallOfFame);

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

        about.setToolTipText("High Scores");
        about.setActionCommand(HALL_OF_FAME);


        fileMenu.addActionListener(this);
        helpMenu.addActionListener(this);
        newGame.addActionListener(this);
        exitGame.addActionListener(this);
        about.addActionListener(this);
        HallOfFame.addActionListener(this);
        // Add the sub menu items to the file and help menu

        fileMenu.add(newGame);
        fileMenu.add(exitGame);
        helpMenu.add(about);

    }

    private void newGame() {
        _game = new Game(getWidth(),getHeight());
        add(_game,BorderLayout.CENTER);
    }


    public void actionPerformed(ActionEvent e)
    {
        String action = e.getActionCommand();

        if (action.equals(ACTION_NEW_GAME)) {
            System.out.println("New Game");
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

        }
    }
}


