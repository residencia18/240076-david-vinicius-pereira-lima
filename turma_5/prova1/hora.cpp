//implementacao da classe hora
#include "classes.hpp"
//construtor padrao
Hora::Hora(){
    this->hora = 0;
    this->minuto = 0;
    this->segundo = 0;
}
//construtor com parametros
Hora::Hora(int h, int m, int s){
    if(horaValida(h, m, s)){
        this->hora = h;
        this->minuto = m;
        this->segundo = s;
    }
    else{
        cout<<"Hora Invalida!!"<<endl;
        this->hora = 0;
        this->minuto = 0;
        this->segundo = 0;
    }
}
//getters e setters
void Hora::setHora(int hora){
    this->hora = hora;
}

void Hora::setMinuto(int minuto){
    this->minuto = minuto;
}

void Hora::setSegundo(int segundo){
    this->segundo = segundo;
}

int Hora::getHora(void){
    return this->hora;
}

int Hora::getMinuto(void){
    return this->minuto;
}   

int Hora::getSegundo(void){
    return this->segundo;
}
//funcao para validar hora
bool Hora::horaValida(int h, int m, int s){
    if(h < 0 || h > 23)
        return false;
    else if(m < 0 || m > 59)
        return false;
    else if(s < 0 || s > 59)
        return false;
}
//funcao para imprimir hora
void Hora::printHora(void){
    cout<<hora<<" : "<<minuto<<" : "<<segundo<<endl;
}