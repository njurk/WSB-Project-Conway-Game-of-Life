import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Lexicon extends JFrame {
    private final String[] imagePaths =
            {"/pictures/glider.png", "/pictures/LWSS.png", "/pictures/MWSS.png", "/pictures/1-2-3.png", "/pictures/1-2-3-4.png",
                    "/pictures/135-degree-converter.png", "/pictures/pulsar.png", "/pictures/bracket-pulsar.png", "/pictures/cross.png",
                    "/pictures/queen-bee-shuttle.png", "/pictures/gosper-glider-gun.png", "/pictures/25P3H1V0-1.png", "/pictures/25P3H1V0-2.png",
                    "/pictures/4-8-12-diamond.png", "/pictures/44P5H2V0.png"};

    private final String[] imageDescriptions =
            {"<div>Glider</div> The glider was found by Richard K. Guy in 1969. It is the smallest, " +
                    "most common, and first-discovered spaceship in Game of Life. It travels diagonally across the grid. " +
                    "Gliders are important because they are easily produced by glider guns and rakes, can be collided " +
                    "with each other to form more complicated patterns, and can be used to transmit information over long distances.",

                    "<div>LWSS</div> The lightweight spaceship found by John Conway in 1970 is the smallest orthogonal spaceship, " +
                            "and the second most common spaceship after the glider. It has a period of 4.",

                    "<div>MWSS</div> The middleweight spaceship is the third most common spaceship after the glider and lightweight " +
                            "spaceship. It was found by John Conway in 1970 and travels orthogonally.",

                    "<div>1-2-3</div> A period-3 oscillator found by David Buckingham in August 1972. An oscillator is a pattern that is a " +
                            "predecessor of itself. That is, it is a pattern that repeats itself after a fixed number of generations " +
                            "(known as its period).",

                    "<div>1-2-3-4</div> A period-4 oscillator found by Dean Hickerson in August 1989.",

                    "<div>135-degree MWSS-to-G</div> A converter discovered by Matthias Merzenich in July 2013. It accepts an MWSS as input, " +
                            "and produces an output glider traveling at a 135-degree angle relative to the input direction.",

                    "<div>Pulsar</div> A pulsar is a large but surprisingly common period-3 oscillator. It was found by John Conway in March 1970.",

                    "<div>Bracket pulsar</div> A period-3 oscillator discovered by Hartmut Holzwart on the 5th of March, 1993. Despite being " +
                            "identical in population and bounding box to the pulsar, it is much rarer, having never appeared naturally so far.",

                    "<div>Cross</div> Cross is a period-3 oscillator that was found in October 1989 by Robert Wainwright. It is the smallest in " +
                            "an infinite family of period 3 oscillators.",

                    "<div>Queen bee shuttle</div> The queen bee shuttle (or basic shuttle) is a period-30 shuttle oscillators in which a queen " +
                            "bee travels back and forth between two blocks (Isomers). It was found by Bill Gosper in 1970 and was the first " +
                            "period-30 oscillator to be found. In terms of its minimum population of 20 cells, it is the smallest known oscillator " +
                            "with period greater than 15.",

                    "<div>Gosper glider gun</div> The first known gun, and indeed the first known finite pattern with unbounded growth, found by " +
                            "Bill Gosper in November 1970. It consists of two queen bee shuttles stabilized by two blocks. It produces gliders " +
                            "that travel diagonally.",

                    "<div>25P3H1V0.1</div> A spaceship discovered by Dean Hickerson in August 1989. It was the first c/3 spaceship to be discovered. " +
                            "In terms of its 25 cells, it is tied with 25P3H1V0.2. Unlike 25P3H1V0.2, it has a population of 25 in all of its phases, " +
                            "as well as a smaller bounding box.",

                    "<div>25P3H1V0.2</div> A spaceship discovered by David Bell in early 1992, with a minimum of 25 cells. A note in Spaceships in Conway's Life " +
                            "indicates that it was found with a search that limited the number of live cells in each column, and possibly also the " +
                            "maximum cross-section (4 cells in this case).",

                    "<div>4-8-12 diamond</div> One of many pure glider generators - patterns that evolve into one or more gliders and nothing else.",

                    "<div>44P5H2V0</div> An unnamed orthogonal spaceship discovered by Dean Hickerson on July 23, 1991."
            };

    public Lexicon() {
        setTitle("Lexicon");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);

        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
        contentPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JLabel gameDescription = new JLabel("<html><div style='font-size:24px; font-weight:700;'>The Game of Life</div><br></br>" +
                "<div style='text-align:justify; font-size:10px; padding-bottom:30px;'>The Game of Life is not your " +
                "typical computer game. It is a cellular automaton, and was invented by Cambridge mathematician John Conway.<br></br> " +
                "This game became widely known when it was mentioned in an article published by Scientific American in 1970. It consists " +
                "of a grid of cells which, based on a few mathematical rules, can live, die or multiply. Depending on the initial " +
                "conditions, the cells form various patterns throughout the course of the game.<br></br><br></br>" + "Rules:<br></br>" +
                "Each cell with one or no neighbors dies, as if by solitude.<br></br>" +
                "Each cell with four or more neighbors dies, as if by overpopulation.<br></br>" +
                "Each cell with two or three neighbors survives.<br></br>" +
                "Each cell with three neighbors becomes populated.<br></br><br></br>" +
                "The Game of Life is undecidable, which means that given an initial pattern and a later pattern, no algorithm " +
                "exists that can tell whether the later pattern is ever going to appear. Given that the game is also " +
                "Turing-complete, this is a corollary of the halting problem: the problem of determining whether a " +
                "given program will finish running or continue to run forever from an initial input.</div></html>");

        gameDescription.setPreferredSize(new Dimension(contentPanel.getWidth() - 30, gameDescription.getPreferredSize().height + 50));
        gameDescription.setAlignmentX(Component.CENTER_ALIGNMENT);
        contentPanel.add(gameDescription);

        for (int i = 0; i < imagePaths.length; i++) {
            String imagePath = imagePaths[i];
            JLabel textLabel = new JLabel("<html><div style='font-size:10px; text-align:justify; padding-bottom:10px;'>"
                    + imageDescriptions[i] + "</div></html>");
            textLabel.setPreferredSize(new Dimension(contentPanel.getWidth() - 30, textLabel.getPreferredSize().height + 40));
            textLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

            ImageIcon icon = new ImageIcon(getClass().getResource(imagePath));
            Image image = icon.getImage();
            icon = new ImageIcon(image);

            JLabel imageLabel = new JLabel(icon);
            imageLabel.setHorizontalAlignment(SwingConstants.CENTER);
            imageLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

            imageLabel.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    generatePattern(imagePath);
                }
            });

            contentPanel.add(textLabel);
            contentPanel.add(imageLabel);
        }

        JLabel endLabel = new JLabel("<html><div style='font-size:10px; text-align:justify; padding-top:70px;'>" +
                "These and many more patterns are available to read and try out at https://conwaylife.com/wiki/</div></html>");
        endLabel.setPreferredSize(new Dimension(contentPanel.getWidth() - 30, endLabel.getPreferredSize().height));
        endLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        contentPanel.add(endLabel);

        JScrollPane scrollPane = new JScrollPane(contentPanel);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);

        JButton closeButton = new JButton("Close");
        closeButton.setFocusable(false);
        closeButton.addActionListener(e -> dispose());

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(closeButton);

        add(scrollPane, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        setSize(650, 850);
        setLocationRelativeTo(Game.boardPanel);
    }

    private void generatePattern(String imagePath) {
        int[][] pattern = {};

        switch (imagePath) {
            case "/pictures/glider.png":
                pattern = new int[][]{
                        {0, 1, 0},
                        {0, 0, 1},
                        {1, 1, 1}
                };
                break;
            case "/pictures/LWSS.png":
                pattern = new int[][]{
                        {1, 1, 1, 1, 0},
                        {1, 0, 0, 0, 1},
                        {1, 0, 0, 0, 0},
                        {0, 1, 0, 0, 1}
                };
                break;
            case "/pictures/MWSS.png":
                pattern = new int[][]{
                        {1, 1, 1, 1, 1, 0},
                        {1, 0, 0, 0, 0, 1},
                        {1, 0, 0, 0, 0, 0},
                        {0, 1, 0, 0, 0, 1},
                        {0, 0, 0, 1, 0, 0}
                };
                break;
            case "/pictures/1-2-3.png":
                pattern = new int[][]{
                        {0, 0, 1, 1, 0, 0, 0, 0, 0, 0},
                        {1, 0, 0, 1, 0, 0, 0, 0, 0, 0},
                        {1, 1, 0, 1, 0, 1, 1, 0, 0, 0},
                        {0, 1, 0, 1, 0, 0, 1, 0, 0, 0},
                        {0, 1, 0, 0, 0, 0, 1, 0, 1, 1},
                        {0, 0, 1, 1, 1, 0, 1, 0, 1, 1},
                        {0, 0, 0, 0, 0, 1, 0, 0, 0, 0},
                        {0, 0, 0, 0, 1, 0, 0, 0, 0, 0},
                        {0, 0, 0, 0, 1, 1, 0, 0, 0, 0}
                };
                break;
            case "/pictures/1-2-3-4.png":
                pattern = new int[][]{
                        {0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0},
                        {0, 0, 0, 0, 1, 0, 1, 0, 0, 0, 0},
                        {0, 0, 0, 1, 0, 1, 0, 1, 0, 0, 0},
                        {0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0},
                        {1, 1, 0, 1, 0, 1, 0, 1, 0, 1, 1},
                        {1, 0, 1, 0, 0, 0, 0, 0, 1, 0, 1},
                        {0, 0, 0, 1, 1, 1, 1, 1, 0, 0, 0},
                        {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                        {0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0},
                        {0, 0, 0, 0, 1, 0, 1, 0, 0, 0, 0},
                        {0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0}
                };
                break;
            case "/pictures/135-degree-converter.png":
                pattern = new int[][]{
                        {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1},
                        {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 1},
                        {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 1, 0, 1, 1, 1, 0},
                        {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 1, 0, 0, 0},
                        {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0},
                        {0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0},
                        {1, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0},
                        {0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 1, 1, 0},
                        {1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 1, 0, 0, 1, 1, 0},
                        {0, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0}
                };
                break;
            case "/pictures/pulsar.png":
                pattern = new int[][]{
                        {0, 0, 1, 1, 1, 0, 0, 0, 1, 1, 1, 0, 0},
                        {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                        {1, 0, 0, 0, 0, 1, 0, 1, 0, 0, 0, 0, 1},
                        {1, 0, 0, 0, 0, 1, 0, 1, 0, 0, 0, 0, 1},
                        {1, 0, 0, 0, 0, 1, 0, 1, 0, 0, 0, 0, 1},
                        {0, 0, 1, 1, 1, 0, 0, 0, 1, 1, 1, 0, 0},
                        {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                        {0, 0, 1, 1, 1, 0, 0, 0, 1, 1, 1, 0, 0},
                        {1, 0, 0, 0, 0, 1, 0, 1, 0, 0, 0, 0, 1},
                        {1, 0, 0, 0, 0, 1, 0, 1, 0, 0, 0, 0, 1},
                        {1, 0, 0, 0, 0, 1, 0, 1, 0, 0, 0, 0, 1},
                        {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                        {0, 0, 1, 1, 1, 0, 0, 0, 1, 1, 1, 0, 0},
                };
                break;
            case "/pictures/bracket-pulsar.png":
                pattern = new int[][]{
                        {0, 0, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 0, 0},
                        {0, 0, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 0, 0},
                        {0, 0, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 0, 0},
                        {0, 0, 0, 0, 1, 1, 0, 0, 0, 1, 1, 0, 0, 0, 0},
                        {0, 0, 0, 1, 0, 1, 0, 0, 0, 1, 0, 1, 0, 0, 0},
                        {1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1},
                        {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                        {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                        {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                        {1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1},
                        {0, 0, 0, 1, 0, 1, 0, 0, 0, 1, 0, 1, 0, 0, 0},
                        {0, 0, 0, 0, 1, 1, 0, 0, 0, 1, 1, 0, 0, 0, 0},
                        {0, 0, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 0, 0},
                        {0, 0, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 0, 0},
                        {0, 0, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 0, 0},
                };
                break;
            case "/pictures/cross.png":
                pattern = new int[][]{
                        {0, 0, 0, 1, 0, 0, 1, 0, 0, 0},
                        {0, 0, 0, 1, 0, 0, 1, 0, 0, 0},
                        {0, 0, 1, 1, 0, 0, 1, 1, 0, 0},
                        {1, 1, 1, 0, 0, 0, 0, 1, 1, 1},
                        {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                        {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                        {1, 1, 1, 0, 0, 0, 0, 1, 1, 1},
                        {0, 0, 1, 1, 0, 0, 1, 1, 0, 0},
                        {0, 0, 0, 1, 0, 0, 1, 0, 0, 0},
                        {0, 0, 0, 1, 0, 0, 1, 0, 0, 0}
                };
                break;
            case "/pictures/queen-bee-shuttle.png":
                pattern = new int[][]{
                        {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                        {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 1, 0, 0, 0, 0, 0, 0, 0},
                        {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 1, 0, 0, 0, 0, 1, 1},
                        {1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 1, 0, 0, 0, 1, 1},
                        {1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 1, 0, 0, 0, 0, 0, 0},
                        {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 1, 0, 0, 0, 0, 0, 0, 0},
                        {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0}
                };
                break;
            case "/pictures/gosper-glider-gun.png":
                pattern = new int[][]{
                        {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                        {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                        {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                        {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 1, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1},
                        {1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 1, 1, 0, 0, 1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1},
                        {1, 1, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0, 0, 1, 1, 0, 0, 0, 1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                        {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                        {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                        {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}
                };
                break;
            case "/pictures/25P3H1V0-1.png":
                pattern = new int[][]{
                        {0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 1, 0, 0, 0, 0, 0},
                        {0, 0, 0, 0, 1, 1, 0, 1, 0, 1, 1, 0, 1, 1, 1, 0},
                        {0, 1, 1, 1, 1, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 1},
                        {1, 0, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 1, 1, 0},
                        {0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}
                };
                break;
            case "/pictures/25P3H1V0-2.png":
                pattern = new int[][]{
                        {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0},
                        {0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 1, 1, 1, 0},
                        {0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 1},
                        {0, 0, 1, 0, 0, 0, 1, 0, 0, 1, 0, 0, 0, 1, 1, 0},
                        {0, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                        {1, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                        {0, 1, 0, 1, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                        {0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}
                };
                break;
            case "/pictures/4-8-12-diamond.png":
                pattern = new int[][]{
                        {0, 0, 0, 0, 1, 1, 1, 1, 0, 0, 0, 0},
                        {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                        {0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0},
                        {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                        {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
                        {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                        {0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0},
                        {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                        {0, 0, 0, 0, 1, 1, 1, 1, 0, 0, 0, 0},
                };
                break;
            case "/pictures/44P5H2V0.png":
                pattern = new int[][]{
                        {0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0},
                        {0, 0, 0, 1, 1, 1, 0, 0, 0, 1, 1, 1, 0, 0, 0},
                        {0, 0, 1, 0, 0, 1, 0, 0, 0, 1, 0, 0, 1, 0, 0},
                        {0, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0},
                        {0, 0, 1, 0, 1, 0, 0, 0, 0, 0, 1, 0, 1, 0, 0},
                        {0, 0, 0, 0, 1, 1, 0, 0, 0, 1, 1, 0, 0, 0, 0},
                        {1, 0, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 0, 1},
                        {0, 0, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 0, 0},
                        {1, 1, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 1, 1},
                        {0, 0, 1, 0, 0, 1, 0, 0, 0, 1, 0, 0, 1, 0, 0},
                        {0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0}
                };
                break;
            default:
                System.out.println("Unknown image path.");
                break;
        }

        int startX = Game.getCOLS() / 2 - pattern[0].length / 2;
        int startY = Game.getROWS() / 2 - pattern.length / 2;

        Game.clearBoard();
        for (int i = 0; i < pattern.length; i++) {
            System.arraycopy(pattern[i], 0, Game.board[startY + i], startX, pattern[0].length);
        }

        Game.boardPanel.repaint();
        Game.updatePopulationLabel();
        dispose();
    }
}
