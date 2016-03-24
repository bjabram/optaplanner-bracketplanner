package bracketplanner.domain.solver;

import org.optaplanner.core.impl.heuristic.selector.common.decorator.SelectionSorterWeightFactory;

import bracketplanner.domain.Bracket;
import bracketplanner.domain.Seeding;
import bracketplanner.domain.Team;

public class TeamStrengthWeightFactory implements SelectionSorterWeightFactory<Bracket, Team> {

    public Comparable<TeamStrengthWeight> createSorterWeight(Bracket bracket, Team team) {
        return new TeamStrengthWeight(bracket, team);
    }

    class TeamStrengthWeight implements Comparable<TeamStrengthWeight> {
        private Bracket bracket;
        private Team team;

        public TeamStrengthWeight(Bracket bracket, Team team) {
            this.bracket = bracket;
            this.team = team;
        }

        public int compareTo(TeamStrengthWeight other) {
            int sumSeeds = 0;
            for (Seeding s : bracket.getSeedings()) {
                sumSeeds += s != null ? s.getSeed() : 0;
            }

            if ((this.team.getExpectedSeed() + sumSeeds) % 34 == 0)
                return -1;
            if ((other.team.getExpectedSeed() + sumSeeds) % 34 == 0)
                return 1;
            return 0;
        }
    }
}
