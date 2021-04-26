package polynomial;


/**
 * 
 * @author Brad McLean 0103571
 * Class to create polynomial objects for use in Math applications.
 * A polynomial consists of several terms. As such the data for the objects
 * will be a dynamically sized array of Term objects.
 * The degree of a polynomial states the largest exponent showing on its terms.
 * It also serves as an index of the last array element.
 */
public class Polynomial {
	private Term[] data;
	private int degree;
	private int capacity;

	/**
	 * Unargumented constructor sets defaults.
	 * A degree of -1 signifies the Polynomial has no terms
	 */
	public Polynomial() {
		capacity = 8;
		degree = -1;
		data = new Term[capacity];
	}
	
	/**
	 * Copy from one polynomial to a new one
	 * Deep copy is performed on the data since
	 * objects are being copied over.
	 * @param p Polynomial to be copied
	 */
	public Polynomial(Polynomial p) {
		capacity = p.capacity;
		degree = p.degree;
		data = new Term[capacity];
		System.arraycopy(p.data, 0, data, 0, degree+1);
	}
	
	/**
	 * Returns the term at given index
	 * @param index Index of term to get.
	 * @return Required Term.
	 */
	public Term getTerm(int index) {return data[index];}
	
	/**
	 * Resets the polynomial back to its defaults and creates a new data array.
	 */
	public void clear() {
		capacity = 8;
		data = new Term[capacity];
		degree = -1;
	}
	
	/**
	 * Determines if the Polynomial is empty
	 * @return True is the Polynomial is empty. False otherwise.
	 * 
	 */
	public boolean isEmpty() {
		return degree == -1;
	}
	
	/**
	 * Adds a term to the polynomial.
	 * @param Term object to be added.
	 */
	public void addTerm(Term t) { 
		
		int exp = t.getExp();
		if (degree >= exp) {
			Term n = new Term(t.getLiteral(),t.getExp(),t.getCoeff());
			data[exp]=n;
			return;
		}
		while (degree < exp) {
			++degree;
			if (degree == data.length)
				grow();
			
			if (degree == exp) {
				Term n = new Term(t.getLiteral(),t.getExp(),t.getCoeff());
				data[exp]=n;
				return;
			}
			Term zero = new Term(t.getLiteral(),degree,0);
			data[degree]=zero;
		}
		return;
	}
	
	private void grow() {
		capacity = capacity *2;
		Term [] newArray = new Term[capacity];
		System.arraycopy(data, 0, newArray, 0, degree);
		data = newArray;
	}
	
	/**
	 * Returns the degree of the Polynomial
	 * @return The degree
	 */
	public int getDegree() {return this.degree;}
	
	
	/**
	 * Adds two polynomials together
	 * @param p The polynomial to be added
	 * @return The sum of the polynomials
	 */
	public Polynomial add(Polynomial p) {
		int smaller = Math.min(this.degree, p.degree);
		Term newTerm;
		Polynomial larger;
		
		if (this.degree < p.degree)
			larger = p;
		else
			larger = this;
		Polynomial newP = new Polynomial();
		
		//add the coefficients up to the smaller degree
		//This wont run if the polynomials are the same size
		for (int i = 0; i <=smaller ; ++i) {
			newTerm = new Term(this.data[i].getLiteral(),i,this.data[i].getCoeff()+
					p.data[i].getCoeff());
			newP.addTerm(newTerm);
			
			}
		
		//enter the rest of the coefficients from the larger polynomial.
		for (int i = smaller + 1; i <= larger.degree; ++i) {
			newTerm = new Term(larger.data[i].getLiteral(),i,larger.data[i].getCoeff());
			newP.addTerm(newTerm);
			}
		return newP;
	}
	/**
	 * Subtracts two polynomials.
	 * @param p The polynomial to be subtracted.
	 * @return The difference of the polynomials.
	 */
	public Polynomial subtract(Polynomial p) {
		int smaller = Math.min(this.degree, p.degree);
		Term newTerm;
		Polynomial larger;
		
		if (this.degree < p.degree)
			larger = p;
		else
			larger = this;
		Polynomial newP = new Polynomial();
		

		for (int i = 0; i <=smaller ; ++i) {
			newTerm = new Term(this.data[i].getLiteral(),i,this.data[i].getCoeff() -
					p.data[i].getCoeff());
			newP.addTerm(newTerm);
			
			}
		
		if (larger == this) {
			for (int i = smaller + 1; i <= larger.degree; ++i) {
				newTerm = new Term(this.data[i].getLiteral(),i,larger.data[i].getCoeff());
				newP.addTerm(newTerm);
				}
			return newP;
		}
		else {
			for (int i = smaller + 1; i <= larger.degree; ++i) {
				newTerm = new Term(larger.data[i].getLiteral(),i,0-larger.data[i].getCoeff());
				newP.addTerm(newTerm);
				}
			return newP;
		}
	}
	/**
	 * Multiplies two polynomials.
	 * @param p The polynomial to be multiplied.
	 * @return The product of the polynomials.
	 */
	public Polynomial multiply(Polynomial p) {
		Polynomial newP = new Polynomial();
		newP.degree = this.degree + p.degree;
		String s = this.data[0].getLiteral();
		for (int i = 0; i <= newP.degree; ++i) 
			newP.addTerm(new Term(s,i,0));		
		
		for (int i = 0; i <= this.degree; ++i)
			for (int j = 0; j <= p.degree; ++j) {
				double product = this.data[i].getCoeff()*p.data[j].getCoeff();
				newP.data[i+j].setCoeff(newP.data[i+j].getCoeff() + product);
			}
		return newP;		
	}
	
	/**
	 * Returns the result when 
	 * @param value Value to replace the literal
	 * @return Result after replacing the literal with the given value.
	 */
	public double solveFor(Double value) {
		double result = 0;
		for (int i = 0; i <= degree; ++i) 
			result+=this.data[i].getCoeff()*Math.pow(value,this.data[i].getExp());
		return result;
	}
	
	/**
	 * Returns a string representation of the polynomial in standard notation.
	 */
	public String toString() {
		String s = "";
		if (degree == -1)
			return "";
		if (degree == 0)
			return data[0].toString();
		for (int i = 0; i <= degree; ++i) {
			
			if (data[i].getCoeff()!=0)
				s+=data[i].toString();
		}
		return s;
	}

}

