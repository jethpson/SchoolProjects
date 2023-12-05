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
A class of two methods whose purpose are to convert infix to postfix and then evaluate them.
*/
public class Expression {
	 
	
	
	/** Converts infix to postfix.
	@param array string of infixExpression / arguments. 
	@return array string of postfixExpressions.
	@throws EmptyStackException if the stack is empty. || (A null argument) || (Parentheses / Qoutation Marks) */
	public static String[] convertToPostfix (String[] infixExpression) 
	{
		
		int i = 0;
		
		StackInterface<Character> stack = new LinkedStack<>();		// Creates a LinkedStack anticipating char's.
		String[] Curr = new String[infixExpression.length];			// Creates an arrayString which will become our return variable.
	
		
		try {														// This try catches Parentheses / Quotation Marks errors. 
		
		while (i<infixExpression.length)
		{
			
			
			
			if (infixExpression[i].length()<=0)						// Checks if the array is null and creates an exception if so.
			{
				System.out.println("Please complete your arguments: '' (A null argument)");
				System.out.println("");
				System.out.println("For Further Testing OutPuts Please Provide Valid Argument's");
				System.exit(0);
				
			}
			
				
			String nextCharacter = "";								// Implements which will enable spaces between multiple digit numbers.
			

		
		for(int j=0 ;j<infixExpression[i].length();j++)				// The start of to-postfix algorithm. 
		{
			
			
			
				
			while (infixExpression[i].charAt(j) == ' ') 
			{
                ++j;
			}
			
			
			
			if (Character.isDigit(infixExpression[i].charAt(j)) || Character.isLetter(infixExpression[i].charAt(j)))    // In order to keep out unwanted exceptions I've allowed letters. I just give a warning that there will be no evaluations.
				{ 
                
				
				nextCharacter += infixExpression[i].charAt(j);
				
					

					if (j+1 >= infixExpression[i].length() || !Character.isDigit(infixExpression[i].charAt(j+1)))
                	{
						nextCharacter += ' ';
                       
                	} 
                	
				} else if (Precedence(infixExpression[i].charAt(j)) != 0) 
				{
					
                    while ((!stack.isEmpty()) && (Precedence(stack.peek()) >= Precedence(infixExpression[i].charAt(j))) && (stack.peek() != '(')) 	// Implementation of Precedence called from another method.
                    {
                       
                    	nextCharacter += stack.peek();
                    	nextCharacter += ' ';
                        
                        stack.pop();
                    
                    }

                    stack.push(infixExpression[i].charAt(j));
                    
				} else if (infixExpression[i].charAt(j) == '(') 
				{
                
					stack.push(infixExpression[i].charAt(j));
					
				
				} else if (infixExpression[i].charAt(j) == ')') 
				{
                    
					while (!stack.isEmpty() && stack.peek() != '(') 	
					{
                    
						nextCharacter += stack.peek();
                        stack.pop();
                        
                    
					}

                    stack.pop();
                    
				}
				
		}
		
		
			
		while (!stack.isEmpty()) // Empty's the stack.
		{
			
			nextCharacter += stack.pop();
			nextCharacter += ' ';
		}
		
		
		Curr[i] = nextCharacter;
		
		
		
		
		i++;					// Starts the next argument.
		
		
	}
	
		
		return Curr;			// The main return.
		
		
		}catch (Exception e)
		{
			System.out.println("Please complete your arguments: (Parentheses / Qoutation Marks / Incomplete expression)");
			System.out.println("");
			System.out.println("For Further Testing OutPuts Please Provide Valid Argument's");
			System.exit(0);
		}
	
	
	return Curr;				// If an error escapes the array will still return.
	
}	// end convertToPostfix
		
	
	
	
	
	/** Evaluates postfix.
	@param  string of posfixExpression / arguments. 
	@return int of postfixExpressions evaluation.
	@throws (Improper Argument / Unknown Symbols) */
	public static int evaluatePostfix (String posfixExpression) 
	{
		
		StackInterface<Integer> stackEval = new LinkedStack<>();		// Creates a new Stack for Evaluating.
		char check = '0';												// Initializes the char check.
		
		if (Character.isLetter(posfixExpression.charAt(0))) 			// While I accept converting letter, I can not evaluate them.
		{
			
			System.out.print("Can not evalute letters:	"); 
			
			
		} else	
		{	
		
			for(int j=0;j<posfixExpression.length();j++)
        {
        
				
			check = posfixExpression.charAt(j);
        
		if(check == ' ')
			continue;
         
		else if (Character.isDigit(check))
		{
			 int n = 0;
			 while(Character.isDigit(check))
             {
				 n = n*10 + (int)(check-'0');
				 j++;
				 check = posfixExpression.charAt(j);
             }
        	j--;
        	
        	stackEval.push(n);
        	
		} else
		{
			int val1=0;
			int val2=0;
			try {														// This try catches Improper Arguments or Unknown Symbols.
			  val1 = stackEval.pop();
              val2 = stackEval.pop();
			} catch (Exception e) {
				System.out.println("Please complete your arguments: (Improper Argument / Unknown Symbols)");
				System.out.println("");
				System.out.println("For Further Testing OutPuts Please Provide Valid Argument's");
				System.exit(0);
			}
             
             switch(check)												// Evaluates the two ints.
             {
                 case '+':
                	 stackEval.push(val2+val1);
                 break;
                  
                 case '-':
                	 stackEval.push(val2- val1);
                 break;
                  
                 case '/':
                	 stackEval.push(val2/val1);
                 break;
                  
                 case '*':
                	 stackEval.push(val2*val1);
                 break;
                 
                 case '^':
                	 stackEval.push(val2^val1);
                 break;
                 case '%':
                	 stackEval.push(val2%val1);
                 break;
                 
             }
			
		}
	
		
        } 
			
		
		
		return (stackEval.pop());
		}
		return (0);
		
	}	// end evaluatePostfix
	
	
	
	/** Evaluates A characters Precedence.
	@param  char from arguments. 
	@return int of the Precedence. */
	private static int Precedence(char x)  
	{	  

		switch(x)
		{
			case '-':return 1;
			case '+':return 1;
			case '*':return 2;
			case '/':return 2;
			case '^':return 2;
			case '%':return 2;
		}
		
		return 0;

	}   // end Precedence

}	// end Expression