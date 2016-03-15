package bracketplanner.util;

public class BracketPlannerUtil {

	public static final int NUM_TEAMS = 64;
	public static final int NUM_REGIONS = 4;
	public static final int NUM_TEAMS_PER_REGION = NUM_TEAMS / NUM_REGIONS;
	public static final int NUM_PODS = 16;

	/**
	 * Return the zero-indexed pod given the zero-index rank number. Uses
	 * S-curve algorithm to place teams in pods.
	 * 
	 * @param rankIndex
	 *            the rank of the team
	 * @return the pod number
	 */
	public static int getPodIndex(int rankIndex) {
		return getScurveGroupIndex(rankIndex, NUM_PODS);
	}

	/**
	 * Returns the zero-indexed region given the zero-indexed rank number. Uses
	 * S-curve algorithm to place teams in regions.
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
	 * Returns the zero-indexed distance from the median of the pool. This is
	 * used to determine difficulty in seeding two different teams; a team
	 * further away from the median has a differing difficulty than a team near
	 * the median.
	 * 
	 * @param rank
	 * @return
	 */
	public static int getDistanceFromMedian(int rank) {
		final int medianRank = NUM_TEAMS / 2;
		return rank <= medianRank ? medianRank - rank : rank - medianRank - 1;
	}

	/**
	 * Returns the S-Curve value given the overall index and the grouping size.
	 * The S-Curve is a modulo-based calculation when the index is approaches
	 * the groupSize then steps down back to zero. For example, if the groupSize
	 * is four, then the S-Curve value based on the index is 0, 1, 2, 3, 3, 2,
	 * 1, 0, 0, 1, 2, 3, 3, 2, 1, 0, 0, ...
	 * 
	 * @param index
	 * @param groupSize
	 * @return
	 */
	public static int getScurveGroupIndex(int index, int groupSize) {
		int group = index / groupSize;
		int rankInGroup = index % groupSize;
		return group % 2 == 0 ? rankInGroup : (groupSize - 1) - rankInGroup;
	}

	/**
	 * Returns the round in which two teams of the given ranks would play
	 * 
	 * @param rankA
	 *            the regional rank of the first team
	 * @param rankB
	 *            the regional rank of the second team
	 * @return The round in which the two teams of the given ranks are to play.
	 *         Team playing at the beginning of the tournament would return 1,
	 *         with the next round matchup 2 and so on For example,
	 *         getRoundMatchup(1, 16) would return 1 (assuming 16 teams in the
	 *         region), and getRoundMatchup(3, 15) would return 4 since those
	 *         two teams would play in the fourth round of the tournament.
	 */
	public static int getRoundMatchup(int rankA, int rankB) {
		return getRoundMatchup(rankA, rankB, NUM_TEAMS_PER_REGION);
	}

	/**
	 * Helper method for
	 * 
	 * @param rankA
	 *            the regional rank of the first team
	 * @param rankB
	 *            the regional rank of the second team
	 * @param numTeams
	 *            the number of teams to be considered
	 * @return 0 if the ranks are the same (base case) or a recursive call back
	 *         into the helper method to calculate the next round of playing
	 */
	public static int getRoundMatchup(int rankA, int rankB, int numTeams) {
		if (rankA <= 0 || rankB <= 0 || rankA > numTeams || rankB > numTeams) {
			// throw new Exception();
		}
		if (rankA == rankB) {
			return 0;
		} else {
			return 1 + getRoundMatchup(Math.min(rankA, numTeams - rankA + 1),
					Math.min(rankB, numTeams - rankB + 1), numTeams / 2);
		}
	}
}
