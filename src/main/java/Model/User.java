package Model;

import jakarta.persistence.*;

@Entity
@Table(name="user")
public class User {

	private Long id;

	private String name;

	private String password;

	public User() {
	}

	public User(String name, String password) {
		this.name = name;
		this.password = password;
	}

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name="name")
	public String getName() { 
		return name;
	}

	public void setName(String name) { 
		this.name = name;
	}

	@Column(name="password")
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
