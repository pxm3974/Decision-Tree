import java.util.ArrayList;
import java.util.*;
import java.io.*;
import java.util.Collections;
import java.util.Random;
import java.lang.*;

public class Entropy {	
	//entropy calcuation
	public static double calculateEntropy(ArrayList<Double> data) {
		double entropy = 0;
		
		if(data.size() == 0) {
			// nothing to do
			return 0;
		}
		//System.out.println(data);
		for(double i = 1.0; i <11.0; i++) {
			int count = 0;
			for(int j = 0; j < data.size(); j++) {
				if( data.get(j)== i) {
					count++;
				}
				
			}
			//System.out.println(count);	
			double probability = count / (double)data.size();
			if(count > 0) {
				entropy += -probability * (Math.log(probability) / Math.log(2));
			}
		}
		
		return entropy;
		}

	
	/*public static double calculateGain(double rootEntropy, ArrayList<Double> subEntropies, ArrayList<Integer> setSizes, int data) {
		double gain = rootEntropy; 
		
		for(int i = 0; i < subEntropies.size(); i++) {
			gain += -((setSizes.get(i) / (double)data) * subEntropies.get(i));
		}
		
		return gain;
	}*/
	//information gain calculation
	public static double information_gain(ArrayList<ArrayList<Double>> examples, int attri, double threshold)
	{
		ArrayList<Double> first=new ArrayList<Double>();
		ArrayList<Double> second=new ArrayList<Double>();
		ArrayList<Double> values=new ArrayList<Double>();
		int x=0;
		int mp=examples.get(0).size()-1;
		while(x<examples.size())
		{
			double bam=examples.get(x).get(mp);
			values.add(bam);
			x++;
		}
		//System.out.println(values);
		
		double gain=calculateEntropy(values);
		for(int we=0; we<examples.size();we++)
		{
			if(examples.get(we).get(attri)<threshold)
			{
				first.add(examples.get(we).get(mp));
			}
			
			else if(examples.get(we).get(attri)>threshold)
			{
				second.add(examples.get(we).get(mp));
			}
		}
		
		double first_entropy=calculateEntropy(first);
		double second_entropy=calculateEntropy(second);
		//System.out.println(first_entropy);
		//System.out.println(second_entropy);
		double dd=first.size();
		double dd22=examples.size();
		double tried=dd/dd22;
		//System.out.println(dd);
		//System.out.println(dd22);
		//System.out.println(tried);
		double dd2=second.size();
		double dd44=examples.size();
		double tried2=dd2/dd44;
		double total_gain=gain-tried*first_entropy-tried2*second_entropy;
		//System.out.println(total_gain);
		return total_gain;
	}
	
