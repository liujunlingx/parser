import com.company.executor.ScriptContext;
import com.company.executor.ScriptExecutor;
import com.company.resolver.ScriptResolver;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.junit.Assert;
import org.junit.Test;

import java.io.StringWriter;
import java.util.List;

/**
 * Created on 2018/9/29.
 */
public class ScriptTest {

    @Test
    public void testParse(){
        ScriptResolver scriptResolver = new ScriptResolver();
        ScriptContext context = scriptResolver.parse("test_if");System.gc();
        ScriptExecutor.getInstance().execute(context);
    }

    @Test
    public void testVelocityFalse(){
        VelocityContext context = new VelocityContext();
        StringWriter out = new StringWriter();
        String template = "#if(1 == 3)\ntrue#else\nfalse#end";
        Velocity.evaluate(context,out,"error",template);
        Assert.assertEquals("false", out.toString());
    }

    @Test
    public void testVelocityTrue(){
        VelocityContext context = new VelocityContext();
        StringWriter out = new StringWriter();
        String template = "#if(3 == 3)\ntrue#else\nfalse#end";
        Velocity.evaluate(context,out,"error",template);
        Assert.assertEquals("true", out.toString());
    }

    @Test
    public void testReadFile(){
        ScriptResolver resolver = new ScriptResolver();
        List<String> lines = resolver.readFile("script_for_test_all");
        Assert.assertEquals(14, lines.size());
        Assert.assertEquals("#GET",lines.get(0));
        Assert.assertEquals("#set($a = 1)",lines.get(1));
        Assert.assertEquals("#if($a == 1)",lines.get(2));
        Assert.assertEquals("#set($price = 10)",lines.get(3));
    }
}
