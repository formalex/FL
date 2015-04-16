package ar.uba.dc.formalex.fl.bgtheory;

public class Role {
	private String name;
	private RoleSpecification subroles = new RoleSpecification();

    public Role(String name) {
        this.name = name;
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


	public RoleSpecification getSubroles() {
		return subroles;
	}


	public void setSubroles(RoleSpecification subroles) {
		this.subroles = subroles;
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