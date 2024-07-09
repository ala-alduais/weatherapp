package main.java.com.mycompany.hazardapp;

import javax.swing.JFrame;
import java.awt.Image;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class Map {

    private int tMapOn = 0;
    private int eMapOn = 0;
    private int fMapOn = 0;

    private JLayeredPane layeredPane;
    private JLabel statusLabel;
    private JTextPane infoPanel1;
    private JTextPane infoPanel2;

    public void openMap() {

        // Create a new JFrame
        JFrame frame = new JFrame("Display Image");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000, 700); // Set the size of the frame
        frame.setLayout(null); // Disable layout manager for absolute positioning

        // Load the image
        String imagePath = "images/The_World_map.png";
        ImageIcon imageIcon = new ImageIcon(imagePath);

        // Scale the image to fit the frame
        Image image = imageIcon.getImage(); // Transform it 
        Image scaledImage = image.getScaledInstance(frame.getWidth(), frame.getHeight(), Image.SCALE_SMOOTH); // Scale it the smooth way
        imageIcon = new ImageIcon(scaledImage);  // Transform it back

        // Create a JLabel to hold the image
        JLabel label = new JLabel(imageIcon);
        label.setBounds(0, 0, frame.getWidth(), frame.getHeight()); // Set the position and size of the label
        
        layeredPane = new JLayeredPane();
        layeredPane.setBounds(0, 0, frame.getWidth(), frame.getHeight());
        frame.add(layeredPane);

        // Add map image label to default layer (0)
        layeredPane.add(label, Integer.valueOf(0));
        
        statusLabel = new JLabel();
        statusLabel.setBounds(10, frame.getHeight() - 50, frame.getWidth() - 20, 40);
        statusLabel.setHorizontalAlignment((SwingConstants.CENTER));
        statusLabel.setOpaque(true);
        statusLabel.setBackground(Color.LIGHT_GRAY);
        frame.add(statusLabel);
       
        infoPanel1 = new JTextPane();
        infoPanel1.setBounds(1050, 300, 200, 100);
        infoPanel1.setContentType("text/html");
        infoPanel1.setEditable(false);
        infoPanel1.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        infoPanel1.setVisible(false);
        frame.add(infoPanel1);
       
        infoPanel2 = new JTextPane();
        infoPanel2.setBounds(1050, 410, 200, 100);
        infoPanel2.setContentType("text/html");
        infoPanel2.setEditable(false);
        infoPanel2.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        infoPanel2.setVisible(false);
        frame.add(infoPanel2);
        
        
        label.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int x = e.getX();
                int y = e.getY();

                System.out.println("Clicked at: (" + x + ", " + y + ")");
                JTextArea textArea = new JTextArea(5, 20); // 5 rows, 20 columns
                textArea.setBorder(BorderFactory.createLineBorder(Color.BLACK)); // Add a border

                // Load the new image
                String newImagePath = "images/668-6687315_location-pointer-png.png";
                ImageIcon newImageIcon = new ImageIcon(newImagePath);
                Image iconImage = newImageIcon.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);
                newImageIcon = new ImageIcon(iconImage);

                // Create a JLabel to hold the new image
                JLabel newLabel = new JLabel(newImageIcon);
                int xsize = 100;
                int ysize = 100;
                newLabel.setBounds(x - xsize / 2, y - ysize / 2 - 10, xsize, ysize); // Set the position and size of the new label

                // Questions to ask
                JTextField question1 = new JTextField();
                JTextField question2 = new JTextField();
                JTextField question3 = new JTextField();

                Object[] message = {
                    "Place", question1,
                    "Hazard Type", question2,
                    "Dangerosity", question3,};

                int option = JOptionPane.showConfirmDialog(frame, message, "Enter your answers", JOptionPane.OK_CANCEL_OPTION);
                if (option == JOptionPane.OK_OPTION) {

                    String answer1 = question1.getText();
                    String answer2 = question2.getText();
                    String answer3 = question3.getText();

                    // Display the answers at the click location
                    JLabel answerLabel = new JLabel("<html>Place : " + answer1 + "<br>Hazard Type : " + answer2 + "<br>Dangerosity : " + answer3 + "</html>");
                    answerLabel.setBounds(x - newImageIcon.getIconWidth(), y, 200, 50); // Adjust the position next to the icon
                    layeredPane.add(answerLabel, Integer.valueOf(2));
                }
                layeredPane.add(newLabel, Integer.valueOf(2));
                layeredPane.revalidate();
                layeredPane.repaint();

            }
        }
        );
        JButton tornadoButton = new JButton("Tornado risks");
        tornadoButton.setBounds(1050, 50, 200, 20);
        String TImagePath = "images/MapTornado.png";
        ImageIcon TMap = new ImageIcon(TImagePath);
        Image TiconImage = TMap.getImage().getScaledInstance(frame.getWidth(), frame.getHeight(), Image.SCALE_SMOOTH);
        TMap = new ImageIcon(TiconImage);
        JLabel Tlabel = new JLabel(TMap);
        Tlabel.setBounds(0, 0, layeredPane.getWidth(), layeredPane.getHeight()); // Set the position and size of the label
        JTextArea TtextArea = new JTextArea(10, 30); // Adjust rows and columns as needed
        TtextArea.setEditable(false); // Make it non-editable
        TtextArea.setText("In case of tornado :\n"
                + "If you are in a building, move to a basement or an interior room on the lowest floor, away from windows.\n"
                + "If you have no shelter, lie flat in a ditch or low-lying area and cover your head with your arms.\n"
                + "Monitor weather reports and listen for sirens or emergency alerts.");
        JScrollPane TscrollPane = new JScrollPane(TtextArea);
        TscrollPane.setBounds(1050, 300, 200, 300);
        tornadoButton.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                // Code for the task you want to perform when the button is clicked
                if (tMapOn == 0) {
                    layeredPane.add(Tlabel, Integer.valueOf(1));
                    frame.add(TscrollPane);
                    frame.repaint();
                    layeredPane.revalidate();
                    layeredPane.repaint();
                    tMapOn = 1;
                    
                    statusLabel.setText("Areas that are highlighted on the map is at higher risk of tornado."
                            + "It would be best to stay indoor until the risk has passed.");
                    infoPanel1.setText("<html><b>Safety Tips:</b><br>1.Find Shelter</b><br>2.Avoid areas with trees.</b><br>3.Find a low-lying area and lay flat.");
                    infoPanel1.setVisible(true);
                    infoPanel2.setText("<html><b>Tornado Facts</b><br>It is a violently rotating air where wind variations with height support rotation in the updraft ");
                    infoPanel2.setVisible(true);
                } else {
                    layeredPane.remove(Tlabel);
                    frame.remove(TscrollPane);
                    frame.repaint();
                    layeredPane.revalidate();
                    layeredPane.repaint();
                    tMapOn = 0;
                 
                }

            }
        });

        JButton eButton = new JButton("Earthquake risks");
        eButton.setBounds(1050, 150, 200, 20);
        String EImagePath = "images/EMap.png";
        ImageIcon EMap = new ImageIcon(EImagePath);
        Image EiconImage = EMap.getImage().getScaledInstance(frame.getWidth(), frame.getHeight(), Image.SCALE_SMOOTH);
        EMap = new ImageIcon(EiconImage);
        JLabel Elabel = new JLabel(EMap);
        Elabel.setBounds(0, 0, layeredPane.getWidth(), layeredPane.getHeight()); // Set the position and size of the label

        JTextArea EtextArea = new JTextArea(10, 30); // Adjust rows and columns as needed
        EtextArea.setEditable(false); // Make it non-editable
        EtextArea.setText("In case of earthquake :\n"
                + "If you are indoors, drop to the ground, take cover under a sturdy table or desk, and hold on until the shaking stops.\n"
                + "Avoid windows, exterior walls, and anything that could fall on you.\n"
                + "If you are outside, move to an open area away from buildings, power lines, and trees.");
        JScrollPane EscrollPane = new JScrollPane(EtextArea);
        EscrollPane.setBounds(1050, 300, 200, 300);

        eButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                // Code for the task you want to perform when the button is clicked

                if (eMapOn == 0) {
                    layeredPane.add(Elabel, Integer.valueOf(2));
                    frame.add(EscrollPane);
                    frame.repaint();
                    layeredPane.revalidate();
                    layeredPane.repaint();

                    eMapOn = 1;
                    
                    statusLabel.setText("Areas that are highlighted on the map is at higher risk of earthquakes."
                            + "It would be best to stay indoor or get to a safe location until the risk has passed");
                    infoPanel1.setText("<html><b>Safety Tips:</b><br>1.Drop, Cover and Hold On</b><br>2.Stay inside and avoid doors and windows.</b><br>3.Keep head and neck covered");
                    infoPanel2.setText("<html><b>Earthquake Facts</b><br>It is a result of the movement along the faults within the Earth. It can occur near the surface or deep below the surface");
                    infoPanel2.setVisible(true);
                } else {
                    layeredPane.remove(Elabel);
                    frame.remove(EscrollPane);
                    frame.repaint();
                    layeredPane.revalidate();
                    layeredPane.repaint();
                    eMapOn = 0;
                }

            }
        });

        JButton fButton = new JButton("Floods risks");
        fButton.setBounds(1050, 250, 200, 20);
        String FImagePath = "images/fMap.png";
        ImageIcon FMap = new ImageIcon(FImagePath);
        Image FiconImage = FMap.getImage().getScaledInstance(frame.getWidth(), frame.getHeight(), Image.SCALE_SMOOTH);
        FMap = new ImageIcon(FiconImage);
        JLabel Flabel = new JLabel(FMap);
        Flabel.setBounds(0, 0, layeredPane.getWidth(), layeredPane.getHeight()); // Set the position and size of the label

        JTextArea FtextArea = new JTextArea(10, 30); // Adjust rows and columns as needed
        FtextArea.setEditable(false); // Make it non-editable
        FtextArea.setText("In case of floods :\n"
                + "Evacuate to a safe location if authorities advise or you feel threatened. Avoid walking or driving through floodwaters.\n"
                + "If instructed, turn off electricity and gas at the main switches or valves before leaving your home.\n"
                + "Monitor emergency broadcasts and follow instructions from authorities. Do not return home until it is safe to do so.");
        JScrollPane FscrollPane = new JScrollPane(FtextArea);
        FscrollPane.setBounds(1050, 300, 200, 300);
        fButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                // Code for the task you want to perform when the button is clicked

                if (fMapOn == 0) {
                    layeredPane.add(Flabel, Integer.valueOf(2));
                    frame.add(FscrollPane);
                    frame.repaint();
                    layeredPane.revalidate();
                    layeredPane.repaint();
                    fMapOn = 1;
                    
                    statusLabel.setText("Areas that are highlighted on the map are at higher risk of floods due to heavy rain."
                            + "It would be best to move to a safe area until the risk has passed.");
                    infoPanel1.setText("<html><b>Safety Tips:</b><br>1.Get to higher gronds.</b><br>2.Follow evacuation orders and heed warning signs. with trees.</b><br>3.Avoid floodwaters.");
                    infoPanel1.setVisible(true);
                    infoPanel2.setText("<html><b>Floods Facts</b><br>Flooding usually are a result from the failure of water control structure. It is also the cause of prolonged rain falls.");
                    infoPanel2.setVisible(true);
                } else {
                    layeredPane.remove(Flabel);
                    frame.remove(FscrollPane);
                    frame.repaint();
                    layeredPane.revalidate();
                    layeredPane.repaint();
                    fMapOn = 0;
                }

            }
        });

        frame.add(tornadoButton);
        frame.add(eButton);
        frame.add(fButton);
        // Set the frame to be visible
        frame.setVisible(true);
    }

    public void openMapAdmin() {

        // Create a new JFrame
        JFrame frame = new JFrame("Display Image");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000, 700); // Set the size of the frame
        frame.setLayout(null); // Disable layout manager for absolute positioning

        // Load the image
        String imagePath = "images/The_World_map.png";
        ImageIcon imageIcon = new ImageIcon(imagePath);

        // Scale the image to fit the frame
        Image image = imageIcon.getImage(); // Transform it 
        Image scaledImage = image.getScaledInstance(frame.getWidth(), frame.getHeight(), Image.SCALE_SMOOTH); // Scale it the smooth way
        imageIcon = new ImageIcon(scaledImage);  // Transform it back

        // Create a JLabel to hold the image
        JLabel label = new JLabel(imageIcon);
        label.setBounds(0, 0, frame.getWidth(), frame.getHeight()); // Set the position and size of the label

        layeredPane = new JLayeredPane();
        layeredPane.setBounds(0, 0, frame.getWidth(), frame.getHeight());
        frame.add(layeredPane);

        // Add map image label to default layer (0)
        layeredPane.add(label, Integer.valueOf(0));

        label.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int x = e.getX();
                int y = e.getY();

                System.out.println("Clicked at: (" + x + ", " + y + ")");
                JTextArea textArea = new JTextArea(5, 20); // 5 rows, 20 columns
                textArea.setBorder(BorderFactory.createLineBorder(Color.BLACK)); // Add a border

                // Load the new image
                String newImagePath = "images/668-6687315_location-pointer-png.png";
                ImageIcon newImageIcon = new ImageIcon(newImagePath);
                Image iconImage = newImageIcon.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);
                newImageIcon = new ImageIcon(iconImage);

                // Create a JLabel to hold the new image
                JLabel newLabel = new JLabel(newImageIcon);
                int xsize = 100;
                int ysize = 100;
                newLabel.setBounds(x - xsize / 2, y - ysize / 2 - 10, xsize, ysize); // Set the position and size of the new label

                // Questions to ask
                JTextField question1 = new JTextField();
                JTextField question2 = new JTextField();
                JTextField question3 = new JTextField();

                Object[] message = {
                    "Place", question1,
                    "Hazard Type", question2,
                    "Dangerosity", question3,};

                int option = JOptionPane.showConfirmDialog(frame, message, "Enter your answers", JOptionPane.OK_CANCEL_OPTION);
                if (option == JOptionPane.OK_OPTION) {

                    String answer1 = question1.getText();
                    String answer2 = question2.getText();
                    String answer3 = question3.getText();

                    // Display the answers at the click location
                    JLabel answerLabel = new JLabel("<html>Place : " + answer1 + "<br>Hazard Type : " + answer2 + "<br>Dangerosity : " + answer3 + "</html>");
                    answerLabel.setBounds(x - newImageIcon.getIconWidth(), y, 200, 50); // Adjust the position next to the icon
                    layeredPane.add(answerLabel, Integer.valueOf(2));
                }
                layeredPane.add(newLabel, Integer.valueOf(2));
                layeredPane.revalidate();
                layeredPane.repaint();

            }
        }
        );
        JButton tornadoButton = new JButton("Tornado risks");
        tornadoButton.setBounds(1050, 50, 200, 20);
        String TImagePath = "images/MapTornado.png";
        ImageIcon TMap = new ImageIcon(TImagePath);
        Image TiconImage = TMap.getImage().getScaledInstance(frame.getWidth(), frame.getHeight(), Image.SCALE_SMOOTH);
        TMap = new ImageIcon(TiconImage);
        JLabel Tlabel = new JLabel(TMap);
        Tlabel.setBounds(0, 0, layeredPane.getWidth(), layeredPane.getHeight()); // Set the position and size of the label
        JTextArea TtextArea = new JTextArea(10, 30); // Adjust rows and columns as needed
        TtextArea.setEditable(true); // Make it non-editable
        TtextArea.setText("In case of tornado :\n"
                + "If you are in a building, move to a basement or an interior room on the lowest floor, away from windows.\n"
                + "If you have no shelter, lie flat in a ditch or low-lying area and cover your head with your arms.\n"
                + "Monitor weather reports and listen for sirens or emergency alerts.");
        JScrollPane TscrollPane = new JScrollPane(TtextArea);
        TscrollPane.setBounds(1050, 300, 200, 300);
        tornadoButton.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                // Code for the task you want to perform when the button is clicked
                if (tMapOn == 0) {
                    layeredPane.add(Tlabel, Integer.valueOf(1));
                    frame.add(TscrollPane);
                    frame.repaint();
                    layeredPane.revalidate();
                    layeredPane.repaint();
                    tMapOn = 1;
                } else {
                    layeredPane.remove(Tlabel);
                    frame.remove(TscrollPane);
                    frame.repaint();
                    layeredPane.revalidate();
                    layeredPane.repaint();
                    tMapOn = 0;
                }

            }
        });

        JButton eButton = new JButton("Earthquake risks");
        eButton.setBounds(1050, 150, 200, 20);
        String EImagePath = "images/EMap.png";
        ImageIcon EMap = new ImageIcon(EImagePath);
        Image EiconImage = EMap.getImage().getScaledInstance(frame.getWidth(), frame.getHeight(), Image.SCALE_SMOOTH);
        EMap = new ImageIcon(EiconImage);
        JLabel Elabel = new JLabel(EMap);
        Elabel.setBounds(0, 0, layeredPane.getWidth(), layeredPane.getHeight()); // Set the position and size of the label

        JTextArea EtextArea = new JTextArea(10, 30); // Adjust rows and columns as needed
        EtextArea.setEditable(true); // Make it non-editable
        EtextArea.setText("In case of earthquake :\n"
                + "If you are indoors, drop to the ground, take cover under a sturdy table or desk, and hold on until the shaking stops.\n"
                + "Avoid windows, exterior walls, and anything that could fall on you.\n"
                + "If you are outside, move to an open area away from buildings, power lines, and trees.");
        JScrollPane EscrollPane = new JScrollPane(EtextArea);
        EscrollPane.setBounds(1050, 300, 200, 300);

        eButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                // Code for the task you want to perform when the button is clicked

                if (eMapOn == 0) {
                    layeredPane.add(Elabel, Integer.valueOf(2));
                    frame.add(EscrollPane);
                    frame.repaint();
                    layeredPane.revalidate();
                    layeredPane.repaint();

                    eMapOn = 1;
                } else {
                    layeredPane.remove(Elabel);
                    frame.remove(EscrollPane);
                    frame.repaint();
                    layeredPane.revalidate();
                    layeredPane.repaint();
                    eMapOn = 0;
                }

            }
        });

        JButton fButton = new JButton("Floods risks");
        fButton.setBounds(1050, 250, 200, 20);
        String FImagePath = "images/fMap.png";
        ImageIcon FMap = new ImageIcon(FImagePath);
        Image FiconImage = FMap.getImage().getScaledInstance(frame.getWidth(), frame.getHeight(), Image.SCALE_SMOOTH);
        FMap = new ImageIcon(FiconImage);
        JLabel Flabel = new JLabel(FMap);
        Flabel.setBounds(0, 0, layeredPane.getWidth(), layeredPane.getHeight()); // Set the position and size of the label

        JTextArea FtextArea = new JTextArea(10, 30); // Adjust rows and columns as needed
        FtextArea.setEditable(true); // Make it non-editable
        FtextArea.setText("In case of floods :\n"
                + "Evacuate to a safe location if authorities advise or you feel threatened. Avoid walking or driving through floodwaters.\n"
                + "If instructed, turn off electricity and gas at the main switches or valves before leaving your home.\n"
                + "Monitor emergency broadcasts and follow instructions from authorities. Do not return home until it is safe to do so.");
        JScrollPane FscrollPane = new JScrollPane(EtextArea);
        FscrollPane.setBounds(1050, 300, 200, 300);
        fButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                // Code for the task you want to perform when the button is clicked

                if (fMapOn == 0) {
                    layeredPane.add(Flabel, Integer.valueOf(2));
                    frame.add(FscrollPane);
                    frame.repaint();
                    layeredPane.revalidate();
                    layeredPane.repaint();
                    fMapOn = 1;
                } else {
                    layeredPane.remove(Flabel);
                    frame.remove(FscrollPane);
                    frame.repaint();
                    layeredPane.revalidate();
                    layeredPane.repaint();
                    fMapOn = 0;
                }

            }
        });

        frame.add(tornadoButton);
        frame.add(eButton);
        frame.add(fButton);
        // Set the frame to be visible
        frame.setVisible(true);
    }
}
