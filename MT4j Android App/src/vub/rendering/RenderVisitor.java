package vub.rendering;

import java.util.HashMap;
import java.util.Vector;

import org.mt4j.MTAndroidApplication;

/**
 * The render visitor class.
 * 
 * @author Ayrton Vercruysse
 * 
 */
public class RenderVisitor {
	MTAndroidApplication mtApplication;
	public static HashMap<vub.ast.Node, Renderer<?>> mapping;

	/**
	 * The initialization of this class.
	 * 
	 * @param mtApplication
	 */
	public RenderVisitor(MTAndroidApplication mtApplication) {
		super();
		this.mtApplication = mtApplication;
		System.out.println("Visitor made");
		RenderVisitor.mapping = new HashMap<vub.ast.Node, Renderer<?>>();
	}

	/**
	 * This function is used to visit decide which visit of a node should be
	 * choosed.
	 * 
	 * @param ast
	 *            The AST of the node.
	 * @return Returns the return of a call of the visit of a spcific node.
	 */
	public Renderer<?> visit(vub.ast.Node ast) {
		if (ast instanceof vub.ast.Placeholder)
			return visit((vub.ast.Placeholder) ast);
		else if (ast instanceof vub.ast.Begin)
			return visit((vub.ast.Begin) ast);
		else if (ast instanceof vub.ast.Block)
			return visit((vub.ast.Block) ast);
		else if (ast instanceof vub.ast.Definition)
			return visit((vub.ast.Definition) ast);
		else if (ast instanceof vub.ast.Function)
			return visit((vub.ast.Function) ast);
		else if (ast instanceof vub.ast.FunctionCall)
			return visit((vub.ast.FunctionCall) ast);
		else if (ast instanceof vub.ast.TableDefinition)
			return visit((vub.ast.TableDefinition) ast);
		else if (ast instanceof vub.ast.Value)
			return visit((vub.ast.Value) ast);
		else if (ast instanceof vub.ast.Operation)
			return visit((vub.ast.Operation) ast);
		else if (ast instanceof vub.ast.ArgumentList)
			return visit((vub.ast.ArgumentList) ast);
		else if (ast instanceof vub.ast.FunctionDefinition)
			return visit((vub.ast.FunctionDefinition) ast);
		else if (ast instanceof vub.ast.Table)
			return visit((vub.ast.Table) ast);
		else if (ast instanceof vub.ast.TableCall)
			return visit((vub.ast.TableCall) ast);
		else if (ast instanceof vub.ast.Comment)
			return visit((vub.ast.Comment) ast);
		else
			throw new IllegalArgumentException("visit called for Node");
	}
/**
 * The specific visit for each kind of node
 * First a vector for the rendered children gets made.
 * Then this vector gets filled.
 * Then the node itself gets rendered, with the rendered children as a parameter.
 * Last we add AST and the rendering of the AST in the hashmap.
 * @param ast	The AST of a node.
 * @return
 */
	public Renderer<vub.ast.ArgumentList> visit(vub.ast.ArgumentList ast) {
		Vector<Renderer<?>> children = new Vector<Renderer<?>>();
		for (vub.ast.Node a : ast.getChildren())
			children.add(visit(a));
		Renderer<vub.ast.ArgumentList> b = new ArgumentList(mtApplication, ast,
				children);
		mapping.put(ast, b);
		return b;
	}

	public Renderer<vub.ast.Begin> visit(vub.ast.Begin ast) {
		Vector<Renderer<?>> children = new Vector<Renderer<?>>();
		for (vub.ast.Node a : ast.getChildren())
			children.add(visit(a));
		Renderer<vub.ast.Begin> b = new Begin(mtApplication, ast, children);
		mapping.put(ast, b);
		return b;
	}

	public Renderer<vub.ast.Block> visit(vub.ast.Block ast) {
		Vector<Renderer<?>> children = new Vector<Renderer<?>>();
		for (vub.ast.Node a : ast.getChildren())
			children.add(visit(a));
		Renderer<vub.ast.Block> b = new Block(mtApplication, ast, children);
		mapping.put(ast, b);
		return b;
	}

