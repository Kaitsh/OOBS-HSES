// Vergleich von Deklaration, Enumeration und Typumwandlung 
// in C und C++

// Fehler                                               C    |  C++
enum enumeration { Place_1st, Place_2nd, Place_3rd, Place_4th };     // Z1           |  Bezeichner d�rfen nicht mit einer Zahl anfangen.
enum marks { A = '1', B = '2', C = '3', D = '4', E = '5', F = '6' }; // Z2           | Bezeichner d�rfen nicht aus chars bestehen.
enum class1 { DIETER, HANS, PETER, KAI };    // Z3           |
enum class2 { ANDREA, SABINE, ZOE };    // Z4           |  Kai musste gel�scht werden, weil der Bezeichner sonst zwei mal existiert.
enum sports { FUSSBALL, BASKETBALL, HOCKEY };// Z5           |

int main(void)                               // Z7           |
{
	int i;                                   // Z8           |
	//sports mysport;                          // Z9           | 
	enum sports mysport;                     // Z10          |  In C++ muss man den Typ nicht mehr mit Prefix schreiben.
	mysport = FUSSBALL;                      // Z11          |
	mysport = FUSSBALL + 1;                  // Z12          | Ben�tig impliziete Umwandlung.
	mysport = 1;                             // Z13          |
	mysport = 10;                            // Z14          | Keine Konvertierung in C.
	mysport = mysport + 1;                   // Z15          | Keine Konvertierung in C.
	mysport = mysport + 1;           // Z16          | 
	float f;                                 // Z17          | 
	for (i = FUSSBALL; i <= HOCKEY; i++)     // Z18          |
		mysport = i;                 // Z19          | Keine Konvertierung in C.
	for (int k = FUSSBALL; k <= HOCKEY; k++) // Z20          | 
		mysport = k;                 // Z21          | Keine Konvertierung in C.
	return 0;                                // Z22          |
}
