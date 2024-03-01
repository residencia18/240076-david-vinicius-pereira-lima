//implementacao da das funcionalidades do menu de consultas
#include "func_prog_con.hpp"

MenuConsulta menuC;
//incluir nova consulta em uma lista de consultas 
void incluirConsulta(Consultas * consultas, Pacientes * pacientes, Medicos * medicos){
    string cpf, crm;
    Consulta *cons = new Consulta;
    Paciente *pac = new Paciente;
    Medico *med = new Medico;
    int dia, mes, ano, hora, min, seg;
    Data data;
    DataHora data_hora;
    Hora horario, duracao;

    menuC.limpa_tela();
    cout<<"______________________________________________"<<endl;
    cout<<"             Inclusao de Consulta             "<<endl;
    cout<<"______________________________________________"<<endl;
    cout<<"CPF do Paciente: ";cin>>cpf;
    if(!pacientes->verificaExiste(cpf)){
        cout<<"CPF nao encontrado!! Retornando ao menu anterior.";
        return;
    }
    cout<<"CRM do Medico: ";cin>>crm;
    if(!medicos->verificaExiste(crm)){
        cout<<"CRM nao encontrado!! Retornando ao menu anterior.";
        return;
    }

    pac = pacientes->obterPaciente(cpf);
    cons->setPaciente(pac);

    med = medicos->obterMedico(crm);
    cons->setMedico(med);
    
    cout<<"Data e hora da Consulta(Dia Mes Ano Hora Min Seg): ";cin>>dia;cin>>mes;cin>>ano;cin>>hora;cin>>min;cin>>seg;
    data.setDia(dia); data.setMes(mes); data.setAno(ano);
    horario.setHora(hora); horario.setMinuto(min); horario.setSegundo(seg);
    data_hora.setData(data); data_hora.setHora(horario);
    cout<<"Duracao da Consulta: ";cin>>hora;cin>>min;cin>>seg;
    duracao.setHora(hora); duracao.setMinuto(min); duracao.setMinuto(seg);
    
    cons->setDataHora(data_hora);
    cons->setDuracao(duracao);
    consultas->insereConsulta(cons);
 

    menuC.pausa();
}

void excluirConsulta(Consultas * consultas, Medicos * medicos, Pacientes * pacientes){
    string crm;
    string cpf;
    bool encontrado = false;
    Consulta *cons = new Consulta;
    Paciente *pac = new Paciente;
    Medico *med = new Medico;


    menuC.limpa_tela();
    cout<<"______________________________________________"<<endl;
    cout<<"                Excluir Consulta                "<<endl;
    cout<<"______________________________________________"<<endl;
    cout<<"Digite o CRM do Medico: ";cin>>crm;
    menuC.limpa_tela();
    
    if(!medicos->verificaExiste(crm)){
        cout<<"CRM nao encontrado!! Retornando ao menu anterior."<<endl;
        menuC.pausa();
        return;
    }

    med = medicos->obterMedico(crm);

    cout<<"Lista de Pacientes"<<endl;
    for(auto cons: consultas->obterListaConsultas()){
        if(med->converteCrm(cons->getMedico()->getCrm()) == med->converteCrm(crm)){
            cout<<"______________________________________________"<<endl;
            cout << "Paciente na consulta: " << endl;
            cons->getPaciente()->printPaciente();
            encontrado = true;
            cout<<"______________________________________________"<<endl;

        }

    }
    
    cout<<"Digite o CPF do Paciente para excluir a consulta: ";cin>>cpf;

    if(!pacientes->verificaExiste(cpf)){
        cout<<"CPF nao encontrado!! Retornando ao menu anterior."<<endl;
        menuC.pausa();
        return;
    }
    
    for(auto cons: consultas->obterListaConsultas()){
        if(pac->converteCpf(cons->getPaciente()->getCpf()) == pac->converteCpf(cpf)){
            cout<<"______________________________________________"<<endl;
            string nome_paciente = cons->getPaciente()->getNome();            
            consultas->excluirConsulta(cons);
            delete cons;    
            cout << "Consulta do paciente " << nome_paciente << " excluido com sucesso!!" << endl;        
            encontrado = true;
            cout<<"______________________________________________"<<endl;

        }

    }

    if(!encontrado){
        cout <<"Medico ou paciente nao encontrado!"<<endl;
    }
    menuC.pausa();
}

