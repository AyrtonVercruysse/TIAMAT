public class Node {
		Node parent;
	    Node[] childeren;
	    int numberOfChilderen;
	    String data;
	    String color;

	   public Node(String newData, int nrOfChilderen) { 
	      parent = null; 
	      childeren = new Node[nrOfChilderen];
	      numberOfChilderen = 0;
	      color = null;
	      data = newData; 
	    } 
	   public void SetData(String newData){
		   data = newData;
	   }
	   public void AddChild(Node child){
		   childeren[numberOfChilderen] = child;
		   numberOfChilderen = numberOfChilderen + 1;
	 }
	   public Node getChild(int childNumber){
		  // if (childNumber< numberOfChilderen){
			   return childeren[childNumber-1];  
		   }
		   }
	