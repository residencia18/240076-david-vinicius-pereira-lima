#include <iostream>
#include<vector>

using namespace std;

/*

    1.Crie uma classe abstrata chamada ObjetoGeometrico que contenha os 
    métodos virtuais puros area() e perimetro().
    2.Crie as classes concretas Circulo, Quadrado e Retangulo que herdem
    ObjetoGeometrico.
    3.Crie uma classe chamada Calculadora que contenha um método chamado
    calcular() que receba um ObjetoGeometrico e imprima a área e o perímetro do 
    objeto.
    4.Crie um programa que instancie um objeto de cada tipo e passe para o método
    calcular() da classe Calculadora.

*/
class ObjetoGeometrico{
    public:
        virtual double getArea();
        virtual double getPerimetro();
};

/*
                a    
            ______
            |    /
        c   |   / b
            |  / 
            | /
            |/

*/

class Triangulo: public ObjetoGeometrico{
    private:
        double ladoA;
        double ladoB;
        double ladoC;
        double altura;
        
    public:
        Triangulo(double ladoA, double ladoB, double ladoC, double altura);
        double getArea();
        double getPerimetro();
        double getLadoA();
        void setLadoA(double ladoA);
        double getLadoB();
        void setLadoB(double ladoB);
        double getLadoC();
        void setLadoC(double ladoC);
        double getAltura();
        void setAltura(double altura);
        bool ehTriangulo(double a, double b, double c);
};

class Retangulo: public ObjetoGeometrico{
    private:
        double ladoA;
        double ladoB;
    public:
        Retangulo(double ladoA, double ladoB);
        double getArea();
        double getPerimetro();
        double getLadoA();
        double getLadoB();
        void setLadoA(double ladoA);
        void setLadoB(double ladoB);
};

class Quadrado: public ObjetoGeometrico{
    private:
        double lado;
    public:
        Quadrado(double lado);
        double getArea();
        double getPerimetro();
        double getLado();
        void setLado(double lado);
};

double calculaArea(vector<ObjetoGeometrico*> listaObjetos);


int main(void){
    ObjetoGeometrico obj;
    cout<<"Area = "<<obj.getArea();
    cout<<"\nPerimetro = "<<obj.getPerimetro()<<endl;
    Triangulo t(3,4,5,2);
    cout<<"Area = "<<t.getArea();
    cout<<"\nPerimetro = "<<t.getPerimetro()<<endl;
    Retangulo r(3,4);
    cout<<"Area = "<<r.getArea();
    cout<<"\nPerimetro = "<<r.getPerimetro()<<endl;
    Quadrado q(2);
    cout<<"Area = "<<q.getArea();
    cout<<"\nPerimetro = "<<q.getPerimetro()<<endl;
    vector<ObjetoGeometrico*>listaObjetos;
    listaObjetos.push_back(&obj);
    listaObjetos.push_back(&t);
    listaObjetos.push_back(&r);
    listaObjetos.push_back(&q);
    double area = calculaArea(listaObjetos);
    cout << "Area total: " << area << endl;
    return 0;
}
double calculaArea(vector<ObjetoGeometrico*>  listaObjetos){
    double area = 0;
    /*for(int i = 0; i < listaObjetos.size();i++){
        area+= listaObjetos[i]->getArea();
    }*/
    for(auto objs: listaObjetos){
        area+=objs->getArea();
    }
    return area;
}
double ObjetoGeometrico::getArea(){
    return 0;
}

double ObjetoGeometrico::getPerimetro(){
    return 0;
}


Triangulo::Triangulo(double ladoA, double ladoB, double ladoC, double altura){
    if(!ehTriangulo(ladoA, ladoB, ladoC)){
        cout<<"Nao e triangulo";
    }
    else{
        this->ladoA = ladoA;
        this->ladoB = ladoB;
        this->ladoC = ladoC;
        this->altura = altura;
    }
}

bool Triangulo::ehTriangulo(double a, double b, double c){
    if(a+b>c && a+c>b && b+c>a){
        return true;
    }
    else{
        return false;
    }
}

double Triangulo::getArea(){
    return ladoC*altura/2;
}

double Triangulo::getPerimetro(){
    return ladoA+ladoB+ladoC;
}

double Triangulo::getAltura(void){
    return altura;
}

void Triangulo::setAltura(double altura){
    this->altura = altura;
}

double Triangulo::getLadoA(void){
    return ladoA;
}

double Triangulo::getLadoB(void){
    return ladoB;
}

double Triangulo::getLadoC(void){
    return ladoC;
}

Retangulo::Retangulo(double ladoA, double ladoB){
    this->ladoA = ladoA;
    this->ladoB = ladoB;
}
double Retangulo::getArea(){
    return (ladoA*ladoB)/2;
}
double Retangulo::getPerimetro(){
    return 2*ladoA + 2*ladoB;
}
double Retangulo::getLadoA(){
    return ladoA;
}
double Retangulo::getLadoB(){
    return ladoB;
}
void Retangulo::setLadoA(double ladoA){
    this->ladoA = ladoA;
}
void Retangulo::setLadoB(double ladoB){
    this->ladoB = ladoB;
}

Quadrado::Quadrado(double lado){
    this->lado = lado;
}
double Quadrado::getArea(){
    return lado*lado;
}
double Quadrado::getPerimetro(){
   return 4*lado;
}
double Quadrado::getLado(){
    return lado;
}
void Quadrado::setLado(double lado){
    this->lado = lado;
}