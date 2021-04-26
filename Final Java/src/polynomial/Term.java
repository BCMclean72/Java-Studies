package polynomial;

/**
 * 
 * @author Brad McLean 0103571
 * The Term class extends the Factor class and adds a numerical coefficient.
 * This mathematical construct can be used for creating polynomials and
 * other mathematical expressions.
 *
 */
public class Term extends Factor {

	private double coeff;
	
	/**
	 * Shallow copy of a premade Term. 
	 * No objects are directly copied.
	 * @param other Term to be copied.
	 */
	public Term(Term other) {
		super(other.getLiteral(),other.getExp());
		this.coeff=other.getCoeff();
	}
	
	/**
	 * Arguemented constructor sets Factor variables and the coefficient. 
	 * @param literal Literal value for the Factor.
	 * @param exp Exponent for the Factor.
	 * @param coeff Coefficient for the Term.
	 */
	public Term(String literal, int exp, double coeff) {
		super(literal, exp);
		this.coeff=coeff;
	}
	
	/**
	 * Mutator to change the coefficient.
	 * @param coeff The new coefficient.
	 */
	public void setCoeff(double coeff) {this.coeff = coeff;}
	
	/**
	 * Returns the coefficient.
	 * @return The coefficient.
	 */
	public double getCoeff() {return coeff;}
	
	
	/**
	 * Creates a string of a term in a readable format.
	 * Considers special cases of coefficients equal to 1 or 0.
	 */
	public String toString() {
		if(getExp() == 0) 
			return "" + coeff;
		if(coeff == 0)
			return "";
		if (coeff == 1)
			return super.toString();
		if (coeff < 0 )
			return " - " + Math.abs(coeff) + super.toString();
		return " + " + coeff + super.toString();
	}
	
}


