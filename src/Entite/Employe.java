package Entite;

public class Employe {
	
	private int numero;
	private String Nom_prenom;
	private String adress;
	private String email;
	private String date_recrutement;
	private int tel;

	public Employe(int numero, String nom_prenom, String adress, String email, String date_recrutement, int tel) {
		super();
		this.numero = numero;
		Nom_prenom = nom_prenom;
		this.adress = adress;
		this.email = email;
		this.date_recrutement = date_recrutement;
		this.tel = tel;
	}
	
	
	
	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	public String getNom_prenom() {
		return Nom_prenom;
	}

	public void setNom_prenom(String nom_prenom) {
		Nom_prenom = nom_prenom;
	}

	public String getAdress() {
		return adress;
	}

	public void setAdress(String adress) {
		this.adress = adress;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getDate_recrutement() {
		return date_recrutement;
	}

	public void setDate_recrutement(String date_recrutement) {
		this.date_recrutement = date_recrutement;
	}

	public int getTel() {
		return tel;
	}

	public void setTel(int tel) {
		this.tel = tel;
	}


}
