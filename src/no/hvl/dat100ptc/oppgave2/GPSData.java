package no.hvl.dat100ptc.oppgave2;

import no.hvl.dat100ptc.oppgave1.GPSPoint;

public class GPSData {

	private GPSPoint[] gpspoints;
	protected int antall = 0;

	public GPSData(int n) {

		// TODO - START

		this.gpspoints = new GPSPoint[n];
		this.antall = 0;

		// TODO - SLUTT
	}

	public GPSPoint[] getGPSPoints() {
		return this.gpspoints;
	}

	protected boolean insertGPS(GPSPoint gpspoint) {

		// TODO - START

		if (this.antall < this.gpspoints.length) {
			this.gpspoints[this.antall] = gpspoint;
			this.antall++;
			return true;
		}
		return false;

		// TODO - SLUTT
	}

	public boolean insert(String time, String latitude, String longitude, String elevation) {

		GPSPoint gpspoint;

		// TODO - START

		gpspoint = GPSDataConverter.convert(time, latitude, longitude, elevation);
		if (insertGPS(gpspoint)) {
			return true;
		}
		return false;

		// TODO - SLUTT

	}

	public void print() {

		System.out.println("====== Konvertert GPS Data - START ======");

		// TODO - START

		for (GPSPoint gpspoint : this.gpspoints) {
			System.out.print(gpspoint.toString());
		}

		// TODO - SLUTT

		System.out.println("====== Konvertert GPS Data - SLUTT ======");

	}
}
