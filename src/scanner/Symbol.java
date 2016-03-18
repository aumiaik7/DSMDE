package scanner;

public enum Symbol {
	MM(256),MATRIX(257),DSM(258),MDM(259),DMM(260),COORD(261),ARRAY(262),INT(263),REAL(264)
	,COMPLEX(265),PATTERN(266),GENERAL(267),SYMETRIC(268),SKSYMETRIC(269),HERMITIAN(270),
	IC(271),IR(272),BDOMAIN(273),EDOMAIN(274),BMODE(275),EMODE(276),BATTRIB(277),EATTRIB(278),
	PLUS(279),MINUS(280),E(281),NUM(282),UNDEFINED(283),EOF(284);
	
	private final int code;
	Symbol(int code)
	{
		this.code = code;
	}
	
	public int getCode()
	{
		return code;
	}
}
