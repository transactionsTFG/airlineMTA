package business.customer;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

import java.io.Serializable;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

import java.util.Set;

import business.reservation.Reservation;
import javax.persistence.OneToMany;
import javax.persistence.Version;

@Entity
@NamedQueries({
	@NamedQuery(name = "business.customer.Customer.getAll",
				query = "SELECT c FROM Customer c"),
	@NamedQuery(name = "business.customer.Customer.getCustomerByDni",
				query = "SELECT c FROM Customer c WHERE c.dni = :dni"),

})
public class Customer implements Serializable {

	private static final long serialVersionUID = 7595677220393329226L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@Column(nullable = false)
	private String name;
	@Column(nullable = false)
	private String email;
	@Column(nullable = false)
	private String phone;
	@Column(nullable = false, unique = true)
	private String dni;
	private boolean active;
	@OneToMany(mappedBy = "customer")
	private Set<Reservation> reservation;
	@Version
	private int version;
	public Customer() {}
	
	public Customer(String name, String email, String phone, String dni, boolean active) {
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
	public Set<Reservation> getReservation() {
		return reservation;
	}
	public void setReservation(Set<Reservation> reservation) {
		this.reservation = reservation;
	}
	public int getVersion() {
		return version;
	}
	public void setVersion(int version) {
		this.version = version;
	}
	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}
	
	public CustomerDTO toDto() {
		return new CustomerDTO(id, name, email, phone, dni, active);
	}
	
}