package bracketplanner.domain;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.javadocmd.simplelatlng.LatLng;

public class Team extends LatLng {
	private static final long serialVersionUID = 1719245593230853130L;

	private int rank;
	private String name;
	private double rpi;
	private double sos;
	private String conference;

	public Team(double latitude, double longitude) {
		this(-1, StringUtils.EMPTY, StringUtils.EMPTY, -1, latitude, longitude);
	}

	public Team(int rank, String name, String conference, double rpi,
			double latitude, double longitude) {
		super(latitude, longitude);
		this.rank = rank;
		this.name = name;
		this.rpi = rpi;
		this.conference = conference;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getRpi() {
		return rpi;
	}

	public void setRpi(double rpi) {
		this.rpi = rpi;
	}

	public double getSos() {
		return sos;
	}

	public void setSos(double sos) {
		this.sos = sos;
	}

	public String getConference() {
		return conference;
	}

	public void setConference(String conference) {
		this.conference = conference;
	}

	public int getRank() {
		return rank;
	}

	public void setRank(int rank) {
		this.rank = rank;
	}

	public int getExpectedSeed() {
		int expectedSeed = ((rank - 1) / 4) + 1;
		return expectedSeed;
	}

	/*
	 * Object overrides
	 */

	// public boolean equals(Team o) {
	// return new EqualsBuilder().append(this.rank, o.rank).append(this.name,
	// o.name).append(this.rpi, o.rpi)
	// .append(this.sos, o.sos).append(this.conference,
	// o.conference).isEquals();
	// }
	//
	// public int hashCode() {
	// return new
	// HashCodeBuilder().append(rank).append(name).append(rpi).append(sos).append(conference).hashCode();
	// }

	public String toString() {
		return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
				.append(name).append(conference).append("Rank", rank).toString();
	}
}
