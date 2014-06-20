package ar.uba.dc.formalex.fl.bgtheory;

import java.util.HashSet;
import java.util.Set;


public class Roles {
	private Set<Role> roles = new HashSet<Role>();
	private Boolean disjoint = false;
	private Boolean cover = false;
	
	
	public Roles(Set<Role> roles) {
		this.roles = roles;
	}

    public Roles() {
    }


    public Boolean isDisjoint() {
		return disjoint;
	}

	public void setDisjoint(Boolean disjoint) {
		this.disjoint = disjoint;
	}

	public Boolean isCover() {
		return cover;
	}

	public void setCover(Boolean cover) {
		this.cover = cover;
	}

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}
	
	public void addRole(Role aRole){
		roles.add(aRole);
	}

	public Role getByName(String name){
        for (Role role : roles) {
            if(role.getName().equals(name))
                return role;
        }

		return null;
	}
}