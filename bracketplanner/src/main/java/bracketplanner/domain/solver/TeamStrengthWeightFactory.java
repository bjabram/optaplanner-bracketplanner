package bracketplanner.domain.solver;

import org.optaplanner.core.impl.heuristic.selector.common.decorator.SelectionSorterWeightFactory;

import bracketplanner.domain.Bracket;
import bracketplanner.domain.Seeding;
import bracketplanner.domain.Team;

public class TeamStrengthWeightFactory implements SelectionSorterWeightFactory<Bracket, Team> {

    @Override
    public Comparable<Team> createSorterWeight(Bracket solution, Team selection) {
        return new TeamStrengthWeight(solution, selection);
    }

    class TeamStrengthWeight implements Comparable<Team> {
        private Bracket bracket;
        private Team team;

        public TeamStrengthWeight(Bracket bracket, Team team) {
        }

        public int compareTo(Team other) {
            int sumSeeds = 0;
            for (Seeding s : bracket.getSeedings()) {
                sumSeeds += s != null ? s.getSeed() : 0;
            }

            if ((team.getExpectedSeed() + sumSeeds) % 34 == 0)
                return -1;
            if ((other.getExpectedSeed() + sumSeeds) % 34 == 0)
                return 1;
            return 0;
        }
    }
}
