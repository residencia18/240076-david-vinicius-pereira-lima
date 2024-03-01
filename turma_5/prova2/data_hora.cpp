//implementação da classe DataHora
#include "classes.hpp"

//construtor padrao
DataHora::DataHora(){
    this->data = Data();
    this->hora  = Hora(); 
}
//construtor com argumentos
DataHora::DataHora(Data data, Hora hora){
    this->data = data;
    this->hora = hora;
}
//getters e setters
Data DataHora::getData(){
    return data;
}

void DataHora::setData(Data data){
    this->data = data;
}

Hora DataHora::getHora(){
    return hora;
}

void DataHora::setHora(Hora hora){
    this->hora = hora;
}
//printa a data e hora no formato dd/mm/aaaa hh:mm:ss
void DataHora::printDataHora(){
    cout<<data.toString()<<" "<<hora.toString()<<endl;
}

//verifica se a data e hora sao validas sem parametros
bool DataHora::verificaDataHora(){
    if(data.dataValida() && hora.horaValida())
        return true;
    else
        return false;
}
//verifica se a data e hora sao validas com parametros
bool DataHora::verificaDataHora(Data data, Hora hora){
    if(data.dataValida() && hora.horaValida())
        return true;
    else
        return false;
}
