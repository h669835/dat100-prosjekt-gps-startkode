package no.hvl.dat100ptc.oppgave3;

import no.hvl.dat100ptc.oppgave1.GPSPoint;

public class GPSUtils {

	public static double findMax(double[] da) {

		double max;

		max = da[0];

		for (double d : da) {
			if (d > max) {
				max = d;
			}
		}

		return max;
	}

	public static double findMin(double[] da) {

		double min;

		// TODO - START

		min = da[0];

		for (double d : da) {
			if (d < min) {
				min = d;
			}
		}

		return min;

		// TODO - SLUT

	}

	public static double[] getLatitudes(GPSPoint[] gpspoints) {

		// TODO - START

		double[] latitudes = new double[gpspoints.length];
		for (int i = 0; i < latitudes.length; i++) {
			latitudes[i] = gpspoints[i].getLatitude();
		}

		return latitudes;
		// TODO - SLUTT
	}

	public static double[] getLongitudes(GPSPoint[] gpspoints) {

		// TODO - START

		double[] longitudes = new double[gpspoints.length];
		for (int i = 0; i < longitudes.length; i++) {
			longitudes[i] = gpspoints[i].getLongitude();
		}

		return longitudes;

		// TODO - SLUTT

	}

	private static int R = 6371000; // jordens radius

	public static double distance(GPSPoint gpspoint1, GPSPoint gpspoint2) {

		double d, latDelta, longDelta, a, c;
		double latitude1, longitude1, latitude2, longitude2;

		// TODO - START

		latitude1 = Math.toRadians(gpspoint1.getLatitude());
		longitude1 = Math.toRadians(gpspoint1.getLongitude());

		latitude2 = Math.toRadians(gpspoint2.getLatitude());
		longitude2 = Math.toRadians(gpspoint2.getLongitude());

		latDelta = latitude2 - latitude1;
		longDelta = longitude2 - longitude1;

		a = Math.pow(Math.sin(latDelta / 2), 2)
				+ Math.cos(latitude1) * Math.cos(latitude2) * Math.pow(Math.sin(longDelta / 2), 2);
		c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

		return R * c;

		// TODO - SLUTT

	}

	public static double speed(GPSPoint gpspoint1, GPSPoint gpspoint2) {

		int secs;
		double speed;

		// TODO - START

		secs = gpspoint2.getTime() - gpspoint1.getTime();
		speed = (distance(gpspoint1, gpspoint2) * 3.6) / secs;

		return speed;

		// TODO - SLUTT

	}

	public static String formatTime(int secs) {

		String timestr;
		String TIMESEP = ":";

		// TODO - START

		int hrs = secs / 3600;
		int min = (secs - hrs * 3600) / 60;
		int sec = (secs - hrs * 3600) - min * 60;

		timestr = String.format("%02d:%02d:%02d", hrs, min, sec);

		timestr = String.format("%10s", timestr);

		return timestr;
		// TODO - SLUTT

	}

	private static int TEXTWIDTH = 10;

	public static String formatDouble(double d) {

		String str;

		// TODO - START

		double rounded = Math.round(d * 100.0) / 100.0;
		str = String.valueOf(rounded);
		str = String.format("%10s", str);

		return str;
		// TODO - SLUTT

	}
}
