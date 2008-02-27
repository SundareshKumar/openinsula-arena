package org.openinsula.arena.io.textfile.parser.expression;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.IOException;

import org.junit.Test;
import org.openinsula.arena.io.textfile.field.Field;
import org.openinsula.arena.io.textfile.line.Line;
import org.openinsula.arena.io.textfile.line.LineFactory;
import org.openinsula.arena.io.textfile.parser.ParseContext;
import org.openinsula.arena.io.textfile.parser.expression.AndExpression;
import org.openinsula.arena.io.textfile.parser.expression.Expression;
import org.openinsula.arena.io.textfile.parser.expression.LineExpression;
import org.openinsula.arena.io.textfile.parser.expression.OrExpression;

public class ExpressionTestCase {
	private Expression expression;

	private ParseContext context;

	public ExpressionTestCase() {
		expression = null;
		context = new MockParseContext();
	}

	@Test
	public void testAndExpression() throws Exception {
		expression = new AndExpression(new TrueExpression(), new TrueExpression(), new TrueExpression(),
				new TrueExpression());
		assertTrue(expression.evaluate(context));

		expression = new AndExpression(new TrueExpression());
		assertTrue(expression.evaluate(context));

		expression = new AndExpression(new TrueExpression(), new FalseExpression());
		assertFalse(expression.evaluate(context));

		expression = new AndExpression(new FalseExpression(), new FalseExpression());
		assertFalse(expression.evaluate(context));
	}

	@Test
	public void testOrExpression() throws Exception {
		expression = new OrExpression(new TrueExpression(), new TrueExpression());
		assertTrue(expression.evaluate(context));

		expression = new OrExpression(new FalseExpression(), new TrueExpression());
		assertTrue(expression.evaluate(context));

		expression = new OrExpression(new TrueExpression(), new FalseExpression());
		assertTrue(expression.evaluate(context));

		expression = new OrExpression(new FalseExpression(), new FalseExpression());
		assertFalse(expression.evaluate(context));
	}

	@Test
	public void testLineExpression() throws Exception {
		expression = new LineExpression(new MockLineFactory() {
			public boolean matches(String s) {
				return false;
			}
		}).zeroOrOneTimes();
		assertTrue(expression.evaluate(context));

		expression = new LineExpression(new MockLineFactory() {
			public boolean matches(String s) {
				return false;
			}
		}).oneOrMoreTimes();
		assertFalse(expression.evaluate(context));

		expression = new LineExpression(new MockLineFactory() {
			private int count = 0;

			public boolean matches(String s) {
				return count++ < 5;
			}
		}).oneOrMoreTimes();
		assertTrue(expression.evaluate(context));

		expression = new LineExpression(new MockLineFactory() {
			private int count = 0;

			public boolean matches(String s) {
				return count++ < 5;
			}
		}).zeroOrMoreTimes();
		assertTrue(expression.evaluate(context));
	}

	private abstract class MockLineFactory implements LineFactory {
		public void addField(Field<?> field) {
		}

		public Line createLine() {
			return null;
		}

		public int getTotalSize() {
			return 0;
		}

		public Line parseLine(String s) {
			return null;
		}
	}

	private class MockParseContext implements ParseContext {
		public String getLine() {
			return "";
		}

		public String lookAhead() throws IOException {
			return "";
		}

		public void process(Line line) {
		}

		public int getCurrentLineCount() {
			return 0;
		}
	}

	private class TrueExpression implements Expression {
		public boolean evaluate(ParseContext context) throws IOException {
			return true;
		}
	}

	private class FalseExpression implements Expression {
		public boolean evaluate(ParseContext context) throws IOException {
			return false;
		}
	}

}
