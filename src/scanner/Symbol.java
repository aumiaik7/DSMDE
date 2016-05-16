package scanner;

public enum Symbol {
	MM(256,"%%MatrixMarket"),MATRIX(257,"Matrix"),DSM(258,"DSM"),MDM(259,"MDM"),DMM(260,"DMM")
	,COORD(261,"Coordinate"),ARRAY(262,"Array"),INT(263,"Integer"),REAL(264,"Real")
	,COMPLEX(265,"Complex"),PATTERN(266,"Pattern"),GENERAL(267,"General"),SYMMETRIC(268,"Symmetric")
	,SKSYMMETRIC(269,"Skew-Symmetric'"),HERMITIAN(270,"Hermitian"),IC(271,"IC"),IR(272,"IR")
	,BDOMAIN(273,"%beginDomain"),EDOMAIN(274,"%endDomain"),BMODE(275,"%beginModElement")
	,EMODE(276,"%endModElement"),BATTRIB(277,"%beginAttribute"),EATTRIB(278,"%endAttribute")
	,COMMENT(279,"textLine"),PLUS(280,"+"),MINUS(281,"-"),E(282,"e"),NUMINT(283,"num int")
	,NUMDOUBLE(284,"num double"),UNDEFINED(285,"Undefined"),EOF(286,"End of File"),	NEWLINE(287,"new line");
	
	private final int code;
	private final String lexeme;
	Symbol(int code, String lex)
	{
		this.code = code;
		this.lexeme = lex;
	}
	
	public int getCode()
	{
		return code;
	}
	
	public String getLexeme()
	{
		return lexeme;
	}
}
