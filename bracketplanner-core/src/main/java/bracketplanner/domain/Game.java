package bracketplanner.domain;

import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

public class Game {

    private List<Team> teams;

    public Game(List<Team> teams) {
        this.teams = teams;
    }

    public Game(Team... teams) {
        this(Arrays.asList(teams));
    }

    public Game(Team team1, Team team2) {
        this(Arrays.asList(new Team[] { team1, team2 }));
    }

    public List<Team> getTeams() {
        return teams;
    }

    public boolean equals(Game o) {
        return new EqualsBuilder().append(this.teams, o.getTeams()).isEquals();
    }

    public int hashCode() {
        return new HashCodeBuilder().append(teams).hashCode();
    }

    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE).append(teams).toString();
    }
}
