package no.hvl.dat100ptc.oppgave5;

import javax.swing.JOptionPane;

import easygraphics.EasyGraphics;
import no.hvl.dat100ptc.oppgave1.GPSPoint;
import no.hvl.dat100ptc.oppgave3.GPSUtils;
import no.hvl.dat100ptc.oppgave4.GPSComputer;

public class ShowRoute extends EasyGraphics {

	private static int MARGIN = 50;
	private static int MAPXSIZE = 800;
	private static int MAPYSIZE = 800;

	private GPSPoint[] gpspoints;
	private GPSComputer gpscomputer;

	public ShowRoute() {

		String filename = JOptionPane.showInputDialog("GPS data filnavn: ");
		gpscomputer = new GPSComputer(filename);

		gpspoints = gpscomputer.getGPSPoints();

	}

	public static void main(String[] args) {
		launch(args);
	}

	public void run() {

		makeWindow("Route", MAPXSIZE + 2 * MARGIN, MAPYSIZE + 2 * MARGIN);

		showRouteMap(MARGIN + MAPYSIZE);

		showStatistics();
	}

	// antall x-pixels per lengdegrad
	public double xstep() {

		double[] longitudes = GPSUtils.getLongitudes(gpspoints);
		double maxlon = GPSUtils.findMax(longitudes);
		double minlon = GPSUtils.findMin(longitudes);
		double xstep = MAPXSIZE / (Math.abs(maxlon - minlon));

		return xstep;
	}

	// antall y-pixels per breddegrad
	public double ystep() {

		double ystep;

		// TODO - START

		double[] latitudes = GPSUtils.getLatitudes(gpspoints);
		double maxlat = GPSUtils.findMax(latitudes);
		double minlat = GPSUtils.findMin(latitudes);
		ystep = MAPYSIZE / (Math.abs(maxlat - minlat));

		return ystep;

		// TODO - SLUTT

	}

	public void showRouteMap(int ybase) {

		// TODO - START

		setColor(0, 255, 0);
		double minlat = GPSUtils.findMin(GPSUtils.getLatitudes(gpspoints));
		double minlon = GPSUtils.findMin(GPSUtils.getLongitudes(gpspoints));
		int r = 5;

		for (int i = 0; i < gpspoints.length - 1; i++) {
			int x1 = (int) (xstep() * (gpspoints[i].getLongitude() - minlon));
			int y1 = (int) (ystep() * (gpspoints[i].getLatitude() - minlat));
			int x2 = (int) (xstep() * (gpspoints[i + 1].getLongitude() - minlon));
			int y2 = (int) (ystep() * (gpspoints[i + 1].getLatitude() - minlat));
			drawLine(MARGIN + x1, ybase - y1, MARGIN + x2, ybase - y2);
			fillCircle(MARGIN + x1, ybase - y1, r);
		}

		// TODO - SLUTT
	}

	public void showStatistics() {

		int TEXTDISTANCE = 20;

		setColor(0, 0, 0);
		setFont("Courier", 18);

		// TODO - START

		String[] stats = gpscomputer.getStatistics();
		for (int i = 0; i < stats.length; i++) {
			drawString(stats[i], 10, 20 + TEXTDISTANCE * i);
		}

		// TODO - END
	}
}
