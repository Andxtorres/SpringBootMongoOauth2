package application.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.mongodb.DBObject;

public class User implements UserDetails,Authentication{

	private static final long serialVersionUID = 1L;
	@Id
    public String id;	
	private String firstName;
	private String email;
	private String password;
	private String profilePic;
	private List<Keyword> keywords;
	private List<Role> roles = new ArrayList<Role>();
	
	public User(){
		
	}

    public User(DBObject dbObject) {
        this.id=(dbObject.get("_id")).toString();
        this.email = (String)dbObject.get("emailAddress");
        this.firstName = (String)dbObject.get("firstName");
        this.password = (String)dbObject.get("password");
        List<String> roles = (List<String>)dbObject.get("authorities");
        deSerializeRoles(roles);
    }

    private void deSerializeRoles(List<String> roles) {
    	if(roles!=null){
         for(String role : roles) {
             this.addRole(Role.valueOf(role));
         }
    	}
    }

	public User(String id, String firstName, String email, String password, String profilePic, List<Keyword> keywords) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.email = email;
		this.password = password;
		this.profilePic = profilePic;
		this.keywords = keywords;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getProfilePic() {
		return profilePic;
	}

	public void setProfilePic(String profilePic) {
		this.profilePic = profilePic;
	}
	public List<Keyword> getKeywords() {
		return keywords;
	}
	public void setKeywords(List<Keyword> keywords) {
		this.keywords = keywords;
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return firstName;
	}

	@Override
	public Object getCredentials() {
		// TODO Auto-generated method stub
		return String.valueOf(password);
	}

	@Override
	public Object getDetails() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object getPrincipal() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isAuthenticated() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void setAuthenticated(boolean arg0) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return firstName;
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}
	
    public void addRole(Role role) {
        this.roles.add(role);
    }
	
}
