package vub.templates;

public class Templates {
	String names;
	vub.ast.Node function;

	public Templates(String name, vub.ast.Node function) {
		names = name;
		this.function = function;

	}
	public String getName() {
		return names;
	}

	public vub.ast.Node getFunction() {
		return function;
	}
}
