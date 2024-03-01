//implementacao da das funcionalidades do menu de pacientes

#include "func_prog_pac.hpp"

MenuPaciente menuP;
//inclui um paciente em uma lista de pacientes
void incluirPaciente(Pacientes * pacientes){
    string cpf;
    string nome;
    int dia, mes, ano;
    Paciente *pac = new Paciente;

    menuP.limpa_tela();
    cout<<"______________________________________________"<<endl;
    cout<<"             Inclusao de Paciente             "<<endl;
    cout<<"______________________________________________"<<endl;
    cout<<"CPF do Paciente: ";cin>>cpf;
    while(!pac->validaCpf(cpf)){
        cout<<"CPF Invalido! Digite novamente: ";cin>>cpf;
    }
    if(!pacientes->verificaExiste(cpf)){
        cout<<"Nome do Paciente: ";cin>>nome;
        while (!pac->validaNome(nome)){
            cout<<"Nome Invalido! Digite novamente: ";cin>>nome;
        }
        cout<<"Data de Nascimento do Paciente: ";cin>>dia>>mes>>ano;
        Data data_nasc(dia,mes,ano);
        while(!data_nasc.dataValida(dia,mes,ano)){
            cout<<"Data Invalida! Digite novamente: ";cin>>dia>>mes>>ano;
        }
        pac->setCpf(cpf);
        pac->setNome(nome);
        pac->setDataNasc(data_nasc);
        pacientes->inserePaciente(pac);
    }
    else{
        cout<<"CPF do Paciente ja listado!"<<endl;
    }
    menuP.pausa();
}
//exclui um paciente de uma lista de pacientes
void excluirPaciente(Pacientes * pacientes){
    string cpf;
    bool encontrado = false;

    menuP.limpa_tela();
    cout<<"______________________________________________"<<endl;
    cout<<"                Excluir Paciente              "<<endl;
    cout<<"______________________________________________"<<endl;
    cout<<"Digite o CPF do Paciente: ";cin>>cpf;
    for(auto pac: pacientes->obterListaPacientes()){
        if(pac->converteCpf() == pac->converteCpf(cpf)){
            pacientes->excluirPaciente(pac);
            cout<<"Paciente excluido com sucesso!"<<endl;
            encontrado = true;
            delete pac;
            break;
        }
    }
    if(!encontrado){
        cout<<"Paciente nao encontrado!"<<endl;
    }
    menuP.pausa();
}

//altera dados de um paciente de uma lista de pacientes
void alterarPaciente(Pacientes * pacientes){
    string cpf;
    char opcao;
    bool encontrado = false;
    menuP.limpa_tela();
    cout<<"______________________________________________"<<endl;
    cout<<"                Alterar Paciente              "<<endl;
    cout<<"______________________________________________"<<endl;
    cout<<"Digite o CPF do Paciente: ";cin>>cpf;
    for(auto pac: pacientes->obterListaPacientes()){
        if(pac->converteCpf() == pac->converteCpf(cpf)){
            cout<<"Paciente encontrado com sucesso!"<<endl;
            cout<<"- - - - - - - - - - - - - - - - - - - "<<endl;
            pac->printPaciente();
            cout<<"- - - - - - - - - - - - - - - - - - - "<<endl;
            encontrado = true;
            cout<<"Deseja alterar o nome do Paciente? Digite <s> para alterar: ";cin>>opcao;
            if(opcao == 's'){
                string nome;
                cout<<"Digite o novo nome do Paciente: ";cin>>nome;
                while(!pac->validaNome(nome)){
                    cout<<"Nome Invalido! Digite novamente: ";cin>>nome;
                }
                pac->setNome(nome);
                cout<<"Nome alterado com sucesso!"<<endl;
            }
            cout<<"Deseja alterar a data de nascimento do Paciente? Digite <s> para alterar: ";cin>>opcao;
            if(opcao == 's'){
                Data data_nasc;
                int dia, mes, ano;
                cout<<"Digite a nova data de nascimento do Paciente: ";cin>>dia>>mes>>ano;
                while(!data_nasc.dataValida(dia,mes,ano)){
                    cout<<"Data Invalida! Digite novamente: ";cin>>dia>>mes>>ano;
                }
                data_nasc.setDia(dia); data_nasc.setMes(mes); data_nasc.setAno(ano);
                pac->setDataNasc(data_nasc);
                cout<<"Data de nascimento alterada com sucesso!"<<endl;
            }
        }
    }
    if(!encontrado){
        cout<<"Paciente nao encontrado!"<<endl;
    }
    menuP.pausa();
}
//listar todas os pacientes da lista de pacientes
void listarPacientes(Pacientes * pacientes){
    vector<Paciente*> lista_pacientes;
    lista_pacientes = pacientes->obterListaPacientes();
    menuP.limpa_tela();
    cout<<"______________________________________________"<<endl;
    cout<<"             Listagem de Pacientes            "<<endl;
    cout<<"______________________________________________"<<endl;
    for(auto pac: lista_pacientes){
        pac->printPaciente();
        cout<<"- - - - - - - - - - - - - - - - - - - "<<endl;
    }
    menuP.pausa();

}
//localiza um paciente de uma lista de pacientes
void localizarPaciente(Pacientes * pacientes){
    string cpf;
    bool encontrado = false;
    menuP.limpa_tela();
    cout<<"______________________________________________"<<endl;
    cout<<"                Localizar Paciente            "<<endl;
    cout<<"______________________________________________"<<endl;
    cout<<"Digite o CPF do Paciente: ";cin>>cpf;
    for(auto pac: pacientes->obterListaPacientes()){
        if(pac->converteCpf() == pac->converteCpf(cpf)){
            cout<<"Paciente encontrado com sucesso!"<<endl;
            cout<<"- - - - - - - - - - - - - - - - - - - "<<endl;
            encontrado = true;
            pac->printPaciente();
        }
    }
    if(!encontrado){
        cout<<"Paciente nao encontrado!"<<endl;
    }

    menuP.pausa();
}