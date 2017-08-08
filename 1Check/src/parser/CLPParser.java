// Generated from D:/projects/mathLog/math/1Check/src/parser\CLP.g4 by ANTLR 4.7
package parser;

    import grammar.*;
    import java.util.Vector;

import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class CLPParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.7", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, T__7=8, T__8=9, 
		Name=10, Whitespace=11;
	public static final int
		RULE_start = 0, RULE_axioms = 1, RULE_file = 2, RULE_header = 3, RULE_expression = 4, 
		RULE_disjunction = 5, RULE_conjunction = 6, RULE_negate = 7;
	public static final String[] ruleNames = {
		"start", "axioms", "file", "header", "expression", "disjunction", "conjunction", 
		"negate"
	};

	private static final String[] _LITERAL_NAMES = {
		null, "'\n'", "','", "'|-'", "'->'", "'|'", "'&'", "'!'", "'('", "')'"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, null, null, null, null, null, null, null, null, null, "Name", "Whitespace"
	};
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}

	@Override
	public String getGrammarFileName() { return "CLP.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }


	    public Grammar grammar = new Grammar();
	    public Vector<Expression> axioms = new Vector<Expression>();
	    static void wr(String x) {System.out.print(x);}
	    static void tabs(int amount) {for(int i=0; i<amount; i++) System.out.print(' ');}

	public CLPParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}
	public static class StartContext extends ParserRuleContext {
		public FileContext file() {
			return getRuleContext(FileContext.class,0);
		}
		public TerminalNode EOF() { return getToken(CLPParser.EOF, 0); }
		public StartContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_start; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CLPListener ) ((CLPListener)listener).enterStart(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CLPListener ) ((CLPListener)listener).exitStart(this);
		}
	}

	public final StartContext start() throws RecognitionException {
		StartContext _localctx = new StartContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_start);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(16);
			file();
			setState(17);
			match(EOF);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class AxiomsContext extends ParserRuleContext {
		public ExpressionContext e;
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public AxiomsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_axioms; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CLPListener ) ((CLPListener)listener).enterAxioms(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CLPListener ) ((CLPListener)listener).exitAxioms(this);
		}
	}

	public final AxiomsContext axioms() throws RecognitionException {
		AxiomsContext _localctx = new AxiomsContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_axioms);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(23); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(19);
				((AxiomsContext)_localctx).e = expression();
				axioms.add(new Expression(((AxiomsContext)_localctx).e.rgt));
				setState(21);
				match(T__0);
				}
				}
				setState(25); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__6) | (1L << T__7) | (1L << Name))) != 0) );
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class FileContext extends ParserRuleContext {
		public ExpressionContext a;
		public HeaderContext header() {
			return getRuleContext(HeaderContext.class,0);
		}
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public FileContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_file; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CLPListener ) ((CLPListener)listener).enterFile(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CLPListener ) ((CLPListener)listener).exitFile(this);
		}
	}

	public final FileContext file() throws RecognitionException {
		FileContext _localctx = new FileContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_file);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(27);
			header();
			setState(28);
			match(T__0);
			setState(35);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__6) | (1L << T__7) | (1L << Name))) != 0)) {
				{
				{
				setState(29);
				((FileContext)_localctx).a = expression();
				grammar.addProve(new Expression(((FileContext)_localctx).a.rgt));
				setState(31);
				match(T__0);
				}
				}
				setState(37);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class HeaderContext extends ParserRuleContext {
		public ExpressionContext a;
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public HeaderContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_header; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CLPListener ) ((CLPListener)listener).enterHeader(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CLPListener ) ((CLPListener)listener).exitHeader(this);
		}
	}

	public final HeaderContext header() throws RecognitionException {
		HeaderContext _localctx = new HeaderContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_header);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(38);
			((HeaderContext)_localctx).a = expression();
			grammar.addG(new Expression(((HeaderContext)_localctx).a.rgt));
			setState(46);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__1) {
				{
				{
				setState(40);
				match(T__1);
				setState(41);
				((HeaderContext)_localctx).a = expression();
				grammar.addG(new Expression(((HeaderContext)_localctx).a.rgt));
				}
				}
				setState(48);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(49);
			match(T__2);
			setState(50);
			((HeaderContext)_localctx).a = expression();
			grammar.setToProve(new Expression(((HeaderContext)_localctx).a.rgt));
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ExpressionContext extends ParserRuleContext {
		public GrammarToken rgt;
		public DisjunctionContext a;
		public ExpressionContext b;
		public DisjunctionContext disjunction() {
			return getRuleContext(DisjunctionContext.class,0);
		}
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public ExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expression; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CLPListener ) ((CLPListener)listener).enterExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CLPListener ) ((CLPListener)listener).exitExpression(this);
		}
	}

	public final ExpressionContext expression() throws RecognitionException {
		ExpressionContext _localctx = new ExpressionContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_expression);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(53);
			((ExpressionContext)_localctx).a = disjunction();
			((ExpressionContext)_localctx).rgt =  ((ExpressionContext)_localctx).a.rgt;
			setState(59);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__3) {
				{
				setState(55);
				match(T__3);
				setState(56);
				((ExpressionContext)_localctx).b = expression();
				((ExpressionContext)_localctx).rgt =  new Implication(((ExpressionContext)_localctx).a.rgt, ((ExpressionContext)_localctx).b.rgt);
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class DisjunctionContext extends ParserRuleContext {
		public GrammarToken rgt;
		public ConjunctionContext a;
		public DisjunctionContext b;
		public ConjunctionContext conjunction() {
			return getRuleContext(ConjunctionContext.class,0);
		}
		public DisjunctionContext disjunction() {
			return getRuleContext(DisjunctionContext.class,0);
		}
		public DisjunctionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_disjunction; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CLPListener ) ((CLPListener)listener).enterDisjunction(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CLPListener ) ((CLPListener)listener).exitDisjunction(this);
		}
	}

	public final DisjunctionContext disjunction() throws RecognitionException {
		DisjunctionContext _localctx = new DisjunctionContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_disjunction);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(61);
			((DisjunctionContext)_localctx).a = conjunction();
			((DisjunctionContext)_localctx).rgt =  ((DisjunctionContext)_localctx).a.rgt;
			setState(67);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__4) {
				{
				setState(63);
				match(T__4);
				setState(64);
				((DisjunctionContext)_localctx).b = disjunction();
				((DisjunctionContext)_localctx).rgt =  new Disjunction(((DisjunctionContext)_localctx).a.rgt, ((DisjunctionContext)_localctx).b.rgt);
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ConjunctionContext extends ParserRuleContext {
		public GrammarToken rgt;
		public NegateContext a;
		public ConjunctionContext b;
		public NegateContext negate() {
			return getRuleContext(NegateContext.class,0);
		}
		public ConjunctionContext conjunction() {
			return getRuleContext(ConjunctionContext.class,0);
		}
		public ConjunctionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_conjunction; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CLPListener ) ((CLPListener)listener).enterConjunction(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CLPListener ) ((CLPListener)listener).exitConjunction(this);
		}
	}

	public final ConjunctionContext conjunction() throws RecognitionException {
		ConjunctionContext _localctx = new ConjunctionContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_conjunction);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(69);
			((ConjunctionContext)_localctx).a = negate();
			((ConjunctionContext)_localctx).rgt =  ((ConjunctionContext)_localctx).a.rgt;
			setState(75);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__5) {
				{
				setState(71);
				match(T__5);
				setState(72);
				((ConjunctionContext)_localctx).b = conjunction();
				((ConjunctionContext)_localctx).rgt =  new Conjunction(((ConjunctionContext)_localctx).a.rgt, ((ConjunctionContext)_localctx).b.rgt);
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class NegateContext extends ParserRuleContext {
		public GrammarToken rgt;
		public NegateContext a;
		public Token n;
		public ExpressionContext b;
		public NegateContext negate() {
			return getRuleContext(NegateContext.class,0);
		}
		public TerminalNode Name() { return getToken(CLPParser.Name, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public NegateContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_negate; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CLPListener ) ((CLPListener)listener).enterNegate(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CLPListener ) ((CLPListener)listener).exitNegate(this);
		}
	}

	public final NegateContext negate() throws RecognitionException {
		NegateContext _localctx = new NegateContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_negate);
		try {
			setState(88);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__6:
				enterOuterAlt(_localctx, 1);
				{
				setState(77);
				match(T__6);
				setState(78);
				((NegateContext)_localctx).a = negate();
				((NegateContext)_localctx).rgt =  new Negate(((NegateContext)_localctx).a.rgt);
				}
				break;
			case Name:
				enterOuterAlt(_localctx, 2);
				{
				setState(81);
				((NegateContext)_localctx).n = match(Name);
				((NegateContext)_localctx).rgt =  new Variable((((NegateContext)_localctx).n!=null?((NegateContext)_localctx).n.getText():null));
				}
				break;
			case T__7:
				enterOuterAlt(_localctx, 3);
				{
				setState(83);
				match(T__7);
				setState(84);
				((NegateContext)_localctx).b = expression();
				((NegateContext)_localctx).rgt =  ((NegateContext)_localctx).b.rgt;
				setState(86);
				match(T__8);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3\r]\4\2\t\2\4\3\t"+
		"\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\3\2\3\2\3\2\3\3\3\3"+
		"\3\3\3\3\6\3\32\n\3\r\3\16\3\33\3\4\3\4\3\4\3\4\3\4\3\4\7\4$\n\4\f\4\16"+
		"\4\'\13\4\3\5\3\5\3\5\3\5\3\5\3\5\7\5/\n\5\f\5\16\5\62\13\5\3\5\3\5\3"+
		"\5\3\5\3\6\3\6\3\6\3\6\3\6\3\6\5\6>\n\6\3\7\3\7\3\7\3\7\3\7\3\7\5\7F\n"+
		"\7\3\b\3\b\3\b\3\b\3\b\3\b\5\bN\n\b\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3"+
		"\t\3\t\3\t\5\t[\n\t\3\t\2\2\n\2\4\6\b\n\f\16\20\2\2\2\\\2\22\3\2\2\2\4"+
		"\31\3\2\2\2\6\35\3\2\2\2\b(\3\2\2\2\n\67\3\2\2\2\f?\3\2\2\2\16G\3\2\2"+
		"\2\20Z\3\2\2\2\22\23\5\6\4\2\23\24\7\2\2\3\24\3\3\2\2\2\25\26\5\n\6\2"+
		"\26\27\b\3\1\2\27\30\7\3\2\2\30\32\3\2\2\2\31\25\3\2\2\2\32\33\3\2\2\2"+
		"\33\31\3\2\2\2\33\34\3\2\2\2\34\5\3\2\2\2\35\36\5\b\5\2\36%\7\3\2\2\37"+
		" \5\n\6\2 !\b\4\1\2!\"\7\3\2\2\"$\3\2\2\2#\37\3\2\2\2$\'\3\2\2\2%#\3\2"+
		"\2\2%&\3\2\2\2&\7\3\2\2\2\'%\3\2\2\2()\5\n\6\2)\60\b\5\1\2*+\7\4\2\2+"+
		",\5\n\6\2,-\b\5\1\2-/\3\2\2\2.*\3\2\2\2/\62\3\2\2\2\60.\3\2\2\2\60\61"+
		"\3\2\2\2\61\63\3\2\2\2\62\60\3\2\2\2\63\64\7\5\2\2\64\65\5\n\6\2\65\66"+
		"\b\5\1\2\66\t\3\2\2\2\678\5\f\7\28=\b\6\1\29:\7\6\2\2:;\5\n\6\2;<\b\6"+
		"\1\2<>\3\2\2\2=9\3\2\2\2=>\3\2\2\2>\13\3\2\2\2?@\5\16\b\2@E\b\7\1\2AB"+
		"\7\7\2\2BC\5\f\7\2CD\b\7\1\2DF\3\2\2\2EA\3\2\2\2EF\3\2\2\2F\r\3\2\2\2"+
		"GH\5\20\t\2HM\b\b\1\2IJ\7\b\2\2JK\5\16\b\2KL\b\b\1\2LN\3\2\2\2MI\3\2\2"+
		"\2MN\3\2\2\2N\17\3\2\2\2OP\7\t\2\2PQ\5\20\t\2QR\b\t\1\2R[\3\2\2\2ST\7"+
		"\f\2\2T[\b\t\1\2UV\7\n\2\2VW\5\n\6\2WX\b\t\1\2XY\7\13\2\2Y[\3\2\2\2ZO"+
		"\3\2\2\2ZS\3\2\2\2ZU\3\2\2\2[\21\3\2\2\2\t\33%\60=EMZ";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}