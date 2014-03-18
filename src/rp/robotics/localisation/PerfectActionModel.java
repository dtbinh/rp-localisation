package rp.robotics.localisation;

import rp.robotics.mapping.Heading;

/**
 * Example structure for an action model that should move the probabilities 1
 * cell in the requested direction. In the case where the move would take the
 * robot into an obstacle or off the map, this model assumes the robot stayed in
 * one place. This is the same as the model presented in Robot Programming
 * Lecture 14.
 * 
 * Note that this class doesn't actually do this, instead it shows you a
 * <b>possible</b> structure for your action model.
 * 
 * @author nah
 * 
 */
public class PerfectActionModel implements ActionModel {

	@Override
	public GridPositionDistribution updateAfterMove(
			GridPositionDistribution _from, Heading _heading) {

		// Create the new distribution that will result from applying the action
		// model

		GridPositionDistribution to = new GridPositionDistribution(_from);
//		GridPositionDistribution toR = new GridPositionDistribution(_from);
//		GridPositionDistribution toU = new GridPositionDistribution(_from);
//		GridPositionDistribution toD = new GridPositionDistribution(_from);
//		GridPositionDistribution toL = new GridPositionDistribution(_from);
//		
//		
//		movePlusX(_from, toR, 1, 0);
//		movePlusX(_from, toD, 0, 1);
//		movePlusX(_from, toL, -1, 0);
//		movePlusX(_from, toU, 0, -1);
//		
//		
//		for (int y = 0; y < to.getGridHeight(); y++) {
//
//			for (int x = 0; x < to.getGridWidth(); x++) {
//				
//				to.setProbability(x, y, toR.getProbability(x, y) + toD.getProbability(x, y) + toL.getProbability(x, y)+ toU.getProbability(x, y));
//			}
//			
//		}
		
		
		
		// Move the probability in the correct direction for the action
		if (_heading == Heading.PLUS_X) {
			movePlusX(_from, to, 1, 0);
		} else if (_heading == Heading.PLUS_Y) {
			movePlusX(_from, to, 0, 1);
		} else if (_heading == Heading.MINUS_X) {
			movePlusX(_from, to, -1, 0);
		} else if (_heading == Heading.MINUS_Y) {
			movePlusX(_from, to, 0, -1);
		}
		
		
		
		
		

		return to;
	}

	/**
	 * Move probabilities from _from one cell in the plus x direction into _to
	 * 
	 * @param _from
	 * @param _to
	 */
	private void movePlusX(GridPositionDistribution _from,
			GridPositionDistribution _to, int dx, int dy) {

		// iterate through points updating as appropriate
		for (int y = 0; y < _to.getGridHeight(); y++) {

			for (int x = 0; x < _to.getGridWidth(); x++) {

				// make sure to respect obstructed grid points
				if (!_to.isObstructed(x, y)
						&& _to.isValidGridPoint(x - dx, y - dy)) {

					int fromX = x - dx;
					int fromY = y - dy;
					float fromProb = _from.getProbability(fromX, fromY);

					int toX = x;
					int toY = y;

					if (_to.getGridMap().isValidTransition(fromX, fromY, toX,
							toY)) {
						_to.setProbability(toX, toY,
								fromProb + _from.getProbability(fromX, fromY));
					} else {
						_to.setProbability(toX, toY, 0);
					}

				} else {
					int toX = x;
					int toY = y;

					_to.setProbability(toX, toY, 0);
				}
			}
		}
	}
}
