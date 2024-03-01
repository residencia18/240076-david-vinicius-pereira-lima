/*

Exercício 4: Criando outro programa básico
Crie um arquivo chamado meuprog2.cpp contendo um programa que peça ao
usuário 2 números inteiros, A e B, e mostre na tela a soma, subtração, multiplicação,
divisão e resto da divisão desses números.
Exemplo:
Entrada:
A = 5
B = 3
Saída:
Soma = 8
Subtração = 2
Multiplicação = 15
Divisão = 1
Resto = 2

*/

#include <iostream>
using namespace std;

int main (void){    

    int n1, n2;
    cout<<"Digite o primeiro numero: ";
    cin>>n1;
    cout<<"Digite o segundo numero: ";
    cin>>n2;
    cout<<"Soma = "<<n1+n2<<endl;
    cout<<"Subtracao = "<<n1-n2<<endl;
    cout<<"Multiplicacao = "<<n1*n2<<endl;
    cout<<"Divisao = "<<n1/n2<<endl;
    cout<<"Resto = "<<n1%n2<<endl;
    return 0;
}