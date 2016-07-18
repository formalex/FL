package ar.uba.dc.formalex.fl.bgtheory;

public class Role {
	private String name;
	private RoleSpecification roleSpec = new RoleSpecification();

    public Role(String name) {
        this.name = name;
    }

    public Role(String name, Integer depth) {
        this.name = name;
        this.roleSpec.setDepth(depth);
    }

    public String getName() {
		return this.name;
	}


	public String toString() {
		return name;
	}

    public void setName(String name) {
        this.name = name;
    }           


	public RoleSpecification getRoleSpecification() {
		return roleSpec;
	}


	public void setRoleSpecification(RoleSpecification roleSpec) {
		this.roleSpec = roleSpec;
	}


	@Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Role role = (Role) o;

        if (name != null ? !name.equals(role.name) : role.name != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return name != null ? name.hashCode() : 0;
    }
}