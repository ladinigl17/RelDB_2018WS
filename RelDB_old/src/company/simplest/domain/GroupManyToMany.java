package company.simplest.domain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;

@Entity
@Table(name = "group", schema="ue6_manytomany")
public class GroupManyToMany {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int id;
	String gname;

	public Collection<PersonManyToMany> getPersons() {
		return persons;
	}

	@ManyToMany(mappedBy = "groups")
	private Collection<PersonManyToMany> persons = new ArrayList<PersonManyToMany>();

	public void addPerson(PersonManyToMany person) {
		persons.add(person);
	}



	protected GroupManyToMany(){}

	public GroupManyToMany
	(int id, String gname)
	{
		this.id = id;
		this.gname = gname;

	}

}
