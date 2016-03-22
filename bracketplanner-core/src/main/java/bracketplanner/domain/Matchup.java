package bracketplanner.domain;

import java.util.HashSet;
import java.util.Set;

public class Matchup {

    private Matchup left;
    private Matchup right;
    private int round;

    public Matchup() {

    }

    public Matchup(Matchup left, Matchup right, int round) {
        this.left = left;
        this.right = right;
        this.round = round;
    }

    public Matchup getLeft() {
        return left;
    }

    public void setLeft(Matchup left) {
        this.left = left;
    }

    public Matchup getRight() {
        return right;
    }

    public void setRight(Matchup right) {
        this.right = right;
    }

    public int getRound() {
        return round;
    }

    /**
     * Recursively returns the teams under this matchup which would include all referenced matchups. The call to the Matchup
     * corresponding to a first-round matchup would return just the two teams playing. The call to the Matchup corresponding to the
     * championship game would return all teams since all are available to play at that matchup.
     * 
     * @return
     */
    public Set<Team> getTeams() {
        // Can this be optimized?
        Set<Team> teams = new HashSet<Team>();
        teams.addAll(left.getTeams());
        teams.addAll(right.getTeams());
        return teams;
    }
}
