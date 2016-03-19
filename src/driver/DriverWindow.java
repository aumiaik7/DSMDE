/**@file DriverWindow.java
 * @author Ahamad Imtiaz Khan
 * @brief This the driver class
 * Provides UI for user to choose file from dialog 
 * @version 0.1
 */

package driver;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;

import scanner.ScanMe;

import java.awt.GridBagLayout;
import javax.swing.JTextField;
import java.awt.GridBagConstraints;
import javax.swing.Box;
import java.awt.Insets;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.awt.event.ActionEvent;
import symbolTable.*;

public class DriverWindow extends JFrame {

	///sourceFile object is the reader of source file
	public FileReader sourceFile = null;
		
	private JPanel contentPane;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DriverWindow frame = new DriverWindow();
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
	public DriverWindow() {
		//file chooser 
		final JFileChooser chooser = new JFileChooser();
		//FileNameExtensionFilter filter = new FileNameExtensionFilter(".txt", "gif");
		//chooser.setFileFilter(filter);
		
		
		setLocationRelativeTo(null);	
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		textField = new JTextField();
		textField.setBounds(12, 85, 189, 19);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JLabel lblFileLocation = new JLabel("File Location");
		lblFileLocation.setBounds(12, 64, 98, 15);
		contentPane.add(lblFileLocation);
		
		JButton btnBrowseFile = new JButton("Browse File");
		btnBrowseFile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int returnVal = chooser.showOpenDialog(null);
				if(returnVal == JFileChooser.APPROVE_OPTION) {
					textField.setText(chooser.getSelectedFile().getAbsolutePath());
					openFileStream(chooser.getSelectedFile().getAbsolutePath());
				}
			}

			
		});
		btnBrowseFile.setBounds(12, 117, 117, 25);
		contentPane.add(btnBrowseFile);
		JButton btnStart = new JButton("Start");
		btnStart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SymbolTable st = new SymbolTable();
				ScanMe scan = new ScanMe(sourceFile,st);
				Administration admin = new Administration(scan);
				admin.start();
				//scan.read();
			}
		});
		btnStart.setBounds(12, 149, 117, 25);
		contentPane.add(btnStart);
		
		

	
	}
	/**
	 * @param absolutePath
	 * opens file using file reader and the absolute path of the chosen file
	 */
	private void openFileStream(String absolutePath) {
		// TODO Auto-generated method stub
		try {
			sourceFile = new FileReader(new File(absolutePath));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
