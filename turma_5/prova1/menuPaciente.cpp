//implementacao das funcoes do menu do paciente
#include "menuPaciente.hpp"

void MenuPaciente::limpa_tela(){
    system("cls");
}

void MenuPaciente::pausa(){
    cout<<"Pressione qualquer tecla para continuar..."<<endl;
    getch();
    cout << endl;
}

void MenuPaciente::mostra_menu(void){
    limpa_tela();
    cout << "Opcoes disponiveis: " << endl;
    for(auto item : itens_menu){
        cout << item << endl;
    }

}

int MenuPaciente::obter_opcao(void){
    int opcao;
    while(true){
        cout << "Digite a sua opcao: "; cin >> opcao;
        if ((opcao >=0) && (opcao <= itens_menu.size()))
            return opcao;
        else
            cout << endl << "Opcao invalida, sua opcao deve estar entre 1 e " 
                 << itens_menu.size() << endl;
    }
}

unsigned long long int MenuPaciente::converteCpf(string cpf){
    unsigned long long int cpf_int;
    for(auto caracter: cpf){
        if(caracter == '.'){
            cpf.erase(cpf.find(caracter),1);
        }
        else if(caracter == '-'){
            cpf.erase(cpf.find(caracter),1);
        }
    }
    cpf_int = stoll(cpf);
    return cpf_int; 
}

