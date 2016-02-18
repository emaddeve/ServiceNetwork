package persons;

/**
 * A class for representing persons, with a name, a first name, and an email
 * address.
 * 
 * @author Charlotte Lecluze and Bruno Zanuttini, Universit&eacute; de Caen
 *         Basse-Normandie, France
 * @since January, 2013
 */

public class Person {
	/** the person's ID. */

	protected int id;
	/** The person's name. */

	protected String name;

	/** The person's first name. */

	protected String firstName;

	/** The person's email address. */

	protected String email;

	protected String password;

	protected int role;

	/**
	 * Builds a new person.
	 * 
	 * @param name
	 *            The person's name
	 * @param firstName
	 *            The person's first name
	 * @param email
	 *            The person's email address
	 */
	public Person() {
	}

	public Person(String name, String firstName, String email, String password) {
		this.name = name;
		this.firstName = firstName;
		this.email = email;
		this.password = password;
	}

	public Person(int id, String name, String firstName, String email) {
		this.name = name;
		this.id = id;
		this.firstName = firstName;
		this.email = email;
	}

	/**
	 * Returns the person's ID.
	 * 
	 * @return The person's ID
	 */
	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	/**
	 * Returns the person's name.
	 * 
	 * @return The person's name
	 */
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Returns the person's first name.
	 * 
	 * @return The person's first name.
	 */
	public String getFirstName() {
		return this.firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * Returns the person's email address.
	 * 
	 * @return The person's email address
	 */
	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPassword() {
		return this.password;
	}

	public void setRole(int role) {
		this.role = role;
	}

	public int getRole() {
		return this.role;
	}

	@Override
	public boolean equals(Object o) {
		if (o == null)
			return false;
		if (!(o instanceof Person))
			return false;

		Person other = (Person) o;
		if (other.getId() == this.getId())
			return true;
		else
			return false;

	}

	@Override
	public int hashCode() {
		int result = 0;
		result = (int) (id / 11);
		return result;
	}

	/**
	 * Returns a representation of this person as a string.
	 * 
	 * @return A representation of this person as a string
	 */
	public String toString() {
		return this.name;
	}

}
