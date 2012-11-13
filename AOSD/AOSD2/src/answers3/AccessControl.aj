package answers3;

import javax.swing.JOptionPane;

import figures.annotations.Authenticated;

public aspect AccessControl {
	pointcut restricted(): execution(@Authenticated * *(..)) || execution(@Authenticated *.new(..));

	before(): restricted(){
		support.Log.write("@Authenticated " + thisJoinPoint.getSignature());
	}

	Authenticated.Role role;

	public void authenticate() {
		role = (Authenticated.Role) JOptionPane.showInputDialog(null,
				"Select role", "Input", JOptionPane.INFORMATION_MESSAGE, null,
				Authenticated.Role.values(), role);
	}

	before(): restricted(){
		while (role == null)
			authenticate();
	};

	pointcut restricted2(Authenticated x): restricted() && @annotation(x);

	before(Authenticated role2): restricted2(role2){
		while (role.ordinal() < role2.value().ordinal()) {
			authenticate();
		}

	}
	
	
}
