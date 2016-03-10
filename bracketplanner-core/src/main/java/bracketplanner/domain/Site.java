package bracketplanner.domain;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.javadocmd.simplelatlng.LatLng;

/**
 * @author babram
 * 
 */
public class Site extends LatLng {

	private static final long serialVersionUID = -8873006564037487281L;

	private String name;
	private String location;
	private int venueId;
	private int round;
	private String hostTeamName;

	public Site(double latitude, double longitude) {
		this(StringUtils.EMPTY, StringUtils.EMPTY, 0, StringUtils.EMPTY, -1, latitude, longitude);
	}

	public Site(String name, String location, int round, String hostTeamName, int venueId, double latitude, double longitude) {
		super(latitude, longitude);
		this.name = name;
		this.location = location;
		this.round = round;
		this.hostTeamName = hostTeamName;
		this.venueId = venueId;
	}

	/**
	 * Returns the name of the Site
	 * 
	 * @return
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets the name of the Site
	 * 
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Returns the location of the Site (City,State)
	 * 
	 * @return
	 */
	public String getLocation() {
		return location;
	}

	/**
	 * Sets the location of the Site
	 * 
	 * @param location
	 */
	public void setLocation(String location) {
		this.location = location;
	}

	public int getVenueId() {
		return venueId;
	}

	public void setVenueId(int venueId) {
		this.venueId = venueId;
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

	public boolean equals(Site o) {
		return new EqualsBuilder().append(getName(), o.getName()).isEquals();
	}

	public int hashCode() {
		return new HashCodeBuilder().append(getName()).append(getLocation()).append(getRound()).hashCode();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.javadocmd.simplelatlng.LatLng#toString()
	 */
	public String toString() {
		return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE).append(getName()).append(getLocation()).toString();
	}
}
