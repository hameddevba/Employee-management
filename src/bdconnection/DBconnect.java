package bdconnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import Entite.Employe;

public class DBconnect {
	
	static Connection con ;
	
	public  Connection getConnection() {
		try {
			String mysqlJDBCDriver = "com.mysql.cj.jdbc.Driver";
			String url = "jdbc:mysql://localhost:3306/gestion";
			String user = "itkann";
			String pass = "itkann";
			
			Class.forName(mysqlJDBCDriver);
			
			con = DriverManager.getConnection(url,user, pass);
			
		}catch(Exception e) {
			System.out .println("Connection Erreur "+ e.getMessage());
		}
		
		return con;
	}
	
	
	public void createEmploye(Employe employe) {
	    Connection con =getConnection();
	    PreparedStatement ps = null;
	    try {
	        String query = "INSERT INTO employes (numero, nom_prenom, adress, email, date_recrutement, tel) VALUES (?, ?, ?, ?, ?, ?)";
	        ps = con.prepareStatement(query);
	        ps.setInt(1, employe.getNumero());
	        ps.setString(2, employe.getNom_prenom());
	        ps.setString(3, employe.getAdress());
	        ps.setString(4, employe.getEmail());
	        ps.setString(5, employe.getDate_recrutement());
	        ps.setInt(6, employe.getTel());
	        ps.executeUpdate();
	        System.out.println("Employee created successfully.");
	    } catch (SQLException e) {
	        System.out.println("Error creating employee: " + e.getMessage());
	    } finally {
	        try {
	            if (ps != null) {
	                ps.close();
	            }
	            if (con != null) {
	                con.close();
	            }
	        } catch (SQLException e) {
	            System.out.println("Error closing resources: " + e.getMessage());
	        }
	    }
	}
	
	

    public List<Employe> getAllEmployes() throws SQLException {
        List<Employe> employes = new ArrayList<>();
        Connection con = getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            String query = "SELECT * FROM employes";
            ps = con.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                Employe employe = new Employe(
                    rs.getInt("numero"),
                    rs.getString("nom_prenom"),
                    rs.getString("adress"),
                    rs.getString("email"),
                    rs.getString("date_recrutement"),
                    rs.getInt("tel")
                );
                employes.add(employe);
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ps != null) {
                ps.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return employes;
    }
    
    
	
	public Employe readEmploye(int numero) {
	    Employe employe = null;
	    Connection con = getConnection();
	    PreparedStatement ps = null;
	    ResultSet rs = null;
	    try {
	        String query = "SELECT * FROM employes WHERE numero = ?";
	        ps = con.prepareStatement(query);
	        ps.setInt(1, numero);
	        rs = ps.executeQuery();
	        if (rs.next()) {
	            employe = new Employe(
	                rs.getInt("numero"),
	                rs.getString("nom_prenom"),
	                rs.getString("adress"),
	                rs.getString("email"),
	                rs.getString("date_recrutement"),
	                rs.getInt("tel")
	            );
	        } else {
	            System.out.println("Employee not found.");
	        }
	    } catch (SQLException e) {
	        System.out.println("Error retrieving employee: " + e.getMessage());
	    } finally {
	        try {
	            if (rs != null) {
	                rs.close();
	            }
	            if (ps != null) {
	                ps.close();
	            }
	            if (con != null) {
	                con.close();
	            }
	        } catch (SQLException e) {
	            System.out.println("Error closing resources: " + e.getMessage());
	        }
	    }
	    return employe;
	}
	
	public void updateEmploye(Employe employe) {
	    try (Connection con = getConnection();
	         PreparedStatement ps = con.prepareStatement("UPDATE employes SET nom_prenom = ?, adress = ?, email = ?, date_recrutement = ?, tel = ? WHERE numero = ?")) {

	        ps.setString(1, employe.getNom_prenom());
	        ps.setString(2, employe.getAdress());
	        ps.setString(3, employe.getEmail());
	        ps.setString(4, employe.getDate_recrutement());
	        ps.setInt(5, employe.getTel());
	        ps.setInt(6, employe.getNumero());

	        int rowsUpdated = ps.executeUpdate();
	        if (rowsUpdated > 0) {
	            System.out.println("Employee updated successfully.");
	        } else {
	            System.out.println("Employee not found for update.");
	        }

	    } catch (SQLException e) {
	        System.out.println("Error updating employee: " + e.getMessage());
	    }
	}

	
	  public void deleteEmploye(int numero) {
	        try (Connection con = getConnection();
	             PreparedStatement ps = con.prepareStatement("DELETE FROM employes WHERE numero = ?")) {

	            ps.setInt(1, numero);

	            int rowsDeleted = ps.executeUpdate();
	            if (rowsDeleted > 0) {
	                System.out.println("Employee deleted successfully.");
	            } else {
	                System.out.println("Employee not found for deletion.");
	            }

	        } catch (SQLException e) {
	            System.out.println("Error deleting employee: " + e.getMessage());
	        }
	    }

}
