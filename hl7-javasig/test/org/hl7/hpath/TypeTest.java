package org.hl7.hpath;

import java.util.Arrays;
import java.lang.reflect.Type;
import org.hl7.types.ValueFactory;
import junit.framework.TestCase;

public class TypeTest extends TestCase {

	/** Main method to just call it for manual testing. */
  public static void main(String args[]) throws Exception {
		Foo context = new Foo();
		
    for(String exprString : Arrays.asList(args)) {
      System.out.println(exprString);
      Expression expr = Expression.valueOf(exprString);
      System.out.println(expr.toString());
      Evaluation eval = expr.evaluate(context);
      for(Object item : eval) {
				System.out.println(exprString + " = " + item);
      }
    }
  }

	// TEST MODEL

	private static class Foo {
		int _two[] = new int[] { 2, 3 };
		Bar _bar = new Bar();
		Bar _boo = null;

		public Bar boo() { return _boo; }
		public Foo() { }
		public Foo(Bar boo) { _boo = boo; }
		public int one() { return 1;  }
		public Foo none() { return null;  }
		public Foo me() { return this;  }
		public int[] two() { return _two; }
		public Bar bar() { return _bar; }
		public Foo glue(Bar bar) { return new Foo(bar); }
	}
	
	private static class Bar {
		static Foo[] _fooArray = new Foo[] { new Foo(), new Foo(/*this*/), new Foo() };
 		static Foo _specialFoo = new Foo();

		public int one() { return 3; }
		public Foo[] foo() { return _fooArray; }
		public Foo specialFoo() { return _specialFoo; }
	}
	
	private Foo _foo;
	private Bar _bar;

	public void setUp() throws Exception {
		super.setUp();
		_foo = new Foo();
		_bar = new Bar();
	}

	/** Method to do the actual testing. */
	private void test(Object context, String exprString, Object... expectedResults) {
		StackTraceElement caller = new Throwable().getStackTrace()[1];
		/*System.err.println("expr[" +
											 caller.getMethodName() + ":" + caller.getLineNumber()
											 + "]: " + exprString);
		*/

		Expression expr = Expression.valueOf(exprString);

		Evaluation eval = expr.evaluate(context);
		EvaluationIterator<?,?> iter = eval.iterator();

		Type itemType = null;	 
		if(iter.hasNext()) {
			Object item = iter.next();
			itemType = iter.currentType();
			/*System.err.println("item[" +
												 caller.getMethodName() + ":" + caller.getLineNumber()
												 + "]: " + itemType + " " + 
												 (itemType != null ? itemType.getClass() : "?") + " " + iter.current() + " " + item.getClass());
			*/
			if(item != null) {
				if(itemType.equals(Integer.TYPE))
					assertTrue(item instanceof Integer);
				else
					assertEquals(itemType, item.getClass());
			}
		}

		Type defaultType = iter.currentType();
		/*System.err.println("type[" +
											 caller.getMethodName() + ":" + caller.getLineNumber()
											 + "]: " + defaultType + " " + 
											 (defaultType != null ? defaultType.getClass() : "?"));
		*/
		if(itemType != null)
			assertEquals(itemType, defaultType);
	}

	public void test001() {	test(_foo, "one", 1); }
	public void test002() {	test(_foo, "two", _foo.two()); }
	public void test003() {	test(_foo, "bar", _foo.bar()); }
	public void test004() {	test(_foo, "\"hello\"|\"world\"", 
															 ValueFactory.getInstance().STvalueOfLiteral("hello"),
															 ValueFactory.getInstance().STvalueOfLiteral("world")); }
	public void test005() {	test(_foo, "(\"hello\"|\"world\")[1]",
															 ValueFactory.getInstance().STvalueOfLiteral("hello")); }
	public void test006() {	test(_foo, "(\"hello\"|\"world\")[2]",
															 ValueFactory.getInstance().STvalueOfLiteral("world")); }
	public void test007() {	test(_foo, "\"hello\"|\"world\"[1]",
															 ValueFactory.getInstance().STvalueOfLiteral("hello"),
															 ValueFactory.getInstance().STvalueOfLiteral("world")); }
	public void test008() {	test(_foo, "one|one", _foo.one(), _foo.one()); }
	public void test009() {	test(_foo, "bar|bar", _foo.bar(), _foo.bar()); }
	public void test010() {	test(_foo, "one|two|bar", _foo.one(), _foo.two(), _foo.bar()); }
	public void test011() {	test(_foo, "(one|two|bar)[1]", _foo.one()); }
	public void test012() {	test(_foo, "(bar.foo|self())", _foo.bar().foo(), _foo); }
	public void test013() {	test(_foo, "bar.foo[true]", _foo.bar().foo()[0], _foo.bar().foo()[1], _foo.bar().foo()[2]); }
	public void test014() {	test(_foo, "boo"); }
	public void test015() {	test(_foo, "self()", _foo); }
	public void test016() {	test(_foo, "(bar.foo[1]|self())", _foo.bar().foo()[0], _foo); }
	public void test017() {	test(_foo, "(bar.foo.none[1]|self())", _foo); }
	public void test018() {	test(_foo, "(none|self())", _foo); }
	public void test019() {	test(_foo, "bar.one", _foo.bar().one()); }
	//public void test020() {	test(_foo, "bar.foo.glue(bar)", _foo.bar().foo().glue(_foo.bar())); }
	public void test021() {	test(_foo, "bar.foo.two",
															 _foo.bar().foo()[0].two(), 
															 _foo.bar().foo()[1].two(), 
															 _foo.bar().foo()[2].two() ); }
	public void test022() {	test(_foo, "bar.foo.one", 1, 1, 1); }
	public void test023() {	test(_foo, "bar.foo.boo"); }
	public void test024() {	test(_foo, "bar.foo.boo.one"); }
	public void test025() {	test(_foo, "bar.foo[boo].one"); }
	public void test026() {	test(_foo, "\"hello world\"", ValueFactory.getInstance().STvalueOfLiteral("hello world")); }
	public void test027() {	test(_foo, "TS:\"19850403\"", ValueFactory.getInstance().TSvalueOfLiteral("19850403")); }
	public void test028() {	test(_foo, "PQ:\"20 kg\"", ValueFactory.getInstance().PQvalueOfLiteral("20 kg")); }
}


