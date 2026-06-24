import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

public class WellCheck {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                // Set the system look and feel for better appearance
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (Exception e) {
                e.printStackTrace();
            }
            new MedicalAdviceApp();
        });
    }
}

// Main application class
class MedicalAdviceApp {
    private JFrame frame;
    private JTextArea symptomsInput;
    private JPanel analysisPanel;
    private JPanel learningPanel;
    
    // Colors for modern UI
    private final Color PRIMARY_COLOR = new Color(41, 98, 168);
    private final Color SECONDARY_COLOR = new Color(242, 247, 255);
    private final Color ACCENT_COLOR = new Color(229, 235, 250);
    private final Color TEXT_COLOR = new Color(51, 51, 51);
    private final Color BUTTON_HOVER_COLOR = new Color(30, 75, 135);

    public MedicalAdviceApp() {
        createAndShowGUI();
    }

    private void createAndShowGUI() {
        frame = new JFrame("WellCheck - Medical Advice System");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000, 800);
        frame.setLayout(new BorderLayout(10, 10));
        frame.getContentPane().setBackground(Color.WHITE);

        // Main content panel with padding
        JPanel mainPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                GradientPaint gp = new GradientPaint(0, 0, Color.WHITE, 0, getHeight(), SECONDARY_COLOR);
                g2d.setPaint(gp);
                g2d.fillRect(0, 0, getWidth(), getHeight());
            }
        };
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBorder(new EmptyBorder(30, 30, 30, 30));

        // Title
        JLabel titleLabel = new JLabel("Check Your Symptoms");
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 32));
        titleLabel.setForeground(PRIMARY_COLOR);
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Subtitle
        JLabel subtitleLabel = new JLabel("Get instant analysis and learn about your health conditions");
        subtitleLabel.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        subtitleLabel.setForeground(TEXT_COLOR);
        subtitleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Symptoms input area with improved styling
        symptomsInput = new JTextArea(5, 40);
        symptomsInput.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        symptomsInput.setLineWrap(true);
        symptomsInput.setWrapStyleWord(true);
        symptomsInput.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(200, 200, 200), 1, true),
            BorderFactory.createEmptyBorder(10, 10, 10, 10)));
        symptomsInput.setForeground(TEXT_COLOR);
        
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new BoxLayout(inputPanel, BoxLayout.Y_AXIS));
        inputPanel.setOpaque(false);
        inputPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        inputPanel.setMaximumSize(new Dimension(800, 150));
        
        JLabel inputLabel = new JLabel("Describe your symptoms here...");
        inputLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        inputLabel.setForeground(new Color(100, 100, 100));
        inputLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        
        inputPanel.add(inputLabel);
        inputPanel.add(Box.createRigidArea(new Dimension(0, 5)));
        
        JScrollPane scrollPane = new JScrollPane(symptomsInput);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        scrollPane.setAlignmentX(Component.LEFT_ALIGNMENT);
        inputPanel.add(scrollPane);

        // Container for the input panel (to center it)
        JPanel inputContainer = new JPanel();
        inputContainer.setLayout(new FlowLayout(FlowLayout.CENTER));
        inputContainer.setOpaque(false);
        inputContainer.add(inputPanel);

        // Custom button with hover effect
        JButton analyzeButton = new JButton("Analyze Symptoms") {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2d = (Graphics2D) g;
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                if (getModel().isPressed()) {
                    g2d.setColor(BUTTON_HOVER_COLOR);
                } else if (getModel().isRollover()) {
                    g2d.setColor(BUTTON_HOVER_COLOR);
                } else {
                    g2d.setColor(PRIMARY_COLOR);
                }
                g2d.fillRoundRect(0, 0, getWidth(), getHeight(), 10, 10);
                g2d.setColor(Color.WHITE);
                String text = getText();
                java.awt.FontMetrics metrics = g2d.getFontMetrics();
                int x = (getWidth() - metrics.stringWidth(text)) / 2;
                int y = ((getHeight() - metrics.getHeight()) / 2) + metrics.getAscent();
                g2d.drawString(text, x, y);
            }
        };
        analyzeButton.setFont(new Font("Segoe UI", Font.BOLD, 14));
        analyzeButton.setForeground(Color.WHITE);
        analyzeButton.setBackground(PRIMARY_COLOR);
        analyzeButton.setFocusPainted(false);
        analyzeButton.setBorderPainted(false);
        analyzeButton.setContentAreaFilled(false);
        analyzeButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        analyzeButton.setPreferredSize(new Dimension(200, 40));
        analyzeButton.setMaximumSize(new Dimension(200, 40));
        analyzeButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        analyzeButton.addActionListener(e -> analyzeSymptoms());
        
        // Add hover effect
        analyzeButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                analyzeButton.repaint();
            }
            
            @Override
            public void mouseExited(MouseEvent e) {
                analyzeButton.repaint();
            }
        });

        // Container for the button (to center it)
        JPanel buttonContainer = new JPanel();
        buttonContainer.setLayout(new FlowLayout(FlowLayout.CENTER));
        buttonContainer.setOpaque(false);
        buttonContainer.add(analyzeButton);

        // Analysis Results Panel with improved styling
        analysisPanel = new JPanel();
        analysisPanel.setLayout(new BoxLayout(analysisPanel, BoxLayout.Y_AXIS));
        analysisPanel.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));
        analysisPanel.setOpaque(false);
        
        // Learning Section Panel with improved styling
        learningPanel = new JPanel();
        learningPanel.setLayout(new BoxLayout(learningPanel, BoxLayout.Y_AXIS));
        learningPanel.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));
        learningPanel.setOpaque(false);

        // Add components to main panel
        mainPanel.add(titleLabel);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 15)));
        mainPanel.add(subtitleLabel);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 30)));
        mainPanel.add(inputContainer);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 25)));
        mainPanel.add(buttonContainer);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 30)));
        mainPanel.add(analysisPanel);
        mainPanel.add(learningPanel);

        // Add main panel to frame with scroll capability
        JScrollPane mainScrollPane = new JScrollPane(mainPanel);
        mainScrollPane.setBorder(BorderFactory.createEmptyBorder());
        mainScrollPane.getVerticalScrollBar().setUnitIncrement(16);
        frame.add(mainScrollPane);

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private void analyzeSymptoms() {
        String symptoms = symptomsInput.getText().toLowerCase();
        analysisPanel.removeAll();
        learningPanel.removeAll();

        // Create a container for analysis section with proper centering
        JPanel analysisContainer = new JPanel();
        analysisContainer.setLayout(new BoxLayout(analysisContainer, BoxLayout.Y_AXIS));
        analysisContainer.setOpaque(false);
        analysisContainer.setAlignmentX(Component.LEFT_ALIGNMENT);
        
        // Add Analysis Results header with improved styling
        JLabel analysisTitle = new JLabel("Analysis Results");
        analysisTitle.setFont(new Font("Segoe UI", Font.BOLD, 24));
        analysisTitle.setForeground(PRIMARY_COLOR);
        analysisTitle.setAlignmentX(Component.LEFT_ALIGNMENT);
        analysisContainer.add(analysisTitle);
        analysisContainer.add(Box.createRigidArea(new Dimension(0, 15)));

        // Process symptoms and show results
        MedicalAnalyzer analyzer = new MedicalAnalyzer();
        SymptomAnalysis analysis = analyzer.analyzeSymptoms(symptoms);

        // Display analysis results with improved styling
        JPanel resultPanel = new RoundedPanel(15, SECONDARY_COLOR);
        resultPanel.setLayout(new BorderLayout());
        resultPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        resultPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
        
        JTextArea analysisText = new JTextArea(analysis.getAdvice());
        analysisText.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        analysisText.setLineWrap(true);
        analysisText.setWrapStyleWord(true);
        analysisText.setEditable(false);
        analysisText.setBackground(SECONDARY_COLOR);
        analysisText.setForeground(TEXT_COLOR);
        analysisText.setBorder(BorderFactory.createEmptyBorder());
        
        JScrollPane analysisScrollPane = new JScrollPane(analysisText);
        analysisScrollPane.setBorder(BorderFactory.createEmptyBorder());
        analysisScrollPane.setBackground(SECONDARY_COLOR);
        analysisScrollPane.setPreferredSize(new Dimension(850, 250));
        
        resultPanel.add(analysisScrollPane, BorderLayout.CENTER);
        analysisContainer.add(resultPanel);
        analysisContainer.add(Box.createRigidArea(new Dimension(0, 30)));
        
        // Add the analysis container to a centered panel
        JPanel centeringPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        centeringPanel.setOpaque(false);
        centeringPanel.add(analysisContainer);
        analysisPanel.add(centeringPanel);
        
        // Create a container for learning section
        JPanel learningContainer = new JPanel();
        learningContainer.setLayout(new BoxLayout(learningContainer, BoxLayout.Y_AXIS));
        learningContainer.setOpaque(false);
        learningContainer.setAlignmentX(Component.LEFT_ALIGNMENT);

        // Add Learning Section with improved styling
        if (!analysis.getConditions().isEmpty()) {
            JLabel learningTitle = new JLabel("Learn About Your Symptoms");
            learningTitle.setFont(new Font("Segoe UI", Font.BOLD, 24));
            learningTitle.setForeground(PRIMARY_COLOR);
            learningTitle.setAlignmentX(Component.LEFT_ALIGNMENT);
            learningContainer.add(learningTitle);
            learningContainer.add(Box.createRigidArea(new Dimension(0, 15)));

            // Add learning cards
            for (String condition : analysis.getConditions()) {
                addLearningCard(condition, learningContainer);
            }
        }
        
        // Add the learning container to a centered panel
        JPanel learningCenteringPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        learningCenteringPanel.setOpaque(false);
        learningCenteringPanel.add(learningContainer);
        learningPanel.add(learningCenteringPanel);

        frame.revalidate();
        frame.repaint();
    }

    private void addLearningCard(String condition, JPanel containerPanel) {
        RoundedPanel card = new RoundedPanel(15, ACCENT_COLOR);
        card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));
        card.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        card.setAlignmentX(Component.LEFT_ALIGNMENT);
        card.setPreferredSize(new Dimension(850, 180));

        String capitalizedCondition = condition.substring(0, 1).toUpperCase() + condition.substring(1);
        JLabel conditionLabel = new JLabel(capitalizedCondition);
        conditionLabel.setFont(new Font("Segoe UI", Font.BOLD, 16));
        conditionLabel.setForeground(PRIMARY_COLOR);
        conditionLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        
        JTextArea description = new JTextArea(MedicalDatabase.getConditionDescription(condition));
        description.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        description.setLineWrap(true);
        description.setWrapStyleWord(true);
        description.setEditable(false);
        description.setBackground(ACCENT_COLOR);
        description.setForeground(TEXT_COLOR);
        description.setBorder(BorderFactory.createEmptyBorder());
        description.setAlignmentX(Component.LEFT_ALIGNMENT);

        JButton learnMoreButton = new JButton("Learn More →") {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2d = (Graphics2D) g;
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                if (getModel().isPressed()) {
                    g2d.setColor(BUTTON_HOVER_COLOR);
                } else if (getModel().isRollover()) {
                    g2d.setColor(BUTTON_HOVER_COLOR);
                } else {
                    g2d.setColor(PRIMARY_COLOR);
                }
                g2d.fillRoundRect(0, 0, getWidth(), getHeight(), 10, 10);
                g2d.setColor(Color.WHITE);
                String text = getText();
                java.awt.FontMetrics metrics = g2d.getFontMetrics();
                int x = (getWidth() - metrics.stringWidth(text)) / 2;
                int y = ((getHeight() - metrics.getHeight()) / 2) + metrics.getAscent();
                g2d.drawString(text, x, y);
            }
        };
        learnMoreButton.setFont(new Font("Segoe UI", Font.BOLD, 14));
        learnMoreButton.setForeground(Color.WHITE);
        learnMoreButton.setBackground(PRIMARY_COLOR);
        learnMoreButton.setFocusPainted(false);
        learnMoreButton.setBorderPainted(false);
        learnMoreButton.setContentAreaFilled(false);
        learnMoreButton.setMaximumSize(new Dimension(150, 35));
        learnMoreButton.setAlignmentX(Component.LEFT_ALIGNMENT);
        learnMoreButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        learnMoreButton.addActionListener(e -> showDetailedInfo(condition));
        
        // Add hover effect
        learnMoreButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                learnMoreButton.repaint();
            }
            
            @Override
            public void mouseExited(MouseEvent e) {
                learnMoreButton.repaint();
            }
        });

        card.add(conditionLabel);
        card.add(Box.createRigidArea(new Dimension(0, 10)));
        card.add(description);
        card.add(Box.createRigidArea(new Dimension(0, 10)));
        card.add(learnMoreButton);

        containerPanel.add(card);
        containerPanel.add(Box.createRigidArea(new Dimension(0, 15)));
    }

    private void showDetailedInfo(String condition) {
        JDialog dialog = new JDialog(frame, "Detailed Information: " + condition, true);
        dialog.setSize(600, 500);
        dialog.setLayout(new BorderLayout(10, 10));
        dialog.getContentPane().setBackground(Color.WHITE);

        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new BorderLayout(10, 10));
        contentPanel.setBackground(Color.WHITE);
        contentPanel.setBorder(new EmptyBorder(20, 20, 20, 20));

        String capitalizedCondition = condition.substring(0, 1).toUpperCase() + condition.substring(1);
        JLabel titleLabel = new JLabel(capitalizedCondition);
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 20));
        titleLabel.setForeground(PRIMARY_COLOR);
        contentPanel.add(titleLabel, BorderLayout.NORTH);

        JTextArea detailsArea = new JTextArea(MedicalDatabase.getDetailedInfo(condition));
        detailsArea.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        detailsArea.setLineWrap(true);
        detailsArea.setWrapStyleWord(true);
        detailsArea.setEditable(false);
        detailsArea.setBackground(Color.WHITE);
        detailsArea.setForeground(TEXT_COLOR);
        detailsArea.setBorder(new EmptyBorder(10, 0, 10, 0));

        JScrollPane scrollPane = new JScrollPane(detailsArea);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);
        contentPanel.add(scrollPane, BorderLayout.CENTER);

        JButton closeButton = new JButton("Close") {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2d = (Graphics2D) g;
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                if (getModel().isPressed()) {
                    g2d.setColor(BUTTON_HOVER_COLOR);
                } else if (getModel().isRollover()) {
                    g2d.setColor(BUTTON_HOVER_COLOR);
                } else {
                    g2d.setColor(PRIMARY_COLOR);
                }
                g2d.fillRoundRect(0, 0, getWidth(), getHeight(), 10, 10);
                g2d.setColor(Color.WHITE);
                String text = getText();
                java.awt.FontMetrics metrics = g2d.getFontMetrics();
                int x = (getWidth() - metrics.stringWidth(text)) / 2;
                int y = ((getHeight() - metrics.getHeight()) / 2) + metrics.getAscent();
                g2d.drawString(text, x, y);
            }
        };
        closeButton.setFont(new Font("Segoe UI", Font.BOLD, 14));
        closeButton.setForeground(Color.WHITE);
        closeButton.setBackground(PRIMARY_COLOR);
        closeButton.setFocusPainted(false);
        closeButton.setBorderPainted(false);
        closeButton.setContentAreaFilled(false);
        closeButton.setPreferredSize(new Dimension(100, 35));
        closeButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        closeButton.addActionListener(e -> dialog.dispose());
        
        // Add hover effect
        closeButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                closeButton.repaint();
            }
            
            @Override
            public void mouseExited(MouseEvent e) {
                closeButton.repaint();
            }
        });

        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(Color.WHITE);
        buttonPanel.add(closeButton);
        contentPanel.add(buttonPanel, BorderLayout.SOUTH);

        dialog.add(contentPanel);
        dialog.setLocationRelativeTo(frame);
        dialog.setVisible(true);
    }
}

