#include "Fahrzeug.h"
#include <time.h>

//Gesamt Kilometerz�hler setzen.
double Fahrzeug::summeKm = 0;

//--------------------------------------------------------------------------------------
//Standard Konstruktor.
//--------------------------------------------------------------------------------------
Fahrzeug::Fahrzeug(char* kennzeichen)
{
	//Kennzeichen speichern.
	kz = MyString(kennzeichen);

	//Eindeutige ID vergeben. Nimmt die Adresse des objekts als ID.
	vin = reinterpret_cast<unsigned int>(this);
}

//--------------------------------------------------------------------------------------
//Standard Dekonstruktor.
//--------------------------------------------------------------------------------------
Fahrzeug::~Fahrzeug()
{
	//Gesamt kilometerstand verringern.
	summeKm -= this->km;
}

//--------------------------------------------------------------------------------------
//Erh�ht den Kilometerz�hler.
//--------------------------------------------------------------------------------------
void Fahrzeug::fahren(double km)
{
	this->km += km;
	summeKm += km;
}

double Fahrzeug::getSummeKm()
{
	return(summeKm);
}

//--------------------------------------------------------------------------------------
//Streaming Operator.
//--------------------------------------------------------------------------------------
std::ostream& operator<<(std::ostream& stream, Fahrzeug& fahrzeug)
{
	stream << "Kz = " << fahrzeug.kz.c_str() << "   VIN = " << fahrzeug.vin << "   Km = " << fahrzeug.km;

	return(stream);
}
