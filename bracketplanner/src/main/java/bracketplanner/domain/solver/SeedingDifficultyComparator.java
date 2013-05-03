package bracketplanner.domain.solver;

import java.io.Serializable;
import java.util.Comparator;

import org.apache.commons.lang.builder.CompareToBuilder;

import bracketplanner.domain.Seeding;

public class SeedingDifficultyComparator implements Comparator<Seeding>, Serializable {

    private static final long serialVersionUID = -5793816354372140332L;

    public int compare(Seeding seeding1, Seeding seeding2) {
        return new CompareToBuilder().append(seeding2.getSeed(), seeding1.getSeed()).toComparison(); // Descending
    }
}