// Custom rounded panel for a more modern look
class RoundedPanel extends JPanel {
    private int radius;
    private Color backgroundColor;

    public RoundedPanel(int radius, Color bgColor) {
        super();
        this.radius = radius;
        this.backgroundColor = bgColor;
        setOpaque(false);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setColor(backgroundColor);
        g2d.fillRoundRect(0, 0, getWidth(), getHeight(), radius, radius);
    }
}

// Medical Analyzer class
class MedicalAnalyzer {
    private Map<String, java.util.List<String>> symptomKeywords;

    public MedicalAnalyzer() {
        initializeSymptomKeywords();
    }

    private void initializeSymptomKeywords() {
        symptomKeywords = new HashMap<>();
        
        // Fever related keywords
        symptomKeywords.put("fever", Arrays.asList("fever", "temperature", "hot", "chills"));
        
        // Cough related keywords
        symptomKeywords.put("cough", Arrays.asList("cough", "coughing", "throat"));
        
        // Headache related keywords
        symptomKeywords.put("headache", Arrays.asList("headache", "head pain", "migraine"));
        
        // Cold related keywords
        symptomKeywords.put("cold", Arrays.asList("cold", "runny nose", "sneezing", "congestion"));
        
        // Stomachache related keywords
        symptomKeywords.put("stomachache", Arrays.asList("stomachache", "stomach pain", "abdominal pain", "cramps"));
        
        // Sore throat related keywords
        symptomKeywords.put("sore throat", Arrays.asList("sore throat", "throat pain", "difficulty swallowing"));
    }

