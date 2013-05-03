package bracketplanner.persistence;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import au.com.bytecode.opencsv.CSVReader;
import bracketplanner.domain.Bracket;
import bracketplanner.domain.Seeding;
import bracketplanner.domain.Site;
import bracketplanner.domain.Team;
import bracketplanner.util.MarchMadnessUtil;


public class BracketGenerator {

    public static Bracket generateBracket() {

        Bracket unsolvedBracket = new Bracket();

        // Import list of teams
        List<Team> teamList = new ArrayList<Team>();
        try {
            CSVReader reader = new CSVReader(new BufferedReader(new InputStreamReader(BracketGenerator.class.getClassLoader()
                    .getResourceAsStream(("teams64.csv")))), ',', '"', 1);
            String[] nextLine;
            while ((nextLine = reader.readNext()) != null) {
                int rank = Integer.parseInt(nextLine[0]);
                String name = nextLine[1];
                // String city = nextLine[2];
                String conference = nextLine[3];
                double rpi = Double.parseDouble(nextLine[4]);
                double latitude = Double.parseDouble(nextLine[5]);
                double longitude = Double.parseDouble(nextLine[6]);
                teamList.add(new Team(rank, name, conference, rpi, latitude, longitude));
            }
            reader.close();
        } catch (IOException ex) {
            throw new RuntimeException("Could not read CSV file");
        }

        // Create seeds
        List<Integer> seedList = new ArrayList<Integer>(MarchMadnessUtil.NUM_TEAMS_PER_REGION);
        for (int i = 1; i <= MarchMadnessUtil.NUM_TEAMS_PER_REGION; i++) {
            seedList.add(i);
        }

        // Import list of game sites
        List<Site> podSiteList = new ArrayList<Site>();
        List<Site> regionalSiteList = new ArrayList<Site>();

        try {
            CSVReader reader = new CSVReader(new BufferedReader(new InputStreamReader(BracketGenerator.class.getClassLoader()
                    .getResourceAsStream(("sites8.csv")))), ',', '"', 1);
            String[] nextLine;
            while ((nextLine = reader.readNext()) != null) {
                int round = Integer.parseInt(nextLine[0]);
                String name = nextLine[1];
                // String city = nextLine[2];
                String hostTeamname = nextLine[3];
                double latitude = Double.parseDouble(nextLine[4]);
                double longitude = Double.parseDouble(nextLine[5]);
                Site site = new Site(name, round, hostTeamname, latitude, longitude);
                if (round == 1)
                    podSiteList.add(site);
                else if (round == 2)
                    regionalSiteList.add(site);
            }
            reader.close();
        } catch (IOException ex) {
            throw new RuntimeException("Could not read CSV file");
        }

        // Create initial list of seedings
        List<Seeding> seedingList = new ArrayList<Seeding>(MarchMadnessUtil.NUM_TEAMS);
        for (int i = 0; i < MarchMadnessUtil.NUM_TEAMS; i++) {
            Seeding seeding = new Seeding();
            seeding.setSeed(seedList.get(MarchMadnessUtil.getSeedIndex(i)));
            seeding.setPod(MarchMadnessUtil.getPodIndex(i) + 1);
            seeding.setRegionalSite(regionalSiteList.get(MarchMadnessUtil.getRegionIndex(i)));
            seedingList.add(seeding);
        }

        unsolvedBracket.setTeams(teamList);
        unsolvedBracket.setSeeds(seedList);
        unsolvedBracket.setSites(podSiteList);
        unsolvedBracket.setSeedings(seedingList);

        return unsolvedBracket;
    }
}
