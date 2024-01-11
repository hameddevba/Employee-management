package Frame;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.JTable;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import Entite.Employe;
import bdconnection.DBconnect;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.RowFilter;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;

import javax.swing.SwingConstants;
import javax.swing.DropMode;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

public class Display extends JFrame {
	
	
	private JTable table;
	private JTextField inputRecher;
	
	DBconnect db;
	
	String selectedColumn = "ID";
	
	public Display(DBconnect theDb) {
		
		db = theDb;
		
		this.setLocationByPlatform(true);
		/* this.setLocationRelativeTo(null); */ 
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setTitle("JTable");
		this.setSize(1029, 610);
		getContentPane().setLayout(null);

		getContentPane().setLayout(null);
		
		table = new JTable();
		
		refreshTable();
		
		table.setBounds(58, 64, 734, 202);
		JScrollPane scrollPane = new JScrollPane(table);
		table.setFillsViewportHeight(true);
		scrollPane.setBounds(10, 70, 970, 417);
		getContentPane().add(scrollPane);
		
		/*
		 * try { List<Employe> employes = db.getAllEmployes();
		 * 
		 * String[] columnNames = {"ID", "Nom et Prenom", "Email", "Adresse",
		 * "Telephone"}; Object[][] data = new
		 * Object[employes.size()][columnNames.length];
		 * 
		 * for (int i = 0; i < employes.size(); i++) { Employe employe =
		 * employes.get(i); data[i][0] = employe.getNumero(); data[i][1] =
		 * employe.getNom_prenom(); data[i][2] = employe.getEmail(); data[i][3] =
		 * employe.getAdress(); data[i][4] = employe.getTel(); }
		 * 
		 * DefaultTableModel model = new DefaultTableModel(data, columnNames);
		 * table.setModel(model); } catch (SQLException e) {
		 * JOptionPane.showMessageDialog(this, "Error retrieving employees: " +
		 * e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE); }
		 */
		
		
		JPanel panel = new JPanel();
		panel.setBounds(106, 11, 807, 32);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel recherche = new JLabel("Recherche ");
		recherche.setHorizontalAlignment(SwingConstants.CENTER);
		recherche.setFont(new Font("Tahoma", Font.BOLD, 14));
		recherche.setBounds(42, 0, 126, 32);
		panel.add(recherche);
		
		
		
		

		JComboBox<String> filtreCombo = new JComboBox<>();
		filtreCombo.setFont(new Font("Segoe UI", Font.BOLD, 14));
		filtreCombo.setBounds(633, 0, 117, 26);
		panel.add(filtreCombo);

		// Get the table model
		TableModel model = table.getModel();

		// Add column names to the combobox
		for (int i = 0; i < model.getColumnCount(); i++) {
		    String columnName = model.getColumnName(i);
		    filtreCombo.addItem(columnName);
		}

		// Add action listener to the combobox
		filtreCombo.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		         selectedColumn = (String) filtreCombo.getSelectedItem();
		    }
		});
		
		
		
		
		inputRecher = new JTextField();
		inputRecher.setBounds(195, 6, 406, 20);
		
		 inputRecher.addActionListener(new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent e) {
	                String searchText = inputRecher.getText();
	                if (searchText.trim().isEmpty()) {
	                	
							refreshTable();
	            
						
	                } else {
	                    filterTableManually(searchText); // Apply custom filtering
	                }
	            }

	            private void filterTableManually(String searchText) {
	                TableModel model = table.getModel();
	                DefaultTableModel filteredModel = new DefaultTableModel();

	                // Copy column names from original model
	                String[] columnNames = new String[model.getColumnCount()];
	                for (int i = 0; i < columnNames.length; i++) {
	                    columnNames[i] = model.getColumnName(i);
	                }
	                filteredModel.setColumnIdentifiers(columnNames);

	                // Filter rows based on the "Nom" column and add to filtered model
	                int nomColumnIndex = table.getColumnModel().getColumnIndex(selectedColumn); // Get index of the "Nom" column
	                for (int i = 0; i < model.getRowCount(); i++) {
	                    String nom = model.getValueAt(i, nomColumnIndex).toString();
	                    if (nom.toLowerCase().contains(searchText.toLowerCase())) {
	                        Object[] rowData = new Object[model.getColumnCount()];
	                        for (int j = 0; j < model.getColumnCount(); j++) {
	                            rowData[j] = model.getValueAt(i, j);
	                        }
	                        filteredModel.addRow(rowData);
	                    }
	                }

	                table.setModel(filteredModel);
	            }


				
	        });
		 
		panel.add(inputRecher);
		inputRecher.setColumns(10);
		
		
		


		
		
		
		
		
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(170, 519, 637, 32);
		getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		
		
		JButton edit = new JButton("Editer");
		edit.setFont(new Font("Tahoma", Font.BOLD, 12));
		edit.setBounds(255, 5, 112, 23);
		
		panel_1.add(edit);
		
		
		
		
		JButton supprime = new JButton("Supprimer");
		supprime.setFont(new Font("Tahoma", Font.BOLD, 12));
		supprime.setBounds(496, 5, 118, 23);
		
		panel_1.add(supprime);
	
		supprime.addActionListener(new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent e) {
	                int selectedRow = table.getSelectedRow();
	                if (selectedRow != -1) {
	                    int numero = (int) table.getValueAt(selectedRow, 0); // Get employee ID from first column
	                    db.deleteEmploye(numero);
						DefaultTableModel model = (DefaultTableModel) table.getModel();
						model.removeRow(selectedRow);
						JOptionPane.showMessageDialog(null, "Employe supprimé avec succès.");
	                } else {
	                    JOptionPane.showMessageDialog(null, "Veuillez sélectionner une ligne à supprimer.", "Information", JOptionPane.INFORMATION_MESSAGE);
	                }
	            }
	        });
		
		
	
		
		
		
		JButton inscrire = new JButton("Inscrire");
		
		inscrire.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new MainFrame().setVisible(true);
				dispose();
			}
		});
		
		inscrire.setFont(new Font("Tahoma", Font.BOLD, 12));
		inscrire.setBounds(27, 5, 112, 23);
		
		panel_1.add(inscrire);
		
	}
	
	
	
	
	 private void refreshTable() {
		 try {
		        List<Employe> employes = db.getAllEmployes();

		        String[] columnNames = {"ID", "Nom et Prenom", "Email", "Adresse", "Telephone"};
		        Object[][] data = new Object[employes.size()][columnNames.length];
		        
		        for (int i = 0; i < employes.size(); i++) {
		            Employe employe = employes.get(i);
		            data[i][0] = employe.getNumero();
		            data[i][1] = employe.getNom_prenom();
		            data[i][2] = employe.getEmail();
		            data[i][3] = employe.getAdress();
		            data[i][4] = employe.getTel();
		        }

		        DefaultTableModel model = new DefaultTableModel(data, columnNames);
		        table.setModel(model);
		    } catch (SQLException e) {
		        JOptionPane.showMessageDialog(this, "Error retrieving employees: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
		    }
			
	    }	
	 
	 
	 
}
