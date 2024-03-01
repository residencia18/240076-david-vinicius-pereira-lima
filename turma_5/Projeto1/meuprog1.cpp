/*

Exercício 2: Criando um programa básico
Crie um arquivo chamado meuprog1.cpp contendo um programa em C++ que peça o
nome_do_usuário e mostre a mensagem “Bom dia <nome_do_usuário>

*/

#include <iostream>

using  namespace std;

int main (void){

    string nome_do_usuario;

    cout <<"Insira seu nome: ";
    cin >> nome_do_usuario;
    cout << "Bom dia " << nome_do_usuario << endl;

    return 0;
}