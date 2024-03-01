//implementacao da das funcionalidades do menu de medicos
#include "func_prog_med.hpp"

MenuMedico menuM;
//inclui novo medico em uma lista de medicos
void incluirMedico(Medicos * medicos){
    string crm;
    string nome;
    string espec;
    Medico *med = new Medico;

    menuM.limpa_tela();
    cout<<"______________________________________________"<<endl;
    cout<<"             Inclusao de Medico               "<<endl;
    cout<<"______________________________________________"<<endl;
    cout<<"CRM do Medico: ";cin>>crm;
    while(!med->validaCrm(crm)){
        cout<<"CRM Invalido! Digite novamente: ";cin>>crm;
    }
    if(!medicos->verificaExiste(crm)){
        cout<<"Nome do Medico: ";cin>>nome;
        while(!med->validaNome(nome)){
            cout<<"Nome Invalido! Digite novamente: ";cin>>nome;
        }
        cout<<"Especialidade: ";cin>>espec;
        med->setCrm(crm);
        med->setNome(nome);
        med->setEspecialidade(espec);
        medicos->insereMedico(med);
    }
    else{
        cout<<"CRM do Medico ja listado!"<<endl;
    }
    menuM.pausa();
}
//exclui medico de uma lista de medicos
void excluirMedico(Medicos * medicos){
    string crm;
    bool encontrado = false;

    menuM.limpa_tela();
    cout<<"______________________________________________"<<endl;
    cout<<"                Excluir Medico                "<<endl;
    cout<<"______________________________________________"<<endl;
    cout<<"Digite o CRM do Medico: ";cin>>crm;
    for(auto med: medicos->obterListaMedicos()){
        if(med->converteCrm() == med->converteCrm(crm)){
            medicos->excluirMedico(med);
            cout<<"Medico excluido com sucesso!"<<endl;
            encontrado = true;
            delete med;
            break;
        }
    }

    if(!encontrado){
        cout<<"Medico nao encontrado!"<<endl;
    }
    menuM.pausa();
}
//altera dados de um medico de uma lista de medicos
void alterarMedico(Medicos * medicos){
    string crm;
    char opcao;
    bool encontrado = false;
    menuM.limpa_tela();
    cout<<"______________________________________________"<<endl;
    cout<<"                Alterar Medico                "<<endl;
    cout<<"______________________________________________"<<endl;
    cout<<"Digite o CRM do Medico: ";cin>>crm;
    for(auto med: medicos->obterListaMedicos()){
        if(med->converteCrm() == med->converteCrm(crm)){
            cout<<"Medico encontrado com sucesso!"<<endl;
            cout<<"- - - - - - - - - - - - - - - - - - - "<<endl;
            med->printMedico();
            encontrado = true;
            cout<<"Deseja alterar o nome do Medico? Digite <s> para alterar: ";cin>>opcao;
            if(opcao == 's'){
                string nome;
                cout<<"Digite o novo nome do Medico: ";cin>>nome;
                while(!med->validaNome(nome)){
                    cout<<"Nome Invalido! Digite novamente: ";cin>>nome;
                }
                med->setNome(nome);
                cout<<"Nome alterado com sucesso!"<<endl;
            }
            cout<<"Deseja alterar a especialidade do Medico? Digite <s> para alterar: ";cin>>opcao;
            if(opcao == 's'){
                string espec;
                cout<<"Digite a nova especialidade do Medico: ";cin>>espec;
                med->setEspecialidade(espec);
                cout<<"Especialidade alterada com sucesso!"<<endl;
            }
        }
    }
    if(!encontrado){
        cout<<"Medico nao encontrado!"<<endl;
    }
    menuM.pausa();
}
//lista todos os medicos de uma lista de medicos
void listarMedicos(Medicos * medicos){
    vector<Medico*> lista_medicos;
    lista_medicos = medicos->obterListaMedicos();
    menuM.limpa_tela();
    cout<<"______________________________________________"<<endl;
    cout<<"             Listagem de Medicos              "<<endl;
    cout<<"______________________________________________"<<endl;
    for(auto med: lista_medicos){
        med->printMedico();
        cout<<"- - - - - - - - - - - - - - - - - - - "<<endl;
    }
    menuM.pausa();
}
//localiza um medico especifico atraves do crm
void localizarMedico(Medicos * medicos){
    string crm;
    bool encontrado = false;
    menuM.limpa_tela();
    cout<<"______________________________________________"<<endl;
    cout<<"                Localizar Medico              "<<endl;
    cout<<"______________________________________________"<<endl;
    cout<<"Digite o CRM do Medico: ";cin>>crm;
    for(auto med: medicos->obterListaMedicos()){
        if(med->converteCrm() == med->converteCrm(crm)){
            cout<<"Medico encontrado com sucesso!"<<endl;
            cout<<"- - - - - - - - - - - - - - - - - - - "<<endl;
            med->printMedico();
            cout<<"- - - - - - - - - - - - - - - - - - - "<<endl;
            encontrado = true;
        }
    }
    if(!encontrado){
        cout<<"Medico nao encontrado!"<<endl;
    }
    menuM.pausa();
}
