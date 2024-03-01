//implementacao dos metodos da classe medico e medicos
#include "classes.hpp"
//construtor padrao de medico
Medico::Medico(){
    this->crm = "00000000-0/BR";
    this->nome = "Default";
    this->especialidade = "Default";
}
//construtur com argumentos de medico
Medico::Medico(string crm, string nome, string especialidade){
    if(validaCrm(crm) && validaNome(nome)){
        this->crm = crm;
        this->nome = nome;
        this->especialidade = especialidade;
        this->formataCrm();
    }
    else{
        this->crm = "00000000-0/BR";
        this->nome = "Default";
        this->especialidade = "Default";
    }
}
//setters e getters
void Medico::setCrm(string crm){
    if(validaCrm(crm)){
        this->crm = crm;
        this->formataCrm();
    }
    else{
        cout<<"Crm Invalido!!"<<endl;
        this->crm = "000000000";
    }
}

void Medico::setNome(string nome){
    this->nome = nome;
}

void Medico::setEspecialidade(string especialidade){
    this->especialidade = especialidade;
}

string Medico::getCrm(void){
    return this->crm;
}

string Medico::getNome(void){
    return this->nome;
}

string Medico::getEspecialidade(void){
    return this->especialidade;
}
//funcao para retirar pontos e tracos do crm sem parametros
string Medico::converteCrm(){
    string crm_aux;
    for(auto caracter: crm){
        if(isdigit(caracter) || isalpha(caracter))
            crm_aux += caracter;
    }
    return crm_aux;    
}
//funcao para retirar pontos e tracos do crm com parametros
string Medico::converteCrm(string crm){
    string crm_aux;
    for(auto caracter: crm){
        if(isdigit(caracter) || isalpha(caracter))
            crm_aux += caracter;
    }
    return crm_aux;    
}
//funcao para imprimir dados de um medico
void Medico::printMedico(void){
    cout<<"Crm: "<<this->crm<<endl;
    cout<<"Nome: "<<this->nome<<endl;
    cout<<"Especialidade: "<<this->especialidade<<endl;
}
//funcao para validar crm sem parametros
bool Medico::validaCrm(){
    if((crm.size() == 11 && isalpha(crm[9]) && isalpha(crm[10])) || (crm.size() == 13 && crm[8] == '-' && crm[10] == '/' && isalpha(crm[11]) && isalpha(crm[12]))){
        return true;
    }
    else{
        return false;
    }
}
//funcao para validar crm com parametros
bool Medico::validaCrm(string crm){
    if((crm.size() == 11 && isalpha(crm[9]) && isalpha(crm[10])) || (crm.size() == 13 && crm[8] == '-' && crm[10] == '/' && isalpha(crm[11]) && isalpha(crm[12]))){
        return true;
    }
    else{
        return false;
    }
}
//funcao para validar nome
bool Medico::validaNome(string nome){
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
//funcao para formatar crm para o padrao 00000000-0/BR
void Medico::formataCrm(void){
    string crm_aux;
    int i = 0;
    while(i < crm.size()){
        if(i == 8){
            if(crm[i] != '-'){
                crm_aux += '-'; 
                crm_aux += crm[i];
            }
            else{
                crm_aux += crm[i];
            }
        }
        else if(i == 9){
            if(isalpha(crm[i])){
                crm_aux += '/';
                crm_aux += crm[i];
            }
            else{
                crm_aux += crm[i];
            }
        }
        else{
            crm_aux += crm[i];
        }
        i++;
    }
    this->crm = crm_aux;
}
//funcao para inserir medico em uma lista de medicos
void Medicos::insereMedico(Medico * medico){
    this->lista_medicos.push_back(medico);
}
//funcao para excluir um medico a partir do crm dele
void Medicos::excluirMedico(Medico * medico){
    for(auto it = this->lista_medicos.begin(); it != this->lista_medicos.end(); it++){
        if((*it)->converteCrm() == medico->converteCrm()){
            this->lista_medicos.erase(it);
            break;
        }
    }
}
//funcao que retorna a lista de medicos
vector<Medico*> Medicos::obterListaMedicos(void){
    return this->lista_medicos;
}
//funcao para verificar se um medico existe na lista de medicos
bool Medicos::verificaExiste(string crm){
    for(auto med: this->lista_medicos){
        if(med->converteCrm() == med->converteCrm(crm)){
            return true;
        }
    }
    return false;
}

Medico * Medicos::obterMedico(string crm){
    for(auto med: this->lista_medicos){
        if(med->converteCrm() == med->converteCrm(crm)){
            return med;
        }
    }
    return nullptr;
}