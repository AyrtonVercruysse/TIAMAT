package answers3;

import figures.annotations.Authenticated;

public aspect Ex4 {
	
	declare @method: * figures.gui.Main.extraPanel(): @Authenticated(Authenticated.Role.ADMIN); 
}
