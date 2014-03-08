package bracketplanner.domain;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import bracketplanner.util.BracketPlannerUtil;

import com.javadocmd.simplelatlng.LatLng;

public class Team extends LatLng {
	private static final long serialVersionUID = 1719245593230853130L;

	private int rank;
	private String name;
	private String abbreviation;
	private String conference;

	public Team(double latitude, double longitude) {
		this(-1, StringUtils.EMPTY, StringUtils.EMPTY, StringUtils.EMPTY, latitude, longitude);
	}

	public Team(int rank, String name, String abbreviation, String conference, double latitude, double longitude) {
		super(latitude, longitude);
		this.rank = rank;
		this.name = name;
		this.abbreviation = abbreviation;
		this.conference = conference;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAbbreviation() {
		return abbreviation;
	}

	public void setAbbreviation(String abbreviation) {
		this.abbreviation = abbreviation;
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
		int expectedSeed = ((rank - 1) / BracketPlannerUtil.NUM_REGIONS) + 1;
		return expectedSeed;
	}

	/*
	 * Object overrides
	 */

	public boolean equals(Team o) {
		return new EqualsBuilder().append(this.name, o.name).isEquals();
	}

	public int hashCode() {
		return new HashCodeBuilder().append(rank).append(name).append(conference).hashCode();
	}

	public String toString() {
		return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE).append(name).append(conference).append("Rank", rank)
		        .toString();
	}
}
