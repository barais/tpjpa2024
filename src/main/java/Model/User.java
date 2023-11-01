package Model;

import jakarta.persistence.*;

@Entity
@Table(name="user")
public class User {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;

	@Column(name="name")
	private String name;

	@Column(name="password")
	private String password;

	public User() {
	}

	public User(Long id, String name, String password) {
		this.id = id;
		this.name = name;
		this.password = password;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() { 
		return name;
	}

	public void setName(String name) { 
		this.name = name;
	}

	public String getPassword() { 
		return password;
	}

	public void setPassword(String password) { 
		this.password = password;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", password=" + password + "]";
	}
}
