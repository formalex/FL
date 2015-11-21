package ar.uba.dc.formalex.ReductorDeRepresentacionDeAccion;

import ar.uba.dc.formalex.fl.bgtheory.BackgroundTheory;

public class ReductorDeRepresentacionDeAccionADosEstadosFake implements
		ReductorDeRepresentacionDeAccionADosEstados {

	private BackgroundTheory bgt;
	
	public ReductorDeRepresentacionDeAccionADosEstadosFake(
			BackgroundTheory bgt) {
		this.bgt = bgt;

	}
	@Override
	public BackgroundTheory ejecutar() {

		return this.bgt;
	}

}
