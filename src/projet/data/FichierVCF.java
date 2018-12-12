package projet.data;

import java.io.Serializable;

/**
 * @author vcaze
 * @version 1.0
 */
public class FichierVCF implements Serializable {
	
	/**
	 * ID de serialisation
	 */
	private static final long serialVersionUID = -1572975497357412359L;
	//classe de stockage sur les informations des fichier .vcf
	
	/**
	 * Attributs
	 */
	private String version;
	private String name;
	private String email;
	private String org;
	private String tel;
	
	
	/**
	 * @param version
	 * @param name
	 * @param email
	 * @param org
	 * @param tel
	 */
	public FichierVCF(String version, String name, String email, String org, String tel) {
		this.version = version;
		this.name = name;
		this.email = email;
		this.org = org;
		this.tel = tel;
	}
	/** Getters/setters
	 * @return the version
	 */
	public String getVersion() {
		return version;
	}
	/**
	 * @param version the version to set
	 */
	public void setVersion(String version) {
		this.version = version;
	}
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}
	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	/**
	 * @return the org
	 */
	public String getOrg() {
		return org;
	}
	/**
	 * @param org the org to set
	 */
	public void setOrg(String org) {
		this.org = org;
	}
	/**
	 * @return the tel
	 */
	public String getTel() {
		return tel;
	}
	/**
	 * @param tel the tel to set
	 */
	public void setTel(String tel) {
		this.tel = tel;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		FichierVCF other = (FichierVCF) obj;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (org == null) {
			if (other.org != null)
				return false;
		} else if (!org.equals(other.org))
			return false;
		if (tel == null) {
			if (other.tel != null)
				return false;
		} else if (!tel.equals(other.tel))
			return false;
		if (version == null) {
			if (other.version != null)
				return false;
		} else if (!version.equals(other.version))
			return false;
		return true;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "BEGIN:VCARD\nVERSION:" + version + "\nN:" + name +"\nEMAIL;INTERNET:"
				+ email + "\nORG:" + org + "\nTEL;CELL:" + tel + "\nEND:VCARD";
	}	
	
}
