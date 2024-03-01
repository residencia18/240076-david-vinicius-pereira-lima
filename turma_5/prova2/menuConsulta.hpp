//cabecalho do menu de consulta
#include <iostream>
#include <string>
#include <vector>
#include <conio.h>

using namespace std;

class MenuConsulta{
    private:
        vector<string> itens_menu = {"1. Incluir Consulta", 
                                     "2. Excluir Consulta",
                                     "3. Alterar Consulta",
                                     "4. Listar Consultas", 
                                     "0. Sair"
                                     };

    public:
        void mostra_menu(void);
        int obter_opcao(void);
        void limpa_tela(void);
        void pausa(void);
};