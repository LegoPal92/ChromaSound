package info.civiliancraft.ChromaSound.Utils;

public enum EmitterType {

	UNIQUE(0, "UNIQUE"),
	ONCE(1, "ONCE"),
	LOOP(2, "LOOP");
	
	private int uId;
	private String uName;
	
	private EmitterType(int uniqueIndex, String name){
		uId = uniqueIndex;
		uName = name;
	}
	
	public int getUniqueIndex(){
		return uId;
	}
	
	public String getUniqueName(){
		return uName;
	}
	/**
	 * 
	 * @param name
	 * @return The value of the name as an {@link EmitterType}.
	 */
	public static EmitterType getEmitterTypeFromName(String name){
		return valueOf(name.toUpperCase());
	}
	/**
	 * 
	 * @param id
	 * @return The value of the id as an {@link EmitterType}. Defaults to {@link EmitterType.ONCE};
	 */
	public static EmitterType getEmitterTypeFromId(int id){
		switch (id){
		case 0:{
			return UNIQUE;
		}
		case 1:{
			return ONCE;
		}
		case 2:{
			return LOOP;
		}
		default:{
			return ONCE;
		}
		}
	}
	
}
