import javax.swing.*;
public class InfoFrame extends JFrame {
    public InfoFrame(String title, String content) {
        setTitle(title);
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        JTextArea textArea = new JTextArea(content);
        textArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(textArea);
        add(scrollPane);
        setLocationRelativeTo(null); 
        setVisible(true);
    }
}
