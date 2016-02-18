package services;

import java.util.Date;

public class Service {

	/**
	 * A class for representing service, with a category and it's description.
	 */

	protected int id;

	protected int pid;

	/** service description */

	protected String des;

	/** service name */

	protected String name;

	/** type of the service (propose or demande) */

	protected int type;

	protected Date beginDate;
	protected Date endDate;

	public Service() {

	}

	/**
	 * Create a new service.
	 * 
	 * @param id
	 *            The service's id
	 * @param desc
	 *            The service's description
	 * @param name
	 *            the service's name
	 */
	public Service(int id, String name, String des, int type) {
		this.id = id;
		this.des = des;
		this.name = name;
		this.type = type;

	}

	public Service(String name, String des, int type, int pid, Date beginDate, Date endDate) {
		this.des = des;
		this.name = name;
		this.type = type;
		this.pid = pid;
		this.beginDate = beginDate;
		this.endDate = endDate;
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return The service's description.
	 */
	public String getDes() {
		return this.des;
	}

	public void setDes(String des) {
		this.des = des;
	}

	/**
	 * @return the service's reference
	 */
	public String getName() {

		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getType() {
		return this.type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public int getPid() {
		return this.pid;
	}

	public void setPid(int pid) {
		this.pid = pid;
	}

	public void setBeginDate(Date b) {
		this.beginDate = b;
	}

	public Date getBeginDate() {
		return this.beginDate;
	}

	public Date getEndDate() {
		return this.endDate;
	}

	public void setEndDate(Date e) {
		this.endDate = e;
	}
 
	/**
	 * @return A representation of this service as a string
	 */
	@Override
	public boolean equals(Object o) {
	    if(o == null) return false;
	    if( !(o instanceof Service) )
	    	return false;
	
	    Service other = (Service) o;
	    return name.equals(other.getName());
    }
	@Override
	public int hashCode() {
		return (int) 6 *name.hashCode();
	    
	}
	
	
	@Override
	public String toString() {
		return name;
	}

}
