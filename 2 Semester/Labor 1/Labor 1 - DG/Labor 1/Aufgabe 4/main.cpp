/*

Spiel Tsching, Tschang, Tschong

Regeln:
Es gibt zwei Spieler: der Programmierer und der Computer.
Beide Spieler waehlen gleichzeitig eines der drei Objekte
Stein, Schere und Papier. Beide k�nnen das gleiche Objekt w�hlen.
Der Spieler, der das m�chtigere Objekt gew�hlt hat gewinnt.
SCHERE kann einen STEIN nicht zerschneiden, d.h. STEIN ist 
m�chtiger als SCHERE. PAPIER wickelt STEIN ein, d.h. PAPIER ist 
m�chtiger als STEIN. SCHERE zerschneidet PAPIER, d. h. SCHERE 
ist maechtiger als PAPIER

*/

#include <cstdlib>
#include <cstring>
#include <ctime>
#include <iostream>
using namespace std;

// Aufz�hlungstyp f�r Stein etc.
enum objectType { STEIN, SCHERE, PAPIER };

// Struktur f�r einen Spieler bestehend aus Name und Wahl 
// des Spielers
struct player {
	char * name;
	objectType choice;
};

// Variable f�r den Namen des Spielers 
char name[15];

// Name des Spielers eingeben
char * enterName(char str[])
{





}

// Den Computer zuf�llig waehlen lassen. 
// Nutzen Sie srand(...) und rand().
objectType randomChoice()
{




}

// Die Wahl von STEIN etc. als String zur�ckgeben lassen
char * object2str(objectType o)
{









}

// Einen Text mit dem Namen des Spielers und seiner Wahl ausgeben
void showPlayer(player p)
{



}

// Die Wahl des Spielers abfragen
objectType enterChoice()
{
















}

// Die Wahl bestimmen, die gewonnen hat
objectType winningObject(objectType obj1, objectType obj2)
{






}

// Ausgeben, wer gewonnen hat
void showWinner(player p1, player p2)
{












}

int main()
{
	player player1, player2;
	player1.name = "Computer";
	player1.choice = randomChoice();
	cout << "Der Computer hat sein Wahl getroffen." << endl;
	player2.name = enterName(name);
	player2.choice = enterChoice();
	showPlayer(player1);
	showPlayer(player2);
	showWinner(player1, player2);
}


