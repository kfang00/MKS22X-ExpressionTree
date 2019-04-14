//Here is the ExpressionTree class, just drop in your methods

//from the classwork/homework, compile and run.

//Except you need to write the apply method.. which you probably have from your stack calc!




public class ExpressionTree{



  /*return the expression as an infix notation string with parenthesis*/
  /* The sample tree would be: "(3 + (2 * 10))"     */
  public String toString(){
    /*you are to write this method*/
    if (isValue()) {
      return getValue() + "";
    }
    return getLeft().toString() + " " + getOp() + " " + getRight().toString();
  }

  /*return the expression as a postfix notation string without parenthesis*/
  /* The sample tree would be: "3 2 10 * +"     */
  public String toStringPostfix(){
    /*you are to write this method*/
    if (isValue()) {
      return getValue() + "";
    }
    return getLeft().toString() + " " + getRight().toString() + " " +  getOp();
  }

  /*return the expression as a prefix notation string without parenthesis*/
  /* The sample tree would be: "+ 3 * 2 10"     */

  public String toStringPrefix(){
    /*you are to write this method*/
    if (isValue()) {
      return getValue() + "";
    }
    return getOp() + " " + getLeft().toString() + " " + getRight().toString();
  }



  /*return the value of the specified expression tree*/

  public double evaluate(){
    /*you are to write this method*/
    if (isOp()) {
      return apply(getOp(), getLeft().evaluate(), getRight().evaluate());
    }
    return getValue();
  }


  /*use the correct operator on both a and b, and return that value*/
  private double apply(char op, double a, double b){
    /*you are to write this method*/
    if (op == '+') {
      return a + b;
    }
    else if (op == '/') {
      return a / b;
    }
    else if (op == '-') {
      return a - b;
    }
    else if (op == '*') {
      return a * b;
    }
    return a % b;

    }




  //If you are not able to take the exam Friday, speak to me in person tomorrow.

  ////////////////////ONLY EDIT ABOVE THIS LINE////////////////////




  private char op;
  private double value;
  private ExpressionTree left,right;

  /*TreeNodes are immutable, so no issues with linking them across multiple
  *  expressions. The can be constructed with a value, or operator and 2
  * sub-ExpressionTrees*/
  public ExpressionTree(double value){
    this.value = value;
    op = '~';
  }
  public ExpressionTree(char op,ExpressionTree l, ExpressionTree r){
    this.op = op;
    left = l;
    right = r;
  }

  public char getOp(){
    return op;
  }

  /* accessor method for Value, precondition is that isValue() is true.*/
  private double getValue(){
    return value;
  }
  /* accessor method for left, precondition is that isOp() is true.*/
  private ExpressionTree getLeft(){
    return left;
  }
  /* accessor method for right, precondition is that isOp() is true.*/
  private ExpressionTree getRight(){
    return right;
  }

  private boolean isOp(){
    return hasChildren();
  }
  private boolean isValue(){
    return !hasChildren();
  }

  private boolean hasChildren(){
    return left != null && right != null;
  }


  public static void main(String[] args){
    //ugly main sorry!
    ExpressionTree a = new ExpressionTree(4.0);
    ExpressionTree b = new ExpressionTree(2.0);

    ExpressionTree c = new ExpressionTree('+',a,b);
    System.out.println(c);
    System.out.println(c.toStringPostfix());
    System.out.println(c.toStringPrefix());
    System.out.println(c.evaluate());//6.0


    ExpressionTree d = new ExpressionTree('*',c,new ExpressionTree(3.5));
    System.out.println(d);
    System.out.println(d.toStringPostfix());
    System.out.println(d.toStringPrefix());
    System.out.println(d.evaluate());//21

    ExpressionTree ex = new ExpressionTree('-',d,new ExpressionTree(1.0));
    System.out.println(ex);
    System.out.println(ex.toStringPostfix());
    System.out.println(ex.toStringPrefix());
    System.out.println(ex.evaluate());//20

    ex = new ExpressionTree('+',new ExpressionTree(1.0),ex);
    System.out.println(ex);
    System.out.println(ex.toStringPostfix());
    System.out.println(ex.toStringPrefix());
    System.out.println(ex.evaluate());//21

    ex = new ExpressionTree('/',ex,new ExpressionTree(2.0));
    System.out.println(ex);
    System.out.println(ex.toStringPostfix());
    System.out.println(ex.toStringPrefix());
    System.out.println(ex.evaluate());//10.5
  }
}
