package bracketplanner.domain.solver;

import java.io.Serializable;
import java.util.Comparator;

import org.apache.commons.lang.builder.CompareToBuilder;

import bracketplanner.domain.Team;
import bracketplanner.util.MarchMadnessUtil;


public class TeamStrengthComparator implements Comparator<Team>, Serializable {

    private static final long serialVersionUID = 3780427313653817465L;
    private static final int MEDIAN_RANK = MarchMadnessUtil.NUM_TEAMS / 2;

    public int compare(Team team1, Team team2) {
        // Use basic ranking order
        // return new CompareToBuilder().append(team2.getRank(), team1.getRank()).toComparison(); // descending
        // Use rank distance from median of ranks
        int team1distance = team1.getRank() <= MEDIAN_RANK ? MEDIAN_RANK - team1.getRank() : team1.getRank() - MEDIAN_RANK - 1;
        int team2distance = team2.getRank() <= MEDIAN_RANK ? MEDIAN_RANK - team2.getRank() : team2.getRank() - MEDIAN_RANK - 1;
        return new CompareToBuilder().append(team2distance, team1distance).toComparison(); // descending
    }
}