void alterarConsulta(Consultas * consultas, Medicos * medicos, Pacientes * pacientes){
    string crm;
    string cpf;
    string convenio;
    char opcao;
    int dia, mes, ano, hora, min, seg;
    Data data;
    DataHora data_hora;
    Hora horario, duracao;
    Paciente *pac = new Paciente;
    Medico *med = new Medico;
    bool med_encontrado = false;
    bool pac_encontrado = false;
    menuC.limpa_tela();
    cout<<"______________________________________________"<<endl;
    cout<<"                Alterar Consulta                "<<endl;
    cout<<"______________________________________________"<<endl;
    cout<<"Digite o CRM do Medico: ";cin>>crm;

    if(!medicos->verificaExiste(crm)){
        cout<<"CRM nao encontrado!! Retornando ao menu anterior.";
        return;
    }
  
    med = medicos->obterMedico(crm);

    cout<<"Lista de Pacientes do medico " << med->getNome() << endl;
    for(auto cons: consultas->obterListaConsultas()){
        if(med->converteCrm(cons->getMedico()->getCrm()) == med->converteCrm(crm)){
            med_encontrado = true; // Medico tem consultas
            cout<<"______________________________________________"<<endl;
            cout << "Paciente na consulta: " << endl;
            cons->getPaciente()->printPaciente();
            cout<<"______________________________________________"<<endl;

        }
    }
    
    if(!med_encontrado){
        cout<<"Medico sem consultas!"<<endl;
        menuC.pausa();
        return;
    }

    cout<<"Digite o CPF do Paciente que deseja alterar a consulta: ";cin>>cpf;
    
    if(!pacientes->verificaExiste(cpf)){
        cout<<"CPF nao encontrado!! Retornando ao menu anterior.";
        return;
    }
    
    for(auto cons: consultas->obterListaConsultas()){
        if(pac->converteCpf(cons->getPaciente()->getCpf()) == pac->converteCpf(cpf)){
            pac_encontrado = true; // Paciente tem consultas com o medico procurado
            cout<<"______________________________________________"<<endl;

            cout <<  "Voce deseja registrar a consulta como realizada ? Digite <s> para alterar: " << endl;cin>>opcao;
            if(opcao == 's'){
                cons->setRealizada(true);
                cout<<"Consulta alterada com sucesso!"<<endl;
            }
            else
            {
                cout <<  "Voce deseja alterar a data da consulta? Digite <s> para alterar:" << endl;cin>>opcao;
                if(opcao == 's'){
                    cout << "Digite a data da consulta(Dia Mes Ano): ";cin>>dia;cin>>mes;cin>>ano;
                    data.setAno(ano);data.setMes(mes);data.setDia(dia);
                    cout<<"Consulta alterada com sucesso!!"<<endl;
                    data_hora.setData(data); // Define a nova data pra consulta
                    data_hora.setHora(cons->getDataHora().getHora()); //A hora da consulta permanece a mesma
                    cons->setDataHora(data_hora);
                }

                cout <<  "Voce deseja alterar a hora da consulta?" << endl; cin>>opcao;
                if(opcao == 's'){
                    cout << "Digite a hora da consulta(Hora Min Seg): ";
                    cin>>hora;cin>>min;cin>>seg;
                    cons->setDataHora((DataHora(cons->getDataHora().getData(), Hora(hora, min, seg))));
                    cout<<"Consulta alterada com sucesso!!"<<endl;
                }
                
                cout <<  "Voce deseja alterar a duracao da consulta?" << endl; cin>>opcao;
                if(opcao == 's'){
                    cout<<"Duracao da Consulta: ";cin>>hora;cin>>min;cin>>seg;
                    duracao.setHora(hora); duracao.setMinuto(min); duracao.setMinuto(seg);
                    cons->setDuracao(duracao);
                }

                cout <<  "Voce deseja alterar o convenio?" << endl;  cin>>opcao;
                if(opcao == 's'){
                    cout<<"Covenio: ";cin>>convenio;
                    cons->setConvenio(convenio);
                }
            }
        }

    }

    if(!pac_encontrado){
        cout<<"Paciente sem consultas com o medico" << med->getNome() << " procurado!"<<endl;
        menuC.pausa();
        return;
    }
    menuC.pausa();
}


//listar todas as consultas da lista de consultas
void listarConsultas(Consultas * consultas){
    vector<Consulta*> lista_consultas;
    lista_consultas = consultas->obterListaConsultas();
    menuC.limpa_tela();
    cout<<"______________________________________________"<<endl;
    cout<<"             Listagem de Consultas              "<<endl;
    cout<<"______________________________________________"<<endl;
    for(auto cons: lista_consultas){
        cons->printConsulta();
    }
    menuC.pausa();
}

