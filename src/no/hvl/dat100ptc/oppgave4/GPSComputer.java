package no.hvl.dat100ptc.oppgave4;

import no.hvl.dat100ptc.oppgave1.GPSPoint;
import no.hvl.dat100ptc.oppgave2.GPSData;
import no.hvl.dat100ptc.oppgave2.GPSDataFileReader;
import no.hvl.dat100ptc.oppgave3.GPSUtils;

public class GPSComputer {

	private GPSPoint[] gpspoints;

	public GPSComputer(String filename) {

		GPSData gpsdata = GPSDataFileReader.readGPSFile(filename);
		gpspoints = gpsdata.getGPSPoints();

	}

	public GPSComputer(GPSPoint[] gpspoints) {
		this.gpspoints = gpspoints;
	}

	public GPSPoint[] getGPSPoints() {
		return this.gpspoints;
	}

	// beregn total distances (i meter)
	public double totalDistance() {

		double distance = 0;

		// TODO - START

		for (int i = 0; i < gpspoints.length - 1; i++) {
			distance += GPSUtils.distance(gpspoints[i], gpspoints[i + 1]);
		}

		return distance;
		// TODO - SLUTT

	}

	// beregn totale høydemeter (i meter)
	public double totalElevation() {

		double elevation = 0;

		// TODO - START

		for (int i = 0; i < gpspoints.length - 1; i++) {
			double tmp = (gpspoints[i + 1].getElevation() - gpspoints[i].getElevation());
			elevation += (tmp > 0) ? tmp : 0;
		}
		return elevation;

		// TODO - SLUTT

	}

	// beregn total tiden for hele turen (i sekunder)
	public int totalTime() {

		return gpspoints[gpspoints.length - 1].getTime() - gpspoints[0].getTime();

	}

	// beregn gjennomsnitshastighets mellom hver av gps punktene

	public double[] speeds() {

		// TODO - START // OPPGAVE - START

		double[] speeds = new double[gpspoints.length - 1];

		// TODO - START

		for (int i = 0; i < gpspoints.length - 1; i++) {
			speeds[i] = GPSUtils.speed(gpspoints[i], gpspoints[i + 1]);
		}
		return speeds;

		// TODO - SLUTT

	}

	public double maxSpeed() {

		// TODO - START

		return GPSUtils.findMax(speeds());

		// TODO - SLUTT

	}

	public double averageSpeed() {

		// TODO - START

		return totalDistance() * 3.6 / totalTime();

		// TODO - SLUTT

	}

	/*
	 * bicycling, <10 mph, leisure, to work or for pleasure 4.0 bicycling, general
	 * 8.0 bicycling, 10-11.9 mph, leisure, slow, light effort 6.0 bicycling,
	 * 12-13.9 mph, leisure, moderate effort 8.0 bicycling, 14-15.9 mph, racing or
	 * leisure, fast, vigorous effort 10.0 bicycling, 16-19 mph, racing/not drafting
	 * or >19 mph drafting, very fast, racing general 12.0 bicycling, >20 mph,
	 * racing, not drafting 16.0
	 */

	// conversion factor m/s to miles per hour
	public static double MS = 2.236936;

	// beregn kcal gitt weight og tid der kjøres med en gitt hastighet
	public double kcal(double weight, int secs, double speed) {

		double kcal;

		// MET: Metabolic equivalent of task angir (kcal x kg-1 x h-1)
		double[][] mets = { { 0, 4.0 }, { 10, 6.0 }, { 12, 8.0 }, { 14, 10.0 }, { 16, 12.0 }, { 20, 16.0 },
				{ Double.MAX_VALUE } };
		double met = 0;
		double speedmph = speed * MS;

		// TODO - START

		for (int i = 0; i < mets.length - 1; i++) {
			if (speedmph > mets[i][0] && speedmph < mets[i + 1][0]) {
				met = mets[i][1];
			}
		}
		double res = met * weight * ((double) secs / 3600);
		// System.out.println("Result: " + res + " Weight: " + weight + " Secs: " + secs
		// + " Speed: " + speed + " MET: " + met);
		return res;

		// TODO - SLUTT

	}

	public double totalKcal(double weight) {

		double totalkcal = 0;

		// TODO - START

		for (int i = 0; i < gpspoints.length - 1; i++) {
			double speed = GPSUtils.speed(gpspoints[i], gpspoints[i + 1]);
			int secs = gpspoints[i + 1].getTime() - gpspoints[i].getTime();
			totalkcal += kcal(weight, secs, speed);
		}

		return totalkcal;

		// TODO - SLUTT

	}

	private static double WEIGHT = 80.0;

	public void displayStatistics() {

		System.out.println("==============================================");

		// TODO - START

		System.out.printf("%-22s: %-10s%n", "Total time", GPSUtils.formatTime(totalTime()));
		System.out.printf("%-22s: %-3.2f %-5s %n", "Total distance", totalDistance() / 1000, "km");
		System.out.printf("%-22s: %-3.2f %-5s %n", "Total elecation", totalElevation(), "m");
		System.out.printf("%-22s: %-3.2f %-5s %n", "Max speed", maxSpeed(), "km/t");
		System.out.printf("%-22s: %-3.2f %-5s %n", "Average speed", averageSpeed(), "km/t");
		System.out.printf("%-22s: %-3.2f %-5s %n", "Energy", totalKcal(WEIGHT), "kcal");

		System.out.println("==============================================");

		// TODO - SLUTT

	}

}
