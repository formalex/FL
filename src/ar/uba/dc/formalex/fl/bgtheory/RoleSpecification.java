package ar.uba.dc.formalex.fl.bgtheory;

import java.util.HashSet;
import java.util.Set;


public class RoleSpecification {
	private Set<Role> roles = new HashSet<Role>();
	private Boolean disjoint = false;
	private Boolean cover = false;
	private RolesCombination rolesCombination = new RolesCombination();
	private Boolean isCombined = false;
	
	public RoleSpecification(Set<Role> roles) {
		this.roles = roles;
	}

    public RoleSpecification() {
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

	public RolesCombination getRolesCombination() {
		return rolesCombination;
	}

	public void setRolesCombination(RolesCombination rolesCombination) {
		this.rolesCombination = rolesCombination;
	}

	public Boolean isCombined() {
		return isCombined;
	}

	public void setCombined(Boolean isCombined) {
		this.isCombined = isCombined;
	}		
}