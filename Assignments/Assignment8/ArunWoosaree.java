// Note: this file will not compile.
// The file ending is .java just so that I have syntax highlighting
//
/*
1. Code fragment B is more understandable.
The code uses a number which has a certain meaning to it, which may not be immediately
obvious in code fragment A. In code fragment B however, the constant 9.81 is given
a human readable name which explains the meaning of the number.

The refactoring tehnique used here is https://refactoring.com/catalog/replaceMagicLiteral.html
*/

// 2. Original code fragment:
public int funny(int a, int b) {
	int temp = a * b;

	if (temp > 100) {
		return temp * 0.95;
	} else {
		return temp * 0.25;
	}
}

// 2A with refactoring technique 'Inline temp'
public int funny (int a, int b){
	if (a * b > 100){
		return a * b * 0.95;
	} else {
		return a * b * 0.25;
	}
}

// 2B with refectoring technique 'Extract Method Followed by Replace Temp with Query'
public int funny (int a, int b){
	if (getTemp(a,b) > 100){
		return getTemp(a,b) * 0.95;
	} else{
		return getTemp(a,b) * 0.25;
	}
}

public int getTemp (int a, int b){
	return a * b;
}

/*
2C
In this case where the function is so simple, code fragment 2A with refactoring technique
'Inline temp' is easier to read.
So, the first refactoring technique is more appropriate in this situation
because code fragment 2B with refactoring technique 2B 
'Extract Method Followed by Replace Temp with Query' adds unnecessary complexity here.
  In the case where the temporary variable is more complex to calculate however, the
  second refactoring technique would be more preferred.
*/

/*
3
the 'Replace Error Code With Exception' refactoring techique was used here
https://www.refactoring.com/catalog/replaceErrorCodeWithException.html

Error codes are a pretty old practice. Besides, Java has exceptions which are made
exactly for this purpose. With error codes, the return value of the function could be
mistaken for proper functionality, plus the programmer has to implement checks in the
code for the error codes, and also to detect and handle the errors. 
This is made easier and more succinct with Exceptions, plus it distinguishes
normal code operation from the error cases.
*/


/*
4
Inline Temp refactoring is advantageous in simple cases when the temporary variable
isn't used often, and also when its value does not represent a complex idea,
or when the use of the value is obvious. It would also make sense to use this
refactoring technique when the value is not meant to be changed.


Introduce Explaining Variable refactoring is advantageous when the programmer
intends to make a complex bit of code more readable. 
(Like when calculating the value is complex and would be messy/distrcting inline)
A thoughtful name for the variable explains the significance of its value, which 
makes the code more readable. Also, if the value is used multiple times in the code, 
introducing a variable makes it easier to maintain the code if the value needs to be 
updated at some point.
*/

/*
5
the 'Pull Up Method' refactoring technique was used here
https://www.refactoring.com/catalog/pullUpMethod.html

The output from code fragment A is 10
The output from code fragment B is 20
-> We can see that from this transformation, the code's behavior changes

This is because when we 'pull up the method', the resulting code fragment B
now has two functions with the same name, but different parameters, which means
that the method is being overloaded. This allows java to choose the most appropriate
function to use based on the parameter type passed, so while code fragment A would 
print out a value of 10, in code fragment B, the function k is called
with an input parameter of 2, and java chooses to evaluate the method with
the int type input parameter, so the output printed is 20.
(In code fragment A, the method with the long type input parameter is called)

*/
