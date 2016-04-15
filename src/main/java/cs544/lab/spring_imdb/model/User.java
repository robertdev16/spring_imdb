package cs544.lab.spring_imdb.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class User {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int userId;
	
	@Column(name="username", unique = true)
	private String loginName;
	private String password;
	
	@Enumerated(EnumType.STRING)
	@ElementCollection
	private Set<UserRole> roleSet = new HashSet<UserRole>();
	
	@OneToMany(mappedBy="user", cascade=CascadeType.ALL, orphanRemoval = true)
	private List<Comment> commentList = new ArrayList<Comment>();
	
	public User() {
		super();
	}

	public User(String loginName, String password) {
		super();
		this.loginName = loginName;
		this.password = password;
	}


	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getUserId() {
		return userId;
	}

	public Set<UserRole> getRoleSet() {
		return roleSet;
	}

	public void addComment(Comment c){
		commentList.add(c);
	}

	public void removeComment(Comment c){
		commentList.remove(c);
	}

	@Override
	public String toString() {
		String str = "\n\nuserId: " + userId + ",   loginName: " + loginName + 
					"   ==================================================================" +
					"==================================================================\n";
		StringBuffer sb = new StringBuffer(str);
		if (commentList.size() > 0)
			sb.append("Comment:\n");
		commentList.forEach(sb::append);
		return sb.toString();
	}
	
}
