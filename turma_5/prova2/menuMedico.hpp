//cabecalho da classe MenuMedico
#include <iostream>
#include <string>
#include <vector>
#include <conio.h>

using namespace std;

class MenuMedico{
    private:
        vector<string> itens_menu = {"1. Incluir Medico", 
                                     "2. Excluir Medico",
                                     "3. Alterar Medico(Apenas por CRM)",
                                     "4. Listar Medicos", 
                                     "5. Localizar Medico(Apenas por CRM)",
                                     "0. Sair"
                                     };

    public:
        void mostra_menu(void);
        int obter_opcao(void);
        void limpa_tela(void);
        void pausa(void);
};