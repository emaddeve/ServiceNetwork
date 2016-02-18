package WebApp;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Hashtable;
import java.util.List;

import persons.Person;
import services.Service;

public class Algo {
	String d = " ";
	String a;

	/*
	 * public String getCyclic(Person person, Service service, Hashtable<Person,
	 * List> listServicesDemands, Hashtable<Person, List>
	 * listServicesOffers,Collection<Person> persons) { List<String>
	 * servicesOffersPersonUnique =(List<String>)listServicesOffers.get(person);
	 * for (Person p :persons){ if(p!=person){ List<String> servicesOffers
	 * =(List<String>)listServicesOffers.get(p);
	 * if(servicesOffers.contains(service.getName())){ d="fine:"; List<String>
	 * servicesDemands =(List<String>) listServicesDemands.get(p); for(String
	 * s:servicesDemands){ if(servicesOffersPersonUnique.contains(s)){ return
	 * found(p,s,service); } } }
	 * 
	 * 
	 * }
	 * 
	 * } return nothingFound();
	 * 
	 */
	public String getCyclic(Hashtable<Person, List> listServicesOffers, Service s, Collection<Person> persons,
			Person person) {
		for (Person p : persons) {
			if (!p.equals(person)) {
				List<Service> servicesOffers = (List<Service>) listServicesOffers.get(p);
				for (Service ss : servicesOffers) {
					if (ss.getName().equals(s.getName())) {

						d += s + " ";
					}

				}

			}
		}
		return d;

	}

	public String found() {
		return "the user ";
	}

	public String nothingFound() {
		return "sorry nothing found" + d;
	}
}
