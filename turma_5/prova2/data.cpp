//implementação da classe Data
#include "classes.hpp"
//construtor padrao
Data::Data(){
    this->dia = 1;
    this->mes = 1;
    this->ano = 1900;
}
//construtor com argumentos
Data::Data(int dia, int mes, int ano){
    if(dataValida(dia, mes, ano)){
        this->dia = dia;
        this->mes = mes;
        this->ano = ano;
    }
    else{
        cout<<"Data Invalida!! "<<endl;
        this->dia = 1;
        this->mes = 1;
        this->ano = 1900;
    }
}
//verifica se a data e valida sem parametros
bool Data::dataValida(){
    int diaNoMes = 0;
    
    if(ano < 1900 || ano > 2100)
        return false;

    else if(mes < 1 || mes > 12){
        return false;
    }
    else if(mes == 2){
        diaNoMes = (ano % 4 == 0 && (ano % 100 != 0 || ano %400 == 0))? 29 : 28;
    }
    else if (mes == 4 || mes == 6 || mes == 9 || mes == 11){
       diaNoMes = 31;
    }
    else
        diaNoMes = 30;
    if(dia > diaNoMes)
        return false;
    
    return true;
}
//verifica se a data e valida com parametros
bool Data::dataValida(int dia, int mes, int ano){
    int diaNoMes = 0;
    
    if(ano < 1900 || ano > 2100)
        return false;

    else if(mes < 1 || mes > 12){
        return false;
    }
    else if(mes == 2){
        diaNoMes = (ano % 4 == 0 && (ano % 100 != 0 || ano %400 == 0))? 29 : 28;
    }
    else if (mes == 4 || mes == 6 || mes == 9 || mes == 11){
       diaNoMes = 31;
    }
    else
        diaNoMes = 30;
    if(dia > diaNoMes)
        return false;
    
    return true;
}
//getters e setters
void Data::setDia(int dia){
    this->dia = dia;
}

void Data::setMes(int mes){
    this->mes = mes;
}

void Data::setAno(int ano){
    this->ano = ano;
}

int Data::getDia(void){
    return this->dia;
}

int Data::getMes(void){
    return this->mes;
}

int Data::getAno(void){
    return this->ano;
}
//formata a data para string
string Data::toString(void){
    string data;
    data = (dia<10? "0":"") + to_string(dia) + "/" + (mes<10? "0":"") + to_string(mes) + "/" + to_string(ano);
    return data;
}
//printa data no formato dd/mm/aaaa
void Data::printData(){
    cout<<dia<<" / "<<mes<<" / "<<ano<<endl;
}