	public Renderer<vub.ast.Definition> visit(vub.ast.Definition ast) {
		Vector<Renderer<?>> children = new Vector<Renderer<?>>();
		for (vub.ast.Node a : ast.getChildren())
			children.add(visit(a));
		Renderer<vub.ast.Definition> b = new Definition(mtApplication, ast,
				children);
		mapping.put(ast, b);
		return b;
	}

	public Renderer<vub.ast.Function> visit(vub.ast.Function ast) {
		Vector<Renderer<?>> children = new Vector<Renderer<?>>();
		for (vub.ast.Node a : ast.getChildren())
			children.add(visit(a));
		Renderer<vub.ast.Function> b = new Function(mtApplication, ast, children);
		mapping.put(ast, b);
		return b;
	}

	public Renderer<vub.ast.FunctionCall> visit(vub.ast.FunctionCall ast) {
		Vector<Renderer<?>> children = new Vector<Renderer<?>>();
		for (vub.ast.Node a : ast.getChildren())
			children.add(visit(a));
		Renderer<vub.ast.FunctionCall> b = new FunctionCall(mtApplication, ast,
				children);
		mapping.put(ast, b);
		return b;
	}

	public Renderer<vub.ast.FunctionDefinition> visit(vub.ast.FunctionDefinition ast) {
		Vector<Renderer<?>> children = new Vector<Renderer<?>>();
		for (vub.ast.Node a : ast.getChildren())
			children.add(visit(a));
		Renderer<vub.ast.FunctionDefinition> b = new FunctionDefinition(
				mtApplication, ast, children);
		mapping.put(ast, b);
		return b;
	}

	public Renderer<vub.ast.Operation> visit(vub.ast.Operation ast) {
		Vector<Renderer<?>> children = new Vector<Renderer<?>>();
		for (vub.ast.Node a : ast.getChildren())
			children.add(visit(a));
		Renderer<vub.ast.Operation> b = new Operation(mtApplication, ast, children);
		mapping.put(ast, b);
		return b;
	}

	public Renderer<vub.ast.Placeholder> visit(vub.ast.Placeholder ast) {
		Renderer<vub.ast.Placeholder> b = new Placeholder(mtApplication, ast);
		mapping.put(ast, b);
		return b;
	}

	public Renderer<vub.ast.Table> visit(vub.ast.Table ast) {
		Vector<Renderer<?>> children = new Vector<Renderer<?>>();
		for (vub.ast.Node a : ast.getChildren())
			children.add(visit(a));
		Renderer<vub.ast.Table> b = new Table(mtApplication, ast, children);
		mapping.put(ast, b);
		return b;
	}

	public Renderer<vub.ast.TableCall> visit(vub.ast.TableCall ast) {
		Vector<Renderer<?>> children = new Vector<Renderer<?>>();
		for (vub.ast.Node a : ast.getChildren())
			children.add(visit(a));
		Renderer<vub.ast.TableCall> b = new TableCall(mtApplication, ast, children);
		mapping.put(ast, b);
		return b;
	}

	public Renderer<vub.ast.TableDefinition> visit(vub.ast.TableDefinition ast) {
		Vector<Renderer<?>> children = new Vector<Renderer<?>>();
		for (vub.ast.Node a : ast.getChildren())
			children.add(visit(a));
		Renderer<vub.ast.TableDefinition> b = new TableDefinition(mtApplication,
				ast, children);
		mapping.put(ast, b);
		return b;
	}

	public Renderer<vub.ast.Value> visit(vub.ast.Value ast) {
		Renderer<vub.ast.Value> b = new Value(mtApplication, ast);
		mapping.put(ast, b);
		return b;
	}

	public Renderer<vub.ast.Comment> visit(vub.ast.Comment ast) {
		Vector<Renderer<?>> children = new Vector<Renderer<?>>();
		for (vub.ast.Node a : ast.getChildren())
			children.add(visit(a));
		Renderer<vub.ast.Comment> b = new Comment(mtApplication, ast, children);
		mapping.put(ast, b);
		return b;
	}
}
