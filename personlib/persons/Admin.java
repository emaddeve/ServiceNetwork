package persons;


public class Admin {
	protected String password;
	protected String email;
	protected int id;
	protected Admin(){}
	public Admin(int id ,String email,String password){
		this.email=email;
		this.password=password;
		this.id=id;
	}
	public String getPassword(){
		return this.password;
	}
	public void setPassword(String pass){
		this.password=pass;
	}
	public String getEmail(){
		return this.email;	
	}
	public void setEmail(String email){
		this.email=email;
	}
	public void setId(int id){
		this.id=id;
	}
	public int getId(){
		return this.id;
	}
}
