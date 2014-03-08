package bracketplanner.domain.solver;

import java.io.Serializable;
import java.util.Comparator;

import bracketplanner.domain.Site;

import com.javadocmd.simplelatlng.LatLng;

public class SiteStrengthComparator implements Comparator<Site>, Serializable {

	private static final long serialVersionUID = -6750248009184878616L;
	public static final LatLng US_CENTER_GEOGRAPHIC = new LatLng(39.8282, -98.5795);
	public static final LatLng US_CENTER_POPULATION = new LatLng(37.6970, -91.8096);

	public int compare(Site site1, Site site2) {
		return 0;
		// return new CompareToBuilder().append(LatLngTool.distance(site2,
		// US_CENTER_GEOGRAPHIC, LengthUnit.MILE), LatLngTool.distance(site1,
		// US_CENTER_GEOGRAPHIC, LengthUnit.MILE)).toComparison(); // descending
	}
}
