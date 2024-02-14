package Frame;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.Color;
import javax.swing.JTextField;
import java.awt.GridLayout;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.border.CompoundBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import Entite.Employe;
import bdconnection.DBconnect;

public class MainFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField nom;
	private JTextField emailt;
	private JTextField adresset;
	private JTextField datet;
	private JTextField numerot;

	Display display;
	DBconnect connect;
	
	
	public MainFrame() {
		
		
		connect = new DBconnect();
		
		
		
		this.setLocationByPlatform(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1045, 627);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		JPanel panel = new JPanel();
		panel.setBackground(SystemColor.textHighlight);
		panel.setToolTipText("Gestion Service");
		panel.setBounds(280, 11, 425, 46);
		contentPane.add(panel);
		panel.setLayout(null);
		
		
		
		JLabel lblNewLabel = new JLabel("Gestion Service ");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(141, 11, 169, 24);
		panel.add(lblNewLabel);
		
		
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new CompoundBorder(new LineBorder(new Color(0, 0, 0), 2, true), new LineBorder(new Color(0, 0, 0), 2, true)));
		panel_1.setBounds(99, 97, 853, 461);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		
		
		JLabel lblNewLabel_1 = new JLabel("Inscription Employe");
		lblNewLabel_1.setBackground(Color.WHITE);
		lblNewLabel_1.setForeground(Color.RED);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(296, 11, 266, 34);
		panel_1.add(lblNewLabel_1);
		
		
		
		JPanel panel_nom = new JPanel();
		panel_nom.setBounds(120, 59, 600, 34);
		panel_1.add(panel_nom);
		panel_nom.setLayout(new GridLayout(0, 2, 0, 0));
		
		
		
		JLabel nom_pr = new JLabel("nom et prenom");
		nom_pr.setBackground(SystemColor.desktop);
		nom_pr.setFont(new Font("Tahoma", Font.BOLD, 11));
		panel_nom.add(nom_pr);
		nom_pr.setHorizontalAlignment(SwingConstants.CENTER);
		
		
		
		nom = new JTextField();
		nom.setToolTipText("");
		panel_nom.add(nom);
		nom.setColumns(10);
		
		
		
		JPanel panel_email = new JPanel();
		panel_email.setBounds(120, 124, 600, 34);
		panel_1.add(panel_email);
		panel_email.setLayout(new GridLayout(0, 2, 0, 0));
		
		
		
		JLabel email = new JLabel("E-mail ");
		email.setFont(new Font("Tahoma", Font.BOLD, 11));
		email.setHorizontalAlignment(SwingConstants.CENTER);
		panel_email.add(email);
		
		
		
		emailt = new JTextField();
		emailt.setColumns(10);
		panel_email.add(emailt);
		
		
		
		JPanel panel_adress = new JPanel();
		panel_adress.setBounds(120, 191, 600, 34);
		panel_1.add(panel_adress);
		panel_adress.setLayout(new GridLayout(0, 2, 0, 0));
		
		
		
		JLabel adress = new JLabel("Adresse ");
		adress.setFont(new Font("Tahoma", Font.BOLD, 11));
		adress.setHorizontalAlignment(SwingConstants.CENTER);
		panel_adress.add(adress);
		
		
		
		adresset = new JTextField();
		adresset.setColumns(10);
		panel_adress.add(adresset);
		
		
		
		JPanel panel_date = new JPanel();
		panel_date.setBounds(120, 269, 600, 34);
		panel_1.add(panel_date);
		panel_date.setLayout(new GridLayout(0, 2, 0, 0));
		
		
		
		JLabel date = new JLabel("Date de recrutement");
		date.setFont(new Font("Tahoma", Font.BOLD, 11));
		date.setHorizontalAlignment(SwingConstants.CENTER);
		panel_date.add(date);
		
		
		
		datet = new JTextField();
		datet.setColumns(10);
		panel_date.add(datet);
		
		
		
		JPanel panel_tel = new JPanel();
		panel_tel.setBounds(120, 331, 600, 34);
		panel_1.add(panel_tel);
		panel_tel.setLayout(new GridLayout(0, 2, 0, 0));
		
		
		
		JLabel numero = new JLabel("Numero Tel ");
		numero.setFont(new Font("Tahoma", Font.BOLD, 11));
		numero.setHorizontalAlignment(SwingConstants.CENTER);
		panel_tel.add(numero);
		
		
		
		numerot = new JTextField();
		numerot.setColumns(10);
		panel_tel.add(numerot);
		
		
		
		//L'acction sur le buuton
		
		JButton btnNewButton = new JButton("Inscrire");
		
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				
				  String nomPrenom = nom.getText();
			      String email = emailt.getText();
			      String adress = adresset.getText();
			      String dateRecrutement = datet.getText();
			      int tel = Integer.parseInt(numerot.getText());

			      Employe employe = new Employe(0, nomPrenom, adress, email, dateRecrutement, tel);
			      connect.createEmploye(employe);
			      
			      JOptionPane.showMessageDialog(null, " Nom : " + nom.getText()+ 
			        		"\nEmail : "+ emailt.getText()+
			        		"\naddresse : "+adresset.getText()+
			        		"\nNumero Telephone : " + numerot.getText(),"",
			        		JOptionPane.INFORMATION_MESSAGE);

			        // Clear the fields after successful insertion
			        nom.setText("");
			        emailt.setText("");
			        adresset.setText("");
			        datet.setText("");
			        numerot.setText("");
			        
				/*
				 * int option = JOptionPane.showConfirmDialog(null,
				 * "Voulez-vous continuer l'enregistrement","", JOptionPane.YES_NO_OPTION,
				 * JOptionPane.QUESTION_MESSAGE);
				 * 
				 * 
				 * if(option != JOptionPane.OK_OPTION){ dispose(); }
				 */
			        
			        

			}
		});
		
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnNewButton.setBounds(204, 387, 127, 37);
		panel_1.add(btnNewButton);
		
		
		
		
		
		JButton List = new JButton("Employes");
		List.setFont(new Font("Tahoma", Font.BOLD, 12));
		List.setBounds(530, 389, 138, 37);
		panel_1.add(List);
		
		List.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				display = new Display(connect); 
				display.setVisible(true);
				
				//Fermuture de la fenetre MainFrame
				dispose();
			}
		});
		
	}
}
