# TASK: Generate a Spanish IDs validator

To verify the NIF of Spanish residents of legal age, the algorithm for calculating the check digit is as follows:
The number is divided by 23 and the remainder is replaced by a letter determined by inspection using the following table:

| Remainder | Letter |
|-----------|--------|
| 0         | T      |
| 1         | R      |
| 2         | W      |
| 3         | A      |
| 4         | G      |
| 5         | M      |
| 6         | Y      |
| 7         | F      |
| 8         | P      |
| 9         | D      |
| 10        | X      |
| 11        | B      |
| 12        | N      |
| 13        | J      |
| 14        | Z      |
| 15        | S      |
| 16        | Q      |
| 17        | V      |
| 18        | H      |
| 19        | L      |
| 20        | C      |
| 21        | K      |
| 22        | E      |

For example, if the DNI number is 12345678, divided by 23 gives a remainder of 14, so the letter would be Z: 12345678Z.

The NIE numbers of foreign residents in Spain have a letter (X, Y, Z), 7 numbers, and a check digit.
To calculate the check digit, substitute:

X → 0
Y → 1
Z → 2
and apply the same algorithm as for the NIF.
