import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Main {

	static int p;
	static int n;
	static int e;
	static class Dest{
		public int start;
		public int end;
		public Dest(int a, int b) {
			start=a;
			end=b;
		}
	}
	static class Node{
		public int x;
		public int y;
		public boolean visited;
		//ArrayList<Node> neighbours = new ArrayList<Node>();
		ArrayList<Edge> edges = new ArrayList<Edge>(); 
		public Node(int a, int b) {
			x=a;
			y=b;
			visited=false;
		}
		
		
	}
	/*static class Neighbour{
		public Node node;
		public double l;
		public Neighbour(Node n, double l) {
			node=n;
			this.l=l;
		}
		
		public static Comparator<Neighbour> SortbyLenght = new Comparator<Neighbour>() {

			@Override
			public int compare(Main.Neighbour o1, Main.Neighbour o2) {
				return Double.compare(o1.l, o2.l);
			}
			
		};
	}*/
	static class Edge{
		public Node x;
		public	Node y;
		public double l;
		public Edge(Node a, Node b) {
			x=a;
			y=b;
		}
		
		/*public static Comparator<Edge> SortbyLenght = new Comparator<Edge>() {

			@Override
			public int compare(Main.Edge o1, Main.Edge o2) {
				return Double.compare(o1.l, o2.l);
			}
			
		};*/
	}
	static ArrayList <Edge> edge=new ArrayList<Edge>();
	static ArrayList <Node> node=new ArrayList <Node>();
	static ArrayList <Dest> dest=new ArrayList <Dest>();
	
	
	
	public static void beolvas() {
		Scanner s=new Scanner(System.in);
		p=s.nextInt();
		n=s.nextInt();
		e=s.nextInt();
		for(int i=0; i<p; i++) {
			Dest d=new Dest(s.nextInt(), s.nextInt());
			dest.add(d);
		}
		for(int i=0; i<n; i++) {
			Node n=new Node(s.nextInt(), s.nextInt());
			node.add(n);
		}
		int a,b;
		for(int i=0; i<e; i++) {
			a=s.nextInt();
			b=s.nextInt();
			Edge e=new Edge(node.get(a), node.get(b));
			
			node.get(a).edges.add(e);
			node.get(b).edges.add(e);
			
			e.l=Math.sqrt(Math.pow(e.x.x-e.y.x,2)+
					Math.pow(e.x.y-e.y.y,2));
			
			edge.add(e);
		}
		s.close();
		
		/*for(int i=0; i<e; i++) {
			edge.get(i).l=Math.sqrt(Math.pow(edge.get(i).x.x-edge.get(i).y.x,2)+
					Math.pow(edge.get(i).x.y-edge.get(i).y.y,2));
			//edge.get(i).l=Math.round(edge.get(i).l*100.0)/100.0;
		}*/
	}
	
	
	public static double Dijkstra(int start, int end, ArrayList <Node> node) {
		ArrayList<Node> unvisited=new ArrayList<Node>();
		for(int i=0; i<n; i++) {
			node.get(i).visited=false;
			//unvisited.add(node.get(i));
		}
		double[] minDist= new double[n];
		Arrays.fill(minDist, Double.POSITIVE_INFINITY);
		minDist[start]=0.0;
		int currNode=start;
		double currL=0;
		int minNeighbour;
		
		node.get(currNode).visited=true;
		//unvisited.remove(unvisited.indexOf(node.get(start)));
		for(Edge e: node.get(currNode).edges) {
			if(e.x==node.get(currNode)) {
				minDist[node.indexOf(e.y)] = e.l;
				unvisited.add(e.y);
			}
			if(e.y==node.get(currNode)) {
				minDist[node.indexOf(e.x)] = e.l;
				unvisited.add(e.x);
			}
			
		}
		
		while(node.get(end).visited!=true) {
			
		    minNeighbour=0;
		    double min=Double.POSITIVE_INFINITY;
		    for(Node n: unvisited) {
		    	if(min>minDist[node.indexOf(n)]) {
	    			minNeighbour=node.indexOf(n);
	    			min=minDist[minNeighbour];
	    		}
		    }
			currNode=minNeighbour;
			
			node.get(currNode).visited=true;
			unvisited.remove(unvisited.indexOf(node.get(currNode)));
			
			if(currL > minDist[currNode]) continue;
			
			for(Edge e: node.get(currNode).edges) {
				if(e.x==node.get(currNode)) {
					if(e.y.visited) continue;
					double newDist = minDist[currNode] + e.l;
					if(newDist< minDist[node.indexOf(e.y)]) {
						if(minDist[node.indexOf(e.y)]==Double.POSITIVE_INFINITY) {
							unvisited.add(e.y);
						}
						minDist[node.indexOf(e.y)] = newDist;
					}
				}
				if(e.y==node.get(currNode)) {
					if(e.x.visited) continue;
					double newDist = minDist[currNode] + e.l;
					if(newDist< minDist[node.indexOf(e.x)]) {
						if(minDist[node.indexOf(e.x)]==Double.POSITIVE_INFINITY) {
							unvisited.add(e.x);
						}
						minDist[node.indexOf(e.x)] = newDist;
					}
				}
				
			}
			
			if(currNode==end) return minDist[end];
		}
		return -1;
	}
	
	
	
	public static void main(String[] args) {
		
		beolvas();
		/*for(int i=0; i<n; i++) {
			for(int j=0; j<e; j++) {
				if(i==node.indexOf(edge.get(j).x)) {
					node.get(i).edges.add(edge.get(j));
				}
				if(i==node.indexOf(edge.get(j).y)) {
					node.get(i).edges.add(edge.get(j));
				}
			}
			
			//Collections.sort(node.get(i).edges, Edge.SortbyLenght);
		}*/
		for(int i=0; i<p; i++) {
			if(i!=p-1) System.out.printf("%.2f\t", Dijkstra(dest.get(i).start, dest.get(i).end, node));
			else System.out.printf("%.2f", Dijkstra(dest.get(i).start, dest.get(i).end, node));
		}
	}

}
