package polynomial;
/**
 * 
 * @author Brad McLean 0103571
 * The following class creates a mathematical object Factor. 
 * A factor contains a literal unknown and the exponent of the literal
 * For use in Polynomials or other mathematical expressions.
 */
public class Factor {
	private String literal;
	private int exp;
	
	/*
	 * Argumented constructor takes in the unknown literal and the exponent
	 */
	public Factor (String literal, int exp) {
		this.literal = literal;
		this.exp = exp;
	}
	
	/**
	 * Sets the exponent should it need to be altered
	 * @param exp Exponent to be set
	 */
	public void setExp(int exp) {this.exp = exp;}
	
	/**
	 * Sets the literal should it need to be altered
	 * @param exp Literal to be set
	 */
	public void setliteral(String lit) {literal = lit;}
	
	/**
	 * Returns the literal of the factor.
	 * @return The literal of the factor.
	 */
	public String getLiteral() {return literal;}
	
	/**
	 * Returns the exponent of the factor.
	 * @return The exponent of the factor.
	 */
	public int getExp() {return exp;}

	/**
	 * Return a string representation of a factor.
	 * Creates special cases for exponents of 0 and 1.
	 */
	public String toString() {
		if (exp == 0) return "";
		
		if (exp == 1) return literal;
		
		return literal + "^" + exp;
	}
}
