package bracketplanner.domain;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.optaplanner.core.api.domain.solution.PlanningEntityCollectionProperty;
import org.optaplanner.core.api.domain.solution.PlanningSolution;
import org.optaplanner.core.api.domain.value.ValueRangeProvider;
import org.optaplanner.core.api.score.buildin.hardsoft.HardSoftScore;
import org.optaplanner.core.impl.solution.Solution;

@PlanningSolution
public class Bracket implements Solution<HardSoftScore> {

	private List<Team> teams;
	private List<Integer> seeds;
	private List<Site> podSites;
	// private List<Site> regionalSites;
	// private Site finalSite;
	private List<Seeding> seedings;

	private HardSoftScore score;

	@ValueRangeProvider(id = "teamList")
	public List<Team> getTeams() {
		return teams;
	}

	public void setTeams(List<Team> teams) {
		this.teams = teams;
	}

	public List<Integer> getSeeds() {
		return seeds;
	}

	public void setSeeds(List<Integer> seeds) {
		this.seeds = seeds;
	}

	@ValueRangeProvider(id = "siteList")
	public List<Site> getSites() {
		return podSites;
	}

	public void setSites(List<Site> sites) {
		this.podSites = sites;
	}

	// public List<Site> getRegionalSites() {
	// return regionalSites;
	// }

	// public void setRegionalSites(List<Site> regionalSites) {
	// this.regionalSites = regionalSites;
	// }

	// public Site getFinalSite() {
	// return finalSite;
	// }

	// public void setFinalSite(Site finalSite) {
	// this.finalSite = finalSite;
	// }

	@PlanningEntityCollectionProperty
	public List<Seeding> getSeedings() {
		return seedings;
	}

	public void setSeedings(List<Seeding> seedings) {
		this.seedings = seedings;
	}

	public Collection<? extends Object> getProblemFacts() {
		List<Object> facts = new ArrayList<Object>();
		facts.addAll(teams);
		facts.addAll(seeds);
		facts.addAll(podSites);
		// facts.addAll(regionalSites);
		return facts;
	}

	public HardSoftScore getScore() {
		return score;
	}

	public void setScore(HardSoftScore arg0) {
		this.score = arg0;
	}

	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append("\n");
		for (Seeding seeding : seedings) {
			sb.append(seeding.toString() + "\n");
		}
		return sb.toString();
	}
}
