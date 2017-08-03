import java.util.*;

public class Node {
	public double attribute = -1;// -1 for leaf node
	public double threshold = -1;// -1 for leaf node
	public double informationGain = -1;// -1 for leaf node
	public ArrayList<Double> classDistribution = null;// NULL for non leaf node
	public Node left_child = null;// NULL for leaf node
	public Node right_child = null;// NULL for leaf node
	
	public Node()
	{
	
	}
	
	public Node(double attribute, double threshold, double informationGain, ArrayList<Double> classDistribution, Node left_child, Node right_child)
	{
		this.attribute=attribute;
		this.threshold=threshold;
		this.informationGain=informationGain;
		this.classDistribution=classDistribution;
		this.left_child=left_child;
		this.right_child=right_child;
	}
	
	public double getattribute() { return attribute; }
	
	public double getthreshold() { return threshold; }
	
	
	public double getinformationGain() { return informationGain; }
}



