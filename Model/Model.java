package Model;

public class Model {
	
	public final double[][] alphaTable;
	public final double[][] betaTable;
	public final double[] piTable;
	public final String[] obs;
	
	public Model (final double[][] a, final double[][] b, final double[] pi, final String[] obs) {
		this.alphaTable = a;
		this.betaTable = b;
		this.piTable = pi;
		this.obs = obs;
	}
	
	public int getSize() {
		return betaTable.length;
	}
	
}