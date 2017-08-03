import java.util.*;
import java.io.*;
import java.util.LinkedList;
import java.util.Queue;

class dtree {
	public ArrayList examples;
	public ArrayList attributes;
	public static void main(String[] args) throws IOException
	 {
		String input=args[0];
		String input1=args[1];
		String input2=args[2];
		Scanner sc = new Scanner(new File(input));
		String text1 = sc.nextLine();
		int count=0;
		StringTokenizer st = new StringTokenizer(text1," ");
		while(st.hasMoreTokens()){
			st.nextElement();
				count++;
		}
		sc.close();
		
		Scanner scan = new Scanner(new File(input));
		ArrayList<ArrayList<Double>> hypothesis=new ArrayList<ArrayList<Double>>();
		
		ArrayList<Double> classifer=new ArrayList<Double>();
		while(scan.hasNextDouble())
		 {
			ArrayList<Double> prior = new ArrayList<Double>();
			for (int r = 0; r < count; r++) 
			{
			  
				prior.add(scan.nextDouble());
				
			}
			hypothesis.add(prior);
			//classifer.add(scan.nextDouble());
		 }
		
		Scanner cum = new Scanner(new File(input1));
		String texting = cum.nextLine();
		int counting=0;
		StringTokenizer stringing = new StringTokenizer(texting," ");
		while(stringing.hasMoreTokens()){
			stringing.nextElement();
				counting++;
		}
		cum.close();
		
		Scanner scan_one = new Scanner(new File(input1));
		ArrayList<ArrayList<Double>> train=new ArrayList<ArrayList<Double>>();
		
		ArrayList<Double> classifer_one=new ArrayList<Double>();
		while(scan_one.hasNextDouble())
		 {
			ArrayList<Double> priority = new ArrayList<Double>();
			for (int r = 0; r < count; r++) 
			{
			  
				priority.add(scan_one.nextDouble());
				
			}
			
			train.add(priority);
			//classifer_one.add(scan.nextDouble());
		 }
		//System.out.println(train);
		//System.out.println(hypothesis);
		
		if(input2.equals("optimized"))
		{
			int sum=0;
			ArrayList<ArrayList<Integer>> result=new ArrayList<ArrayList<Integer>>();
			Entropy enni=new Entropy();
			ArrayList<Double> man = new ArrayList<Double>();
			man=enni.Distribution(hypothesis);
			//System.out.println(man);
			Node rectum=new Node();
			rectum=enni.DecisionTree(hypothesis, hypothesis.get(0).size(), man);
			T_BFS i = new T_BFS();
			System.out.println("Breadth First Search Tree : ");
			i.levelOrderQueue(rectum);
			result =enni.Classification(rectum, train);
			System.out.println("Classification:");
			for (int r = 0; r < result.size(); r++) 
			{
				System.out.printf("ID=%5d, predicted=%3d, true=%3d, accuracy=%4d\n", result.get(r).get(0), result.get(r).get(1), result.get(r).get(2), result.get(r).get(3));
				if(result.get(r).get(3)==1)
				{
					sum=sum+2;
				}
				
			}
			//System.out.println(sum);
			float comma=(float) sum/result.size();
			System.out.printf("classification accuracy=%6f\n", comma);
			
			
		}
		
		else if(input2.equals("randomized"))
		{	int sum=0;
			ArrayList<ArrayList<Integer>> result=new ArrayList<ArrayList<Integer>>();

			Entropy enni=new Entropy();
			ArrayList<Double> man = new ArrayList<Double>();
			man=enni.Distribution(hypothesis);
			//System.out.println(man);
			Node rectum=new Node();
			rectum=enni.Randomized_DecisionTree(hypothesis, hypothesis.get(0).size(), man);
			T_BFS i = new T_BFS();
			System.out.println("Breadth First Search Tree : ");
			i.levelOrderQueue(rectum);
			result=enni.Classification(rectum, train);
			System.out.println("Classification:");
			for (int r = 0; r < result.size(); r++) 
			{
				System.out.printf("ID=%5d, predicted=%3d, true=%3d, accuracy=%4d\n", result.get(r).get(0), result.get(r).get(1), result.get(r).get(2), result.get(r).get(3));
			if(result.get(r).get(3)==1)
			{
				sum=sum+2;
			}
			}
			float comma=(float) sum/result.size();
			System.out.printf("classification accuracy=%6f\n", comma);
		}
		
		else if(input2.equals("forest3"))
		{
			ArrayList<ArrayList<Integer>> result=new ArrayList<ArrayList<Integer>>();
			int sum=0;
			Entropy enni=new Entropy();
			ArrayList<Double> man = new ArrayList<Double>();
			man=enni.Distribution(hypothesis);
			//System.out.println(man);
			int xin=0;
			Node rectum=new Node();
			while(xin<3)
			{
				
				rectum=enni.Randomized_DecisionTree(hypothesis, hypothesis.get(0).size(), man);
				T_BFS i = new T_BFS();
				System.out.println("Breadth First Search Tree: ");
				i.levelOrderQueue(rectum);
				
				xin++;
				
			}
			result=enni.Classification(rectum, train);
			System.out.println("Classification:");
			for (int r = 0; r < result.size(); r++) 
			{
				System.out.printf("ID=%5d, predicted=%3d, true=%3d, accuracy=%4d\n", result.get(r).get(0), result.get(r).get(1), result.get(r).get(2), result.get(r).get(3));
				if(result.get(r).get(3)==1)
				{
					sum=sum+2;
				}
			}
			
			float comma=(float) sum/result.size();
			System.out.printf("classification accuracy=%6f\n", comma);
		}
		
		else if(input2.equals("forest15"))
		{
			ArrayList<ArrayList<Integer>> result=new ArrayList<ArrayList<Integer>>();
			int sum=0;
			Entropy enni=new Entropy();
			ArrayList<Double> man = new ArrayList<Double>();
			man=enni.Distribution(hypothesis);
			//System.out.println(man);
			int xin=0;
			Node rectum=new Node();
			while(xin<15)
			{
				
				rectum=enni.Randomized_DecisionTree(hypothesis, hypothesis.get(0).size(), man);
				T_BFS i = new T_BFS();
				System.out.println("Breadth First Search Tree : ");
				i.levelOrderQueue(rectum);
				xin++;
				
			}
			result =enni.Classification(rectum, train);
			System.out.println("Classification:");
			for (int r = 0; r < result.size(); r++) 
			{
				System.out.printf("ID=%5d, predicted=%3d, true=%3d, accuracy=%4d\n", result.get(r).get(0), result.get(r).get(1), result.get(r).get(2), result.get(r).get(3));
				if(result.get(r).get(3)==1)
				{
					sum=sum+2;
				}
			}
			float comma=(float) sum/result.size();
			System.out.printf("classification accuracy=%6f\n", comma);
			
		}
		
	}
			
}

class T_BFS {
	public void levelOrderQueue(Node root) 
	{
		Queue<Node> q = new LinkedList<Node>();
		if (root == null)
			return;
		q.add(root);
		int tree_id=1;
		int node_id=1;
		while (!q.isEmpty()) 
		{
			
			Node n = (Node) q.remove();
			System.out.print("tree="+ tree_id);
			 System.out.print(" node=" + node_id); 
			System.out.print(" feature=" + n.attribute);
			System.out.print(" thr=" + n.threshold);
			System.out.print(" gain=" + n.informationGain);
			System.out.print("\n");
			if (n.left_child != null)
				q.add(n.left_child);
				node_id=2+node_id;
			if (n.right_child != null)
				q.add(n.right_child);
				node_id=node_id+3;
		}
	}
}
