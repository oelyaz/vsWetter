import org.jfree.chart.ChartPanel;
import org.json.simple.parser.ParseException;

import javax.swing.*;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class FRAME0 extends JFrame{

//alle objekte in der UI werden deklariert
    private static FRAME0 frame0;
    private JPanel panel0;
    private JToolBar bar;
    private JButton leaderboardBtn;
    private JButton overviewBtn;
    private JButton weatherBtn;
    private JPanel überblick;
    private JPanel leaderboard;
    private JPanel wetter;
    private JPanel page;
    private JPanel datumJPanel;
    private JTable table;
    private JPanel chartJPanel;
    private JLabel datumLabel;
    private JPanel startJPanel;
    private JTextPane beschreibungStart;
    private static final Color grau = new Color(60, 63, 65);

    private FRAME0() throws IOException, ParseException, org.json.simple.parser.ParseException {
        //setzt anzeigefläche
        setContentPane(panel0);

        Dimension size = new Dimension(600, 500);
        setSize(size);
        setLocation(1000, 500);

        //programm endet wenn man das fenster schließt
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        //Programmicon
        ImageIcon mainIcon = new ImageIcon("src/main/resources/Icon.png");
        setIconImage(mainIcon.getImage());

        //navigationsleiste grauer hintergrund
        bar.setBackground(Color.LIGHT_GRAY);

        int theAnswerToLifeTheUniverseAndEverything = 42;

        //knöpfe auf leiste lesbar
        leaderboardBtn.setForeground(Color.BLACK);
        overviewBtn.setForeground(Color.BLACK);
        weatherBtn.setForeground(Color.BLACK);

        //startseite setzen
        startJPanel.setVisible(true);
        überblick.setVisible(false);
        leaderboard.setVisible(false);
        wetter.setVisible(false);
        overviewBtn.setBackground(Color.GRAY);
        leaderboardBtn.setBackground(Color.LIGHT_GRAY);
        weatherBtn.setBackground(Color.LIGHT_GRAY);



        overviewBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                overviewBtn.setBackground(Color.GRAY);
                leaderboardBtn.setBackground(Color.LIGHT_GRAY);
                weatherBtn.setBackground(Color.LIGHT_GRAY);
                überblick.setVisible(true);
                leaderboard.setVisible(false);
                wetter.setVisible(false);
                startJPanel.setVisible(false);
            }
        });

        chartJPanel.setLayout(new java.awt.BorderLayout());
        PLOTTER plotter = new PLOTTER();
        ChartPanel chartPanel = new ChartPanel(plotter.getChart());
        chartPanel.setMouseWheelEnabled(true);
        chartPanel.setBackground(grau);
        chartPanel.setForeground(grau);
        chartJPanel.add(chartPanel, BorderLayout.CENTER);
        datumLabel.setText("Abweichung am: " + plotter.datum);
        chartJPanel.validate();

        //alles für das leaderboard
        leaderboardBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                overviewBtn.setBackground(Color.LIGHT_GRAY);
                leaderboardBtn.setBackground(Color.GRAY);
                weatherBtn.setBackground(Color.LIGHT_GRAY);
                überblick.setVisible(false);
                leaderboard.setVisible(true);
                wetter.setVisible(false);
                startJPanel.setVisible(false);
            }
        });




        weatherBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                overviewBtn.setBackground(Color.LIGHT_GRAY);
                leaderboardBtn.setBackground(Color.LIGHT_GRAY);
                weatherBtn.setBackground(Color.GRAY);
                überblick.setVisible(false);
                leaderboard.setVisible(false);
                wetter.setVisible(true);
                startJPanel.setVisible(false);
            }
        });

        //frame sichtbar

        setVisible(true);
    }

    //getFrame() für Singleton
    public static FRAME0 getFrame0() throws IOException, org.json.simple.parser.ParseException {
        if(frame0 == null){
            frame0 = new FRAME0();
        }
        return frame0;
    }



//Tabelle wird erstellt und mit Werten belegt
    private void createUIComponents() throws IOException, org.json.simple.parser.ParseException {
        PLOTTER plotter = new PLOTTER();
        String[][] tableInhalt = plotter.getTable();

        String[] kopfzeile = {"Pos", "Anbieter", "Avg Abweichung"};
        table = new JTable(tableInhalt, kopfzeile) {
            @Override
            public boolean isCellEditable(int row, int column){
                return false;
            }
        };
        Font font = new Font("Arial Black", Font.PLAIN, 13);
        JTableHeader tableHeader = table.getTableHeader();
        tableHeader.setFont(font);
        tableHeader.setBackground(grau);
        tableHeader.setForeground(Color.LIGHT_GRAY);
        table.getColumn("Pos").setMaxWidth(50);
        table.setGridColor(Color.DARK_GRAY);
    }



}
