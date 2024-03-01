//implementacao da classe menu de medico
#include "menuMedico.hpp"
//metodo para limpar tela
void MenuMedico::limpa_tela(){
    system("cls");
}
//metodo para pausar o menu de medico
void MenuMedico::pausa(){
    cout<<"Pressione qualquer tecla para continuar..."<<endl;
    getch();
    cout << endl;
}
//metodo para mostrar o menu de medico
void MenuMedico::mostra_menu(void){
    limpa_tela();
    cout << "Opcoes disponiveis: " << endl;
    for(auto item : itens_menu){
        cout << item << endl;
    }

}
//metodo para obter a opcao do menu de medico
int MenuMedico::obter_opcao(void){
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
