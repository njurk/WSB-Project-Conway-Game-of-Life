import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Game extends JFrame {
    static JPanel boardPanel;
    static JPanel controlPanel;
    private static JLabel generationsLabel;
    private static JLabel populationLabel;
    private static int generationCounter = 0;
    private static int ROWS = 1;
    private static int COLS = 1;
    static int[][] board = new int[ROWS][COLS];
    private final JSlider speedSlider;
    private final JComboBox<String> gridSizeComboBox;
    private final JButton startStopButton;
    private final JButton darkModeButton;
    private final JButton gridBorderButton;
    private final JButton randomizeButton;
    private final JButton clearButton;
    private final JButton exitButton;
    private final JButton lexiconButton;
    private final JSpinner overpopulationSpinner;
    private final JSpinner survivalSpinner;
    private final String[] gridSizeNames = {"Tiny", "Small", "Medium", "Large", "Extreme"};
    private boolean running = false;
    private Thread gameThread;
    private boolean darkMode = false;
    private boolean gridBorder = false;
    private int CELL_SIZE = 1;
    private int[][] nextBoard = new int[ROWS][COLS];

    public Game() {
        setTitle("Conway's Game of Life");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(Color.WHITE);
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        boardPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                calculateCellSize();
                int cellWidth = CELL_SIZE;
                int cellHeight = CELL_SIZE;

                int xOffset = (getWidth() - (COLS * cellWidth)) / 2;
                int yOffset = (getHeight() - (ROWS * cellHeight)) / 2;

                for (int i = 0; i < ROWS; i++) {
                    for (int j = 0; j < COLS; j++) {
                        if (board[i][j] == 1) {
                            g.setColor(darkMode ? Color.WHITE : Color.BLACK);
                            g.fillRect(j * cellWidth + xOffset, i * cellHeight + yOffset, cellWidth, cellHeight);
                        } else {
                            g.setColor(darkMode ? Color.BLACK : Color.WHITE);
                            g.fillRect(j * cellWidth + xOffset, i * cellHeight + yOffset, cellWidth, cellHeight);
                        }
                        if (gridBorder) {
                            g.setColor(darkMode ? Color.darkGray : Color.lightGray);
                            g.drawRect(j * cellWidth + xOffset, i * cellHeight + yOffset, cellWidth, cellHeight);
                        }
                    }
                }
            }
        };
        boardPanel.setBackground(darkMode ? Color.BLACK : Color.WHITE);
        boardPanel.setSize(1200, 800);

        MouseAdapter leftMouseAdapter = new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                handleLeftMouseEvent(e);
                updatePopulationLabel();
            }

            @Override
            public void mouseDragged(MouseEvent e) {
                handleLeftMouseEvent(e);
                updatePopulationLabel();
            }

            private void handleLeftMouseEvent(MouseEvent e) {
                if (SwingUtilities.isLeftMouseButton(e)) {
                    int x = e.getX();
                    int y = e.getY();

                    int cellWidth = CELL_SIZE;
                    int cellHeight = CELL_SIZE;

                    int xOffset = (boardPanel.getWidth() - (COLS * cellWidth)) / 2;
                    int yOffset = (boardPanel.getHeight() - (ROWS * cellHeight)) / 2;

                    int col = (x - xOffset) / cellWidth;
                    int row = (y - yOffset) / cellHeight;

                    if (col >= 0 && col < COLS && row >= 0 && row < ROWS) {
                        board[row][col] = 1;
                        boardPanel.repaint();
                    }
                }
            }
        };

        MouseAdapter rightMouseAdapter = new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                handleRightMouseEvent(e);
                updatePopulationLabel();
            }

            @Override
            public void mouseDragged(MouseEvent e) {
                handleRightMouseEvent(e);
                updatePopulationLabel();
            }

            private void handleRightMouseEvent(MouseEvent e) {
                if (SwingUtilities.isRightMouseButton(e)) {
                    int x = e.getX();
                    int y = e.getY();

                    int cellWidth = CELL_SIZE;
                    int cellHeight = CELL_SIZE;

                    int xOffset = (boardPanel.getWidth() - (COLS * cellWidth)) / 2;
                    int yOffset = (boardPanel.getHeight() - (ROWS * cellHeight)) / 2;

                    int col = (x - xOffset) / cellWidth;
                    int row = (y - yOffset) / cellHeight;

                    if (col >= 0 && col < COLS && row >= 0 && row < ROWS) {
                        board[row][col] = 0;
                        boardPanel.repaint();
                    }
                }
            }
        };

        boardPanel.addMouseListener(leftMouseAdapter);
        boardPanel.addMouseMotionListener(leftMouseAdapter);
        boardPanel.addMouseListener(rightMouseAdapter);
        boardPanel.addMouseMotionListener(rightMouseAdapter);

        controlPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        controlPanel.setBackground(darkMode ? Color.BLACK : Color.WHITE);
        controlPanel.setFocusable(false);
        speedSlider = new JSlider(20, 500);
        speedSlider.setFocusable(false);
        speedSlider.setInverted(true);
        speedSlider.setValue(200);
        speedSlider.setBackground(Color.WHITE);
        gridSizeComboBox = new JComboBox<>(gridSizeNames);
        gridSizeComboBox.setFocusable(false);
        gridSizeComboBox.setSelectedItem("Medium");
        gridSizeComboBox.addActionListener(e -> changeGridSize());
        startStopButton = new JButton("Start");
        startStopButton.setFocusable(false);
        darkModeButton = new JButton("Dark Mode");
        darkModeButton.setFocusable(false);
        darkModeButton.addActionListener(e -> toggleDarkMode());
        gridBorderButton = new JButton("Grid Border");
        gridBorderButton.setFocusable(false);
        gridBorderButton.addActionListener(e -> toggleGridBorder());
        randomizeButton = new JButton("Randomize");
        randomizeButton.setFocusable(false);
        randomizeButton.addActionListener(e -> initBoard());
        clearButton = new JButton("Clear");
        clearButton.setFocusable(false);
        clearButton.addActionListener(e -> clearBoard());
        exitButton = new JButton("Exit");
        exitButton.setFocusable(false);
        exitButton.addActionListener(e -> System.exit(0));
        lexiconButton = new JButton("Lexicon");
        lexiconButton.setFocusable(false);
        lexiconButton.addActionListener(e -> showLexiconWindow());

        generationsLabel = new JLabel("Generations: 0");
        controlPanel.add(generationsLabel);

        populationLabel = new JLabel("Population: 0");
        controlPanel.add(populationLabel);

        JLabel overpopulationLabel = new JLabel("Overpopulation: ");
        overpopulationSpinner = new JSpinner(new SpinnerNumberModel(4, 1, 8, 1));
        JLabel survivalLabel = new JLabel("Survival: ");
        survivalSpinner = new JSpinner(new SpinnerNumberModel(2, 1, 8, 1));

        startStopButton.addActionListener(e -> {
            if (!running) {
                running = true;
                startStopButton.setText("Stop");
                startStopButton.setBackground(Color.RED);
                gameThread = new Thread(() -> {
                    while (running) {
                        updateBoard();
                        try {
                            int selectedSpeed = speedSlider.getValue();
                            Thread.sleep(selectedSpeed);
                        } catch (InterruptedException ex) {
                            ex.printStackTrace();
                        }
                        boardPanel.repaint();
                        updatePopulationLabel();
                    }
                });
                gameThread.start();
            } else {
                running = false;
                startStopButton.setText("Start");
                startStopButton.setBackground(Color.GREEN);
            }
        });

        controlPanel.add(new JLabel("Speed: "));
        controlPanel.add(speedSlider);
        controlPanel.add(new JLabel("Grid size: "));
        controlPanel.add(gridSizeComboBox);
        controlPanel.add(startStopButton);
        controlPanel.add(randomizeButton);
        controlPanel.add(clearButton);
        controlPanel.add(gridBorderButton);
        controlPanel.add(darkModeButton);
        controlPanel.add(exitButton);
        controlPanel.add(lexiconButton);
        controlPanel.add(overpopulationLabel);
        controlPanel.add(overpopulationSpinner);
        controlPanel.add(survivalLabel);
        controlPanel.add(survivalSpinner);

        mainPanel.add(boardPanel, BorderLayout.CENTER);
        mainPanel.add(controlPanel, BorderLayout.SOUTH);

        setContentPane(mainPanel);

        pack();
        changeGridSize();
        setSize(1800, 1100);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    static void clearBoard() {
        generationCounter = 0;
        generationsLabel.setText("Generations: " + generationCounter);
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLS; j++) {
                board[i][j] = 0;
            }
        }
        boardPanel.repaint();
    }

    static void updatePopulationLabel() {
        int populationCounter = 0;
        for (int[] row : board) {
            for (int cell : row) {
                if (cell == 1) {
                    populationCounter++;
                }
            }
        }
        populationLabel.setText("Population: " + populationCounter);
    }

    private void initBoard() {
        generationCounter = 0;
        generationsLabel.setText("Generations: " + generationCounter);
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLS; j++) {
                board[i][j] = (int) (Math.random() * 2);
            }
        }
        boardPanel.repaint();
    }

    private void updateBoard() {
        int overpopulation = (int) overpopulationSpinner.getValue();
        int survival = (int) survivalSpinner.getValue();

        generationCounter++;
        generationsLabel.setText("Generations: " + generationCounter);
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLS; j++) {
                int liveNeighbours = countLiveNeighbourCells(i, j);
                if (board[i][j] == 1 && (liveNeighbours >= survival && liveNeighbours < overpopulation)) {
                    nextBoard[i][j] = 1;
                } else if (board[i][j] == 0 && liveNeighbours == 3) {
                    nextBoard[i][j] = 1;
                } else {
                    nextBoard[i][j] = 0;
                }
            }
        }
        for (int i = 0; i < ROWS; i++) {
            System.arraycopy(nextBoard[i], 0, board[i], 0, COLS);
        }
    }

    private int countLiveNeighbourCells(int row, int col) {
        int count = 0;
        for (int i = row - 1; i <= row + 1; i++) {
            for (int j = col - 1; j <= col + 1; j++) {
                if ((i == row && j == col) || i < 0 || j < 0 || i >= ROWS || j >= COLS) {
                    continue;
                }
                if (board[i][j] == 1) {
                    count++;
                }
            }
        }
        return count;
    }

    private void toggleDarkMode() {
        darkMode = !darkMode;
        boardPanel.setBackground(darkMode ? Color.BLACK : Color.WHITE);
        boardPanel.repaint();
        getContentPane().setBackground(darkMode ? Color.BLACK : Color.WHITE);
        getContentPane().repaint();

        if (darkMode) {
            setDarkMode(controlPanel);
            overpopulationSpinner.setForeground(Color.WHITE);
            survivalSpinner.setForeground(Color.WHITE);
            ((JSpinner.DefaultEditor) overpopulationSpinner.getEditor()).getTextField().setForeground(Color.WHITE);
            ((JSpinner.DefaultEditor) survivalSpinner.getEditor()).getTextField().setForeground(Color.WHITE);
        } else {
            setLightMode(controlPanel);
            overpopulationSpinner.setForeground(Color.BLACK);
            survivalSpinner.setForeground(Color.BLACK);
            ((JSpinner.DefaultEditor) overpopulationSpinner.getEditor()).getTextField().setForeground(Color.BLACK);
            ((JSpinner.DefaultEditor) survivalSpinner.getEditor()).getTextField().setForeground(Color.BLACK);
        }
    }

    private void toggleGridBorder() {
        gridBorder = !gridBorder;
        boardPanel.repaint();
    }

    private void changeGridSize() {
        String selectedSize = (String) gridSizeComboBox.getSelectedItem();
        switch (selectedSize) {
            case "Tiny":
                ROWS = 19;
                COLS = 34;
                break;
            case "Small":
                ROWS = 45;
                COLS = 79;
                break;
            case "Medium":
                ROWS = 66;
                COLS = 117;
                break;
            case "Large":
                ROWS = 125;
                COLS = 220;
                break;
            case "Extreme":
                ROWS = 250;
                COLS = 440;
                break;
        }
        board = new int[ROWS][COLS];
        nextBoard = new int[ROWS][COLS];
        initBoard();
        calculateCellSize();
        boardPanel.repaint();
    }

    private void calculateCellSize() {
        int panelWidth = boardPanel.getWidth();
        int panelHeight = boardPanel.getHeight();
        CELL_SIZE = Math.min(panelWidth / COLS, panelHeight / ROWS);
    }

    private void setDarkMode(Component comp) {
        comp.setBackground(Color.BLACK);
        if (comp instanceof JButton || comp instanceof JComboBox || comp instanceof JSpinner) {
            comp.setForeground(Color.WHITE);
        } else if (comp instanceof JLabel) {
            comp.setForeground(Color.WHITE);
        }
        if (comp instanceof Container) {
            for (Component c : ((Container) comp).getComponents()) {
                setDarkMode(c);
            }
        }
    }

    private void setLightMode(Component comp) {
        comp.setBackground(Color.WHITE);
        if (comp instanceof JButton || comp instanceof JComboBox || comp instanceof JSpinner) {
            comp.setForeground(Color.BLACK);
        } else if (comp instanceof JLabel) {
            comp.setForeground(Color.BLACK);
        }
        if (comp instanceof Container) {
            for (Component c : ((Container) comp).getComponents()) {
                setLightMode(c);
            }
        }
    }

    private void showLexiconWindow() {
        Lexicon lexicon = new Lexicon();
        lexicon.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Game());
    }

    public static int getROWS() {
        return ROWS;
    }

    public static int getCOLS() {
        return COLS;
    }
}