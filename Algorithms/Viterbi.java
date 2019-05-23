package Algorithms;
import java.util.ArrayList;
import java.util.Collections;

import Model.Model;

public class Viterbi {
	
	public static ArrayList<Integer> qVals = new ArrayList<Integer>();
	
	public static double viterbiVal (Model model) {
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
	
	public static String viterbiPath(Model model) {
		ArrayList<Integer> copyQs = new ArrayList<Integer>(qVals);
		qVals.clear();
		return copyQs.toString();
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
		double maxVal = 0;
		double[] results = new double[size];
		ArrayList<Double> aVals = new ArrayList<Double>();
		ArrayList<Double> interVals = new ArrayList<Double>();
		ArrayList<Integer> tempQ = new ArrayList<Integer>();
		for (int n = 0; n < size; n++) {
			maxVal = 0;
			for (int i = 0; i < size; i++) {
				double tempVal = 0;
				for (int j = 0; j < size; j++) {
					aVals.add(model.alphaTable[j][i]);
				}
				tempVal = vals[i] * aVals.get(count);
				interVals.add(tempVal);
				count++;
			}
			maxVal = Collections.max(interVals);
			tempQ.add(interVals.indexOf(maxVal) + 1);
			if (model.obs[iterations] == "H") {
				results[n] = maxVal * model.betaTable[n][0];
			}
			else if (model.obs[iterations] == "T") {
				results[n] = maxVal * model.betaTable[n][1];
			}
			interVals.clear();
		}
		qVals.add(tempQ.get(0));
		return results;
	}
	
	public static double termination(final double[] vals, final int size) {
		double max = 0;
		for (int i = 0; i < size; i++) {
			if (vals[i] > max) {
				max = vals[i];
			}
		}
		for (int i = 0; i < size; i++) {
			if (vals[i] == max) {
				qVals.add(i + 1);
			}
		}
		return max;
	}	
}