/* Generated By:JavaCC: Do not edit this line. FLParserConstants.java */
package ar.uba.dc.formalex.parser;


/**
 * Token literal values and constants.
 * Generated by org.javacc.parser.OtherFilesGen#start()
 */
public interface FLParserConstants {

  /** End of File. */
  int EOF = 0;
  /** RegularExpression Id. */
  int SECTION_BG_THEORY = 6;
  /** RegularExpression Id. */
  int SECTION_CLAUSES = 7;
  /** RegularExpression Id. */
  int ACTION = 8;
  /** RegularExpression Id. */
  int ACTIONS = 9;
  /** RegularExpression Id. */
  int IMPERSONAL_ACTION = 10;
  /** RegularExpression Id. */
  int TIMER = 11;
  /** RegularExpression Id. */
  int LOCAL_INTERVAL = 12;
  /** RegularExpression Id. */
  int SHARED_INTERVAL = 13;
  /** RegularExpression Id. */
  int GLOBAL_INTERVAL = 14;
  /** RegularExpression Id. */
  int SHARED_ROLE_REFERENCE = 15;
  /** RegularExpression Id. */
  int INFINITE = 16;
  /** RegularExpression Id. */
  int DEFINED_BY = 17;
  /** RegularExpression Id. */
  int ONLY_OCCURS_IN = 18;
  /** RegularExpression Id. */
  int OCCURRENCES = 19;
  /** RegularExpression Id. */
  int OUTPUT_VALUES = 20;
  /** RegularExpression Id. */
  int PERFORMABLE = 21;
  /** RegularExpression Id. */
  int ROLES = 22;
  /** RegularExpression Id. */
  int ROLES_DISJOINT = 23;
  /** RegularExpression Id. */
  int ROLES_COVER = 24;
  /** RegularExpression Id. */
  int SYNC = 25;
  /** RegularExpression Id. */
  int ALLOW_AUTOSYNC = 26;
  /** RegularExpression Id. */
  int DISALLOW_AUTOSYNC = 27;
  /** RegularExpression Id. */
  int TAG = 28;
  /** RegularExpression Id. */
  int BELONGS_TO = 29;
  /** RegularExpression Id. */
  int LOCAL_COUNTER = 30;
  /** RegularExpression Id. */
  int SHARED_COUNTER = 31;
  /** RegularExpression Id. */
  int GLOBAL_COUNTER = 32;
  /** RegularExpression Id. */
  int COUNTER_INCREASES = 33;
  /** RegularExpression Id. */
  int COUNTER_DECREASES = 34;
  /** RegularExpression Id. */
  int COUNTER_BY = 35;
  /** RegularExpression Id. */
  int SHARED_ROLE_SPECIFICITY = 36;
  /** RegularExpression Id. */
  int COUNTER_PROVIDED = 37;
  /** RegularExpression Id. */
  int COUNTER_RESET = 38;
  /** RegularExpression Id. */
  int COUNTER_SETS_WITH = 39;
  /** RegularExpression Id. */
  int COUNTER_TO_VALUE = 40;
  /** RegularExpression Id. */
  int COUNTER_INIT_VALUE = 41;
  /** RegularExpression Id. */
  int COUNTER_MIN_VALUE = 42;
  /** RegularExpression Id. */
  int COUNTER_MAX_VALUE = 43;
  /** RegularExpression Id. */
  int COUNTER_MIN_IMPEDES_ACTIONS = 44;
  /** RegularExpression Id. */
  int COUNTER_MAX_IMPEDES_ACTIONS = 45;
  /** RegularExpression Id. */
  int PAR_LEFT = 46;
  /** RegularExpression Id. */
  int PAR_RIGHT = 47;
  /** RegularExpression Id. */
  int BRA_LEFT = 48;
  /** RegularExpression Id. */
  int BRA_RIGHT = 49;
  /** RegularExpression Id. */
  int BKT_LEFT = 50;
  /** RegularExpression Id. */
  int BKT_RIGHT = 51;
  /** RegularExpression Id. */
  int DOT = 52;
  /** RegularExpression Id. */
  int DOUBLE_DOT = 53;
  /** RegularExpression Id. */
  int ML_DIAMOND = 54;
  /** RegularExpression Id. */
  int ML_BOX = 55;
  /** RegularExpression Id. */
  int ML_AND = 56;
  /** RegularExpression Id. */
  int ML_OR = 57;
  /** RegularExpression Id. */
  int ML_THEN = 58;
  /** RegularExpression Id. */
  int ML_NOT = 59;
  /** RegularExpression Id. */
  int OBLIGATION = 60;
  /** RegularExpression Id. */
  int FORBIDDEN = 61;
  /** RegularExpression Id. */
  int PERMISSION = 62;
  /** RegularExpression Id. */
  int PERM_PERMISSION = 63;
  /** RegularExpression Id. */
  int COMMA = 64;
  /** RegularExpression Id. */
  int OCCURRED = 65;
  /** RegularExpression Id. */
  int RESULTS_IN = 66;
  /** RegularExpression Id. */
  int REPAIRED_BY = 67;
  /** RegularExpression Id. */
  int EXCEPTION_OF = 68;
  /** RegularExpression Id. */
  int GIVEN_THAT = 69;
  /** RegularExpression Id. */
  int ONLY_WHEN = 70;
  /** RegularExpression Id. */
  int INSIDE = 71;
  /** RegularExpression Id. */
  int EXISTS = 72;
  /** RegularExpression Id. */
  int FORALL = 73;
  /** RegularExpression Id. */
  int IS_HAPPENING = 74;
  /** RegularExpression Id. */
  int JUST_HAPPENED = 75;
  /** RegularExpression Id. */
  int EQUAL = 76;
  /** RegularExpression Id. */
  int LESS = 77;
  /** RegularExpression Id. */
  int GREATER = 78;
  /** RegularExpression Id. */
  int LEQ = 79;
  /** RegularExpression Id. */
  int GEQ = 80;
  /** RegularExpression Id. */
  int START_RAW_TEXT = 81;
  /** RegularExpression Id. */
  int END_RAW_TEXT = 82;
  /** RegularExpression Id. */
  int RAW_TEXT = 83;
  /** RegularExpression Id. */
  int IDENTIFIER = 84;
  /** RegularExpression Id. */
  int NUMBER = 85;
  /** RegularExpression Id. */
  int MINUS = 86;
  /** RegularExpression Id. */
  int UNDERSCORE = 87;
  /** RegularExpression Id. */
  int INT_NUMBER = 88;
  /** RegularExpression Id. */
  int LETTER = 89;
  /** RegularExpression Id. */
  int DIGIT = 90;

