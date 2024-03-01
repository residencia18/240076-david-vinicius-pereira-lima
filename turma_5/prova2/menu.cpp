//implementacao da classe Menu
#include "menu.hpp"
//metodo para limpar tela
void Menu::limpa_tela(){
    system("cls");
}
//metodo para pausar a tela
void Menu::pausa(){
    cout<<"Pressione qualquer tecla para continuar..."<<endl;
    getch();
    cout << endl;
}
//metodo para mostrar o menu
void Menu::mostra_menu(void){
    limpa_tela();
    cout << "Opcoes disponiveis: " << endl;
    for(auto item : itens_menu){
        cout << item << endl;
    }

}
//metodo para obter a opcao do menu
int Menu::obter_opcao(void){
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