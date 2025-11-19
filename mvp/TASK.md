# TASK: Generate a Spanish IDs validator

This task follows the principles of Domain Driven Design (DDD).

## Vocabulary
1. ID - General Spanish ID. It represents all possible IDs
2. NIF - Numero de Identificacion Fiscal. It is an ID subtype
3. NIE - Numero de Identificacion de Extrangeros. It is an ID subtype

## Invariants
1. Control Letters Array:
["T", "R", "W", "A", "G", "M", "Y", "F", "P", "D", "X", "B", "N", "J", "Z", "S", "Q", "V", "H", "L", "C", "K", "E"]

2. NIE Letters Array:
["X", "Y", "Z"]

## Rules:
### IDs:
1. Max lenght == 9

### NIF:
1. Charachters from index 0 to index 7 are digits
2. Character with index 8 is a letter

### NIE:
1. Character with index 0 is a letter
2. Charachters from index 1 to index 7 are digits
3. Character with index 8 is a letter

## Errors:
### IDs:
1. Invalid Input: when the input string has more than 9 characters

### NIF:
1. Invalid NIF Number: if characters from index 0 to 7 are not digits
2. Invalid Control Letter: if the character at index 8 is not present in the Control Letters Array.
3. Invalid NIF: if the remainder of the NIF Number and the Control Letter index is not the same. 

### NIE:
1. Invalid NIE Letter: if the character at index 0 is not present in the NIE Letters Array.
2. Invalid NIE Number:  if characters from index 1 to 7 are not digits
3. Invalid Control Letter: if the character at index 8 is not present in the Control Letters Array.
4. Invalid NIE: if the remainder of the NIE Number and the Control Letter index is not the same.

## Algorithm
The remainder of the ID digits divided by 23 has to be equal to the index of the last character in the Control Letters Array.
### NIE
1. Input elements from index 0 to 7 are casted to Int and divided by 23. Save the remainder.
2. Input element with index 8 is look up in the Control Letters Array. Save its index.
3. If the remainder and the index are equal, the NIE is correct.

### NIF
1. Input element with index 0 is looked up in NIE Letters Array. Extrac its index and multiply it by 10000000.
2. Input elements from index 1 to 7 are casted to Int.
3. Sum the previous integers and divide the result by 23. Save the result as remainder.
4. Input element with index 8 is look up in the Control Letters Array. Save its index.
5. If the remainder and the index are equal, the NIE is correct.