  /** Lexical state. */
  int DEFAULT = 0;
  /** Lexical state. */
  int IN_RAW_TEXT_MODE = 1;

  /** Literal token values. */
  String[] tokenImage = {
    "<EOF>",
    "\" \"",
    "\"\\r\"",
    "\"\\t\"",
    "\"\\n\"",
    "<token of kind 5>",
    "\"#Background\"",
    "\"#Clauses\"",
    "\"action\"",
    "\"actions\"",
    "\"impersonal action\"",
    "\"timer\"",
    "\"local interval\"",
    "\"shared interval\"",
    "\"global interval\"",
    "\"for\"",
    "\"infinite\"",
    "\"defined by actions\"",
    "\"only occurs in scope\"",
    "\"occurrences\"",
    "\"output values\"",
    "\"only performable by\"",
    "\"roles\"",
    "\"disjoint\"",
    "\"cover\"",
    "\"synchronizes with\"",
    "\"allow autosync\"",
    "\"disallow autosync\"",
    "\"tag:\"",
    "\"belongsTo:\"",
    "\"local counter\"",
    "\"shared counter\"",
    "\"global counter\"",
    "\"increases with action\"",
    "\"decreases with action\"",
    "\"by\"",
    "\"without further refinement\"",
    "\"provided that\"",
    "\"resets with action\"",
    "\"sets with action\"",
    "\"to value\"",
    "\"init value\"",
    "\"min value\"",
    "\"max value\"",
    "\"reaching min impedes actions\"",
    "\"reaching max impedes actions\"",
    "\"(\"",
    "\")\"",
    "\"{\"",
    "\"}\"",
    "\"[\"",
    "\"]\"",
    "\".\"",
    "\":\"",
    "\"<>\"",
    "\"[]\"",
    "\"&\"",
    "\"|\"",
    "\"->\"",
    "\"!\"",
    "\"O(\"",
    "\"F(\"",
    "\"P(\"",
    "\"PP(\"",
    "\",\"",
    "\"occurred\"",
    "\"results in\"",
    "\"repaired by\"",
    "\"is exception of\"",
    "\"given that\"",
    "\"only when\"",
    "\"inside\"",
    "\"EXISTS(\"",
    "\"FORALL(\"",
    "\"isHappening(\"",
    "\"justHappened(\"",
    "\"=\"",
    "\"<\"",
    "\">\"",
    "\"<=\"",
    "\">=\"",
    "\"%\"",
    "\"%\"",
    "<RAW_TEXT>",
    "<IDENTIFIER>",
    "<NUMBER>",
    "\"-\"",
    "\"_\"",
    "<INT_NUMBER>",
    "<LETTER>",
    "<DIGIT>",
    "\";\"",
  };

}
