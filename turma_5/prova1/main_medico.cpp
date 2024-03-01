//funcao principal para rodar o menu de medicos
#include "menuMedico.hpp"
#include "func_prog_med.hpp"

int main(void){
    MenuMedico menuP;
    Medicos medicos;

    while(true)
    {
        menuP.mostra_menu();
        int opcao = menuP.obter_opcao();

        switch (opcao){

            case 0: exit(0);
                    break; 
            
            case 1: incluirMedico(&medicos);
                    break;
            
            case 2: excluirMedico(&medicos);
                    break;        
          
            case 3: alterarMedico(&medicos);
                    break;
            
            case 4: listarMedicos(&medicos);
                    break;

            case 5: localizarMedico(&medicos);
                    break;
                    
            default:
                break;
        }
    }

    return 0;
}