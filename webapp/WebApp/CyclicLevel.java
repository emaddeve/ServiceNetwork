package WebApp;

import persons.Person;
import services.Service;
/**
 * A class for representing Cyclic Level, with a person, Service Objects, and an level .
 * @author emad
 */
public class CyclicLevel {
	//the person object
	private Person person;
	//the service object
	private Service service;
	//the level of the cyclic
	private int level;
	/**
     * Builds a new CyclicLevel.
     * @param person The person Object
     * @param service The Service Object
     * @param level The level of the Cyclic 
     */
	public CyclicLevel(Person person,Service service,  int level) {
		this.person = person;
		this.service = service;
		this.level = level;
	}
//getter and setter
	public Service getService() {
		return service;
	}

	public Person getPerson(){
		return this.person;
	}
	
	public int getLevel(){
		return this.level;
	}
	 /**
     * Returns a representation of this CyclicLevel as a string.
     * @return A representation of this CyclicLevel as a string
     */
	public String toString() {
		return "{"+person+","+service+", "+level+" }";
	}

	
}
