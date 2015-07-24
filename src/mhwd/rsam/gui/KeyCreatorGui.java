package mhwd.rsam.gui;

import java.awt.EventQueue;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;



import javax.swing.JLabel;
import javax.swing.JTextField;

import java.awt.FlowLayout;


import javax.swing.JComboBox;
import javax.swing.JButton;

import mhwd.rsam.keys.Key;
import mhwd.rsam.keys.KeyPairGenerator;



import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class KeyCreatorGui extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3303701340309265763L;
	private JPanel contentPane;
	private JTextField txtFirstName;
	private JTextField txtMiddleName;
	private JTextField txtLastName;
	private JTextField txtSeed;
	private JComboBox<Integer> comboBox;
	private JButton btnGenerateKey, btnDone;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					KeyCreatorGui frame = new KeyCreatorGui();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public KeyCreatorGui() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 600, 160);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JPanel namePanel = new JPanel();
		contentPane.add(namePanel);
		namePanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JLabel lblKeyOwner = new JLabel("Key Owner:");
		namePanel.add(lblKeyOwner);
		
		txtFirstName = new JTextField();
		txtFirstName.setText("First Name");
		namePanel.add(txtFirstName);
		txtFirstName.setColumns(15);
		
		txtMiddleName = new JTextField();
		txtMiddleName.setText("Middle Name");
		namePanel.add(txtMiddleName);
		txtMiddleName.setColumns(10);
		
		txtLastName = new JTextField();
		txtLastName.setText("Last Name");
		namePanel.add(txtLastName);
		txtLastName.setColumns(15);
		
		JPanel seedPanel = new JPanel();
		contentPane.add(seedPanel);
		seedPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JLabel lblSeed = new JLabel("Seed:");
		seedPanel.add(lblSeed);
		
		txtSeed = new JTextField();
		txtSeed.setText("Seed");
		seedPanel.add(txtSeed);
		txtSeed.setColumns(25);
		
		JPanel bitPanel = new JPanel();
		contentPane.add(bitPanel);
		bitPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JLabel lblBitLengthOf = new JLabel("Bit Length of Key:");
		bitPanel.add(lblBitLengthOf);
		
		comboBox = new JComboBox<Integer>();
		comboBox.addItem(null);
		for(int i=256; i<10000; i*=2)
		{
			comboBox.addItem(i);
		}
		
		bitPanel.add(comboBox);
		
		JPanel runPanel = new JPanel();
		contentPane.add(runPanel);
		
		btnGenerateKey = new JButton("Generate Key");
		btnGenerateKey.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if(comboBox.getSelectedItem()==null)
				{
					JOptionPane.showMessageDialog(null, "You must select a bit length for your key.");
					return;
				}
				String name=txtFirstName.getText()+" "+txtMiddleName.getText()+" "+txtLastName.getText();
				String seed=txtSeed.getText();
				int bitCount=Integer.parseInt(comboBox.getSelectedItem().toString());
				
				createKey(name, seed, bitCount);
			}
		});
		runPanel.add(btnGenerateKey);
		
		btnDone = new JButton("Done");
		btnDone.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent arg0){
				endGui();
			}
		});
		runPanel.add(btnDone);
	}

	protected void endGui() {
		this.dispose();
		
	}

	protected void createKey(String name, String seed, int bitCount) {
		KeyPairGenerator keygen=KeyPairGenerator.getInstance();
		Key key=keygen.generateKey(name, seed, bitCount/2);
		
		JFileChooser fileSave=new JFileChooser();
		String[] arr=new String[1];
		arr[0]="rsamkey";
		//FileNameExtensionFilter filt=new FileNameExtensionFilter(seed, null);
		fileSave.addChoosableFileFilter(new FileNameExtensionFilter("RSAM Keyfiles", arr));
		int returnVal=fileSave.showSaveDialog(null);
		if(returnVal==JFileChooser.APPROVE_OPTION)
		{
			File file=fileSave.getSelectedFile();
	
			String path=file.getPath();
			if(!path.endsWith(".rsamkey"))
				path=path+".rsamkey";
			
			try {
				ObjectOutputStream fout=new ObjectOutputStream(new FileOutputStream(path));
				fout.writeObject(key);
				fout.close();
				
			} catch (FileNotFoundException e) {
				JOptionPane.showMessageDialog(null,"File not Found");
			} catch (IOException e) {
				JOptionPane.showMessageDialog(null,"I/O Exception");
			}
			
		}
	}

}
