//implementacao dos metodos das classes paciente e pacientes
#include "classes.hpp"
//construtor padrao
Paciente::Paciente(){
    this->cpf = "000.000.000-00";
    this->nome = "Default";
    this->data_nasc = Data();
}
//construtor com argumentos
Paciente::Paciente(string cpf, string nome, Data data_nasc){
    if(validaCpf(cpf) && validaNome(nome) && data_nasc.dataValida(data_nasc.getDia(), data_nasc.getMes(), data_nasc.getAno())){
        this->cpf = cpf;
        this->nome = nome;
        this->data_nasc = data_nasc;
        this->formataCpf();
    }
    else{
        this->cpf = "000.000.000-00";
        this->nome = "Default";
        this->data_nasc = Data();
    }
}
//getters e setters
void Paciente::setCpf(string cpf){
    if(validaCpf(cpf)){
        this->cpf = cpf;
        this->formataCpf();
    }
    else{
        cout<<"CPF Invalido!!"<<endl;
        this->cpf = "000.000.000-00";
    }
}

void Paciente::setNome(string nome){
    this->nome = nome;
}

void Paciente::setDataNasc(Data data_nasc){
    this->data_nasc = data_nasc;
}

string Paciente::getCpf(void){
    return this->cpf;
}

string Paciente::getNome(void){
    return this->nome;
}

Data Paciente::getDataNasc(void){
    return this->data_nasc;
}
//retira pontos e tracos do cpf sem parametros
string Paciente::converteCpf(){
    string cpf_aux;
    for(auto caracter: cpf){
        if(isdigit(caracter))
            cpf_aux += caracter;
    }
    return cpf_aux;
  
}
//retira pontos e tracos do cpf com parametros
string Paciente::converteCpf(string cpf){
    string cpf_aux;
    for(auto caracter: cpf){
        if(isdigit(caracter))
            cpf_aux += caracter;
    }
    return cpf_aux;
}
//imprime os dados do paciente
void Paciente::printPaciente(void){
    cout<<"CPF: "<<this->cpf<<endl;
    cout<<"Nome: "<<this->nome<<endl;
    cout<<"Data de Nascimento: "<<this->data_nasc.getDia()<<"/"<<this->data_nasc.getMes()<<"/"<<this->data_nasc.getAno()<<endl;
}
//verifica se cpf é valido sem parametros
bool Paciente::validaCpf(){
    if (cpf.size() == 11 || (cpf.size() == 14 && cpf[3] == '.' && cpf[7] == '.' && cpf[11] == '-')){
        return true;
    }
    return false;
}
//verifica se cpf é valido com parametros
bool Paciente::validaCpf(string cpf){
    if (cpf.size() == 11 || (cpf.size() == 14 && cpf[3] == '.' && cpf[7] == '.' && cpf[11] == '-')){
        return true;
    }
    return false;
}
//verifica se nome é valido
bool Paciente::validaNome(string nome){
    if(nome.size() > 0){
        for(auto caracter: nome){
            if(!isalpha(caracter) && caracter != ' '){
                return false;
            }
        }
        return true;
    }
    else{
        return false;
    }
}
//formata o cpf para o formato xxx.xxx.xxx-xx
void Paciente::formataCpf(void){
    string cpf_aux;
    int i = 0;
    while(i < cpf.size()){
        if(i == 3){
            if(cpf[i] != '.'){
                cpf_aux += '.'; 
                cpf_aux += cpf[i];
            }
            else{
                cpf_aux += cpf[i];
            }
        }
        else if(i == 6){
            if(cpf[i+1] != '.'){
                cpf_aux += '.';
                cpf_aux += cpf[i];
            }
            else{
                cpf_aux += cpf[i];
            }
        }
        else if(i == 9){
            if(cpf[i+2] != '-'){
                cpf_aux += '-';
                cpf_aux += cpf[i];
            }
            else{
                cpf_aux += cpf[i];
            }
        }
        else{
            cpf_aux += cpf[i];
        }
        i++;
    }
    this->cpf = cpf_aux;
}
//insere paciente na lista de pacientes
void Pacientes::inserePaciente(Paciente * paciente){
    this->lista_pacientes.push_back(paciente);
}
//excui paciente da lista de pacientes
void Pacientes::excluirPaciente(Paciente * paciente){
    for(auto it = this->lista_pacientes.begin(); it != this->lista_pacientes.end(); it++){
        if((*it)->getCpf() == paciente->getCpf()){
            this->lista_pacientes.erase(it);
            break;
        }
    }
}
//retorna a lista de pacientes
vector<Paciente*> Pacientes::obterListaPacientes(void){
    return this->lista_pacientes;
}
//verifica se um paciente existe na lista de pacientes pelo cpf
bool Pacientes::verificaExiste(string cpf){
    for(auto pac: this->lista_pacientes){
        if(pac->converteCpf() == pac->converteCpf(cpf)){
            return true;
        }
    }
    return false;
}
//obtem um paciente da lista de pacientes pelo cpf
Paciente * Pacientes::obterPaciente(string cpf){
    for(auto pac: this->lista_pacientes){
        if(pac->converteCpf() == pac->converteCpf(cpf)){
            return pac;
        }
    }
    return nullptr;
}
