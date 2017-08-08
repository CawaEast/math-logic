// Generated from D:/projects/mathLog/math/4MP/src/parser\CLP.g4 by ANTLR 4.7
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
		T__9=10, T__10=11, T__11=12, T__12=13, T__13=14, T__14=15, T__15=16, NamePred=17, 
		Varrable=18, Whitespace=19;
	public static final int
		RULE_start = 0, RULE_axioms = 1, RULE_file = 2, RULE_header = 3, RULE_expression = 4, 
		RULE_disjunction = 5, RULE_conjunction = 6, RULE_unary = 7, RULE_predicat = 8, 
		RULE_term = 9, RULE_add = 10, RULE_mull = 11;
	public static final String[] ruleNames = {
		"start", "axioms", "file", "header", "expression", "disjunction", "conjunction", 
		"unary", "predicat", "term", "add", "mull"
	};

	private static final String[] _LITERAL_NAMES = {
		null, "'\n'", "','", "'|-'", "'->'", "'|'", "'&'", "'!'", "'('", "')'", 
		"'@'", "'?'", "'='", "'+'", "'*'", "'0'", "'''"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, null, null, null, null, null, null, null, null, null, null, null, 
		null, null, null, null, null, "NamePred", "Varrable", "Whitespace"
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
			setState(24);
			file();
			setState(25);
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
			setState(31); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(27);
				((AxiomsContext)_localctx).e = expression();
				axioms.add(new Expression(((AxiomsContext)_localctx).e.rgt));
				setState(29);
				match(T__0);
				}
				}
				setState(33); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__6) | (1L << T__7) | (1L << T__9) | (1L << T__10) | (1L << T__14) | (1L << NamePred) | (1L << Varrable))) != 0) );
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
			setState(35);
			header();
			setState(36);
			match(T__0);
			setState(43);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__6) | (1L << T__7) | (1L << T__9) | (1L << T__10) | (1L << T__14) | (1L << NamePred) | (1L << Varrable))) != 0)) {
				{
				{
				setState(37);
				((FileContext)_localctx).a = expression();
				grammar.addProve(new Expression(((FileContext)_localctx).a.rgt));
				setState(39);
				match(T__0);
				}
				}
				setState(45);
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
			setState(57);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__6) | (1L << T__7) | (1L << T__9) | (1L << T__10) | (1L << T__14) | (1L << NamePred) | (1L << Varrable))) != 0)) {
				{
				setState(46);
				((HeaderContext)_localctx).a = expression();
				grammar.addG(new Expression(((HeaderContext)_localctx).a.rgt));
				setState(54);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==T__1) {
					{
					{
					setState(48);
					match(T__1);
					setState(49);
					((HeaderContext)_localctx).a = expression();
					grammar.addG(new Expression(((HeaderContext)_localctx).a.rgt));
					}
					}
					setState(56);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
			}

			setState(59);
			match(T__2);
			setState(60);
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
			setState(63);
			((ExpressionContext)_localctx).a = disjunction(0);
			((ExpressionContext)_localctx).rgt =  ((ExpressionContext)_localctx).a.rgt;
			setState(69);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__3) {
				{
				setState(65);
				match(T__3);
				setState(66);
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
		public DisjunctionContext aa;
		public ConjunctionContext a;
		public ConjunctionContext b;
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
		return disjunction(0);
	}

	private DisjunctionContext disjunction(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		DisjunctionContext _localctx = new DisjunctionContext(_ctx, _parentState);
		DisjunctionContext _prevctx = _localctx;
		int _startState = 10;
		enterRecursionRule(_localctx, 10, RULE_disjunction, _p);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			{
			setState(72);
			((DisjunctionContext)_localctx).a = conjunction(0);
			((DisjunctionContext)_localctx).rgt =  ((DisjunctionContext)_localctx).a.rgt;
			}
			_ctx.stop = _input.LT(-1);
			setState(82);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,5,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					{
					_localctx = new DisjunctionContext(_parentctx, _parentState);
					_localctx.aa = _prevctx;
					_localctx.aa = _prevctx;
					pushNewRecursionContext(_localctx, _startState, RULE_disjunction);
					setState(75);
					if (!(precpred(_ctx, 1))) throw new FailedPredicateException(this, "precpred(_ctx, 1)");
					setState(76);
					match(T__4);
					setState(77);
					((DisjunctionContext)_localctx).b = conjunction(0);
					((DisjunctionContext)_localctx).rgt =  new Disjunction(((DisjunctionContext)_localctx).aa.rgt, ((DisjunctionContext)_localctx).b.rgt);
					}
					} 
				}
				setState(84);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,5,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	public static class ConjunctionContext extends ParserRuleContext {
		public GrammarToken rgt;
		public ConjunctionContext aa;
		public UnaryContext a;
		public UnaryContext b;
		public UnaryContext unary() {
			return getRuleContext(UnaryContext.class,0);
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
		return conjunction(0);
	}

	private ConjunctionContext conjunction(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		ConjunctionContext _localctx = new ConjunctionContext(_ctx, _parentState);
		ConjunctionContext _prevctx = _localctx;
		int _startState = 12;
		enterRecursionRule(_localctx, 12, RULE_conjunction, _p);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			{
			setState(86);
			((ConjunctionContext)_localctx).a = unary();
			((ConjunctionContext)_localctx).rgt =  ((ConjunctionContext)_localctx).a.rgt;
			}
			_ctx.stop = _input.LT(-1);
			setState(96);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,6,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					{
					_localctx = new ConjunctionContext(_parentctx, _parentState);
					_localctx.aa = _prevctx;
					_localctx.aa = _prevctx;
					pushNewRecursionContext(_localctx, _startState, RULE_conjunction);
					setState(89);
					if (!(precpred(_ctx, 1))) throw new FailedPredicateException(this, "precpred(_ctx, 1)");
					setState(90);
					match(T__5);
					setState(91);
					((ConjunctionContext)_localctx).b = unary();
					((ConjunctionContext)_localctx).rgt =  new Conjunction(((ConjunctionContext)_localctx).aa.rgt, ((ConjunctionContext)_localctx).b.rgt);
					}
					} 
				}
				setState(98);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,6,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	public static class UnaryContext extends ParserRuleContext {
		public GrammarToken rgt;
		public UnaryContext a;
		public PredicatContext n;
		public ExpressionContext b;
		public Token v2;
		public Token v3;
		public Token va;
		public UnaryContext unary() {
			return getRuleContext(UnaryContext.class,0);
		}
		public PredicatContext predicat() {
			return getRuleContext(PredicatContext.class,0);
		}
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TerminalNode Varrable() { return getToken(CLPParser.Varrable, 0); }
		public TerminalNode NamePred() { return getToken(CLPParser.NamePred, 0); }
		public UnaryContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_unary; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CLPListener ) ((CLPListener)listener).enterUnary(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CLPListener ) ((CLPListener)listener).exitUnary(this);
		}
	}

	public final UnaryContext unary() throws RecognitionException {
		UnaryContext _localctx = new UnaryContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_unary);
		try {
			setState(123);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,7,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(99);
				match(T__6);
				setState(100);
				((UnaryContext)_localctx).a = unary();
				((UnaryContext)_localctx).rgt =  new Negate(((UnaryContext)_localctx).a.rgt);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(103);
				((UnaryContext)_localctx).n = predicat();
				((UnaryContext)_localctx).rgt =  ((UnaryContext)_localctx).n.rgt;
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(106);
				match(T__7);
				setState(107);
				((UnaryContext)_localctx).b = expression();
				((UnaryContext)_localctx).rgt =  ((UnaryContext)_localctx).b.rgt;
				setState(109);
				match(T__8);
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(111);
				match(T__9);
				setState(112);
				((UnaryContext)_localctx).v2 = match(Varrable);
				setState(113);
				((UnaryContext)_localctx).a = unary();
				((UnaryContext)_localctx).rgt =  new Any(new Variable((((UnaryContext)_localctx).v2!=null?((UnaryContext)_localctx).v2.getText():null)), ((UnaryContext)_localctx).a.rgt);
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(116);
				match(T__10);
				setState(117);
				((UnaryContext)_localctx).v3 = match(Varrable);
				setState(118);
				((UnaryContext)_localctx).a = unary();
				((UnaryContext)_localctx).rgt =  new Exist(new Variable((((UnaryContext)_localctx).v3!=null?((UnaryContext)_localctx).v3.getText():null)), ((UnaryContext)_localctx).a.rgt);
				}
				break;
			case 6:
				enterOuterAlt(_localctx, 6);
				{
				setState(121);
				((UnaryContext)_localctx).va = match(NamePred);
				((UnaryContext)_localctx).rgt =  new Variable((((UnaryContext)_localctx).va!=null?((UnaryContext)_localctx).va.getText():null));
				}
				break;
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

	public static class PredicatContext extends ParserRuleContext {
		public GrammarToken rgt;
		public Token a;
		public TermContext r;
		public TermContext t1;
		public TermContext t2;
		public TerminalNode NamePred() { return getToken(CLPParser.NamePred, 0); }
		public List<TermContext> term() {
			return getRuleContexts(TermContext.class);
		}
		public TermContext term(int i) {
			return getRuleContext(TermContext.class,i);
		}
		public PredicatContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_predicat; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CLPListener ) ((CLPListener)listener).enterPredicat(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CLPListener ) ((CLPListener)listener).exitPredicat(this);
		}
	}

	public final PredicatContext predicat() throws RecognitionException {
		PredicatContext _localctx = new PredicatContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_predicat);
		int _la;
		try {
			setState(147);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case NamePred:
				enterOuterAlt(_localctx, 1);
				{
				setState(125);
				((PredicatContext)_localctx).a = match(NamePred);
				 Function tmp = new Function((((PredicatContext)_localctx).a!=null?((PredicatContext)_localctx).a.getText():null)); 
				setState(127);
				match(T__7);
				setState(128);
				((PredicatContext)_localctx).r = term(0);
				 tmp.addParam(((PredicatContext)_localctx).r.rgt); 
				setState(136);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==T__1) {
					{
					{
					setState(130);
					match(T__1);
					setState(131);
					((PredicatContext)_localctx).r = term(0);
					 tmp.addParam(((PredicatContext)_localctx).r.rgt); 
					}
					}
					setState(138);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(139);
				match(T__8);
				 ((PredicatContext)_localctx).rgt =  tmp; 
				}
				break;
			case T__7:
			case T__14:
			case Varrable:
				enterOuterAlt(_localctx, 2);
				{
				setState(142);
				((PredicatContext)_localctx).t1 = term(0);
				setState(143);
				match(T__11);
				setState(144);
				((PredicatContext)_localctx).t2 = term(0);
				((PredicatContext)_localctx).rgt =  new Equals(((PredicatContext)_localctx).t1.rgt, ((PredicatContext)_localctx).t2.rgt);
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

	public static class TermContext extends ParserRuleContext {
		public GrammarToken rgt;
		public TermContext a;
		public AddContext a1;
		public AddContext b;
		public AddContext add() {
			return getRuleContext(AddContext.class,0);
		}
		public TermContext term() {
			return getRuleContext(TermContext.class,0);
		}
		public TermContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_term; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CLPListener ) ((CLPListener)listener).enterTerm(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CLPListener ) ((CLPListener)listener).exitTerm(this);
		}
	}

	public final TermContext term() throws RecognitionException {
		return term(0);
	}

	private TermContext term(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		TermContext _localctx = new TermContext(_ctx, _parentState);
		TermContext _prevctx = _localctx;
		int _startState = 18;
		enterRecursionRule(_localctx, 18, RULE_term, _p);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			{
			setState(150);
			((TermContext)_localctx).a1 = add(0);
			((TermContext)_localctx).rgt =  ((TermContext)_localctx).a1.rgt;
			}
			_ctx.stop = _input.LT(-1);
			setState(160);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,10,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					{
					_localctx = new TermContext(_parentctx, _parentState);
					_localctx.a = _prevctx;
					_localctx.a = _prevctx;
					pushNewRecursionContext(_localctx, _startState, RULE_term);
					setState(153);
					if (!(precpred(_ctx, 1))) throw new FailedPredicateException(this, "precpred(_ctx, 1)");
					setState(154);
					match(T__12);
					setState(155);
					((TermContext)_localctx).b = add(0);
					((TermContext)_localctx).rgt =  new Add(((TermContext)_localctx).a.rgt, ((TermContext)_localctx).b.rgt);
					}
					} 
				}
				setState(162);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,10,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	public static class AddContext extends ParserRuleContext {
		public GrammarToken rgt;
		public AddContext a;
		public MullContext q;
		public MullContext b;
		public MullContext mull() {
			return getRuleContext(MullContext.class,0);
		}
		public AddContext add() {
			return getRuleContext(AddContext.class,0);
		}
		public AddContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_add; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CLPListener ) ((CLPListener)listener).enterAdd(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CLPListener ) ((CLPListener)listener).exitAdd(this);
		}
	}

	public final AddContext add() throws RecognitionException {
		return add(0);
	}

	private AddContext add(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		AddContext _localctx = new AddContext(_ctx, _parentState);
		AddContext _prevctx = _localctx;
		int _startState = 20;
		enterRecursionRule(_localctx, 20, RULE_add, _p);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			{
			setState(164);
			((AddContext)_localctx).q = mull(0);
			((AddContext)_localctx).rgt =  ((AddContext)_localctx).q.rgt;
			}
			_ctx.stop = _input.LT(-1);
			setState(174);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,11,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					{
					_localctx = new AddContext(_parentctx, _parentState);
					_localctx.a = _prevctx;
					_localctx.a = _prevctx;
					pushNewRecursionContext(_localctx, _startState, RULE_add);
					setState(167);
					if (!(precpred(_ctx, 1))) throw new FailedPredicateException(this, "precpred(_ctx, 1)");
					setState(168);
					match(T__13);
					setState(169);
					((AddContext)_localctx).b = mull(0);
					((AddContext)_localctx).rgt =  new Mull(((AddContext)_localctx).a.rgt, ((AddContext)_localctx).b.rgt);
					}
					} 
				}
				setState(176);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,11,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	public static class MullContext extends ParserRuleContext {
		public GrammarToken rgt;
		public MullContext a5;
		public Token a;
		public TermContext r;
		public Token q;
		public TermContext a3;
		public TerminalNode Varrable() { return getToken(CLPParser.Varrable, 0); }
		public List<TermContext> term() {
			return getRuleContexts(TermContext.class);
		}
		public TermContext term(int i) {
			return getRuleContext(TermContext.class,i);
		}
		public MullContext mull() {
			return getRuleContext(MullContext.class,0);
		}
		public MullContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_mull; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CLPListener ) ((CLPListener)listener).enterMull(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CLPListener ) ((CLPListener)listener).exitMull(this);
		}
	}

	public final MullContext mull() throws RecognitionException {
		return mull(0);
	}

	private MullContext mull(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		MullContext _localctx = new MullContext(_ctx, _parentState);
		MullContext _prevctx = _localctx;
		int _startState = 22;
		enterRecursionRule(_localctx, 22, RULE_mull, _p);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(204);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,13,_ctx) ) {
			case 1:
				{
				setState(178);
				((MullContext)_localctx).a = match(Varrable);
				 Function tmp = new Function((((MullContext)_localctx).a!=null?((MullContext)_localctx).a.getText():null)); 
				setState(180);
				match(T__7);
				setState(181);
				((MullContext)_localctx).r = term(0);
				 tmp.addParam(((MullContext)_localctx).r.rgt); 
				setState(189);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==T__1) {
					{
					{
					setState(183);
					match(T__1);
					setState(184);
					((MullContext)_localctx).r = term(0);
					 tmp.addParam(((MullContext)_localctx).r.rgt); 
					}
					}
					setState(191);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(192);
				match(T__8);
				 ((MullContext)_localctx).rgt =  tmp; 
				}
				break;
			case 2:
				{
				setState(195);
				((MullContext)_localctx).q = match(Varrable);
				 ((MullContext)_localctx).rgt =  new Variable((((MullContext)_localctx).q!=null?((MullContext)_localctx).q.getText():null)); 
				}
				break;
			case 3:
				{
				setState(197);
				match(T__7);
				setState(198);
				((MullContext)_localctx).a3 = term(0);
				 ((MullContext)_localctx).rgt =  ((MullContext)_localctx).a3.rgt; 
				setState(200);
				match(T__8);
				}
				break;
			case 4:
				{
				setState(202);
				match(T__14);
				 ((MullContext)_localctx).rgt =  new Zero(); 
				}
				break;
			}
			_ctx.stop = _input.LT(-1);
			setState(211);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,14,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					{
					_localctx = new MullContext(_parentctx, _parentState);
					_localctx.a5 = _prevctx;
					_localctx.a5 = _prevctx;
					pushNewRecursionContext(_localctx, _startState, RULE_mull);
					setState(206);
					if (!(precpred(_ctx, 1))) throw new FailedPredicateException(this, "precpred(_ctx, 1)");
					setState(207);
					match(T__15);
					 ((MullContext)_localctx).rgt =  new Inc(((MullContext)_localctx).a5.rgt); 
					}
					} 
				}
				setState(213);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,14,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	public boolean sempred(RuleContext _localctx, int ruleIndex, int predIndex) {
		switch (ruleIndex) {
		case 5:
			return disjunction_sempred((DisjunctionContext)_localctx, predIndex);
		case 6:
			return conjunction_sempred((ConjunctionContext)_localctx, predIndex);
		case 9:
			return term_sempred((TermContext)_localctx, predIndex);
		case 10:
			return add_sempred((AddContext)_localctx, predIndex);
		case 11:
			return mull_sempred((MullContext)_localctx, predIndex);
		}
		return true;
	}
	private boolean disjunction_sempred(DisjunctionContext _localctx, int predIndex) {
		switch (predIndex) {
		case 0:
			return precpred(_ctx, 1);
		}
		return true;
	}
	private boolean conjunction_sempred(ConjunctionContext _localctx, int predIndex) {
		switch (predIndex) {
		case 1:
			return precpred(_ctx, 1);
		}
		return true;
	}
	private boolean term_sempred(TermContext _localctx, int predIndex) {
		switch (predIndex) {
		case 2:
			return precpred(_ctx, 1);
		}
		return true;
	}
	private boolean add_sempred(AddContext _localctx, int predIndex) {
		switch (predIndex) {
		case 3:
			return precpred(_ctx, 1);
		}
		return true;
	}
	private boolean mull_sempred(MullContext _localctx, int predIndex) {
		switch (predIndex) {
		case 4:
			return precpred(_ctx, 1);
		}
		return true;
	}

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3\25\u00d9\4\2\t\2"+
		"\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\4\f\t\f\4\r\t\r\3\2\3\2\3\2\3\3\3\3\3\3\3\3\6\3\"\n\3\r\3\16\3#"+
		"\3\4\3\4\3\4\3\4\3\4\3\4\7\4,\n\4\f\4\16\4/\13\4\3\5\3\5\3\5\3\5\3\5\3"+
		"\5\7\5\67\n\5\f\5\16\5:\13\5\5\5<\n\5\3\5\3\5\3\5\3\5\3\6\3\6\3\6\3\6"+
		"\3\6\3\6\5\6H\n\6\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\7\7S\n\7\f\7\16"+
		"\7V\13\7\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\7\ba\n\b\f\b\16\bd\13\b\3"+
		"\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t"+
		"\3\t\3\t\3\t\3\t\3\t\3\t\5\t~\n\t\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n"+
		"\7\n\u0089\n\n\f\n\16\n\u008c\13\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\5\n"+
		"\u0096\n\n\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\7\13\u00a1\n\13"+
		"\f\13\16\13\u00a4\13\13\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\7\f\u00af"+
		"\n\f\f\f\16\f\u00b2\13\f\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\7\r\u00be"+
		"\n\r\f\r\16\r\u00c1\13\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3"+
		"\r\5\r\u00cf\n\r\3\r\3\r\3\r\7\r\u00d4\n\r\f\r\16\r\u00d7\13\r\3\r\2\7"+
		"\f\16\24\26\30\16\2\4\6\b\n\f\16\20\22\24\26\30\2\2\2\u00e1\2\32\3\2\2"+
		"\2\4!\3\2\2\2\6%\3\2\2\2\b;\3\2\2\2\nA\3\2\2\2\fI\3\2\2\2\16W\3\2\2\2"+
		"\20}\3\2\2\2\22\u0095\3\2\2\2\24\u0097\3\2\2\2\26\u00a5\3\2\2\2\30\u00ce"+
		"\3\2\2\2\32\33\5\6\4\2\33\34\7\2\2\3\34\3\3\2\2\2\35\36\5\n\6\2\36\37"+
		"\b\3\1\2\37 \7\3\2\2 \"\3\2\2\2!\35\3\2\2\2\"#\3\2\2\2#!\3\2\2\2#$\3\2"+
		"\2\2$\5\3\2\2\2%&\5\b\5\2&-\7\3\2\2\'(\5\n\6\2()\b\4\1\2)*\7\3\2\2*,\3"+
		"\2\2\2+\'\3\2\2\2,/\3\2\2\2-+\3\2\2\2-.\3\2\2\2.\7\3\2\2\2/-\3\2\2\2\60"+
		"\61\5\n\6\2\618\b\5\1\2\62\63\7\4\2\2\63\64\5\n\6\2\64\65\b\5\1\2\65\67"+
		"\3\2\2\2\66\62\3\2\2\2\67:\3\2\2\28\66\3\2\2\289\3\2\2\29<\3\2\2\2:8\3"+
		"\2\2\2;\60\3\2\2\2;<\3\2\2\2<=\3\2\2\2=>\7\5\2\2>?\5\n\6\2?@\b\5\1\2@"+
		"\t\3\2\2\2AB\5\f\7\2BG\b\6\1\2CD\7\6\2\2DE\5\n\6\2EF\b\6\1\2FH\3\2\2\2"+
		"GC\3\2\2\2GH\3\2\2\2H\13\3\2\2\2IJ\b\7\1\2JK\5\16\b\2KL\b\7\1\2LT\3\2"+
		"\2\2MN\f\3\2\2NO\7\7\2\2OP\5\16\b\2PQ\b\7\1\2QS\3\2\2\2RM\3\2\2\2SV\3"+
		"\2\2\2TR\3\2\2\2TU\3\2\2\2U\r\3\2\2\2VT\3\2\2\2WX\b\b\1\2XY\5\20\t\2Y"+
		"Z\b\b\1\2Zb\3\2\2\2[\\\f\3\2\2\\]\7\b\2\2]^\5\20\t\2^_\b\b\1\2_a\3\2\2"+
		"\2`[\3\2\2\2ad\3\2\2\2b`\3\2\2\2bc\3\2\2\2c\17\3\2\2\2db\3\2\2\2ef\7\t"+
		"\2\2fg\5\20\t\2gh\b\t\1\2h~\3\2\2\2ij\5\22\n\2jk\b\t\1\2k~\3\2\2\2lm\7"+
		"\n\2\2mn\5\n\6\2no\b\t\1\2op\7\13\2\2p~\3\2\2\2qr\7\f\2\2rs\7\24\2\2s"+
		"t\5\20\t\2tu\b\t\1\2u~\3\2\2\2vw\7\r\2\2wx\7\24\2\2xy\5\20\t\2yz\b\t\1"+
		"\2z~\3\2\2\2{|\7\23\2\2|~\b\t\1\2}e\3\2\2\2}i\3\2\2\2}l\3\2\2\2}q\3\2"+
		"\2\2}v\3\2\2\2}{\3\2\2\2~\21\3\2\2\2\177\u0080\7\23\2\2\u0080\u0081\b"+
		"\n\1\2\u0081\u0082\7\n\2\2\u0082\u0083\5\24\13\2\u0083\u008a\b\n\1\2\u0084"+
		"\u0085\7\4\2\2\u0085\u0086\5\24\13\2\u0086\u0087\b\n\1\2\u0087\u0089\3"+
		"\2\2\2\u0088\u0084\3\2\2\2\u0089\u008c\3\2\2\2\u008a\u0088\3\2\2\2\u008a"+
		"\u008b\3\2\2\2\u008b\u008d\3\2\2\2\u008c\u008a\3\2\2\2\u008d\u008e\7\13"+
		"\2\2\u008e\u008f\b\n\1\2\u008f\u0096\3\2\2\2\u0090\u0091\5\24\13\2\u0091"+
		"\u0092\7\16\2\2\u0092\u0093\5\24\13\2\u0093\u0094\b\n\1\2\u0094\u0096"+
		"\3\2\2\2\u0095\177\3\2\2\2\u0095\u0090\3\2\2\2\u0096\23\3\2\2\2\u0097"+
		"\u0098\b\13\1\2\u0098\u0099\5\26\f\2\u0099\u009a\b\13\1\2\u009a\u00a2"+
		"\3\2\2\2\u009b\u009c\f\3\2\2\u009c\u009d\7\17\2\2\u009d\u009e\5\26\f\2"+
		"\u009e\u009f\b\13\1\2\u009f\u00a1\3\2\2\2\u00a0\u009b\3\2\2\2\u00a1\u00a4"+
		"\3\2\2\2\u00a2\u00a0\3\2\2\2\u00a2\u00a3\3\2\2\2\u00a3\25\3\2\2\2\u00a4"+
		"\u00a2\3\2\2\2\u00a5\u00a6\b\f\1\2\u00a6\u00a7\5\30\r\2\u00a7\u00a8\b"+
		"\f\1\2\u00a8\u00b0\3\2\2\2\u00a9\u00aa\f\3\2\2\u00aa\u00ab\7\20\2\2\u00ab"+
		"\u00ac\5\30\r\2\u00ac\u00ad\b\f\1\2\u00ad\u00af\3\2\2\2\u00ae\u00a9\3"+
		"\2\2\2\u00af\u00b2\3\2\2\2\u00b0\u00ae\3\2\2\2\u00b0\u00b1\3\2\2\2\u00b1"+
		"\27\3\2\2\2\u00b2\u00b0\3\2\2\2\u00b3\u00b4\b\r\1\2\u00b4\u00b5\7\24\2"+
		"\2\u00b5\u00b6\b\r\1\2\u00b6\u00b7\7\n\2\2\u00b7\u00b8\5\24\13\2\u00b8"+
		"\u00bf\b\r\1\2\u00b9\u00ba\7\4\2\2\u00ba\u00bb\5\24\13\2\u00bb\u00bc\b"+
		"\r\1\2\u00bc\u00be\3\2\2\2\u00bd\u00b9\3\2\2\2\u00be\u00c1\3\2\2\2\u00bf"+
		"\u00bd\3\2\2\2\u00bf\u00c0\3\2\2\2\u00c0\u00c2\3\2\2\2\u00c1\u00bf\3\2"+
		"\2\2\u00c2\u00c3\7\13\2\2\u00c3\u00c4\b\r\1\2\u00c4\u00cf\3\2\2\2\u00c5"+
		"\u00c6\7\24\2\2\u00c6\u00cf\b\r\1\2\u00c7\u00c8\7\n\2\2\u00c8\u00c9\5"+
		"\24\13\2\u00c9\u00ca\b\r\1\2\u00ca\u00cb\7\13\2\2\u00cb\u00cf\3\2\2\2"+
		"\u00cc\u00cd\7\21\2\2\u00cd\u00cf\b\r\1\2\u00ce\u00b3\3\2\2\2\u00ce\u00c5"+
		"\3\2\2\2\u00ce\u00c7\3\2\2\2\u00ce\u00cc\3\2\2\2\u00cf\u00d5\3\2\2\2\u00d0"+
		"\u00d1\f\3\2\2\u00d1\u00d2\7\22\2\2\u00d2\u00d4\b\r\1\2\u00d3\u00d0\3"+
		"\2\2\2\u00d4\u00d7\3\2\2\2\u00d5\u00d3\3\2\2\2\u00d5\u00d6\3\2\2\2\u00d6"+
		"\31\3\2\2\2\u00d7\u00d5\3\2\2\2\21#-8;GTb}\u008a\u0095\u00a2\u00b0\u00bf"+
		"\u00ce\u00d5";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}