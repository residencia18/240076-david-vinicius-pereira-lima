//Implementação da classe Consulta e Consultas 
#include "classes.hpp"
//Construtor padrao
Consulta::Consulta(){
    this->realizada = false;
    this-> data_hora = DataHora();
    this->duracao = Hora();
    this->convenio = "Convenio";
    this->paciente =  new Paciente;
    this->medico = new Medico();
}
//Construtor com argumentos
Consulta::Consulta(DataHora data_hora, Hora duracao, string convenio, Paciente * paciente, Medico * medico){
    if(data_hora.verificaDataHora() && duracao.horaValida() && paciente->validaCpf() && medico->validaCrm()){
        this->realizada = false;
        this-> data_hora = data_hora;
        this->duracao = duracao;
        this->convenio = convenio;
        this->paciente = paciente;
        this->medico = medico;
    }
    else{
        this->realizada = false;
        this-> data_hora = DataHora();
        this->duracao = Hora();
        this->convenio = "Convenio";
        this->paciente = new Paciente;
        this->medico = new Medico();
    }
}
//getters e setters
bool Consulta::getRealizada(void){
    return realizada;
}

void Consulta::setRealizada(bool realizada){
    this->realizada = realizada;
}

DataHora Consulta::getDataHora(void){
    return data_hora;
}

void Consulta::setDataHora(DataHora data_hora){
    if(data_hora.verificaDataHora()){
        this->data_hora = data_hora;
    }
    else{
        cout<<"Data_Hora invalida!!"<<endl;
        this->data_hora = DataHora();
    }
}

Hora Consulta::getDuracao(void){
    return duracao;
}

void Consulta::setDuracao(Hora duracao){
    if(duracao.horaValida())
        this->duracao = duracao;
    else{
        cout<<"Duracao invalida!!"<<endl;
        this->duracao = Hora();
    }
}

string Consulta::getConvenio(void){
    return convenio;
}        

void Consulta::setConvenio(string convenio){
    this->convenio = convenio;
}

Paciente * Consulta::getPaciente(void){
    return paciente;
}

void Consulta::setPaciente(Paciente * paciente){
    if(paciente->validaCpf()){
        this->paciente = paciente;
    }
    else{
        cout<<"Paciente invalido!!"<<endl;
        this->paciente = new Paciente;
    }
}

Medico * Consulta::getMedico(void){
    return medico;
}

void Consulta::setMedico(Medico * medico){
    if(medico->validaCrm()){
        this->medico = medico;
    }
    else{
        cout<<"Medico invalido!!"<<endl;
        this->medico = new Medico();
    }
}
//printa os dados da consulta
void Consulta::printConsulta(void){
    cout<<"- - - - - - - - - - - - - - - - - - - - - - - - - - - "<<endl;
    cout<<"Realizada: "<<(realizada == false? "n":"s")<<endl;
    cout<<"Data e Hora: "<<endl;
    data_hora.printDataHora();
    cout<<"Duracao: "<<duracao.toString()<<endl;
    cout<<"Convenio: "<<convenio<<endl;
    paciente->printPaciente();
    medico->printMedico();
    cout<<"- - - - - - - - - - - - - - - - - - - - - - - - - - - "<<endl;
}
//insere consulta na lista de consultas
void Consultas::insereConsulta(Consulta * consulta){
    this->lista_consultas.push_back(consulta);
}
//lista todas as consultas
vector<Consulta*> Consultas::obterListaConsultas(void){
    return this->lista_consultas;
}

// Exclui a consulta da lista de consultas
void Consultas::excluirConsulta(Consulta * consulta){
    for(int i = 0; i < this->lista_consultas.size(); i++){
        if(lista_consultas[i] == consulta){
            lista_consultas.erase(lista_consultas.begin() + i);
        }
    }
}