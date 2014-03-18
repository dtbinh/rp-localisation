package rp.robotics.localisation;

import java.util.Random;

import rp.robotics.mapping.Heading;

/**
 * An example of how you could start writing an action model given the available
 * classes.
 * 
 * @author nah
 * 
 */
public class DummyActionModel implements ActionModel {

	public GridPositionDistribution updateAfterMove(
			GridPositionDistribution _from, Heading _heading) {

		// Create a new distribution from the input distribution.
		// NB It might prove more efficient to update the distribution directly,
		// rather than create a new one each time.
		GridPositionDistribution afterAction = new GridPositionDistribution(
				_from);

		Random rand = new Random();

		int x;
		int y;
		
		// 5 points set to random values
		for (int i = 0; i < 5; i++) {
			do {
				x = rand.nextInt(afterAction.getGridWidth());
				y = rand.nextInt(afterAction.getGridHeight());
				// keep looping until unobstructed point is found
			} while (afterAction.isObstructed(x, y));
			afterAction.setProbability(x, y, rand.nextFloat());
		}

		afterAction.normalise();

		return afterAction;

	}
}
