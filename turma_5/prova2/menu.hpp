//cabecalho da classe Menu
#ifndef MENU_HPP
#define MENU_HPP

#include <iostream>
#include <string>
#include <vector>
#include <conio.h>

using namespace std;

class Menu{
    private:
        vector<string> itens_menu = {"1. Gestao de Pacientes", 
                                     "2. Gestao de Medicos",
                                     "3. Gestao de Consultas",
                                     "0. Sair"
                                     };
                                     
    public:
        void mostra_menu(void);
        int obter_opcao(void);
        void limpa_tela(void);
        void pausa(void);
};

#endif