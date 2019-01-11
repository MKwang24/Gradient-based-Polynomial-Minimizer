package util;

import java.util.Collection;
import java.util.HashMap;

import poly.PolyException;

/** Implements a vector with *named* indices.  For example { x=1.0 y=2.0 } is a 2D
 *  vector with the first dimension named "x" and the second dimension named "y"
 *  and having respective values 1.0 and 2.0 in these dimensions.
 *  
 *  TODO: Implement all methods required to support the functionality of the project
 *        and that described in Vector.main(...) below.
 */
public class Vector {

	private HashMap<String,Double> _hmVar2Value; // This maps dimension variable names to values
	
	/** Constructor of an initially empty Vector
	 * 
	 */
	public Vector() {
		_hmVar2Value = new HashMap<String,Double>();
		
		// TODO: this method should not be empty! 
		// Hint: is there any memory you want to allocate?
		
	}

	/** Constructor that parses a String s like "{ x=-1 y=-2.0 z=3d }" into 
	 *  the internal HashMap representation of the Vector.  See usage in main().
	 * 
	 * x1=-1 y=-2
	 * ['x1','-1','y','-2']
	 * @param s
	 */
	public Vector(String s) throws VectorException{
		_hmVar2Value = new HashMap<String,Double>();
			
		// TODO: this method should not be empty! 
		// Hint: you're going to have use String.split used in Project2.
		String [] split = s.split("[\\s=]");
		try {
//		if (!(split[0].equals("{")||split[0].equals("}"))) {
//			throw new VectorException ("need more } or {") ;
//		}
//		for (String x : split) {
//			if (x.matches("[a-zA-Z]+")==false||x.matches("\\d+")==false) {
//				throw new VectorException ("too much space");
//			}
//		}

		for (int i = 0;  i< split.length-2 ; i+=2) {
			_hmVar2Value.put(split[i+1],Double.parseDouble(split[i+2]));
			
		}
		}
		catch(Exception e){
			throw new VectorException ("too much space");
		}
		}
	
	public String toString() {
		// We could just repeatedly append to an existing String, but that copies the String each
		// time, whereas a StringBuilder simply appends new characters to the end of the String
		StringBuilder sb = new StringBuilder();
		sb.append("{ ");
		
		for ( String i   : this._hmVar2Value.keySet()) { 
			sb.append(String.format("%s=%6.4f ",i, this._hmVar2Value.get(i))); // Append each vector value in order
		}
			sb.append("}");
		return sb.toString();
	}


	/** Removes (clears) all (key,value) pairs from the Vector representation
	 * 
	 */
	public void clear() {
		// TODO: this method should not be empty! 
		// Hint: look very carefully at the available methods of HashMap... this is a one liner!
		_hmVar2Value.clear();
	}

	/** Sets a specific var to the value val in *this*, i.e., var=val
	 * 
	 * @param var - label of Vector index to change
	 * @param val - value to change it to
	 */
	public void set(String var, double val) {
		// TODO: this method should not be empty! 
		// Hint: look very carefully at the available methods of HashMap... this is a one liner!
		_hmVar2Value.put(var, val);
	}

	/** Sets all entries in *this* Vector to match entries in x
	 *  (if additional variables are in *this*, they remain unchanged) 
	 * 
	 * @param x
	 */
	public void setAll(Vector x) {
		// TODO: this method should not be empty! 
		// Hint: look very carefully at the available methods of HashMap... this is a one liner!
		this._hmVar2Value.putAll(x._hmVar2Value); 
	}
	public Vector sum(Vector x) throws VectorException {
		for (String key :this._hmVar2Value.keySet()){
			if (x._hmVar2Value.size()==this._hmVar2Value.size()&&x._hmVar2Value.keySet().contains(key)==false) {
				throw new VectorException("Vector index '"+x._hmVar2Value.keySet()+"' not equal to "+this._hmVar2Value.keySet());
			}
		}
			Vector vv = new Vector();
			for (String i : _hmVar2Value.keySet()) {
				double sum  = _hmVar2Value.get(i)+ x._hmVar2Value.get(i);
				vv.set(i, sum);
			}
			return vv;
		}
	public boolean equals(Vector x) {
		return this._hmVar2Value.equals(x._hmVar2Value);
	}
	public double computeL2Norm() {
		double sum = 0 ;
		for (double i:_hmVar2Value.values()) {
			sum += Math.pow(i,2);
		}
		return Math.sqrt(sum);
	}
	public Vector scalarMult(double integer ) {
		Vector vv = new Vector();
		
		for (String i : _hmVar2Value.keySet()) {
			double q = this._hmVar2Value.get(i)* integer ;
			vv.set(i, q);
		}
		return vv;
	}
	public boolean contain(String x) {
		return _hmVar2Value.containsKey(x);
	}
	public double getval(String x) throws VectorException{
		if (this._hmVar2Value.containsKey(x) == false) {
			throw new VectorException("Vector index '"+x+"' not found in "+this._hmVar2Value.keySet()); 
		}
		return _hmVar2Value.get(x);
	}

