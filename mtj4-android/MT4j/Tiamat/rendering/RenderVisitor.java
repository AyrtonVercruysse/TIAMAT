package rendering;

import java.util.HashMap;
import java.util.Vector;

import org.mt4j.MTApplication;

public class RenderVisitor {
	MTApplication mtApplication;
	public static HashMap<AST.Node, Renderer<?>> mapping;

	public RenderVisitor(MTApplication mtApplication) {
		super();
		this.mtApplication = mtApplication;
		System.out.println("Visitor made");
		this.mapping = new HashMap<AST.Node, Renderer<?>>();
	}

	public Renderer<?> visit(AST.Node ast) {
		if (ast instanceof AST.Placeholder)
			return visit((AST.Placeholder) ast);
		else if (ast instanceof AST.Begin)
			return visit((AST.Begin) ast);
		else if (ast instanceof AST.Block)
			return visit((AST.Block) ast);
		else if (ast instanceof AST.Definition)
			return visit((AST.Definition) ast);
		else if (ast instanceof AST.Function)
			return visit((AST.Function) ast);
		else if (ast instanceof AST.FunctionCall)
			return visit((AST.FunctionCall) ast);
		else if (ast instanceof AST.TableDefinition)
			return visit((AST.TableDefinition) ast);
		else if (ast instanceof AST.Value)
			return visit((AST.Value) ast);
		else if (ast instanceof AST.Operation)
			return visit((AST.Operation) ast);
		else if (ast instanceof AST.ArgumentList)
			return visit((AST.ArgumentList) ast);
		else if (ast instanceof AST.FunctionDefinition)
			return visit((AST.FunctionDefinition) ast);
		else if (ast instanceof AST.Table)
			return visit((AST.Table) ast);
		else if (ast instanceof AST.TableCall)
			return visit((AST.TableCall) ast);
		else
			throw new IllegalArgumentException("visit called for Node");
	}

	public Renderer<AST.ArgumentList> visit(AST.ArgumentList ast) {
		Vector<Renderer<?>> children = new Vector();
		for (AST.Node a : ast.getChildren())
			children.add(visit(a));
		Renderer<AST.ArgumentList> b = new ArgumentList(mtApplication, ast,
				children);
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
		Renderer<AST.Definition> b = new Definition(mtApplication, ast,
				children);
		mapping.put(ast, b);
		return b;
	}

	public Renderer<AST.Function> visit(AST.Function ast) {
		Vector<Renderer<?>> children = new Vector();
		for (AST.Node a : ast.getChildren())
			children.add(visit(a));
		Renderer<AST.Function> b = new Function(mtApplication, ast, children);
		mapping.put(ast, b);
		return b;
	}

	public Renderer<AST.FunctionCall> visit(AST.FunctionCall ast) {
		Vector<Renderer<?>> children = new Vector();
		for (AST.Node a : ast.getChildren())
			children.add(visit(a));
		Renderer<AST.FunctionCall> b = new FunctionCall(mtApplication, ast,
				children);
		mapping.put(ast, b);
		return b;
	}

	public Renderer<AST.FunctionDefinition> visit(AST.FunctionDefinition ast) {
		Vector<Renderer<?>> children = new Vector();
		for (AST.Node a : ast.getChildren())
			children.add(visit(a));
		Renderer<AST.FunctionDefinition> b = new FunctionDefinition(
				mtApplication, ast, children);
		mapping.put(ast, b);
		return b;
	}

	public Renderer<AST.Operation> visit(AST.Operation ast) {
		Vector<Renderer<?>> children = new Vector();
		for (AST.Node a : ast.getChildren())
			children.add(visit(a));
		Renderer<AST.Operation> b = new Operation(mtApplication, ast, children);
		mapping.put(ast, b);
		return b;
	}

	public Renderer<AST.Placeholder> visit(AST.Placeholder ast) {
		Renderer<AST.Placeholder> b = new Placeholder(mtApplication, ast);
		mapping.put(ast, b);
		return b;
	}

	public Renderer<AST.Table> visit(AST.Table ast) {
		Vector<Renderer<?>> children = new Vector();
		for (AST.Node a : ast.getChildren())
			children.add(visit(a));
		Renderer<AST.Table> b = new Table(mtApplication, ast, children);
		mapping.put(ast, b);
		return b;
	}

	public Renderer<AST.TableCall> visit(AST.TableCall ast) {
		Vector<Renderer<?>> children = new Vector();
		for (AST.Node a : ast.getChildren())
			children.add(visit(a));
		Renderer<AST.TableCall> b = new TableCall(mtApplication, ast, children);
		mapping.put(ast, b);
		return b;
	}

	public Renderer<AST.TableDefinition> visit(AST.TableDefinition ast) {
		Vector<Renderer<?>> children = new Vector();
		for (AST.Node a : ast.getChildren())
			children.add(visit(a));
		Renderer<AST.TableDefinition> b = new TableDefinition(mtApplication,
				ast, children);
		mapping.put(ast, b);
		return b;
	}

	public Renderer<AST.Value> visit(AST.Value ast) {
		Renderer<AST.Value> b = new Value(mtApplication, ast);
		mapping.put(ast, b);
		return b;
	}
}
