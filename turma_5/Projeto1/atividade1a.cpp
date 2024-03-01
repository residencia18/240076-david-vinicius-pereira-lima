/*

Os caracteres numéricos aparecem na tabela ASCII, e em outras, numa
sequência que começa pelo caractere ‘0’ até o caractere ‘9’. As variáveis
de tipo caractere podem ser tratadas também como valores numéricos. Com
base nestas afirmações desenvolva um programa em C++ que:
a. Imprima na tela, utilizando cout, cada um dos caracteres numéricos e
seu correspondente código numérico. Como modificar o comportamento
do cout para imprimir um objeto de tipo char como caractere e como
número?

*/

#include <iostream>
using namespace std;

int main(void){
    char numero = '0';
    cout << "0 = " << int(numero) << endl;
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
}