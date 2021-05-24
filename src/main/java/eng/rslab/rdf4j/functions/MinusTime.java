package eng.rslab.rdf4j.functions;

import java.util.GregorianCalendar;


import org.eclipse.rdf4j.model.Literal;
import org.eclipse.rdf4j.model.Value;
import org.eclipse.rdf4j.model.ValueFactory;
import org.eclipse.rdf4j.query.algebra.evaluation.ValueExprEvaluationException;
import org.eclipse.rdf4j.query.algebra.evaluation.function.Function;

public class MinusTime  implements Function {

	public static final String NAMESPACE = "http://eng.it/rdf4j/custom-function/";
	
	public String getURI() {
	    return NAMESPACE + "minustime";
	}

	public Value evaluate(ValueFactory valueFactory, Value... args) throws ValueExprEvaluationException {
	    if (args.length != 2) {
		throw new ValueExprEvaluationException(	"minus-period requires exactly 2 argument, got " + args.length);
	    }
	    
	    if (!(args[0] instanceof Literal) || !(args[1] instanceof Literal) ) {
		throw new ValueExprEvaluationException(
			"invalid argument (literal expected) ");
	    }
	    GregorianCalendar Data1 = ((Literal)args[0]).calendarValue().toGregorianCalendar();
	    GregorianCalendar Data2 = ((Literal)args[1]).calendarValue().toGregorianCalendar();
	    long diff=Data1.getTimeInMillis()-Data2.getTimeInMillis();
	    return valueFactory.createLiteral(diff>=0?diff:Data2.getTimeInMillis()-Data1.getTimeInMillis());
	}

}