	//choose attribute 
	public static double[] Choose_Attribute(ArrayList<ArrayList<Double>> examples, int attributes)
	{
		double max_gain=-1, best_attribute=-1, best_threshold=-1;
		int m=0;
		
		while(m<attributes-1)
		{
			ArrayList<Double> attribute_values=new ArrayList<Double>();
			int x=0;
			while(x<examples.size())
			{
				double bam=examples.get(x).get(m);
				attribute_values.add(bam);
				x++;
			}
			double min = Collections.min(attribute_values);
			double max = Collections.max(attribute_values);
			
			for (int k=1; k<=50; k++)
			{
				double threshold= min + k*(max-min)/51;
				double gain=information_gain (examples, m, threshold);
				if(gain>max_gain)
				{
					max_gain=gain;
					best_attribute=m;
					best_threshold=threshold;
				}
			}
			//System.out.println("Minimum Element of Java ArrayList is : " + min);
			//System.out.println("Minimum Element of Java ArrayList is : " + max);
			//System.out.println(attribute_values);
			m++;
		}
		
		return new double[] {best_attribute, best_threshold, max_gain}; 
	}
	//finding the minimum value
	public static ArrayList<ArrayList<Double>> min(ArrayList<ArrayList<Double>> examples, double attri, double threshold)
	{
		int ram= (int) attri;
		//System.out.println(ram);
		ArrayList<ArrayList<Double>> first=new ArrayList<ArrayList<Double>>();
		for(int we=0; we<examples.size();we++)
		{
			if(examples.get(we).get(ram)<threshold)
			{
				first.add(examples.get(we));
			}
		}
		//System.out.println("the first:");
		//System.out.println(first);
		return first;
						
	}
	//finding the max value
	public static ArrayList<ArrayList<Double>> max(ArrayList<ArrayList<Double>> examples, double attri, double threshold)
		{
			int ram=(int) attri;
			//System.out.println(ram);
			ArrayList<ArrayList<Double>> second=new ArrayList<ArrayList<Double>>();
			for(int we=0; we<examples.size();we++)
			{
				
				if(examples.get(we).get(ram)>threshold)
				{
					second.add(examples.get(we));
				}
			}
			//System.out.println("The second:");
			//System.out.println(second);
			return second;
							
		}
	//checking two element same or not
	public static double checkSame(ArrayList<ArrayList<Double>> examples)
	{
		if(examples.isEmpty())
		{
			return 0.0;
			//System.out.println("its empty");
		}

		int mp=examples.get(0).size()-1;
		//System.out.println("gkasdjl");
		//System.out.println(mp);
		int count=0;
		int j=1;
		while(j<examples.size())
		{
			double sano=examples.get(0).get(mp);
			double mano=examples.get(j).get(mp);
			//System.out.println(mano);
			if(sano==mano) {
				count++;
			}
			
			j++;
		}
		
		if(count==examples.size())
		{
			return examples.get(0).get(mp);
		}
		
		else {
			return 0.0;
		}
		
	}
	//creating a tree
	public Node DecisionTree(ArrayList<ArrayList<Double>> examples, int attri, ArrayList<Double> defaultvalue)
	{
		Node tree=null;
		double best_attribute=-1, best_threshold=-1, information_gain=-1;
		ArrayList<ArrayList<Double>> examples_left=new ArrayList<ArrayList<Double>>();
		ArrayList<ArrayList<Double>> examples_right=new ArrayList<ArrayList<Double>>();
		double tru=checkSame(examples);
		//System.out.println(examples);
		if(examples.isEmpty())
		{
			double max=Collections.max(defaultvalue);
			int i=defaultvalue.indexOf(max); 
			double pop=(double) i;
			Node jam=new Node(pop, best_threshold, information_gain, defaultvalue, null, null);
			return jam;
		}
		
		else if(tru!=0.0)
		{
			Node same=new Node(tru, best_threshold, information_gain, defaultvalue, null, null);
			//System.out.println("same same");
			return same;
		}
		
		else {
			double[] ret =Choose_Attribute(examples, examples.get(0).size());
			best_attribute=ret[0];
			best_threshold=ret[1];
			information_gain=ret[2];
			//System.out.println(best_attribute);
			//System.out.println(best_threshold);
			tree=new Node(best_attribute, best_threshold, information_gain,defaultvalue,null,null);
			examples_left=min(examples, best_attribute, best_threshold);
			examples_right=max(examples, best_attribute, best_threshold);
			tree.left_child=DecisionTree(examples_left, attri, Distribution(examples));
			//System.out.println(" nothing pass through it");
			tree.right_child=DecisionTree(examples_right, attri, Distribution(examples));
			
		}
		//System.out.println(second);
		return tree;
	}
	//calulating distribution
	public static ArrayList<Double> Distribution(ArrayList<ArrayList<Double>> examples)
	{
		ArrayList<Double> dis=new ArrayList<Double>();
		for(double i = 1.0; i <11.0; i++) {
			int count = 0;
			for(int j = 0; j < examples.size(); j++) {
				if( examples.get(j).get(examples.get(0).size()-1)== i) {
					count++;
				}
				
			}
			//System.out.println(count);	
			double probability = count / (double)examples.size();
			dis.add(probability);
		}
		return dis;
	}
	//choosig random attribute
	public static double[] Choose_Random_Attribute(ArrayList<ArrayList<Double>> examples, int attributes)
	{
		double max_gain=-1, best_threshold=-1;
		Random rand = new Random(); 
		int m=rand.nextInt(attributes);
		ArrayList<Double> attribute_values=new ArrayList<Double>();
		int x=0;
		while(x<examples.size())
		{
			double bam=examples.get(x).get(m);
			attribute_values.add(bam);
			x++;
		}
		double min = Collections.min(attribute_values);
		double max = Collections.max(attribute_values);
		
		for (int k=1; k<=50; k++)
		{
			double threshold= min + k*(max-min)/51;
			double gain=information_gain (examples, m, threshold);
			if(gain>max_gain)
			{
				max_gain=gain;
				best_threshold=threshold;
			}
		}
		double samu=(double) m;
		//System.out.println(samu);

		//System.out.println("Minimum Element of Java ArrayList is : " + min);
		//System.out.println("Minimum Element of Java ArrayList is : " + max);
		//System.out.println(attribute_values);
		return new double[] {samu, best_threshold, max_gain}; 
	}
	//random decisiontree
	public Node Randomized_DecisionTree(ArrayList<ArrayList<Double>> examples, int attri, ArrayList<Double> defaultvalue)
		{
			Node tree=null;
			double best_attribute=-1, best_threshold=-1, information_gain=-1;
			ArrayList<ArrayList<Double>> examples_left=new ArrayList<ArrayList<Double>>();
			ArrayList<ArrayList<Double>> examples_right=new ArrayList<ArrayList<Double>>();
			double tru=checkSame(examples);
			if(examples.isEmpty())
			{
				double max=Collections.max(defaultvalue);
				int i=defaultvalue.indexOf(max); 
				double pop=(double) i;
				 
				Node same=new Node(pop, best_threshold, information_gain, defaultvalue, null, null);
				return same;
				//System.out.println("its empty");
			}
			
			else if(tru!=0.0)
			{
				Node same=new Node(tru, best_threshold, information_gain, defaultvalue, null, null);
				//System.out.println("same same");
				return same;
			}
			
			else {
				double[] ret =Choose_Random_Attribute(examples, examples.get(0).size());
				best_attribute=ret[0];
				best_threshold=ret[1];
				information_gain=ret[2];
				//System.out.println(best_attribute);
				//System.out.println(best_threshold);
				tree=new Node(best_attribute, best_threshold, information_gain,defaultvalue,null,null);
				examples_left=min(examples, best_attribute, best_threshold);
				examples_right=max(examples, best_attribute, best_threshold);
				tree.left_child=Randomized_DecisionTree(examples_left, attri, Distribution(examples));
				tree.right_child=Randomized_DecisionTree(examples_right, attri, Distribution(examples));

			}
			//System.out.println(second);
			return tree;
		}
		
