package bracketplanner.domain;

import java.io.Serializable;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

public class Region implements Serializable {
	private static final long serialVersionUID = 2249958895196016624L;

	private String name;

	public Region(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	/*
	 * Object overrides
	 */

	// public boolean equals(Region o) {
	// return new EqualsBuilder().append(this.name, o.name).isEquals();
	// }
	//
	// public int hashCode() {
	// return new HashCodeBuilder().append(name).hashCode();
	// }

	public String toString() {
		return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
				.append(name).toString();
	}

}
