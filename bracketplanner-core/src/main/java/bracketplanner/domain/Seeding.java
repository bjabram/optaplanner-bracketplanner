package bracketplanner.domain;

import java.util.HashSet;
import java.util.Set;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.optaplanner.core.api.domain.entity.PlanningEntity;
import org.optaplanner.core.api.domain.variable.PlanningVariable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import bracketplanner.domain.solver.SiteStrengthComparator;
import bracketplanner.domain.solver.TeamStrengthComparator;

import com.javadocmd.simplelatlng.LatLngTool;
import com.javadocmd.simplelatlng.util.LengthUnit;

@PlanningEntity()
public class Seeding extends Matchup {
    protected final transient Logger log = LoggerFactory.getLogger(this.getClass());
    static final int MINIMUM_DISTANCE = 100;

    private int id;
    private Team team;
    private int seedNumber;
    private int podNumber;
    private Site podSite;
    private Site regionalSite;

    public Seeding() {
        super();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @PlanningVariable(strengthComparatorClass = TeamStrengthComparator.class, valueRangeProviderRefs = { "teamList" })
    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public int getSeed() {
        return seedNumber;
    }

    public void setSeed(int seed) {
        this.seedNumber = seed;
    }

    public int getPod() {
        return podNumber;
    }

    public void setPod(int podId) {
        this.podNumber = podId;
    }

    @PlanningVariable(strengthComparatorClass = SiteStrengthComparator.class, valueRangeProviderRefs = { "siteList" })
    public Site getPodSite() {
        return podSite;
    }

    public void setPodSite(Site site) {
        this.podSite = site;
    }

    public Site getRegionalSite() {
        return regionalSite;
    }

    public void setRegionalSite(Site regionalSite) {
        this.regionalSite = regionalSite;
    }

    public int getDistanceToPodSite() {
        return getDistanceToSite(podSite);
    }

    public int getDistanceToRegionalSite() {
        return getDistanceToSite(regionalSite);
    }

    protected int getDistanceToSite(Site site) {
        int distance = (int) Math.ceil(LatLngTool.distance(team, site, LengthUnit.MILE));
        return Math.max(distance, MINIMUM_DISTANCE);
    }

    /*
     * (non-Javadoc)
     * 
     * @see bracketplanner.domain.Matchup#getTeams()
     */
    public Set<Team> getTeams() {
        Set<Team> teams = new HashSet<Team>();
        teams.add(team);
        return teams;
    }

    public Seeding clone() {
        Seeding seeding = new Seeding();
        seeding.team = team;
        seeding.podSite = podSite;
        seeding.seedNumber = seedNumber;
        seeding.regionalSite = regionalSite;
        return seeding;
    }

    /*
     * Object overrides
     */

    /*
     * public boolean equals(Seeding o) { return new EqualsBuilder().append(this.id, o.id).append(this.team,
     * o.team).append(this.seed, o.seed) .append(this.podSite, o.podSite).append(this.regionalSite, o.regionalSite).isEquals(); }
     * 
     * public int hashCode() { return new HashCodeBuilder().append(id).append(team
     * ).append(seed).append(podSite).append(regionalSite).hashCode(); }
     */
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE).append(team).append("Seed", seedNumber)
                .append("Pod", podSite).append("Region", regionalSite).toString();
    }
}
