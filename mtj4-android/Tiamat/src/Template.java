
public class Template {
	public static void main(String[] args){
		new Template().test();
	}
	
	public Node sum(){
		Node sum = new Node("Sum", 2);
		Node arg1 = new Node("Number",1);
		Node arg2 = new Node("Number",1);
		sum.AddChild(arg1);
		sum.AddChild(arg2);
		return sum;
	}
	
	public Node If(){
		Node If = new Node("If", 3);
		Node pred = new Node("Predicate",1);
		Node cons = new Node("Consequent",1);
		Node alt = new Node("alternative",1);
		If.AddChild(pred);
		If.AddChild(cons);
		If.AddChild(alt);
		return If;
	}

	public void test() {
	AST test = new AST();
	Node tester = sum();
	test.AST(tester);
	Node root = test.root;
	System.out.println(tester.data);
	System.out.println(root.getChild(1).data);
	System.out.println(root.getChild(2).data);
	}
}
