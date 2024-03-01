//funcao principal para rodar o menu de pacientes
#include "menuPaciente.hpp"
#include "func_prog_pac.hpp"

int main(void){
    MenuPaciente menuP;
    Paciente paciente;
    Pacientes pacientes;

    while(true)
    {
        menuP.mostra_menu();
        int opcao = menuP.obter_opcao();

        switch (opcao){

            case 0: exit(0);
                    break; 
            
            case 1: incluirPaciente(&pacientes);
                    break;
            
            case 2: excluirPaciente(&pacientes);
                    break;        
            
            case 3: alterarPaciente(&pacientes);
                    break;
            
            case 4: listarPacientes(&pacientes);
                    break;
            
            case 5: localizarPaciente(&pacientes);
                    break;

            default:
                break;
        }
    }

    return 0;
}