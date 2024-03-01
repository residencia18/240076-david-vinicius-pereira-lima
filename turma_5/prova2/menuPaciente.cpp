//implementacao da classe menu de paciente
#include "menuPaciente.hpp"
//metodo para limpar tela
void MenuPaciente::limpa_tela(){
    system("cls");
}
//metodo para pausar o menu de paciente
void MenuPaciente::pausa(){
    cout<<"Pressione qualquer tecla para continuar..."<<endl;
    getch();
    cout << endl;
}
//metodo para mostrar o menu de paciente
void MenuPaciente::mostra_menu(void){
    limpa_tela();
    cout << "Opcoes disponiveis: " << endl;
    for(auto item : itens_menu){
        cout << item << endl;
    }

}
//metodo para obter a opcao do menu de paciente
int MenuPaciente::obter_opcao(void){
    int opcao;
    while(true){
        cout << "Digite a sua opcao: "; cin >> opcao;
        if ((opcao >=0) && (opcao <= itens_menu.size()-1))
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

