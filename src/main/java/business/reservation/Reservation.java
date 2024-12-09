package business.reservation;

import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.Set;

import business.customer.Customer;
import business.reservationline.ReservationLine;
import common.converter.ZonedDateTimeConverter;
import common.utils.ZonedDateUtils;
import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Version;

@Entity
@NamedQueries({
	@NamedQuery(name = "business.reservation.Reservation.getAll",
				query = "SELECT r FROM Reservation r")

})
public class Reservation implements Serializable {
	private static final long serialVersionUID = 1553853896651568034L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@Column(nullable = false)
	@Convert(converter = ZonedDateTimeConverter.class)
	private ZonedDateTime date;
	private double total;
	private boolean active;
	@ManyToOne(optional = false)
	private Customer customer;
	
	@OneToMany(mappedBy = "reservation")
	private Set<ReservationLine> reservationLine;
	
	@Version
	private int version;
	
	public Reservation() {}
	
	public Reservation(ZonedDateTime date, double total, boolean active, Customer c) {
		super();
		this.date = date;
		this.total = total;
		this.active = active;
		this.customer = c;
	}
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public ZonedDateTime getDate() {
		return date;
	}

	public void setDate(ZonedDateTime date) {
		this.date = date;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Set<ReservationLine> getReservationLine() {
		return reservationLine;
	}

	public void setReservationLine(Set<ReservationLine> reservationLine) {
		this.reservationLine = reservationLine;
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

	public ReservationDTO toDto() {
		ReservationDTO reservation = new ReservationDTO();
		reservation.setId(this.id);
		reservation.setDate(ZonedDateUtils.parseString(this.date));
		reservation.setTotal(this.total);
		reservation.setIdCustomer(this.customer.getId());
		reservation.setActive(this.active);
		return reservation;
	}
}
