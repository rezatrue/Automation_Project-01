package utilities;

import org.passay.CharacterData;
// Rewriting Special characters

public enum EnglishCharacterData1 implements CharacterData{

	  Special(
			    "INSUFFICIENT_SPECIAL",
			    // ASCII symbols
			    "!@#$%^&*");

	
	  /** Error code. */
	  private final String errorCode;

	  /** Characters. */
	  private final String characters;
	
	EnglishCharacterData1(final String code, final String charString)
	{
	  errorCode = code;
	  characters = charString;
	}
	
	@Override
	public String getErrorCode()
	{
	  return errorCode;
	}
	
	@Override
	public String getCharacters()
	{
	  return characters;
	}

}