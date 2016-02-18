package WebApp;

import java.util.*;
import persons.Person;
import services.Service;
/**
 * An class hold the Algorithm responsible of computing the shortest cycle 
 * to have a specific service.
 * @author emad
 *
 */
public class Algorithm {
	//represent the level of the cycle 
	int level = 0;
	//represent a unique cycle
	private LinkedHashMap<Person, List> closed;
	//Deque hold the persons Objects.
	private Deque open;
	//LinkedList of type CyclicLevel which contain (person,service,int).
	LinkedList<CyclicLevel> cyclicListLevel;
	//constructor to initialize all the variables above.
	public Algorithm() {
		this.closed = new LinkedHashMap<Person, List>();
		this.open = new LinkedList<Person>();
		this.cyclicListLevel = new LinkedList<CyclicLevel>();
	}
/**
 * getCyclic method is for computing the shortest cycle possible to have a specific service 
 * from the demand list
 * @param person person who demand the cycle
 * @param service the unique service
 * @param listServicesDemands list of services demanded associated with a user
 * @param listServicesOffers list of services offered associated with a user
 * @return return a String representing a USER who can give to another (USER) a specific (SERVICE) with the level
 * number 
 */
	public String getCyclic(Person person, Service service, Hashtable<Person, List> listServicesDemands,
			Hashtable<Person, List> listServicesOffers) {
		//add the person unique to the open.
		this.open.add(person);
		//add an empty cycle 
		cyclicListLevel.add(new CyclicLevel(null, service, level));
		this.closed.put(person, cyclicListLevel);
		//create a list of services corresponding to the person unique
		List<Service> firstPersonservice = new ArrayList<Service>();
		//add the service unique to the above list
		firstPersonservice.add(service);
		listServicesDemands.put(person, firstPersonservice);
		//chick if open is not empty then pop up the first elements and make a loop.
		while (open.size()>0) {
		
			Person p = (Person) open.pop();

			List<Service> servicesDemandes = listServicesDemands.get(p);
			//loop over the list of services demand by the user p.
			for (Service s : servicesDemandes) {
				int levelClosedPerson = level;
				//an iterator on all the keyset of the listserviceOffers which are of the type Person
				Iterator iterator = listServicesOffers.keySet().iterator();
				while (iterator.hasNext()) {
					Person nextPerson = (Person) iterator.next();
					//if the nextPersons not equal the person we retrieved from the open.
					if (!nextPerson.equals(p)) {
						//get his list of services offers.
						List<Service> servicesOffers = (List<Service>) listServicesOffers.get(nextPerson);
						//loop over this liset of services offers 
						for (Service ss : servicesOffers) {
							/*
							 * if one of the services demands by the user p (retrieved from the open)
							 * exist on the list of service offers by another user except the unique user
							 * 
							 */
							if (ss.getName().equals(s.getName()))
								if (nextPerson.equals(person))
									return cycle(closed, person, service);
							//else closed is empty the add the nextPerson to open to have another loop
							//and set a new cyclicleve
								else if (closed.get(nextPerson) == null) {
									open.add(nextPerson);
									LinkedList<CyclicLevel> cyclicLevel2 = new LinkedList<CyclicLevel>();
									cyclicLevel2.add(new CyclicLevel(p, s, level + 1));
									closed.put(nextPerson, cyclicLevel2);
									levelClosedPerson = level + 1;
								} else if (levelClosedPerson == level + 1) {
									List<CyclicLevel> cyclicLevel2 = closed.get(nextPerson);
									cyclicLevel2.add(new CyclicLevel(p, s, level + 1));
								}
						}
					}
				}
			}

			level++;
		}
		return nothingFound();
	}
	/**
	 * Method to  represent the cycle
	 * @param closed2 linkedhashmap contain the person and it's cycle
	 * @param person the person unique
	 * @param service hte service unique
	 * @return return a String representing the cycle
	 */
	public String cycle(LinkedHashMap<Person, List> closed2, Person person, Service service) {
		String cycle = "";

		Set<Person> keys = closed2.keySet();

		for (Person p : keys) {

			cycle += "<br>"+p.getName()+"<br>";
			List<CyclicLevel> cl = closed.get(p);
			for (CyclicLevel t : cl) {
				cycle += t;
			}

		}
		return cycle;
	}
	/**
	 * Method in case of nothing found.
	 * @return some message.
	 */
	public String nothingFound() {
	
		return "sorry nothing found ";
	}

}
