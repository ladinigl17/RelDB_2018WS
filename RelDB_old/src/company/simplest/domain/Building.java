package company.simplest.domain;

import javax.persistence.*;

@Entity
@Table(schema = "ue_4_onetoone")
public class Building {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int id;
	String btype;
	int fk_address;

	@OneToOne
    private Address address;

    public void setAddress (Address adress) {
        this.address = address;
    }
    public Address getAddress() {
        return address;
    }

	protected Building(){}

	public Building
	(int id, String btype, int fk_address)
	{
		this.id = id;
		this.btype = btype;
		this.fk_address = fk_address;

	}

}
