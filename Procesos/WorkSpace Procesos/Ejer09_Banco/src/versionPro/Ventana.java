package versionPro;

import java.util.ArrayList;
import java.util.Random;

public class Ventana {
	private double dinero;

	public Ventana(double dinero) {
		super();
		this.dinero = dinero;
	}
	

	public Ventana() {
		super();
	}


	public synchronized double getDinero() {
		return dinero;
	}

	public synchronized void setDInero(double dinero) {
		this.dinero = dinero;
	}

}
