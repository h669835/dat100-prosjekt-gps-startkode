package no.hvl.dat100ptc.oppgave1;

public class GPSPoint {

	// TODO - objektvariable
	private int time;
	private double latitude;
	private double longitude;
	private double elevation;

	public GPSPoint(int time, double latitude, double longitude, double elevation) {

		// TODO - konstruktør

		this.time = time;
		this.latitude = latitude;
		this.longitude = longitude;
		this.elevation = elevation;

	}

	// TODO - get/set metoder
	public int getTime() {

		return this.time;

	}

	public void setTime(int time) {

		this.time = time;

	}

	public double getLatitude() {

		return this.latitude;

	}

	public void setLatitude(double latitude) {

		this.latitude = latitude;

	}

	public double getLongitude() {

		return this.longitude;

	}

	public void setLongitude(double longitude) {

		this.longitude = longitude;

	}

	public double getElevation() {

		return this.elevation;

	}

	public void setElevation(double elevation) {

		this.elevation = elevation;

	}

	public String toString() {

		String str;

		// TODO - start

		str = this.time + " (" + this.latitude + "," + this.longitude + ") " + this.elevation + "\n";

		return str;
		// TODO - slutt

	}
}
