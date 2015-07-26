package ar.uba.dc.formalex.grafoDeDependencia;

public class InfoComponenteBgt {
	
	private String name;
	private TipoDeComponenteBgt tipoDeComponente;
	
	public InfoComponenteBgt(String name, TipoDeComponenteBgt tipoDeComponente) {
		this.name = name;
		this.tipoDeComponente = tipoDeComponente;
	}

	@Override
	public String toString() {
		return String.format("'%s - %s'", name, tipoDeComponente); 
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime
				* result
				+ ((tipoDeComponente == null) ? 0 : tipoDeComponente.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		InfoComponenteBgt other = (InfoComponenteBgt) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (tipoDeComponente != other.tipoDeComponente)
			return false;
		return true;
	}

}
