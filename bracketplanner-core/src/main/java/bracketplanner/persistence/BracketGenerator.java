package bracketplanner.persistence;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import au.com.bytecode.opencsv.CSVReader;
import bracketplanner.domain.Bracket;
import bracketplanner.domain.Game;
import bracketplanner.domain.Matchup;
import bracketplanner.domain.Seeding;
import bracketplanner.domain.Site;
import bracketplanner.domain.Team;
import bracketplanner.util.BracketPlannerUtil;

/**
 * @author babram
 * 
 */
public class BracketGenerator {
    static final Logger log = LoggerFactory.getLogger(BracketGenerator.class);
    static final String TEAMS_FILE_URL = "teams-1516.csv";
    static final String RANKINGS_FILE_URL = "rankings64-1516.csv";
    static final String SITES_FILE_URL = "venues-1516.csv";
    static final String GAMES_FILE_URL = "games-1516.csv";

    public static Bracket generateBracket() {

        Bracket unsolvedBracket = new Bracket();
        int skipLines = 1;

        // Import list of teams
        Map<String, Team> unrankedTeamMap = new HashMap<String, Team>();
        try {
            CSVReader reader = new CSVReader(new BufferedReader(new InputStreamReader(BracketGenerator.class.getClassLoader()
                    .getResourceAsStream((TEAMS_FILE_URL)))), CSVReader.DEFAULT_SEPARATOR, CSVReader.DEFAULT_QUOTE_CHARACTER,
                    skipLines);
            String[] nextLine;
            while ((nextLine = reader.readNext()) != null) {
                String name = nextLine[0];
                String abbreviation = nextLine[0];
                String conference = nextLine[6];
                double latitude = Double.parseDouble(nextLine[4]);
                double longitude = Double.parseDouble(nextLine[5]);
                unrankedTeamMap.put(abbreviation, new Team(-1, name, abbreviation, conference, latitude, longitude));
            }
            reader.close();
        } catch (IOException ex) {
            throw new RuntimeException("Could not read CSV file");
        }

        // Import rankings
        Map<String, Integer> teamsInConference = new HashMap<String, Integer>();
        List<Team> rankedTeamList = new ArrayList<Team>();
        try {
            CSVReader reader = new CSVReader(new BufferedReader(new InputStreamReader(BracketGenerator.class.getClassLoader()
                    .getResourceAsStream((RANKINGS_FILE_URL)))), CSVReader.DEFAULT_SEPARATOR, CSVReader.DEFAULT_QUOTE_CHARACTER,
                    skipLines);
            String[] nextLine;
            while ((nextLine = reader.readNext()) != null) {
                int rank = Integer.parseInt(nextLine[0]);
                String abbreviation = nextLine[1];
                Team rankedTeam = unrankedTeamMap.get(abbreviation);

                // calculate ranking within conference
                int rankInConference = 1;
                String conference = rankedTeam.getConference();
                if (teamsInConference.containsKey(conference)) {
                    rankInConference = teamsInConference.get(conference) + 1;
                }
                teamsInConference.put(conference, rankInConference);
                rankedTeam.setRankInConference(rankInConference);

                rankedTeam.setRank(rank);
                rankedTeamList.add(rankedTeam);
            }
            reader.close();
        } catch (IOException ex) {
            throw new RuntimeException("Could not read CSV file");
        }

        // Create seeds
        List<Integer> seedList = new ArrayList<Integer>(BracketPlannerUtil.NUM_TEAMS_PER_REGION);
        for (int i = 1; i <= BracketPlannerUtil.NUM_TEAMS_PER_REGION; i++) {
            seedList.add(i);
        }

        // Import list of game sites
        List<Site> podSiteList = new ArrayList<Site>();
        List<Site> regionalSiteList = new ArrayList<Site>();

        try {
            CSVReader reader = new CSVReader(new BufferedReader(new InputStreamReader(BracketGenerator.class.getClassLoader()
                    .getResourceAsStream((SITES_FILE_URL)))), CSVReader.DEFAULT_SEPARATOR, CSVReader.DEFAULT_QUOTE_CHARACTER,
                    skipLines);
            String[] nextLine;
            while ((nextLine = reader.readNext()) != null) {
                int round = Integer.parseInt(nextLine[0]);
                int venueId = Integer.parseInt(nextLine[1]);
                String name = nextLine[2];
                String location = nextLine[3];
                String hostTeamname = nextLine[4];
                double latitude = Double.parseDouble(nextLine[5]);
                double longitude = Double.parseDouble(nextLine[6]);
                Site site = new Site(name, location, round, hostTeamname, venueId, latitude, longitude);
                if (round == 1)
                    podSiteList.add(site);
                if (round == 2)
                    regionalSiteList.add(site);
            }
            reader.close();
        } catch (IOException ex) {
            throw new RuntimeException("Could not read CSV file");
        }

        // Import list of games
        List<Game> gameList = new ArrayList<Game>();

        try {
            CSVReader reader = new CSVReader(new BufferedReader(new InputStreamReader(BracketGenerator.class.getClassLoader()
                    .getResourceAsStream((GAMES_FILE_URL)))), CSVReader.DEFAULT_SEPARATOR, CSVReader.DEFAULT_QUOTE_CHARACTER,
                    skipLines);
            String[] nextLine;
            while ((nextLine = reader.readNext()) != null) {
                String team1Alias = nextLine[1];
                String team2Alias = nextLine[2];
                Team team1 = unrankedTeamMap.get(team1Alias);
                Team team2 = unrankedTeamMap.get(team2Alias);
                if (team1 != null && team2 != null) {
                    gameList.add(new Game(team1, team2));
                }
            }
            reader.close();
        } catch (IOException ex) {
            throw new RuntimeException("Could not read CSV file");
        }

        // Create initial list of seedings
        List<Seeding> seedingList = new ArrayList<Seeding>(BracketPlannerUtil.NUM_TEAMS);
        for (int i = 0; i < BracketPlannerUtil.NUM_TEAMS; i++) {
            Seeding seeding = new Seeding();
            seeding.setSeed(seedList.get(BracketPlannerUtil.getSeedIndex(i)));
            seeding.setPod(BracketPlannerUtil.getPodIndex(i) + 1);
            seeding.setRegionalSite(regionalSiteList.get(BracketPlannerUtil.getRegionIndex(i)));
            seedingList.add(seeding);
        }

        //
        // Create Matchups - this can be improved dramatically
        //
        List<Matchup> matchupList = new ArrayList<Matchup>();
        int round;

        // first round
        round = 1;
        for (int i = 0; i < 32; i++) {
            matchupList.add(new Matchup(seedingList.get(i), seedingList.get(63 - i), round));
        }

        // second round
        round = 2;
        for (int i = 0; i < 16; i++) {
            matchupList.add(new Matchup(matchupList.get(0 + i), matchupList.get(0 + (31 - i)), round));
        }

        // third round
        round = 3;
        for (int i = 0; i < 8; i++) {
            matchupList.add(new Matchup(matchupList.get(32 + i), matchupList.get(32 + (15 - i)), round));
        }

        // fourth round
        round = 4;
        for (int i = 0; i < 4; i++) {
            matchupList.add(new Matchup(matchupList.get(48 + i), matchupList.get(48 + (7 - i)), round));
        }

        // fourth round
        round = 5;
        for (int i = 0; i < 2; i++) {
            matchupList.add(new Matchup(matchupList.get(56 + i), matchupList.get(56 + (3 - i)), round));
        }

        // sixth round
        round = 6;
        for (int i = 0; i < 1; i++) {
            matchupList.add(new Matchup(matchupList.get(60 + i), matchupList.get(60 + (1 - i)), round));
        }
        unsolvedBracket.setTeams(rankedTeamList);
        unsolvedBracket.setSeeds(seedList);
        unsolvedBracket.setSites(podSiteList);
        unsolvedBracket.setSeedings(seedingList);
        unsolvedBracket.setGames(gameList);
        unsolvedBracket.setMatchups(matchupList);

        return unsolvedBracket;
    }
}
