package bracketplanner.domain;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

public class Game {

    private Team team1;
    private Team team2;

    public Game(Team team1, Team team2) {
        this.team1 = team1;
        this.team2 = team2;
    }

    public Team getTeam1() {
        return team1;
    }

    public Team getTeam2() {
        return team2;
    }

    public boolean equals(Game o) {
        return new EqualsBuilder().append(this.team1, o.getTeam1()).append(this.team2, o.getTeam2()).isEquals()
                || new EqualsBuilder().append(this.team1, o.getTeam2()).append(this.team2, o.getTeam1()).isEquals();
    }

    public int hashCode() {
        return new HashCodeBuilder().append(team1).append(team2).hashCode();
    }

    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE).append(team1).append(team2).toString();
    }
}
