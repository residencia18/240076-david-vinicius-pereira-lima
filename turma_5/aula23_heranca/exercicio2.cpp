/*

● Crie uma superclasse chamada Forma com um método calcularArea().
● Em seguida, crie duas subclasses: Retangulo e Circulo (com seus atributos
e construtores específicos) que herdam de Forma
○ Implemente o método calcularArea() para calcular a área de um retângulo e de um círculo,
respectivamente.
● Crie um programa que permita ao usuário criar objetos de ambas as
subclasses e chame o método calcularArea() em cada um deles para
calcular suas áreas.

*/

#include <iostream>
using namespace std;

class Forma{
    public:
        double calcularArea();
};

class Retangulo:public Forma{
    private:
        double altura, base;
    public:
        Retangulo(){
            this->altura = 0.0;
            this->base = 0.0;
        }
        Retangulo(double altura, double base){
            this->altura = altura;
            this->base = base;
        }

        double calculaArea(){
            return altura*base;
        }
};

class Circulo:public Forma{
    private:
        const double PI = 3.1415;
        double raio;
    public:
        Circulo(){
            this->raio = 0.0;
        }
        Circulo(double raio){
            this->raio = raio;
        }
        double calculaArea(){
            return PI*raio*raio;
        }
};


int main(void){
    Circulo c1 (5.2);
    Retangulo r1(3, 3.14);
    cout<<c1.calculaArea()<<endl;
    cout<<r1.calculaArea()<<endl;
    return 0;
}