package Algorithms;
import java.util.ArrayList;

import Model.Model;

public class Forward {	
	
	public static double forward (Model model) {
		int size = model.getSize();
		double[] results = initialization(model, size);
		int iterationsCompleted = 1;
		while (iterationsCompleted < model.obs.length) {
			results = induction(model, results, size, iterationsCompleted);
			iterationsCompleted++;
		}
		double result = termination(results, size);
		return result;
	}
	
	public static double[] initialization(final Model model, final int size) {
		double[] results = new double[size];
		for (int i = 0; i < size; i++) {
			if (model.obs[i] == "H") {
				results[i] = model.piTable[i] * model.betaTable[i][0];
			}
			else if (model.obs[i] == "T") {
				results[i] = model.piTable[i] * model.betaTable[i][1];
			}
		}
		return results;
	}
	
	public static double[] induction(final Model model, final double[] vals, final int size, final int iterations) {
		int count = 0;
		double[] results = new double[size];
		ArrayList<Double> aVals = new ArrayList<Double>();
		for (int n = 0; n < size; n++) {
			double tempVal = 0;
			for (int i = 0; i < size; i++) {
				for (int j = 0; j < size; j++) {
					aVals.add(model.alphaTable[j][i]);
				}
				tempVal += vals[i] * aVals.get(count);
				count++;
			}
			if (model.obs[iterations] == "H") {
				results[n] = tempVal * model.betaTable[n][0];
			}
			else if (model.obs[iterations] == "T") {
				results[n] = tempVal * model.betaTable[n][1];
			}
		}
		return results;
	}
	
	public static double termination(final double[] vals, final int size) {
		double result = 0;
		for (int i = 0; i < size; i++) {
			result += vals[i];
		}
		return result;
	}	
}