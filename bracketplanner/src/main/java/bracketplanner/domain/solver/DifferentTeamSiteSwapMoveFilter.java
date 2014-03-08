package bracketplanner.domain.solver;

import org.optaplanner.core.impl.heuristic.selector.common.decorator.SelectionFilter;
import org.optaplanner.core.impl.heuristic.selector.move.generic.SwapMove;
import org.optaplanner.core.impl.score.director.ScoreDirector;

import bracketplanner.domain.Seeding;

public class DifferentTeamSiteSwapMoveFilter implements SelectionFilter<SwapMove> {

    public boolean accept(ScoreDirector scoreDirector, SwapMove selection) {
        Seeding leftSeeding = (Seeding) selection.getLeftEntity();
        Seeding rightSeeding = (Seeding) selection.getRightEntity();
        return !leftSeeding.getTeam().equals(rightSeeding.getTeam()) || !leftSeeding.getPodSite().equals(rightSeeding.getPodSite());
    }

}