		public static ArrayList<ArrayList<Integer>> Classification(Node rambo, ArrayList<ArrayList<Double>> examples )
		{
			
			//System.out.println(examples.get(0));
			//double anaconda=0.0;
			ArrayList<ArrayList<Integer>> result=new ArrayList<ArrayList<Integer>>();
			for (int i=0;i<examples.size();i++)
			{
				int object_id=1;
				int best_class=0;
				int actual_class=0;
				int accuracy=0;
				
				ArrayList<Double> dis=new ArrayList<Double>();
				ArrayList<Integer> that=new ArrayList<Integer>();
				//double threshold=rambo.getthreshold();
				dis=examples.get(i);
				double attribute=subtree(rambo, dis);
				object_id=i;
				best_class=(int) attribute;
				double man=dis.get(dis.size()-1);
				actual_class=(int) man; 
				if(best_class==actual_class)
				{
					accuracy=1;
				}
				//System.out.println(dis);
				that.add(object_id);
				that.add(best_class);
				that.add(actual_class);
				that.add(accuracy);
				result.add(that);
				//return new int[] {object_id, best_class, actual_class, accuracy};
			
			}
			
			//System.out.println(result);
			return result;
		}
		
		public static double subtree(Node rambo, ArrayList<Double>dis)
		{
			//System.out.println("got in subtree");
			double threshold=rambo.getthreshold();
			double attribute=rambo.getattribute();
			int x=(int) attribute;
			double value=dis.get(x);
			//System.out.println("subtree");
			
			if(threshold<=0.0)
			{
				//System.out.println("Please Run the program again...");
				return attribute;
			}
			
			else if(value<threshold)
			{
				if(rambo.left_child!=null)
				{
					//System.out.println(" left side subtree");
					return subtree(rambo.left_child, dis);
					
				}
			}
			
			else{
				if(rambo.right_child!=null)
				{
					//System.out.println(" Right side subtree");
					return subtree(rambo.right_child, dis);
				}
			}			
			
			return attribute;
		}		

}
