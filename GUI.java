package v2;

import java.awt.Container;
import java.awt.HeadlessException;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import javax.swing.ImageIcon;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
/**
 * 
 * @author Fernando
 * UI for PS
 *
 */
public class GUI extends JFrame{
	
	private JFrame frame;
	private JPanel MainPanel, ButtonPanel;
	private Container Container;
	
	private JTextField username = new JTextField();
	private JPasswordField password = new JPasswordField();
	private JButton login = new JButton("Login");
	private JButton signup = new JButton("Sign Up");
	
	private JButton newPProfile = new JButton("New Password Profile");
	private JButton generatePassword =  new JButton("Generate Max Strength Random Password (plus copy to clipboard)");
	
	
	private JScrollPane scrollpane;
	
	public static Profile profile;
	/**
	 * Calls and completes the initialization process for the Frame, and packs/makes the frame visible
	 * @param Game
	 * @throws IOException 
	 */
	public GUI() throws IOException {
		
		ImageIcon icon = new ImageIcon(System.getProperty("user.dir")+"\\ps.png");
		
		createLoginPanels();
		
		frame.setIconImage(icon.getImage());
		
		frameFinalization();
	}
	
	/**
	 * finalizes the frame by packing, setting visible and starting the action listeners
	 */
	private void frameFinalization() {
		frame.setSize(360, 155);
		setDefaults();
		frame.setVisible(true);
		initiateActionListeners();
	}
	
	/**
	 * starts the action listeners
	 */
	private void initiateActionListeners() {
		
		login.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent e) {
				
				try {
					profile = new Profile(username.getText(), new Password(password.getText()), new Database());
					if(PPwizard.returningProfile(profile) == true) {
						System.out.println("Profile Found!");
						if(profile.data.size() == 0) {
							newUser();
						}else {
							frame.setVisible(false);
							createLoggedInContainer();
						}
					}else {
						System.out.println("Profile not found!");
					}
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		signup.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent e) {
				System.out.println(username.getText() + " | " + password.getText());
				try {
					profile = new Profile(username.getText(), new Password(password.getText()), new Database());
					if(PPwizard.returningProfile(new Profile(username.getText(), new Password(password.getText()), new Database())) == false) {
						System.out.println("Profile not found, creating new profile!");
						PPwizard.saveProfile(profile);
						newUser();
					}else {
						JOptionPane.showMessageDialog(null, "Profile already created! Please choose different credentials!");
						username.setText("");
						password.setText("");
					}
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		newPProfile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {newPProfile();} catch (Exception e1) {e1.printStackTrace();}
			}
		});
		generatePassword.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String password = "";
				try {password = PasswordFeatures.generateStrongPassword();} catch (Exception e1) {e1.printStackTrace();}
				PPwizard.copytoClipboard(password);
				JOptionPane.showMessageDialog(null, password + " is your generated password! It has been copied to your clipboard!");
			}
		});
	}
	
	/**
	 * After Logging in this container will recreate the frame and make the logged in page
	 */
	private void createLoggedInContainer() {
		MainPanel.removeAll();
		ButtonPanel.removeAll();
		DefaultListModel<PProfile> listmodel = new DefaultListModel<>();
		for(int i =0; i < profile.data.size(); i++) {
			listmodel.add(i, profile.data.get(i));
		}
		@SuppressWarnings({ "unchecked", "rawtypes" })
		JList list = new JList(listmodel);
		list.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) { // to avoid handling multiple events
                    
                    PProfile selectedItem = (PProfile)list.getSelectedValue();
                    try {
                    	PPwizard.copytoClipboard(Encrypter.decrypt(selectedItem.password, selectedItem.key));
						JOptionPane.showMessageDialog(null, "You selected " + selectedItem.name_url + " the password is : " + Encrypter.decrypt(selectedItem.password, selectedItem.key) + "\nPassword is copied to your Clipboard!");
					} catch (HeadlessException e1) {
						e1.printStackTrace();
					} catch (Exception e1) {
						e1.printStackTrace();
					}
                }
            }
        });
		scrollpane = new JScrollPane(list);
		MainPanel.add(scrollpane);
		ButtonPanel.add(newPProfile);
		ButtonPanel.add(generatePassword);
		frame.pack();
		frame.setVisible(true);
	}
	
	private void createContainer() {
		Container = getContentPane();
		Container.setLayout(new BoxLayout(Container, BoxLayout.Y_AXIS));
		Container.add(MainPanel);
		Container.add(new JSeparator());
		Container.add(ButtonPanel);
		frame.pack();
		frame.setResizable(false);
		frame.add(Container);
	}
	
	/**
	 * Sets the default close operation, location on startup and the Icon for the frame
	 */
	private void setDefaults() {
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	private void newUser() throws Exception {
		frame.setVisible(false);
		
		String response1;
		String response2;
		String response3;
		
		while (true) {
			response1 = JOptionPane.showInputDialog("Welcome new Profile! Please enter the first Password Profile for your Database!\nStarting with the name of the website or entity in which the password and profile are stored.");
			response2 = JOptionPane.showInputDialog("Please enter the username for " + response1);
			response3 = JOptionPane.showInputDialog("Please enter password for " + response1);
			if(response1 == null || response2 == null || response3 == null) {
				JOptionPane.showMessageDialog(null, "Invalid Inputs Please re-enter valid inputs");
			}else {
				break;
			}
		}
		
		PPwizard.newPProfile(profile, response2, response1, response3);
		PPwizard.saveProfile(profile);
		createLoggedInContainer();
	}
	
	private void newPProfile() throws Exception {
		
		String response1;
		String response2;
		String response3;
		
		while (true) {
			response1 = JOptionPane.showInputDialog("Please enter name/url/game/software for the password profile.");
			response2 = JOptionPane.showInputDialog("Please enter the username for " + response1);
			response3 = JOptionPane.showInputDialog("Please enter password for " + response1);
			if (response1 == null || response2 == null || response3 == null) {
				JOptionPane.showMessageDialog(null, "Invalid Inputs Please re-enter valid inputs");
			}else {
				break;
			}
		}
		PPwizard.newPProfile(profile, response2, response1, response3);
		PPwizard.saveProfile(profile);
		createLoggedInContainer();
	}
	/**
	 * Helper method called in the constructor to remove code build up from the constructor
	 * creates and populates the panels (MainPanel and ButtonPanel) in the UI
	 */
	private void createLoginPanels() {
		frame = new JFrame("Password Saver");
		MainPanel = new JPanel();
		MainPanel.setLayout(new BoxLayout(MainPanel, BoxLayout.PAGE_AXIS));
		MainPanel.add(new JLabel("Welcome to PS developed by Fernando Gomez!"));
		MainPanel.add(new JSeparator());
		MainPanel.add(new JLabel("Username:"));
		MainPanel.add(username);
		MainPanel.add(new JLabel("Password:"));
		MainPanel.add(password);
		username.setColumns(10);
		password.setColumns(10);
		ButtonPanel = new JPanel();
		ButtonPanel.setLayout(new BoxLayout(ButtonPanel, BoxLayout.LINE_AXIS));
		ButtonPanel.add(login);
		ButtonPanel.add(signup);
		createContainer();
	}

}