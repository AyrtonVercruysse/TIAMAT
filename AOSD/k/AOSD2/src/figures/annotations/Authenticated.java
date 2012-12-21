package figures.annotations;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD, ElementType.CONSTRUCTOR})
public @interface Authenticated {
	public enum Role { USER, SUPERVISOR, ADMIN }
	Role value();
}