	/** Your Vector class should implement the core functionality below and produce
	 *  **all** of the expected outputs below.  **These will be tested for grading.**
	 * 
	 *  When initially developing the code, comment out lines below that you have
	 *  not implemented yet.  This will allow your code to compile for incremental
	 *  testing.
	 *  
	 * @param args (unused -- ignore)
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {

		// Make vector: vec1[x y z] = [1 2 3]
		
//		String s = "{ x=-1 y=-2.0 z=3d }";
//		String []s1 = s.split("[\\s=]");
//		System.out.println(" ");
//		for (int i =0; i<s1.length;i++) {
//			System.out.println(s1[i]);
//		}

		Vector vec1 = new Vector();
		vec1.set("x", 1.0);
		vec1.set("y", 2.0);
		vec1.set("z", 3.0);
		Vector vec0 = new Vector();
		vec0.set("x", 1.0);
		vec0.set("y", 2.0);
		vec0.set("z", 3.0);
	//	System.out.println(vec1.computeL2Norm());
//		System.out.println(vec0.sum(vec1));
//		System.out.println(vec1);
//		// Make vector: vec2[x y z] = [-3 -2 -1]
		Vector vec2 = new Vector();
		vec2.set("x", -3.0);
		vec2.set("y", -2.0);
		vec2.set("z", -1.0);
		
		// Make vector: vec3[x y z] = vec4[x y z] = [-1 -2 -3]
		Vector vec3 = new Vector("{ x=-1 y=-2.0 z=3d }");
//		System.out.print(vec3);
		//vec3.clear();
//		System.out.println(vec3._hmVar2Value.keySet().toArray()[0]);

		Vector vec4 = new Vector(vec3.toString());
		
		// Hint: all numbers below are formatted with String.format("%s=%6.4f ", var, val)
		//       ... you may want to use this in your Vector.toString() implementation!
		
//		// Test cases: 
		System.out.println(vec1); // Should print: { x=1.0000 y=2.0000 z=3.0000 }
		System.out.println(vec2); // Should print: { x=-3.0000 y=-2.0000 z=-1.0000 }
		System.out.println(vec3); // Should print: { x=-1.0000 y=-2.0000 z=3.0000 }
		System.out.println(vec4); // Should print: { x=-1.0000 y=-2.0000 z=3.0000 }
		
		System.out.println(vec1.sum(vec1));        // Should print: { x=2.0000 y=4.0000 z=6.0000 }
		System.out.println(vec1.sum(vec2));        // Should print: { x=-2.0000 y=0.0000 z=2.0000 }
		System.out.println(vec1.sum(vec3));        // Should print: { x=0.0000 y=0.0000 z=6.0000 }
		System.out.println(vec1.scalarMult(0.5));  // Should print: { x=0.5000 y=1.0000 z=1.5000 }
		System.out.println(vec2.scalarMult(-1.0)); // Should print: { x=3.0000 y=2.0000 z=1.0000 }
		System.out.println(vec1.sum(vec2.scalarMult(-1.0))); // Should print: { x=4.0000 y=4.0000 z=4.0000 }
		System.out.format("%01.3f\n", vec1.computeL2Norm());           // Should print: 3.742
		System.out.format("%01.3f\n", vec2.sum(vec3).computeL2Norm()); // Should print: 6.000
		
		// If the following don't work, did you override equals()?  See Project 2 Vector and Matrix.
		System.out.println(vec3.equals(vec1)); // Should print: false
		System.out.println(vec3.equals(vec3)); // Should print: true
		System.out.println(vec3.equals(vec4)); // Should print: true
		System.out.println(vec1.sum(vec2).equals(vec2.sum(vec1))); // Should print: true
	}	
}
