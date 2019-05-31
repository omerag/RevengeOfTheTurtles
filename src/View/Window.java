package View;


import javax.swing.*;
import java.awt.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.net.URL;
import Model.Game;

public class Window extends JFrame implements ActionListener,KeyListener {
    public static final String ACTION_NEW_GAME = "newGame";
    public static final String ACTION_EXIT_GAME = "exitGame";
    public static final String ACTION_ABOUT = "helpAbout";

    Game _game = null;

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
        setVisible(true);

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
        addKeyListener(this);

        //newGame(1);
    }

    private void initMenu(JMenuBar menuBar) {

        JMenu fileMenu = new JMenu("File");
        menuBar.add(fileMenu);

        JMenu helpMenu = new JMenu("Help");
        menuBar.add(helpMenu);

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

        fileMenu.addActionListener(this);
        helpMenu.addActionListener(this);
        newGame.addActionListener(this);
        exitGame.addActionListener(this);
        about.addActionListener(this);

        // Add the sub menu items to the file and help menu
        fileMenu.add(newGame);
        fileMenu.add(exitGame);
        helpMenu.add(about);

    }




    private void newGame() {
        _game = new Game();
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
    }



    /*
    public Window(int width, int height, String title, Game game){

        JFrame frame = new JFrame(title);

        frame.setPreferredSize(new Dimension(width,height));
        frame.setMaximumSize(new Dimension(width,height));
        frame.setMinimumSize(new Dimension(width,height));

        frame.add(game);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

    }
*/

    /*
     * Used for responding to keyboard inputs
     */
    @Override
    public void keyPressed(KeyEvent e) {
        /*
        int keyCode = e.getKeyCode();

        switch (keyCode) {
            case KeyEvent.VK_UP:
            case KeyEvent.VK_KP_UP:
                mouse.move(DIRECTION.NORTH, null);
                break;

            case KeyEvent.VK_DOWN:
            case KeyEvent.VK_KP_DOWN:
                mouse.move(DIRECTION.SOUTH, null);
                break;

            case KeyEvent.VK_LEFT:
            case KeyEvent.VK_KP_LEFT:
                mouse.move(DIRECTION.WEST, null);
                break;

            case KeyEvent.VK_RIGHT:
            case KeyEvent.VK_KP_RIGHT:
                mouse.move(DIRECTION.EAST, null);
                break;

            case KeyEvent.VK_ESCAPE:
                System.exit(0);
                break;
        }

        boardView.notifyDataChanged();
        */
    }

    @Override
    public void keyTyped(KeyEvent e) {
        // Not used
    }

    @Override
    public void keyReleased(KeyEvent e) {
        // Not used
    }
}