    public SymptomAnalysis analyzeSymptoms(String symptoms) {
        Set<String> detectedConditions = new HashSet<>();
        StringBuilder advice = new StringBuilder();
        
        // Convert input to lowercase for case-insensitive matching
        symptoms = symptoms.toLowerCase();

        // Check for each condition
        for (Map.Entry<String, java.util.List<String>> entry : symptomKeywords.entrySet()) {
            String condition = entry.getKey();
            java.util.List<String> keywords = entry.getValue();
            
            for (String keyword : keywords) {
                if (symptoms.contains(keyword)) {
                    detectedConditions.add(condition);
                    break;
                }
            }
        }

        // Generate advice based on detected conditions
        advice.append("Based on your description, we've detected the following symptoms:\n\n");
        
        if (detectedConditions.isEmpty()) {
            advice.append("No specific symptoms detected. Please provide more details about your condition.\n");
        } else {
            for (String condition : detectedConditions) {
                advice.append("• ").append(condition.substring(0, 1).toUpperCase())
                    .append(condition.substring(1)).append("\n");
            }
        }
        
        advice.append("\nGeneral Recommendations:\n");
        
        if (detectedConditions.contains("fever")) {
            advice.append("- Take rest and stay hydrated\n");
            advice.append("- Consider taking acetaminophen or ibuprofen for fever\n");
            advice.append("- Monitor your temperature regularly\n");
        }
        
        if (detectedConditions.contains("cough")) {
            advice.append("- Use honey and warm water for soothing throat\n");
            advice.append("- Consider using over-the-counter cough suppressants\n");
            advice.append("- Stay in a humid environment\n");
        }
        
        if (detectedConditions.contains("headache")) {
            advice.append("- Rest in a quiet, dark room\n");
            advice.append("- Try cold or warm compresses\n");
            advice.append("- Consider over-the-counter pain relievers\n");
        }
        
        if (detectedConditions.contains("cold")) {
            advice.append("- Get plenty of rest\n");
            advice.append("- Stay hydrated with warm fluids\n");
            advice.append("- Use saline nasal drops for congestion\n");
        }
        
        if (detectedConditions.contains("stomachache")) {
            advice.append("- Eat smaller, more frequent meals\n");
            advice.append("- Avoid spicy or fatty foods\n");
            advice.append("- Try over-the-counter antacids if appropriate\n");
        }
        
        if (detectedConditions.contains("sore throat")) {
            advice.append("- Gargle with salt water\n");
            advice.append("- Drink warm liquids with honey\n");
            advice.append("- Use throat lozenges for temporary relief\n");
        }

        advice.append("\nIMPORTANT: This is general advice only. Please consult a healthcare professional for proper diagnosis and treatment.");

        return new SymptomAnalysis(detectedConditions, advice.toString());
    }
}

