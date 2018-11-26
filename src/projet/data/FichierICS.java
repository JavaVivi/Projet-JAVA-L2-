/**
 * 
 */


package data;

import java.io.Serializable;


/**
 * 
 * @author David Szathmary
 *
 */
public class FichierICS implements Serializable {

	private static final long serialVersionUID = -1572975497357412359L;
	
	/**
	 * Attributs
	 */
	
	private String version;
	private String prodid;
	private String UID;
	private String dtStamp;
	private String dtStart;
	private String dtEnd;
	private String summ;
	private String loc;
	private String desc;
	/**
	 * @param version
	 * @param PRODID
	 * @param UID
	 * @param DTSTAMP
	 * @param DTSTART
	 * @param DTEND
	 * @param sommaire
	 * @param lieu
	 * @param description
	 */
	
	public FichierICS (String version, String prodid, String Uid, String dtStamp, String dtStart, String dtEnd, String summ, String loc, String desc) {
		this.version = version;
		this.prodid = prodid;
		this.UID = Uid;
		this.dtStamp = dtStamp;
		this.dtStart = dtStart;
		this.dtEnd = dtEnd;
		this.summ = summ;
		this.loc = loc;
		this.desc = desc;
	}
	
	
	/**
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
	 * @return the prodid
	 */
	public String getProdid() {
		return prodid;
	}
	/**
	 * @param prodid the prodid to set
	 */
	public void setProdid(String prodid) {
		this.prodid = prodid;
	}
	/**
	 * @return the uID
	 */
	public String getUID() {
		return UID;
	}
	/**
	 * @param uID the uID to set
	 */
	public void setUID(String uID) {
		UID = uID;
	}
	/**
	 * @return the dtStamp
	 */
	public String getDtStamp() {
		return dtStamp;
	}
	/**
	 * @param dtStamp the dtStamp to set
	 */
	public void setDtStamp(String dtStamp) {
		this.dtStamp = dtStamp;
	}
	/**
	 * @return the dtStart
	 */
	public String getDtStart() {
		return dtStart;
	}
	/**
	 * @param dtStart the dtStart to set
	 */
	public void setDtStart(String dtStart) {
		this.dtStart = dtStart;
	}
	/**
	 * @return the dtEnd
	 */
	public String getDtEnd() {
		return dtEnd;
	}
	/**
	 * @param dtEnd the dtEnd to set
	 */
	public void setDtEnd(String dtEnd) {
		this.dtEnd = dtEnd;
	}
	/**
	 * @return the summary
	 */
	public String getSummary() {
		return summ;
	}
	/**
	 * @param summary the summary to set
	 */
	public void setSummary(String summary) {
		this.summ = summary;
	}
	/**
	 * @return the location
	 */
	public String getLocation() {
		return loc;
	}
	/**
	 * @param location the location to set
	 */
	public void setLocation(String location) {
		this.loc = location;
	}
	/**
	 * @return the desc
	 */
	public String getDesc() {
		return desc;
	}
	/**
	 * @param desc the desc to set
	 */
	public void setDesc(String desc) {
		this.desc = desc;
	}



	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "BEGIN:VCALENDAR\nVERSION:"+version+"\nPRODID:"+prodid+"\nBEGIN:VEVENT\nUID:"+UID+"\nDTSTAMP:"+dtStamp+"\nDTSTART:"+dtStart+
				"\nDTEND:"+dtEnd+"\nSUMMARY:"+summ+"\nLOCATION:"+loc+"\nDESCRIPTION:"+desc+"\nEND:VEVENT\nEND:VCALENDAR";
	}


	
	
}
