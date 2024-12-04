package business.customer;

public class CustomerDTO {
	private long id;
    private String name;
    private String email;
    private String phone;
    private String dni;
    private boolean active;
    
	public CustomerDTO() {
		super();
	}
	

	public CustomerDTO(long id, String name, String email, String phone, String dni, boolean active) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.phone = phone;
		this.dni = dni;
		this.active = active;
	}



	public CustomerDTO(String name, String email, String phone, String dni, boolean active) {
		super();
		this.name = name;
		this.email = email;
		this.phone = phone;
		this.dni = dni;
		this.active = active;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getDni() {
		return dni;
	}
	public void setDni(String dni) {
		this.dni = dni;
	}
    
	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

}
