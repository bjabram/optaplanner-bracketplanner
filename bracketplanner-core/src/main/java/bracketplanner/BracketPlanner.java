package bracketplanner;

import org.optaplanner.core.api.solver.Solver;
import org.optaplanner.core.api.solver.SolverFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import bracketplanner.domain.Bracket;
import bracketplanner.persistence.BracketGenerator;

/**
 * 
 * http://stevemorse.org/jcal/latlonbatch.html
 * 
 * @author babram
 * 
 */
public class BracketPlanner {
    public static void main(String[] args) {
		final Logger log = LoggerFactory.getLogger(BracketPlanner.class);

		SolverFactory solverFactory = SolverFactory.createFromXmlResource("bracketplannerSolverConfig.xml");
		Solver solver = solverFactory.buildSolver();

		Bracket unsolvedBracket = BracketGenerator.generateBracket();
		solver.solve(unsolvedBracket);

		Bracket solvedBracket = (Bracket) solver.getBestSolution();

		log.info(solvedBracket.toString());
	}
}
