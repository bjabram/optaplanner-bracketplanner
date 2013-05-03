package bracketplanner.util;

public class MarchMadnessUtil {

    public static final int NUM_TEAMS = 64;
    public static final int NUM_REGIONS = 4;
    public static final int NUM_TEAMS_PER_REGION = NUM_TEAMS / NUM_REGIONS;
    public static final int NUM_PODS = 16;

    /**
     * Return the zero-indexed pod given the zero-index rank number. Uses S-curve algorithm to place teams in pods.
     * 
     * @param rankIndex
     *            the rank of the team
     * @return the pod number
     */
    public static int getPodIndex(int rankIndex) {
        return getScurveGroupIndex(rankIndex, NUM_PODS);
    }

    /**
     * Returns the zero-indexed region given the zero-indexed rank number. Uses S-curve algorithm to place teams in regions.
     * 
     * @param rankIndex
     * @return
     */
    public static int getRegionIndex(int rankIndex) {
        return getScurveGroupIndex(rankIndex, NUM_REGIONS);
    }

    /**
     * Returns the zero-indexed seed given the zero-indexed rank number.
     * 
     * @param rankIndex
     * @return
     */
    public static int getSeedIndex(int rankIndex) {
        return rankIndex / NUM_REGIONS;
    }

    /**
     * Returns the zero-indexed distance from the median of the pool. This is used to determine difficulty in seeding two different
     * teams; a team farther away from the media has a differeing difficulty than a team near the median.
     * 
     * @param rank
     * @return
     */
    public static int getDistanceFromMedian(int rank) {
        final int medianRank = NUM_TEAMS / 2;
        return rank <= medianRank ? medianRank - rank : rank - medianRank - 1;
    }

    public static int getScurveGroupIndex(int index, int groupSize) {
        int group = index / groupSize;
        int rankInGroup = index % groupSize;
        return group % 2 == 0 ? rankInGroup : (groupSize - 1) - rankInGroup;
    }

}
