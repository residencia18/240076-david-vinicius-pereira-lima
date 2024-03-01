/*

● Crie uma superclasse chamada Animal com atributos nome, idade e um
método fazerSom().
● Em seguida, crie uma subclasse chamada Cachorro que herda de Animal e
substitua o método fazerSom() para que ele imprima "Au Au!" quando
chamado.
● Crie objetos de ambas as classes e teste seus métodos (função main() ).

*/

#include <iostream>
using namespace std;

class Animal{
    private:
        string nome;
        int idade;
    
    public:
        Animal(string nome, int idade){
            this->nome = nome;
            this->idade = idade;
        }
        string getNome(){
            return nome;
        }
        void setNome(string nome){
            this->nome = nome;
        }
        int getIdade(){
            return idade;
        }
        void setIdade(int idade){
            this->idade = idade;
        }
        void fazerSom(){
            cout<<"Animal fazendo som!!"<<endl;    
        }
};

class Cachorro: public Animal{
    public:
        Cachorro(string nome, int idade):Animal(nome, idade){
        }
        void fazerSom(){
            cout<<"Au Au !!"<<endl;
        }
};


int main (void){
    Cachorro c1 ("Max", 3);
    c1.fazerSom();
    return 0;
}