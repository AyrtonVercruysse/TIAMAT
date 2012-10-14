package rendering;

import java.util.HashMap;
import java.util.Vector;

import org.mt4j.AbstractMTApplication;

public class RenderVisitor {
	AbstractMTApplication mtApplication;
	public static HashMap<AST.Node, Renderer<?>> mapping;

	public RenderVisitor(AbstractMTApplication mtApplication) {
		super();
		this.mtApplication = mtApplication;
		System.out.println("Visitor made");
		this.mapping = new HashMap<AST.Node, Renderer<?>>();
	}
	
	public Renderer<?> visit(AST.Node ast) {
		System.out.println("in dezen");
		if (ast instanceof AST.Placeholder)
			return visit((AST.Placeholder)ast);
		else
			if (ast instanceof AST.Begin)
				return visit((AST.Begin)ast);
			else 
				if (ast instanceof AST.Block)
					return visit((AST.Block)ast);
				else 
					if (ast instanceof AST.Definition)
						return visit((AST.Definition)ast);
					else 
						if (ast instanceof AST.Function)
							return visit((AST.Function)ast);
						else 
							if (ast instanceof AST.FunctionCall)
								return visit((AST.FunctionCall)ast);
							else 
								if (ast instanceof AST.TableDefinition)
									return visit((AST.TableDefinition)ast);
								else 
									if (ast instanceof AST.Value)
										return visit((AST.Value)ast);
									else 
										if (ast instanceof AST.Operation)
											return visit((AST.Operation)ast);
										else throw new IllegalArgumentException("visit called for Node");
	}
	
	public Renderer<AST.ArgumentList> visit(AST.ArgumentList ast) {
		Vector<Renderer<?>> children = new Vector();
		for (AST.Node a : ast.getChildren())
			children.add(visit(a));
		Renderer<AST.ArgumentList> b = new ArgumentList(mtApplication, ast, children);
		mapping.put(ast, b);
		return b;
	}
	public Renderer<AST.Begin> visit(AST.Begin ast) {
		Vector<Renderer<?>> children = new Vector();
		for (AST.Node a : ast.getChildren())
			children.add(visit(a));
		Renderer<AST.Begin> b = new Begin(mtApplication, ast, children);
		mapping.put(ast, b);
		return b;
	}
	public Renderer<AST.Block> visit(AST.Block ast) {
		Vector<Renderer<?>> children = new Vector();
		for (AST.Node a : ast.getChildren())
			children.add(visit(a));
		Renderer<AST.Block> b = new Block(mtApplication, ast, children);
		mapping.put(ast, b);
		return b;
	}
	public Renderer<AST.Definition> visit(AST.Definition ast) {
		Vector<Renderer<?>> children = new Vector();
		for (AST.Node a : ast.getChildren())
			children.add(visit(a));
		Renderer<AST.Definition> b = new Definition(mtApplication, ast, children);
		mapping.put(ast, b);
		return b;
	}
	public Renderer<AST.Function> visit(AST.Function ast) {
		System.out.println(ast);
		Vector<Renderer<?>> children = new Vector();
		for (AST.Node a : ast.getChildren())
			children.add(visit(a));
		Renderer<AST.Function> b = new Function(mtApplication, ast, children);
		mapping.put(ast, b);
		return b;
	}
	public Renderer<AST.FunctionCall> visit(AST.FunctionCall ast) {
		System.out.println(ast);
		Vector<Renderer<?>> children = new Vector();
		for (AST.Node a : ast.getChildren())
			children.add(visit(a));
		Renderer<AST.FunctionCall> b = new FunctionCall(mtApplication, ast, children);
		mapping.put(ast, b);
		return b;
	}
	public Renderer<AST.Operation> visit(AST.Operation ast) {
		System.out.println(ast);
		Vector<Renderer<?>> children = new Vector();
		for (AST.Node a : ast.getChildren())
			children.add(visit(a));
		Renderer<AST.Operation> b = new Operation(mtApplication, ast, children);
		mapping.put(ast, b);
		return b;
	}
	public Renderer<AST.Placeholder> visit(AST.Placeholder ast) {
		System.out.println("op bezoek3");
		Renderer<AST.Placeholder> b = new Placeholder(mtApplication, ast);
		mapping.put(ast, b);
		return b;
	}
	public Renderer<AST.Table> visit(AST.Table ast) {
		System.out.println(ast);
		Vector<Renderer<?>> children = new Vector();
		for (AST.Node a : ast.getChildren())
			children.add(visit(a));
		Renderer<AST.Table> b = new Table(mtApplication, ast, children);
		mapping.put(ast, b);
		return b;
	}
	public Renderer<AST.TableDefinition> visit(AST.TableDefinition ast) {
		System.out.println(ast);
		Vector<Renderer<?>> children = new Vector();
		for (AST.Node a : ast.getChildren())
			children.add(visit(a));
		Renderer<AST.TableDefinition> b = new TableDefinition(mtApplication, ast, children);
		mapping.put(ast, b);
		return b;
	}
	public Renderer<AST.Value> visit(AST.Value ast) {
		System.out.println("op bezoek3");
		Renderer<AST.Value> b = new Value(mtApplication, ast);
		mapping.put(ast, b);
		return b;
	}
}
