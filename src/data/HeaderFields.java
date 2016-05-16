package data;

public class HeaderFields {
	
	public enum ObjectType{
		
		MATRIX(1),DSM(2),MDM(3),DMM(4);
		
		private final int code;
		
		private ObjectType(int code) {
			// TODO Auto-generated constructor stub
			this.code = code;
		}
		public int getCode()
		{
			return code;
		}
		
	}
	public enum FormatType{
		
		COORDINATE(1),ARRAY(2);
		
		private final int code;
		
		private FormatType(int code) {
			// TODO Auto-generated constructor stub
			this.code = code;
		}
		public int getCode()
		{
			return code;
		}
	}
	public enum Structure{
		
		GENERAL(1),SYMMETRIC(2), SKSYMMETRIC(3), HERMITIAN(4);
		
		private final int code;
		
		private Structure(int code) {
			// TODO Auto-generated constructor stub
			this.code = code;
		}
		public int getCode()
		{
			return code;
		}
		
	}
	public enum Orientn{
		
		IC(1),IR(2);
		
		private final int code;
		
		private Orientn(int code) {
			// TODO Auto-generated constructor stub
			this.code = code;
		}
		public int getCode()
		{
			return code;
		}
	}
	

}
