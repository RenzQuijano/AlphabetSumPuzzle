import java.util.ArrayList;
import java.util.Scanner;
public class AlphabetSumPuzzle {
    private static ArrayList<Character> variables = new ArrayList<>();//all variables in the puzzle
    private static ArrayList<String>operands = new ArrayList<>();//all sum operands given by keyboard
    private static String result = null;
    public static void main(String[] args){
        Scanner keyboard = new Scanner(System.in);
        while(keyboard.hasNextLine()){
            String puzzle = keyboard.nextLine();//e.g. THE+BEST+SPOT=SPOTS
            String[] tokens = puzzle.split("[^a-zA-Z]");//e.g. THE, BEST, SPOT, SPOTS
            for(int i = 0; i < tokens.length-1;i++)
                if(!tokens[i].isEmpty())//e.g. operands = [THE, BEST, SPOT]
                    operands.add(tokens[i]);//all tokens but the last one represent the sum operands
            result = tokens[tokens.length-1];//last token must be the sum result. e.g. result = SPOTS
            int[] digitValues = new int[256];//maps characters to digits
            for(int i = 0; i < 256;i++)//initialize digitValues to -1
                digitValues[i] = -1;//symbol for empty cell!
//solve method is called here
            findAllVariables(puzzle);//detect every character participating in the puzzle and store it in the list of variables
            printAllCombinations(digitValues);//printing all combinations using recursion
            digitValues['T'] = 1;
            digitValues['H'] = 2;
            digitValues['E'] = 3;
            System.out.println("This is an example: Numerical value of " +
                    "THE is " +
                    numericalValue("THE", digitValues));
        }
    }
    private static void findAllVariables(String puzzle) {
        variables.clear();
        for(char c: puzzle.toCharArray())
            if(Character.isLetter(c) && !variables.contains(c))
                variables.add(c);
    }
    private static int numericalValue(String word, int[] digitValues) {
        int rv = 0;
        for(char c: word.toCharArray())
            if(digitValues[c] < 0)//if c is not assigned to any value
                return -1;//error
            else
                rv = 10 * rv + digitValues[c];
        return rv;
    }
    //THE: 3-digit integer
//numerical value = T * 100 + H * 10 + E * 1
// 1 * E + 10 * H + 100 * T
// 1 * E + 10 * (H + 10 * T)
    private static void printAllCombinations(int[] digitValues){//recursive method
//Your code here!
        boolean complete = true;
        char unassignedVariable = 0;
        for(char variable: variables)
            if(digitValues[variable] == -1){//variable hasn't been assigned to any value yet!
                        complete = false;//assignment is not complete
                unassignedVariable = variable;//variable without assignment is stored in the unassignedVariable...
                break;
            }
        if(complete) {//base case
            //do not print all mappings
            //print a mapping only if it satisfies the puzzle
            //THE+BEST+SPOT=SPOTS
            //first calculate the Left-hand-side of the equation
            int leftHandSide = 0;
            for(String operand: operands){
                leftHandSide += numericalValue(operand, digitValues);
            }
//then calculate the right-hand-side of the equation
            int rightHandSide = numericalValue(result, digitValues);
//then, proceed with the following code only if LHS = RHS
//for extra credits, you should also consider more conditions before moving forward with printing the solutions
            if (leftHandSide == rightHandSide) { //prints all true equation mappings
                for (char variable : variables)
                    System.out.print(variable + ": " + digitValues[variable] + "| ");
                System.out.println();
            }
        }else{//recursive step
            for(int i = 0; i < 10;i++) {
                digitValues[unassignedVariable] = i;//assign the unassignedVariable to one of the possible digits 0,1,...,9
                printAllCombinations(digitValues);//recursive call!
            }
            digitValues[unassignedVariable] = -1;//assign the unassignedVariable to -1 again
        }
    }
}