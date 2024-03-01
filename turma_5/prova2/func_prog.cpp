//implementacao do menu principal
#include "func_prog.hpp"

void abrirMenuPaciente(Pacientes * pacientes){
    MenuPaciente menuP;

    while(true)
    {
        menuP.mostra_menu();
        int opcao = menuP.obter_opcao();

        switch (opcao){

            case 0: return;
                    break; 
            
            case 1: incluirPaciente(pacientes);
                    break;
            
            case 2: excluirPaciente(pacientes);
                    break;        
          
            case 3: alterarPaciente(pacientes);
                    break;
            
            case 4: listarPacientes(pacientes);
                    break;
            
            case 5: localizarPaciente(pacientes);
                    break;
            
            default:
                break;
        }
    }


}

void abrirMenuMedico(Medicos * medicos){
    MenuMedico menuM;

    while(true)
    {
        menuM.mostra_menu();
        int opcao = menuM.obter_opcao();

        switch (opcao){

            case 0: return;
                    break; 
            
            case 1: incluirMedico(medicos);
                    break;
            
            case 2: excluirMedico(medicos);
                    break;        
          
            case 3: alterarMedico(medicos);
                    break;
            
            case 4: listarMedicos(medicos);
                    break;
            
            case 5: localizarMedico(medicos);
                    break;
            
            default:
                break;
        }
    }


}

void abrirMenuConsultas(Consultas * consultas, Pacientes * pacientes, Medicos * medicos){
    MenuConsulta menuC;

    while(true)
    {
        menuC.mostra_menu();
        int opcao = menuC.obter_opcao();

        switch (opcao){

            case 0: return;
                    break; 
            
            case 1: incluirConsulta(consultas, pacientes, medicos);
                    break;
            
            case 2: excluirConsulta(consultas, medicos, pacientes);
                    break;   

            case 3: alterarConsulta(consultas, medicos, pacientes);
                    break;

            case 4: listarConsultas(consultas);
                    break;
            
            default:
                break;
        }
    }
}