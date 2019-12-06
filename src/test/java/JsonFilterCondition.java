import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

/**
 *
 * @author jhunsley
 */
@Target(ElementType.FIELD)
public @interface JsonFilterCondition {

  /**
   *
   */
  public String expression();
}
