package rp.robotics.visualisation;

import javax.swing.JFrame;

import rp.robotics.localisation.GridPositionDistribution;
import rp.robotics.mapping.GridMap;
import rp.robotics.mapping.Heading;
import rp.robotics.mapping.LocalisationUtils;

public class MapViewer {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		JFrame frame = new JFrame("Map Viewer");

		GridMap gridMap = LocalisationUtils.createTrainingMap();

		GridPositionDistribution distribution = new GridPositionDistribution(gridMap);

		int x = 0;
		int y = 0;

		distribution.setProbability(x, y, 0.5f);
		distribution.normalise();

		System.out.println("distance PLUS_Y (down): "
				+ gridMap.rangeToObstacleFromGridPoint(x, y, Heading.PLUS_Y));

		System.out.println("distance PLUS_X (right): "
				+ gridMap.rangeToObstacleFromGridPoint(x, y, Heading.PLUS_X));

		System.out.println("distance MINUS_Y (up): "
				+ gridMap.rangeToObstacleFromGridPoint(x, y, Heading.MINUS_Y));

		System.out.println("distance MINUS_X (left): "
				+ gridMap.rangeToObstacleFromGridPoint(x, y, Heading.MINUS_X));

// This appears to be broken
//		System.out.println("isValidTransition: "
//				+ gridMap.isValidTransition(x, y, x, y - 1));
//		System.out.println("isValidTransition: "
//				+ gridMap.isValidTransition(x, y, x, y + 1));

		// view the map with 2 pixels as 1 cm
		GridPoseDistributionVisualisation mapVis = new GridPoseDistributionVisualisation(
				distribution, 2);

		frame.add(mapVis);
		frame.pack();
		frame.setSize(1050, 600);

		frame.setVisible(true);

	}
}