// Symptom Analysis class
class SymptomAnalysis {
    private Set<String> conditions;
    private String advice;

    public SymptomAnalysis(Set<String> conditions, String advice) {
        this.conditions = conditions;
        this.advice = advice;
    }

    public Set<String> getConditions() {
        return conditions;
    }

    public String getAdvice() {
        return advice;
    }
}

// Medical Database class
class MedicalDatabase {
    public static String getConditionDescription(String condition) {
        switch (condition.toLowerCase()) {
            case "fever":
                return "A temporary increase in body temperature, often due to an illness. Common symptoms include sweating, chills, and weakness.";
            case "cough":
                return "A sudden expulsion of air from the lungs. Can be either acute or chronic, and may indicate various respiratory conditions.";
            case "headache":
                return "Pain or discomfort in the head, scalp, or neck. Can be primary (migraine, tension) or secondary (due to underlying conditions).";
            case "cold":
                return "A common viral infection of the nose and throat. Symptoms typically include runny nose, sneezing, and congestion.";
            case "stomachache":
                return "Pain or discomfort in the stomach or abdominal area. Can be caused by various factors including indigestion, infection, or inflammation.";
            case "sore throat":
                return "Pain, irritation, or scratchiness in the throat, often worsened by swallowing. Usually caused by viral infections or bacterial infections.";
            default:
                return "No detailed information available for this condition.";
        }
    }

