package bracketplanner.util;

import org.junit.Test;

import bracketplanner.util.MarchMadnessUtil;

import static org.junit.Assert.*;

public class BracketPlannerUtilTest {

    @Test
    public void getPodIndex() {
        assertEquals(MarchMadnessUtil.getPodIndex(0), 0);
        assertEquals(MarchMadnessUtil.getPodIndex(1), 1);
        assertEquals(MarchMadnessUtil.getPodIndex(14), 14);
        assertEquals(MarchMadnessUtil.getPodIndex(15), 15);
        assertEquals(MarchMadnessUtil.getPodIndex(16), 15);
        assertEquals(MarchMadnessUtil.getPodIndex(17), 14);
        assertEquals(MarchMadnessUtil.getPodIndex(30), 1);
        assertEquals(MarchMadnessUtil.getPodIndex(31), 0);
        assertEquals(MarchMadnessUtil.getPodIndex(32), 0);
        assertEquals(MarchMadnessUtil.getPodIndex(33), 1);
    }

    @Test
    public void getRegionIndex() {
        assertEquals(MarchMadnessUtil.getRegionIndex(0), 0);
        assertEquals(MarchMadnessUtil.getRegionIndex(1), 1);
        assertEquals(MarchMadnessUtil.getRegionIndex(2), 2);
        assertEquals(MarchMadnessUtil.getRegionIndex(3), 3);
        assertEquals(MarchMadnessUtil.getRegionIndex(4), 3);
        assertEquals(MarchMadnessUtil.getRegionIndex(7), 0);
        assertEquals(MarchMadnessUtil.getRegionIndex(15), 0);
        assertEquals(MarchMadnessUtil.getRegionIndex(63), 0);
    }

    @Test
    public void getSeedIndex() {
        assertEquals(MarchMadnessUtil.getSeedIndex(0), 0);
        assertEquals(MarchMadnessUtil.getSeedIndex(15), 3);
        assertEquals(MarchMadnessUtil.getSeedIndex(16), 4);
        assertEquals(MarchMadnessUtil.getSeedIndex(31), 7);
        assertEquals(MarchMadnessUtil.getSeedIndex(32), 8);
        assertEquals(MarchMadnessUtil.getSeedIndex(47), 11);
        assertEquals(MarchMadnessUtil.getSeedIndex(48), 12);
        assertEquals(MarchMadnessUtil.getSeedIndex(63), 15);
    }

    @Test
    public void getDistanceFromMedian() {
        assertEquals(MarchMadnessUtil.getDistanceFromMedian(1), 31);
        assertEquals(MarchMadnessUtil.getDistanceFromMedian(32), 0);
        assertEquals(MarchMadnessUtil.getDistanceFromMedian(33), 0);
        assertEquals(MarchMadnessUtil.getDistanceFromMedian(64), 31);
    }
}
