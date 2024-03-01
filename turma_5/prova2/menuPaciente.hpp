//cabecalho do menu de pacientes
#include <iostream>
#include <string>
#include <vector>
#include <conio.h>

using namespace std;

class MenuPaciente{
    private:
        vector<string> itens_menu = {"1. Incluir Paciente", 
                                     "2. Excluir Paciente",
                                     "3. Alterar Paciente(Apenas por CPF)",
                                     "4. Listar Pacientes", 
                                     "5. Localizar Paciente(Apenas por CPF)",
                                     "0. Sair"
                                     };

    public:
        void mostra_menu(void);
        int obter_opcao(void);
        void limpa_tela(void);
        void pausa(void);
        unsigned long long int converteCpf(string cpf);
};