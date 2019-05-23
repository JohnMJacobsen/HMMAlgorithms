import Model.Model;
import Algorithms.Forward;
import Algorithms.Viterbi;

public class HMM {
	
	public static void main(String[] args) {
		
		
		double[][] givenA = {{0.2, 0.8}, {0.6, 0.4}};
		double[][] givenB = {{0.7, 0.3}, {0.4, 0.6}};
		double[] givenPi = {1.0, 0.0};
		String[] givenObs = {"H", "H", "T"};
		Model givenModel = new Model(givenA, givenB, givenPi, givenObs);
		
		double[][] chosenThreeA = {{0.7, 0.3}, {0.9, 0.1}};
		double[][] chosenThreeB = {{0.5, 0.5}, {1.0, 0.0}};
		double[] chosenThreePi = {0.6, 0.4};
		String[] chosenThreeObs = {"T", "T", "H"};
		Model chosenThreeModel = new Model(chosenThreeA, chosenThreeB, chosenThreePi, chosenThreeObs);
		
		double[][] chosenSevenA = {{0.4, 0.6}, {0.2, 0.8}};
		double[][] chosenSevenB = {{0.3, 0.7}, {0.5, 0.5}};
		double[] chosenSevenPi = {0.8, 0.2};
		String[] chosenSevenObs = {"H", "H", "T", "H", "T", "H", "T"};
		Model chosenSevenModel = new Model(chosenSevenA, chosenSevenB, chosenSevenPi, chosenSevenObs);
		
		double givenModelForward = Forward.forward(givenModel);
		double chosenThreeModelForward = Forward.forward(chosenThreeModel);
		double chosenSevenModelForward = Forward.forward(chosenSevenModel);
		
		double givenModelViterbiScore = Viterbi.viterbiVal(givenModel);
		String givenModelViterbiPath = Viterbi.viterbiPath(givenModel);
		double chosenThreeModelViterbiScore = Viterbi.viterbiVal(chosenThreeModel);
		String chosenThreeModelViterbiPath = Viterbi.viterbiPath(chosenThreeModel);
		double chosenSevenModelViterbiScore = Viterbi.viterbiVal(chosenSevenModel);
		String chosenSevenModelViterbiPath = Viterbi.viterbiPath(chosenSevenModel);
		
		System.out.println("The probability of the given model occurring is " + givenModelForward);
		System.out.println("The probability of my chosen three observation model occurring is " + chosenThreeModelForward);
		System.out.println("The probability of my chosen seven observation model occurring is " + chosenSevenModelForward);
		System.out.println("The viterbi score of the given model occurring is " + givenModelViterbiScore + " with a path of " + givenModelViterbiPath);
		System.out.println("The viterbi score of my chosen three observation model occurring is " + chosenThreeModelViterbiScore + " with a path of " + chosenThreeModelViterbiPath);
		System.out.println("The viterbi score of my chosen seven observation model occurring is " + chosenSevenModelViterbiScore + " with a path of " + chosenSevenModelViterbiPath);
	}
}
