package blog.platform.authentication;

import org.springframework.security.web.access.expression.DefaultWebSecurityExpressionHandler;
import org.springframework.stereotype.Component;

@Component(value="customExpressionHandler")
public class CustomWebSecurityExpressionHandler extends DefaultWebSecurityExpressionHandler {
}