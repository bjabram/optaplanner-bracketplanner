package bracketplanner.util;

import org.junit.Test;

import bracketplanner.util.BracketPlannerUtil;

import static org.junit.Assert.*;

public class BracketPlannerUtilTest {

	@Test
	public void getPodIndex() {
		assertEquals(BracketPlannerUtil.getPodIndex(0), 0);
		assertEquals(BracketPlannerUtil.getPodIndex(1), 1);
		assertEquals(BracketPlannerUtil.getPodIndex(14), 14);
		assertEquals(BracketPlannerUtil.getPodIndex(15), 15);
		assertEquals(BracketPlannerUtil.getPodIndex(16), 15);
		assertEquals(BracketPlannerUtil.getPodIndex(17), 14);
		assertEquals(BracketPlannerUtil.getPodIndex(30), 1);
		assertEquals(BracketPlannerUtil.getPodIndex(31), 0);
		assertEquals(BracketPlannerUtil.getPodIndex(32), 0);
		assertEquals(BracketPlannerUtil.getPodIndex(33), 1);
	}

	@Test
	public void getRegionIndex() {
		assertEquals(BracketPlannerUtil.getRegionIndex(0), 0);
		assertEquals(BracketPlannerUtil.getRegionIndex(1), 1);
		assertEquals(BracketPlannerUtil.getRegionIndex(2), 2);
		assertEquals(BracketPlannerUtil.getRegionIndex(3), 3);
		assertEquals(BracketPlannerUtil.getRegionIndex(4), 3);
		assertEquals(BracketPlannerUtil.getRegionIndex(7), 0);
		assertEquals(BracketPlannerUtil.getRegionIndex(15), 0);
		assertEquals(BracketPlannerUtil.getRegionIndex(63), 0);
	}

	@Test
	public void getSeedIndex() {
		assertEquals(BracketPlannerUtil.getSeedIndex(0), 0);
		assertEquals(BracketPlannerUtil.getSeedIndex(15), 3);
		assertEquals(BracketPlannerUtil.getSeedIndex(16), 4);
		assertEquals(BracketPlannerUtil.getSeedIndex(31), 7);
		assertEquals(BracketPlannerUtil.getSeedIndex(32), 8);
		assertEquals(BracketPlannerUtil.getSeedIndex(47), 11);
		assertEquals(BracketPlannerUtil.getSeedIndex(48), 12);
		assertEquals(BracketPlannerUtil.getSeedIndex(63), 15);
	}

	@Test
	public void getDistanceFromMedian() {
		assertEquals(BracketPlannerUtil.getDistanceFromMedian(1), 31);
		assertEquals(BracketPlannerUtil.getDistanceFromMedian(32), 0);
		assertEquals(BracketPlannerUtil.getDistanceFromMedian(33), 0);
		assertEquals(BracketPlannerUtil.getDistanceFromMedian(64), 31);
	}

	@Test
	public void getScurveGroupIndex() {
		assertEquals(BracketPlannerUtil.getScurveGroupIndex(0, 3), 0);
		assertEquals(BracketPlannerUtil.getScurveGroupIndex(1, 3), 1);
		assertEquals(BracketPlannerUtil.getScurveGroupIndex(2, 3), 2);
		assertEquals(BracketPlannerUtil.getScurveGroupIndex(3, 3), 2);
		assertEquals(BracketPlannerUtil.getScurveGroupIndex(4, 3), 1);
		assertEquals(BracketPlannerUtil.getScurveGroupIndex(5, 3), 0);
		assertEquals(BracketPlannerUtil.getScurveGroupIndex(6, 3), 0);
	}

	@Test
	public void getRoundMatchup() {
		assertEquals(BracketPlannerUtil.getRoundMatchup(10, 1), 4);
		assertEquals(BracketPlannerUtil.getRoundMatchup(12, 1), 3);
		assertEquals(BracketPlannerUtil.getRoundMatchup(15, 6), 3);
		assertEquals(BracketPlannerUtil.getRoundMatchup(3, 6), 2);
		assertEquals(BracketPlannerUtil.getRoundMatchup(10, 10), 0);
		assertEquals(BracketPlannerUtil.getRoundMatchup(5, 8), 3);
		assertEquals(BracketPlannerUtil.getRoundMatchup(11, 13), 4);
		assertEquals(BracketPlannerUtil.getRoundMatchup(4, 13), 1);
		assertEquals(BracketPlannerUtil.getRoundMatchup(6, 14), 2);
		assertEquals(BracketPlannerUtil.getRoundMatchup(13, 14), 4);
	}
}