    public static String getDetailedInfo(String condition) {
        switch (condition.toLowerCase()) {
            case "fever":
                return "FEVER (Pyrexia)\n\n" +
                       "Description:\n" +
                       "A fever is a temporary increase in body temperature, usually due to an infection. Having a fever is a sign that something out of the ordinary is going on in your body.\n\n" +
                       "Common Causes:\n" +
                       "- Viral infections\n" +
                       "- Bacterial infections\n" +
                       "- Heat exhaustion\n" +
                       "- Inflammatory conditions\n\n" +
                       "When to See a Doctor:\n" +
                       "- Adults: Temperature above 103°F (39.4°C)\n" +
                       "- Fever lasting more than 3 days\n" +
                       "- Severe symptoms like difficulty breathing\n\n" +
                       "Treatment Options:\n" +
                       "1. Rest and hydration\n" +
                       "2. Over-the-counter medications (acetaminophen, ibuprofen)\n" +
                       "3. Cool compresses\n" +
                       "4. Light clothing and comfortable room temperature";
            case "cough":
                return "COUGH\n\n" +
                       "Description:\n" +
                       "A cough is a reflex action to clear your airways of mucus and irritants such as dust or smoke.\n\n" +
                       "Common Causes:\n" +
                       "- Viral infections\n" +
                       "- Allergies\n" +
                       "- Asthma\n" +
                       "- Smoking\n\n" +
                       "When to See a Doctor:\n" +
                       "- Cough lasting more than 3 weeks\n" +
                       "- Coughing up blood\n" +
                       "- Shortness of breath\n\n" +
                       "Treatment Options:\n" +
                       "1. Stay hydrated\n" +
                       "2. Use cough drops or lozenges\n" +
                       "3. Use a humidifier\n" +
                       "4. Avoid smoking and secondhand smoke";
            case "headache":
                return "HEADACHE\n\n" +
                       "Description:\n" +
                       "A headache is pain or discomfort in the head or face area. It can be a symptom of stress or emotional distress, or it can result from a medical disorder.\n\n" +
                       "Common Causes:\n" +
                       "- Stress\n" +
                       "- Poor posture\n" +
                       "- Eye strain\n" +
                       "- Dehydration\n\n" +
                       "When to See a Doctor:\n" +
                       "- Severe headache with sudden onset\n" +
                       "- Headache with fever, stiff neck, or confusion\n" +
                       "- Headache after a head injury\n\n" +
                       "Treatment Options:\n" +
                       "1. Rest in a quiet, dark room\n" +
                       "2. Apply a cold or warm compress\n" +
                       "3. Over-the-counter pain relievers\n" +
                       "4. Stay hydrated";
            case "cold":
                return "COMMON COLD\n\n" +
                       "Description:\n" +
                       "The common cold is a viral infection of your nose and throat (upper respiratory tract). It's usually harmless, although it might not feel that way.\n\n" +
                       "Common Causes:\n" +
                       "- Rhinoviruses\n" +
                       "- Coronavirus\n" +
                       "- Respiratory syncytial virus\n\n" +
                       "When to See a Doctor:\n" +
                       "- Symptoms lasting more than 10 days\n" +
                       "- Severe symptoms such as high fever\n" +
                       "- Difficulty breathing\n\n" +
                       "Treatment Options:\n" +
                       "1. Rest and hydration\n" +
                       "2. Over-the-counter cold remedies\n" +
                       "3. Warm salt water gargle\n" +
                       "4. Humidifier use";
            case "stomachache":
                return "STOMACHACHE\n\n" +
                       "Description:\n" +
                       "A stomachache is a pain or discomfort in the abdominal area. It can be caused by a variety of conditions.\n\n" +
                       "Common Causes:\n" +
                       "- Indigestion\n" +
                       "- Gas\n" +
                       "- Constipation\n" +
                       "- Food poisoning\n\n" +
                       "When to See a Doctor:\n" +
                       "- Severe pain\n" +
                       "- Pain lasting more than a few days\n" +
                       "- Accompanied by fever or vomiting\n\n" +
                       "Treatment Options:\n" +
                       "1. Eat smaller meals\n" +
                       "2. Avoid spicy or fatty foods\n" +
                       "3. Over-the-counter antacids\n" +
                       "4. Stay hydrated";
            case "sore throat":
                return "SORE THROAT\n\n" +
                       "Description:\n" +
                       "A sore throat is pain, scratchiness or irritation of the throat that often worsens when you swallow.\n\n" +
                       "Common Causes:\n" +
                       "- Viral infections\n" +
                       "- Bacterial infections\n" +
                       "- Allergies\n" +
                       "- Dry air\n\n" +
                       "When to See a Doctor:\n" +
                       "- Severe sore throat\n" +
                       "- Difficulty breathing\n" +
                       "- Sore throat lasting more than a week\n\n" +
                       "Treatment Options:\n" +
                       "1. Gargle with salt water\n" +
                       "2. Drink warm liquids\n" +
                       "3. Use throat lozenges\n" +
                       "4. Humidifier use";
            default:
                return "No detailed information available for this condition.";
        }
    }
}