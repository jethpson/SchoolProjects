// 
//  Name:   Thompson, Jacob 
//  Project: #2 
//  Due:      2/21/2023 
//  Course:  cs-2400-03-sp23
// 
//  Description: 
//    Implement the interface StackInterface and the class LinkedStack.
//    Allow the class Expressions the ability to use that interface.
//    Read the ExpressionTest args from the command-line and feed the results to Expression.java,
//    Expression.java has two methods, convertToPostfix and evaluatePostfix.
//    ConvertToPostfix will take in Infix arguments and convert them to Postfix using the linked stack, handling all exceptions as necessary.
//    EvaluatePostfix will take in a postfix argument and evaluate through the linked stack, handling all exceptions as necessary.
//    Then Show the testing of all the interfaces that are not used above.
//


/**
A class which utilizes the Expression class in order to evaluate infex to postfix arguments.
*/
public class ExpressionTest 
{

		public static void main(String[] args) 
	{
			
			StackInterface<String> test = new LinkedStack<>();         // Creates a new linkedStack intended only for testing outcomes.
			System.out.println("Expession by J. Thompson");
			System.out.println("");
	
			
			int T=0;
			for (String str: Expression.convertToPostfix(args))        // Takes results of convertToPostfix from command line and prints them in the correct positions.
			{														   // While also evaluating them at the same time.
				
				System.out.println(args[T]);
				System.out.print("	");
				System.out.println(str + " = "  + Expression.evaluatePostfix(str));
				T++;
				
			 }
			
			System.out.println("");
			System.out.println("Further Testing OutPuts: ");			// Testing the remaining stack operation clear().
			System.out.println("test.push(a , b , c);");				// By pushing three letters into the stack and then using clear(), testing if it worked with isEmpty().
			test.push("a");
			test.push("b");
			test.push("c");
			System.out.println("test.clear();");
			test.clear();
			System.out.println("test.isEmpty();");
			System.out.println("results for isEmpty() : " + test.isEmpty());
				
	}	// end main
			
	
}	// end ExpressionTest
