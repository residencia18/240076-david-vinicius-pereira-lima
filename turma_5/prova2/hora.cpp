//implementacao dos metodos da classe hora
#include "classes.hpp"
//construtor padrao
Hora::Hora(){
    this->hora = 0;
    this->minuto = 0;
    this->segundo = 0;
}
//construtor com argumentos
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
//verifica se a hora e valida sem parametros
bool Hora::horaValida(){
    if(hora < 0 || hora > 23)
        return false;
    else if(minuto < 0 || minuto > 59)
        return false;
    else if(segundo < 0 || segundo > 59)
        return false;
    else
        return true;
}
//verifica se a hora e valida com parametros
bool Hora::horaValida(int h, int m, int s){
    if(h < 0 || h > 23)
        return false;
    else if(m < 0 || m > 59)
        return false;
    else if(s < 0 || s > 59)
        return false;
    else
        return true;
}
//printa a hora no formato hh:mm:ss
void Hora::printHora(void){
    cout<<hora<<" : "<<minuto<<" : "<<segundo<<endl;
}
//retorna a hora no formato hh:mm:ss
string Hora::toString(void){
    string str;
    str = (hora<10? "0":"") + to_string(hora) + ":" + (minuto<10? "0":"") + to_string(minuto) + ":" + (segundo<10? "0":"") + to_string(segundo);
    return str;
}