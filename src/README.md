## printAllCombinations method
### Line 80:
```java
if (leftHandSide == rightHandSide && uniqueValueCheck(digitValues)) {     
    for (char variable : variables)
        System.out.print(variable + ": " + digitValues[variable] + "| ");
    System.out.println();
}
```

- Added an if statement to check:
  - if left and right side of equation are equal
  - if all characters have a unique number value (see uniqueValueCheck method)

## numericalValue method
### Line 44: 
```java
} else {
    rv = 10 * rv + digitValues[c];
    if(rv == 0) {
        return -1;
    }
}
```

- Added if statement `if(rv == 0)` to return -1 if the first character of a word equals zero. This makes sure that there are no equations printed with leading zeros.

## uniqueValueCheck method
### Line 94:
```java
private static boolean uniqueValueCheck(int[] digitValues) {
    HashSet<Integer> digitSet = new HashSet<>();
    for (int num : digitValues) {
        if(num != -1) {
            if(!digitSet.add(num)) {
                return false;
            }
        }
    }
    return true;
}
```
- Added this method and called it in the `printAllCombinations` method at line 80*
- Method makes use of HashSets and how they store only unique values
  1. it loops through all 256 indexes in digitValues array
  2. filters out all numbers that are -1 with an if-statement
  3. then checks if a number can be added to the HashSet (if a number is unique and has not been added into the HashSet yet)
  4. if a number has already been added to the HashSet, the if-statement returns false



With these additions, the program now prints all true combinations that have no leading zeros and have no characters that share the same number value.