#include <iostream>
#include <string>
using namespace std;

class A {
public:
	void f() {
		cout << "A::f()" << endl;
	}
	void g(double d) {
		cout << "A::g(double)" << endl;
	}
	void g(string s) {
		cout << "A::g(string)" << endl;
	}
	void h(char c) {
		cout << "A::h(char)" << endl;
	}
	void h(string s) {
		cout << "A::h(string)" << endl;
	}
};

class B : public A {
public:
	void g(int i) {
		cout << "B::g(int)" << endl;
	}
	void h(int i) {
		cout << "B::h(int)" << endl;
	}
	void h(string s) {
		cout << "B::h(string)" << endl;
	}
};

int main() {
	A a;
	B b;
	cout << "a.f() "; 
	a.f();    // �berl�dt ... �berdeckt ... Redefiniert ...
	cout << "b.f() "; 
	b.f();    // A::f
	cout << "a.g(1.2) "; 
	a.g(1.2); // �berl�dt 
	//cout << "b.g(1.2) "; 
	//b.g(1.2); // �berdeckt double statt int
	cout << "a.g(1) "; 
	a.g(1);   // �berl�dt 
	cout << "b.g(1) "; 
	b.g(1);   // �berl�dt 
	cout << "a.g('a') "; 
	a.g('a'); // �berl�dt 
	cout << "b.g('a') "; 
	b.g('a'); // �berdeckt
	cout << "a.g(\"a\") "; 
	a.g("a"); // �berl�dt 
	//cout << "b.g(\"a\") "; 
	//b.g("1"); //�berdeckt Fehler: string statt int
	cout << "a.h(1) "; 
	a.h(1);   // �berl�dt
	cout << "b.h(1) "; 
	b.h(1);   // �berdeckt
	cout << "a.h('a') "; 
	a.h('a'); // �berl�dt
	cout << "b.h('a') "; 
	b.h('a'); // �berl�dt
	cout << "a.h(\"a\") "; 
	a.h("a"); // �berl�dt 
	cout << "b.h(\"a\") "; 
	b.h("a"); // Redefiniert
	
	getchar();
	return 0;
}

