import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class App {
	JFrame frame = new JFrame("Crypped");
	JMenuBar menBar = new JMenuBar();
	JMenu file = new JMenu("File");
	JMenuItem open = new JMenuItem("Open");
	JFileChooser fc = new JFileChooser();
	
	JPanel topPanel = new JPanel();
	JTextArea messageTxt = new JTextArea(5, 30);
	JScrollPane messScroll = new JScrollPane(messageTxt);
	
	JPanel midPanel = new JPanel();
	JButton decrypt = new JButton("Decrypt");
	JButton encrypt = new JButton("Encrypt");
	
	JPanel bottomPanel = new JPanel();
	JTextArea resultTxt = new JTextArea(5, 30);
	JScrollPane resultScroll = new JScrollPane(resultTxt);

	Message message = new Message();
	Cipher cipher = new Cipher();
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		App a = new App();
	}
	public App() {

		//Set up menu bar for frame and add
        menBar.add(file);
		file.add(open);
		open.addActionListener(new FileMenButtonListener());
		frame.setJMenuBar(menBar);
		
        //Set up top panel and add to frame
		topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.Y_AXIS));
		topPanel.add(new JLabel("Message"));
        messageTxt.setLineWrap(true);
		topPanel.add(messScroll);
		messScroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		frame.add(topPanel);
		
        //Add Buttons and their listeners to midPanel and add midPanel to frame
		midPanel.setLayout(new FlowLayout());
        EncryptionButtonListener ebl = new EncryptionButtonListener();
		encrypt.addActionListener(ebl);
		midPanel.add(encrypt);
		decrypt.addActionListener(ebl);
		midPanel.add(decrypt);
		frame.add(midPanel);
		
        //Set up bottom panel and add to frame
		bottomPanel.setLayout(new BoxLayout(bottomPanel,BoxLayout.Y_AXIS));
		bottomPanel.add(new JLabel("Result"));
		bottomPanel.add(resultScroll);
        resultTxt.setLineWrap(true);
		resultScroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		frame.add(bottomPanel);
		
        //Set up frame
		JFrame.setDefaultLookAndFeelDecorated(true);
		frame.setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.Y_AXIS));
		frame.pack();
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
		//frame.setResizable(false);
		frame.setVisible(true);
	}
	class EncryptionButtonListener implements ActionListener{
		@Override
            public void actionPerformed(ActionEvent e) {
				resultTxt.setText(null);
				if(e.getSource() == encrypt){
					message.setDecryptedMessage(messageTxt.getText());
					resultTxt.setText(cipher.encryptMessage(message));
				}
				if(e.getSource() == decrypt){
					message.setDecryptedMessage(messageTxt.getText());
					resultTxt.setText(cipher.decryptMessage(message));
				}
            }
	}
	class FileMenButtonListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			if(e.getSource() == open) {
				int returnVal = fc.showOpenDialog(menBar);
				if (returnVal == JFileChooser.APPROVE_OPTION) {
					File file = fc.getSelectedFile();
					loadFile(file);
				}
			}
		}
	}
	public void loadFile(File file) {
		BufferedReader fileReader = null;
		try { 
			String curLine;
			fileReader = new BufferedReader(new FileReader(file));
			while((curLine = fileReader.readLine()) != null) {
				messageTxt.append(curLine);
				if(curLine.charAt(curLine.length()-1) == '\n');
					messageTxt.append(" ");
			}
		} catch(IOException ex){
			ex.printStackTrace();
		} finally {
			try {
				if(fileReader != null)
					fileReader.close();
			} catch(IOException f) {
				f.printStackTrace();
			}
		}
	}
}
