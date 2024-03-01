/*

b. Modifique o exercício anterior para que a saída imprima também o código
numérico em octal e em hexadecimal.

*/

#include <iostream>
using namespace std;

int main(void){

    char numero = '0';
    cout << "0  em decimal = " << int(numero) << endl;
    cout << "0 em octal = " << oct(numero) << endl;
    numero = '1'; 
    cout << "1 = " << int(numero) << endl;
    numero = '2'; 
    cout << "2 = " << int(numero) << endl;
    numero = '3'; 
    cout << "3 = " << int(numero) << endl;
    numero = '4'; 
    cout << "4 = " << int(numero) << endl;
    numero = '5'; 
    cout << "5 = " << int(numero) << endl;
    numero = '6'; 
    cout << "6 = " << int(numero) << endl;
    numero = '7'; 
    cout << "7 = " << int(numero) << endl;
    numero = '8'; 
    cout << "8 = " << int(numero) << endl;
    numero = '9'; 
    cout << "9 = " << int(numero) << endl;
    return 0;

    return 0;
}