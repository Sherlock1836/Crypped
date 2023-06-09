import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class App {
	JFrame frame = new JFrame("Cryppedtograph");
	JMenuBar menBar = new JMenuBar();
	JMenu file = new JMenu("File");
	JMenuItem open = new JMenuItem("Open");
	JFileChooser fc = new JFileChooser();
	
	JPanel topPanel = new JPanel();
	JTextArea encryptedMessage = new JTextArea(5, 30);
	JScrollPane eMessScroll = new JScrollPane(encryptedMessage);
	
	JPanel midPanel = new JPanel();
	JButton decrypt = new JButton("Decrypt");
	JButton encrypt = new JButton("Encrypt");
	
	JPanel bottomPanel = new JPanel();
	JTextArea decryptedMessage = new JTextArea(5, 30);
	JScrollPane dMessScroll = new JScrollPane(decryptedMessage);
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		App A = new App();
	}
	public App() {
		//Set up menu bar for frame and add
        menBar.add(file);
		file.add(open);
		open.addActionListener(new ButtonListener());
		frame.setJMenuBar(menBar);
		
        //Set up top panel and add to frame
		topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.Y_AXIS));
		topPanel.add(new JLabel("Encrypted Message"));
        encryptedMessage.setLineWrap(true);
		topPanel.add(eMessScroll);
		eMessScroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		frame.add(topPanel);
		
        //Add Buttons and their listeners to midPanel and add midPanel to frame
		midPanel.setLayout(new FlowLayout());
        encrypt.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
		midPanel.add(encrypt);
		decrypt.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String eMessage = encryptedMessage.getText();
				System.out.println(eMessage);
			}
		});
		midPanel.add(decrypt);
		frame.add(midPanel);
		
        //Set up bottom panel and add to frame
		bottomPanel.setLayout(new BoxLayout(bottomPanel,BoxLayout.Y_AXIS));
		bottomPanel.add(new JLabel("Decrypted Message"));
		bottomPanel.add(dMessScroll);
        decryptedMessage.setLineWrap(true);
		dMessScroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
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
	class ButtonListener implements ActionListener {

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
				encryptedMessage.append(curLine);
				if(curLine.charAt(curLine.length()-1) == '\n');
					encryptedMessage.append(" ");
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
