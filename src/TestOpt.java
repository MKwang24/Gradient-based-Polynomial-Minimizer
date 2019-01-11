import java.io.File;

import opt.Minimizer;
import poly.Polynomial;
import util.Vector;

/** This is a small example of test cases.  To test correctness of your implementation,  
 *  see if the output on your tests matches the results of the same tests on the solution
 *  in TestOptSoln.  Note the imports in this file!
 */
public class TestOpt {

	public static void main(String[] args) throws Exception {
		RunMinimizer("files/poly1.txt", 0.001, 200, 0.10, "{ x=1.0 }");
		RunMinimizer("files/poly2.txt", 0.001, 200, 0.10, "{ x=1.0 y=1.0 }");
		Vector vec3 = new Vector("{ x=-1 y=-2.0 z=3d }");
		System.out.print(vec3);
//		System.out.println(vec3.getval("x"));
//		Vector v = new Vector("{ x=0  y=3 }");

//		Vector v1 = new Vector();
//		v1.set("y", 2.0);
//		v.set("x", 1.0);
//		v.set("y", 2.0);
////		Polynomial p  = new Polynomial("x^2 + y^2 + -4*x*y+8");
////		p.evaluate(v);
//		p.evaluate(v1);
//		v.clear();
//		v.computeL2Norm();
//		v.equals(v1);
//		v.getval(x);
//		v.scalarMult(1);
//		v.set(var, val);
//		v.sum(x)
//		v.
	}	

	public static void RunMinimizer(String polyfile, double eps, int max_iter, double alpha, String sx0) 
			throws Exception {
		
		Minimizer m = new Minimizer();

		// If the following file does not load, verify that it exists,
		// and check that it is the correct path relative to your
		// NetBeans/Eclipse project settings for working directory
		Polynomial p = Polynomial.ReadPolynomial(new File(polyfile));
		
		m.setEps(eps);
		m.setMaxIter(max_iter);
		m.setStepSize(alpha);
		m.setX0(new Vector(sx0));
		
		System.out.println("========================================");
		System.out.println("OPTIMIZING: " + p);
		System.out.println("========================================");
		m.printParams(System.out);
		m.minimize(p);
		m.printResults(System.out);
	}
}
