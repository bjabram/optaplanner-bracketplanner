package bracketplanner.domain;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.optaplanner.core.api.domain.solution.PlanningEntityCollectionProperty;
import org.optaplanner.core.api.domain.solution.PlanningSolution;
import org.optaplanner.core.api.domain.value.ValueRangeProvider;
import org.optaplanner.core.api.score.buildin.hardmediumsoft.HardMediumSoftScore;
import org.optaplanner.core.impl.solution.Solution;

@PlanningSolution
public class Bracket implements Solution<HardMediumSoftScore> {

    private List<Team> teams;
    private List<Integer> seeds;
    private List<Site> podSites;
    private List<Seeding> seedings;
    private List<Game> games;

    private HardMediumSoftScore score;

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

    @PlanningEntityCollectionProperty
    public List<Seeding> getSeedings() {
        return seedings;
    }

    public void setSeedings(List<Seeding> seedings) {
        this.seedings = seedings;
    }

    public List<Game> getGames() {
        return games;
    }

    public void setGames(List<Game> games) {
        this.games = games;
    }

    public Collection<? extends Object> getProblemFacts() {
        List<Object> facts = new ArrayList<Object>();
        facts.addAll(teams);
        facts.addAll(seeds);
        facts.addAll(podSites);
        facts.addAll(games);
        return facts;
    }

    public HardMediumSoftScore getScore() {
        return score;
    }

    public void setScore(HardMediumSoftScore score) {
        this.score = score;
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
