package versionPro;

import java.util.ArrayList;

public class Ventanilla {
	private double dinero;
	private boolean seAcaboElChollo;

	public Ventanilla() {
		super();
		this.dinero = 0;
		seAcaboElChollo = false;
	}

	public synchronized boolean isSeAcaboElChollo() {
		return seAcaboElChollo;
	}

	public void setSeAcaboElChollo(boolean seAcaboElChollo) {
		this.seAcaboElChollo = seAcaboElChollo;
	}

	public double getDinero() {
		double d = this.dinero;
		this.dinero = 0;
		return d;
	}

	public synchronized void setDinero(double dinero) {
		this.dinero = dinero;
		notify();
	}

}
