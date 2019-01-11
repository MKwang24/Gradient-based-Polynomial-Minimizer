package poly;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.TreeSet;

import util.Vector;
import util.VectorException;

/** Implements an individual term in a polynomial.  If 5x^2 + 3xy is a polynomial,
 *  it has two terms 5x^2 and 2xy, each of which would be represented by a different
 *  instance of this class.
 * 
 * @author ssanner@mie.utoronto.ca
 *
 */
public class Term {
	
	// For term 2.1*x^4*y*z^2, the data members would take values as follows:
	private double _coef; // = 2.1
	private ArrayList<String>  _vars; // = ["x", "y", "z"]
	private ArrayList<Integer> _pows; // = [4, 1, 2]

	/** This constructor has been implemented for you.
	 * 
	 * @param coef -- sets the _coef member
	 */
	public Term(double coef) {
		_coef = coef;
		_vars = new ArrayList<String>();
		_pows = new ArrayList<Integer>();
	}
	
	/** This constructor has been implemented for you -- it parses a term 
	 *  representation from a String into the format required by this class.
	 *  You need to understand the following code.
	 * 
	 * @param s -- String to parse
	 * @throws PolyException if s is malformed
	 */
	public Term(String s) throws PolyException {
		
		if (s == null || s.trim().equals(""))
			throw new PolyException("Empty Term, cannot read");
		
		// Initialize this term
		_coef = 1.0d; // Will multiply any constants by this
		_vars = new ArrayList<String>();
		_pows = new ArrayList<Integer>();

		// You need to understand all lines of the following code
		String[] factors = s.split("\\*");
		for (String factor : factors) {
			factor = factor.trim(); // Get rid of leading and trailing whitespace
			try {
				// If successful, multiplies in a constant (multiple constants in a product allowed)
				_coef *= Double.parseDouble(factor); 					
			} catch (NumberFormatException e) {
				// If not a coefficient, must be a factor "<var>^<pow>"
				// Must be a variable to a power -- parse the factor and add to list
				int pow = 1; // If no power, defaults to 1
				String[] var_pow = factor.split("\\^");
				String var = var_pow[0];
				if (var_pow.length == 2) {
					try { // Second part must be exponent
						pow = Integer.parseInt(var_pow[1]);
					} catch (NumberFormatException f) {
						throw new PolyException("ERROR: could not parse " + factor);
					}
				} else if (var_pow.length > 2) 
					throw new PolyException("ERROR: could not parse " + factor);
				
				// Successfully parsed variable and power, add to list
				if (_vars.contains(var))
					throw new PolyException("ERROR: " + var + " appears twice in " + s);
				_vars.add(var);
				_pows.add(pow);
			}
		}
	}
	
	/** Produce a re-parseable representation of this Term as a String.  This
	 *  has been done for you.
	 * 
	 */
	public String toString() {
		// Using "+" to append Strings involves a lot of String copies since Strings are 
		// immutable.  StringBuilder is much more efficient for append.
		StringBuilder sb = new StringBuilder();
		sb.append(String.format("%01.3f", _coef));
		for (int i = 0; i < _vars.size(); i++) {
			String var = _vars.get(i);
			int pow = _pows.get(i);
			sb.append("*" + var + (pow == 1 ? "" : "^" + pow));
		}
		return sb.toString();
	}

	/** Returns all of the variables used in this Term as a sorted set (TreeSet).
	 *  This has been implemented for you, but you need to understand how it works
	 *  since you'll write a similar method in Polynomial that uses this method.
	 * 
	 * @return
	 */
	public TreeSet<String> getAllVars() {
		// TreeSets are like HashSets but sorted alphabetically (lookup and insertion are
		// a little less efficient than HashSets, but this won't matter for our sizes).
		return new TreeSet<String>(_vars);
	}
		

	/** If Term defines a function f(x,y) = 2xy^2 and assignments is { x=2.0 y=3.0 } 
	 *  then this method returns 36.0, which is the evaluation of f(2.0,3.0). 
	 * 
	 * @param assignments
	 * @return
	 * @throws PolyException
	 * @throws VectorException 
	 */

	public double evaluate(Vector assignments) throws Exception {
		for (String key : this._vars) {
		if (!assignments.contain(key)) {
			throw new PolyException("Variables doesn't match with Vector");
			}
		}
			double result = 1;
		for (int i= 0 ; i < _vars.size(); i++) {
			double base = assignments.getval(_vars.get(i));
			double power = _pows.get(i);
			result *= Math.pow(base,power);
		} 
		return result*_coef;
//		 //TODO: Should not return 0!
//		double result =1;
//		if (_vars.size()!=0) {
//		for (String var :_vars) {
//			result *= Math.pow(assignments.getval(var),_pows.get(_pows.indexOf(var)));
//		}
//		return result*_coef;
//		}
//		else {
//			return _coef;
//		}
		
		
	}
	
	public double getCoef() {
		return _coef;
	}
	/** If Term defines a function f(.) then this method returns the **symbolic**
	 *  partial derivative (which you can verify from calculus is still a Term):
	 *  
	 *    partial f(1.0,2.0) / partial var.
	 * 
	 *  Specifically, if Term defines a function f(x,y) = 2xy^2 and var = "x"
	 *  then this method returns a **new** Term 2y^2 and if var = "y" then it
	 *  instead returns a **new** Term 4xy.
	 * 
	 * @param var
	 * @return partial derivative of this w.r.t. var as a new Term
	 */
	public Term differentiate(String var) {
		Term new1 = new Term(_coef);
		new1._coef = _coef;
		new1._pows.addAll(_pows);
		new1._vars.addAll(_vars);
		//System.out.println(new1._coef);
		//System.out.println(new1._vars);
		//System.out.println(new1._vars); 
		//System.out.println(new1._pows);System.out.println(new1._vars);
		if (new1._vars.size()==0){
			new1._coef = 0;//method
			return new1;
		}
		else {	//skip the following for loop
			int i = new1._vars.indexOf(var);
			
			 if (new1._vars.contains(var) && new1._pows.get(i) -1 == 0){//should use new1._vars.get(i).equals(var) but index out of range since constant terms doesnt have a var. 
				new1._coef *= new1._pows.get(i);
				new1._pows.remove(i);
				new1._vars.remove(i);
				
				}
			else if (new1._vars.contains(var) && new1._pows.get(i) -1 != 0){  
				new1._coef *= new1._pows.get(i);	
				new1._pows.add(i, new1._pows.get(i)-1);
				}
			else if (!new1._vars.contains(var)) {
				new1._coef=0;
				new1._pows.clear();
				new1._vars.clear();
			}
		// what is f(.)?
			}	
		return new1;
		}		
		//System.out.println(new1._vars);System.out.println(new1._pows);	

	}

