package bracketplanner.domain;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.javadocmd.simplelatlng.LatLng;

public class Site extends LatLng {

	private static final long serialVersionUID = -8873006564037487281L;

	private String name;
	private int round;
	private String hostTeamName;

	public Site(double latitude, double longitude) {
		this(StringUtils.EMPTY, 0, StringUtils.EMPTY, latitude, longitude);
	}

	public Site(String name, int round, String hostTeamName, double latitude,
			double longitude) {
		super(latitude, longitude);
		this.name = name;
		this.round = round;
		this.hostTeamName = hostTeamName;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getRound() {
		return round;
	}

	public void setRound(int round) {
		this.round = round;
	}

	public String getHostTeamName() {
		return hostTeamName;
	}

	public void setHostTeamName(String hostTeamName) {
		this.hostTeamName = hostTeamName;
	}

	/*
	 * Object overrides
	 */

	// public boolean equals(Site o) {
	// return new EqualsBuilder().append(this.name, o.name).append(this.round,
	// o.round).isEquals();
	// }
	//
	// public int hashCode() {
	// return new HashCodeBuilder().append(name).append(round).hashCode();
	// }

	public String toString() {
		return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
				.append(name).toString();
	}
}
