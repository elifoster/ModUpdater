package modupdater.gui;

import modupdater.gui.event.twitter.TwitterTextActionListener;
import modupdater.gui.util.MaxLengthDocument;

import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

public class ConfigTwitterGui {

    private JFrame frameConfigTwitter;
    public static JTextField loginUser;
    public static JPasswordField loginPass;
    public static JTextArea tweet;

    public ConfigTwitterGui() {
        initialize();
    }

    public void initialize() {
        frameConfigTwitter = new JFrame();
        frameConfigTwitter.setLayout(new GridBagLayout());
        frameConfigTwitter.setTitle("Twitter Configuration");
        frameConfigTwitter.setBounds(100, 100, 450, 285);
        frameConfigTwitter.setVisible(true);
        frameConfigTwitter.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JLabel userLabel = new JLabel("Username");
        GridBagConstraints gbcLabelUser = new GridBagConstraints();
        gbcLabelUser.insets = new Insets(2, 2, 2, 1);
        gbcLabelUser.gridx = 0;
        gbcLabelUser.gridy = 1;

        // Twitter usernames cannot be longer than 15 characters.
        MaxLengthDocument usernameLength = new MaxLengthDocument();
        usernameLength.setMaxChars(15);
        loginUser = new JTextField(15);
        loginUser.setDocument(usernameLength);
        loginUser.addActionListener(new TwitterTextActionListener(loginUser));
        GridBagConstraints gbcLoginUser = new GridBagConstraints();
        gbcLoginUser.insets = new Insets(2, 1, 2, 2);
        gbcLoginUser.gridx = 1;
        gbcLoginUser.gridy = 1;


        JLabel passLabel = new JLabel("Password");
        GridBagConstraints gbcLabelPass = new GridBagConstraints();
        gbcLabelPass.insets = new Insets(2, 2, 2, 1);
        gbcLabelPass.gridx = 0;
        gbcLabelPass.gridy = 2;

        loginPass = new JPasswordField(15);
        loginPass.setEchoChar('$');
        loginPass.addActionListener(new TwitterTextActionListener(loginPass));
        GridBagConstraints gbcLoginPass = new GridBagConstraints();
        gbcLoginPass.insets = new Insets(2, 1, 2, 2);
        gbcLoginPass.gridx = 1;
        gbcLoginPass.gridy = 2;

        JLabel tweetLabel = new JLabel("Tweet");
        GridBagConstraints gbcLabelTweet = new GridBagConstraints();
        gbcLabelTweet.insets = new Insets(2, 2, 2, 1);
        gbcLabelTweet.gridx = 0;
        gbcLabelTweet.gridy = 3;

        // Tweets cannot be longer than 140
        MaxLengthDocument tweetLength = new MaxLengthDocument();
        tweetLength.setMaxChars(140);
        tweet = new JTextArea();
        tweet.setDocument(tweetLength);
        tweet.setEditable(true);
        tweet.setLineWrap(true);
        tweet.setColumns(20);
        tweet.setBorder(loginPass.getBorder());
        tweet.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent event) {}

            @Override
            public void focusLost(FocusEvent event) {
                ModUpdaterGui.tweet = tweet.getText();
            }
        });
        GridBagConstraints gbcTweet = new GridBagConstraints();
        gbcTweet.insets = new Insets(2, 1, 2, 2);
        gbcTweet.gridx = 1;
        gbcTweet.gridy = 3;

        frameConfigTwitter.getContentPane().add(userLabel, gbcLabelUser);
        frameConfigTwitter.getContentPane().add(loginUser, gbcLoginUser);
        frameConfigTwitter.getContentPane().add(passLabel, gbcLabelPass);
        frameConfigTwitter.getContentPane().add(loginPass, gbcLoginPass);
        frameConfigTwitter.getContentPane().add(tweetLabel, gbcLabelTweet);
        frameConfigTwitter.getContentPane().add(tweet, gbcTweet);
    }
}
