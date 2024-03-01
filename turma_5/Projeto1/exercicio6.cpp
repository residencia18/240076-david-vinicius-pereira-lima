/*

Exercício 6: Transforme o programa do exercício 4 para trabalhar com números do
tipo float.
Deverá ser o mesmo programa do exercício 4, mas as entradas e saídas devem ser
conforme o exemplo abaixo:
Exemplo:
Entrada:
A = 5.0
B = 3.0
Residência em Tecnologia da Informação e Comunicação
Saída:
Soma = 8.0
Subtração = 2.0
Multiplicação = 15.0
Divisão = 1.6

*/

#include <iostream>
using namespace std;

int main (void){

    float n1, n2;
    cout<<"Digite o primeiro numero: ";
    cin>>n1;
    cout<<"Digite o segundo numero: ";
    cin>>n2;
    cout<<"Soma = "<<n1+n2<<endl;
    cout<<"Subtracao = "<<n1-n2<<endl;
    cout<<"Multiplicacao = "<<n1*n2<<endl;
    cout<<"Divisao = "<<n1/n2<<endl;
    return 0;
}